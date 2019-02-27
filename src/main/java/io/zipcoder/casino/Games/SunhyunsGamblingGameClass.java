package io.zipcoder.casino.Games;

import io.zipcoder.casino.Guest;

public abstract class SunhyunsGamblingGameClass extends SunhyunsGameClass {
    int minimumBet;

    SunhyunsGamblingGameClass(Guest guest, int minimumBet) {
        super(guest);
        this.currentGuest = guest;
        this.minimumBet = minimumBet;
    }

    /**
     * This method asks the user what how much they would like to bet, and then returns their bet amount as a Double.
     * This method will continue to prompt the player if they have entered a bet that is larger than their avaliable
     * balance. It also checks to make sure that their bet is greater than or equal to the minimum bet.
     *
     * @return the Double value of the amount the user is betting
     */
    Double takeBetFromPlayer() {
        println("\nYour current balance is $%.2f", currentGuest.getAccountBalance());
        Double bet = getDoubleInput("Please enter how much you would like to bet:");

        while (!((currentGuest.getAccountBalance() - bet > -.00001) && (bet >= minimumBet))) {
            if (currentGuest.getAccountBalance() - bet < -.00001) {
                println("Sorry, you don't have enough money to make a bet of $%.2f.\nYour current balance is $%.2f:", bet, currentGuest.getAccountBalance());
            } else if (bet < minimumBet) {
                println("Sorry, the minimum bet is $%d.", minimumBet);
            }
            bet = getDoubleInput("Please enter a valid bet.");
        }

        println("You have chosen to bet $%.2f", bet);
        currentGuest.removeFunds(bet);
        return bet;
    }

    /**
     * for testing purposes
     */
    int getMinimumBet() {
        return minimumBet;
    }
}
