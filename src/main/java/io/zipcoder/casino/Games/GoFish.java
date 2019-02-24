package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Players.BlackJackPlayer;
import io.zipcoder.casino.Players.GoFishPlayer;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.Hand;

import java.util.ArrayList;

public class GoFish extends CardGame implements Game {

    private GoFishPlayer player;
    private GoFishPlayer opponent;
    private Integer numberOfPairsPlayer;
    private Integer numberOfPairsOpponent;
    private CardDeck gameDeck;
    private Hand playerHand;


    public GoFish(GoFishPlayer player, GoFishPlayer opponent, CardDeck gameDeck, Hand playerHand) {
        this.player = player;
        this.opponent = opponent;
        this.numberOfPairsPlayer = 0;
        this.numberOfPairsOpponent = 0;
        this.gameDeck = gameDeck;
        gameDeck.shuffleDeck();
        this.playerHand = playerHand;
    }

    @Override
    public GoFishPlayer getPlayer() {
        return player;
    }

    public GoFishPlayer getOpponent() {
        return opponent;
    }

    public void playFullGame() {
        Casino.console.println("You have played a full game of Go Fish!");
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
