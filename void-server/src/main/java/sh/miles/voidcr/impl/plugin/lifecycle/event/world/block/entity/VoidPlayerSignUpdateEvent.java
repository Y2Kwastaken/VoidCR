package sh.miles.voidcr.impl.plugin.lifecycle.event.world.block.entity;

import com.badlogic.gdx.graphics.Color;
import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.blockentities.BlockEntitySign;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.world.Zone;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.plugin.lifecycle.event.world.VoidWorldEvent;
import sh.miles.voidcr.impl.world.position.VoidBlockPos;
import sh.miles.voidcr.plugin.lifecycle.event.world.block.entity.PlayerSignUpdateEvent;
import sh.miles.voidcr.server.Server;
import sh.miles.voidcr.world.position.BlockPos;

public abstract class VoidPlayerSignUpdateEvent extends VoidWorldEvent implements PlayerSignUpdateEvent {

    protected String[] lines;
    protected Color color;
    protected float fontSize;
    protected final BlockPos pos;
    protected final PlayerEntity player;

    public VoidPlayerSignUpdateEvent(final Server context, final Zone zone, BlockEntitySign sign, final String[] lines, Color color, float fontSize, final Player player) {
        super(context, zone);
        this.lines = lines;
        this.color = color;
        this.fontSize = fontSize;
        this.player = ((finalforeach.cosmicreach.entities.player.PlayerEntity) player.getEntity()).getVoidMirror();
        this.pos = VoidBlockPos.fromCRBlockEntity(sign);
    }

    @Override
    public String[] getLines() {
        Preconditions.checkState(lines.length == 5, "The length of any sign array must be 5, this is a bug with VoidCR");

        final String[] copy = new String[lines.length];
        System.arraycopy(this.lines, 0, copy, 0, copy.length);
        return copy;
    }

    @Override
    public Color getColor() {
        return new Color(this.color);
    }

    @Override
    public float getTextSize() {
        return this.fontSize;
    }

    @Override
    public BlockPos getBlockPos() {
        return this.pos;
    }

    @Override
    public PlayerEntity getPlayer() {
        return this.player;
    }
}
