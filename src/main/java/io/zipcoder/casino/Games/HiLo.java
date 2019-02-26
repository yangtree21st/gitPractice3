package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.CrapsDisplay;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Main;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.Player;


public class HiLo extends CardGame implements GamblingGame {

    /**
     * This class creates game interface that allows the user to play Hi-Lo
     */

    private Card currentCard;
    private Card nextCard;
    private CardDeck cardDeck;
    private Guest guest;
    private Player hiloPlayer;
    private String playerChoice;
    private Double bet = 0.0;
    private Double getAccountBalance;
    private boolean continueGame;
    private Double minimumBet = 5.0;
    private Double winnings;

    /**
     * This is the constructor for the Hi-Lo game
     *
     * @param guest It takes a guest from the casino that becomes a player. Gives a deck of cards that then shuffles it
     */
    public HiLo(Guest guest) {

        hiloPlayer = new Player(guest);
        this.cardDeck = new CardDeck();
        cardDeck.shuffleDeck();
        this.continueGame = true;

    }

    HiLo(Guest testGuest, CardDeck testDeck) {
        this.cardDeck = testDeck;
        hiloPlayer = new Player(testGuest);
        this.continueGame = true;
    }

    /**
     * This method takes a card from the deck and deals the first card for the player
     *
     * @return a card
     */
    public Card deal() {


        if (currentCard != null) {

            nextCard = cardDeck.dealNextCard();
            return nextCard;


        }
        currentCard = cardDeck.dealNextCard();
        return currentCard;

    }

    /**
     * This method takes a card from the deck and deals the second card for the player
     *
     * @return another card from the deck
     */
    public Card dealSecondCard() {
        nextCard = cardDeck.dealNextCard();
        return nextCard;

    }

    /**
     * This method compares the current  and next card value
     *
     * @param currentCard It takes the value of the currentCard
     * @param nextCard    It takes the value of the nextCard
     * @return true if the next card is less than the current one
     */

    public boolean isLess(Card currentCard, Card nextCard) {
        Integer currentCardValue = currentCard.getValue().ordinal() + 1;
        Integer nextCardValue = nextCard.getValue().ordinal() + 1;
//         Casino.console.println("cardvalue"+currentCardValue);
//        Casino.console.println("secondcardvalue  "+nextCardValue);
        if (nextCardValue < currentCardValue) {

            return true;
        }
        return false;
    }

    /**
     * This method compares the current and next card values
     *
     * @param currentCard it takes the currentCard value
     * @param nextCard    it takes the nextCard value
     * @return true if the nextCard value is more than the currentCard value
     */

    public boolean isMore(Card currentCard, Card nextCard) {

        return !isLess(currentCard, nextCard);
    }

    /**
     * This method lets the guest play a full game of Hi-Lo by putting the methods in the order they need to be used
     */
    public void playFullGame() {
        welcomeMessage();
        setUp();

        do {
            currentCard = null;
            this.bet = Casino.console.getDoubleInput("Please enter your bet:");

            while (this.bet < 5) {
                this.bet = Casino.console.getDoubleInput("Error, Please enter a bet over $5:");
            }

            if (enoughMoneyForBet(bet, hiloPlayer)) {
                receiveBetFromPlayer(bet);
                showFirstCardAndGetHiOrLo();

                checkPlayersBalance(hiloPlayer);
                showSecondCard();
                winning();
                checkPlayersBalance(hiloPlayer);
                quitGame();
            }
        }
        while (checkPlayersBalance(hiloPlayer) >= minimumBet && this.continueGame == true);
        Casino.console.println("You have played a full game of Hi-Lo!");
    }

    /**
     * This method welcomes the guest and let the player know the minimum bet
     */
    public void setUp() {

        Casino.console.println("WELCOME TO HI-LO GAME" + "\n" +
                "The minimum bet is $5.00");

    }

    /**
     * This method checks the player balance
     *
     * @param currentPlayer it takes a player
     * @return a double representing the player's balance
     */

    public Double checkPlayersBalance(Player currentPlayer) {
        getAccountBalance = hiloPlayer.getAccountBalance();
        return getAccountBalance;
    }

