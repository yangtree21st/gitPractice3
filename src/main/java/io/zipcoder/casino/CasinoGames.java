package io.zipcoder.casino;

import io.zipcoder.casino.Displays.CasinoGamesDisplay;
import io.zipcoder.casino.Games.*;
import io.zipcoder.casino.Interfaces.Game;
import javafx.scene.Scene;

import java.util.Arrays;

public class CasinoGames {

    private Guest currentGuest;
    private Game currentGame;

    public CasinoGames(Guest guest) {
        this.currentGuest = guest;
    }

    public void runSelectedGUIGames() {
        CasinoGamesDisplay casinoGamesDisplay = new CasinoGamesDisplay();
        Main.mainStage.setScene(new Scene(casinoGamesDisplay.createCasinoGamesContent()));
        Main.mainStage.show();
    }

    void selectAndRunGame() {
        Casino.console.println("Welcome to the Casino Game Floor!");
        boolean continuePlayingAGame = getUserChoiceToContinuePlaying();

        while (continuePlayingAGame) {
            String userInput = Casino.console.getStringInput(Arrays.toString(GameEnum.values()));
            GameEnum enumeration = GameEnum.getValueOf(userInput);
            Game gameInterface = enumeration.create(currentGuest);
            gameInterface.playFullGame();

            Casino.console.println("Welcome back to the Casino Game Floor!");
            continuePlayingAGame = getUserChoiceToContinuePlaying();
        }

        Casino.console.println("Thank you for visiting the Casino Game Floor!");
    }


    boolean getUserChoiceToContinuePlaying() {
        boolean result = false;

        String choice = Casino.console.getStringInput("Would you like to play one of the games? (yes or no):").toLowerCase();

        while (!(choice.equals("yes") || choice.equals("no"))) {
            choice = Casino.console.getStringInput("Sorry, I couldn't understand you. Please enter 'yes' or 'no':");
        }

        if (choice.equals("yes")) {
            result = true;
        }

        return result;
    }

    Game getCurrentGame() {
        return currentGame;
    }

}
