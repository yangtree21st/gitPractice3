package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.GoFishPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class GoFishTest {

    // Before
    GuestAccount highRoller = new GuestAccount("Julian", 777, 1000000.00);
    GuestAccount guestAccount2 = new GuestAccount("guest2", 2, 1.00);
    Guest guest = new Guest("Julian", highRoller);
    Guest guest2 = new Guest("guest2", guestAccount2);
    GoFishPlayer playerOne = new GoFishPlayer(guest);
    GoFishPlayer playerTwo = new GoFishPlayer(guest2);
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
    public void setUpTest1() {
        //Given
        game.getPlayer(guest);

        //When
        game.setUp();
        Boolean actual = game.getPlayerHand().getAllOfPlayerCards().size() > 0;

        //Then
        Assert.assertTrue(actual);
    }

    @Test
    public void setUpTest2() {
        //Given
        game.getOpponent();


        //When
        game.setUp();
        Boolean actual = game.getOpponentHand().getAllOfPlayerCards().size() > 0;

        //Then
        Assert.assertTrue(actual);
    }


    @Test
    public void takeTurnTest1() {
        //Given
        String input = "0\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());

        Casino testCasino = new Casino(bytArrInpStr, System.out);

        GoFish testGoFish = new GoFish(guest);

        Hand playerHand = new Hand();
        playerHand.addCard(testCard2);
        playerHand.addCard(testCard3);
        playerHand.addCard(testCard4);
        playerHand.addCard(testCard5);

        Hand opponentHand = new Hand();
        opponentHand.addCard(testCard2);
        opponentHand.addCard(testCard3);
        opponentHand.addCard(testCard4);
        opponentHand.addCard(testCard5);

        testGoFish.takeTurn(playerHand, opponentHand);

        Assert.assertFalse(opponentHand.getAllOfPlayerCards().contains(testCard2));
    }


    @Test
    public void takeTurnTest2() {
        //Given
        String input = "0\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();

        Casino testCasino = new Casino(bytArrInpStr, new PrintStream(bytArrOutStr));

        GoFish testGoFish = new GoFish(guest);

        Hand playerHand = new Hand();
        playerHand.addCard(testCard2);
        playerHand.addCard(testCard3);
        playerHand.addCard(testCard4);
        playerHand.addCard(testCard5);

        Hand opponentHand = new Hand();
        opponentHand.addCard(testCard2);
        opponentHand.addCard(testCard3);
        opponentHand.addCard(testCard4);
        opponentHand.addCard(testCard5);

        String expectedOutput = "\n" +
                "Take Your Turn\n" +
                "\"Pick A Card To Fish For [Enter Index Number Below Card Choice]: \"\n" +
                "\n" +
                "YOU GOT YOUR OPPONENT'S\n" +
                "  \u001B[30m--------------\u001B[0m\n" +
                "\u001B[30m|2           |\u001B[0m\n" +
                "\u001B[30m|♠           |\u001B[0m\n" +
                "\u001B[30m|            |\u001B[0m\n" +
                "\u001B[30m|     2♠     |\u001B[0m\n" +
                "\u001B[30m|            |\u001B[0m\n" +
                "\u001B[30m|           ♠|\u001B[0m\n" +
                "\u001B[30m|           2|\u001B[0m\n" +
                "\u001B[30m--------------\u001B[0m\n" +
                "\n";

        testGoFish.takeTurn(playerHand, opponentHand);

        String actualOutput = bytArrOutStr.toString();

        Assert.assertEquals(expectedOutput, actualOutput);
    }








    @Test
    public void dealTest(){
        //Given
        game.getPlayerHand().addCard(game.deal());
        Integer expected = 2;

        //When
        game.getPlayerHand().addCard(game.deal());
        Integer actual = game.getPlayerHand().getAllOfPlayerCards().size();

        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void dealTest2(){
        //Given
        Card expected = game.getDeck().peekAtTopCard();


        //When
       Card actual = game.deal();


        //Then
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void shuffleDeckTest(){
        //Given
        Card peek = game.getDeck().peekAtTopCard();

        //When
        game.getDeck().shuffleDeck();
        Card newPeek = game.getDeck().peekAtTopCard();

        //Then
        Assert.assertNotEquals(peek, newPeek);

    }

    @Test
    public void containsPairsTest() {
        //Given
        game.getPlayerHand().addCard(testCard1);
        game.getPlayerHand().addCard(testCard2);
        game.getPlayerHand().addCard(testCard3);
        game.getPlayerHand().addCard(testCard4);
        game.getPlayerHand().addCard(testCard5);
        Integer expected = 1;


        //When
        Integer actual = game.containsPairs(game.getPlayerHand());

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void containsPairsTest2() {
        //Given
        game.getPlayerHand().addCard(testCard2);
        game.getPlayerHand().addCard(testCard3);
        game.getPlayerHand().addCard(testCard4);
        game.getPlayerHand().addCard(testCard5);
        Integer expected = 0;


        //When
        Integer actual = game.containsPairs(game.getPlayerHand());

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removePairsTest1() {
        //Given
        game.getPlayerHand().addCard(testCard1);
        game.getPlayerHand().addCard(testCard2);
        game.getPlayerHand().addCard(testCard3);
        game.getPlayerHand().addCard(testCard4);
        game.getPlayerHand().addCard(testCard5);
        Integer expected = 3;
        //When
        game.containsPairs(game.getPlayerHand());

        Integer actual = game.getPlayerHand().getAllOfPlayerCards().size();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removePairsTest2() {
        //Given
        game.getPlayerHand().addCard(testCard2);
        game.getPlayerHand().addCard(testCard3);
        game.getPlayerHand().addCard(testCard4);
        game.getPlayerHand().addCard(testCard5);
        Integer expected = 4;

        //When
        game.containsPairs(game.getPlayerHand());
        Integer actual = game.getPlayerHand().getAllOfPlayerCards().size();

        //Then
        Assert.assertEquals(expected, actual);

    }



    @Test
    public void playerTurnTest() {

        //Given
        game.getPlayerHand().addCard(testCard2);
        game.getPlayerHand().addCard(testCard3);
        game.getPlayerHand().addCard(testCard4);
        game.getPlayerHand().addCard(testCard5);
        player2Hand.addCard(testCard2);
        player2Hand.addCard(testCard6);
        player2Hand.addCard(testCard8);


    }




}
