package dev.vrba.cartographers.engine.map;

import dev.vrba.cartographers.engine.Material;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Map {
    /**
     * The state representation of each tile on the map arranged in a 11x11 grid
     * Tile loading can be done by calling {@code map.getTileAt}.
     */
    private Tile[][] tiles;

    /**
     * List of positions, which contains ruins
     */
    private Set<Position> ruins;

    /**
     * Creates a default game board used in each game (standard board)
     * @return built instance of the map
     */
    public static Map createDefault() {
        final Set<Position> mountains = Set.of(
                new Position(3, 1),
                new Position(8, 2),
                new Position(5, 5),
                new Position(2, 8),
                new Position(7, 9)
        );

        final Set<Position> ruins = Set.of(
                new Position(5, 1),
                new Position(1, 2),
                new Position(9, 2),
                new Position(1, 8),
                new Position(9, 8),
                new Position(5, 9)
        );

        Map instance = new Map();

        Tile[][] tiles = new Tile[10][10];

        for (int x = 0; x <= 10; x++) {
            for (int y = 0; y <= 10; y++) {
                Position position = new Position(x, y);
                Material material = mountains.contains(position) ? Material.MOUNTAIN : Material.EMPTY;

                tiles[y][x] = new Tile(instance, material, position);
            }
        }

        instance.setTiles(tiles);
        instance.setRuins(ruins);

        return instance;
    }
}
