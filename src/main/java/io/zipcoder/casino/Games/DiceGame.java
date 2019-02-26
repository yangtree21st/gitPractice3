package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Models.Die;
import io.zipcoder.casino.utilities.AnsiStuff;

public abstract class DiceGame {
    Die die;

    /**
     * Rolls a single die, displays the result, and returns the value of the die roll
     * @return the int value of the die roll
     */
    static int rollSingleDieAndDisplayResult(Die die) {
        int dieRollResult = die.getSingleDieRoll();
        displaySingleRollResult(dieRollResult);
        return dieRollResult;
    }

    /**
     * This method displays a representation of a die roll, based on the integer input (1-6)
     *
     * @param value the integer value of the die roll to be displayed.
     */
    static private void displaySingleRollResult(Integer value) {
        String representation = createDieRepresentation(value);
        Casino.console.print(representation);
    }

    /**
     * This method constructs a String representation of a die roll based on the integer input(1-6)
     *
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
    static private String createDieRepresentation(Integer value) {
        return boundaryLineOfDie() +
                firstRowOfDie(value) +
                secondRowOfDie(value) +
                thirdRowOfDie(value) +
                boundaryLineOfDie();
    }

    /**
     * creates and returns the top or bottom lines of the die representation
     * @return the bottom or top line of the die representation
     */
    static private String boundaryLineOfDie() {
        return "---------\n";
    }

    /**
     * @param value the integer value of the die roll
     * @return the String representation of the first row of the die
     */
    static private String firstRowOfDie(Integer value) {
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
    static private String secondRowOfDie(Integer value) {
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
    static private String thirdRowOfDie(Integer value) {
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

    public static void main(String[] args) {
        Casino casino = new Casino();
        Die die = new Die();
        rollSingleDieAndDisplayResult(die);
    }

}
