package io.zipcoder.casino;

import io.zipcoder.casino.Models.Dice;
import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void getDiceRollTest() {
        // Given
        Dice dice = new Dice();
        boolean expected = dice.getDiceRoll()>=1 || dice.getDiceRoll()<=12;
        // When
        Integer actual = dice.getDiceRoll();

        // Then
        Assert.assertTrue(actual>=2 && actual<=12);
    }
}
