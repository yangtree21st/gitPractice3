package io.zipcoder.casino;

import io.zipcoder.casino.Models.GuestAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class CasinoTest {

    @Test
    public void createNewCasinoTestWithParametersTest() {
        //given
        GuestAccountDataBase expected = new GuestAccountDataBase();
        Casino newCasino = new Casino(expected, System.in, System.out);
        //when
        GuestAccountDataBase actual = newCasino.getGuestAccountDatabase();
        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createNewCasinoTestWithNoParametersTest() {
        //given
        Casino newCasino = new Casino();
        //when
        GuestAccountDataBase gab = newCasino.getGuestAccountDatabase();
        //then
        Assert.assertNotEquals(null, gab);
    }

    @Test
    public void setGuestInCasinoTest() {
        //given
        Casino newCasino = new Casino();
        String name = "Marci Brahma";
        Double startingBalance = 500.00;
        GuestAccount newGuestAcc = new GuestAccount(name, 23, startingBalance);

        newCasino.setGuest(name, newGuestAcc);
        //when
        Guest actual = newCasino.getGuest();
        //then
        Assert.assertEquals("Marci Brahma", actual.getName());

    }

    @Test
    public void getGuestAccountTest() {
        //given
        Casino newCasino = new Casino();
        String name = "Marci Brahma";
        Double startingBalance = 500.00;
        GuestAccount newGuestAcc = new GuestAccount(name, 23, startingBalance);

        newCasino.setGuest(name, newGuestAcc);
        //when
        Guest actual = newCasino.getGuest();
        //then
        Assert.assertEquals("Marci Brahma", actual.getName());
    }


    @Test
    public void getGuestNameTest() {
        // Given
        String input = "Leon\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        Casino testCasino = new Casino(bytArrInpStr, System.out);

        String expected = "Leon";

        // When
        String actual = testCasino.getGuestName();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStartingBalanceTest() {
        // Given
        String input = "100\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        Casino testCasino = new Casino(bytArrInpStr, System.out);

        Double expected = 100.0;

        // When
        Double actual = testCasino.getStartingBalance();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createGuestAccountTest() {
        // Given
        String name = "asdf";
        Double balance = 100.0;
        int id = 1;
        Casino testCasino = new Casino();

        GuestAccount expectedGuestAccount = new GuestAccount(name, id, balance);


        // When
        GuestAccount actuaGuestAccount = testCasino.createGuestAccount(name, balance);

        // Then
        Assert.assertEquals(expectedGuestAccount.toString(), actuaGuestAccount.toString());
    }

    @Test
    public void initializeAccountCreationTest() {
        // Given
        String name = "asdf";
        Double balance = 100.0;
        int id = 1;
        Casino testCasino = new Casino();

        String expected = "This is your new account.\n" +
                "Name: asdf, ID: 1, Balance: $100.00\n";


        // When
        String actual = testCasino.initializeAccountCreation(name, balance);


        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAdditionalFundsTest(){
        //given
        String name = "Marci";
        GuestAccount expectedGuestAccount = new GuestAccount(name, 1, 100.0);

        Double input = 50.0;
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.toString().getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr,bytArrOutStr);
        testCasino.setGuest(name,expectedGuestAccount);

        String expected = "Sorry, please enter a positive cash amount:\n";
        //when
        testCasino.addAdditionalFunds(-50.0);
        String actual = bytArrOutStr.toString();
        //then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void addAdditionalFundsTestWithCorrectInput(){
        //given
        String name = "Marci";
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = new Casino(System.in, new PrintStream(bytArrOutStr));
        testCasino.getGuestAccountDatabase().addAccount(name,100.0);
        testCasino.setGuest(name, testCasino.getGuestAccountDatabase().getAccount(1));

        String expected = "This is your current account status:\n" +
        "Name: Marci, ID: 1, Balance: $150.00\n";
        //when
        testCasino.addAdditionalFunds(50.0);
        String actual = testCasino.accountToString(testCasino.getGuest());
        //then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void collectNewAccountInfoAndInitializeTest(){
        //given
        String input = "Marci\n100.00";

        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr,bytArrOutStr);


        String expected = "\nPlease provide your name to start a new guest account:\n" +
        "How much money for gambling would you like to deposit into your account?\n";
        //when
        testCasino.collectNewAccountInfoAndInitialize();
        String actual = bytArrOutStr.toString();
        //then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void collectNewAccountInfoAndInitializeGetReturnStringTest(){
        //given
        String input = "Marci\n100.00";

        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = getCasinoWithBufferedInputAndOutput(bytArrInpStr,bytArrOutStr);


        String expected = "\nNew Account Details:\n" +
                "Name: Marci, ID: 1, Balance: $100.00\n";
        //when

        String actual = testCasino.collectNewAccountInfoAndInitialize();
        //then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void accountToStringTest() {
        // Given
        String name = "asdf";
        Double balance = 100.0;
        int id = 1;
        GuestAccount testGuestAccount = new GuestAccount(name, id, balance);
        Guest testGuest = new Guest(name, testGuestAccount);
        Casino testCasino = new Casino();
        testCasino.getGuestAccountDatabase().addAccount(name, balance);

        String expected = "This is your current account status:\n" +
                "Name: asdf, ID: 1, Balance: $100.00\n";

        // When
        String actual = testCasino.accountToString(testGuest);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void continueOrExitTest() {
        // Given
        String input = "1\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        Casino testCasino = new Casino(bytArrInpStr, System.out);

        // When
        boolean actual = testCasino.continueOrExit();

        // Then
        Assert.assertTrue(actual);
    }

    @Test
    public void accessAccountTest() {
        // Given
        String name = "asdf";
        Double balance = 100.0;
        int id = 1;
        String input = "2\n";
        ByteArrayInputStream bytArrInpStr = new ByteArrayInputStream(input.getBytes());
        Casino testCasino = new Casino(bytArrInpStr, System.out);
        GuestAccount newGuestAccount = testCasino.createGuestAccount(name, balance);
        testCasino.setGuest(name, newGuestAccount);

        Double expectedEndingBalance = 0.0;

        // When
        testCasino.accessAccount();

        Double actualEndingBalance = testCasino.getGuest().getAccountBalance();

        // Then
        Assert.assertEquals(expectedEndingBalance, actualEndingBalance);
    }

    @Test
    public void welcomeHeaderTest(){
        //given
        Casino newCasino = new Casino();
        String expected = "\u001B[35m\u001B[0m\n\n" + "Welcome to the Casino lobby!\n" +
                "A friendly attendant asks you for your name to start a new account at the Casino Royale.";

        //when
        String actual = newCasino.welcomeHeader();

        //then
        Assert.assertEquals(expected,actual);
    }

    private Casino getCasinoWithBufferedInputAndOutput(ByteArrayInputStream bytArrInpStr, ByteArrayOutputStream bytArrOutStr) {
        return new Casino(bytArrInpStr, new PrintStream(bytArrOutStr));
    }

}
