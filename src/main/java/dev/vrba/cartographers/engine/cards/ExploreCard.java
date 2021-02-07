package dev.vrba.cartographers.engine.cards;

import lombok.Data;

@Data
public class ExploreCard {
    private final String name;
    private final ExploreCardVariant[] variants;
    private final int timeValue;
}
