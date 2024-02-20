package main;

import java.util.List;

public class TieUtils {
    public static int handleTie(int rank, List<String> cards1, List<String> cards2) {
        switch (rank) {
            case 8:
                return compareOneByOne(SortUtils.sortFourOfAKind(cards1), SortUtils.sortFourOfAKind(cards2));
            case 7:
                return compareOneByOne(SortUtils.sortFullHouse(cards1), SortUtils.sortFullHouse(cards2));
            case 4:
                return compareOneByOne(SortUtils.sortThreeOfAKind(cards1), SortUtils.sortThreeOfAKind(cards2));
            case 3:
                return compareOneByOne(SortUtils.sortTwoPairs(cards1), SortUtils.sortTwoPairs(cards2));
            case 2:
                return compareOneByOne(SortUtils.sortPair(cards1), SortUtils.sortPair(cards2));
            default:
                return compareOneByOne(SortUtils.sortHighCard(cards1), SortUtils.sortHighCard(cards2));
        }
    }

    private static int compareOneByOne(List<String> cards1, List<String> cards2) {
        for (int i = 0; i < cards1.size(); i++) {
            int value1 = RankUtils.estimateCard(RankUtils.cardValue(cards1, i));
            int value2 = RankUtils.estimateCard(RankUtils.cardValue(cards2, i));
            if (value1 > value2) {
                return 1;
            }
            if (value2 > value1) {
                return 2;
            }
        }
        return 0;
    }
}
