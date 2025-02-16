package sh.miles.voidcr.plugin.lifecycle.event.entity.pre;

import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;
import sh.miles.voidcr.plugin.lifecycle.event.entity.EntityDamageEvent;
import sh.miles.voidcr.server.Server;

/**
 * Event that takes place before an entity attacks
 *
 * @since 0.3.22
 */
public interface PreEntityDamageEvent extends EntityDamageEvent, LifecycleEvent<Server> {

    /**
     * Sets the amount of invulnerability frames applied after this event
     * <p>
     * These frames are applied with no explicit maximum and are counted down at a constant rate
     *
     * @param invulnerabilityFrames the amount of invulnerability frames to apply
     * @since 0.3.22
     */
    void setInvulnerabilityFrames(int invulnerabilityFrames);

    /**
     * Sets the amount of damage that results after this event
     *
     * @param pendingDamage the amount of damage to apply
     * @since 0.3.22
     */
    void setDamage(float pendingDamage);

}
