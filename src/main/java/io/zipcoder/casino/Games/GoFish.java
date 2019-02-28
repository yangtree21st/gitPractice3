package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.Player;
import io.zipcoder.casino.utilities.Banners;

import java.util.Random;

public class GoFish extends CardGame implements Game {
    private Player player;
    private Player dealer;
    private Integer numberOfPairsPlayer;
    private Integer numberOfPairsDealer;
    private CardDeck goFishDeck;
    private Hand playerHand;
    private Hand dealerHand;
    private Card currentCard;
    private Card checkCard;


    /**
     * @param guest
     */
    public GoFish(Guest guest) {
        this.player = new Player(guest);
        this.dealer = new Player(null);
        this.numberOfPairsPlayer = 0;
        this.numberOfPairsDealer = 0;
        this.goFishDeck = new CardDeck();
        this.goFishDeck.shuffleDeck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.currentCard = new Card();
        this.checkCard = new Card();
    }

    /**
     * FOR TESTING PURPOSES ONLY
     *
     * @param testGuest test guest
     * @param testDeck  non random deck of cards
     */
    GoFish(Guest testGuest, CardDeck testDeck) {
        this.player = new Player(testGuest);
        this.dealer = new Player(null);
        this.numberOfPairsPlayer = 0;
        this.numberOfPairsDealer = 0;
        this.goFishDeck = new CardDeck();
        this.goFishDeck.shuffleDeck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.currentCard = new Card();
        this.checkCard = new Card();
        this.goFishDeck = new CardDeck();
    }

    public Player getPlayer(Guest guest) {
        return player;
    }

    public void setPlayer() {
        this.player = player;
    }

    public Player getOpponent() {
        return dealer;
    }

    public void setOpponent() {
        this.dealer = dealer;
    }


    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getOpponentHand() {
        return dealerHand;
    }


    public CardDeck getDeck() {
        return this.goFishDeck;
    }

    public void playFullGame() {


        Banners.goFishUpdateDisplay();

        playGoFish();

        setUp();

        showPlayerHand(playerHand);


        do {


            if (containsPairs(playerHand) > 0) {
                numberOfPairsPlayer++;
            }
            displayCurrentScore();
            showPlayerHand(playerHand);
            takeTurn(playerHand, dealerHand);
            displayCurrentScore();


            showPlayerHand(playerHand);

            if (containsPairs(dealerHand) > 0) {
                numberOfPairsDealer++;
            }

            opponentTurn(dealerHand, playerHand);


        } while (!(playerHand.getAllOfPlayerCards().size() == 0 || dealerHand.getAllOfPlayerCards().size() == 0 || goFishDeck.getCardDeckSize() == 0));

        winning(numberOfPairsPlayer, numberOfPairsDealer);

        losing(numberOfPairsDealer, numberOfPairsPlayer);

        tie(numberOfPairsPlayer, numberOfPairsDealer);

        Casino.console.println("You have played a full game of Go Fish!");

        playGoFish();

    }



    public void playGoFish() {

        Casino.console.getStringInput("Press Enter To Continue");
    }


