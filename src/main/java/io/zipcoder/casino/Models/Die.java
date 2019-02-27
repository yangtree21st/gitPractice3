package io.zipcoder.casino.Models;

import java.util.Random;

/**
 * The Die program simply simulates a single die being rolled.
 *
 * @version 2019-02-22
 */
public class Die {
    private Random random;

    /**
     * Standard constructor for die. Creates a die with a random seed.
     */
    public Die() {
        this.random = new Random();
    }

    /**
     * Constructor for testing purposes only! Allows the creation of a non-random die for predictable results.
     * @param testRandom a supplied random, that will be non-random.
     */
    public Die(Random testRandom) {
        this.random = testRandom;
    }

    /**
     * This method gets the result of a toss with a six-sided die
     *
     * @return the random result of the six-sided die toss
     */
    public Integer getSingleDieRoll() {
        return getSingleDieRoll(6);
    }

    /**
     * This method gets the result of a toss of a die with a number of faces equal to numOfFaces
     *
     * @param numOfFaces the number of faces of the die to be rolled
     * @return the random result of the die toss
     */
    public Integer getSingleDieRoll(int numOfFaces) {
        return random.nextInt(numOfFaces) + 1;
    }
}
