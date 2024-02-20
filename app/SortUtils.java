package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class SortUtils {
    public static List<String> sortFourOfAKind(List<String> cards) {
        return extractTargetsAndSort(cards, 4, 1);
    }

    public static List<String> sortFullHouse(List<String> cards) {
        return extractTargetsAndSort(cards, 3, 2);
    }

    public static List<String> sortThreeOfAKind(List<String> cards) {
        return extractTargetsAndSort(cards, 3, 1);
    }

    public static List<String> sortTwoPairs(List<String> cards) {
        return extractTargetsAndSort(cards, 2, 1);
    }

    public static List<String> sortPair(List<String> cards) {
        return extractTargetsAndSort(cards, 2, 1);
    }

    public static List<String> sortHighCard(List<String> cards) {
        return extractTargetsAndSort(cards, 1);
    }

    private static List<String> extractTargetsAndSort(List<String> cards, int... targets) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cards.size(); i++) {
            String value = RankUtils.cardValue(cards, i);
            map.compute(value, (key, count) -> count == null ? 1 : count + 1);
        }
        List<String> sorted = new ArrayList<>();
        for (int i = 0; i < targets.length; i++) {
            final int target = targets[i];
            List<String> targetList = map.entrySet().stream().filter((entry -> entry.getValue() == target))
                    .map(e -> e.getKey()).sorted((card1, card2) -> RankUtils.estimateCard(RankUtils.cardValue(card2))
                            - RankUtils.estimateCard(RankUtils.cardValue(card1)))
                    .collect(Collectors.toList());
            sorted.addAll(targetList);
        }
        return sorted;
    }
}
