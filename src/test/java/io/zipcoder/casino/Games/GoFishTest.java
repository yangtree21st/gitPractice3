package io.zipcoder.casino.Games;

import io.zipcoder.casino.Games.GoFish;
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
    Hand playerHand;
    GoFish game = new GoFish(guest);

    @Test
    public void deal() {
        // Given
        GoFishPlayer playerOne = new GoFishPlayer(guest);
        playerOne.getPlayerHand().addCard(game.deal());
        // Then
        Assert.assertNotNull(playerOne.getPlayerHand());
    }

    @Test
    public void goFishTest() {
        // Given
        GoFishPlayer playerOne = new GoFishPlayer(guest);
        playerOne.getPlayerHand().addCard(game.deal());
        Integer expected = 2;

        // When
        playerOne.getPlayerHand().addCard(game.deal());

        // Then
        Integer actual = playerOne.getPlayerHand().getAllOfPlayerCards().size();
        Assert.assertEquals(expected, actual);
    }
}
