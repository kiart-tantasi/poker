package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * [why I use SortUtils to help handle "tie" cases]
 *
 * let's assume player 1 has "3S 3H 5D 5D AS" and player 2 has "4S 4S 7H 7H AD"
 * which both of them are having two-pairs hand
 *
 * to figure out who wins, we need to sort the card values with specific method
 * (consider two-pairs before high-card)
 * after using this SortUtils to sort, the results will be "5 3 A" and "7 4 A"
 *
 * now we can start to compare the results one-by-one and can easily see that
 * player 2 wins because 7 > 5
 */
class SortUtils {
    public static List<String> sortFourOfAKind(List<String> cards) {
        return sortHand(cards, 4, 1);
    }

    public static List<String> sortFullHouse(List<String> cards) {
        return sortHand(cards, 3, 2);
    }

    public static List<String> sortThreeOfAKind(List<String> cards) {
        return sortHand(cards, 3, 1);
    }

    public static List<String> sortTwoPairs(List<String> cards) {
        return sortHand(cards, 2, 1);
    }

    public static List<String> sortPair(List<String> cards) {
        return sortHand(cards, 2, 1);
    }

    public static List<String> sortHighCard(List<String> cards) {
        return sortHand(cards, 1);
    }

    private static List<String> sortHand(List<String> cards, int... targets) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cards.size(); i++) {
            String value = RankUtils.cardValue(cards, i);
            map.compute(value, (key, count) -> count == null ? 1 : count + 1);
        }
        List<String> sorted = new ArrayList<>();
        for (int i = 0; i < targets.length; i++) {
            final int target = targets[i];
            List<String> targetList = map.entrySet().stream().filter((entry -> entry.getValue() == target))
                    .map(e -> e.getKey())
                    .sorted((card1, card2) -> RankUtils.estimateCard(RankUtils.cardValue(card2))
                            - RankUtils.estimateCard(RankUtils.cardValue(card1)))
                    .collect(Collectors.toList());
            sorted.addAll(targetList);
        }
        return sorted;
    }
}
