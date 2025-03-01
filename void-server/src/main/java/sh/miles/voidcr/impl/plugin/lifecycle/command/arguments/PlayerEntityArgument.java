package sh.miles.voidcr.impl.plugin.lifecycle.command.arguments;

import finalforeach.cosmicreach.GameSingletons;
import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.entity.PlayerEntity;
import sh.miles.voidcr.impl.entity.VoidPlayerEntity;
import sh.miles.voidcr.plugin.lifecycle.command.CommandArgumentResolver;

@NullMarked
public final class PlayerEntityArgument implements CommandArgumentResolver<PlayerEntity> {

    @Override
    public PlayerEntity resolve(final String literal) throws IllegalStateException {
        final var player = GameSingletons.world.nameToPlayer.get(literal);
        if (player == null) {
            throw new IllegalStateException("Failed to parse argument %s as type PlayerEntity".formatted(literal));
        }
        return (VoidPlayerEntity) player.getEntity().getVoidMirror();
    }
}
