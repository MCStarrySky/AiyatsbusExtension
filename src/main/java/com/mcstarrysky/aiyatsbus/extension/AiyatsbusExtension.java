package com.mcstarrysky.aiyatsbus.extension;

import com.mcstarrysky.aiyatsbus.core.Aiyatsbus;
import com.mcstarrysky.aiyatsbus.core.AiyatsbusEnchantmentBase;
import com.mcstarrysky.aiyatsbus.core.AiyatsbusEventExecutor;
import com.mcstarrysky.aiyatsbus.core.data.trigger.event.EventMapping;
import com.mcstarrysky.aiyatsbus.core.data.trigger.event.EventResolver;
import com.mcstarrysky.aiyatsbus.module.kether.AiyatsbusKetherRegistry;
import com.mcstarrysky.aiyatsbus.taboolib.module.configuration.Configuration;
import com.mcstarrysky.aiyatsbus.taboolib.module.configuration.Type;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class AiyatsbusExtension extends JavaPlugin {

    private static final Map<String, EventMapping> mappings = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new VanillaListener(), this);

        final File mappingFile = new File(getDataFolder(), "event-mapping.yml");
        if (!mappingFile.exists()) {
            saveResource("event-mapping.yml", true);
        }

        final FileConfiguration eventMappings = YamlConfiguration.loadConfiguration(mappingFile);
        Objects.requireNonNull(eventMappings.getConfigurationSection("mappings"))
                .getKeys(false).forEach(key -> {
                    final ConfigurationSection section = eventMappings.getConfigurationSection("mappings." + key);
                    assert section != null;
                    final com.mcstarrysky.aiyatsbus.taboolib.library.configuration.ConfigurationSection tbSection
                            = Configuration.Companion.loadFromString(ConfigUtils.saveToString(section), Type.YAML, true);
                    final EventMapping mapping = new EventMapping(tbSection);

                    // 这个 EventMappings 是由你自己维护的一个列表, 要添加到 Aiyatsbus 的外部 EventMappings 列表里
                    // 注意这里的监听器 id 与其他拓展和 Aiyatsbus 共享, 必须是独一无二的
                    AiyatsbusEventExecutor.Companion.getExternalMappings().put(key, mapping);

                    Aiyatsbus.INSTANCE.api().getEventExecutor().registerListener(key, mapping);

                    mappings.put(key, mapping);
                });

        AiyatsbusKetherRegistry.INSTANCE.registerProperty(PropertyPlayerJumpEvent.class, new PropertyPlayerJumpEvent());

        final EventResolver<PlayerJumpEvent> resolver = new EventResolver<>(
                (event, player) -> event.getPlayer(),
                (event) -> {
                },
                (event, item, entity) -> null
        );

        AiyatsbusEventExecutor.Companion.getResolver().put(PlayerJumpEvent.class, resolver);
        CustomizeOperation.initialize();

        final File testEnchantmentFile = new File(getDataFolder(), "test.yml");
        if (!testEnchantmentFile.exists()) {
            saveResource("test.yml", true);
        }

        Aiyatsbus.INSTANCE.api().getEnchantmentManager().register(
                new AiyatsbusEnchantmentBase(
                        "test",
                        testEnchantmentFile,
                        Configuration.Companion.loadFromFile(testEnchantmentFile, Type.YAML, true)
                )
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // 销毁自己插件注册的监听器
        mappings.forEach((listen, mapping) -> Aiyatsbus.INSTANCE.api().getEventExecutor().destroyListener(listen));
        mappings.clear();
    }
}
