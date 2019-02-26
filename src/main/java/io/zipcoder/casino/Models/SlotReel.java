package io.zipcoder.casino.Models;

import java.util.Random;

public class SlotReel {
    static final String[][] numbers = {
            {"  ______  ",
                    " /      \\ ",
                    "/$$$$$$  |",
                    "$$$  \\$$ |",
                    "$$$$  $$ |",
                    "$$ $$ $$ |",
                    "$$ \\$$$$ |",
                    "$$   $$$/ ",
                    " $$$$$$/  "},

            {"   __   ",
                    " _/  |  ",
                    "/ $$ |  ",
                    "$$$$ |  ",
                    "  $$ |  ",
                    "  $$ |  ",
                    " _$$ |_ ",
                    "/ $$   |",
                    "$$$$$$/ "},

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

    Random randomReel;

    /**
     * Default constructor, simply initializes the random.
     */
    public SlotReel() {
        randomReel = new Random();
    }

    /**
     * For testing purposes, to use a rigged slotReel to check the different scenarios.
     *
     * @param testRandom the Random provided with a known seed.
     */
    SlotReel(Random testRandom) {
        randomReel = testRandom;
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
     * @return
     */
    public String getStringRepresentationOfSlotReelResults(int[] slotReelResults) {
        StringBuilder representation = new StringBuilder();


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < slotReelResults.length; j++) {
                representation.append(numbers[slotReelResults[j]][i]).append(" | ");
            }
            representation.append('\n');
        }


        return representation.toString();
    }

    public static void main(String[] args) {
        SlotReel testReel = new SlotReel();
        int[] asdf = {5, 8, 2};
        System.out.println(testReel.getStringRepresentationOfSlotReelResults(asdf));
    }

}
