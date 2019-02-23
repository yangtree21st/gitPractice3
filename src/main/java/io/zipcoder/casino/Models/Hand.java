package io.zipcoder.casino.Models;

import io.zipcoder.casino.Models.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> playerHand;

    public Hand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void addCard(Card cardToAdd){
        playerHand.add(cardToAdd);
    }

    public void removeCard(Card cardToRemove){
        playerHand.remove(cardToRemove);

    }

//    public int getHandValue(){
//
//        return playerHand.
//    }

    public String makeOfCard(Card cardValue) {
//        enum Suit suit = cardFace.getCardSuit();
        return cardValue.getValue().toString();
    }

    @Override
    public String toString() {
        StringBuilder createCardFace = new StringBuilder();
        for(Card faceCard : playerHand) {
            createCardFace.append(makeOfCard(faceCard));
        }
        return createCardFace.toString();
    }


}
