package io.zipcoder.casino;

import io.zipcoder.casino.Displays.CasinoDisplay;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.utilities.AnsiStuff;
import io.zipcoder.casino.utilities.Banners;
import io.zipcoder.casino.utilities.Console;
import javafx.scene.Scene;

import java.io.InputStream;
import java.io.PrintStream;


public class Casino {
    public static GuestAccountDataBase guestAccountDatabase;
    public static Console console;
    private Guest currentGuest;


    /**
     * Constructor to instantiate the Casino class taking 3 params
     */
    public Casino() {
        this(new GuestAccountDataBase(), System.in, System.out);
    }

    /**
     * Constructor to instantiate the Casino class taking 3 params
     *
     * @param gab
     * @param inStream
     * @param outputStream
     */
    public Casino(GuestAccountDataBase gab, InputStream inStream, PrintStream outputStream) {
        guestAccountDatabase = gab;
        console = new Console(inStream, outputStream);
    }

    /**
     * Constructor to create Casino with only Input and Print streams
     *
     * @param inStream
     * @param outputStream
     */

    public Casino(InputStream inStream, PrintStream outputStream) {
        guestAccountDatabase = new GuestAccountDataBase();
        console = new Console(inStream, outputStream);
    }

    /**
     * Begins the start of taking user input and return of account creation. Begins Casino Games run method.
     */
    public void startCasinoExperience() {
        console.println(welcomeHeader());
        console.println(collectNewAccountInfoAndInitialize());
        console.println("You are now ready to start your Casino experience!");

        while (continueOrExit()) {
            CasinoGames casinoGames = new CasinoGames(currentGuest);
            casinoGames.selectAndRunGame();
            console.println("Welcome back to the lobby!");
        }

        console.println("Goodbye, and thank you for visiting the Casino Royale!");
        System.exit(1);
    }

    /**
     * @return
     */

    public String collectNewAccountInfoAndInitialize() {
        String guestNewName = getGuestName();
        Double guestStartingBalance = getStartingBalance();

        GuestAccount newGuestAccount = createGuestAccount(guestNewName, guestStartingBalance);
        setGuest(guestNewName, newGuestAccount);

        Integer accountId = newGuestAccount.getId();
        String accountInfo = String.format("\nNew Account Details:\n%s", guestAccountDatabase.getAccount(accountId).toString());

        return accountInfo;
    }

    /**
     * Prompts the user to provide their name
     *
     * @return String representation of user inputted name
     */
    public String getGuestName() {
        return console.getStringInput("\nPlease provide your name to start a new guest account:");
    }

    /**
     * Prompts the user to provide their starting balance
     *
     * @return Double representation of user inputted starting balance
     */
    public Double getStartingBalance() {
        Double startingBalance = console.getDoubleInput("How much money for gambling would you like to deposit into your account?");
        while (startingBalance < 0) {
            startingBalance = console.getDoubleInput("Sorry, please enter a positive cash amount:");
        }
        return startingBalance;
    }


    /**
     * Method sets a newly created guest as the class' currentGuest
     *
     * @param name
     * @param guestAccount
     */
    public void setGuest(String name, GuestAccount guestAccount) {
        this.currentGuest = new Guest(name, guestAccount);
    }

    /**
     * Returns the current guest that is set for the class
     *
     * @return
     */
    public Guest getGuest() {
        return this.currentGuest;
    }

    /**
     * Returns the guest account database that houses all the guest accounts
     *
     * @return
     */

    public GuestAccountDataBase getGuestAccountDatabase() {
        return guestAccountDatabase;
    }


    /**
     * Prints out the welcome header
     */

    public String welcomeHeader() {
        Banners.casinoWelcomeHeader();
        String startingHeader = AnsiStuff.ANSI_PURPLE +
//
                AnsiStuff.ANSI_RESET + "\n\n" +
                "Welcome to the Casino lobby!\nA friendly attendant asks you for your name to start a new account at the Casino Royale.";

        return startingHeader;
    }


    /**
     * Creates the guest account, stores the returned id number to retrieve the the newly created account and return it.
     *
     * @param name
     * @param startingBalance
     * @return
     */
    public GuestAccount createGuestAccount(String name, Double startingBalance) {
        Integer guestId = guestAccountDatabase.addAccount(name, startingBalance);
        return guestAccountDatabase.getAccount(guestId);
    }

