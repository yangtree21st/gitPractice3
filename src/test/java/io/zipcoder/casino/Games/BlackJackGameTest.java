package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.BlackJackPlayer;
import io.zipcoder.casino.Players.Player;
import org.junit.Assert;
import org.junit.Test;

public class BlackJackGameTest {
    GuestAccount guestAccount = new GuestAccount("Julian",1, 100.00);
    Guest guest = new Guest("Julian", guestAccount);
    BlackJack testBlackJack = new BlackJack(guest);
    Casino newCasino = new Casino();
    //BlackJackPlayer player = new BlackJackPlayer(guest);

    @Test
    public void hitTest() {
        // Given
        BlackJackPlayer getplayer  = (BlackJackPlayer) testBlackJack.getPlayer();
        Integer expected = getplayer.getHandTotal();
        // Then
        testBlackJack.hit();
        // When
        Integer actual = getplayer.getHandTotal();
        System.out.println(expected);
        System.out.println(actual);

        Assert.assertTrue(expected < actual);
    }

    @Test
    public void getHandTotalTest() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) testBlackJack.getPlayer();
        Card cardOne = new Card(Card.Suit.HEARTS, Card.Rank.TWO);
        Card cardTwo = new Card(Card.Suit.SPADES, Card.Rank.FIVE);
        Integer expected = 7;
        getPlayer.getPlayerHand().addCard(cardOne);
        getPlayer.getPlayerHand().addCard(cardTwo);

        // Then
        Integer actual = getPlayer.getHandTotal();
        System.out.println(expected);
        System.out.println(actual);

        //When
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHandTotalTestForAce() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) testBlackJack.getPlayer();
        Card cardOne = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
        Card cardTwo = new Card(Card.Suit.SPADES, Card.Rank.FIVE);
        Integer expected = 16;
        getPlayer.getPlayerHand().addCard(cardOne);
        getPlayer.getPlayerHand().addCard(cardTwo);

        // Then
        Integer actual = getPlayer.getHandTotal();
        System.out.println(expected);
        System.out.println(actual);

        //When
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHandTotalTestForAce2() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) testBlackJack.getPlayer();
        Card cardOne = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
        Card cardTwo = new Card(Card.Suit.SPADES, Card.Rank.KING);
        Integer expected = 21;
        getPlayer.getPlayerHand().addCard(cardOne);
        getPlayer.getPlayerHand().addCard(cardTwo);

        // Then
        Integer actual = getPlayer.getHandTotal();
        System.out.println(expected);
        System.out.println(actual);

        //When
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void dealTest() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) testBlackJack.getPlayer();
        Integer expected = getPlayer.getHandTotal();
        testBlackJack.deal();
        // When
        Integer actual = getPlayer.getHandTotal();

        // Then
        Assert.assertNotNull(actual);

    }


}
