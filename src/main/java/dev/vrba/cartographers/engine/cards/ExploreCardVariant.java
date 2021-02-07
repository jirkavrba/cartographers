package dev.vrba.cartographers.engine.cards;

import dev.vrba.cartographers.engine.Material;
import dev.vrba.cartographers.engine.map.Shape;
import lombok.Data;

@Data
public class ExploreCardVariant {
    private final Shape shape;
    private final Material material;
    private final boolean coinReward;
}
