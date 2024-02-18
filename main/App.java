package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        int tie = 0;
        while (scanner.hasNextLine()) {
            int winner = compare(scanner.nextLine());
            if (winner == 1) {
                win1++;
            }
            if (winner == 2) {
                win2++;
            }
            if (winner == 0) {
                tie++;
            }
        }
        System.out.println("win 1 - " + win1 + ", win2 - " + win2 + ", tie - " + tie);
        System.out.println("all rounds: " + (tie + win1 + win2));
        scanner.close();
    }

    private static int compare(String line) {
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

    private static int handleTie(int rank, List<String> cards1, List<String> cards2) {
        // NOTE: from "poker-handes.txt", tie is only found on rank 1, 2 and 3
        if (rank == 8) {
            return handleTieFourOfAKind();
        }
        if (rank == 7) {
            return handleTieFullHouse();
        }
        if (rank == 4) {
            return handleTieThreeOfAKind();
        }
        if (rank == 3) {
            return handleTieTwoPairs();
        }
        if (rank == 2) {
            // System.out.println("RANK 2: " + String.join(" ", cards1));
            return handleTiePair();
        }
        // System.out.println("RANK 1: " + String.join(" ", cards1));
        return 0;
    }

    private static int handleTieFourOfAKind() {
        return 0;
    }

    private static int handleTieFullHouse() {
        return 0;
    }

    private static int handleTieThreeOfAKind() {
        return 0;
    }

    private static int handleTieTwoPairs() {
        return 0;
    }

    private static int handleTiePair() {
        return 0;
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
        String firstSuite = cards.get(0).substring(1, 2);
        for (int i = 1; i < cards.size(); i++) {
            String currentSuite = cards.get(i).substring(1, 2);
            if (!firstSuite.equals(currentSuite)) {
                return false;
            }
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
        if (isFlush(cards) && isStraight(cards)) {
            return true;
        }
        return false;
    }

    private static boolean isFourOfAKind(List<String> cards) {
        for (int i = 0; i < 2; i++) {
            int count = 1;
            String value1 = cards.get(i).substring(0, 1);
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2)) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isFullHouse(List<String> cards) {
        String threeKindValue = "INITIAL_VALUE";
        // find three kind
        for (int i = 0; i < 3; i++) {
            int count = 1;
            String value1 = cards.get(i).substring(0, 1);
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2)) {
                    count++;
                    if (count == 3) {
                        threeKindValue = value1;
                    }
                }
            }
        }
        // early exit when three kind is not found
        if (threeKindValue.equals("INITIAL_VALUE")) {
            return false;
        }
        // find a pair
        for (int i = 0; i < cards.size() - 1; i++) {
            String value1 = cards.get(i).substring(0, 1);
            if (value1.equals(threeKindValue)) {
                continue;
            }
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2)) {
                    return true;
                }
            }
        }
        return false;
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

    // not fully understand what straight means
    // Are all of below straight ?
    // 1 2 3 4 5
    // 5 4 3 2 1
    // 1 2 5 4 3
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
        for (int i = 0; i < 3; i++) {
            int count = 1;
            String value1 = cards.get(i).substring(0, 1);
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2)) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isTwoPairs(List<String> cards) {
        String firstPair = "INITIAL_VALUE";
        int count = 0;
        for (int i = 0; i < cards.size(); i++) {
            String value1 = cards.get(i).substring(0, 1);
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2) && !value1.equals(firstPair)) {
                    count++;
                    if (count == 2) {
                        return true;
                    } else {
                        firstPair = value1;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isPair(List<String> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            String value1 = cards.get(i).substring(0, 1);
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2)) {
                    return true;
                }
            }
        }
        return false;
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
                return 10_000;
            case "Q":
                return 11_000;
            case "K":
                return 12_000;
            case "A":
                return 13_000;
            default:
                return -1;
        }
    }

    // ======================= TEST UTILITIES =======================
    // please run `java main/App.java test` to run tests

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
        assertFalse(isFourOfAKind(Arrays.asList(new String[] { "2D", "2D", "3D", "3D", "3D" })));

        // isFullHouse
        assertTrue(isFullHouse(Arrays.asList(new String[] { "3D", "3D", "3D", "2D", "2D" })));
        assertFalse(isFullHouse(Arrays.asList(new String[] { "3C", "3D", "3D", "3D", "2D" })));
        assertFalse(isFullHouse(Arrays.asList(new String[] { "3C", "4D", "4D", "5D", "5D" })));

        // isFlush
        assertTrue(isFlush(Arrays.asList(new String[] { "5D", "6D", "2D", "3D", "4D" })));
        assertFalse(isFlush(Arrays.asList(new String[] { "5C", "6D", "2D", "3D", "4D" })));

        // isStraight
        assertTrue(isStraight(Arrays.asList(new String[] { "8D", "9D", "TD", "JD", "QD" })));
        assertTrue(isStraight(Arrays.asList(new String[] { "QD", "JD", "TD", "9D", "8D" })));
        assertFalse(isStraight(Arrays.asList(new String[] { "TD", "JD", "QD", "8D", "9D" })));
        assertFalse(isStraight(Arrays.asList(new String[] { "9D", "8D", "QD", "JD", "TD" })));

        // isThreeOfAKind
        assertTrue(isThreeOfAKind(Arrays.asList(new String[] { "4D", "5D", "3D", "3D", "3D" })));
        assertFalse(isThreeOfAKind(Arrays.asList(new String[] { "4D", "5D", "6D", "3D", "3D" })));

        // isTwoPairs
        assertTrue(isTwoPairs(Arrays.asList(new String[] { "3D", "3D", "4D", "4D", "5D" })));
        assertFalse(isTwoPairs(Arrays.asList(new String[] { "3D", "3D", "3D", "3D", "5D" })));
        assertFalse(isTwoPairs(Arrays.asList(new String[] { "1D", "2D", "3D", "4D", "5D" })));

        // isPairs
        assertTrue(isPair(Arrays.asList(new String[] { "2D", "2D", "4D", "5D", "6D" })));
        assertFalse(isPair(Arrays.asList(new String[] { "2D", "3D", "4D", "5D", "6D" })));
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
}
