package io.zipcoder.casino;

import io.zipcoder.casino.Games.*;
import io.zipcoder.casino.Interfaces.Game;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CasinoGames {

    private Guest currentGuest;
    private Game currentGame;

    CasinoGames(Guest guest) {
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

        while(continuePlayingAGame) {
            String gameChoice = getUserChoiceForWhichGameToPlay();
            createAndSetCurrentGame(gameChoice);

            currentGame.playFullGame();

            Casino.console.println("Welcome back to the Casino Game Floor!");

            continuePlayingAGame = getUserChoiceToContinuePlaying();
        }

        Casino.console.println("Thank you for visiting the Casino Game Floor!");
    }


    boolean getUserChoiceToContinuePlaying() {
        boolean result = false;

        String choice = Casino.console.getStringInput("Would you like to play one of the games? (yes or no):").toLowerCase();

        while(!(choice.equals("yes") || choice.equals("no"))) {
            choice = Casino.console.getStringInput("Sorry, I couldn't understand you. Please enter 'yes' or 'no':");
        }

        if (choice.equals("yes")) {
            result = true;
        }

        return result;
    }


    String getUserChoiceForWhichGameToPlay() {
        String gameChoice = Casino.console.getStringInput("Which game would you like to play?\n" +
                "Please enter: 'Slots' 'Go Fish', 'Black Jack', 'Hi-Lo', or 'Craps'").toLowerCase().replaceAll("-", "").replaceAll(" ", "");

        while(!(gameChoice.equals("slots") || gameChoice.equals("gofish") || gameChoice.equals("blackjack") || gameChoice.equals("hilo") || gameChoice.equals("craps"))) {
            gameChoice = Casino.console.getStringInput("Error: Please enter one of the following games\n" +
                    "'Slots', 'Go Fish', 'Black Jack', 'Hi-Lo', or 'Craps'").toLowerCase();
        }

        return gameChoice;
    }


    void createAndSetCurrentGame(String gameName) {
        switch (gameName) {
            case "slots":
                this.currentGame = new SlotMachine(this.currentGuest);
                break;
            case "gofish" :
                this.currentGame = new GoFish(this.currentGuest);
                break;
            case "blackjack" :
                this.currentGame = new BlackJack(this.currentGuest);
                break;
            case "hilo" :
                this.currentGame = new HiLo(this.currentGuest);
                break;
            case "craps" :
                this.currentGame = new Craps(this.currentGuest);
        }
    }

    Game getCurrentGame() {
        return currentGame;
    }

}
