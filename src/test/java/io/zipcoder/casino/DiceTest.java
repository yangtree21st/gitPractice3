package io.zipcoder.casino;

import io.zipcoder.casino.Models.Dice;
import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void getDiceRollTest() {
        // Given
        Dice dice = new Dice();

        // When
        Integer result = dice.getDiceRoll();

        // Then
        Assert.assertTrue(result>=1 && result<=6);
    }
}
