package dev.vrba.cartographers.engine.map;

import com.sun.istack.NotNull;
import dev.vrba.cartographers.engine.Material;
import lombok.Data;

import java.util.Set;

@Data
public class TilesCluster {

    private final Map map;

    private final Material material;

    private final Set<Tile> tiles;

    public TilesCluster(final @NotNull Set<Tile> tiles) {
        Tile tile = tiles.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tile set passed to a tiles cluster cannot be empty"));

        this.tiles = tiles;
        this.material = tile.getMaterial();
        this.map = tile.getMap();
    }

    public int getSize() {
        return this.tiles.size();
    }
}
