package sh.miles.voidcr.world;

import com.badlogic.gdx.math.Vector3;
import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.entity.EntityIdentifier;
import sh.miles.voidcr.util.NamedKey;

import java.util.Collection;

/**
 * Represents a singular world within CosmicReach, which contains entities, chunks, blocks.
 *
 * @since 0.3.14
 */
public interface World {

    /**
     * Gets the world spawn point
     *
     * @return the world spawn point
     * @since 0.3.14
     */
    Vector3 getWorldSpawn();

    /**
     * Gets an entity from a given entity identifier
     *
     * @param identifier the identifier
     * @return the identifier
     * @since 0.3.14
     */
    Entity getEntity(final EntityIdentifier identifier);

    /**
     * Removes an entity from this world
     *
     * @param entity the entity to remove
     * @since 0.3.14
     */
    void removeEntity(final Entity entity);

    /**
     * Gets all entities within this world
     *
     * @return all entities within this world
     * @since 0.3.14
     */
    Collection<Entity> getEntities();

    /**
     * Gets the universe this world belongs too
     *
     * @return the universe of this world
     * @since 0.3.14
     */
    Universe getUniverse();

    /**
     * Gets the world id
     *
     * @return the world id
     * @since 0.3.14
     */
    NamedKey getWorldId();

}
