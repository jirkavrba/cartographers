package dev.vrba.cartographers.engine.cards;

import dev.vrba.cartographers.engine.cards.rules.ScoringRule;
import lombok.Data;

@Data
public class EdictCard implements GameCard {

    private final String name;

    private final String text;

    private final Category category;

    private final ScoringRule rule;

    public enum Category {
        A, B, C, D
    }
}
