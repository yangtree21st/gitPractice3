package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Games.Craps.TypeOfBet;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.Die;
import io.zipcoder.casino.Models.GuestAccount;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class CrapsTest {
    private Casino testCasino;
    private Craps testCraps;
    private ByteArrayInputStream bytArrInpStr;
    private ByteArrayOutputStream bytArrOutStr;

    private final int NATURAL_7_SEED = 9;
    private final int NATURAL_11_SEED = 5;
    private final int CRAPS_2_SEED = 17;
    private final int CRAPS_3_SEED = 6;
    private final int CRAPS_12_SEED = 63;
    private final int PUSH_12_THEN_HIT_POINT_SEED = 63;
    private final int PUSH_12_THEN_NATURAL_7_SEED = 64;
    private final int PUSH_12_THEN_SEVEN_OUT_SEED = 95;
    private final int HIT_POINT_SEED = 2; //6
    private final int SEVEN_OUT_SEED = 1; //10


    @Test
    public void normalConstructorTest() {
        // Given
        Guest expectedGuest = new Guest("", new GuestAccount("", 0, 0.0));
        Craps craps = new Craps(expectedGuest);

        // When
        Guest actualGuest = craps.getCurrentGuest();

        // Then
        Assert.assertEquals(expectedGuest, actualGuest);
    }

    @Test
    public void playFullGameTest1() {
        // Hit point seed, Pass Bet, Bet is $100, Starting balance is $2000, new balance should be $2100
        // Given
        String input = "yes\nyes\npass\n100\n\n\n\n\n\n\nno\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        Double startingBalance = 2000.0;
        testCraps = getNewCraps(startingBalance, new Random(HIT_POINT_SEED));

        Double expectedNewBalance = 2100.0;

        // When
        testCraps.playFullGame();
        Double actualNewBalance = testCraps.getCurrentGuest().getAccountBalance();

        // Then
        Assert.assertEquals(expectedNewBalance, actualNewBalance, .0000000001);
    }

    @Test
    public void playFullGameTest2() {
        // Hit point seed, Pass Bet, Bet is $100, Starting balance is $2000, new balance should be $2100
        // Given
        String input = "yes\nyes\ndon't pass\n100\n\n\n\n\n\n\nno\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        Double startingBalance = 2000.0;
        testCraps = getNewCraps(startingBalance, new Random(HIT_POINT_SEED));

        Double expectedNewBalance = 1900.0;

        // When
        testCraps.playFullGame();
        Double actualNewBalance = testCraps.getCurrentGuest().getAccountBalance();

        // Then
        Assert.assertEquals(expectedNewBalance, actualNewBalance, .0000000001);
    }

    @Test
    public void shootCrapsTest1() {
        // Natural 7, Pass Bet, Should return true
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(NATURAL_7_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.PASS);

        // Then
        Assert.assertTrue(hasWon);
    }

    @Test
    public void shootCrapsTest2() {
        // Natural 7, Don't Pass Bet, Should return false
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(NATURAL_7_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest3() {
        // Natural 11, Pass Bet, Should return true
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(NATURAL_11_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.PASS);

        // Then
        Assert.assertTrue(hasWon);
    }

    @Test
    public void shootCrapsTest4() {
        // Natural 11, Don't Pass Bet, Should return false
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(NATURAL_11_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest5() {
        // Craps 2, Pass Bet, Should return false
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(CRAPS_2_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest6() {
        // Craps 2, Don't Pass Bet, Should return true
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(CRAPS_2_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertTrue(hasWon);
    }

    @Test
    public void shootCrapsTest7() {
        // Craps 3, Pass Bet, Should return false
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(CRAPS_3_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest8() {
        // Craps 3, Don't Pass Bet, Should return true
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(CRAPS_3_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertTrue(hasWon);
    }

    @Test
    public void shootCrapsTest9() {
        // Craps 12, Pass Bet, Should return false
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(CRAPS_12_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest10() {
        // Push 12, then hit Point, Don't Pass Bet, Should return false
        // Given
        String input = "\n\n\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(PUSH_12_THEN_HIT_POINT_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest11() {
        // Push 12, then Natural 7, Don't Pass Bet, Should return false
        // Given
        String input = "\n\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(PUSH_12_THEN_NATURAL_7_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest12() {
        // Push 12, then Seven-Out, Don't Pass Bet, Should return true
        // Given
        String input = "\n\n\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(PUSH_12_THEN_SEVEN_OUT_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertTrue(hasWon);
    }

    @Test
    public void shootCrapsTest13() {
        // Hit point, Pass Bet, Should return true
        // Given
        String input = "\n\n\n\n\n\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(HIT_POINT_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.PASS);

        // Then
        Assert.assertTrue(hasWon);
    }

    @Test
    public void shootCrapsTest14() {
        // Hit point, Don't Pass Bet, Should return false
        // Given
        String input = "\n\n\n\n\n\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(HIT_POINT_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest15() {
        // Seven-Out, Pass Bet, Should return false
        // Given
        String input = "\n\n\n\n\n\n\n\n\n\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(SEVEN_OUT_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void shootCrapsTest16() {
        // Seven-Out, Don't Pass Bet, Should return true
        // Given
        String input = "\n\n\n\n\n\n\n\n\n\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(SEVEN_OUT_SEED));

        // When
        boolean hasWon = testCraps.shootCraps(TypeOfBet.DONT_PASS);

        // Then
        Assert.assertTrue(hasWon);
    }



    @Test
    public void rollDiceTest1() {
        // Natural 7
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(NATURAL_7_SEED));

        int expectedResult = 7;

        // When
        int actualResult = testCraps.rollDice();

        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void comeOutRollTest1() {
        // Natural 7, Pass Bet, Should return true
        // Given
        String input = "\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(NATURAL_7_SEED));

        // When
        boolean hasWon = testCraps.comeOutRoll(TypeOfBet.PASS);

        // Then
        Assert.assertTrue(hasWon);
    }

    @Test
    public void pointRollTest1() {
        // Hit point seed, The second roll result in a roll again, so hasWon should still be false
        // Given
        String input = "\n\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(new Random(HIT_POINT_SEED));
        testCraps.comeOutRoll(TypeOfBet.PASS);

        // When
        boolean hasWon = testCraps.comeOutRoll(TypeOfBet.PASS);

        // Then
        Assert.assertFalse(hasWon);
    }

    @Test
    public void payOutTest1() {
        boolean hasWon = true;
        Double currentBet = 100.0;
        testCasino = new Casino();
        testCraps = getNewCraps(1000.0);
        Double expectedNewBalance = 1200.00;

        // When
        testCraps.payOut(hasWon, currentBet);
        Double actualNewBalance = testCraps.getCurrentGuest().getAccountBalance();

                // Then
        Assert.assertEquals(expectedNewBalance, actualNewBalance);
    }

    @Test
    public void payOutTest2() {
        boolean hasWon = false;
        Double currentBet = 100.0;
        testCasino = new Casino();
        testCraps = getNewCraps(1000.0);
        Double expectedNewBalance = 1000.00;

        // When
        testCraps.payOut(hasWon, currentBet);
        Double actualNewBalance = testCraps.getCurrentGuest().getAccountBalance();

        // Then
        Assert.assertEquals(expectedNewBalance, actualNewBalance);
    }

    @Test
    public void getTypeOfBetFromPlayer1() {
        // Given
        String input = "pass\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps();

        TypeOfBet expectedTypeOfBet = TypeOfBet.PASS;

        // When
        TypeOfBet actualTypeOfBet = testCraps.getTypeOfBetFromPlayer();

        // Then
        Assert.assertEquals(expectedTypeOfBet, actualTypeOfBet);
    }

    @Test
    public void getTypeOfBetFromPlayer2() {
        // Given
        String input = "don't pass\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps();

        TypeOfBet expectedTypeOfBet = TypeOfBet.DONT_PASS;

        // When
        TypeOfBet actualTypeOfBet = testCraps.getTypeOfBetFromPlayer();

        // Then
        Assert.assertEquals(expectedTypeOfBet, actualTypeOfBet);
    }

    @Test
    public void getTypeOfBetFromPlayer3() {
        // Given
        String input = "IASDHF\nuhasdiufh\ndon't pass\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps();

        TypeOfBet expectedTypeOfBet = TypeOfBet.DONT_PASS;

        // When
        TypeOfBet actualTypeOfBet = testCraps.getTypeOfBetFromPlayer();

        // Then
        Assert.assertEquals(expectedTypeOfBet, actualTypeOfBet);
    }

    @Test
    public void takeBetFromPlayer1() {
        // Given
        String input = "100\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps();

        Double expectedBet = 100.0;

        // When
        Double actualBet = testCraps.takeBetFromPlayer();

        // Then
        Assert.assertEquals(expectedBet, actualBet);
    }

    @Test
    public void takeBetFromPlayer2() {
        // Given
        String input = "oaudhf\n150\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps();

        Double expectedBet = 150.0;

        // When
        Double actualBet = testCraps.takeBetFromPlayer();

        // Then
        Assert.assertEquals(expectedBet, actualBet);
    }

    @Test
    public void takeBetFromPlayer3() {
        // Given
        String input = "1500\n500\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps(1100.0);

        Double expectedBet = 500.0;

        // When
        Double actualBet = testCraps.takeBetFromPlayer();

        // Then
        Assert.assertEquals(expectedBet, actualBet);
    }

    @Test
    public void yesOrNoQuestionTest1() {
        // Given
        String input = "yes\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps();

        // When
        boolean actualResult = testCraps.yesOrNoQuestion("");

        // Then
        Assert.assertTrue(actualResult);
    }

    @Test
    public void yesOrNoQuestionTest2() {
        // Given
        String input = "no\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps();

        // When
        boolean actualResult = testCraps.yesOrNoQuestion("");

        // Then
        Assert.assertFalse(actualResult);
    }

    @Test
    public void yesOrNoQuestionTest3() {
        // Given
        String input = "asdfh\niuahsodiug\n234\nno\n";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = getCasinoWithBufferedInputAndOutput(input, bytArrOutStr);
        testCraps = getNewCraps();

        // When
        boolean actualResult = testCraps.yesOrNoQuestion("");

        // Then
        Assert.assertFalse(actualResult);
    }

    private Casino getCasinoWithBufferedInputAndOutput(String input, ByteArrayOutputStream bytArrOutStr) {
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        return new Casino(bytArrInpStr, new PrintStream(bytArrOutStr));
    }

    private Craps getNewCraps(Double balance, Random testRandom) {
        return new Craps(new Guest("", new GuestAccount("", 1, balance)), new Die(testRandom));
    }

    private Craps getNewCraps(Random testRandom) {
        return getNewCraps(1000.0, testRandom);
    }

    private Craps getNewCraps(Double balance) {
        return getNewCraps(balance, new Random());
    }

    private Craps getNewCraps() {
        return getNewCraps(1000.0, new Random());
    }

}