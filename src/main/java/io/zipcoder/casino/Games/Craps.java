package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Dice;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.Player;

public class Craps implements Game {

    private Guest currentGuest;
    private Dice dice1;
    private Dice dice2;
    private enum TypeOfBet {PASS, DONTPASS}
    String startPrompt = "Would you like to start playing Craps?";
    String continuePrompt = "Would you like to play again?";

    public Craps(Guest newGuest) {
        currentGuest = newGuest;
        dice1 = new Dice();
        dice2 = new Dice();
    }

    public void playFullGame() {
        printWelcomeMessage(); // Display welcome and instructions

        // setup stuff
        boolean continuePlaying;
        continuePlaying = choiceToPlay(startPrompt);


        while (continuePlaying) {
            TypeOfBet currentTypeOfBet = getTypeOfBetFromPlayer(); // This will get the type of bet from player
            Double currentBet = takeBetFromPlayer(); // This will take a bet from the player, make sure it's not over their balance

            boolean hasWon = playCraps(currentBet, currentTypeOfBet);

            // end of game stuff
            payOut(hasWon, currentBet);
            continuePlaying = choiceToPlay(continuePrompt);
        }

        printGoodbyeMessage();
    }


    
    private void printWelcomeMessage() { //TODO add instructions
        println("Welcome to the Craps table!");
        println("Instructions");
    }

    private void printGoodbyeMessage() {
        println("Thank you for visiting the Craps table!");
    }
    
    
    
    private boolean choiceToPlay(String prompt) {
        String choice = getLowerCaseStringInput(prompt);
        
        while (!(choice.equals("yes") || choice.equals("no"))) {
            choice = getLowerCaseStringInput("Sorry, I couldn't understand you. Please enter 'yes' or 'no':");
        }

        return choice.equals("yes");
    }

    
    
    private TypeOfBet getTypeOfBetFromPlayer() { //TODO
        println("The two types of bets are 'pass' or 'don't pass'");
        String choice = getLowerCaseStringInput("Please enter the type of bet you would like to make:");

        while (!(choice.equals("pass") || choice.equals("don't pass"))) {
            choice = getLowerCaseStringInput("Sorry, I couldn't understand you. Please enter 'pass' or 'don't pass':");
        }

        TypeOfBet typeOfBetChoice = null;
        switch (choice) {
            case "pass":
                typeOfBetChoice = TypeOfBet.PASS;
                break;
            case "don't pass":
                typeOfBetChoice = TypeOfBet.DONTPASS;
        }
        
        return typeOfBetChoice;
    }

    private Double takeBetFromPlayer() { //TODO
        println("Your current balance is %.2f", currentGuest.getAccountBalance());
        Double bet = getDoubleInput("Please enter how much you would like to bet:");

        while (! (currentGuest.getAccountBalance() - bet > .00001)) {
            println("Sorry, you don't have enough money to make a bet of ");
            bet = getDoubleInput("Please enter a bet that is less than your balance of %.2f:", currentGuest.getAccountBalance());
        }

        currentGuest.removeFunds(bet);
        return bet;
    }



    private boolean playCraps(Double currentBet, TypeOfBet currentTypeOfBet) { //TODO
        boolean roundOver = false;
        boolean hasWon = false;

        while(!roundOver) {
            // Come out roll
            // Point roll

            // updateDisplay(currentBet, currentTypeOfBet, currentRoll, hasWon);
            roundOver = true;
        }

        return hasWon;
    }
    
    private void payOut(boolean hasWon, Double currentBet) { //TODO
        if (hasWon) {
            this.currentGuest.addFunds(currentBet * 2);
            println("You won $%.2f!\nYour balance is now %.2f.", currentBet, this.currentGuest.getAccountBalance());
        } else {
            println("Better luck next time!\nYour bet of %.2f has been taken by the Casino\n" +
                    "Your balance is now %.2f.", currentBet, this.currentGuest.getAccountBalance());
        }
    }


    
    private String getLowerCaseStringInput(String prompt, Object... args) {
        return Casino.console.getStringInput(prompt, args).toLowerCase();
    }
    
    private Double getDoubleInput(String prompt, Object... args) {
        return Casino.console.getDoubleInput(prompt, args);
    }
    
    private void println(String val, Object... args) {
        Casino.console.println(val, args);
    }
    
    
    public static void main(String[] args) {
        Casino casino = new Casino();
        Craps craps = new Craps(new Guest("Sunhyun", new GuestAccount("Sunhyun", 1, 1000.0)));

        craps.playFullGame();
    }









    public void updateDisplay() {

    }

    public void setUp() {

    }

    public void takeTurn() {

    }

    public void quitGame() {

    }

    public void winning() {

    }

    public void losing() {

    }

    public void receiveBetFromPlayer(Double bet) {

    }

    public void giveWinningsToPlayer(Double winnings) {

    }

    public Double checkPlayersBalance(Player currentPlayer) {
        return null;
    }

    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {
        return false;
    }


}
