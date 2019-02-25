package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Players.GoFishPlayer;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.Player;
import java.util.Random;

public class GoFish extends CardGame implements Game {
    private Guest guest;
    private Player player;
    private Player dealer;
    private Integer numberOfPairsPlayer;
    private Integer numberOfPairsDealer;
    private CardDeck goFishDeck;
    private Hand playerHand;
    private Hand dealerHand;
    private Card currentCard;
    private Card doYouHaveCard;
    private Card pairCard1;
    private Card pairCard2;
    private Integer matchesCounter;
    private Integer dealerCounter;


    public GoFish(Guest guest) {
        this.player = new Player(guest);
        this.dealer = new Player(null);
        this.numberOfPairsPlayer = 0;
        this.numberOfPairsDealer = 0;
        this.goFishDeck = new CardDeck();
        this.goFishDeck.shuffleDeck();
        this.playerHand = new Hand();
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOpponent() {
        return dealer;
    }

    public void playFullGame() {
        Casino.console.println("You have played a full game of Go Fish!");
    }

    public void updateDisplay() {

    }

    public void setUp() {
        this.matchesCounter = 0;
        this.dealerCounter = 0;

        this.goFishDeck.shuffleDeck();

        while(playerHand.getHandValue() == null && dealerHand.getHandValue() == null) {

            playerHand.addCard(goFishDeck.dealNextCard());
            dealerHand.addCard(goFishDeck.dealNextCard());

            playerHand.addCard(goFishDeck.dealNextCard());
            dealerHand.addCard(goFishDeck.dealNextCard());

            playerHand.addCard(goFishDeck.dealNextCard());
            dealerHand.addCard(goFishDeck.dealNextCard());

            playerHand.addCard(goFishDeck.dealNextCard());
            dealerHand.addCard(goFishDeck.dealNextCard());

            playerHand.addCard(goFishDeck.dealNextCard());
            dealerHand.addCard(goFishDeck.dealNextCard());

            playerHand.addCard(goFishDeck.dealNextCard());
            dealerHand.addCard(goFishDeck.dealNextCard());

            playerHand.addCard(goFishDeck.dealNextCard());
            dealerHand.addCard(goFishDeck.dealNextCard());
        }
        playerHand.getAllOfPlayerCards();

        }







    public void takeTurn() {

        for (int cardIndexBase = 0; cardIndexBase < playerHand.getAllOfPlayerCards().size(); cardIndexBase++) {

            for (int cardIndexCompare = 1; cardIndexCompare < playerHand.getAllOfPlayerCards().size(); cardIndexCompare++){

                if (playerHand.getAllOfPlayerCards().indexOf(cardIndexBase) == playerHand.getAllOfPlayerCards().indexOf(cardIndexCompare)){

                    System.out.println("You Have Matches!!! Put Those Bad Boys Down!!");

                    pairCard1 = playerHand.getAllOfPlayerCards().get(cardIndexBase) ;
                    pairCard2 = playerHand.getAllOfPlayerCards().get(cardIndexCompare);

                    playerHand.removeCard(pairCard1);
                    playerHand.removeCard(pairCard2);

                } else if (playerHand.getAllOfPlayerCards().get(cardIndexBase) == null){
                    System.out.println("Annnnnnd That's Game!!");
                }
            }
            //playerHand.getCardRank(playerHand.(cardIndex));
        }
        System.out.println("Ask the dealer if he has any: " + this.doYouHaveCard);

        for (int dealerIndex = 0; dealerIndex < dealerHand.getAllOfPlayerCards().size(); dealerIndex++) {
            if (doYouHaveCard == dealerHand.getAllOfPlayerCards().get(dealerIndex)) {
                dealerHand.removeCard(doYouHaveCard);
                playerHand.addCard(doYouHaveCard);
            } else {
                System.out.println("Go Fish!!!");
                playerHand.addCard(goFishDeck.dealNextCard());
            }
        }
    }

    public void dealerTurn() {
        Random randomDealerCard = new Random();

        for (int dealerIndex = 0; dealerIndex < dealerHand.getAllOfPlayerCards().size(); dealerIndex++) {

        }

    }

    public void quitGame() {

    }

    public void winning() {
        while (playerHand == null || dealerHand == null){
            if(matchesCounter > dealerCounter){
                System.out.println("You Seemed To Have Beaten The House There Fisher! Congratulations!!");
            }

        }

    }

    public void losing() {
        while (playerHand == null || dealerHand == null){}

    }

    public Hand getPlayerHand(GoFishPlayer player) {
        return null;
    }

    public Card askOtherPlayerforCard() {
        return null;
    }

    public void goFish() {

    }

    public GoFishPlayer breakTie() {
        return null;
    }

    public void incrementNumberOfPairs(GoFishPlayer playerToIncrement) {

    }

    public Card deal() {
        return super.getDeck().dealNextCard();
    }
}
