package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Models.SlotReel;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

public class SlotMachineTest {
    private Guest testGuest = new Guest("", new GuestAccount("", 1, 1000.0));
    private SlotReel noLinesReel = new SlotReel(new Random(1));
    private SlotReel zigZagReel = new SlotReel(new Random(4));
    private SlotReel jackpotReel = new SlotReel(new Random(63));
    private SlotReel diagonalReel = new SlotReel(new Random(554));


    @Test
    public void getSeeds() {
        SlotReel slotReel;
        for (int i = 0; i < 2000; i ++) {
            slotReel = new SlotReel(new Random(i));
            System.out.println("Current seed is: " + i);
            System.out.println(Arrays.toString(slotReel.getSlotReelResults(3)));
        }
    }


    @Test
    public void constructorTest1() {
        // Given
        SlotMachine testSlotMachine = new SlotMachine(testGuest);

        //Then
        Assert.assertNotNull(testSlotMachine.getSlotReel());
    }

    @Test
    public void constructorTest2() {
        // Given
        SlotMachine testSlotMachine = new SlotMachine(testGuest);

        //Then
        Assert.assertNotNull(testSlotMachine.getCurrentGuest());
    }

    @Test
    public void constructorTest3() {
        // Given
        SlotMachine testSlotMachine = new SlotMachine(testGuest);

        //Then
        Assert.assertEquals(testSlotMachine.getMinimumBet(), 5);
    }

    @Test
    public void playFullGameTest1() {


    }

    @Test
    public void useTheSlotsTest1() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest, noLinesReel);

        // When
        

    }

    @Test
    public void checkReelResultsTest1() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {0, 0, 0};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isJackpot());
    }

    @Test
    public void checkReelResultsTest2() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {3, 3, 3};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isJackpot());
    }

    @Test
    public void checkReelResultsTest3() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {4, 3, 3};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertFalse(testSlotMachine.isJackpot());
    }

    @Test
    public void checkReelResultsTest4() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {4, 5, 3};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertFalse(testSlotMachine.isJackpot());
    }

    @Test
    public void checkReelResultsTest5a() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {1, 2, 3};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isDiagonal());
    }

    @Test
    public void checkReelResultsTest5b() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {9, 0, 1};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isDiagonal());
    }

    @Test
    public void checkReelResultsTest6() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {7, 6, 5};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isDiagonal());
    }

    @Test
    public void checkReelResultsTest6b() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {0, 9, 8};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isDiagonal());
    }

    @Test
    public void checkReelResultsTest7() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {4, 3, 3};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertFalse(testSlotMachine.isDiagonal());
    }

    @Test
    public void checkReelResultsTest8() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {4, 5, 3};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertFalse(testSlotMachine.isDiagonal());
    }

    @Test
    public void checkReelResultsTest9() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {1, 2, 1};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest10() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {7, 6, 7};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest11() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {4, 5, 5};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest12() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {6, 5, 5};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest13a() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {0, 0, 9};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest13b() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {9, 9, 0};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest13c() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {0, 9, 0};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest13d() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {9, 0, 9};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest13e() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {8,9,8};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest14() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {3, 3, 4};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertTrue(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest15() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {7, 1, 8};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertFalse(testSlotMachine.isZigZag());
    }

    @Test
    public void checkReelResultsTest16() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        int[] slotReelResults = {4, 9, 3};

        // When
        testSlotMachine.checkReelResults(slotReelResults);

        // Then
        Assert.assertFalse(testSlotMachine.isZigZag());
    }

    @Test
    public void setupTest1() {
        // Given
        SlotMachine testSlotMachine = new SlotMachine(testGuest);

        // When
        testSlotMachine.setup();

        // Then
        Assert.assertFalse(testSlotMachine.isJackpot());
    }

    @Test
    public void setupTest2() {
        // Given
        SlotMachine testSlotMachine = new SlotMachine(testGuest);

        // When
        testSlotMachine.setup();

        // Then
        Assert.assertFalse(testSlotMachine.isDiagonal());
    }

    @Test
    public void setupTest3() {
        // Given
        SlotMachine testSlotMachine = new SlotMachine(testGuest);

        // When
        testSlotMachine.setup();

        // Then
        Assert.assertFalse(testSlotMachine.isZigZag());
    }

    @Test
    public void payOutTest1() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        testSlotMachine.setJackpot(true);

        Double expectedFinalBalance = 6100.0;

        // When
        testSlotMachine.payOut(100.0);
        Double actualFinalBalance = testSlotMachine.getCurrentGuest().getAccountBalance();

        // Then
        Assert.assertEquals(expectedFinalBalance, actualFinalBalance);
    }

    @Test
    public void payOutTest2() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        testSlotMachine.setDiagonal(true);

        Double expectedFinalBalance = 3600.0;

        // When
        testSlotMachine.payOut(100.0);
        Double actualFinalBalance = testSlotMachine.getCurrentGuest().getAccountBalance();

        // Then
        Assert.assertEquals(expectedFinalBalance, actualFinalBalance);
    }

    @Test
    public void payOutTest3() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);
        testSlotMachine.setZigZag(true);

        Double expectedFinalBalance = 2100.0;

        // When
        testSlotMachine.payOut(100.0);
        Double actualFinalBalance = testSlotMachine.getCurrentGuest().getAccountBalance();

        // Then
        Assert.assertEquals(expectedFinalBalance, actualFinalBalance);
    }

    @Test
    public void payOutTest4() {
        // Given
        Casino testCasino = new Casino();
        SlotMachine testSlotMachine = new SlotMachine(testGuest);

        Double expectedFinalBalance = 1000.0;

        // When
        testSlotMachine.payOut(100.0);
        Double actualFinalBalance = testSlotMachine.getCurrentGuest().getAccountBalance();

        // Then
        Assert.assertEquals(expectedFinalBalance, actualFinalBalance);
    }

    @Test
    public void printInstructionsTest1() {
        // Given
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = new Casino(System.in, new PrintStream(bytArrOutStr));
        SlotMachine testSlotMachine = new SlotMachine(testGuest);

        String expectedOutput = "When you use the slot machine, three reels will spin. Each reel will land on a number between 0 and 9.\n" +
                "The numbers above and below these numbers will also be displayed. There are three ways to win: hitting the\n" +
                "jackpot, getting a diagonal, or getting a zig-zag. You hit the jackpot by getting all three middle numbers\n" +
                "in the reels to be the same. You get a diagonal by getting three numbers to line up in a diagonal, and you\n" +
                "get a zig-zag by getting three numbers to line up in any other way (but any two numbers in the line must be\n" +
                "at least diagonally adjacent).\n\n";

        // When
        testSlotMachine.printInstructions();
        String actualOutput = bytArrOutStr.toString();

        // Then
        Assert.assertEquals(expectedOutput, actualOutput);
    }
}
