package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.Game;

public abstract class SunhyunsGameClass implements Game {
    Guest currentGuest;

    /**
     * Standard constructor sets guest to currentGuest
     *
     * @param guest guest who is playing the game
     */
    SunhyunsGameClass(Guest guest) {
        this.currentGuest = guest;
    }

    /**
     * This asks the user a yes or no question, makes sure they answer yes or no, and returns what they wrote.
     *
     * @param prompt the text prompt that displays
     * @return the user's answer, yes or no
     */
    boolean yesOrNoQuestion(String prompt) {
        String choice = getLowerCaseStringInput(prompt);
        while (!(choice.equals("yes") || choice.equals("no"))) {
            choice = getLowerCaseStringInput("Sorry, I couldn't understand you. Please enter 'yes' or 'no':");
        }
        return choice.equals("yes");
    }

    /**
     * This uses the Casino class's console to get String input from the user.
     *
     * @param prompt The text prompt that displays
     * @param args   formatting arguments for the prompt, if any
     * @return the String that the user inputs
     */
    String getStringInput(String prompt, Object... args) {
        return Casino.console.getStringInput(prompt, args);
    }

    /**
     * This uses the Casino class's console to get String input from the user, and then converts the input to lower case.
     *
     * @param prompt The text prompt that displays
     * @param args   formatting arguments for the prompt, if any
     * @return the String that the user inputs
     */
    String getLowerCaseStringInput(String prompt, Object... args) {
        return Casino.console.getStringInput(prompt, args).toLowerCase();
    }

    /**
     * This uses the Casino class's console to get Double input from the user.
     *
     * @param prompt The text prompt that displays
     * @param args   formatting arguments for the prompt, if any
     * @return the Double that the user inputs
     */
    Double getDoubleInput(String prompt, Object... args) {
        return Casino.console.getDoubleInput(prompt, args);
    }

    /**
     * This simply uses the Casino class's console to print output to the screen.
     *
     * @param val  String to print
     * @param args formatting args, if any
     */
    void println(String val, Object... args) {
        Casino.console.println(val, args);
    }

    /**
     * This method simply prints the instructions for the gambling game. This method is only called if the user answers
     * yes to a preceding question, asking them if they would like to read the instructions.
     */
    abstract void printInstructions();

    /**
     * For testing purposes.
     *
     * @return the current guest
     */
    Guest getCurrentGuest() {
        return currentGuest;
    }

}
