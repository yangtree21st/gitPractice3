package io.zipcoder.casino;

import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.Hand;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class HandTest {

    @Test
    public void getPlayerHandTest(){
        //Given
        ArrayList<Card> expectedHand = new ArrayList<>();
        Hand hand = new Hand(expectedHand);

        //When
        ArrayList<Card> actualHand = hand.getPlayerHand();

        //Then
        Assert.assertEquals(expectedHand, actualHand);
    }

    @Test
    public void addCardTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card expectedCard = new Card(Card.Suit.HEARTS, Card.Rank.TEN);

        // When
        hand.addCard(expectedCard);
        ArrayList<Card> actualHand = hand.getPlayerHand();

        // Then
        boolean expected = actualHand.contains(expectedCard);

        Assert.assertTrue(expected);
    }

    @Test
    public void removeCardTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card cardRemove = new Card(Card.Suit.HEARTS, Card.Rank.TEN);

        // When
        hand.addCard(cardRemove);
        ArrayList<Card> actualHand = hand.getPlayerHand();
        hand.removeCard(cardRemove);

        // Then
        boolean expected = actualHand.contains(cardRemove);

        Assert.assertFalse(expected);
    }

    @Test
    public void getCardValueTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card expectedCard = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        Card.Rank expectedCardValue = Card.Rank.TEN;

        // When
        hand.addCard(expectedCard);
        Card.Rank actualCardValue = expectedCard.getValue();

        // Then
       Assert.assertEquals(expectedCardValue, actualCardValue);
    }

    @Test
    public void getCardSuitTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card expectedCard = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        String expectedCardSuit = Card.Suit.HEARTS.toString();

        // When
        hand.addCard(expectedCard);
        String actualCardSuit = hand.getCardSuit(expectedCard);

        // Then
        Assert.assertEquals(expectedCardSuit, actualCardSuit);
    }

    @Test
    public void toStringTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);

        Card expectedCardFirst = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
        hand.addCard(expectedCardFirst);

        Card expectedCardSecond = new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN);
        hand.addCard(expectedCardSecond);

        String expectedString = "**************\n" + "   " + "HEARTS\n" + "    " + "TEN\n**************\n";
        expectedString += "**************\n" + "   " + "DIAMONDS\n" + "    " + "QUEEN\n**************\n";

        // When
        String actualString = hand.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void setHandValueTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Integer expectedHandValue = 10;

        // When
        hand.setHandValue(10);
        Integer actualHandValue = hand.getHandValue();

        // Then
        Assert.assertEquals(expectedHandValue, actualHandValue);
    }

    @Test
    public void getHandValueTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Integer expectedHandValue = 1;

        // When
        hand.setHandValue(1);
        Integer actualHandValue = hand.getHandValue();

        // Then
        Assert.assertEquals(expectedHandValue, actualHandValue);
    }
}
