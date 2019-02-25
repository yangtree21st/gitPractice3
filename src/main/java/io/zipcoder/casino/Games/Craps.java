package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Die;
import io.zipcoder.casino.Models.GuestAccount;

public class Craps extends DiceGame implements Game {

    private Guest currentGuest;
    private boolean roundIsStillGoing;
    private int point;

    private enum TypeOfBet {PASS, DONT_PASS}

    /**
     * Constructor. Creates two die, and assigns the specified guest to the currentGuest field.
     *
     * @param newGuest guest who is playing the game.
     */
    public Craps(Guest newGuest) {
        currentGuest = newGuest;
        die = new Die();
    }

    /**
     * This is the only public method of this class. Calling this method starts the Craps experience.
     * display welcome message, ask user if they want to read instructions, ask user if they want to play.
     * If they do want to play, ask user for their type of bet, then ask for the amount of their bet
     * Play a game of craps, and store the result of the game in a boolean (boolean hasWon - true is win, false is lose)
     */
    public void playFullGame() {
        println("Welcome to the Craps table!");

        if (yesOrNoQuestion("Would you like to read the instructions? (yes or no):")) {
            printInstructions();
        }

        boolean continuePlaying = yesOrNoQuestion("Would you like to start playing Craps? (yes or no):");


        while (continuePlaying) {
            TypeOfBet currentTypeOfBet = getTypeOfBetFromPlayer();
            Double currentBet = takeBetFromPlayer();

            boolean hasWon = shootCraps(currentTypeOfBet);

            payOut(hasWon, currentBet);

            continuePlaying = yesOrNoQuestion("Would you like to play again? (yes or no):");
        }

        println("Thanks for visiting the Craps table!");
    }


    /**
     * This method takes care of the actual rolling of the die. It takes in the currentTypeOfBet and determines whether
     * the player wins or loses based on the rolling of the die. It asks the user to enter any key to make a roll, keeping
     * the game from being determined instantly. After each roll, the result of the roll will be displayed to the screen,
     * along with a message saying whether the player has won, lost, or needs to roll again.
     *
     * @param currentTypeOfBet indicates which type of bet the player has made, which determines the win/lose conditions
     * @return a boolean indicating whether a player has won or lost. true = win, false = lose.
     */
    boolean shootCraps(TypeOfBet currentTypeOfBet) {
        boolean playerHasWon = comeOutRoll(currentTypeOfBet);

        if (this.roundIsStillGoing) {
            playerHasWon = pointRoll(currentTypeOfBet);
        }

        return playerHasWon;
    }


    private boolean comeOutRoll(TypeOfBet currentTypeOfBet) {
        println("It is now time for the Come-Out roll");
        int totalValue = diceRoll();

        while (totalValue == 12 && currentTypeOfBet == TypeOfBet.DONT_PASS) {
            println("Push! Please roll the Come-Out roll again.");
            totalValue = diceRoll();
        }

        boolean hasWon;
        if (totalValue == 2 || totalValue == 3 || totalValue == 12) {
            hasWon = false;
            this.roundIsStillGoing = false;
            println("Natural!");
        } else if (totalValue == 7 || totalValue == 11) {
            hasWon = true;
            this.roundIsStillGoing = false;
            println("Craps!");
        } else {
            hasWon = false; // This value is never checked, but need to return a value so as not to cause a null pointer exception
            this.point = totalValue;
            println("The point is now %d!", this.point);
        }

        // If the bet is Don't Pass, then the result of the hasWon condition needs to be reversed.
        if (currentTypeOfBet == TypeOfBet.DONT_PASS) {
            hasWon = !hasWon;
        }

        return hasWon;
    }

    private int diceRoll() {
        getStringInput("Press Enter to roll die");
        int diceRoll1 = this.die.getDiceRoll();
        int diceRoll2 = this.die.getDiceRoll();
        displaySingleRollResult(diceRoll1);
        displaySingleRollResult(diceRoll2);
        return diceRoll1 + diceRoll2;
    }

    private boolean pointRoll(TypeOfBet currentTypeOfBet) {
        return false;
    }


    /**
     * This method determines the appropriate payout based on the win/lose result of the preceding game of Craps.
     * If the player has won (hasWon == true), then the currentBet * 2 is added to the player's balance (i.e., they get
     * their original bet back plus their winnings)
     * If the player has lost (hasWon == false), then they get nothing (i.e., their original bet is taken by the Casino)
     * Either way, their updated account balance is printed to the screen.
     *
     * @param hasWon     whether the player has won the preceding game of Craps (true = win, false = lose)
     * @param currentBet the amount the player bet on the preceding game of Craps
     */
    void payOut(boolean hasWon, Double currentBet) {
        if (hasWon) {
            this.currentGuest.addFunds(currentBet * 2);
            println("You won $%.2f!\nYour balance is now %.2f.", currentBet, this.currentGuest.getAccountBalance());
        } else {
            println("Better luck next time!\nYour bet of %.2f has been taken by the Casino\n" +
                    "Your balance is now %.2f.", currentBet, this.currentGuest.getAccountBalance());
        }
    }


