package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.CrapsDisplay;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Main;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.HiLowPlayer;
import io.zipcoder.casino.Players.Player;
import io.zipcoder.casino.utilities.Console;
import javafx.scene.Scene;


import java.util.ArrayList;
import java.util.HashMap;

public class HiLo extends CardGame implements GamblingGame {

    private Card currentCard;
    private Card nextCard;
    private CardDeck cardDeck;
    private Guest guest;
    private Player hiloPlayer;
    private String playerChoice;
    private Double bet = 0.0;
    private Double getAccountBalance;
    private boolean continueGame;
    private Double minimumBet = 5.0;
    private Double winnings;


    public HiLo(Guest guest) {

        hiloPlayer = new Player(guest);
        this.cardDeck = new CardDeck();
        cardDeck.shuffleDeck();
        this.continueGame = true;

    }

    public CardDeck getDeck() {
        return cardDeck;
    }

    public Card deal() {
        if (cardDeck.peekAtTopCard() == null) {
            continueGame = false;
        }
        currentCard = cardDeck.dealNextCard();
//        if (currentCard != null) {
//            nextCard = cardDeck.dealNextCard();
//            return nextCard;
////        } else if (nextCard!=null){
////                currentCard =nextCard;}
//////            return nextCard =cardDeck.dealNextCard();
////        return currentCard;
        return currentCard;
    }

    public Card deal2() {
        nextCard = cardDeck.dealNextCard();
        return nextCard;
    }

//    public void discard(){
//        if(nextCard!=null){
//        currentCard =nextCard;}
//
//
//    }


    public boolean isLess(Card currentCard, Card nextCard) {
        Integer currentCardValue = currentCard.getValue().ordinal() + 1;
        Integer nextCardValue = nextCard.getValue().ordinal() + 1;

        // Casino.console.println("cardvalue"+currentCardValue);
        //Casino.console.println("secondcardvalue  "+nextCardValue);

        if (nextCardValue < currentCardValue) {

            return true;
        }
        return false;
    }


    public boolean isMore(Card currentCard, Card nextCard) {

        return !isLess(currentCard, nextCard);
    }

    public void playFullGame() {
        setUp();
        do {
            //checkPlayersBalance(hiloPlayer);

            //if (checkPlayersBalance(hiloPlayer) >= minimumBet) {
                    this.bet = Casino.console.getDoubleInput("Please enter your bet:");
                    if(enoughMoneyForBet(bet, hiloPlayer)){
                receiveBetFromPlayer(bet);
                //checkPlayersBalance(hiloPlayer);
                takeTurn();
                updateDisplay();
                winning();
                checkPlayersBalance(hiloPlayer);
                quitGame();
            }
        }
        while (checkPlayersBalance(hiloPlayer) >= minimumBet && this.continueGame ==true);
        Casino.console.println("You have played a full game of Hi-Lo!");
    }

    public void setUp() {

        Casino.console.println("WELCOME TO HI-LO GAME" + "\n" +
                "The minimum bet is $5.00");

    }


    public Double checkPlayersBalance(Player currentPlayer) {
        getAccountBalance = hiloPlayer.getAccountBalance();
        //Casino.console.println("This is your balance "+ getAccountBalance);
        return getAccountBalance;
    }

    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {
        //this.bet = Casino.console.getDoubleInput("Please enter your bet:");
        if (bet != 0) {
            if (bet <= checkPlayersBalance(currentPlayer)) {
                return true;
            } else {
                Casino.console.println("Not enough funds, please try again");
                //this.continueGame = false;
                return false;
            }
        } else {
            return false;
        }

        /*
        if (checkPlayersBalance(hiloPlayer) >= minimumBet) {
            this.bet = Casino.console.getDoubleInput("Please enter your bet:");
            return true;
        } else {
            continueGame = false;
            return false;
        }
        */
    }

//
//        else if (this.bet > checkPlayersBalance(hiloPlayer)) {
//            continueGame=false;
//            Casino.console.println("You don't have enough funds");
//            }
//
//        if ((checkPlayersBalance(hiloPlayer) >= minimumBet) && (checkPlayersBalance(hiloPlayer) >= this.bet &&
//                this.bet < minimumBet)&&this.bet > minimumBet) {
//
//            return true;
////        } else if (this.bet < minimumBet && checkPlayersBalance(hiloPlayer) >= minimumBet) {
////            do {
////                Casino.console.println("Your bet needs to be equal or higher than the minimum bet");
////                this.bet = Casino.console.getDoubleInput("Please enter your bet:");
////                this.continueGame = false;
////
////                return false;
////            } while ((this.bet < minimumBet));
//
//
//        }return continueGame;


//    public void betLessThanMinimum() {
//        if (this.bet < minimumBet && checkPlayersBalance(hiloPlayer) >= minimumBet) {
//            do {
//                Casino.console.println("Your bet needs to be equal or higher than the minimum bet");
//                this.bet = Casino.console.getDoubleInput("Please enter your bet:");
////                this.continueGame = false;
////
////                return false;
//            } while ((this.bet < minimumBet));
//        }
//    }


    public void receiveBetFromPlayer(Double bet) {
        hiloPlayer.removeFunds(bet);
        getAccountBalance = hiloPlayer.getAccountBalance();

    }

    public void takeTurn() {
        Casino.console.println(deal().toStringCard());
        this.playerChoice = Casino.console.getStringInput("Enter 'H' for Higher,'L' for Low");

    }

    public void updateDisplay() {
        Casino.console.println(deal2().toStringCard());

    }

    public void winning() {
        if (playerChoice.equalsIgnoreCase("H") && isMore(currentCard, nextCard) ||
                (playerChoice.equalsIgnoreCase("L") && isLess(currentCard, nextCard))) {
            Casino.console.println("You Win");
            giveWinningsToPlayer(winnings);
        } else Casino.console.println("You Lose");
//        {
//        else if(playerChoice.equalsIgnoreCase("L")&&isLess(currentCard,nextCard)){
//            Casino.console.println("You Win");
//            giveWinningsToPlayer(winnings);


    }

    public void losing() {
        if (playerChoice.equals("H") && isMore(currentCard, nextCard) == false) {
            Casino.console.println("You Lose");

        } else if (playerChoice.equals("L") && isLess(currentCard, nextCard) == false) {
            Casino.console.println("You Lose");

        }

    }

    public void giveWinningsToPlayer(Double winnings) {

        winnings = bet * 1.25;
        hiloPlayer.addFunds(winnings);
        getAccountBalance = hiloPlayer.getAccountBalance();


    }

    public void quitGame() {
        if(getAccountBalance != 0) {
            while (true) {
                this.playerChoice =
                        Casino.console.getStringInput("Do you wish to continue, Enter 'Y' for Yes, 'N' for No");

                if (playerChoice.equalsIgnoreCase("N")) {
                    this.continueGame = false;
                    break;

                } if (playerChoice.equalsIgnoreCase("Y")) {
                    this.continueGame = true;
                    break;
                }
            }
        }
    }

/*
    public static void main(String[] args) {
        Casino testCasino = new Casino();
        GuestAccount guestAccount = new GuestAccount("Marlys", 1, 100.0);

        Guest guest = new Guest("Marlys", guestAccount);
        Player hiloplayer = new HiLowPlayer(guest);
        HiLo testHiLo = new HiLo(guest);
        testHiLo.playFullGame();
    }

*/

}


