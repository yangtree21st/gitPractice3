package io.zipcoder.casino.Models;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.utilities.AnsiStuff;

import java.util.Random;

/**
 * The Die program simply simulates a single die being rolled.
 *
 * @version 2019-02-22
 */
public class Die {
    private static AnsiStuff ansiStuff = new AnsiStuff();
    private Random random;


    /**
     * Standard constructor for die. Creates a die with a random seed.
     */
    public Die() {
        this.random = new Random();
    }

    /**
     * Constructor for testing purposes only! Allows the creation of a non-random die for predictable results.
     *
     * @param testRandom a supplied random, that will be non-random.
     */
    public Die(Random testRandom) {
        this.random = testRandom;
    }

    /**
     * This method gets the result of a toss with a six-sided die
     *
     * @return the random result of the six-sided die toss
     */
    public Integer getSingleDieRoll() {
        return getSingleDieRoll(6);
    }

    /**
     * This method gets the result of a toss of a die with a number of faces equal to numOfFaces
     *
     * @param numOfFaces the number of faces of the die to be rolled
     * @return the random result of the die toss
     */
    public Integer getSingleDieRoll(int numOfFaces) {
        return random.nextInt(numOfFaces) + 1;
    }

    /**
     * Rolls a single die, displays the result, and returns the value of the die roll
     *
     * @return the int value of the die roll
     */
    public int getSingleDieRollAndDisplayResult() {
        int dieRollResult = getSingleDieRoll();
        displaySingleRollResult(dieRollResult);
        return dieRollResult;
    }

    /**
     * This method displays a representation of a die roll, based on the integer input (1-6)
     *
     * @param value the integer value of the die roll to be displayed.
     */
    private void displaySingleRollResult(Integer value) {
        String representation = createDieRepresentation(value);
        String ansiColor = ansiStuff.getRandomAnsiColor();
        Casino.console.print(ansiColor + representation + AnsiStuff.ANSI_RESET);
    }

    /**
     * This method constructs a String representation of a die roll based on the integer input(1-6)
     * <p>
     * The string it returns looks something like this:
     * ---------
     * | o   o |
     * | o   o |
     * | o   o |
     * ---------
     *
     * @param value the integer value of the die roll to be displayed
     * @return a String representation of the die roll
     */
    private String createDieRepresentation(Integer value) {
        return boundaryLineOfDie() +
                firstRowOfDie(value) +
                secondRowOfDie(value) +
                thirdRowOfDie(value) +
                boundaryLineOfDie();
    }

    /**
     * creates and returns the top or bottom lines of the die representation
     *
     * @return the bottom or top line of the die representation
     */
    private String boundaryLineOfDie() {
        return "---------\n";
    }

    /**
     * @param value the integer value of the die roll
     * @return the String representation of the first row of the die
     */
    private String firstRowOfDie(Integer value) {
        String result = "";

        if (value == 6 || value == 5 || value == 4) {
            result = "| \u25CF   \u25CF |\n";
        } else if (value == 3 || value == 2) {
            result = "| \u25CF     |\n";
        } else if (value == 1) {
            result = "|       |\n";
        }

        return result;
    }

    /**
     * @param value the integer value of the die roll
     * @return the String representation of the second row of the die
     */
    private String secondRowOfDie(Integer value) {
        String result = "";

        if (value == 6) {
            result = "| \u25CF   \u25CF |\n";
        } else if (value == 5 || value == 3 || value == 1) {
            result = "|   \u25CF   |\n";
        } else if (value == 4 || value == 2) {
            result = "|       |\n";
        }

        return result;
    }

    /**
     * @param value the integer value of the die roll
     * @return the String representation of the third row of the die
     */
    private String thirdRowOfDie(Integer value) {
        String result = "";

        if (value == 6 || value == 5 || value == 4) {
            result = "| \u25CF   \u25CF |\n";
        } else if (value == 3 || value == 2) {
            result = "|     \u25CF |\n";
        } else if (value == 1) {
            result = "|       |\n";
        }

        return result;
    }

}
