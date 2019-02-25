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
    public void constructorTest1() {
        // Given
        Card.Rank expectedRank = FIVE;
        
        // When
        Card testCard = new Card(HEARTS,expectedRank);
        Card.Rank actual = testCard.getValue();
        
        // Then
        Assert.assertEquals(expectedRank, actual);
    }

    @Test
    public void constructorTest2() {
        // Given
        Card.Suit expectedSuit = HEARTS;

        // When
        Card testCard = new Card(expectedSuit, TEN);
        Card.Suit actualSuit = testCard.getCardSuit();

        // Then
        Assert.assertEquals(expectedSuit, actualSuit);
    }

    @Test
    public void setValue() {
        // Given
        Card testCard = new Card();
        Card.Rank expectedRank = TWO;
        
        // When
        testCard.setValue(expectedRank);
        Card.Rank actualRank = testCard.getValue();
        
        // Then
        Assert.assertEquals(expectedRank, actualRank);
    }

    @Test
    public void setCardSuit() {
        // Given
        Card testCard = new Card();
        Card.Suit expectedSuit = DIAMONDS;

        // When
        testCard.setCardSuit(expectedSuit);
        Card.Suit actualSuit = testCard.getCardSuit();

        // Then
        Assert.assertEquals(expectedSuit, actualSuit);
    }


    @Test
    public void toStringCard() {
        Card testCard = new Card(SPADES,SEVEN);

        String expert = String.format("\n %10s \n %10s","SPADES","SEVEN");

        String actual = testCard.toStringCard();
        Assert.assertEquals(expert, actual);
    }

    @Test
    public void toStringTest1() {
        // Given
        Card testCard = new Card(HEARTS, ACE);

        String expectedString = "--------------\n" +
                                "|A           |\n" +
                                "|♥           |\n" +
                                "|            |\n" +
                                "|     A♥     |\n" +
                                "|            |\n" +
                                "|           ♥|\n" +
                                "|           A|\n" +
                                "--------------\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest2() {
        // Given
        Card testCard = new Card(DIAMONDS, TEN);

        String expectedString = "--------------\n" +
                "|10          |\n" +
                "|♦           |\n" +
                "|            |\n" +
                "|    10♦     |\n" +
                "|            |\n" +
                "|           ♦|\n" +
                "|          10|\n" +
                "--------------\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }
}