package dev.vrba.cartographers.engine.map;

import com.sun.istack.NotNull;
import dev.vrba.cartographers.engine.Material;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Tile> neighbourTiles() {
        List<Position> directions = List.of(
            new Position(-1, 0),
            new Position(1, 0),
            new Position(0, -1),
            new Position(0, 1)
        );

        return directions.stream()
                .map(position -> position.add(this.position))
                .filter(map::isWithinBounds)
                .map(map::tileAt)
                .collect(Collectors.toSet());
    }
}
