package dev.vrba.cartographers.engine;

public enum Material {
    EMPTY('.'),
    MOUNTAIN('m'),
    MONSTER('M'),
    FOREST('f'),
    VILLAGE('v'),
    FARM('F'),
    WATER('w');

    public final char character;

    Material(char character) {
        this.character = character;
    }
}
