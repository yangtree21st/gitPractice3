package io.zipcoder.casino;

import io.zipcoder.casino.Games.BlackJack;
import io.zipcoder.casino.Games.Craps;
import io.zipcoder.casino.Games.GoFish;
import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Interfaces.Game;

public class CasinoGames {

    private Guest currentGuest;
    private Game currentGame;

    CasinoGames(Guest guest) {
        this.currentGuest = guest;
    }

    void runSelectedGames() {
        Casino.console.println("Welcome to the Casino Game Floor!");
        boolean continuePlayingAGame = getUserChoiceToContinuePlaying();

        while(continuePlayingAGame) {
            String gameChoice = getUserChoiceForWhichGameToPlay();
            createAndSetCurrentGame(gameChoice);

            currentGame.playFullGame();

            continuePlayingAGame = getUserChoiceToContinuePlaying();
        }

        Casino.console.println("Thank you for visiting the Casino Game Floor!");
    }


    boolean getUserChoiceToContinuePlaying() {
        boolean result = false;

        String choice = Casino.console.getStringInput("Would you like to play a game? (yes or no):").toLowerCase();

        while(!(choice.equals("yes") || choice.equals("no"))) {
            choice = Casino.console.getStringInput("Error: Please enter yes or no:");
        }

        if (choice.equals("yes")) {
            result = true;
        }

        return result;
    }


    String getUserChoiceForWhichGameToPlay() {
        String gameChoice = Casino.console.getStringInput("Which game would you like to play?\n" +
                "Please enter: 'GoFish', 'BlackJack', 'HiLo', or 'Craps'").toLowerCase();

        while(!(gameChoice.equals("gofish") || gameChoice.equals("blackjack") || gameChoice.equals("hilo") || gameChoice.equals("craps"))) {
            gameChoice = Casino.console.getStringInput("Error: Please enter one of the following games exactly as they are written\n" +
                    "'GoFish', 'BlackJack', 'HiLo', or 'Craps'");
        }

        return gameChoice;
    }


    void createAndSetCurrentGame(String gameName) {
        switch (gameName) {
            case "gofish" :
                this.currentGame = new GoFish(this.currentGuest);
                break;
            case "blackjack" :
                this.currentGame = new BlackJack(this.currentGuest);
                break;
            case "hilo" :
                this.currentGame = new HiLo(/*this.currentGuest*/);
                break;
            case "craps" :
                this.currentGame = new Craps(this.currentGuest);
        }
    }

    public Game getCurrentGame() {
        return currentGame;
    }

}
