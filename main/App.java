package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // while (scanner.hasNextLine()) {
        // compare(scanner.nextLine());
        // }
        // scanner.close();

        // test isPair and isTwoPairs
        List<String> list = new ArrayList<>(Arrays.asList(new String[] { "1D", "6D", "2D", "3D", "4D" }));
        System.out.println("isFlush:" + isFlush(list));
        list = new ArrayList<>(Arrays.asList(new String[] { "1C", "6D", "2D", "3D", "4D" }));
        System.out.println("isFlush:" + isFlush(list));

        // test isThreeOfAKind
        // List<String> list = new ArrayList<>(Arrays.asList(new String[] { "1D", "2D",
        // "3D", "3D", "3D" }));
        // System.out.println("isThreeOfAKind: " + isThreeOfAKind(list));
        // list = new ArrayList<>(Arrays.asList(new String[] { "1D", "2D", "3D", "4D",
        // "5D" }));
        // System.out.println("isThreeOfAKind: " + isThreeOfAKind(list));

        // test isPair and isTwoPairs
        // List<String> list = new ArrayList<>(Arrays.asList(new String[] { "1D", "6D",
        // "2D", "3D", "4D" }));
        // System.out.println("isTwoPairs:" + isTwoPairs(list));
        // System.out.println("isPair:" + isPair(list));
    }

    private static void compare(String line) {
        List<String> player1 = new ArrayList<>();
        List<String> player2 = new ArrayList<>();
        String[] cards = line.split(" ");
        for (int i = 0; i < cards.length; i++) {
            if (i < cards.length / 2) {
                player1.add(cards[i]);
            } else {
                player2.add(cards[i]);
            }
        }
        int rank1 = rank(player1);
        int rank2 = rank(player2);

        System.out.println(String.join(" ", player1));
        System.out.println(String.join(" ", player2));
        // TODO: get rank of p1 and p2
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
        return false;
    }

    private static boolean isStraightFlush(List<String> cards) {
        return false;
    }

    private static boolean isFourOfAKind(List<String> cards) {
        return false;
    }

    private static boolean isFullHouse(List<String> cards) {
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

    private static boolean isStraight(List<String> cards) {
        // not fully understand what straight means
        // Are all of below straight ?
        // 1 2 3 4 5
        // 5 4 3 2 1
        // 1 2 5 4 3
        return false;
    }

    private static boolean isThreeOfAKind(List<String> cards) {
        for (int i = 0; i < 3; i++) {
            String value1 = cards.get(i).substring(0, 1);
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2)) {
                    for (int k = j + 1; k < cards.size(); k++) {
                        String value3 = cards.get(k).substring(0, 1);
                        if (value2.equals(value3)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isTwoPairs(List<String> cards) {
        List<Integer> counted = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < cards.size(); i++) {
            String value1 = cards.get(i).substring(0, 1);
            for (int j = i + 1; j < cards.size(); j++) {
                String value2 = cards.get(j).substring(0, 1);
                if (value1.equals(value2) && !counted.contains(i) && !counted.contains(j)) {
                    counted.add(i);
                    counted.add(j);
                    count++;
                    if (count == 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isPair(List<String> cards) {
        for (int i = 0; i < cards.size(); i++) {
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
}
