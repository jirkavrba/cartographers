package dev.vrba.cartographers.engine.cards;

import lombok.Data;

import java.util.Set;

@Data
public class Season {

    private final String name;

    private final int timeThreshold;

    private final Set<EdictCard> activeEdictCards;
}
