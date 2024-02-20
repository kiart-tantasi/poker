package app;

import java.util.List;
import java.util.stream.Collectors;

public class RankUtils {
    public static int rank(List<String> cards) {
        if (isRoyalFlush(cards)) {
            return 10;
        }
        if (isStraightFlush(cards)) {
            return 9;
        }
        if (isFourOfAKind(cards)) {
            return 8;
        }
        if (isFullHouse(cards)) {
            return 7;
        }
        if (isFlush(cards)) {
            return 6;
        }
        if (isStraight(cards)) {
            return 5;
        }
        if (isThreeOfAKind(cards)) {
            return 4;
        }
        if (isTwoPairs(cards)) {
            return 3;
        }
        if (isPair(cards)) {
            return 2;
        }
        return 1;
    }

    public static String cardValue(List<String> cards, int index) {
        return cards.get(index).substring(0, 1);
    }

    public static String cardValue(String card) {
        return card.substring(0, 1);
    }

    public static String cardSuit(List<String> cards, int index) {
        return cards.get(index).substring(1, 2);
    }

    public static String cardSuit(String card) {
        return card.substring(1, 2);
    }

    public static int estimateCard(String card) {
        switch (cardValue(card)) {
            case "2":
                return 1000;
            case "3":
                return 2000;
            case "4":
                return 3000;
            case "5":
                return 4000;
            case "6":
                return 5000;
            case "7":
                return 6000;
            case "8":
                return 7000;
            case "9":
                return 8000;
            case "T":
                return 9000;
            case "J":
                return 10000;
            case "Q":
                return 11000;
            case "K":
                return 12000;
            case "A":
                return 13000;
            default:
                return -1;
        }
    }

    private static boolean isRoyalFlush(List<String> cards) {
        if (!isFlush(cards)) {
            return false;
        }
        boolean isTen = false;
        boolean isJack = false;
        boolean isQueen = false;
        boolean isKing = false;
        boolean isAce = false;
        for (int i = 0; i < cards.size(); i++) {
            switch (cardValue(cards, i)) {
                case "T":
                    isTen = true;
                    break;
                case "J":
                    isJack = true;
                    break;
                case "Q":
                    isQueen = true;
                    break;
                case "K":
                    isKing = true;
                    break;
                case "A":
                    isAce = true;
                    break;
                default:
                    break;
            }
        }
        return isTen && isJack && isQueen && isKing && isAce;
    }

    private static boolean isStraightFlush(List<String> cards) {
        return isStraight(cards) && isFlush(cards);
    }

    private static boolean isFourOfAKind(List<String> cards) {
        return isXOfAKind(cards, 4);
    }

    private static boolean isFullHouse(List<String> cards) {
        return isThreeOfAKind(cards) && isPair(cards);
    }

    private static boolean isFlush(List<String> cards) {
        String firstSuit = cardSuit(cards, 0);
        return cards.stream().allMatch(card -> cardSuit(card).equals(firstSuit));
    }

    private static boolean isStraight(List<String> cards) {
        int firstDiff = estimateCard(cards.get(0)) - estimateCard(cards.get(1));
        for (int i = 1; i < cards.size() - 1; i++) {
            int currentDiff = estimateCard(cards.get(i)) - estimateCard(cards.get(i + 1));
            if (currentDiff != firstDiff) {
                return false;
            }
        }
        return true;
    }

    private static boolean isThreeOfAKind(List<String> cards) {
        return isXOfAKind(cards, 3);
    }

    private static boolean isXOfAKind(List<String> cards, int x) {
        return cards.stream().collect(Collectors.groupingBy(card -> cardValue(card), Collectors.counting()))
                .values().stream().anyMatch(count -> count == x);
    }

    private static boolean isTwoPairs(List<String> cards) {
        return countPairs(cards) == 2;
    }

    private static boolean isPair(List<String> cards) {
        return countPairs(cards) == 1;
    }

    private static long countPairs(List<String> cards) {
        return cards.stream().collect(Collectors.groupingBy(card -> cardValue(card), Collectors.counting()))
                .values().stream().filter(count -> count == 2).count();
    }
}
