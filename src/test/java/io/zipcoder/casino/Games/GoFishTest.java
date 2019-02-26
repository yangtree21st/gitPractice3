package io.zipcoder.casino.Games;

import io.zipcoder.casino.Games.GoFish;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.GoFishPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GoFishTest {

    // Before
    GuestAccount highRoller = new GuestAccount("Julian", 777, 1000000.00);
    GuestAccount guestAccount2 = new GuestAccount("guest2", 2, 1.00);
    Guest guest = new Guest("Julian", highRoller);
    Guest guest2 = new Guest("guest2", guestAccount2);
    GoFishPlayer playerOne = new GoFishPlayer(guest);
    GoFishPlayer playerTwo = new GoFishPlayer(guest2);
    CardDeck gameDeck = new CardDeck();
    Hand playerHand = new Hand();
    Hand player2Hand = new Hand();
    GoFish game = new GoFish(guest);
    Card testCard1 = new Card(Card.Suit.SPADES, Card.Rank.TEN);
    Card testCard2 = new Card(Card.Suit.HEARTS, Card.Rank.TEN);
    Card testCard3 = new Card(Card.Suit.SPADES, Card.Rank.TWO);
    Card testCard4 = new Card(Card.Suit.CLUBS, Card.Rank.KING);
    Card testCard5 = new Card(Card.Suit.DIAMONDS, Card.Rank.JACK);
    Card testCard6 = new Card(Card.Suit.HEARTS, Card.Rank.QUEEN);
    Card testCard7 = new Card(Card.Suit.CLUBS, Card.Rank.JACK);
    Card testCard8 = new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE);


    @Test
    public void setUpTest() {
        //Given
        playerHand.addCard(gameDeck.dealNextCard());
        playerHand.addCard(gameDeck.dealNextCard());
        playerHand.addCard(gameDeck.dealNextCard());
        playerHand.getAllOfPlayerCards();

        //When
        Boolean expected = playerHand.getAllOfPlayerCards().isEmpty();

        //Then
        Assert.assertFalse(expected);

    }

    @Test
    public void containsPairsTest() {
        //Given
        playerHand.addCard(testCard1);
        playerHand.addCard(testCard2);
        playerHand.addCard(testCard3);
        playerHand.addCard(testCard4);
        playerHand.addCard(testCard5);
        Integer expected = 1;


        //When
        Integer actual = game.containsPairs(playerHand);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void containsPairsTest2() {
        //Given
        playerHand.addCard(testCard2);
        playerHand.addCard(testCard3);
        playerHand.addCard(testCard4);
        playerHand.addCard(testCard5);
        Integer expected = 0;


        //When
        Integer actual = game.containsPairs(playerHand);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removePairsTest1() {
        //Given
        playerHand.addCard(testCard1);
        playerHand.addCard(testCard2);
        playerHand.addCard(testCard3);
        playerHand.addCard(testCard4);
        playerHand.addCard(testCard5);
        Integer expected = 3;
        //When
        game.containsPairs(playerHand);

        Integer actual = playerHand.getAllOfPlayerCards().size();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removePairsTest2() {
        //Given
        playerHand.addCard(testCard2);
        playerHand.addCard(testCard3);
        playerHand.addCard(testCard4);
        playerHand.addCard(testCard5);
        Integer expected = 4;

        //When
        game.containsPairs(playerHand);
        Integer actual = playerHand.getAllOfPlayerCards().size();

        //Then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void playerTurnTest() {

        //Given
        playerHand.addCard(testCard2);
        playerHand.addCard(testCard3);
        playerHand.addCard(testCard4);
        playerHand.addCard(testCard5);
        player2Hand.addCard(testCard2);
        player2Hand.addCard(testCard6);
        player2Hand.addCard(testCard8);


    }


}
