package io.zipcoder.casino.Models;

import java.util.ArrayList;
/**
 * The Hand program simply simulates a hand that a player or opponent holds.
 *
 * @version 2019-02-23
 */
public class Hand {

    private ArrayList<Card> playerHand;
    private Integer handValue;

    /**
     * The Hand Constructor will initialize an ArrayList of Card.
     * @param playerHand The playerHand is used to store an ArrayList of Cards.
     */
    public Hand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
        this.handValue = 0;
    }

    /**
     * This method will return an ArrayList of Card to the hand.
     * @return The an ArrayList of Card is specifying the cards in the hand.
     */
    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    /**
     * This method will add a card to the hand.
     * @param cardToAdd The cardToAdd is the card being added to the hand.
     */
    public void addCard(Card cardToAdd){
        playerHand.add(cardToAdd);
    }

    /**
     * This method will remove a card from the hand.
     * @param cardToRemove The cardToRemove is the card being removed from the hand.
     */
    public void removeCard(Card cardToRemove){
        playerHand.remove(cardToRemove);

    }

    /**
     * This method will return the total value held in the hand.
     * @param playerHand The playerHand is used to check the cards in the hand.
     * @return The total value in the hand is returned.
     */
    public Integer getCardValuesInHand(ArrayList<Card> playerHand) {
        for (Card cardInHand: playerHand) {
            this.handValue += cardInHand.getValue();
        }
        return this.handValue;
    }

    /**
     * This method will return the total value held in the hand after a card value is changed.
     * @param playerHand The playerHand is used to check the cards in the hand.
     * @param cardToChange The cardToChange is the card that will mutate it's state.
     * @param changeValue The changeValue is the value the card will change too.
     */
    public void setCardValuesInHand(ArrayList<Card> playerHand, Card cardToChange, Integer changeValue) {
        for (Card cardInHand: playerHand) {
            if(cardInHand.equals(cardToChange)) {
                cardInHand.setValue(changeValue);
            }
        }
    }

    /**
     * This method will get the cardValue and return it as a string.
     * @param cardValue The cardValue is the card being parsed.
     * @return Returns the card value as a String
     */
    public String getCardValue(Card cardValue) {
        return cardValue.getValue().toString();
    }

    /**
     * This method will get the cardSuit and return it as a string.
     * @param cardSuit The cardSuit is the card being parsed.
     * @return Returns the card suit as a string.
     */
    public String getCardSuit(Card cardSuit) {
        return cardSuit.getCardSuit().toString();
    }

    /**
     * This method will return a string representation of the cards in a hand.
     * @return Returns the cards in a hand as a string.
     */
    @Override
    public String toString() {
        StringBuilder createCardFace = new StringBuilder();
        for(Card faceCard : playerHand) {
            createCardFace.append("**************\n");
            createCardFace.append("   " + getCardSuit(faceCard));
            createCardFace.append("\n");
            createCardFace.append("     " + getCardValue(faceCard));
            createCardFace.append("\n");
            createCardFace.append("**************\n");
        }
        return createCardFace.toString();
    }
}
