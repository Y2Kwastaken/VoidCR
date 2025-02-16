package sh.miles.voidcr.impl.entity;

import sh.miles.voidcr.entity.DroneTrapEntity;

public class VoidDroneTrapEntity extends VoidEntity implements DroneTrapEntity {

    public VoidDroneTrapEntity(final finalforeach.cosmicreach.entities.DroneTrapEntity mirror) {
        super(mirror);
    }

    @Override
    public void release() {
        getMirror().releaseTrap();
    }

    @Override
    public finalforeach.cosmicreach.entities.DroneTrapEntity getMirror() {
        return (finalforeach.cosmicreach.entities.DroneTrapEntity) super.mirror;
    }
}
