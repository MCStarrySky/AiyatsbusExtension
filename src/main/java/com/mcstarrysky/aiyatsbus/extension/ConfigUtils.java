package com.mcstarrysky.aiyatsbus.extension;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * AiyatsbusExtension
 * com.mcstarrysky.aiyatsbus.extension.ConfigUtils
 *
 * @author mical
 * @since 2024/7/18 23:53
 */
public class ConfigUtils {

    @SuppressWarnings("deprecation")
    public static String saveToString(ConfigurationSection section) {
        Yaml yaml = new Yaml(new YamlConstructor(), new YamlRepresenter(), new DumperOptions());
        return yaml.dumpAsMap(section.getValues(false));
    }
}
