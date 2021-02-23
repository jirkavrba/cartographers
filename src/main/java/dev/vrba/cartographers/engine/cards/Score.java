package dev.vrba.cartographers.engine.cards;

import lombok.Data;

@Data
public class Score {

    public final int firstEdictCardEvaluation;

    public final int secondEdictCardEvaluation;

    public final int acquiredCoins;

    public final int ambushedTilesLoss;

    public int getTotal() {
        return firstEdictCardEvaluation + secondEdictCardEvaluation + acquiredCoins - ambushedTilesLoss;
    }
}
