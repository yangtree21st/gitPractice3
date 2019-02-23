package io.zipcoder.casino.Models;

import org.junit.Assert;
import org.junit.Test;

import static io.zipcoder.casino.Models.Card.Suit.HEARTS;
import static io.zipcoder.casino.Models.Card.Suit.SPADES;
import static io.zipcoder.casino.Models.Card.Suit.DIAMONDS;
import static io.zipcoder.casino.Models.Card.Suit.CLUBS;

import static io.zipcoder.casino.Models.Card.Rank.ACE;
import static io.zipcoder.casino.Models.Card.Rank.TWO;
import static io.zipcoder.casino.Models.Card.Rank.THREE;
import static io.zipcoder.casino.Models.Card.Rank.FOUR;
import static io.zipcoder.casino.Models.Card.Rank.FIVE;
import static io.zipcoder.casino.Models.Card.Rank.SIX;
import static io.zipcoder.casino.Models.Card.Rank.SEVEN;
import static io.zipcoder.casino.Models.Card.Rank.EIGHT;
import static io.zipcoder.casino.Models.Card.Rank.NINE;
import static io.zipcoder.casino.Models.Card.Rank.TEN;
import static io.zipcoder.casino.Models.Card.Rank.JACK;
import static io.zipcoder.casino.Models.Card.Rank.QUEEN;
import static io.zipcoder.casino.Models.Card.Rank.KING;


import static org.junit.Assert.*;

public class CardTest {




    @Test
    public void getValue() {
        Card card = new Card(HEARTS,FIVE);
        Card.Rank actual = card.getValue();
        Assert.assertEquals(FIVE, actual);
    }



    @Test
    public void setValue() {
        Card card = new Card();
        Card.Rank actual = card.setValue(TWO);
        Assert.assertEquals(TWO, actual);
    }

    @Test
    public void setCardSuit() {
        Card card = new Card();
        Card.Suit actual = card.setCardSuit(DIAMONDS);
        Assert.assertEquals(DIAMONDS, actual);

    }

    @Test
    public void getCardSuit() {
        Card card = new Card(HEARTS,FIVE);
        Card.Suit actual = card.getCardSuit();
        Assert.assertEquals(HEARTS, actual);
    }


    @Test
    public void toStringCard() {
        Card card = new Card(SPADES,SEVEN);

        String expert = String.format("\n %10s \n %10s","SPADES","SEVEN");

        String actual = card.toStringCard();
        Assert.assertEquals(expert, actual);
    }
}