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
     * @return Returned is the total value in the hand.
     */
    public Integer getHandValue() {
        return this.handValue;
    }

    /**
     * This method changes the value of the hand.
     * @param changeValue The changeValue is the value the card will be set to.
     */
    public void setHandValue(Integer changeValue) {
        this.handValue = changeValue;
    }

    /**
     * This method will get the card suit and return it as a string.
     * @param cardSuit The cardSuit is the card being parsed.
     * @return Returns the card suit as a string.
     */
    public String getCardSuit(Card cardSuit) {
        return cardSuit.getCardSuit().toString();
    }

    /**
     * This method will get the card rank and return it as a string.
     * @param cardRank The cardRank is the card being parsed.
     * @return Returns the card rank as a string.
     */
    public String getCardRank(Card cardRank) {
        return cardRank.getValue().toString();
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
            createCardFace.append("   " );
            createCardFace.append(getCardSuit(faceCard));
            createCardFace.append("\n");
            createCardFace.append("    ");
            createCardFace.append(getCardRank(faceCard));
            createCardFace.append("\n");
            createCardFace.append("**************\n");
        }
        return createCardFace.toString();
    }
}
