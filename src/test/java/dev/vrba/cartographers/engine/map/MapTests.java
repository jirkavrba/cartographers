package dev.vrba.cartographers.engine.map;

import dev.vrba.cartographers.engine.Material;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class MapTests {
    @Test
    public void testMapCanBeCreatedWithDefaultFactoryMethod() {
        Map map = Map.createDefault();

        assertEquals(6, map.getRuins().size());
        assertEquals(11, map.getTiles().length);
        assertEquals(11, map.getTiles()[0].length);
    }

    @Test
    public void testCanGetTileReferenceUsingMapTileAt() {
        Map map = Map.createDefault();
        Tile tile = map.tileAt(10, 10);

        assertNotNull(tile);
        assertEquals(map, tile.getMap());
        assertEquals(10, tile.getPosition().getX());
        assertEquals(10, tile.getPosition().getY());
        assertEquals(Material.EMPTY, tile.getMaterial());
    }

    @Test
    public void testCannotGetTileReferenceUsingInvalidCoordinates() {
        Map map = Map.createDefault();

        assertThrows(IllegalArgumentException.class, () -> map.tileAt(11, 11));
        assertThrows(IllegalArgumentException.class, () -> map.tileAt(10, 11));
        assertThrows(IllegalArgumentException.class, () -> map.tileAt(11, 10));
        assertThrows(IllegalArgumentException.class, () -> map.tileAt(0, -1));
        assertThrows(IllegalArgumentException.class, () -> map.tileAt(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> map.tileAt(-1, -1));
    }

    @Test
    public void testCanCreateInstanceWithBlueprint() {
        char[][] blueprint = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'f', '.', 'f', '.', 'f', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'f', '.', 'f', '.', 'f', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'f', '.', 'f', '.', 'f', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        };

        Map map = assertDoesNotThrow(() -> Map.createFromBlueprint(blueprint, Set.of()));
        Set<Tile> forests = map.tilesByMaterial(Material.FOREST);
        Set<Position> positions = forests.stream().map(Tile::getPosition).collect(Collectors.toSet());

        assertEquals(9, forests.size());

        assertTrue(positions.containsAll(Set.of(
                new Position(1, 1),
                new Position(3, 1),
                new Position(5, 1),
                new Position(1, 4),
                new Position(3, 4),
                new Position(5, 4),
                new Position(1, 7),
                new Position(3, 7),
                new Position(5, 7)
        )));
    }
}
