package io.zipcoder.casino;

import io.zipcoder.casino.Games.BlackJack;
import io.zipcoder.casino.Games.Craps;
import io.zipcoder.casino.Games.GoFish;
import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Models.GuestAccount;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CasinoGamesTest {

    private CasinoGames testCasinoGames;
    private Guest testGuest;
    private GuestAccount testGuestAccount;

    private ByteArrayOutputStream byteArrOutStream = new ByteArrayOutputStream();
    private ByteArrayInputStream byteArrInStream;


    @Before
    public void createCasinoGamesAndChangeSystemInOut() {
        byteArrOutStream.reset();
        if (byteArrInStream != null) {
            byteArrInStream.reset();
        }
        System.setOut(new PrintStream(byteArrOutStream));

        testGuestAccount = new GuestAccount("Bob", 12, 1000.0);
        testGuest = new Guest("Bob", testGuestAccount);
        testCasinoGames = new CasinoGames(testGuest);
    }

    @After
    public void resetSystemInOut() {
        System.setOut(System.out);
        System.setIn(System.in);
        byteArrOutStream.reset();
        if (byteArrInStream != null) {
            byteArrInStream.reset();
        }
    }

    @Test
    public void runSelectedGamesTest1() {
        // Given
        String continueChoice1 = "yes\n";
        String gameChoice1 = "blackjack\n";
        String continueChoice2 = "no\n";
        String input = continueChoice1 + gameChoice1 + continueChoice2;

        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        String expectedOutput = "Welcome to the Casino Game Floor!\n" +
                "Would you like to play a game? (yes or no):\n" +
                "Which game would you like to play?\n" +
                "Please enter: 'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n" +
                "You have played a full game of Black Jack!\n" +
                "Would you like to play a game? (yes or no):\n" +
                "Thank you for visiting the Casino Game Floor!\n";


        // When
        testCasinoGames.runSelectedGames();
        String actualOutput = byteArrOutStream.toString();

        // Then
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void runSelectedGamesTest2() {
        // Given
        String continueChoice1 = "yes\n";
        String gameChoice1 = "Craps\n";
        String continueChoice2 = "no\n";
        String input = continueChoice1 + gameChoice1 + continueChoice2;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        String expectedOutput = "Welcome to the Casino Game Floor!\n" +
                "Would you like to play a game? (yes or no):\n" +
                "Which game would you like to play?\n" +
                "Please enter: 'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n" +
                "You have played a full game of Craps!\n" +
                "Would you like to play a game? (yes or no):\n" +
                "Thank you for visiting the Casino Game Floor!\n";


        // When
        testCasinoGames.runSelectedGames();
        String actualOutput = byteArrOutStream.toString();

        // Then
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getUserChoiceToContinuePlayingTest1() {
        // Given
        String validChoice = "yes\n";
        String input = validChoice;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        boolean expectedResult = true;

        // When
        boolean actualResult = testCasinoGames.getUserChoiceToContinuePlaying();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUserChoiceToContinuePlayingTest2() {
        // Given
        String validChoice = "no\n";
        String input = validChoice;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        boolean expectedResult = false;

        // When
        boolean actualResult = testCasinoGames.getUserChoiceToContinuePlaying();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUserChoiceToContinuePlayingTest3() {
        // Given
        String invalidChoice1 = ";lkj\n";
        String invalidChoice2 = "asdf\n";
        String validChoice = "no\n";
        String input = invalidChoice1 + invalidChoice2 + validChoice;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        boolean expectedResult = false;
        String expectedOutput = "Would you like to play a game? (yes or no):\n" +
                "Error: Please enter yes or no:\n" +
                "Error: Please enter yes or no:\n";

        // When
        boolean actualResult = testCasinoGames.getUserChoiceToContinuePlaying();
        String actualOutput = byteArrOutStream.toString();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getUserChoiceForWhichGameToPlayTest1() {
        // Given
        String validChoice = "GoFish\n";
        String input = validChoice;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        String expectedResult = "gofish";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUserChoiceForWhichGameToPlayTest2() {
        // Given
        String validChoice = "BlackJack\n";
        String input = validChoice;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        String expectedResult = "blackjack";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUserChoiceForWhichGameToPlayTest3() {
        // Given
        String validChoice = "HiLo\n";
        String input = validChoice;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        String expectedResult = "hilo";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUserChoiceForWhichGameToPlayTest4() {
        // Given
        String validChoice = "Craps\n";
        String input = validChoice;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        String expectedResult = "craps";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUserChoiceForWhichGameToPlayTest5() {
        // Given
        String invalidChoice1 = ";lkj\n";
        String invalidChoice2 = "asdf\n";
        String validChoice = "gofish\n";
        String input = invalidChoice1 + invalidChoice2 + validChoice;
        byteArrInStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrInStream);

        String expectedResult = "gofish";
        String expectedOutput = "Which game would you like to play?\n" +
                "Please enter: 'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n" +
                "Error: Please enter one of the following games exactly as they are written\n" +
                "'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n" +
                "Error: Please enter one of the following games exactly as they are written\n" +
                "'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();
        String actualOutput = byteArrOutStream.toString();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void createAndSetCurrentGameTest1() {
        // Given
        String gameChoice = "gofish";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() instanceof GoFish);
    }

    @Test
    public void createAndSetCurrentGameTest2() {
        // Given
        String gameChoice = "blackjack";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() instanceof BlackJack);
    }

    @Test
    public void createAndSetCurrentGameTest3() {
        // Given
        String gameChoice = "hilo";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() instanceof HiLo);
    }

    @Test
    public void createAndSetCurrentGameTest4() {
        // Given
        String gameChoice = "craps";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() instanceof Craps);
    }

    @Test
    public void createAndSetCurrentGameTest5() {
        // Given
        String gameChoice = "asdf";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() == null);
    }

}