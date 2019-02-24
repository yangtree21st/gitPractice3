package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Dice;
import io.zipcoder.casino.Models.GuestAccount;

public class Craps extends DiceGame implements Game {

    private Guest currentGuest;
    private Dice dice1;
    private Dice dice2;
    private enum TypeOfBet {PASS, DONT_PASS}

    /**
     * Constructor. Creates two dice, and assigns the specified guest to the currentGuest field.
     * @param newGuest guest who is playing the game.
     */
    public Craps(Guest newGuest) {
        currentGuest = newGuest;
        dice1 = new Dice();
        dice2 = new Dice();
    }

    /**
     * This is the only public method of this class. Calling this method starts the Craps experience.
     * display welcome message, ask user if they want to read instructions, ask user if they want to play.
     * If they do want to play, ask user for their type of bet, then ask for the amount of their bet
     * Play a game of craps, and store the result of the game in a boolean (boolean hasWon - true is win, false is lose)
     */
    public void playFullGame() {
        printWelcomeMessage();

        if (yesOrNoQuestion("Would you like to read the instructions? (yes or no):")) {
            printInstructions();
        }

        boolean continuePlaying = yesOrNoQuestion("Would you like to start playing Craps? (yes or no):");


        while (continuePlaying) {
            TypeOfBet currentTypeOfBet = getTypeOfBetFromPlayer();
            Double currentBet = takeBetFromPlayer();

            boolean hasWon = playCraps(currentTypeOfBet);

            payOut(hasWon, currentBet);

            continuePlaying = yesOrNoQuestion("Would you like to play again? (yes or no):");
        }

        printGoodbyeMessage();
    }


    /**
     * This method takes care of the actual rolling of the dice. It takes in the currentTypeOfBet and determines whether
     * the player wins or loses based on the rolling of the dice. It asks the user to enter any key to make a roll, keeping
     * the game from being determined instantly. After each roll, the result of the roll will be displayed to the screen,
     * along with a message saying whether the player has won, lost, or needs to roll again.
     * @param currentTypeOfBet indicates which type of bet the player has made, which determines the win/lose conditions
     * @return a boolean indicating whether a player has won or lost. true = win, false = lose.
     */
    boolean playCraps(TypeOfBet currentTypeOfBet) { //TODO
//        boolean roundOver = false;
//        boolean hasWon = false;
//
//        while(!roundOver) {
//            // Come out roll
//            // Point roll
//
//            roundOver = true;
//        }
//
//        return hasWon;
        return false;
    }


    void payOut(boolean hasWon, Double currentBet) {
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

    boolean yesOrNoQuestion(String prompt) {
        String choice = getLowerCaseStringInput(prompt);
        while (!(choice.equals("yes") || choice.equals("no"))) {
            choice = getLowerCaseStringInput("Sorry, I couldn't understand you. Please enter 'yes' or 'no':");
        }
        return choice.equals("yes");
    }

    void printWelcomeMessage() {
        println("Welcome to the Craps table!");
    }

    void printGoodbyeMessage() {
        println("Thank you for visiting the Craps table!");
    }

    private void printInstructions() {
        println("Craps is a dice game involving the rolling of two dice. You win or lose money depending on what the result of the dice tosses are.\n" +
                " The game is split into two phases: the first roll, called the come-out roll, and every subsequent roll, which are called Point rolls.\n\n" +
                "There are two main bets in the game of Craps - Pass and Don’t Pass, which both pay even money. Let’s explain the Pass bet first.\n\n" +
                "Let’s say you make a Pass bet. The first thing you do is pick up the two dice and roll them for the come-out roll. If you roll a 7 or\n" +
                "an 11 on your come-out roll (this is called rolling a Natural) you win. If you roll a 2, 3, or 12 (called rolling Craps) you lose.\n\n" +
                "If you roll anything else (a 4, 5, 6, 8, 9, or 10) that specific roll result becomes the “Point”, and you enter the second phase of the\n" +
                "game. Once you establish a Point, your goal becomes to roll the Point again before rolling a 7. If you roll the Point, you win, if you\n" +
                "roll a 7, you lose (called a Seven-Out), if you roll anything else, nothing happens and you roll again.\n\n" +
                "Now let’s explain the Don’t Pass bet.\n" +
                "The Don’t Pass bet is essentially the opposite of the Pass bet. You win when the Pass bet loses, and you lose when the Pass bet wins.\n" +
                "Thus, the ways of winning a Don’t Pass bet are either rolling Craps or getting a Seven-Out. The ways of losing are rolling a Natural\n" +
                "or rolling the Point. The only difference is that if you roll a 12 on the come-out roll, the round is over but you neither win nor lose\n" +
                "(in other words, your bet stays where it is, and you roll the come-out roll again). Rolling a 12 first on a Don't Pass bet is called a Push.\n\n" +
                "After a round is over (by rolling a Natural, rolling your Point, rolling Craps, or getting a Seven-Out) you have the choice to try again.\n" +
                "Each time you try again, you can change your bet type and amount.\n\n");
    }

    TypeOfBet getTypeOfBetFromPlayer() {
        println("The two types of bets are 'pass' or 'don't pass'");
        String choice = getLowerCaseStringInput("Please enter the type of bet you would like to make:");

        while (!(choice.equals("pass") || choice.equals("don't pass"))) {
            choice = getLowerCaseStringInput("Sorry, I couldn't understand you. Please enter 'pass' or 'don't pass':");
        }

        TypeOfBet typeOfBetChoice = null;
        switch (choice) {
            case "pass":
                println("You have chosen to make a Pass bet.");
                typeOfBetChoice = TypeOfBet.PASS;
                break;
            case "don't pass":
                println("You have chosen to make a Don't Pass bet.");
                typeOfBetChoice = TypeOfBet.DONT_PASS;
        }

        return typeOfBetChoice;
    }

    Double takeBetFromPlayer() {
        println("Your current balance is %.2f", currentGuest.getAccountBalance());
        Double bet = getDoubleInput("Please enter how much you would like to bet:");

        while (! (currentGuest.getAccountBalance() - bet > .00001)) {
            println("Sorry, you don't have enough money to make a bet of ");
            bet = getDoubleInput("Please enter a bet that is less than your balance of %.2f:", currentGuest.getAccountBalance());
        }

        currentGuest.removeFunds(bet);
        return bet;
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

}
