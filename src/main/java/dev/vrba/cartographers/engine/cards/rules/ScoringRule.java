package dev.vrba.cartographers.engine.cards.rules;

import com.sun.istack.NotNull;
import dev.vrba.cartographers.engine.map.Map;

public interface ScoringRule {
    String getName();
    String getDescription();
    int evaluate(final @NotNull Map map);
}
