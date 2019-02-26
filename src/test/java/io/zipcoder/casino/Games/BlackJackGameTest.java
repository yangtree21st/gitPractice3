package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.BlackJackPlayer;
import io.zipcoder.casino.Players.Player;
import org.junit.Assert;
import org.junit.Test;

public class BlackJackGameTest {
    Casino testCasino = new Casino();

    GuestAccount guestAccount = new GuestAccount("Julian", 1, 100.0);
    Guest guest = new Guest("Julian", guestAccount);
    BlackJackPlayer player = new BlackJackPlayer(guest);
    BlackJackPlayer dealer = new BlackJackPlayer(guest);
    BlackJack blackJack = new BlackJack(guest);

    @Test
    public void hitTest() {
        // Given
        BlackJackPlayer getPlayer  = (BlackJackPlayer) blackJack.getPlayer();
        Integer expected = getPlayer.getHandTotal();
        // Then
        blackJack.hit();
        // When
        Integer actual = getPlayer.getHandTotal();
        System.out.println(expected);
        System.out.println(actual);

        Assert.assertTrue(expected < actual);
    }

    @Test
    public void getHandTotalTest() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
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
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
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
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
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
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
        Integer expected = getPlayer.getHandTotal();
        blackJack.deal();
        // When
        Integer actual = getPlayer.getHandTotal();

        // Then
        Assert.assertNotNull(actual);
    }

    @Test
    public void getHandTotalTestInBlackJack() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
        blackJack.deal();
        Integer expected = getPlayer.getHandTotal();

        // When
        Integer actual = blackJack.getHandTotal();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void giveWinningsToPlayerTest() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
//        System.out.println(getPlayer.getGuest().getAccountBalance());
        Double expected = 120.00;
        blackJack.receiveBetFromPlayer(20.00);
//        System.out.println(getPlayer.getGuest().getAccountBalance());

        // When
        blackJack.giveWinningsToPlayer(20.00);
//        System.out.println(getPlayer.getGuest().getAccountBalance());

        // Then
        Double actual = getPlayer.getGuest().getAccountBalance();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void receiveBetFromPlayerTest() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
//        System.out.println(getPlayer.getGuest().getAccountBalance());
        Double expected = 80.00;
        // When
        blackJack.receiveBetFromPlayer(20.00);

        // Then
        Double actual = blackJack.checkPlayersBalance(getPlayer);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkPlayersBalanceTest() {
        // Given
        BlackJack blackJack = new BlackJack(guest);
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
        Double expected = 100.00;
        // When
        Double actual = blackJack.checkPlayersBalance(getPlayer);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void playFullGameTest() {
        // Given

        // Then

        // When
    }

    @Test
    public void enoughMoneyForBetTest() {
        // Given
        Casino casino = new Casino(System.in, System.out);
        Double expected = 100.00;
        GuestAccount guestAccount = new GuestAccount("Julian", 1, expected);
        Guest guest = new Guest("Julian", guestAccount);
//        BlackJackPlayer playerOne = new BlackJackPlayer(guest);
        Player playerOne = new Player(guest);
        BlackJack blackJackGame = new BlackJack(guest);
        blackJackGame.setBet(10.00);
        Double bet = blackJackGame.getMinBet();
//        System.out.println(playerOne.getAccountBalance());

        // Then
        boolean actual = blackJackGame.enoughMoneyForBet(bet, playerOne);

        // When
        Assert.assertFalse(actual);
    }

    @Test
    public void enoughMoneyForBetTest2() {
        // Given
        Casino casino = new Casino(System.in, System.out);
        Double expected = 100.00;
        GuestAccount guestAccount = new GuestAccount("Julian", 1, expected);
        Guest guest = new Guest("Julian", guestAccount);
//        BlackJackPlayer playerOne = new BlackJackPlayer(guest);
        Player playerOne = new Player(guest);
        BlackJack blackJackGame = new BlackJack(guest);
        blackJackGame.setBet(15.00);
        Double bet = blackJackGame.getMinBet();
//        System.out.println(playerOne.getAccountBalance());

        // Then
        boolean actual = blackJackGame.enoughMoneyForBet(bet, playerOne);

        // When
        Assert.assertTrue(actual);
    }

}
