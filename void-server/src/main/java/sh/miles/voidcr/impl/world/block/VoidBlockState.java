package sh.miles.voidcr.impl.world.block;

import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.world.block.BlockState;
import sh.miles.voidcr.world.block.BlockType;

public class VoidBlockState implements BlockState, Mirrored<finalforeach.cosmicreach.blocks.BlockState> {

    private final finalforeach.cosmicreach.blocks.BlockState mirror;

    public VoidBlockState(finalforeach.cosmicreach.blocks.BlockState mirror) {
        this.mirror = mirror;
    }

    @Override
    public finalforeach.cosmicreach.blocks.BlockState getMirror() {
        return this.mirror;
    }

    @Override
    public boolean isOpaque() {
        return this.mirror.isOpaque;
    }

    @Override
    public boolean canWalkThrough() {
        return this.mirror.isOpaque;
    }

    @Override
    public boolean isFluid() {
        return this.mirror.isFluid;
    }

    @Override
    public boolean isOccluding() {
        return this.mirror.isPosXFaceOccluding ||
                this.mirror.isNegXFaceOccluding ||
                this.mirror.isPosYFaceOccluding ||
                this.mirror.isNegYFaceOccluding ||
                this.mirror.isPosZFaceOccluding ||
                this.mirror.isNegZFaceOccluding ||
                this.mirror.isSelfPosXFaceOccluding ||
                this.mirror.isSelfNegXFaceOccluding ||
                this.mirror.isSelfPosYFaceOccluding ||
                this.mirror.isSelfNegYFaceOccluding ||
                this.mirror.isSelfPosZFaceOccluding ||
                this.mirror.isSelfNegZFaceOccluding ||
                this.mirror.isPosXFacePartOccluding ||
                this.mirror.isNegXFacePartOccluding ||
                this.mirror.isPosYFacePartOccluding ||
                this.mirror.isNegYFacePartOccluding ||
                this.mirror.isPosZFacePartOccluding ||
                this.mirror.isNegZFacePartOccluding;
    }

    @Override
    public BlockType getBlockType() {
        return VoidBlockType.toVoid(this.mirror.getBlock());
    }
}
