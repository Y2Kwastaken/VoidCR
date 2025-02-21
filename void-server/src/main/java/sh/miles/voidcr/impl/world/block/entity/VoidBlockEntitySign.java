package sh.miles.voidcr.impl.world.block.entity;

import com.badlogic.gdx.graphics.Color;
import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.networking.packets.blocks.SignsEntityPacket;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import sh.miles.voidcr.world.block.entity.BlockEntitySign;

public class VoidBlockEntitySign extends VoidBlockEntity<finalforeach.cosmicreach.blockentities.BlockEntitySign> implements BlockEntitySign {
    public VoidBlockEntitySign(final finalforeach.cosmicreach.blockentities.BlockEntitySign mirror) {
        super(mirror);
    }

    @Override
    public String[] getLines() {
        final var lines = mirror.getText();
        final String[] copy = new String[lines.length];
        System.arraycopy(lines, 0, copy, 0, copy.length);
        Preconditions.checkState(copy.length == 5, "The copied sign text array is not of length 5, this is a bug!");
        return copy;
    }

    @Override
    public void setLines(final String... lines) {
        Preconditions.checkArgument(lines != null, "The provided lines must not be null");
        Preconditions.checkArgument(lines.length <= 5, "The given sign lines is larger than size 5, which is not permitted");
        final String[] copy = new String[5];
        final var length = lines.length;
        for (int i = 0; i < copy.length; i++) {
            copy[i] = i >= length ? "" : lines[i];
        }

        super.mirror.setTexts(lines);
        refresh();
    }

    @Override
    public Color getColor() {
        return new Color(super.mirror.getTextColor());
    }

    @Override
    public void setColor(final Color color) {
        Preconditions.checkArgument(color != null, "the provided color must not be null");
        super.mirror.setTextColor(new Color(color));
        refresh();
    }

    @Override
    public float getTextSize() {
        return super.mirror.getFontSize();
    }

    @Override
    public void setTextSize(final float size) {
        super.mirror.setFontSize(size);
        refresh();
    }

    private void refresh() {
        ServerSingletons.SERVER.broadcast(super.mirror.getZone(), new SignsEntityPacket(super.mirror));
    }
}
