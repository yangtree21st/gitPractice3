package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.SlotReel;
import io.zipcoder.casino.utilities.AnsiStuff;
import io.zipcoder.casino.utilities.Banners;

public class SlotMachine extends SunhyunsGamblingGameClass {
//    private static final String JACKPOT_DISPLAY ="";//Banners.slotMachineJackPot();
//    private static final String DIAGONAL_DISPLAY = "";//Banners.slotMachineDiagonal();
//    private static final String ZIG_ZAG_DISPLAY =  ""; //Banners.slotMachineZigZag();
    private SlotReel slotReel;
    private boolean jackpot;
    private boolean diagonal;
    private boolean zigZag;

    /**
     * Standard constructor, sets currentGuest to guest, sets slotReel to new SlotReel
     *
     * @param guest guest who is playing the game
     */
    public SlotMachine(Guest guest) {
        super(guest, 1);
        this.slotReel = new SlotReel();
    }

    /**
     * For testing purposes
     *
     * @param testGuest    test guest
     * @param testSlotReel seeded slotReel with predictable output
     */
    SlotMachine(Guest testGuest, SlotReel testSlotReel) {
        super(testGuest, 5);
        this.minimumBet = 5;
        this.slotReel = testSlotReel;
    }

    public void playFullGame() {
        displayWelcome();

        println("The minimum bet is $%d.", minimumBet);

        if (yesOrNoQuestion("Would you like to read the instructions? (yes or no):")) {
            printInstructions();
        }

        boolean continuePlaying = yesOrNoQuestion("Would you like to start playing the Slot Machine? (yes or no):");

        while (continuePlaying) {
            setup();

            if (currentGuest.getAccountBalance() < minimumBet) {
                println("Sorry, you do not have enough money to play the Slot Machine.\n" +
                        "The minimum bet is $%d, and your current balance is $%.2f.", minimumBet, currentGuest.getAccountBalance());
                break;
            }

            Double currentBet = takeBetFromPlayer();

            useTheSlots();

            payOut(currentBet);

            continuePlaying = yesOrNoQuestion("\nWould you like to play again? (yes or no):");
        }

        println("\nWe hope you enjoyed your visit to the Slot Machine!");
    }


    /**
     * Resets all the win conditions to false
     */
    void setup() {
        jackpot = false;
        diagonal = false;
        zigZag = false;
    }

    /**
     * uses the slots, check the reel results, and display the slot results
     */
    void useTheSlots() {
        int[] slotResults = slotReel.getSlotReelResultsAndDisplayRepresentation(3);
        checkReelResults(slotResults);
    }

    /**
     * This method checks the results of the slot machine spin, and sets the various win conditions accordingly
     *
     * @param slotResults the results of the slot machine spin
     */
    void checkReelResults(int[] slotResults) {
        boolean jackpotBoolean = slotResults[0] == slotResults[1] && slotResults[1] == slotResults[2];

        boolean upwardsDiagonal = ((slotResults[0] == slotResults[1] - 1) || (slotResults[0] == 9 && slotResults[1] == 0)) && ((slotResults[1] == slotResults[2] - 1) || (slotResults[1] == 9 && slotResults[2] == 0));
        boolean downwardsDiagonal = ((slotResults[0] == slotResults[1] + 1) || (slotResults[0] == 0 && slotResults[1] == 9)) && ((slotResults[1] == slotResults[2] + 1) || (slotResults[1] == 0 && slotResults[2] == 9));
        boolean diagonalBoolean = upwardsDiagonal || downwardsDiagonal;

        boolean zigZag0And1 = ((Math.abs(slotResults[0] % 9 - slotResults[1] % 9) == 0) || (Math.abs(slotResults[0] - slotResults[1]) <= 1));
        boolean zigZag1And2 = ((Math.abs(slotResults[1] % 9 - slotResults[2] % 9) == 0) || (Math.abs(slotResults[1] - slotResults[2]) <= 1));
        boolean zigZagBoolean = zigZag0And1 && zigZag1And2;

        if (jackpotBoolean) {
            Banners.slotMachineJackPot();
            jackpot = true;
        } else if (diagonalBoolean) {
            Banners.slotMachineDiagonal();
            diagonal = true;
        } else if (zigZagBoolean) {
            Banners.slotMachineZigZag();
            zigZag = true;
        } else {
            Casino.console.println("Sorry, no Lines...\n");
        }
    }

    /**
     * This method determines the appropriate payout based on the win/lose result of the preceding game.
     * If the player has won (hasWon == true), then the currentBet * 2 is added to the player's balance (i.e., they get
     * their original bet back plus their winnings)
     * If the player has lost (hasWon == false), then they get nothing (i.e., their original bet is taken by the Casino)
     * Either way, their updated account balance is printed to the screen.
     *
     * @param currentBet the amount the player bet on the preceding game
     */
    void payOut(Double currentBet) {
        if (jackpot) {
            this.currentGuest.addFunds(currentBet * 51);
            println("You won $%.2f!\nYour balance is now $%.2f.", currentBet * 50, this.currentGuest.getAccountBalance());
        } else if (diagonal) {
            this.currentGuest.addFunds(currentBet * 26);
            println("You won $%.2f!\nYour balance is now $%.2f.", currentBet * 25, this.currentGuest.getAccountBalance());
        } else if (zigZag) {
            this.currentGuest.addFunds(currentBet * 11);
            println("You won $%.2f!\nYour balance is now $%.2f.", currentBet * 10, this.currentGuest.getAccountBalance());
        } else {
            Casino.console.println("Better luck next time!\nThe casino has taken your bet of $%.2f. Your balance is now $%.2f.", currentBet, this.currentGuest.getAccountBalance());
        }
    }

    /**
     * This method simply prints the instructions for the Slot Machine. This method is only called if the user answers
     * yes to a preceding question, asking them if they would like to read the instructions.
     */
    void printInstructions() {
        println("When you use the slot machine, three reels will spin. Each reel will land on a number between 0 and 9.\n" +
                "The numbers above and below these numbers will also be displayed. There are three ways to win: hitting the\n" +
                "jackpot, getting a diagonal, or getting a zig-zag. You hit the jackpot by getting all three middle numbers\n" +
                "in the reels to be the same. You get a diagonal by getting three numbers to line up in a diagonal, and you\n" +
                "get a zig-zag by getting three numbers to line up in any other way (but any two numbers in the line must be\n" +
                "at least diagonally adjacent).\n");
    }

    /**
     * Displays the welcome sign
     */
    private void displayWelcome() {
        Banners.slotMachineWelcome();
    }

    /**
     * For testing purposes
     */
    SlotReel getSlotReel() {
        return slotReel;
    }

    /**
     * For testing purposes
     */
    boolean isJackpot() {
        return jackpot;
    }

    /**
     * For testing purposes
     */
    void setJackpot(boolean jackpot) {
        this.jackpot = jackpot;
    }

    /**
     * For testing purposes
     */
    boolean isDiagonal() {
        return diagonal;
    }

    /**
     * For testing purposes
     */
    void setDiagonal(boolean diagonal) {
        this.diagonal = diagonal;
    }

    /**
     * For testing purposes
     */
    boolean isZigZag() {
        return zigZag;
    }

    /**
     * For testing purposes
     */
    void setZigZag(boolean zigZag) {
        this.zigZag = zigZag;
    }

//    public static void main(String[] args) {
//        Casino testCasino = new Casino();
//        SlotMachine slotMachine = new SlotMachine(new Guest("", new GuestAccount("", 0, 1000.0)));
//        slotMachine.playFullGame();
//    }
}
