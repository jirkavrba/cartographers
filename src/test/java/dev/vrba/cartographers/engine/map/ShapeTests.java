package dev.vrba.cartographers.engine.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTests {

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testShapeCanBeCreatedUsingDefinition() {
        // This is just to make things aligned and easier to test
        boolean y = true;
        boolean n = false;
        boolean[][] definition =  new boolean[][] {
            { y, n, n, y },
            { y, y, y, y },
            { y, y, n, n }
        };

        Shape shape = new Shape(definition);

        assertEquals(4, shape.getWidth());
        assertEquals(3, shape.getHeight());
    }

    @Test
    public void testShapeCanBeCreatedUsingShortFormat() {
        Shape shape = new Shape(
            "...#," +
                     "####," +
                     "#..."
        );

        assertEquals(4, shape.getWidth());
        assertEquals(3, shape.getHeight());

        assertFalse(shape.tileAt(0, 0));
        assertFalse(shape.tileAt(1, 0));
        assertFalse(shape.tileAt(2, 0));
        assertTrue(shape.tileAt(3, 0));
        assertTrue(shape.tileAt(0, 1));
        assertTrue(shape.tileAt(1, 1));
        assertTrue(shape.tileAt(2, 1));
        assertTrue(shape.tileAt(3, 1));
        assertTrue(shape.tileAt(0, 2));
        assertFalse(shape.tileAt(1, 2));
        assertFalse(shape.tileAt(2, 2));
        assertFalse(shape.tileAt(3, 2));
    }

    @Test
    public void testShapeCannotBeCreatedUsingInvalidShortFormat() {
        assertEquals(
                "Cannot create an empty shape.",
                assertThrows(IllegalArgumentException.class, () -> new Shape("")).getMessage()
        );

        assertEquals(
                "Row [....#] doesn't have a matching length.",
                assertThrows(IllegalArgumentException.class, () -> new Shape("...,.#.,....#,###")).getMessage()
        );

        assertEquals(
                "Cannot match char [x] to a shape definition.",
                assertThrows(IllegalArgumentException.class, () -> new Shape("...,.#.,x.#,###")).getMessage()
        );
    }

    @Test
    public void testShapeCanBeRotatedClockwise() {
        Shape original = new Shape(
            "..#",
            "###"
        );

        Shape rotated = original.rotateClockwise();
        Shape expected = new Shape(
                "##",
                ".#",
                ".#"
        );

        assertEquals(expected.getWidth(), rotated.getWidth());
        assertEquals(expected.getHeight(), rotated.getHeight());

        for (int x = 0; x < expected.getWidth(); x++) {
            for (int y = 0; y < expected.getHeight(); y++) {
                assertEquals(expected.tileAt(x, y), rotated.tileAt(x, y));
            }
        }
    }

    @Test
    public void testShapeCanBeRotatedCounterClockwise() {
        Shape original = new Shape(
                "..#",
                "###"
        );

        Shape rotated = original.rotateCounterClockwise();
        Shape expected = new Shape(
                "#.",
                "#.",
                "##"
        );

        assertEquals(expected.getWidth(), rotated.getWidth());
        assertEquals(expected.getHeight(), rotated.getHeight());

        for (int x = 0; x < expected.getWidth(); x++) {
            for (int y = 0; y < expected.getHeight(); y++) {
                assertEquals(expected.tileAt(x, y), rotated.tileAt(x, y));
            }
        }
    }
}
