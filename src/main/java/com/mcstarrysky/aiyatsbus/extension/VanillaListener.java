package com.mcstarrysky.aiyatsbus.extension;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

/**
 * AiyatsbusExtension
 * com.mcstarrysky.aiyatsbus.extension.VanillaListener
 *
 * @author mical
 * @since 2024/7/18 16:35
 */
public class VanillaListener implements Listener {

    @EventHandler
    public void onPlayerStatisticIncrement(final PlayerStatisticIncrementEvent event) {
        // 判断方式简单粗暴, 这里仅作为示例来展示附属插件自定义事件
        if (event.getStatistic() == Statistic.JUMP) {
            final PlayerJumpEvent jumpEvent = new PlayerJumpEvent(event.getPlayer());
            Bukkit.getServer().getPluginManager().callEvent(jumpEvent);
            event.setCancelled(jumpEvent.isCancelled());
        }
    }
}
