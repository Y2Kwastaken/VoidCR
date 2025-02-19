package sh.miles.voidcr.impl.world;

import sh.miles.voidcr.util.Mirrored;
import sh.miles.voidcr.world.Chunk;

public class VoidChunk implements Chunk, Mirrored<finalforeach.cosmicreach.world.Chunk> {

    private final finalforeach.cosmicreach.world.Chunk mirror;

    public VoidChunk(finalforeach.cosmicreach.world.Chunk mirror) {
        this.mirror = mirror;
    }

    @Override
    public int chunkX() {
        return mirror.chunkX;
    }

    @Override
    public int chunkY() {
        return mirror.chunkY;
    }

    @Override
    public int chunkZ() {
        return mirror.chunkZ;
    }

    @Override
    public finalforeach.cosmicreach.world.Chunk getMirror() {
        return this.mirror;
    }
}
