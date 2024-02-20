package app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // ===================== [TEST] ===================== //
        if (args.length == 1 && args[0].equals("test")) {
            try {
                TestUtils.runAllTests();
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
        System.out.println("Player 1: " + win1);
        System.out.println("Player 2: " + win2);
        scanner.close();
    }

    private static int comparePlayersCards(String line) {
        List<String> allCards = Arrays.asList(line.split(" "));
        List<String> cards1 = allCards.subList(0, allCards.size() / 2);
        List<String> cards2 = allCards.subList(allCards.size() / 2, allCards.size());
        int rank1 = RankUtils.rank(cards1);
        int rank2 = RankUtils.rank(cards2);
        if (rank1 > rank2) {
            return 1;
        }
        if (rank2 > rank1) {
            return 2;
        }
        return TieUtils.handleTie(rank1, cards1, cards2);
    }
}
