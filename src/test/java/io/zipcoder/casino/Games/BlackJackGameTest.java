package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.BlackJackPlayer;
import io.zipcoder.casino.Players.Player;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BlackJackGameTest {
    private Casino testCasino;
    private ByteArrayInputStream bytArrInpStr;
    private ByteArrayOutputStream bytArrOutStr;

    GuestAccount guestAccount = new GuestAccount("Julian", 1, 100.0);
    Guest guest = new Guest("Julian", guestAccount);
    BlackJackPlayer player = new BlackJackPlayer(guest);
    BlackJackPlayer dealer = new BlackJackPlayer(guest);
    BlackJack blackJack = new BlackJack(guest);

    @Test
    public void hitTest() {
        // Given
        BlackJackPlayer getPlayer  = (BlackJackPlayer) blackJack.getPlayer();
        Integer expected = getPlayer.getHandScore();
        // Then
        blackJack.hit();
        // When
        Integer actual = getPlayer.getHandScore();
//        System.out.println(expected);
//        System.out.println(actual);

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
        Integer actual = getPlayer.getHandScore();
//        System.out.println(expected);
//        System.out.println(actual);

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
        Integer actual = getPlayer.getHandScore();
//        System.out.println(expected);
//        System.out.println(actual);

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
        Integer actual = getPlayer.getHandScore();
//        System.out.println(expected);
//        System.out.println(actual);

        //When
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void dealTest() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
        blackJack.dealOneCard();
        // When
        Integer actual = getPlayer.getHandScore();

        // Then
        Assert.assertNotNull(actual);
    }

    @Test
    public void getHandTotalTestInBlackJack() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
        blackJack.dealOneCard();
        Integer expected = getPlayer.getHandScore();

        // When
        Integer actual = blackJack.getHandScore();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void giveJackpotToPlayerTest() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
//        System.out.println(getPlayer.getGuest().getAccountBalance());
        Double expected = 140.00;
        blackJack.setBet(20.00);
//        System.out.println(getPlayer.getGuest().getAccountBalance());

        // When
        blackJack.giveJackpotToPlayer();
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
        Double bet = blackJackGame.getBet();
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
        Double bet = blackJackGame.getBet();
//        System.out.println(playerOne.getAccountBalance());

        // Then
        boolean actual = blackJackGame.enoughMoneyForBet(bet, playerOne);

        // When
        Assert.assertTrue(actual);
    }

    @Test
    public void enoughMoneyForBetTest3() {
        // Given
        Casino casino = new Casino(System.in, System.out);
        Double expected = 1.00;
        GuestAccount guestAccount = new GuestAccount("Julian", 1, expected);
        Guest guest = new Guest("Julian", guestAccount);
        Player playerOne = new Player(guest);
        BlackJack blackJackGame = new BlackJack(guest);
        blackJackGame.setBet(15.00);
        Double bet = blackJackGame.getBet();

        // Then
        boolean actual = blackJackGame.enoughMoneyForBet(bet, playerOne);

        // When
        Assert.assertFalse(actual);
    }

    @Test
    public void clearPlayersHandPlayersTest() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
        BlackJackPlayer dealer = new BlackJackPlayer(guest);
        blackJack.dealOneCard();
        Integer expected = 0;

        // Then
        blackJack.clearPlayersHandPlayers();
        Integer actualPlayerHand = getPlayer.getHandScore();

        // When
        Assert.assertEquals(expected, actualPlayerHand);

    }

    @Test
    public void clearPlayersHandPlayersTest2() {
        // Given
        BlackJackPlayer getPlayer = (BlackJackPlayer) blackJack.getPlayer();
        BlackJackPlayer dealer = new BlackJackPlayer(guest);
        blackJack.dealOneCard();
        Integer expected = 0;

        // Then
        blackJack.clearPlayersHandPlayers();
        Integer actualDealerHand = dealer.getHandScore();

        // When
        Assert.assertEquals(expected, actualDealerHand);
    }

    @Test
    public void dealCardsTest() {
        // Given
        Player getPlayer = blackJack.getPlayer();

        blackJack.dealCards();
        Integer expected = 2;

        // When
        Integer actualPlayerHand = blackJack.getPlayer().getPlayerHand().getAllOfPlayerCards().size();

        // Then
        Assert.assertEquals(expected, actualPlayerHand);
    }

    @Test
    public void dealCardsTest2() {
        // Given
        BlackJackPlayer dealer = new BlackJackPlayer(guest);
        blackJack.dealCards();
        Integer expected = 1;

        // When
        Integer actualDealerHand = blackJack.getDealer().getPlayerHand().getAllOfPlayerCards().size();

        // Then
        Assert.assertEquals(expected, actualDealerHand);
    }

    @Test
    public void continueGameInputTest() {
        // Given
        String input = "yes";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = new Casino (bytArrInpStr, System.out);
        String expected = "yes";

        // When
        String actual = blackJack.continueGameInput();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void continueGameInputTest2() {
        // Given
        String input = "no";
        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        bytArrOutStr = new ByteArrayOutputStream();
        testCasino = new Casino (bytArrInpStr, System.out);
        String expected = "no";

        // When
        String actual = blackJack.continueGameInput();

        // Then
        Assert.assertEquals(expected, actual);
    }

//    @Test
//    public void continueGameInputTest3() {
//        // Given
//        String input = "no";
//        bytArrInpStr = new ByteArrayInputStream(input.getBytes());
//        bytArrOutStr = new ByteArrayOutputStream();
//        testCasino = new Casino (bytArrInpStr, bytArrOutStr = new ByteArrayOutputStream());
//        String expected = "no";
//
//        // When
//        String actual = blackJack.continueGameInput();
//
//        // Then
//        Assert.assertEquals(expected, actual);
//    }

    @Test
    public void endGameTest() {
        // Given
        Boolean actual = blackJack.endGame("no");
        // Then
        Assert.assertFalse(actual);
    }

    @Test
    public void endGameTest2() {
        // Given
        Boolean actual = blackJack.endGame("yes");
        // Then
        Assert.assertTrue(actual);
    }

    @Test
    public void playFullGameTest1() {
        // Given
        String input = "15\ns\nno\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        Casino testCasino = new Casino(bytArrInpStr, System.out);
        Double startingBalance = 1000.0;
        Guest testGuest = new Guest("", new GuestAccount("", 0, startingBalance));
        BlackJack testBlackJack = new BlackJack(testGuest, new CardDeck()); // This deck has not been shuffled

        Double expectedFinalBalance = 1000.00;

        // When
        testBlackJack.playFullGame();
        Double actualFinalBalance = testGuest.getAccountBalance();

        // Then
        Assert.assertEquals(expectedFinalBalance, actualFinalBalance);
    }

    @Test
    public void playFullGameTest2() {
        // Given
        String input = "0\n15\nh\nno\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        Casino testCasino = new Casino(bytArrInpStr, System.out);
        Double startingBalance = 1000.0;
        Guest testGuest = new Guest("", new GuestAccount("", 0, startingBalance));
        BlackJack testBlackJack = new BlackJack(testGuest, new CardDeck()); // This deck has not been shuffled

        Double expectedFinalBalance = 985.00;

        // When
        testBlackJack.playFullGame();
        Double actualFinalBalance = testGuest.getAccountBalance();

        // Then
        Assert.assertEquals(expectedFinalBalance, actualFinalBalance);
    }

    @Test
    public void playFullGameTest3() {
        // Given
        String input = "0";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        Casino testCasino = new Casino(bytArrInpStr, System.out);
        Double startingBalance = 0.0;
        Guest testGuest = new Guest("", new GuestAccount("", 0, startingBalance));
        BlackJack testBlackJack = new BlackJack(testGuest, new CardDeck()); // This deck has not been shuffled

        Double expectedFinalBalance = 0.00;

        // When
        testBlackJack.playFullGame();
        Double actualFinalBalance = testGuest.getAccountBalance();

        // Then
        Assert.assertEquals(expectedFinalBalance, actualFinalBalance);
    }


}
