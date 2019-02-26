package io.zipcoder.casino.Models;

import io.zipcoder.casino.Casino;

import java.util.Random;

public class SlotReel {
    public static final String[][] fancyAsciiNumbers = {
            {"  ______  ",
                    " /      \\ ",
                    "/$$$$$$  |",
                    "$$$  \\$$ |",
                    "$$$$  $$ |",
                    "$$ $$ $$ |",
                    "$$ \\$$$$ |",
                    "$$   $$$/ ",
                    " $$$$$$/  "},

            {"    __    ",
                    "  _/  |   ",
                    " / $$ |   ",
                    " $$$$ |   ",
                    "   $$ |   ",
                    "   $$ |   ",
                    "  _$$ |_  ",
                    " / $$   | ",
                    " $$$$$$/  "},

            {"  ______  ",
                    " /      \\ ",
                    "/$$$$$$  |",
                    "$$____$$ |",
                    " /    $$/ ",
                    "/$$$$$$/  ",
                    "$$ |_____ ",
                    "$$       |",
                    "$$$$$$$$/ "},

            {"  ______  ",
                    " /      \\ ",
                    "/$$$$$$  |",
                    "$$ ___$$ |",
                    "  /   $$< ",
                    " _$$$$$  |",
                    "/  \\__$$ |",
                    "$$    $$/ ",
                    " $$$$$$/  "},

            {" __    __ ",
                    "/  |  /  |",
                    "$$ |  $$ |",
                    "$$ |__$$ |",
                    "$$    $$ |",
                    "$$$$$$$$ |",
                    "      $$ |",
                    "      $$ |",
                    "      $$/ "},

            {" _______  ",
                    "/       | ",
                    "$$$$$$$/  ",
                    "$$ |____  ",
                    "$$      \\ ",
                    "$$$$$$$  |",
                    "/  \\__$$ |",
                    "$$    $$/ ",
                    " $$$$$$/  "},

            {"  ______  ",
                    " /      \\ ",
                    "/$$$$$$  |",
                    "$$ \\__$$/ ",
                    "$$      \\ ",
                    "$$$$$$$  |",
                    "$$ \\__$$ |",
                    "$$    $$/ ",
                    " $$$$$$/  "},

            {" ________ ",
                    "/        |",
                    "$$$$$$$$/ ",
                    "    /$$/  ",
                    "   /$$/   ",
                    "  /$$/    ",
                    " /$$/     ",
                    "/$$/      ",
                    "$$/       "},

            {"  ______  ",
                    " /      \\ ",
                    "/$$$$$$  |",
                    "$$ \\__$$ |",
                    "$$    $$< ",
                    " $$$$$$  |",
                    "$$ \\__$$ |",
                    "$$    $$/ ",
                    " $$$$$$/  "},

            {"  ______  ",
                    " /      \\ ",
                    "/$$$$$$  |",
                    "$$ \\__$$ |",
                    "$$    $$ |",
                    " $$$$$$$ |",
                    "/  \\__$$ |",
                    "$$    $$/ ",
                    " $$$$$$/  "},

    };

    private Random randomReel;

    /**
     * Default constructor, simply initializes the random.
     */
    public SlotReel() {
        randomReel = new Random();
    }

    /**
     * For testing purposes, to use a rigged slotReel to check the different scenarios.
     * @param testRandom the Random provided with a known seed.
     */
    SlotReel(Random testRandom) {
        randomReel = testRandom;
    }

    /**
     * This method gets the results of the slot reel spins and dipslays the result. It also returns the slot reel
     * results as an int array.
     * @param numOfReels the number of reels for this slot machine.
     * @return an int array that contains the results of the middle line of the slot machine spin.
     */
    public int[] getSlotReelResultsAndDisplayRepresentation(int numOfReels) {
        int[] slotReelResults = getSlotReelResults(numOfReels);
        Casino.console.println(getStringRepresentationOfSlotReelResults(slotReelResults));
        return slotReelResults;
    }

    /**
     * This method returns an int array of the results of the slotReel spins
     *
     * @param numOfReels the number of reels in the slot machine
     * @return the int array containing the results of the slotReel spins
     */
    public int[] getSlotReelResults(int numOfReels) {
        int[] slotReelResults = new int[numOfReels];
        for (int i = 0; i < numOfReels; i++) {
            slotReelResults[i] = randomReel.nextInt(10);
        }
        return slotReelResults;
    }

    /**
     * This method creates a string representation of the results of slot reels.
     *
     * @return the string representation of slot machine results.
     */
    public String getStringRepresentationOfSlotReelResults(int[] slotReelResults) {
        StringBuilder representation = new StringBuilder();

        for (int k = -1; k <= 1; k++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < slotReelResults.length; j++) {
                    representation.append("|               ").append(fancyAsciiNumbers[Math.floorMod(slotReelResults[j] + k, 10)][i]).append("               |");
                }
                representation.append('\n');
            }
        }

        return representation.toString();
    }




//    public static void main(String[] args) {
//        SlotReel testReel = new SlotReel();
//        int[] results = {0, 4, 9};
//        System.out.println(testReel.getStringRepresentationOfSlotReelResults(results));
//    }

}
