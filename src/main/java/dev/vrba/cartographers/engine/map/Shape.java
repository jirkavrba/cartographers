package dev.vrba.cartographers.engine.map;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class Shape {
    public static final char SHAPE_EMPTY = '.';
    public static final char SHAPE_FILLED = '#';

    private final int width;
    private final int height;

    private boolean[][] definition;

    public Shape(@NotNull boolean[][] definition) {

        if (definition.length == 0 || definition[0].length == 0) {
            throw new IllegalArgumentException("Shape cannot be empty");
        }

        this.definition = definition;

        this.width = definition[0].length;
        this.height = definition.length;
    }

    public Shape(@NotNull String definition) {
        this(definition.split(","));
    }

    public Shape(@NotNull String... rows) {
        this.width = rows[0].length();
        this.height = rows.length;

        if (width == 0) {
            throw new IllegalArgumentException("Cannot create an empty shape.");
        }

        boolean[][] base = new boolean[height][width];

        for (int y = 0; y < rows.length; y++) {
            final String row = rows[y];

            if (row.length() != width) {
                throw new IllegalArgumentException("Row [" + row + "] doesn't have a matching length.");
            }

            final char[] chars = row.toCharArray();

            for (int x = 0; x < chars.length; x++) {
                char current = chars[x];
                switch (current) {
                    case SHAPE_EMPTY -> base[y][x] = false;
                    case SHAPE_FILLED -> base[y][x] = true;
                    default -> throw new IllegalArgumentException("Cannot match char [" + current + "] to a shape definition.");
                }
            }
        }

        this.definition = base;
    }

    public boolean tileAt(int x, int y) {
        if (x < 0 || x > width || y < 0 || y > height) {
            throw new IndexOutOfBoundsException();
        }

        return definition[y][x];
    }

    @NotNull
    public Shape rotateClockwise() {
        boolean[][] rotated = new boolean[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rotated[x][y] = definition[y][width - 1 - x];
            }
        }

        return new Shape(rotated);
    }

    @NotNull
    public Shape rotateCounterClockwise() {
        boolean[][] rotated = new boolean[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rotated[x][y] = definition[height - 1 - y][x];
            }
        }

        return new Shape(rotated);
    }
}
