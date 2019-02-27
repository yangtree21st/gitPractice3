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
        Card testCard = new Card(HEARTS, expectedRank);
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
        Card testCard = new Card(SPADES, SEVEN);

        String expert = String.format("\n %10s \n %10s", "SPADES", "SEVEN");

        String actual = testCard.toStringCard();
        Assert.assertEquals(expert, actual);
    }

    @Test
    public void toStringTest1() {
        // Given
        Card testCard = new Card(HEARTS, ACE);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|A           |\u001B[0m\n" +
                "\u001B[31m|♥           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     A♥     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♥|\u001B[0m\n" +
                "\u001B[31m|           A|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest2() {
        // Given
        Card testCard = new Card(SPADES, ACE);

        String expectedString = "\u001B[30m--------------\u001B[0m\n" +
                "\u001B[30m|A           |\u001B[0m\n" +
                "\u001B[30m|♠           |\u001B[0m\n" +
                "\u001B[30m|            |\u001B[0m\n" +
                "\u001B[30m|     A♠     |\u001B[0m\n" +
                "\u001B[30m|            |\u001B[0m\n" +
                "\u001B[30m|           ♠|\u001B[0m\n" +
                "\u001B[30m|           A|\u001B[0m\n" +
                "\u001B[30m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest3() {
        // Given
        Card testCard = new Card(CLUBS, ACE);

        String expectedString = "\u001B[30m--------------\u001B[0m\n" +
                "\u001B[30m|A           |\u001B[0m\n" +
                "\u001B[30m|♣           |\u001B[0m\n" +
                "\u001B[30m|            |\u001B[0m\n" +
                "\u001B[30m|     A♣     |\u001B[0m\n" +
                "\u001B[30m|            |\u001B[0m\n" +
                "\u001B[30m|           ♣|\u001B[0m\n" +
                "\u001B[30m|           A|\u001B[0m\n" +
                "\u001B[30m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest4() {
        // Given
        Card testCard = new Card(DIAMONDS, TWO);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|2           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     2♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           2|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest5() {
        // Given
        Card testCard = new Card(DIAMONDS, THREE);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|3           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     3♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           3|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest6() {
        // Given
        Card testCard = new Card(DIAMONDS, FOUR);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|4           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     4♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           4|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest7() {
        // Given
        Card testCard = new Card(DIAMONDS, FIVE);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|5           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     5♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           5|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest8() {
        // Given
        Card testCard = new Card(DIAMONDS, SIX);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|6           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     6♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           6|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest9() {
        // Given
        Card testCard = new Card(DIAMONDS, SEVEN);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|7           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     7♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           7|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest10() {
        // Given
        Card testCard = new Card(DIAMONDS, EIGHT);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|8           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     8♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           8|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest11() {
        // Given
        Card testCard = new Card(DIAMONDS, NINE);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|9           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     9♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           9|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest12() {
        // Given
        Card testCard = new Card(DIAMONDS, TEN);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|10          |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|    10♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|          10|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest13() {
        // Given
        Card testCard = new Card(DIAMONDS, JACK);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|J           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     J♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           J|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest14() {
        // Given
        Card testCard = new Card(DIAMONDS, QUEEN);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|Q           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     Q♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           Q|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringTest15() {
        // Given
        Card testCard = new Card(DIAMONDS, KING);

        String expectedString = "\u001B[31m--------------\u001B[0m\n" +
                "\u001B[31m|K           |\u001B[0m\n" +
                "\u001B[31m|♦           |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|     K♦     |\u001B[0m\n" +
                "\u001B[31m|            |\u001B[0m\n" +
                "\u001B[31m|           ♦|\u001B[0m\n" +
                "\u001B[31m|           K|\u001B[0m\n" +
                "\u001B[31m--------------\u001B[0m\n";

        // When
        String actualString = testCard.toString();

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

}