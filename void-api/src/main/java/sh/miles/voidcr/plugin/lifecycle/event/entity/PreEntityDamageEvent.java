package sh.miles.voidcr.plugin.lifecycle.event.entity;

import sh.miles.voidcr.entity.Entity;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.server.Server;

/**
 * Event that takes place before an entity attacks
 *
 * @since 0.3.22
 */
public interface PreEntityDamageEvent extends EntityEvent, LifecycleEvent<Server> {

    int getInvulnerabilityFrames();

    void setInvulnerabilityFrames(int invulnerabilityFrames);

    float getDamage();

    void setDamage(float pendingDamage);

    /**
     * Gets the targeted entity
     *
     * @return the targeted entity
     * @since 0.3.22
     */
    Entity getDamager();
}
