package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.BlackJackPlayer;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Players.Player;

/**
 * The BlackJack class simulates a gambling game the object of the game is to get a higher score than 21.
 * @ authors Yang Shu, Marshilla Brahma, and Julian Rios
 * @ version 2-26-19
 */
public class BlackJack implements GamblingGame, Game {

    private final Double minBet = 15.0;
    private Double bet = 0.0;
    private Guest newGuest;
    private BlackJackPlayer player;
    private BlackJackPlayer dealer;
    private CardDeck deck;
    private String playerChoice;
    private Boolean continueGame;
    private Double getAccountBalance;

    // TODO Write-up Documentation
    // TODO Test Methods
    // TODO Organize Code Structure follow flow of the game


    public BlackJack(Guest newGuest) {
        this.newGuest = newGuest;
        this.deck = new CardDeck();
        deck.shuffleDeck();
        this.continueGame = true;
        player = new BlackJackPlayer(newGuest);
        dealer = new BlackJackPlayer();
        this.playerChoice = "";
    }

    /**
     * This method will run the game until the player runs out of money or quits.
     */
    public void playFullGame() {
        Casino.console.println("Welcome to BLACKJACK" + "\n" + "The minimum bet is $15.00");
        do {
            this.bet = Casino.console.getDoubleInput("Please enter your bet");
            if (enoughMoneyForBet(bet, this.player)) {
                setUp();
                player.getGuest().removeFunds(bet);
                takeTurn();
                gameWinner(player, dealer);
                checkPlayersBalance(player);
                Casino.console.println("Your new balance is: " + checkPlayersBalance(player).toString());
                endGame();
            }
        }
        while (continueGame && checkPlayersBalance(this.player) > minBet);
        //TODO - print when you are kicked out of loop because of insufficient funds
        Casino.console.println("You have played a full game of Black Jack!");
        if(checkPlayersBalance(this.player) < minBet) {
            Casino.console.println("You broke go get more money.");
        }
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

    public Double getBet() {
        return this.bet;
    }

    /**
     * This method will return the current account balance of the player.
     *
     * @param currentPlayer currentPlayer is the player who's account is being checked.
     * @return Returns the current balance of the player.
     */
    public Double checkPlayersBalance(Player currentPlayer) {
        return currentPlayer.getAccountBalance();
    }

    /**
     * This method will return a boolean of dependent on the amount of the player's bet and balance.
     *
     * @param bet The bet a player wants to place.
     * @param currentPlayer The current player placing a bet.
     * @return Returns a boolean dependent on the bet and balance of the current player.
     */
    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {
        if (this.bet != 0) {
            if (this.bet <= checkPlayersBalance(currentPlayer) && this.bet >= this.minBet) {
                return true;
            } else {
                Casino.console.println("Not enough funds, please try again");
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * This method will receive the bet from a player and remove the amount from their account.
     *
     * @param bet The bet amount a player places.
     */

    public void receiveBetFromPlayer(Double bet) {
        player.removeFunds(bet);
        getAccountBalance = player.getAccountBalance();
    }

    /**
     * This method will start a new game of blackjack. Resetting the hands of both the player and dealer.
     */
    public void setUp() {
        clearPlayersHandPlayers();

        Card cardOne = deal();
        Card cardTwo = deal();

        player.addCardToHand(cardOne);
        player.addCardToHand(cardTwo);
        Casino.console.println(player.getPlayerHand().toString());
        Casino.console.println("Player Score: " + player.getHandTotal().toString());

        Card dealerCardOne = deal();
        dealer.addCardToHand(dealerCardOne);
        Casino.console.println("Dealer's Cards");
        Casino.console.println(dealer.getPlayerHand().toString());
        Casino.console.println("Dealer Score: " + dealer.getHandTotal().toString());

        if(player.getHandTotal() == 21){
            Casino.console.println("You won!!");
//            giveWinningsToPlayer(bet);
            giveJackpotToPlayer();
            continueGame = false;
        }
    }

    /**
     * This method will return the card drawn.
     *
     * @return The next card from the deck.
     */
    public Card deal() {
        return deck.dealNextCard();
    }

    public void clearPlayersHandPlayers() {
        this.player.getPlayerHand().clearHand();
        this.dealer.getPlayerHand().clearHand();
    }

    /**
     * This method will display the current score the player.
     */
    public void updateDisplay() {
        Casino.console.println("Player Score: " + player.getHandTotal().toString());
    }

    /**
     * This method will allow a player to chose to stand or hit during a game.
     */
    public void takeTurn() {

        while(player.getHandTotal() < 21 && dealer.getHandTotal() < 21) {
            this.playerChoice = Casino.console.getStringInput("Enter 'S' for Stand,  Enter'H' for Hit");

            while (!(this.playerChoice.equalsIgnoreCase("H") || (playerChoice.equalsIgnoreCase("S")))) {
                this.playerChoice = Casino.console.getStringInput("Error, please enter one of the following options\nEnter 'H' for Hit, Enter 'B' for Bet, Enter'S' for Stand");
            }

            if (playerChoice.equalsIgnoreCase("H")) {
                hit();

            } else if ((playerChoice.equalsIgnoreCase("S"))) {
                stand();
                break;
            }
        }
    }

    /**
     * This method will add a card to the hand of the player or dealer.
     */
    public void hit() {
        Card cardOne = deal();
        player.addCardToHand(cardOne);
        if (player.getHandTotal() > 21) {
            Casino.console.println("You lost!!");
            Casino.console.println("Player Score: " + player.getHandTotal().toString());
            continueGame = false;
        } else if (player.getHandTotal() == 21) {
            Card cardTwo = deal();
            dealer.addCardToHand(cardTwo);
            if (dealer.getHandTotal() == 21) {
                Casino.console.println("You Draw!!");
                Casino.console.println("Player Score: " + player.getHandTotal().toString());
                Casino.console.println("Current Dealer Score: " + dealer.getHandTotal().toString());
                player.addFunds(bet);
                Casino.console.println(checkPlayersBalance(player).toString());
                continueGame = false;
            } else {
                Casino.console.println("You Won!!");
                Casino.console.println("Current Dealer Score: " + dealer.getHandTotal().toString());
                Casino.console.println("Player Score: " + getHandTotal().toString());
//                giveWinningsToPlayer(bet);
                giveJackpotToPlayer();
                Casino.console.println(checkPlayersBalance(player).toString());
                continueGame = false;
            }
        }
        Casino.console.println(cardOne.toString());
        updateDisplay();
    }

    /**
     * This method will add a card to the opposing player's hand until their hand bust or equals 21.
     */
    public void stand() {

        while (this.dealer.getHandTotal() <= 17) {
            Card cardOne = deal();
            dealer.addCardToHand(cardOne);
            Casino.console.println("Dealer's Cards");
            Casino.console.println(dealer.getPlayerHand().toString());
            Casino.console.println("Current Dealer Score: " + dealer.getHandTotal().toString());
        }
        if (this.dealer.getHandTotal() > 21) {
//            giveWinningsToPlayer(bet);
            giveJackpotToPlayer();
            Casino.console.println("You won!!");
        } else if (this.dealer.getHandTotal() == 21) {
            Casino.console.println("You lost!!");
        } else if (this.dealer.getHandTotal() < this.player.getHandTotal()) {
//            giveWinningsToPlayer(bet);
            giveJackpotToPlayer();
            Casino.console.println("You won!!");
        } else if (this.dealer.getHandTotal() > this.player.getHandTotal() && this.dealer.getHandTotal() < 21) {
            Casino.console.println("You lost!!");
        }
    }

    /**
     * This method will double the player's bet and add it into their account.
     *
     */
    public void giveJackpotToPlayer() {
        Double winning = getBet();
        winning *= 2;
        player.addFunds(winning);
        checkPlayersBalance(player);
    }

    /**
     * This method will allow the player to start a new game or quit the game and sets continue game to the result.
     *
     * @return Returns (true = new game) (false = quit)
     */
    public boolean endGame() {
        String answer = Casino.console.getStringInput("Enter Yes to play again or No to quit the game");

        while(!(answer.equals("yes") || answer.equals("no"))) {
            answer = Casino.console.getStringInput("Enter Yes to play again or No to quit the game");
            if(answer.equals("yes") || answer.equals("no")) {
                answer = answer.toLowerCase();
                break;
            }
        }

        if (answer.equals("yes")) {
            this.continueGame = true;
        } else if (answer.equals("no")) {
            this.continueGame = false;
        }
        return this.continueGame;

    }

    // TODO REMOVE THIS???
    /**
     *
     * @param player
     * @param dealer
     * @return
     */
    public BlackJackPlayer gameWinner(BlackJackPlayer player, BlackJackPlayer dealer) {

        if (player.getHandTotal() > dealer.getHandTotal()) {
            return player;
        } else {
            return dealer;
        }
    }

    /**
     * This method will return the total score in the hand.
     *
     * @return returns the total score of hand.
     */
    public Integer getHandTotal() {
        return player.getHandTotal();
    }

    /**
     * This method will return a player.
     *
     * @return Returns a player.
     */
    public Player getPlayer() {
        return this.player;
    }

    // TODO REMOVE THIS
    /**
     * This method will double the player's bet and add it into their account.
     *
     * @param winning
     */
    public void giveWinningsToPlayer(Double winning) {
        player.addFunds(winning);
        checkPlayersBalance(player);
    }

    public static void main(String[] args) {
        Casino testCasino = new Casino();
        GuestAccount guestAccount = new GuestAccount("Julian", 1, 100.0);

        Guest guest = new Guest("Julian", guestAccount);
        BlackJackPlayer player = new BlackJackPlayer(guest);
        BlackJackPlayer dealer = new BlackJackPlayer(guest);
        BlackJack blackJack = new BlackJack(guest);
        blackJack.playFullGame();
    }
}
