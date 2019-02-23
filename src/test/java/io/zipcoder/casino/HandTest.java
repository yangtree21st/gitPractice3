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
        Card expectedCard = new Card(Card.Suit.HEARTS, 2);

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
        Card cardRemove = new Card(Card.Suit.HEARTS, 2);

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
        Card expectedCard = new Card(Card.Suit.HEARTS, 2);
        String expectedCardValue = Integer.toString(2);

        // When
        hand.addCard(expectedCard);
        String actualCardValue = hand.getCardValue(expectedCard);

        // Then
       Assert.assertEquals(expectedCardValue, actualCardValue);
    }

    @Test
    public void getCardSuitTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card expectedCard = new Card(Card.Suit.HEARTS, 2);
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

        Card expectedCardFirst = new Card(Card.Suit.HEARTS, 12);
        hand.addCard(expectedCardFirst);

        Card expectedCardSecond = new Card(Card.Suit.DIAMONDS, 2);
        hand.addCard(expectedCardSecond);

        String expectedString = "**************\n" + "   " + "HEARTS\n" + "     " + "12\n**************\n";
        expectedString += "**************\n" + "   " + "DIAMONDS\n" + "     " + "2\n**************\n";

        // When
        String actualString = hand.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void getHandValueTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card cardOne = new Card(Card.Suit.HEARTS, 11);
        Card cardTwo = new Card(Card.Suit.SPADES, 10);
        Integer expectedHandValue = 21;

        // When
        hand.addCard(cardOne);
        hand.addCard(cardTwo);
        Integer actualHandValue = hand.getCardValuesInHand(playerHand);

        // Given
        Assert.assertEquals(expectedHandValue, actualHandValue);
    }

    @Test
    public void setHandValueTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card cardOne = new Card(Card.Suit.HEARTS, 1);
        Integer expectedHandValue = 11;

        // When
        hand.addCard(cardOne);
        hand.setCardValuesInHand(playerHand, cardOne, 11);
        Integer actualHandValue = hand.getCardValuesInHand(playerHand);

        // Given
        Assert.assertEquals(expectedHandValue, actualHandValue);
    }


    @Test
    public void getHandValueTest2() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card cardOne = new Card(Card.Suit.HEARTS, 1);
        Card cardTwo = new Card(Card.Suit.SPADES, 10);
        Integer expectedHandValue = 21;

        // When
        hand.addCard(cardOne);
        hand.addCard(cardTwo);
        hand.setCardValuesInHand(playerHand, cardOne, 11);
        Integer actualHandValue = hand.getCardValuesInHand(playerHand);

        // Given
        Assert.assertEquals(expectedHandValue, actualHandValue);
    }
}
