package io.zipcoder.casino;

import io.zipcoder.casino.Models.Die;
import org.junit.Assert;
import org.junit.Test;

public class DieTest {

    @Test
    public void getDiceRollTest() {
        // Given
        Die die = new Die();

        // When
        Integer result = die.getDiceRoll();

        // Then
        Assert.assertTrue(result >= 1 && result <= 6);
    }
}
