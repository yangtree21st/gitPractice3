package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Models.Die;

public abstract class DiceGame {
    Die die;

    int diceRoll() {
        Casino.console.getStringInput("Press Enter to roll dice");
        int diceRoll1 = this.die.getDiceRoll();
        int diceRoll2 = this.die.getDiceRoll();
        displaySingleRollResult(diceRoll1);
        displaySingleRollResult(diceRoll2);
        Casino.console.println("");
        return diceRoll1 + diceRoll2;
    }

    void displaySingleRollResult(Integer value) {
        String representation = createDieRepresentation(value);
        Casino.console.print(representation);
    }

    private String createDieRepresentation(Integer value) {

        return boundaryLineOfDie() +
                firstRowOfDie(value) +
                secondRowOfDie(value) +
                thirdRowOfDie(value) +
                boundaryLineOfDie();
    }

    private String boundaryLineOfDie() {
        return "---------\n";
    }

    private String firstRowOfDie(Integer value) {
        String result = "";

        if (value == 6 || value == 5 || value == 4) {
            result = "| o   o |\n";
        } else if (value == 3 || value == 2) {
            result = "| o     |\n";
        } else if (value == 1) {
            result = "|       |\n";
        }

        return result;
    }

    private String secondRowOfDie(Integer value) {
        String result = "";

        if (value == 6) {
            result = "| o   o |\n";
        } else if (value == 5 || value == 3 || value == 1) {
            result = "|   o   |\n";
        } else if (value == 4 || value == 2) {
            result = "|       |\n";
        }

        return result;
    }

    private String thirdRowOfDie(Integer value) {
        String result = "";

        if (value == 6 || value == 5 || value == 4) {
            result = "| o   o |\n";
        } else if (value == 3 || value == 2) {
            result = "|     o |\n";
        } else if (value == 1) {
            result = "|       |\n";
        }

        return result;
    }

}
