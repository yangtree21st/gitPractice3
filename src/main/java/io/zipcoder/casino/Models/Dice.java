package io.zipcoder.casino.Models;

import java.util.Random;
/**
 * The Dice program simply simulates a pair of dice being rolled.
 *
 * @version 2019-02-22
 */
public class Dice {
    Random random;

    /**
     * This method is get the dice roll sum of the dice rolled.
     * @return Integer This returns the sum of both dice rolled.
     */
    public Integer getDiceRoll(){
        this.random = new Random();
        Integer dieOne = random.nextInt((6)+1);
        Integer dieTwo = random.nextInt((6)+1);
        Integer roll = dieOne + dieTwo;
        return roll;
    }


}
