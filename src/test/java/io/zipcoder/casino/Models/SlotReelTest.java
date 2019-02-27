package io.zipcoder.casino.Models;

import com.sun.source.tree.AssertTree;
import io.zipcoder.casino.Casino;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class SlotReelTest {
    Random testRandom1 = new Random(1);
    Random testRandom2 = new Random(2);


    @Test
    public void constructorTest1() {
        // Given
        SlotReel testSlotReel = new SlotReel();

        // Then
        Assert.assertNotNull(testSlotReel.getRandomReel());
    }

    @Test
    public void constructorTest2() {
        // Given
        SlotReel testSlotReel = new SlotReel();

        // Then
        Assert.assertNotNull(testSlotReel.getAnsiStuff());
    }

    @Test
    public void getSlotReelResultsAndDisplayRepresentationTest1() {
        // Given
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedOutput(bytArrOutStr);
        SlotReel testSlotReel = new SlotReel(testRandom1);

        int numOfReels = 3;
        int[] expectedSlotReelResults = {5, 8, 7};

        // When
        int[] actualSlotReelResults = testSlotReel.getSlotReelResultsAndDisplayRepresentation(3);

        // Then
        Assert.assertArrayEquals(expectedSlotReelResults, actualSlotReelResults);
    }

    @Test
    public void getSlotReelResultsAndDisplayRepresentationTest2() {
        // Given
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedOutput(bytArrOutStr);
        SlotReel testSlotReel = new SlotReel(testRandom2);

        int numOfReels = 3;
        int[] expectedSlotReelResults = {8, 2, 0};

        // When
        int[] actualSlotReelResults = testSlotReel.getSlotReelResultsAndDisplayRepresentation(3);

        // Then
        Assert.assertArrayEquals(expectedSlotReelResults, actualSlotReelResults);
    }

    @Test
    public void getSlotReelResultsTest1() {
        // Given
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedOutput(bytArrOutStr);
        SlotReel testSlotReel = new SlotReel(testRandom1);

        int numOfReels = 3;

        int[] expectedSlotReelResults = {5, 8, 7};

        // When
        int[] actualSlotReelResults = testSlotReel.getSlotReelResults(3);

        // Then
        Assert.assertArrayEquals(expectedSlotReelResults, actualSlotReelResults);
    }

    @Test
    public void getSlotReelResultsTest2() {
        // Given
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedOutput(bytArrOutStr);
        SlotReel testSlotReel = new SlotReel(testRandom2);

        int numOfReels = 3;

        int[] expectedSlotReelResults = {8, 2, 0};

        // When
        int[] actualSlotReelResults = testSlotReel.getSlotReelResults(3);

        // Then
        Assert.assertArrayEquals(expectedSlotReelResults, actualSlotReelResults);
    }

    @Test
    public void getStringRepresentationOfSlotReelResultsTest1() {
        // Given
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedOutput(bytArrOutStr);
        SlotReel testSlotReel = new SlotReel(testRandom1);

        int[] slotReelResults = {5, 8, 7};

        String expectedSlotReelRepresentation = "\u001B[34m|                __    __                ||                ________                ||                 ______                 |\n" +
                "|               /  |  /  |               ||               /        |               ||                /      \\                |\n" +
                "|               $$ |  $$ |               ||               $$$$$$$$/                ||               /$$$$$$  |               |\n" +
                "|               $$ |__$$ |               ||                   /$$/                 ||               $$ \\__$$/                |\n" +
                "|               $$    $$ |               ||                  /$$/                  ||               $$      \\                |\n" +
                "|               $$$$$$$$ |               ||                 /$$/                   ||               $$$$$$$  |               |\n" +
                "|                     $$ |               ||                /$$/                    ||               $$ \\__$$ |               |\n" +
                "|                     $$ |               ||               /$$/                     ||               $$    $$/                |\n" +
                "|                     $$/                ||               $$/                      ||                $$$$$$/                 |\n" +
                "\u001B[0m\u001B[34m|                _______                 ||                 ______                 ||                ________                |\n" +
                "|               /       |                ||                /      \\                ||               /        |               |\n" +
                "|               $$$$$$$/                 ||               /$$$$$$  |               ||               $$$$$$$$/                |\n" +
                "|               $$ |____                 ||               $$ \\__$$ |               ||                   /$$/                 |\n" +
                "|               $$      \\                ||               $$    $$<                ||                  /$$/                  |\n" +
                "|               $$$$$$$  |               ||                $$$$$$  |               ||                 /$$/                   |\n" +
                "|               /  \\__$$ |               ||               $$ \\__$$ |               ||                /$$/                    |\n" +
                "|               $$    $$/                ||               $$    $$/                ||               /$$/                     |\n" +
                "|                $$$$$$/                 ||                $$$$$$/                 ||               $$/                      |\n" +
                "\u001B[0m\u001B[31m|                 ______                 ||                 ______                 ||                 ______                 |\n" +
                "|                /      \\                ||                /      \\                ||                /      \\                |\n" +
                "|               /$$$$$$  |               ||               /$$$$$$  |               ||               /$$$$$$  |               |\n" +
                "|               $$ \\__$$/                ||               $$ \\__$$ |               ||               $$ \\__$$ |               |\n" +
                "|               $$      \\                ||               $$    $$ |               ||               $$    $$<                |\n" +
                "|               $$$$$$$  |               ||                $$$$$$$ |               ||                $$$$$$  |               |\n" +
                "|               $$ \\__$$ |               ||               /  \\__$$ |               ||               $$ \\__$$ |               |\n" +
                "|               $$    $$/                ||               $$    $$/                ||               $$    $$/                |\n" +
                "|                $$$$$$/                 ||                $$$$$$/                 ||                $$$$$$/                 |\n" +
                "\u001B[0m";

        // When
        String actualSlotReelRepresentation = testSlotReel.getStringRepresentationOfSlotReelResults(slotReelResults);

        // Then
        Assert.assertEquals(expectedSlotReelRepresentation, actualSlotReelRepresentation);
    }

    @Test
    public void getStringRepresentationOfSlotReelResultsTest3() {
        // Given
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedOutput(bytArrOutStr);
        SlotReel testSlotReel = new SlotReel(testRandom2);

        int[] slotReelResults = {5, 8, 7};

        String expectedSlotReelRepresentation = "\u001B[35m|                __    __                ||                ________                ||                 ______                 |\n" +
                "|               /  |  /  |               ||               /        |               ||                /      \\                |\n" +
                "|               $$ |  $$ |               ||               $$$$$$$$/                ||               /$$$$$$  |               |\n" +
                "|               $$ |__$$ |               ||                   /$$/                 ||               $$ \\__$$/                |\n" +
                "|               $$    $$ |               ||                  /$$/                  ||               $$      \\                |\n" +
                "|               $$$$$$$$ |               ||                 /$$/                   ||               $$$$$$$  |               |\n" +
                "|                     $$ |               ||                /$$/                    ||               $$ \\__$$ |               |\n" +
                "|                     $$ |               ||               /$$/                     ||               $$    $$/                |\n" +
                "|                     $$/                ||               $$/                      ||                $$$$$$/                 |\n" +
                "\u001B[0m\u001B[31m|                _______                 ||                 ______                 ||                ________                |\n" +
                "|               /       |                ||                /      \\                ||               /        |               |\n" +
                "|               $$$$$$$/                 ||               /$$$$$$  |               ||               $$$$$$$$/                |\n" +
                "|               $$ |____                 ||               $$ \\__$$ |               ||                   /$$/                 |\n" +
                "|               $$      \\                ||               $$    $$<                ||                  /$$/                  |\n" +
                "|               $$$$$$$  |               ||                $$$$$$  |               ||                 /$$/                   |\n" +
                "|               /  \\__$$ |               ||               $$ \\__$$ |               ||                /$$/                    |\n" +
                "|               $$    $$/                ||               $$    $$/                ||               /$$/                     |\n" +
                "|                $$$$$$/                 ||                $$$$$$/                 ||               $$/                      |\n" +
                "\u001B[0m\u001B[34m|                 ______                 ||                 ______                 ||                 ______                 |\n" +
                "|                /      \\                ||                /      \\                ||                /      \\                |\n" +
                "|               /$$$$$$  |               ||               /$$$$$$  |               ||               /$$$$$$  |               |\n" +
                "|               $$ \\__$$/                ||               $$ \\__$$ |               ||               $$ \\__$$ |               |\n" +
                "|               $$      \\                ||               $$    $$ |               ||               $$    $$<                |\n" +
                "|               $$$$$$$  |               ||                $$$$$$$ |               ||                $$$$$$  |               |\n" +
                "|               $$ \\__$$ |               ||               /  \\__$$ |               ||               $$ \\__$$ |               |\n" +
                "|               $$    $$/                ||               $$    $$/                ||               $$    $$/                |\n" +
                "|                $$$$$$/                 ||                $$$$$$/                 ||                $$$$$$/                 |\n" +
                "\u001B[0m";

        // When
        String actualSlotReelRepresentation = testSlotReel.getStringRepresentationOfSlotReelResults(slotReelResults);

        // Then
        Assert.assertEquals(expectedSlotReelRepresentation, actualSlotReelRepresentation);
    }

    private Casino getCasinoWithBufferedOutput(ByteArrayOutputStream bytArrOutStr) {
        return new Casino(System.in, new PrintStream(bytArrOutStr));
    }

}