    public void setUp() {
        this.numberOfPairsDealer = 0;
        this.numberOfPairsPlayer = 0;

        shuffleDeck(goFishDeck);

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());


    }

    public Card deal() {

        return goFishDeck.dealNextCard();
    }

    public void shuffleDeck(CardDeck cardDeck) {

        cardDeck.shuffleDeck();
    }

    public void showPlayerHand(Hand hand) {

        Casino.console.println(hand.toString() + "\n");
        for (int i = 0; i < hand.getAllOfPlayerCards().size(); i++) {
            Casino.console.print("[%d]            ", i);
        }
    }

    public Integer containsPairs(Hand hand) {
        int counter = 0;

        for (int cardIndex = 0; cardIndex < hand.getAllOfPlayerCards().size() - 1; cardIndex++) {
            for (int checkIndex = cardIndex + 1; checkIndex < hand.getAllOfPlayerCards().size(); checkIndex++) {
                currentCard = hand.getAllOfPlayerCards().get(cardIndex);
                checkCard = hand.getAllOfPlayerCards().get(checkIndex);

                if (currentCard.getValue() == checkCard.getValue()) {
                    counter++;
                    removePairs(hand);
                    pairsCounter();
                }

            }
        }
        return counter;

    }

    private void removePairs(Hand hand) {


        hand.getAllOfPlayerCards().remove(currentCard);
        hand.getAllOfPlayerCards().remove(checkCard);

    }

    public void pairsCounter() {

        numberOfPairsPlayer += containsPairs(playerHand);

        numberOfPairsDealer += containsPairs(dealerHand);

    }

    public void takeTurn(Hand playerHand, Hand dealerHand) {

        Casino.console.println("\nTake Your Turn");

        boolean goFish = true;

        int input = Casino.console.getIntegerInput("\"Pick A Card To Fish For [Enter Index Number Below Card Choice]: \"");

        while (input < 0 || input >= playerHand.getAllOfPlayerCards().size()) {
            input = Casino.console.getIntegerInput("\"Pick A Card To Fish For [Enter Index Number Below Card Choice]: \"");
        }

        for (Card c : dealerHand.getAllOfPlayerCards()) {
            if (c.getValue() == playerHand.getAllOfPlayerCards().get(input).getValue()) {
                dealerHand.getAllOfPlayerCards().remove(c);
                playerHand.addCard(c);
                Casino.console.println("\nYOU GOT YOUR OPPONENT'S\n  " + c.toString());
                numberOfPairsDealer++;

                goFish = false;
                break;
            }
        }
        /*for (int dealerIndex = 0; dealerIndex < dealerHand.getAllOfPlayerCards().size(); dealerIndex++){
        if (playerHand.getAllOfPlayerCards().get(input).getValue() == dealerHand.getAllOfPlayerCards().get(dealerIndex).getValue()) {

                dealerHand.removeCard(dealerHand.getAllOfPlayerCards().get(dealerIndex));
                playerHand.addCard(dealerHand.getAllOfPlayerCards().get(dealerIndex));
                Casino.console.println("\nYOU GOT YOUR OPPONENT'S\n  " +  dealerHand.getAllOfPlayerCards().get(dealerIndex).toString() );
                goFish = false;


                }*/

        if (goFish) {
            Casino.console.println("\nGO FISH!!!");
            playerHand.addCard(deal());
        }

    }


    public void opponentTurn(Hand dealerHand, Hand playerHand) {

        Casino.console.println("\nNow It Is Your Opponent's Turn");

        Random randomNumGen = new Random();


        Card fishCard = dealerHand.getAllOfPlayerCards().get(randomNumGen.nextInt(dealerHand.getAllOfPlayerCards().size()));

        Casino.console.println("\nDo You Have Any " + fishCard.getValue());
        Casino.console.getStringInput("\nPress [Enter] To Continue");
        boolean goFish = true;
//        for (int cardIndex = 0; cardIndex < playerHand.getAllOfPlayerCards().size(); cardIndex++) {
//            if (playerHand.getAllOfPlayerCards().get(cardIndex).getValue() == fishCard.getValue()) {
//                playerHand.getAllOfPlayerCards().remove(cardIndex);
//                dealerHand.removeCard(fishCard);
//                Casino.console.println("\nYOU JUST GAVE UP YOUR\n  " +  playerHand.getAllOfPlayerCards().get(cardIndex).toString() );
//                numberOfPairsDealer++;
//
//                goFish = false;
//                break;
//
//            }
//        }

        for (Card c : playerHand.getAllOfPlayerCards()) {
            if (c.getValue() == fishCard.getValue()) {
                playerHand.getAllOfPlayerCards().remove(c);
                dealerHand.removeCard(fishCard);
                Casino.console.println("\nYOU JUST GAVE UP YOUR\n  " + c.toString());
                numberOfPairsDealer++;

                goFish = false;
                break;

            }
        }
        if (goFish) {
            Casino.console.println("YOU LAUGH AND TELL YOUR OPPONENT TO GO FISH!!!");
            if (goFishDeck.getCardDeckSize() > 0) {

                dealerHand.addCard(deal());
            }
        }


    }


    private void displayCurrentScore() {

        Casino.console.println("\nYou Currently Have " + numberOfPairsPlayer + " matches.");
        Casino.console.println("\nYour Opponent Currently Has " + numberOfPairsDealer + " matches.");

    }

    public void winning(Integer numberOfPairsPlayer, Integer numberOfPairsDealer) {
        if (numberOfPairsPlayer > numberOfPairsDealer) {
            Banners.goFishWinMessage();

        }
    }

    public void losing(Integer numberOfPairsDealer, Integer numberOfPairsPlayer) {

        if (numberOfPairsDealer > numberOfPairsPlayer) {
            Banners.goFishLostMessage();

        }

    }

    public void tie(Integer numberOfPairsDealer, Integer numberOfPairsPlayer) {

        if (numberOfPairsDealer == numberOfPairsPlayer) {

            Banners.goFishStatieMessage();
        }

    }
}




