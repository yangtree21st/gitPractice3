package io.zipcoder.casino.Games;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.SlotReel;

public class SlotMachine extends SunhyunsGamblingGameClass {
    private SlotReel slotReel;
    private boolean hasWon;

    /**
     * Standard constructor, sets currentGuest to guest, sets slotReel to new SlotReel
     * @param guest guest who is playing the game
     */
    public SlotMachine(Guest guest) {
        super(guest, 5);
        this.slotReel = new SlotReel();
    }

    /**
     * For testing purposes
     * @param testGuest test guest
     * @param testSlotReel seeded slotReel with predictable output
     */
    SlotMachine(Guest testGuest, SlotReel testSlotReel) {
        super(testGuest, 5);
        this.currentGuest = testGuest;
        this.minimumBet = 5;
        this.slotReel = testSlotReel;
    }

    public void playFullGame() {
        println("\nWelcome to the Slot Machine!\nThe minimum bet is $%d.", minimumBet);

        if (yesOrNoQuestion("Would you like to read the instructions? (yes or no):")) {
            printInstructions();
        }

        boolean continuePlaying = yesOrNoQuestion("Would you like to start playing the Slot Machine? (yes or no):");

        while (continuePlaying) {
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
     *
     * @return
     */
    private void useTheSlots() {
        int[] slotResults = slotReel.getSlotReelResultsAndDisplayRepresentation(3);

        checkReelResults(slotResults);
    }

    private void checkReelResults(int[] slotResults) {
        this.hasWon = true;
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
        if (hasWon) {
            this.currentGuest.addFunds(currentBet * 2);
            println("You won $%.2f!\nYour balance is now $%.2f.", currentBet, this.currentGuest.getAccountBalance());
        } else {
            println("Better luck next time!\nYour bet of $%.2f has been taken by the Casino.\n" +
                    "Your balance is now $%.2f.", currentBet, this.currentGuest.getAccountBalance());
        }
    }

    /**
     * This method simply prints the instructions for the Slot Machine. This method is only called if the user answers
     * yes to a preceding question, asking them if they would like to read the instructions.
     */
    void printInstructions() {
        println("");
    }
}
