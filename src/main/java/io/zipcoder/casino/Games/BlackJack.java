package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.CardDeck;
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
    private Guest guest;
    private BlackJackPlayer player;
    private BlackJackPlayer dealer;
    private CardDeck deck;
    private String playerChoice;
    private Boolean continueGame;
    private Double getAccountBalance;


    // TODO Organize Code Structure follow flow of the game

    /**
     * BlackJack game constructor
     * @param guest Guest
     */
    public BlackJack(Guest guest) {
        this.guest = guest;
        this.deck = new CardDeck();
        deck.shuffleDeck();
        this.continueGame = true;
        player = new BlackJackPlayer(guest);
        dealer = new BlackJackPlayer();
        this.playerChoice = "";
    }

    /**
     * Test constructor
     * @param testGuest Test guest
     * @param testDeck Test deck
     */
    BlackJack(Guest testGuest, CardDeck testDeck) {
        this.deck= testDeck;
        player = new BlackJackPlayer(testGuest);
        dealer = new BlackJackPlayer();
        this.continueGame = true;
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
                String answer = continueGameInput();
                answer = kickOutIfBroke(answer);
                endGame(answer);
            }
        }
        while (continueGame && checkPlayersBalance(this.player) > minBet);
        Casino.console.println("You have played a full game of Black Jack!");
    }

    /**
     * This method will end the game if the player's account balance is less than the buy in.
     * @param answer Answer is the string passed from continue game input.
     * @return Returns a new answer of no to end the game.
     */
    String kickOutIfBroke(String answer) {
        if(checkPlayersBalance(this.player) < minBet) {
            Casino.console.println("You broke go get more money.");
            answer = "no";
        }
        return answer;
    }

    /**
     * This method will set the bet.
     *
     * @param bet The bet the player gives.
     */
    void setBet(Double bet) {
        this.bet = bet;
    }

    /**
     * This method will return the bet.
     *
     * @return Returns the bet.
     */
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
    void setUp() {
        clearPlayersHandPlayers();
        dealCards();
        displayCardsAndScore();
        twentyOne();
    }

    /**
     * This method will return the card drawn.
     *
     * @return The next card from the deck.
     */
    Card dealOneCard() {
        return deck.dealNextCard();
    }

    /**
     * This method will instantly end the game if the player gets 21.
     */
    void twentyOne() {
        if(player.getHandScore() == 21){
            Casino.console.println("You won!!");
            giveJackpotToPlayer();
            continueGame = false;
        }
    }

    /**
     * This method will deal two cards to the player and one to the dealer at the start of the game.
     */
    void dealCards() {
        Card cardOne = dealOneCard();
        Card cardTwo = dealOneCard();

        player.addCardToHand(cardOne);
        player.addCardToHand(cardTwo);

        Card dealerCardOne = dealOneCard();
        dealer.addCardToHand(dealerCardOne);
    }

    /**
     * This method will display the current cards and score of the players in the game.
     */
    void displayCardsAndScore() {
        Casino.console.println(player.getPlayerHand().toString());
        Casino.console.println("Player Score: " + player.getHandScore().toString());


        Casino.console.println("Dealer's Cards");
        Casino.console.println(dealer.getPlayerHand().toString());
        Casino.console.println("Dealer Score: " + dealer.getHandScore().toString());
    }

    /**
     * This method will remove all the cards from the player and dealers hands.
     */
    void clearPlayersHandPlayers() {
        this.player.getPlayerHand().clearHand();
        this.dealer.getPlayerHand().clearHand();
    }

    /**
     * This method will display the current score the player.
     */
    void updateDisplay() {
        Casino.console.println("Player Score: " + player.getHandScore().toString());
    }

    /**
     * This method will allow a player to chose to stand or hit during a game.
     */
    void takeTurn() {

        while(player.getHandScore() < 21 && dealer.getHandScore() < 21) {
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
    void hit() {
        Card cardOne = dealOneCard();
        player.addCardToHand(cardOne);
        if (player.getHandScore() > 21) {
            Casino.console.println("You lost!!");
            Casino.console.println("Player Score: " + player.getHandScore().toString());
            continueGame = false;
        } else if (player.getHandScore() == 21) {
            Card cardTwo = dealOneCard();
            dealer.addCardToHand(cardTwo);
            if (dealer.getHandScore() == 21) {
                Casino.console.println("You Draw!!");
                Casino.console.println("Player Score: " + player.getHandScore().toString());
                Casino.console.println("Current Dealer Score: " + dealer.getHandScore().toString());
                player.addFunds(bet);
                Casino.console.println(checkPlayersBalance(player).toString());
                continueGame = false;
            } else {
                Casino.console.println("You Won!!");
                Casino.console.println("Current Dealer Score: " + dealer.getHandScore().toString());
                Casino.console.println("Player Score: " + getHandScore().toString());
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
    void stand() {

        while (this.dealer.getHandScore() <= 17) {
            Card cardOne = dealOneCard();
            dealer.addCardToHand(cardOne);
            Casino.console.println("Dealer's Cards");
            Casino.console.println(dealer.getPlayerHand().toString());
            Casino.console.println("Current Dealer Score: " + dealer.getHandScore().toString());
        }
        if (this.dealer.getHandScore() > 21) {
//            giveWinningsToPlayer(bet);
            giveJackpotToPlayer();
            Casino.console.println("You won!!");
        } else if (this.dealer.getHandScore() == 21) {
            Casino.console.println("You lost!!");
        } else if (this.dealer.getHandScore() < this.player.getHandScore()) {
//            giveWinningsToPlayer(bet);
            giveJackpotToPlayer();
            Casino.console.println("You won!!");
        } else if (this.dealer.getHandScore() > this.player.getHandScore() && this.dealer.getHandScore() < 21) {
            Casino.console.println("You lost!!");
        } else if(this.dealer.getHandScore() == this.player.getHandScore()) {
            Casino.console.println("Draw!!");
            player.addFunds(getBet());
        }
    }

    /**
     * This method will double the player's bet and add it into their account.
     */
    void giveJackpotToPlayer() {
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
    boolean endGame(String answer) {
        if (answer.equals("yes")) {
            this.continueGame = true;
        } else if (answer.equals("no")) {
            this.continueGame = false;
        }
        return this.continueGame;
    }

    /**
     * This method will return the user input the player enters to continue or quit the game.
     *
     * @return Returns a string based on the user input.
     */
    String continueGameInput() {
        String answer = Casino.console.getStringInput("Enter Yes to play again or No to quit the game");

        while(!(answer.equals("yes") || answer.equals("no"))) {
            answer = Casino.console.getStringInput("Enter Yes to play again or No to quit the game");
            if(answer.equals("yes") || answer.equals("no")) {
                answer = answer.toLowerCase();
                break;
            }
        }
        return answer;
    }

    /**
     * This method will return the total score in the hand.
     *
     * @return returns the total score of hand.
     */
    Integer getHandScore() {
        return player.getHandScore();
    }

    /**
     * This method will return the guest account balance.
     *
     * @return Returns the guest account balance.
     */
    Double getGetAccountBalance() {
        return getAccountBalance;
    }

    /**
     * This method will return a player.
     *
     * @return Returns a player.
     */
    BlackJackPlayer getPlayer() {
        return this.player;
    }

    /**
     * This method will return a guest.
     * @return Returns a guest.
     */
    BlackJackPlayer getDealer() {
        return dealer;
    }

    /**
     * This method will return a guest.
     *
     * @return Returns a guest.
     */
    Guest getGuest() {
        return guest;
    }

    /**
     * This method will get a return the winning player.
     * @param player The blackjack player.
     * @param dealer The blackjack dealer.
     * @return Returns the winner.
     */
    BlackJackPlayer gameWinner(BlackJackPlayer player, BlackJackPlayer dealer) {
        if (player.getHandScore() >= dealer.getHandScore()) {
            return player;
        } else {
            return dealer;
        }
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

//    public static void main(String[] args) {
//        Casino testCasino = new Casino();
//        GuestAccount guestAccount = new GuestAccount("Julian", 1, 100.0);
//
//        Guest guest = new Guest("Julian", guestAccount);
//        BlackJackPlayer player = new BlackJackPlayer(guest);
//        BlackJackPlayer dealer = new BlackJackPlayer(guest);
//        BlackJack blackJack = new BlackJack(guest);
//        blackJack.playFullGame();
//    }
}
