package io.zipcoder.casino;

import io.zipcoder.casino.Games.BlackJack;
import io.zipcoder.casino.Games.Craps;
import io.zipcoder.casino.Games.GoFish;
import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Models.GuestAccount;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CasinoGamesTest{
    
    @Test
    public void runSelectedGames1() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String continueChoice1 = "yes\n";
        String gameChoice1 = "blackjack\n";
        String continueChoice2 = "no\n";
        String input = continueChoice1 + gameChoice1 + continueChoice2;
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr= new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);
        String expectedOutput = "Welcome to the Casino Game Floor!\n" +
                "Would you like to play a game? (yes or no):\n" +
                "Which game would you like to play?\n" +
                "Please enter: 'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n" +
                "You have played a full game of Black Jack!\n" +
                "Would you like to play a game? (yes or no):\n" +
                "Thank you for visiting the Casino Game Floor!\n";

        // When
        testCasinoGames.runSelectedGames();
        String actualOutput = bytArrOutStr.toString();

        // Then
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void runSelectedGames2() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String continueChoice1 = "yes\n";
        String gameChoice1 = "Craps\n";
        String continueChoice2 = "no\n";
        String input = continueChoice1 + gameChoice1 + continueChoice2;
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr= new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);
        String expectedOutput = "Welcome to the Casino Game Floor!\n" +
                "Would you like to play a game? (yes or no):\n" +
                "Which game would you like to play?\n" +
                "Please enter: 'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n" +
                "You have played a full game of Craps!\n" +
                "Would you like to play a game? (yes or no):\n" +
                "Thank you for visiting the Casino Game Floor!\n";

        // When
        testCasinoGames.runSelectedGames();
        String actualOutput = bytArrOutStr.toString();

        // Then
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getUserChoiceToContinuePlayingTest1() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String validChoice = "yes\n";
        String input = validChoice;
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr= new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);
        boolean expectedResult = true;

        // When
        boolean actualResult = testCasinoGames.getUserChoiceToContinuePlaying();

        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUserChoiceToContinuePlayingTest2() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String validChoice = "no\n";
        String input = validChoice;
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr= new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);
        boolean expectedResult = false;

        // When
        boolean actualResult = testCasinoGames.getUserChoiceToContinuePlaying();

        // Then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUserChoiceToContinuePlayingTest3() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String invalidChoice1 = ";lkj\n";
        String invalidChoice2 = "asdf\n";
        String validChoice = "no\n";
        String input = invalidChoice1 + invalidChoice2 + validChoice;
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr= new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);

        boolean expectedResult = false;
        String expectedOutput = "Would you like to play a game? (yes or no):\n" +
                "Error: Please enter yes or no:\n" +
                "Error: Please enter yes or no:\n";

        // When
        boolean actualResult = testCasinoGames.getUserChoiceToContinuePlaying();
        String actualOutput = bytArrOutStr.toString();


        // Then
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getUserChoiceForWhichGameToPlay1() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "GoFish\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(gameChoice.getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);

        String expectedOutput = "gofish";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();

        // Then
        Assert.assertEquals(expectedOutput, actualResult);
    }

    @Test
    public void getUserChoiceForWhichGameToPlay2() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "BlackJack\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(gameChoice.getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);

        String expectedOutput = "blackjack";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();

        // Then
        Assert.assertEquals(expectedOutput, actualResult);
    }

    @Test
    public void getUserChoiceForWhichGameToPlay3() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "HiLo\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(gameChoice.getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);

        String expectedOutput = "hilo";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();

        // Then
        Assert.assertEquals(expectedOutput, actualResult);
    }

    @Test
    public void getUserChoiceForWhichGameToPlay4() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "Craps\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(gameChoice.getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);

        String expectedOutput = "craps";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();

        // Then
        Assert.assertEquals(expectedOutput, actualResult);
    }

    @Test
    public void getUserChoiceForWhichGameToPlay5() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String invalidChoice1 = ";lkj\n";
        String invalidChoice2 = "asdf\n";
        String validChoice = "gofish\n";
        String input = invalidChoice1 + invalidChoice2 + validChoice;
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();

        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr, bytArrOutStr);

        String expectedResult = "gofish";
        String expectedOutput = "Which game would you like to play?\n" +
                "Please enter: 'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n" +
                "Error: Please enter one of the following games exactly as they are written\n" +
                "'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n" +
                "Error: Please enter one of the following games exactly as they are written\n" +
                "'GoFish', 'BlackJack', 'HiLo', or 'Craps'\n";

        // When
        String actualResult = testCasinoGames.getUserChoiceForWhichGameToPlay();
        String actualOutput = bytArrOutStr.toString();

        // Then
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void createAndSetCurrentGameTest1() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "gofish";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() instanceof GoFish);
    }

    @Test
    public void createAndSetCurrentGameTest2() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "blackjack";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() instanceof BlackJack);
    }

    @Test
    public void createAndSetCurrentGameTest3() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "hilo";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() instanceof HiLo);
    }

    @Test
    public void createAndSetCurrentGameTest4() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "craps";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertTrue(testCasinoGames.getCurrentGame() instanceof Craps);
    }

    @Test
    public void createAndSetCurrentGameTest5() {
        // Given
        CasinoGames testCasinoGames = getNewCasinoGames();
        String gameChoice = "asdf";

        // When
        testCasinoGames.createAndSetCurrentGame(gameChoice);

        // Then
        Assert.assertNull(testCasinoGames.getCurrentGame());
    }

    private Casino getCasinoWithBufferedInputAndOutput(ByteArrayInputStream bytArrInpStr, ByteArrayOutputStream bytArrOutStr) {
        return new Casino(bytArrInpStr, new PrintStream(bytArrOutStr));
    }
    
    private CasinoGames getNewCasinoGames(){
        return new CasinoGames(new Guest("", new GuestAccount("", 1, 100.0)));
    }

}