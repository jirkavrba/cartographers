package dev.vrba.cartographers.engine.map;

import com.sun.istack.NotNull;
import dev.vrba.cartographers.engine.Material;
import lombok.Data;

import java.util.Set;
import java.util.stream.IntStream;

@Data
public class Map {
    /**
     * The state representation of each tile on the map arranged in a 11x11 grid
     * Tile loading can be done by calling {@code map.getTileAt}.
     */
    private Tile[][] tiles;

    /**
     * List of positions, which contains ruins
     */
    private final Set<Position> ruins;

    private Map(@NotNull Tile[][] tiles, @NotNull Set<Position> ruins) {
        this.tiles = tiles;
        this.ruins = ruins;
    }

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

        final Map instance = new Map(new Tile[11][11], ruins);

        final Tile[][] tiles = (Tile[][]) IntStream.range(0, 10).mapToObj(y ->
                IntStream.range(0, 10).mapToObj(x -> {
                    Position position = new Position(x, y);
                    Material material = mountains.contains(position) ? Material.MOUNTAIN : Material.EMPTY;

                    return new Tile(instance, material, position);
                }).toArray()
        ).toArray();

        instance.setTiles(tiles);
        return instance;
    }
}
