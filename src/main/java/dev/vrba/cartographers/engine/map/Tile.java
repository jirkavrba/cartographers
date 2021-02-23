package dev.vrba.cartographers.engine.map;

import com.sun.istack.NotNull;
import dev.vrba.cartographers.engine.Material;
import lombok.Data;

@Data
public class Tile {
    private final Map map;
    private final Material material;
    private final Position position;

    public Tile(@NotNull Map map, @NotNull Material material, @NotNull Position position) {
        this.map = map;
        this.material = material;
        this.position = position;
    }

    public boolean isEmpty() {
        return material == Material.EMPTY;
    }
}
