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
import io.zipcoder.casino.utilities.Console;


public class BlackJack implements GamblingGame, Game {

    private final Double minBet = 15.0;
    private Double bet = 0.0;
    //    private Double pot;
    private Guest newGuest;
    private BlackJackPlayer player;
    private BlackJackPlayer dealer;
    private CardDeck deck;
    private String playerChoice;
    private Boolean continueGame;
    private Double getAccountBalance;


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
     * This method will add a card to the hand of the current player.
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
                giveWinningsToPlayer(bet);
                Casino.console.println(checkPlayersBalance(player).toString());
                continueGame = false;
            }

        }
        Casino.console.println(cardOne.toString());
        updateDisplay();
    }

    public Integer getHandTotal() {
        return player.getHandTotal();
    }

    public Card deal() {
        return deck.dealNextCard();
    }

    public void giveWinningsToPlayer(Double winning) {
        winning = bet * 2;
        player.addFunds(winning);
    }

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
        while (continueGame == true && checkPlayersBalance(this.player) > minBet);//TODO - print when you are kicked out of loop because of insufficient funds

        Casino.console.println("You have played a full game of Black Jack!");
    }

    /**
     * This method will add a card to the opposing player's hand until their hand bust or equals 21.
     *
     * @param
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
            giveWinningsToPlayer(bet);
            Casino.console.println("You won!!");
        } else if (this.dealer.getHandTotal() == 21) {
            Casino.console.println("You lost!!");
        }


    }

//    /**
//     * This method will withdraw their hand and give the dealer half of their pot.
//     */
//    public void withdraw(){
//        dealer.givePlayerMoney(bet /= 2);// relate to end the current game
//    }

    /**
     * This method will return the current account balanace of the player.
     *
     * @param currentPlayer currentPlayer is the player who's account is being checked.
     * @return
     */
    public Double checkPlayersBalance(Player currentPlayer) {
        return currentPlayer.getAccountBalance();
    }

    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {
        if (this.bet != 0) {
            if (this.bet <= checkPlayersBalance(currentPlayer) && this.bet >= this.minBet) {
                return true;
            } else {
                Casino.console.println("Not enough funds, please try again");
                return false;
            }
        } else {
            return false;
        }
    }

    public void receiveBetFromPlayer(Double bet) {
        player.removeFunds(bet);
        getAccountBalance = player.getAccountBalance();
    }

    public void updateDisplay() {
        Casino.console.println("Player Score: " + player.getHandTotal().toString());
    }

    public void setUp() {
        this.player.getPlayerHand().clearHand();
        this.dealer.getPlayerHand().clearHand();
        Card cardOne = deal();
        //Casino.console.println("Player's Card");
        //Casino.console.println(cardOne.toStringCard());

        Card cardTwo = deal();
        //Casino.console.println(cardTwo.toStringCard());

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
            giveWinningsToPlayer(bet);
            continueGame = false;
        }
    }

    public void takeTurn() {

        // do {

        //Casino.console.println("Is it looping here?@@#?$@#?$@#$?@#?$?@#$?");
        while(player.getHandTotal() < 21 && dealer.getHandTotal() < 21) {
            this.playerChoice = Casino.console.getStringInput("Enter 'S' for Stand,  Enter'H' for Hit");

            while (!(this.playerChoice.equalsIgnoreCase("H") || (playerChoice.equalsIgnoreCase("S")))) {
                this.playerChoice = Casino.console.getStringInput("Error, please enter one of the following options\nEnter 'H' for Hit, Enter 'B' for Bet, Enter'S' for Stand");
            }

            if (playerChoice.equalsIgnoreCase("H")) {
                hit();

//            } else if (playerChoice.equalsIgnoreCase("B")) {
//                receiveBetFromPlayer(bet);

            } else if ((playerChoice.equalsIgnoreCase("S"))) {
                stand();
                break;
//                String playerH = player.getPlayerHand().toString();
//                String dealerH = dealer.getPlayerHand().toString();
//                Casino.console.println("The player's card: " + playerH);
//                Casino.console.println("The dealer's card: " + dealerH);

                // gameWinner(player, dealer);
                // giveWinningsToPlayer(bet);
//                endGame();
            }
        }


    }


    //while (endGame());


//    public void takeTurn() {
//        this.playersChoice = Casino.console.getStringInput("Enter 'H' for hit, 'S' for stand, 'W' for Withdraw");
//
//        this.playersChoice = this.playersChoice.toUpperCase();
//        if(this.playersChoice.equals("H")) {
//            hit();
//        }
//        else if (this.playersChoice.equals("P")) {
//            stand();
//        } else if(this.playersChoice.equals("W")) {
//            withdraw();
//        }
//    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean endGame() {

        String answer = Casino.console.getStringInput("Enter Yes to play again or No to quit the game");
        answer = answer.toLowerCase();
        if (answer.equals("yes")) {
            this.continueGame = true;
        } else if (answer.equals("no")) {

            this.continueGame = false;

        } else {

            Casino.console.getStringInput("Please make an option from Enter yes or no");

        }
        return this.continueGame;

    }

    public void winning() {

    }

    public void losing() {

    }

    public BlackJackPlayer gameWinner(BlackJackPlayer player, BlackJackPlayer dealer) {

        if (player.getHandTotal() > dealer.getHandTotal()) {
            return player;
        } else {
            return dealer;
        }
    }

    public static void main(String[] args) {
        Casino testCasino = new Casino();
        GuestAccount guestAccount = new GuestAccount("Julian", 1, 100.00);

        Guest guest = new Guest("Julian", guestAccount);
        BlackJackPlayer player = new BlackJackPlayer(guest);
        BlackJack testBlackJack = new BlackJack(guest);

        GuestAccount casino = new GuestAccount("Casino", 999, 100000000.00);
        Guest dealer = new Guest("Dealer", casino);
        BlackJackPlayer dealerBlackJack = new BlackJackPlayer(dealer);

        testBlackJack.playFullGame();
    }
}
