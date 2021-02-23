package dev.vrba.cartographers.engine.cards.rules;

import com.sun.istack.NotNull;
import dev.vrba.cartographers.engine.map.Map;
import dev.vrba.cartographers.engine.map.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Borderlands implements ScoringRule {

    @Override
    public String getName() {
        return "Borderlands";
    }

    @Override
    public String getDescription() {
        return "Earn six reputation stars for each complete row or complete column of filled spaces.";
    }

    @Override
    public int evaluate(@NotNull Map map) {
        return (int) (IntStream.range(0, 10)
                        .mapToObj(n -> List.of(map.row(n), map.column(n)))
                        .mapToLong(pair -> pair.stream().filter(tiles -> Arrays.stream(tiles).noneMatch(Tile::isEmpty)).count())
                        .sum() * 6);
    }
}
