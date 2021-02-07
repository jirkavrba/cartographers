package dev.vrba.cartographers.engine.map;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class Shape {
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
}
