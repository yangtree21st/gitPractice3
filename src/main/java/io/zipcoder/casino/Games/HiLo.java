package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Players.Player;

public class HiLo extends CardGame implements GamblingGame {

    private Card currentCard;
    private Card nextCard;
    private CardDeck cardDeck;
    private Guest guest;

    public HiLo(){

    }

    public HiLo(Guest guest){
        this.guest = guest;
        this.cardDeck = new CardDeck();
        cardDeck.shuffleDeck();

    }

    public Card deal() {

        currentCard = cardDeck.dealNextCard();
        if (currentCard == null) {
            currentCard = cardDeck.dealNextCard();
            return currentCard;
        } else {
            return nextCard =cardDeck.dealNextCard();
        }
    }
    public CardDeck getDeck() {

        return cardDeck;
    }
    public void discard(){


    }

    public boolean isLess(Card currentCard, Card nextCard) {

        return false;
    }

    public boolean isMore(Card currentCard, Card nextCard) {
        return false;
    }

    public void playFullGame() {
        Casino.console.println("You have played a full game of Hi-Lo!");
    }

    public void updateDisplay() {

    }

    public void setUp() {

    }

    public void takeTurn() {

    }

    public void quitGame() {

    }

    public void winning() {

    }

    public void losing() {

    }

    public void receiveBetFromPlayer(Double bet) {

    }

    public void giveWinningsToPlayer(Double winnings) {

    }

    public Double checkPlayersBalance(Player currentPlayer) {
        return null;
    }

    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {
        return false;
    }

}
