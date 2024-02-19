package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        // ===================== [TEST] ===================== //
        if (args.length == 1 && args[0].equals("test")) {
            try {
                test();
                System.out.println("All tests passed");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // ===================== [APP] ===================== //
        Scanner scanner = new Scanner(System.in);
        int win1 = 0;
        int win2 = 0;
        while (scanner.hasNextLine()) {
            switch (comparePlayersCards(scanner.nextLine())) {
                case 1:
                    win1++;
                    break;
                case 2:
                    win2++;
                    break;
                case 0:
                    System.out.println("there is a strong tie !");
                    break;
                default:
                    break;
            }
        }
        // TODO: replace sout with stdout
        System.out.println("Player 1: " + win1);
        System.out.println("Player 2: " + win2);
        scanner.close();
    }

    private static int comparePlayersCards(String line) {
        List<String> cards1 = new ArrayList<>();
        List<String> cards2 = new ArrayList<>();
        String[] cards = line.split(" ");
        for (int i = 0; i < cards.length; i++) {
            if (i < cards.length / 2) {
                cards1.add(cards[i]);
            } else {
                cards2.add(cards[i]);
            }
        }
        int rank1 = rank(cards1);
        int rank2 = rank(cards2);
        if (rank1 > rank2) {
            return 1;
        }
        if (rank2 > rank1) {
            return 2;
        }
        return handleTie(rank1, cards1, cards2);
    }

    private static int rank(List<String> cards) {
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
            switch (cards.get(i).substring(0, 1)) {
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
        String firstSuit = cards.get(0).substring(1, 2);
        for (int i = 1; i < cards.size(); i++) {
            String currentSuit = cards.get(i).substring(1, 2);
            if (!firstSuit.equals(currentSuit)) {
                return false;
            }
        }
        return true;
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
        for (int i = 0; i < (cards.size() - x + 1); i++) {
            int count = 1;
            String value1 = cards.get(i).substring(0, 1);
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2)) {
                    count++;
                    if (count == x) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isTwoPairs(List<String> cards) {
        return countPairs(cards) == 2;
    }

    private static boolean isPair(List<String> cards) {
        return countPairs(cards) == 1;
    }

    private static int countPairs(List<String> cards) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cards.size(); i++) {
            String value = cards.get(i).substring(0, 1);
            map.compute(value, (key, count) -> count == null ? 1 : count + 1);
        }
        return map.values().stream().filter(count -> count == 2).collect(Collectors.toList()).size();

    }

    private static int estimateCard(String card) {
        String value = card.substring(0, 1);
        switch (value) {
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
                return -999999;
        }
    }

    // ===================== TIE UTILITIES ===================== //

    private static int handleTie(int rank, List<String> cards1, List<String> cards2) {
        switch (rank) {
            case 8:
                return handleTieFourOfAKind(cards1, cards2);
            case 7:
                return handleTieFullHouse(cards1, cards2);
            case 4:
                return handleTieThreeOfAKind(cards1, cards2);
            case 3:
                return handleTieTwoPairs(cards1, cards2);
            case 2:
                return handleTiePair(cards1, cards2);
            default:
                return handleTieDefault(cards1, cards2);
        }
    }

    private static int handleTieFourOfAKind(List<String> cards1, List<String> cards2) {
        return compareOneByOne(sortFourOfAKind(cards1), sortFourOfAKind(cards2));
    }

    private static int handleTieFullHouse(List<String> cards1, List<String> cards2) {
        return compareOneByOne(sortFullHouse(cards1), sortFullHouse(cards2));
    }

    private static int handleTieThreeOfAKind(List<String> cards1, List<String> cards2) {
        return compareOneByOne(sortThreeOfAKind(cards1), sortThreeOfAKind(cards2));
    }

    private static int handleTieTwoPairs(List<String> cards1, List<String> cards2) {
        return compareOneByOne(sortTwoPairs(cards1), sortTwoPairs(cards2));
    }

    private static int handleTiePair(List<String> cards1, List<String> cards2) {
        return compareOneByOne(sortPair(cards1), sortPair(cards2));
    }

    private static int handleTieDefault(List<String> cards1, List<String> cards2) {
        return compareOneByOne(sortHighest(cards1), sortHighest(cards2));
    }

    private static int compareOneByOne(List<String> cards1, List<String> cards2) {
        for (int i = 0; i < cards1.size(); i++) {
            int value1 = estimateCard(cards1.get(i).substring(0, 1));
            int value2 = estimateCard(cards2.get(i).substring(0, 1));
            if (value1 > value2) {
                return 1;
            }
            if (value2 > value1) {
                return 2;
            }
        }
        return 0;
    }

    // ===================== SORT UTILITIES ===================== //

    private static List<String> sortFourOfAKind(List<String> cards) {
        List<String> result = new ArrayList<>();
        result.addAll(extractTargetsAndSort(cards, 4));
        result.addAll(extractTargetsAndSort(cards, 1));
        return result;
    }

    private static List<String> sortFullHouse(List<String> cards) {
        List<String> result = new ArrayList<>();
        result.addAll(extractTargetsAndSort(cards, 3));
        result.addAll(extractTargetsAndSort(cards, 2));
        return result;
    }

    private static List<String> sortThreeOfAKind(List<String> cards) {
        List<String> result = new ArrayList<>();
        result.addAll(extractTargetsAndSort(cards, 3));
        result.addAll(extractTargetsAndSort(cards, 1));
        return result;
    }

    private static List<String> sortTwoPairs(List<String> cards) {
        List<String> result = new ArrayList<>();
        result.addAll(extractTargetsAndSort(cards, 2));
        result.addAll(extractTargetsAndSort(cards, 1));
        return result;
    }

    private static List<String> sortPair(List<String> cards) {
        List<String> result = new ArrayList<>();
        result.addAll(extractTargetsAndSort(cards, 2));
        result.addAll(extractTargetsAndSort(cards, 1));
        return result;
    }

    private static List<String> sortHighest(List<String> cards) {
        return extractTargetsAndSort(cards, 1);
    }

    private static List<String> extractTargetsAndSort(List<String> cards, int target) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cards.size(); i++) {
            String value = cards.get(i).substring(0, 1);
            map.compute(value, (key, count) -> count == null ? 1 : count + 1);
        }
        return map.entrySet().stream().filter((entry -> entry.getValue() == target)).map(e -> e.getKey())
                .sorted((card1, card2) -> estimateCard(card2.substring(0, 1)) - estimateCard(card1.substring(0, 1)))
                .collect(Collectors.toList());
    }

    // ===================== TEST UTILITIES ===================== //

    private static void test() throws Exception {
        // isRoyalFlush
        assertTrue(isRoyalFlush(Arrays.asList(new String[] { "TD", "JD", "QD", "KD", "AD" })));
        assertFalse(isRoyalFlush(Arrays.asList(new String[] { "TD", "JS", "QD", "KD", "AD" })));
        assertFalse(isRoyalFlush(Arrays.asList(new String[] { "3D", "JD", "QD", "KD", "AD" })));

        // isStraightFlush
        assertTrue(isStraightFlush(Arrays.asList(new String[] { "8D", "9D", "TD", "JD", "QD" })));
        assertTrue(isStraightFlush(Arrays.asList(new String[] { "QD", "JD", "TD", "9D", "8D" })));
        assertFalse(isStraightFlush(Arrays.asList(new String[] { "8S", "9D", "TD", "JD", "QD" })));
        assertFalse(isStraightFlush(Arrays.asList(new String[] { "QS", "JD", "TD", "9D", "8D" })));
        assertFalse(isStraightFlush(Arrays.asList(new String[] { "TD", "JD", "QD", "8D", "9D" })));
        assertFalse(isStraightFlush(Arrays.asList(new String[] { "9D", "8D", "QD", "JD", "TD" })));

        // isFourOfAKind
        assertTrue(isFourOfAKind(Arrays.asList(new String[] { "2D", "3D", "3D", "3D", "3D" })));
        assertTrue(isFourOfAKind(Arrays.asList(new String[] { "JS", "3C", "JH", "JH", "JD" })));
        assertFalse(isFourOfAKind(Arrays.asList(new String[] { "2D", "2D", "3D", "3D", "3D" })));
        assertFalse(isFourOfAKind(Arrays.asList(new String[] { "2D", "2H", "3H", "2S", "3D" })));

        // isFullHouse
        assertTrue(isFullHouse(Arrays.asList(new String[] { "3D", "3D", "3D", "2D", "2D" })));
        assertTrue(isFullHouse(Arrays.asList(new String[] { "JH", "JS", "4D", "4C", "JD" })));
        assertFalse(isFullHouse(Arrays.asList(new String[] { "3C", "3D", "3D", "3D", "2D" })));
        assertFalse(isFullHouse(Arrays.asList(new String[] { "3C", "4D", "4D", "5D", "5D" })));

        // isFlush
        assertTrue(isFlush(Arrays.asList(new String[] { "5D", "6D", "2D", "3D", "4D" })));
        assertTrue(isFlush(Arrays.asList(new String[] { "3S", "4S", "5S", "6S", "JS" })));
        assertFalse(isFlush(Arrays.asList(new String[] { "5C", "6D", "2D", "3D", "4D" })));
        assertFalse(isFlush(Arrays.asList(new String[] { "3S", "4S", "5S", "6S", "JD" })));

        // isStraight
        assertTrue(isStraight(Arrays.asList(new String[] { "8D", "9D", "TD", "JD", "QD" })));
        assertTrue(isStraight(Arrays.asList(new String[] { "QD", "JD", "TD", "9D", "8D" })));
        assertFalse(isStraight(Arrays.asList(new String[] { "TD", "JD", "QD", "8D", "9D" })));
        assertFalse(isStraight(Arrays.asList(new String[] { "9D", "8D", "QD", "JD", "TD" })));

        // isThreeOfAKind
        assertTrue(isThreeOfAKind(Arrays.asList(new String[] { "4D", "5D", "3D", "3D", "3D" })));
        assertTrue(isThreeOfAKind(Arrays.asList(new String[] { "5D", "5S", "5C", "JD", "QS" })));
        assertFalse(isThreeOfAKind(Arrays.asList(new String[] { "4D", "5D", "6D", "3D", "3D" })));
        assertFalse(isThreeOfAKind(Arrays.asList(new String[] { "4D", "4D", "6D", "6D", "QD" })));

        // isTwoPairs
        assertTrue(isTwoPairs(Arrays.asList(new String[] { "3D", "3D", "4D", "4D", "5D" })));
        assertTrue(isTwoPairs(Arrays.asList(new String[] { "KS", "KC", "JS", "JH", "5D" })));
        assertFalse(isTwoPairs(Arrays.asList(new String[] { "3D", "3D", "3D", "3D", "5D" })));
        assertFalse(isTwoPairs(Arrays.asList(new String[] { "1D", "2D", "3D", "4D", "5D" })));

        // isPairs
        assertTrue(isPair(Arrays.asList(new String[] { "2D", "2D", "4D", "5D", "6D" })));
        assertTrue(isPair(Arrays.asList(new String[] { "JS", "4S", "JC", "5D", "6S" })));
        assertFalse(isPair(Arrays.asList(new String[] { "2D", "3D", "4D", "5D", "6D" })));
        assertFalse(isPair(Arrays.asList(new String[] { "TC", "QD", "4H", "JD", "6S" })));

        // countPair
        assertEqual(1, countPairs(Arrays.asList(new String[] { "2D", "2D", "4D", "5D", "6D" })));
        assertEqual(2, countPairs(Arrays.asList(new String[] { "2D", "2D", "4D", "4D", "6D" })));

        // handleTieFourOfAKind
        assertEqual(1, handleTieFourOfAKind(
                Arrays.asList(new String[] { "5D", "5D", "5D", "5D", "3D" }),
                Arrays.asList(new String[] { "4D", "4D", "4D", "4D", "AD" })));
        assertEqual(2, handleTieFourOfAKind(
                Arrays.asList(new String[] { "AD", "5D", "5D", "5D", "5D" }),
                Arrays.asList(new String[] { "3D", "KD", "KD", "KD", "KD" })));

        // handleTieFullHouse
        assertEqual(1, handleTieFullHouse(
                Arrays.asList(new String[] { "5D", "5D", "5D", "2D", "2D" }),
                Arrays.asList(new String[] { "4D", "4D", "4D", "3D", "3D" })));
        assertEqual(2, handleTieFullHouse(
                Arrays.asList(new String[] { "AD", "AD", "5D", "5D", "5D" }),
                Arrays.asList(new String[] { "3D", "3D", "6D", "6D", "6D" })));

        // handleTieThreeOfAKind
        assertEqual(1, handleTieThreeOfAKind(
                Arrays.asList(new String[] { "5D", "5D", "5D", "1D", "2D" }),
                Arrays.asList(new String[] { "4D", "4D", "4D", "2D", "3D" })));
        assertEqual(2, handleTieThreeOfAKind(
                Arrays.asList(new String[] { "AD", "KD", "5D", "5D", "5D" }),
                Arrays.asList(new String[] { "3D", "4D", "6D", "6D", "6D" })));

        // handleTieTwoPairs
        assertEqual(1, handleTieTwoPairs(
                Arrays.asList(new String[] { "3D", "3D", "KD", "KD", "AD" }),
                Arrays.asList(new String[] { "3D", "3D", "4D", "4D", "AD" })));
        assertEqual(2, handleTieTwoPairs(
                Arrays.asList(new String[] { "4D", "JD", "JD", "QD", "QD" }),
                Arrays.asList(new String[] { "3D", "TD", "TD", "AD", "AD" })));

        // handleTiePair
        assertEqual(1, handleTiePair(
                Arrays.asList(new String[] { "KD", "KD", "3D", "JD", "4D" }),
                Arrays.asList(new String[] { "3D", "2D", "4D", "4D", "AD" })));
        assertEqual(2, handleTiePair(
                Arrays.asList(new String[] { "KD", "KD", "3D", "JD", "4D" }),
                Arrays.asList(new String[] { "3D", "2D", "4D", "AD", "AD" })));

        // handleTieDefault
        assertEqual(1, handleTieDefault(
                Arrays.asList(new String[] { "KD", "AD", "3D", "4D", "5D" }),
                Arrays.asList(new String[] { "QD", "JD", "TD", "KD", "9D" })));
        assertEqual(2, handleTieDefault(
                Arrays.asList(new String[] { "7D", "5D", "3D", "4D", "5D" }),
                Arrays.asList(new String[] { "QD", "JD", "TD", "KD", "9D" })));

        // prepare reusable actual and expected list
        List<String> actual;
        String[] expected;

        // sortFourOfAKind
        actual = sortFourOfAKind(Arrays.asList(new String[] { "3C", "3S", "3H", "3D", "AS" }));
        expected = new String[] { "3", "A" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i).substring(0, 1));
        }

        // sortFullHouse
        actual = sortFullHouse(Arrays.asList(new String[] { "5C", "5S", "5H", "KD", "KS" }));
        expected = new String[] { "5", "K" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i).substring(0, 1));
        }

        // sortThreeOfAKind
        actual = sortThreeOfAKind(Arrays.asList(new String[] { "TC", "TS", "TH", "5D", "KS" }));
        expected = new String[] { "T", "K", "5" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i).substring(0, 1));
        }

        // sortTwoPairs
        actual = sortTwoPairs(Arrays.asList(new String[] { "3C", "JS", "JH", "3D", "AS" }));
        expected = new String[] { "J", "3", "A" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i).substring(0, 1));
        }

        // sortPair
        actual = sortPair(Arrays.asList(new String[] { "KD", "KD", "3C", "JD", "4S" }));
        expected = new String[] { "K", "J", "4", "3" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i).substring(0, 1));
        }

        // sortHigest
        actual = sortHighest(Arrays.asList(new String[] { "AD", "TD", "7C", "3D", "KS" }));
        expected = new String[] { "A", "K", "T", "7", "3" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i).substring(0, 1));
        }

        // extractTargetAndSort
        actual = extractTargetsAndSort(Arrays.asList(new String[] { "AS", "AS", "3S", "4S", "5A" }), 1);
        expected = new String[] { "5", "4", "3" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i));
        }
        actual = extractTargetsAndSort(Arrays.asList(new String[] { "AS", "AS", "3S", "4S", "4A" }), 2);
        expected = new String[] { "A", "4" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i));
        }
        actual = extractTargetsAndSort(Arrays.asList(new String[] { "AS", "AS", "JS", "JS", "JD" }), 3);
        expected = new String[] { "J" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i));
        }
        actual = extractTargetsAndSort(Arrays.asList(new String[] { "AS", "2S", "2S", "2S", "2D" }), 4);
        expected = new String[] { "2" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], actual.get(i));
        }
    }

    private static void assertTrue(boolean statement) throws Exception {
        if (!statement) {
            throw new Exception("FAILED");
        }
    }

    private static void assertFalse(boolean statement) throws Exception {
        if (statement) {
            throw new Exception("FAILED");
        }
    }

    private static void assertEqual(String expected, String actual) throws Exception {
        if (!expected.equals(actual)) {
            System.out.println(expected + " is not equal to " + actual);
            throw new Exception("FAILED");
        }
    }

    private static <T> void assertEqual(T expected, T actual) throws Exception {
        if (expected != actual) {
            System.out.println(expected + " is not equal to " + actual);
            throw new Exception("FAILED");
        }
    }
}
