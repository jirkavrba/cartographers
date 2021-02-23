package dev.vrba.cartographers.engine.map;

import com.sun.istack.NotNull;
import dev.vrba.cartographers.engine.Material;
import lombok.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Map {
    /**
     * The state representation of each tile on the map arranged in a 11x11 grid
     * Tile loading can be done by calling {@code map.getTileAt}.
     */
    @Setter(AccessLevel.PRIVATE)
    private Tile[][] tiles;

    /**
     * List of positions, which contains ruins
     */
    @Setter(AccessLevel.PRIVATE)
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

        Tile[][] tiles = new Tile[11][11];

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

    /**
     * Creates new map instance from given material blueprint.
     * Material blueprint is a 10x10 matrix of characters, where each character represents a material.
     *
     * For material codes see {@link Material} enum values.
     *
     * @param blueprint 10x10 characters matrix of materials
     * @param ruins set of ruins
     * @return created map instance
     */
    @NotNull
    public static Map createFromBlueprint(@NotNull char[][] blueprint, @NotNull Set<Position> ruins) {
        if (blueprint.length != 11 || blueprint[0].length != 11) {
            throw new IllegalArgumentException("Blueprint must be a 11x11 char matrix!");
        }

        Map instance = new Map();
        Tile[][] tiles = new Tile[11][11];

        for (int x = 0; x <= 10; x++) {
            for (int y = 0; y <= 10; y++) {
                char currentMaterial = blueprint[y][x];

                Position position = new Position(x, y);
                Material material = Arrays.stream(Material.values())
                        .filter(it -> it.character == currentMaterial)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Material \"" + currentMaterial + "\" is not valid."));

                tiles[y][x] = new Tile(instance, material, position);
            }
        }

        instance.setTiles(tiles);
        instance.setRuins(ruins);

        return instance;
    }

    public @NotNull Tile tileAt(@NotNull Position position) {
        return tileAt(position.getX(), position.getY());
    }

    public @NotNull Tile tileAt(int x, int y) {
        if (Math.max(x, y) < 0) {
            throw new IllegalArgumentException("Both x and y coordinates must be positive.");
        }

        try { return tiles[y][x]; }
        catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("Maximum index for both x and y coordinate is 10.", exception);
        }
    }

    public @NotNull Set<Tile> tilesByMaterial(@NotNull Material material) {
        return Arrays.stream(tiles)
            .flatMap(Arrays::stream)
            .filter(tile -> tile.getMaterial() == material)
            .collect(Collectors.toSet());
    }

    public boolean isWithinBounds(@NotNull Position position) {
        return Math.min(position.getX(), position.getY()) >= 0 &&
               Math.max(position.getX(), position.getY()) < 11;
    }
}
