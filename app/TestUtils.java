package app;

import java.util.Arrays;
import java.util.List;

public class TestUtils {
    public static void runAllTests() throws Exception {
        // [RankUtils]
        // royal flush
        assertEqual(10, RankUtils.rank(Arrays.asList(new String[] { "TD", "JD", "QD", "KD", "AD" })));
        assertNotEqual(10, RankUtils.rank(Arrays.asList(new String[] { "TS", "JD", "QD", "KD", "AD" })));

        // straight flush
        assertEqual(9, RankUtils.rank(Arrays.asList(new String[] { "8D", "9D", "TD", "JD", "QD" })));
        assertEqual(9, RankUtils.rank(Arrays.asList(new String[] { "QD", "JD", "TD", "9D", "8D" })));
        assertNotEqual(9, RankUtils.rank(Arrays.asList(new String[] { "8S", "9D", "TD", "JD", "QD" })));
        assertNotEqual(9, RankUtils.rank(Arrays.asList(new String[] { "QS", "JD", "TD", "9D", "8D" })));
        assertNotEqual(9, RankUtils.rank(Arrays.asList(new String[] { "TD", "JD", "QD", "8D", "9D" })));
        assertNotEqual(9, RankUtils.rank(Arrays.asList(new String[] { "9D", "8D", "QD", "JD", "TD" })));

        // four of a kind
        assertEqual(8, RankUtils.rank(Arrays.asList(new String[] { "2D", "3D", "3D", "3D", "3D" })));
        assertEqual(8, RankUtils.rank(Arrays.asList(new String[] { "JS", "3C", "JH", "JH", "JD" })));
        assertNotEqual(8, RankUtils.rank(Arrays.asList(new String[] { "2D", "2D", "3D", "3D", "3D" })));
        assertNotEqual(8, RankUtils.rank(Arrays.asList(new String[] { "2D", "2H", "3H", "2S", "3D" })));

        // full house
        assertEqual(7, RankUtils.rank(Arrays.asList(new String[] { "3D", "3D", "3D", "2D", "2D" })));
        assertEqual(7, RankUtils.rank(Arrays.asList(new String[] { "JH", "JS", "4D", "4C", "JD" })));
        assertNotEqual(7, RankUtils.rank(Arrays.asList(new String[] { "3C", "3D", "3D", "3D", "2D" })));
        assertNotEqual(7, RankUtils.rank(Arrays.asList(new String[] { "3C", "4D", "4D", "5D", "5D" })));

        // flush
        assertEqual(6, RankUtils.rank(Arrays.asList(new String[] { "5D", "6D", "2D", "3D", "4D" })));
        assertEqual(6, RankUtils.rank(Arrays.asList(new String[] { "3S", "4S", "5S", "6S", "JS" })));
        assertNotEqual(6, RankUtils.rank(Arrays.asList(new String[] { "5C", "6D", "2D", "3D", "4D" })));
        assertNotEqual(6, RankUtils.rank(Arrays.asList(new String[] { "3S", "4S", "5S", "6S", "JD" })));

        // straight
        assertEqual(5, RankUtils.rank(Arrays.asList(new String[] { "8S", "9S", "TD", "JD", "QD" })));
        assertEqual(5, RankUtils.rank(Arrays.asList(new String[] { "QC", "JC", "TD", "9D", "8D" })));
        assertNotEqual(5, RankUtils.rank(Arrays.asList(new String[] { "TD", "JD", "QD", "8D", "9D" })));
        assertNotEqual(5, RankUtils.rank(Arrays.asList(new String[] { "9D", "8D", "QD", "JD", "TD" })));

        // three of a kind
        assertEqual(4, RankUtils.rank(Arrays.asList(new String[] { "4S", "5S", "3D", "3D", "3D" })));
        assertEqual(4, RankUtils.rank(Arrays.asList(new String[] { "5D", "5S", "5C", "JD", "QS" })));
        assertNotEqual(4, RankUtils.rank(Arrays.asList(new String[] { "4D", "5D", "6D", "3D", "3D" })));
        assertNotEqual(4, RankUtils.rank(Arrays.asList(new String[] { "4D", "4D", "6D", "6D", "QD" })));

        // two pairs
        assertEqual(3, RankUtils.rank(Arrays.asList(new String[] { "3S", "3D", "4D", "4D", "5D" })));
        assertEqual(3, RankUtils.rank(Arrays.asList(new String[] { "KS", "KC", "JS", "JH", "5D" })));
        assertNotEqual(3, RankUtils.rank(Arrays.asList(new String[] { "3D", "3D", "3D", "3D", "5D" })));
        assertNotEqual(3, RankUtils.rank(Arrays.asList(new String[] { "1D", "2D", "3D", "4D", "5D" })));

        // pair
        assertEqual(2, RankUtils.rank(Arrays.asList(new String[] { "2D", "2D", "4S", "5C", "6H" })));
        assertEqual(2, RankUtils.rank(Arrays.asList(new String[] { "JS", "4S", "JC", "5D", "6S" })));
        assertNotEqual(2, RankUtils.rank(Arrays.asList(new String[] { "2D", "3D", "4D", "5D", "6D" })));
        assertNotEqual(2, RankUtils.rank(Arrays.asList(new String[] { "TC", "QD", "4H", "JD", "6S" })));

        // high card
        assertEqual(1, RankUtils.rank(Arrays.asList(new String[] { "QD", "3H", "JS", "5C", "6H" })));
        assertEqual(1, RankUtils.rank(Arrays.asList(new String[] { "JS", "4S", "TC", "5D", "6S" })));
        assertNotEqual(1, RankUtils.rank(Arrays.asList(new String[] { "2D", "3D", "4D", "5D", "6D" })));
        assertNotEqual(1, RankUtils.rank(Arrays.asList(new String[] { "TH", "JH", "QH", "KH", "AH" })));

        // [TieUtils]
        // four of a kind
        assertEqual(1, TieUtils.handleTie(8,
                Arrays.asList(new String[] { "5D", "5D", "5D", "5D", "3D" }),
                Arrays.asList(new String[] { "4D", "4D", "4D", "4D", "AD" })));
        assertEqual(2, TieUtils.handleTie(8,
                Arrays.asList(new String[] { "AD", "5D", "5D", "5D", "5D" }),
                Arrays.asList(new String[] { "3D", "KD", "KD", "KD", "KD" })));

        // full house
        assertEqual(1, TieUtils.handleTie(7,
                Arrays.asList(new String[] { "5D", "5D", "5D", "2D", "2D" }),
                Arrays.asList(new String[] { "4D", "4D", "4D", "3D", "3D" })));
        assertEqual(2, TieUtils.handleTie(7,
                Arrays.asList(new String[] { "AD", "AD", "5D", "5D", "5D" }),
                Arrays.asList(new String[] { "3D", "3D", "6D", "6D", "6D" })));

        // three of a kind
        assertEqual(1, TieUtils.handleTie(4,
                Arrays.asList(new String[] { "5D", "5D", "5D", "1D", "2D" }),
                Arrays.asList(new String[] { "4D", "4D", "4D", "2D", "3D" })));
        assertEqual(2, TieUtils.handleTie(4,
                Arrays.asList(new String[] { "AD", "KD", "5D", "5D", "5D" }),
                Arrays.asList(new String[] { "3D", "4D", "6D", "6D", "6D" })));

        // two pairs
        assertEqual(1, TieUtils.handleTie(3,
                Arrays.asList(new String[] { "3D", "3D", "KD", "KD", "AD" }),
                Arrays.asList(new String[] { "3D", "3D", "4D", "4D", "AD" })));
        assertEqual(2, TieUtils.handleTie(3,
                Arrays.asList(new String[] { "4D", "JD", "JD", "QD", "QD" }),
                Arrays.asList(new String[] { "3D", "TD", "TD", "AD", "AD" })));

        // pair
        assertEqual(1, TieUtils.handleTie(2,
                Arrays.asList(new String[] { "KD", "KD", "3D", "JD", "4D" }),
                Arrays.asList(new String[] { "3D", "2D", "4D", "4D", "AD" })));
        assertEqual(2, TieUtils.handleTie(2,
                Arrays.asList(new String[] { "KD", "KD", "3D", "JD", "4D" }),
                Arrays.asList(new String[] { "3D", "2D", "4D", "AD", "AD" })));

        // high card
        assertEqual(1, TieUtils.handleTie(1,
                Arrays.asList(new String[] { "KD", "AD", "3D", "4D", "5D" }),
                Arrays.asList(new String[] { "QD", "JD", "TD", "KD", "9D" })));
        assertEqual(2, TieUtils.handleTie(1,
                Arrays.asList(new String[] { "7D", "5D", "3D", "4D", "5D" }),
                Arrays.asList(new String[] { "QD", "JD", "TD", "KD", "9D" })));

        List<String> actual;
        String[] expected;

        // [SortUtils]
        // sortFourOfAKind
        actual = SortUtils.sortFourOfAKind(Arrays.asList(new String[] { "3C", "3S", "3H", "3D", "AS" }));
        expected = new String[] { "3", "A" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], RankUtils.cardValue(actual, i));
        }

        // sortFullHouse
        actual = SortUtils.sortFullHouse(Arrays.asList(new String[] { "5C", "5S", "5H", "KD", "KS" }));
        expected = new String[] { "5", "K" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], RankUtils.cardValue(actual, i));
        }

        // sortThreeOfAKind
        actual = SortUtils.sortThreeOfAKind(Arrays.asList(new String[] { "TC", "TS", "TH", "5D", "KS" }));
        expected = new String[] { "T", "K", "5" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], RankUtils.cardValue(actual, i));
        }

        // sortTwoPairs
        actual = SortUtils.sortTwoPairs(Arrays.asList(new String[] { "3C", "JS", "JH", "3D", "AS" }));
        expected = new String[] { "J", "3", "A" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], RankUtils.cardValue(actual, i));
        }

        // sortPair
        actual = SortUtils.sortPair(Arrays.asList(new String[] { "KD", "KD", "3C", "JD", "4S" }));
        expected = new String[] { "K", "J", "4", "3" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], RankUtils.cardValue(actual, i));
        }

        // sortHighCard
        actual = SortUtils.sortHighCard(Arrays.asList(new String[] { "AD", "TD", "7C", "3D", "KS" }));
        expected = new String[] { "A", "K", "T", "7", "3" };
        for (int i = 0; i < actual.size(); i++) {
            assertEqual(expected[i], RankUtils.cardValue(actual, i));
        }

        System.out.println("All tests passed !");
    }

    private static void assertEqual(String expected, String actual) throws Exception {
        if (!expected.equals(actual)) {
            System.out.println(expected + " is not equal to " + actual);
            throw new Exception("FAILED");
        }
    }

    private static <T> void assertEqual(T expected, T actual) throws Exception {
        if (expected != actual) {
            System.out.println(expected + " is expected to be equal to " + actual);
            throw new Exception("FAILED");
        }
    }

    private static <T> void assertNotEqual(T expected, T actual) throws Exception {
        if (expected == actual) {
            System.out.println(expected + " is not expected to be equal to " + actual);
            throw new Exception("FAILED");
        }
    }
}
