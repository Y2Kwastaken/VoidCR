package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.pre;

import com.badlogic.gdx.graphics.Color;
import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.blockentities.BlockEntitySign;
import finalforeach.cosmicreach.entities.player.Player;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity.VoidPlayerSignUpdateEvent;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.pre.PrePlayerSignUpdateEvent;
import sh.miles.voidcr.server.Server;

public class VoidPrePlayerSignUpdateEvent extends VoidPlayerSignUpdateEvent implements PrePlayerSignUpdateEvent {

    private boolean canceled = false;

    public VoidPrePlayerSignUpdateEvent(final Server context, final BlockEntitySign sign, final String[] lines, final Color color, final float fontSize, final Player player) {
        super(context, sign.getZone(), sign, lines, color, fontSize, player);
    }

    @Override
    public void setLines(final String... lines) throws IllegalArgumentException {
        Preconditions.checkState(lines.length <= 5, "The provided number of lines must be between 0 and 5");
        final var copy = new String[5];
        final var length = lines.length;
        for (int i = 0; i < copy.length; i++) {
            copy[i] = i >= length ? "" : lines[i];
        }

        super.lines = copy;
    }

    @Override
    public void setColor(final Color color) {
        super.color = new Color(color);
    }

    @Override
    public void setTextSize(final float size) {
        super.fontSize = size;
    }

    @Override
    public Class<? extends LifecycleEvent<Server>> getEventClass() {
        return PrePlayerSignUpdateEvent.class;
    }

    @Override
    public void setCanceled(final boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean isCanceled() {
        return this.canceled;
    }
}
