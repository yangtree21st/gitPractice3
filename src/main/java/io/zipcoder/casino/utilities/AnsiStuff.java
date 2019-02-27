package io.zipcoder.casino.utilities;

import java.util.Random;

public class AnsiStuff {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String[] ANSI_COLOR_ARRAY = {ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN};

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private Random random;

    /**
     * Default constructor, sets the random to a new random.
     */
    public AnsiStuff() {
        this.random = new Random();
    }

    /**
     * For testing purposes only
     *
     * @param testRandom seeded Random for predictable results.
     */
    public AnsiStuff(Random testRandom) {
        this.random = testRandom;
    }

    /**
     * Method for getting a random color.
     *
     * @return random Ansi color
     */
    public String getRandomAnsiColor() {
        return ANSI_COLOR_ARRAY[random.nextInt(ANSI_COLOR_ARRAY.length)];
    }


}