    /**
     * This uses the Casino class's console to get String input from the user.
     *
     * @param prompt The text prompt that displays
     * @param args   formatting arguments for the prompt, if any
     * @return the String that the user inputs
     */
    private String getStringInput(String prompt, Object... args) {
        return Casino.console.getStringInput(prompt, args).toLowerCase();
    }

    /**
     * This uses the Casino class's console to get String input from the user, and then converts the input to lower case.
     *
     * @param prompt The text prompt that displays
     * @param args   formatting arguments for the prompt, if any
     * @return the String that the user inputs
     */
    private String getLowerCaseStringInput(String prompt, Object... args) {
        return Casino.console.getStringInput(prompt, args).toLowerCase();
    }

    /**
     * This uses the Casino class's console to get Double input from the user.
     *
     * @param prompt The text prompt that displays
     * @param args   formatting arguments for the prompt, if any
     * @return the Double that the user inputs
     */
    private Double getDoubleInput(String prompt, Object... args) {
        return Casino.console.getDoubleInput(prompt, args);
    }

    /**
     * This simply uses the Casino class's console to print output to the screen.
     *
     * @param val  String to print
     * @param args formatting args, if any
     */
    private void println(String val, Object... args) {
        Casino.console.println(val, args);
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
     * This method simply prints the instructions for the game of Craps. This method is only called if the user answers
     * yes to a preceding question, asking them if they would like to read the instructions.
     */
    private void printInstructions() {
        println("Craps is a die game involving the rolling of two die. You win or lose money depending on what the result of the die tosses are.\n" +
                "The game is split into two phases: the first roll, called the Come-Out roll, and every subsequent roll, which are called Point rolls.\n\n" +
                "There are two main bets in the game of Craps - Pass and Don’t Pass, which both pay even money. Let’s explain the Pass bet first.\n\n" +
                "Let’s say you make a Pass bet. The first thing you do is pick up the two die and roll them for the come-out roll. If you roll a 7 or\n" +
                "an 11 on your come-out roll (this is called rolling a Natural) you win. If you roll a 2, 3, or 12 (called rolling Craps) you lose.\n\n" +
                "If you roll anything else (a 4, 5, 6, 8, 9, or 10) that specific roll result becomes the “Point”, and you enter the second phase of the\n" +
                "game. Once you establish a Point, your goal becomes to roll the Point again before rolling a 7. If you roll the Point, you win, if you\n" +
                "roll a 7(called a Seven-Out), you lose, if you roll anything else, nothing happens and you roll again.\n\n" +
                "Now let’s explain the Don’t Pass bet.\n" +
                "The Don’t Pass bet is essentially the opposite of the Pass bet. You win when the Pass bet loses, and you lose when the Pass bet wins.\n" +
                "Thus, the ways of winning a Don’t Pass bet are either rolling Craps or getting a Seven-Out. The ways of losing are rolling a Natural\n" +
                "or rolling the Point. The only difference is that if you roll a 12 on the come-out roll, the round is over but you neither win nor lose\n" +
                "(in other words, your bet stays where it is, and you roll the come-out roll again). Rolling a 12 first on a Don't Pass bet is called a Push.\n\n" +
                "After a round is over (by rolling a Natural, rolling your Point, rolling Craps, or getting a Seven-Out) you have the choice to try again.\n" +
                "Each time you try again, you can change your bet type and amount.\n\n");
    }

    /**
     * This method asks the user what type of bet they would like to make, and then returns their choice of bet as
     * a TypeOfBet enum value. TypeOfBet is defined at the start of the class to only hold the values PASS or DONT_PASS.
     * This method will continue to prompt the player if they have not chosen Pass or Don't Pass.
     *
     * @return the user's choice of bet
     */
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

    /**
     * This method asks the user what how much they would like to bet, and then returns their bet amount as a Double.
     * This method will continue to prompt the player if they have entered a bet that is larger than their avaliable
     * balance.
     *
     * @return the Double value of the amount the user is betting
     */
    Double takeBetFromPlayer() {
        println("Your current balance is %.2f", currentGuest.getAccountBalance());
        Double bet = getDoubleInput("Please enter how much you would like to bet:");

        while (!(currentGuest.getAccountBalance() - bet > .00001)) {
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