    /**
     * Creates the new guest account based on the user input
     *
     * @param guestNewName
     * @param guestStartingBalance
     * @return
     */

    public String initializeAccountCreation(String guestNewName, Double guestStartingBalance) {
        GuestAccount newGuestAccount = createGuestAccount(guestNewName, guestStartingBalance);
        setGuest(guestNewName, newGuestAccount);
        Integer accountId = newGuestAccount.getId();

        return String.format("This is your new account.\n%s", guestAccountDatabase.getAccount(accountId).toString());
    }

    /**
     * Returns the string of the newly created account
     *
     * @param guest
     * @return
     */

    public String accountToString(Guest guest) {
        Integer accountId = guest.getMyAccount().getId();
        return String.format("This is your current account status:\n%s", guestAccountDatabase.getAccount(accountId).toString());
    }

    /**
     * Starts the GUI for Casino Royale. Creates a stage.
     */

    public void startCasinoGuiExperience() {
        CasinoDisplay casinoDisplay = new CasinoDisplay();
        Main.mainStage.setScene(new Scene(casinoDisplay.createCasinoContent()));
        Main.mainStage.show();
    }

    /**
     * Returns a string line in the color purple
     *
     * @return String
     */
    public String returnSeparatorLine() {
        return AnsiStuff.ANSI_PURPLE + "\n************************************************************\n" + AnsiStuff.ANSI_RESET;
    }

    /**
     * @return
     */
    public Integer getUserChoiceForContCashierExit() {
        Integer userChoice = console.getIntegerInput(AnsiStuff.ANSI_PURPLE + "Would you like to:\n[1]Enter the Casino Game floor\n" +
                "[2]Access your guest account\n[3]Exit the Casino" + AnsiStuff.ANSI_RESET);

        return userChoice;
    }


    /**
     * Asks the user for their choice of whether they want to continue to the casino floor, cashier, or exit the casino completely
     *
     * @return
     */
    public boolean continueOrExit() {
        returnSeparatorLine();
        while (true) {
            Integer userChoice = getUserChoiceForContCashierExit();
            if (userChoice == 1) {
                return true;
            } else if (userChoice == 2) {
                accessAccount();
            } else if (userChoice == 3) {
                return false;
            } else {
                console.println("Sorry, I couldn't understand you. Please enter '1', '2', or '3'.");
            }
        }

    }


    /**
     * When the user choses to access the account, the method is invoked to ask the user how much money they want to add or whether
     * they want to cashoout
     */

    public void accessAccount() {
        console.println(accountToString(currentGuest));
        Integer userChoice = addFundsOrCashout();
        if (userChoice == 1) {
            Double fundsToAdd = console.getDoubleInput("How much money would you like to add to your account?");
            addAdditionalFunds(fundsToAdd);
        } else {
            cashoutAccount();
        }
        accountToString(currentGuest);
    }

    /**
     * Asks the user whether they want to add funds or cashout
     */

    public Integer addFundsOrCashout() {
        Integer userChoice = console.getIntegerInput("Would you like to:\n[1]Add funds to your account\n[2]Cash out your account");

        while (!(userChoice == 1 || userChoice == 2)) {
            userChoice = console.getIntegerInput("Sorry, I couldn't understand you. Please enter '1' or '2'.");
        }

        return userChoice;//getUserChoiceForContCashierExit();
    }

    /**
     * Checks that the value of parameter passed in is more than 0. If it is now, it asks again for another value.
     *
     * @param amountToAdd
     */

    public void addAdditionalFunds(Double amountToAdd) {
        while (amountToAdd < 0) {
            amountToAdd = console.getDoubleInput("Sorry, please enter a positive cash amount:");
        }
        currentGuest.addFunds(amountToAdd);
    }

    /**
     * Removes all money from the current guest's account balance
     */

    private void cashoutAccount() {
        console.println("You are cashing out $%.2f", currentGuest.getAccountBalance());
        currentGuest.removeFunds(currentGuest.getAccountBalance());
    }


}