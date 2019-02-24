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

    private Double bet;
    private Guest newGuest;
    private BlackJackPlayer player;
    private BlackJackPlayer opponent;
    private CardDeck deck;
    private String playerChoice;
    private Console console;
    private boolean continueGame;
    private boolean enoughMoneyForBet;


    public BlackJack(Guest newGuest){
        this.newGuest = newGuest;

    }


    public void playFullGame() {
        this.continueGame = true;
        do {
            setUp();
            checkPlayersBalance(player);

            enoughMoneyForBet(bet, player);
            receiveBetFromPlayer(bet);

            deal();
            hit(player);


            checkPlayersBalance(player);
            updateDisplay();
            winning();
            checkPlayersBalance(player);
            quitGame();
        }
        while (continueGame);{

        }
        Casino.console.println("You have played a full game of Hi-Lo!");
   }

    public void setUp() {
        Casino.console.println("WELCOME TO BlackJack GAME"+"\n"+
                "The minimum bet is $15.00");

    }


    public Double checkPlayersBalance(Player currentPlayer) {

        return currentPlayer.getAccountBalance();

    }


    public boolean enoughMoneyForBet(Double bet, BlackJackPlayer player){
        this.bet = Casino.console.getDoubleInput("Please enter your bet:");
        double pocket = player.getAccountBalance();

        if(pocket < bet){
            console.println("Your don't have enough money for next bet");
            continueGame = false;}
            else
                continueGame = true;

    }


    //     if(pocket < mindBet){
    //     console.println("Your don't have enough money for next bet");


    public void updateDisplay() {

    }


    public void split() {

    }





    public Card deal() {
        Casino.console.println("Dealing Cards. \n");
        return super.getDeck().dealNextCard();


    }

    public void hit(BlackJackPlayer player) {

        player.getPlayerHand().addCard(deck.dealNextCard());

    }


    public void takeTurn() {
        Casino.console.println(deal().toStringCard());
        this.playerChoice =Casino.console.getStringInput("Enter 'P' for pass,'S' for surrender, Enter 'H' for hit");

        if(playerChoice.equalsIgnoreCase("P")

    }

    public Integer getHandTotal() {
        return player.getHandTotal();

    }





    public Double giveWinnings() {

        return null  ;
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

        opponent.givePlayerMoney(bet /= 2);// relate to end the current game


    }



    public void giveWinningsToPlayer(Double winnings) {

        BlackJackPlayer winner = gameWinner(player, opponent);

        winner.givePlayerMoney(bet *= 2);

    }




    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {

        if(currentPlayer.getAccountBalance() >= bet) {

            return true;

        } else {

            return false;

        }

    }



    public void receiveBetFromPlayer(Double bet) {

        this.bet += bet;

    }











    public void quitGame() {
 //       double pocket = player.getAccountBalance();

   //     if(pocket < mindBet){
   //     console.println("Your don't have enough money for next bet");


        }






    public void losing() {

    }

    public void winning(){

    }




}

