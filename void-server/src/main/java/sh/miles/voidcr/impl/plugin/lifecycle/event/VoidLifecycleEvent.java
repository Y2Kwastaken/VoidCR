package sh.miles.voidcr.impl.plugin.lifecycle.event;

import org.jspecify.annotations.NullMarked;
import sh.miles.voidcr.plugin.lifecycle.event.LifecycleEvent;

@NullMarked
public abstract class VoidLifecycleEvent<C> implements LifecycleEvent<C> {

    private final C context;

    public VoidLifecycleEvent(C context) {
        this.context = context;
    }

    @Override
    public C getContext() {
        return this.context;
    }
}
