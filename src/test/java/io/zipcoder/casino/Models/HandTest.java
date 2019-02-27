package io.zipcoder.casino.Models;

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
        Hand hand = new Hand();

        //When
        ArrayList<Card> actualHand = hand.getAllOfPlayerCards();

        //Then
        Assert.assertEquals(expectedHand, actualHand);
    }

    @Test
    public void addCardTest() {
        // Given
        Hand hand = new Hand();
        Card expectedCard = new Card(Card.Suit.HEARTS, Card.Rank.TEN);

        // When
        hand.addCard(expectedCard);
        ArrayList<Card> actualHand = hand.getAllOfPlayerCards();

        // Then
        boolean expected = actualHand.contains(expectedCard);

        Assert.assertTrue(expected);
    }

    @Test
    public void removeCardTest() {
        // Given
        Hand hand = new Hand();
        Card cardRemove = new Card(Card.Suit.HEARTS, Card.Rank.TEN);

        // When
        hand.addCard(cardRemove);
        ArrayList<Card> actualHand = hand.getAllOfPlayerCards();
        hand.removeCard(cardRemove);

        // Then
        boolean expected = actualHand.contains(cardRemove);

        Assert.assertFalse(expected);
    }

    @Test
    public void getCardValueTest() {
        // Given
        Hand hand = new Hand();
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
        Hand hand = new Hand();
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
        Hand hand = new Hand();

        Card expectedCardFirst = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
        hand.addCard(expectedCardFirst);

        Card expectedCardSecond = new Card(Card.Suit.DIAMONDS, Card.Rank.TEN);
        hand.addCard(expectedCardSecond);

        Card expectedCardThird = new Card(Card.Suit.SPADES, Card.Rank.SEVEN);
        hand.addCard(expectedCardThird);

        String expectedString = "-------------- -------------- -------------- \n" +
                "|A           | |10          | |7           | \n" +
                "|♥           | |♦           | |♠           | \n" +
                "|            | |            | |            | \n" +
                "|     A♥     | |    10♦     | |     7♠     | \n" +
                "|            | |            | |            | \n" +
                "|           ♥| |           ♦| |           ♠| \n" +
                "|           A| |          10| |           7| \n" +
                "-------------- -------------- -------------- \n";

        // When
        String actualString = hand.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void getCardRank() {
        // Given
        Hand hand = new Hand();
        Card.Rank expectedRank = Card.Rank.TEN;
        String expected = "TEN";


        // When
        String actual = hand.getCardRank(new Card(Card.Suit.HEARTS, expectedRank));

        // Then
        Assert.assertEquals(expected, actual);
    }
}
