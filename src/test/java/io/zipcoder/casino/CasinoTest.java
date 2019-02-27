package io.zipcoder.casino;

import io.zipcoder.casino.Models.GuestAccount;
import org.junit.Assert;
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
    public void welcomeHeaderTest() {
        // Given
        ByteArrayOutputStream bytArrOutStr = new ByteArrayOutputStream();
        Casino testCasino = new Casino(System.in, new PrintStream(bytArrOutStr));

        String expectedOutput = "\u001B[35m\n" +
                "\n" +
                "\n" +
                " /$$      /$$           /$$                                                     /$$                       /$$     /$$                \n" +
                "| $$  /$ | $$          | $$                                                    | $$                      | $$    | $$                \n" +
                "| $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$        /$$$$$$    /$$$$$$        /$$$$$$  | $$$$$$$   /$$$$$$ \n" +
                "| $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$      |_  $$_/   /$$__  $$      |_  $$_/  | $$__  $$ /$$__  $$\n" +
                "| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$        | $$    | $$  \\ $$        | $$    | $$  \\ $$| $$$$$$$$\n" +
                "| $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/        | $$ /$$| $$  | $$        | $$ /$$| $$  | $$| $$_____/\n" +
                "| $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$        |  $$$$/|  $$$$$$/        |  $$$$/| $$  | $$|  $$$$$$$\n" +
                "|__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/         \\___/   \\______/          \\___/  |__/  |__/ \\_______/\n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "        /$$$$$$                      /$$                           /$$$$$$$                                /$$                       \n" +
                "       /$$__  $$                    |__/                          | $$__  $$                              | $$                       \n" +
                "      | $$  \\__/  /$$$$$$   /$$$$$$$ /$$ /$$$$$$$   /$$$$$$       | $$  \\ $$  /$$$$$$  /$$   /$$  /$$$$$$ | $$  /$$$$$$              \n" +
                "      | $$       |____  $$ /$$_____/| $$| $$__  $$ /$$__  $$      | $$$$$$$/ /$$__  $$| $$  | $$ |____  $$| $$ /$$__  $$             \n" +
                "      | $$        /$$$$$$$|  $$$$$$ | $$| $$  \\ $$| $$  \\ $$      | $$__  $$| $$  \\ $$| $$  | $$  /$$$$$$$| $$| $$$$$$$$             \n" +
                "      | $$    $$ /$$__  $$ \\____  $$| $$| $$  | $$| $$  | $$      | $$  \\ $$| $$  | $$| $$  | $$ /$$__  $$| $$| $$_____/             \n" +
                "      |  $$$$$$/|  $$$$$$$ /$$$$$$$/| $$| $$  | $$|  $$$$$$/      | $$  | $$|  $$$$$$/|  $$$$$$$|  $$$$$$$| $$|  $$$$$$$             \n" +
                "       \\______/  \\_______/|_______/ |__/|__/  |__/ \\______/       |__/  |__/ \\______/  \\____  $$ \\_______/|__/ \\_______/             \n" +
                "                                                                                       /$$  | $$                                     \n" +
                "                                                                                      |  $$$$$$/                                     \n" +
                "                                                                                       \\______/                                      \u001B[0m\n" +
                "\n" +
                "\n";

        // When
        testCasino.welcomeHeader();

        String actualOutput = bytArrOutStr.toString();

        // Then
        Assert.assertEquals(expectedOutput, actualOutput);
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
    public void accountToStringTest() {
        // Given
        String name = "asdf";
        Double balance = 100.0;
        int id = 1;
        GuestAccount testGuestAccount = new GuestAccount(name, id, balance);
        Guest testGuest = new Guest(name, testGuestAccount);
        Casino testCasino = new Casino();
        testCasino.getGuestAccountDatabase().addAccount(name, balance);

        String expected = "This is your account status:\n" +
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
}
