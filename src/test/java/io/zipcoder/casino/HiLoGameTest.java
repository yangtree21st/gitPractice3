package io.zipcoder.casino;

import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import org.junit.Assert;
import org.junit.Test;

public class HiLoGameTest {


    @Test
    public void getDeckTest(){
        //Given
        HiLo hiloGame = new HiLo();
        CardDeck expected = new CardDeck();


        //When
        CardDeck actual= hiloGame.getDeck();

        //Then

        Assert.assertEquals(expected,actual);
    }
}
