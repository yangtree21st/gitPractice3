package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Models.Hand;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BlackJackPlayerTest {


    private ArrayList<Card> PHand   = new ArrayList<>();
    private  Hand playerhand = new Hand(PHand);

    Guest newGuest = new Guest("Yang", new GuestAccount("Yang", 1 ,100.00));

    @Test
    public void addCardToHand() {

           Card card1 =  new Card(Card.Suit.DIAMONDS, Card.Rank.TEN);
           BlackJackPlayer player = new BlackJackPlayer(newGuest);

           Hand actual =  player.addCardToHand(card1);

           Hand expected = player.getPlayerHand();

           Assert.assertEquals(expected, actual);

    }

    @Test
    public void getPlayerHand() {
        Card card1 =  new Card(Card.Suit.SPADES, Card.Rank.ACE);
        playerhand.addCard(card1);

        BlackJackPlayer player = new BlackJackPlayer(newGuest);
        player.addCardToHand(card1);
        Hand actual = player.getPlayerHand();

        Assert.assertEquals(playerhand, actual);

    }

    @Test
    public void removeCardFromHand() {
        Card card1 =  new Card(Card.Suit.SPADES, Card.Rank.ACE);
        Card card2 =  new Card(Card.Suit.DIAMONDS, Card.Rank.TEN);
        playerhand.addCard(card1);
        playerhand.addCard(card2);
        playerhand.removeCard(card1);

        BlackJackPlayer player = new BlackJackPlayer(newGuest);
        player.addCardToHand(card1);
        player.addCardToHand(card2);

        player.removeCardFromHand(card1);

        Hand actual = player.getPlayerHand();


        Assert.assertEquals(playerhand, actual);


    }
}