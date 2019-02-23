package io.zipcoder.casino;

import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.Hand;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
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
    public void makeTopOfCardTest() {
        // Given
        ArrayList<Card> playerHand = new ArrayList<>();
        Hand hand = new Hand(playerHand);
        Card expectedCard = new Card(Card.Suit.HEARTS, 2);

        // When
        ArrayList<Card> actualHand = hand.getPlayerHand();
        hand.removeCard(cardRemove);

        // Then
        boolean expected = actualHand.contains(cardRemove);
    }

//    @Test Need this?
//    public void getHandValueTest() {
//        // Given
//        ArrayList<Card> playerHand = new ArrayList<>();
//        Hand hand = new Hand(playerHand);
//    }


}
