package com.mcstarrysky.aiyatsbus.extension;

import com.mcstarrysky.aiyatsbus.module.kether.AiyatsbusGenericProperty;
import com.mcstarrysky.aiyatsbus.module.kether.AiyatsbusProperty;
import com.mcstarrysky.aiyatsbus.taboolib.common.OpenResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AiyatsbusExtension
 * com.mcstarrysky.aiyatsbus.extension.PropertyPlayerJumpEvent
 *
 * @author mical
 * @since 2024/7/18 16:39
 */
@AiyatsbusProperty(
        id = "player-jump-event",
        bind = PlayerJumpEvent.class
)
public class PropertyPlayerJumpEvent extends AiyatsbusGenericProperty<PlayerJumpEvent> {

    public PropertyPlayerJumpEvent() {
        super("player-jump-event");
    }

    @NotNull
    @Override
    public OpenResult readProperty(@NotNull PlayerJumpEvent instance, @NotNull String key) {
        Object property = null;
        switch (key) {
            case "player":
                property = instance.getPlayer();
                break;
            default:
                return OpenResult.failed();
        }
        return OpenResult.successful(property);
    }

    @NotNull
    @Override
    public OpenResult writeProperty(@NotNull PlayerJumpEvent instance, @NotNull String key, @Nullable Object value) {
        return OpenResult.failed();
    }
}
