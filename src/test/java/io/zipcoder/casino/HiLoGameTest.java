package io.zipcoder.casino;

import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.HiLowPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HiLoGameTest {

    @Before
    public void setUp(){
        HiLo hiloGame = new HiLo();
    }



    @Test
    public void dealTest(){
        //Given
        HiLo hiloGame = new HiLo();


        //When
        Card actual= hiloGame.deal();

        //Then

        Assert.assertNotNull(actual);

    }


    @Test
    public void isLessTest(){
        //Given
        HiLo hiloGame = new HiLo();
        Card firstCard = new Card(Card.Suit.HEARTS, Card.Rank.THREE);
        Card secondCard = new Card(Card.Suit.HEARTS, Card.Rank.EIGHT);

        //When
        Boolean actual = hiloGame.isLess(firstCard,secondCard);

        //Then
        Assert.assertFalse(actual);

    }

    @Test
    public void isMoreTest(){
        //Given
        HiLo hiloGame = new HiLo();
        Card firstCard = new Card(Card.Suit.HEARTS, Card.Rank.THREE);
        Card secondCard = new Card(Card.Suit.HEARTS,Card.Rank.JACK);

        //When
        Boolean actual = hiloGame.isMore(firstCard,secondCard);

        //Then
        Assert.assertTrue(actual);

    }
//    @Test
//    public void isMoreTest1(){
//        //Given
//        HiLo hiloGame = new HiLo();
//
//        Card firstCard = hiloGame.deal();
//        Card secondCard = hiloGame.deal();
//
//
//        //When
//        Boolean actual = hiloGame.isMore(firstCard,secondCard);
//
//        //Then
//        Assert.assertTrue(actual);

    @Test
    public void checkPlayersBalanceTest(){
        //Given
        Double expected = 20.00;
        GuestAccount guestAccount = new GuestAccount(null,null,expected);
        Guest guest = new Guest(null,guestAccount);
        HiLowPlayer hiLowPlayer = new HiLowPlayer(guest);
        HiLo hiloGame = new HiLo(guest);

        //When
        Double actual = hiloGame.checkPlayersBalance(hiLowPlayer);


        //Then
        Assert.assertEquals(expected,actual);


    }
    @Test
    public void winningTest(){
        //Given
        Guest guest = new Guest(null,null);
        HiLowPlayer hiLowPlayer = new HiLowPlayer(guest);
        HiLo hiloGame = new HiLo(guest);


        //When
        hiloGame.winning();

        //Then
    }





}
