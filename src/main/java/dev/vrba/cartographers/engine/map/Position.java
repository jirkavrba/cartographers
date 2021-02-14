package dev.vrba.cartographers.engine.map;

import com.sun.istack.NotNull;
import lombok.Data;

/**
 * Class representing a location on map sheet
 */
@Data
public class Position {
    /**
     * The X value of the given position (represented by number 1 .. 11 on the game board)
     */
    private final int x;

    /**
     * The Y value of the given position (represented by letters A .. H on the game board)
     */
    private final int y;

    public Position(@NotNull int x, @NotNull int y) {
       this.x = x;
       this.y = y;
    }

    @NotNull
    public Position add(@NotNull Position other) {
        return new Position(x + other.getX(), y + other.getY());
    }

    @NotNull
    public Position add(@NotNull int x, @NotNull int y) {
        return new Position(this.x + x, this.y + y);
    }
}
