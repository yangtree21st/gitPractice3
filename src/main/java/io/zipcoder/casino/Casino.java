package io.zipcoder.casino;

import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.utilities.Console;
import javafx.scene.Scene;

import java.io.InputStream;
import java.io.PrintStream;


public class Casino {
    public static GuestAccountDataBase guestAccountDatabase;
    public static Console console;
    private Guest currentGuest;


    /**
     *Constructor to instantiate the Casino class taking 3 params
     */
    public Casino(){
       this(new GuestAccountDataBase(), System.in,System.out);
    }

    /**
     * Constructor to instantiate the Casino class taking 3 params
     * @param gab
     * @param inStream
     * @param outputStream
     */
    public Casino(GuestAccountDataBase gab, InputStream inStream, PrintStream outputStream){
        this.guestAccountDatabase = gab;
        this.console = new Console(inStream, outputStream);
    }

    /**
     * Constructor to create Casino with only Input and Print streams
     * @param inStream
     * @param outputStream
     */

    public Casino(InputStream inStream, PrintStream outputStream){
        this.guestAccountDatabase = new GuestAccountDataBase();
        this.console = new Console(inStream, outputStream);
    }

    /**
     * Method sets a newly created guest as the class' currentGuest
     * @param name
     * @param guestAccount
     */
    public void setGuest(String name, GuestAccount guestAccount){
        this.currentGuest = new Guest(name, guestAccount);
    }

    /**
     * Returns the current guest that is set for the class
     * @return
     */
    public Guest getGuest() {
        return this.currentGuest;
    }

    /**
     * Returns the guest account database that houses all the guest accounts
     * @return
     */

    public GuestAccountDataBase getGuestAccountDatabase(){
        return this.guestAccountDatabase;
    }


    /**
     * Prints out the welcome header
     */

    public void welcomeHeader(){
        console.println("*******************************************\nWELCOME TO THE CASINO\n*******************************************");
    }

    /**
     * Prompts the user to provide their name
     * @return String representation of user inputted name
     */
    public String getGuestName() {
        return console.getStringInput("Please provide your name to start a new guest account.");
    }

    /**
     *Prompts the user to provide their starting balance
     *@return Double representation of user inputted starting balance
     */
    public Double getStartingBalance(){
        return console.getDoubleInput("How much money would you like to deposit into your account?");
    }

    /**
     * Creates the guest account, stores the returned id number to retrieve the the newly created account and return it.
     * @param name
     * @param startingBalance
     * @return
     */
    public GuestAccount createGuestAccount(String name, Double startingBalance){
        Integer guestId = guestAccountDatabase.addAccount(name, startingBalance);
        return guestAccountDatabase.getAccount(guestId);
    }

    public String initializeAccountCreation(String guestNewName, Double guestStartingBalance){
        GuestAccount newGuestAccount = createGuestAccount(guestNewName, guestStartingBalance);
        setGuest(guestNewName, newGuestAccount);
        Integer accountId = newGuestAccount.getId();

        return String.format("This is your new account.\n%s",guestAccountDatabase.getAccount(accountId).toString());
    }

    /**
     * Begins the start of taking user input and return of account creation. Begins Casino Games run method.
     */
    public void startCasinoExperience() {
        this.welcomeHeader();
        String guestNewName = this.getGuestName();
        Double guestStartingBalance = this.getStartingBalance();

        GuestAccount newGuestAccount = createGuestAccount(guestNewName, guestStartingBalance);
        setGuest(guestNewName, newGuestAccount);

        Integer accountId = newGuestAccount.getId();
        console.println(String.format("\nThis is your account.\n%s", guestAccountDatabase.getAccount(accountId).toString()));

        while(continueOrExit()) {
            CasinoGames casinoGames = new CasinoGames(currentGuest);
            casinoGames.runSelectedGames();

        }
        console.println("GOODBYE!");
        System.exit(1);

    }

    public void startCasinoGuiExperience(){
        CasinoDisplay casinoDisplay = new CasinoDisplay();
        Main.mainStage.setScene(new Scene(casinoDisplay.createCasinoContent()));
        Main.mainStage.show();
    }

    public boolean continueOrExit(){
        Integer userChoice = console.getIntegerInput("*******************************************" +
                "\nWould you like to:\n[1]Enter the Casino Game floor\n[2]Add funds to your account\n[3]Exit Casino");

        while(true){
            if(userChoice==1){
                return true;
            }
            else if(userChoice==2){
                return addFundsToAccount();
            }
            else if(userChoice==3){
                return false;
            }
            else{
                console.getIntegerInput("Incorrect input.\n[1]Enter the Casino Game\n[2]Add funds to your account\n[3]Exit Casino");
            }
        }

    }

    public boolean addFundsToAccount(){
        Double fundsToAdd = console.getDoubleInput("How much would you like to add?");
        currentGuest.addFunds(fundsToAdd);
        console.println(String.format("Your account balance is now %.2f",currentGuest.getAccountBalance()));
        return true;
    }



}