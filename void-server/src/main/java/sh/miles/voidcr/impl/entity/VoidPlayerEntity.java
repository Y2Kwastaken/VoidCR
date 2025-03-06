package sh.miles.voidcr.impl.entity;

import com.google.common.base.Preconditions;
import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.networking.packets.MessagePacket;
import finalforeach.cosmicreach.networking.packets.entities.PlayerPositionPacket;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.world.World;
import sh.miles.voidcr.world.inventory.container.ItemContainer;
import sh.miles.voidcr.world.position.Position;

public final class VoidPlayerEntity extends VoidEntity implements PlayerEntity {

    public VoidPlayerEntity(finalforeach.cosmicreach.entities.player.PlayerEntity mirror) {
        super(mirror);
        Preconditions.checkState(player() != null, "Every VoidPlayerEntity must have a player associated with it");
    }

    @Override
    public void teleport(final World world, final Position to) {
        super.teleport(world, to);
        // notify player they have been moved
        final var connection = ServerSingletons.getConnection(player());
        connection.send(new PlayerPositionPacket(player()));
    }

    @Override
    public ItemContainer getItemContainer() {
        return player().inventory.getVoidMirror();
    }

    @Override
    public ItemContainer getInventory() {
        return player().inventory.getVoidMirror();
    }

    @Override
    public void respawn() {
        player().respawn(player().getZone());
    }

    @Override
    public World getWorld() {
        return player().getZone().getVoidMirror();
    }

    @Override
    public boolean isSprinting() {
        return player().isSprinting;
    }

    @Override
    public boolean isProne() {
        return player().isProne;
    }

    @Override
    public boolean isDead() {
        return player().isDead();
    }

    @Override
    public String getDisplayName() {
        return player().getUsername();
    }

    @Override
    public boolean isOperator() {
        return ServerSingletons.OP_LIST.hasAccount(player().getAccount());
    }

    @Override
    public finalforeach.cosmicreach.entities.player.PlayerEntity getMirror() {
        return (finalforeach.cosmicreach.entities.player.PlayerEntity) super.mirror;
    }

    private Player player() {
        return getMirror().player;
    }

    @Override
    public void sendMessage(final String message) {
        final var connection = ServerSingletons.getConnection(player());
        connection.send(new MessagePacket(message));
    }
}
