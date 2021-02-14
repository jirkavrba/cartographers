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

        assertFalse(shape.at(0, 0));
        assertFalse(shape.at(1, 0));
        assertFalse(shape.at(2, 0));
        assertTrue(shape.at(3, 0));
        assertTrue(shape.at(0, 1));
        assertTrue(shape.at(1, 1));
        assertTrue(shape.at(2, 1));
        assertTrue(shape.at(3, 1));
        assertTrue(shape.at(0, 2));
        assertFalse(shape.at(1, 2));
        assertFalse(shape.at(2, 2));
        assertFalse(shape.at(3, 2));
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
}
