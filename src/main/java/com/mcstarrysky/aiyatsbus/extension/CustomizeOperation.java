package com.mcstarrysky.aiyatsbus.extension;

import com.mcstarrysky.aiyatsbus.module.kether.operation.Operations;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * AiyatsbusExtension
 * com.mcstarrysky.aiyatsbus.extension.CustomizeOperation
 *
 * @author mical
 * @since 2024/7/18 17:12
 */
public class CustomizeOperation {

    public static void execute(final List<Object> args) {
        execute((Player) args.get(0));
    }

    public static void execute(final Player player) {
        for (int i = 0; i < 5; i++) {
            Bukkit.broadcast(player.name().append(Component.text("的妈死啦!")));
        }
    }

    public static void initialize() {
        Operations.INSTANCE.register("customize", (args) -> {
            execute(new ArrayList<>(args));
            return null; // 由于语句是可以有返回值的, 但显然这句不需要返回一个东西, 所以返回 null
        });
    }
}
