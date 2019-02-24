package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.BlackJackPlayer;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Players.Player;
import io.zipcoder.casino.utilities.Console;


public class BlackJack extends CardGame implements GamblingGame {

    private final Integer max = 21;
    private final Double mindBet = 15.0;
    private BlackJackPlayer oppositePlayer;
    private Double pot;
    private Guest newGuest;
    private BlackJackPlayer player;
    private BlackJackPlayer opponent;
    private CardDeck deck;
    private boolean
    private Console console;



    public BlackJack(Guest newGuest){
        this.newGuest = newGuest;

    }

    public void setUp() {

    }

    public void updateDisplay() {

    }


    public void split() {

    }



    public void hit() {
        Card card = deck.dealNextCard();
        player.addCardToHand(card);

    }

    public Integer getHandTotal() {
        return player.getHandTotal();

    }


    public Card deal() {
        Casino.console.println("Dealing Cards. \n");
        return super.getDeck().dealNextCard();


        }


    public Double giveWinnings() {

        return null  ;
    }

    public void playFullGame() {

        Casino.console.println("You have played a full game of Black Jack!");
    }


    public void hit(BlackJackPlayer player) {

        player.getPlayerHand().addCard(deck.dealNextCard());

    }



    public BlackJackPlayer gameWinner(BlackJackPlayer player, BlackJackPlayer opponent) {

        if(player.getHandTotal() > opponent.getHandTotal()) {

            return player;

        } else {

            return opponent;

        }

    }



    public void pass(){

        hit(opponent);

    }



    public void surrender(){

        opponent.givePlayerMoney(pot /= 2);// relate to end the current game


    }



    public void giveWinningsToPlayer(Double winnings) {

        BlackJackPlayer winner = gameWinner(player, opponent);

        winner.givePlayerMoney(pot *= 2);

    }



    public Double checkPlayersBalance(Player currentPlayer) {

        return currentPlayer.getAccountBalance();

    }



    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {

        if(currentPlayer.getAccountBalance() >= bet) {

            return true;

        } else {

            return false;

        }

    }



    public void receiveBetFromPlayer(Double bet) {

        this.pot += bet;

    }







    public void takeTurn() {

    }

    public void noticeAc

    public void quitGame() {
 //       double pocket = player.getAccountBalance();

   //     if(pocket < mindBet){
   //     console.println("Your don't have enough money for next bet");


        }

    }




    public void losing() {

    }



}
