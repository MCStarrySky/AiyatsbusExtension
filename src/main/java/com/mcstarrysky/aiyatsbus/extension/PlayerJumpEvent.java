package com.mcstarrysky.aiyatsbus.extension;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 示例事件
 * 代表其他插件自定义的事件
 *
 * @author mical
 * @since 2024/7/18 16:30
 */
public class PlayerJumpEvent extends Event implements Cancellable {

    private final Player player;

    public PlayerJumpEvent(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    private static final HandlerList handlerList = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    private boolean isCancelled = false;

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }
}
