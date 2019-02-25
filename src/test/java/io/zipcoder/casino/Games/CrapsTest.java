package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.GuestAccount;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CrapsTest {

    @Test
    public void playFullGame() {
    }


    @Test
    public void choiceToPlay() {
    }

    @Test
    public void getTypeOfBetFromPlayer() {
    }

    @Test
    public void takeBetFromPlayer() {
    }

    @Test
    public void playCraps() {
    }

    @Test
    public void payOut() {
    }

    private Casino getCasinoWithBufferedOutput(ByteArrayOutputStream bytArrOutStr) {
        return new Casino(System.in, new PrintStream(bytArrOutStr));
    }

    private Casino getCasinoWithBufferedInputAndOutput(ByteArrayInputStream bytArrInpStr, ByteArrayOutputStream bytArrOutStr) {
        return new Casino(bytArrInpStr, new PrintStream(bytArrOutStr));
    }

    private Craps getNewCraps() {
        return this.getNewCraps(0.0);
    }

    private Craps getNewCraps(Double startingBalance) {
        return new Craps(new Guest("", new GuestAccount("", 1, startingBalance)));
    }
}