    /**
     * This method check if the player has enough balance to bet
     *
     * @param bet           it takes a double for the bet
     * @param currentPlayer it takes a player
     * @return a boolean, true if the balance is enough to bet, false if it isn't
     */

    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {
        //this.bet = Casino.console.getDoubleInput("Please enter your bet:");
        if (bet != 0) {
            if (bet <= checkPlayersBalance(currentPlayer)) {
                return true;
            } else {
                Casino.console.println("Not enough funds, please try again");
                //this.continueGame = false;
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * This method removes the bet amount from the player's account
     *
     * @param bet it takes a double bet
     */

    public void receiveBetFromPlayer(Double bet) {
        hiloPlayer.removeFunds(bet);
        getAccountBalance = hiloPlayer.getAccountBalance();
        Casino.console.println("This is your balance " + getAccountBalance);

    }

    /**
     * This method lets the player take a turn to guess if the coming card is higher or lower than the one just displayed
     */
    public void showFirstCardAndGetHiOrLo() {
        deal();
        Casino.console.println(currentCard.toString()+"Guess next Card");
        
        this.playerChoice = Casino.console.getStringInput("Enter 'H' for Higher,'L' for Low");

    }

    /**
     * This method update the display by printing the nextCard the user needs to see
     */
    public void showSecondCard() {
        deal();
        Casino.console.println(nextCard.toString());
    }

    /**
     * This method checks if the player won or lose by comparing if the entry matches the isMore or isLess method
     */
    public void winning() {
        if (playerChoice.equalsIgnoreCase("H") && isMore(currentCard, nextCard) ||
                (playerChoice.equalsIgnoreCase("L") && isLess(currentCard, nextCard))) {
            Casino.console.println("You Win");
            giveWinningsToPlayer(winnings);

        } else Casino.console.println("You Lose");
        Casino.console.println("This is your balance " + getAccountBalance);


    }

    /**
     * This method add funds to the player's account balance if the player won by adding a 25% of the player's bet
     *
     * @param winnings it takes a double
     */

    public void giveWinningsToPlayer(Double winnings) {

        winnings = bet * 1.25;
        hiloPlayer.addFunds(winnings);
        getAccountBalance = hiloPlayer.getAccountBalance();


    }

    /**
     * This method let the player quit the game  by asking if they want to continue or
     */

    void quitGame() {
        if (getAccountBalance != 0) {
            while (true) {
                this.playerChoice =
                        Casino.console.getStringInput("Do you wish to continue, Enter Yes or No");

                if (playerChoice.equalsIgnoreCase("No")) {
                    this.continueGame = false;
                    break;

                }
                if (playerChoice.equalsIgnoreCase("Yes")) {
                    this.continueGame = true;
                    break;
                }
            }
        }
    }

    public void welcomeMessage() {

        Casino.console.println("/$$      /$$           /$$                                                     /$$                     /$$   /$$ /$$         /$$\n" +
                "                | $$  /$ | $$          | $$                                                    | $$                    | $$  | $$|__/        | $$\n" +
                "                | $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$        /$$$$$$    /$$$$$$       | $$  | $$ /$$        | $$        /$$$$$$\n" +
                "                | $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$      |_  $$_/   /$$__  $$      | $$$$$$$$| $$ /$$$$$$| $$       /$$__  $$\n" +
                "                | $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$        | $$    | $$  \\ $$      | $$__  $$| $$|______/| $$      | $$  \\ $$\n" +
                "                | $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/        | $$ /$$| $$  | $$      | $$  | $$| $$        | $$      | $$  | $$\n" +
                "                | $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$        |  $$$$/|  $$$$$$/      | $$  | $$| $$        | $$$$$$$$|  $$$$$$/\n" +
                "                |__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/         \\___/   \\______/       |__/  |__/|__/        |________/ \\______/\n" +
                "        ");


    }



        String[] faceDownCard = {"\n"+
                 "-------------- \n" +
                 "|            | \n" +
                 "|            | \n" +
                 "|            | \n" +
                 "|            | \n" +
                 "|            | \n" +
                 "|            | \n" +
                 "|            | \n" +
                 "--------------  "};



    public String createOneCardUpOneDown() {
        StringBuilder createHand = new StringBuilder();

        createHand.append(currentCard.boundaryLineOfCard()).append(' ').append(faceDownCard[0]);
        createHand.append('\n');
        createHand.append(currentCard.firstRankLineOfCard()).append(' ').append(faceDownCard[1]);
        createHand.append('\n');
        createHand.append(currentCard.firstSuitLineOfCard()).append(' ').append(faceDownCard[2]);
        createHand.append('\n');
        createHand.append(currentCard.emptyLineOfCard()).append(' ').append(faceDownCard[3]);
        createHand.append('\n');
        createHand.append(currentCard.middleLineOfCard()).append(' ').append(faceDownCard[4]);
        createHand.append('\n');
        createHand.append(currentCard.emptyLineOfCard()).append(' ').append(faceDownCard[5]);
        createHand.append('\n');
        createHand.append(currentCard.secondSuitLineOfCard()).append(' ').append(faceDownCard[6]);
        createHand.append('\n');
        createHand.append(currentCard.secondRankLineOfCard()).append(' ').append(faceDownCard[7]);
        createHand.append('\n');
        createHand.append(currentCard.boundaryLineOfCard()).append(' ').append(faceDownCard[8]);
        createHand.append('\n');

        return createHand.toString();
    }



    public static void main(String[] args) {
        Casino testCasino = new Casino();
        HiLo testHiLo = new HiLo(new Guest("", new GuestAccount("", 0, 1000.0)));

        testHiLo.playFullGame();
    }
}
//    public static void main(String[] args) {
//        Casino testCasino = new Casino();
//        GuestAccount guestAccount = new GuestAccount("Marlys", 1, 100.0);
//
//        Guest guest = new Guest("Marlys", guestAccount);
//        Player hiloplayer = new HiLowPlayer(guest);
//        HiLo testHiLo = new HiLo(guest);
//        testHiLo.playFullGame();
//    }





//
//        else if (this.bet > checkPlayersBalance(hiloPlayer)) {
//            continueGame=false;
//            Casino.console.println("You don't have enough funds");
//            }
//
//        if ((checkPlayersBalance(hiloPlayer) >= minimumBet) && (checkPlayersBalance(hiloPlayer) >= this.bet &&
//                this.bet < minimumBet)&&this.bet > minimumBet) {
//
//            return true;
////        } else if (this.bet < minimumBet && checkPlayersBalance(hiloPlayer) >= minimumBet) {
////            do {
////                Casino.console.println("Your bet needs to be equal or higher than the minimum bet");
////                this.bet = Casino.console.getDoubleInput("Please enter your bet:");
////                this.continueGame = false;
////
////                return false;
////            } while ((this.bet < minimumBet));
//
//
//        }return continueGame;


//    public void betLessThanMinimum() {
//        if (this.bet < minimumBet && checkPlayersBalance(hiloPlayer) >= minimumBet) {
//            do {
//                Casino.console.println("Your bet needs to be equal or higher than the minimum bet");
//                this.bet = Casino.console.getDoubleInput("Please enter your bet:");
////                this.continueGame = false;
////
////                return false;
//            } while ((this.bet < minimumBet));
//        }
//    }

//        {
//        else if(playerChoice.equalsIgnoreCase("L")&&isLess(currentCard,nextCard)){
//            Casino.console.println("You Win");
//            giveWinningsToPlayer(winnings);

// if (currentCard!=null){
//            currentCard =nextCard;
//            currentCard =cardDeck.dealNextCard();
//
//
//            Casino.console.println("value 2 "+(nextCard.getValue().ordinal()+ 1));
//            return currentCard;
//
//        }currentCard=cardDeck.dealNextCard();
//
//
//        Casino.console.println("value 1 "+(currentCard.getValue().ordinal()+ 1));

 /*
        if (checkPlayersBalance(hiloPlayer) >= minimumBet) {
            this.bet = Casino.console.getDoubleInput("Please enter your bet:");
            return true;
        } else {
            continueGame = false;
            return false;
        }
        */