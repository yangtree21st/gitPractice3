package io.zipcoder.casino.Games;
import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.Player;


public class HiLo extends CardGame implements GamblingGame {

    /**
     * This class creates game interface that allows the user to play Hi-Lo
     */

    private Card firstCard;
    private Card secondCard;
    private CardDeck cardDeck;
    private Guest guest;
    private Player hiloPlayer;
    private String playerChoice;
    private Double bet = 0.0;
    private Double getAccountBalance;
    private boolean continueGame;
    private Double minimumBet = 5.0;
    private Double winnings;
    private Hand hand;

    /**
     * This is the constructor for the Hi-Lo game
     *
     * @param guest It takes a guest from the casino that becomes a player.
     *              Gives a deck of cards that then shuffles it
     */
    public HiLo(Guest guest) {
        hiloPlayer = new Player(guest);
        this.cardDeck = new CardDeck();
        cardDeck.shuffleDeck();
        this.continueGame = true;

    }

    /**
     * Hi-Lo game constructor
     * @param testGuest it takes a Guest
     * @param testDeck it takes a CardDeck
     */
    HiLo(Guest testGuest, CardDeck testDeck) {
        this.cardDeck = testDeck;
        hiloPlayer = new Player(testGuest);
        this.continueGame = true;
    }
    /**
     * This method takes a card from the deck and deals the first card for the player if it the first card hasn't been dealt
     *
     * @return a card which is the firstCard or the secondCard
     */
    public Card deal() {
        if (firstCard != null) {
            secondCard = cardDeck.dealNextCard();
            return secondCard;
        }
        firstCard = cardDeck.dealNextCard();
        return firstCard;
    }

    /**
     * This method gets the curretCard
     * @return a Card
     */
    public Card getFirstCard() {
        return firstCard;
    }

    /**
     * This method sets the firstCard for the game
     * @param firstCard it takes a Card
     */
    public void setFirstCard(Card firstCard) {
        this.firstCard = firstCard;
    }

    /**
     * This method gets the secondCard dealt
     * @return a Card
     */
        Card getsecondCard() {
        return secondCard;
    }

    /**
     * This method sets the secondCard for the game
     * @param secondCard it takes a Card
     */
    public void setsecondCard(Card secondCard) {
        this.secondCard = secondCard;
    }

    /**
     * This method creates a Hand of cards that takes the firstCard and Second card
     * @return a Hand of cards
     */

        Hand firstAndSecondCard() {
        hand = new Hand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        return hand;
    }

    /**
     * This method compares the first card and second card value
     *
     * @param firstCard It takes the value of the firstCard
     * @param secondCard It takes the value of the secondCard
     * @return true if the second card is less than the first one
     */

    public boolean isLess(Card firstCard, Card secondCard) {
        Integer firstCardValue = firstCard.getValue().ordinal() + 1;
        Integer secondCardValue = secondCard.getValue().ordinal() + 1;

        if (secondCardValue < firstCardValue) {

            return true;
        }
        return false;
    }

    /**
     * This method compares the first and second card values
     *
     * @param firstCard it takes the firstCard value
     * @param secondCard    it takes the secondCard value
     * @return true if the secondCard value is more than the firstCard value
     */

    public boolean isMore(Card firstCard, Card secondCard) {

        return !isLess(firstCard, secondCard);
    }

    /**
     * This method lets the guest play a full game of Hi-Lo by putting the methods in the order they need to be used
     */
    public void playFullGame() {
        welcomeMessage();
        gameInstructions();
        do {
            firstCard = null;
            this.bet = Casino.console.getDoubleInput("Let's start!!!\n"+"The minimum bet is $5.00\n"+"Please enter your bet:");

            while (this.bet < 5) {
                this.bet = Casino.console.getDoubleInput("Error, Please enter a bet equal or grater than $5:");
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
        Casino.console.println(OneCardUpOneDown()+"Guess second Card");
        this.playerChoice = Casino.console.getStringInput("Enter 'H' for Higher,'L' for Low");
        while(!playerChoice.equalsIgnoreCase("h")&&!playerChoice.equalsIgnoreCase("l")){

        this.playerChoice = Casino.console.getStringInput("Enter 'H' for Higher,'L' for Low");
        }
    }

    /**
     * This method update the display by printing the secondCard the player guessed
     */
    public void showSecondCard() {
        deal();
        Casino.console.println(firstAndSecondCard().toString());
    }

    /**
     * This method checks if the player won or lose by comparing if the entry matches the isMore or isLess method
     */
    public void winning() {
        if (playerChoice.equalsIgnoreCase("H") && isMore(firstCard, secondCard) ||
                (playerChoice.equalsIgnoreCase("L") && isLess(firstCard, secondCard))) {
            Casino.console.println("You Won!!!");
            giveWinningsToPlayer(winnings);

        } else Casino.console.println("You Lost!!!");
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

    /**
     * This method displays the welcome message for the player
     */
    public void welcomeMessage() {

        Casino.console.println(
                "\n+" +

                "                /$$      /$$           /$$                                                     /$$                     /$$   /$$ /$$         /$$\n" +
                "                | $$  /$ | $$          | $$                                                    | $$                    | $$  | $$|__/        | $$\n" +
                "                | $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$        /$$$$$$    /$$$$$$       | $$  | $$ /$$        | $$        /$$$$$$\n" +
                "                | $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$      |_  $$_/   /$$__  $$      | $$$$$$$$| $$ /$$$$$$| $$       /$$__  $$\n" +
                "                | $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$        | $$    | $$  \\ $$      | $$__  $$| $$|______/| $$      | $$  \\ $$\n" +
                "                | $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/        | $$ /$$| $$  | $$      | $$  | $$| $$        | $$      | $$  | $$\n" +
                "                | $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$        |  $$$$/|  $$$$$$/      | $$  | $$| $$        | $$$$$$$$|  $$$$$$/\n" +
                "                |__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/         \\___/   \\______/       |__/  |__/|__/        |________/ \\______/\n" +
                "        ");
    }
    /**
     * This method prints the first card up and the second card down
     * @return
     */
    public String OneCardUpOneDown() {
        String[] faceDownCard = {
                "-------------- " ,
                "|************| " };
        StringBuilder createHand = new StringBuilder();

        createHand.append(firstCard.boundaryLineOfCard()).append(' ').append(faceDownCard[0]);
        createHand.append('\n');
        createHand.append(firstCard.firstRankLineOfCard()).append(' ').append(faceDownCard[1]);
        createHand.append('\n');
        createHand.append(firstCard.firstSuitLineOfCard()).append(' ').append(faceDownCard[1]);
        createHand.append('\n');
        createHand.append(firstCard.emptyLineOfCard()).append(' ').append(faceDownCard[1]);
        createHand.append('\n');
        createHand.append(firstCard.middleLineOfCard()).append(' ').append(faceDownCard[1]);
        createHand.append('\n');
        createHand.append(firstCard.emptyLineOfCard()).append(' ').append(faceDownCard[1]);
        createHand.append('\n');
        createHand.append(firstCard.secondSuitLineOfCard()).append(' ').append(faceDownCard[1]);
        createHand.append('\n');
        createHand.append(firstCard.secondRankLineOfCard()).append(' ').append(faceDownCard[1]);
        createHand.append('\n');
        createHand.append(firstCard.boundaryLineOfCard()).append(' ').append(faceDownCard[0]);
        createHand.append('\n');
        return createHand.toString();
    }



    public static void main(String[] args) {
        Casino testCasino = new Casino();
        HiLo testHiLo = new HiLo(new Guest("", new GuestAccount("", 0, 1000.0)));

        testHiLo.playFullGame();
    }


    /**
     * This method display the instructions for the game.
     */
    public void gameInstructions(){
        Casino.console.println("Game Rules:\n"+

                "Hi-Lo, is a fairly simple card game.It uses a standard deck of 52 cards.\n"+
                "The dealer is in control of the deck, and the player is responsible for guessing\n" +
                "the values of cards.\n"+
                "Dealing and Play:\n" +
                "After shuffling and cutting the deck, the dealer will place one card  face-up.\n"+
                "Guessing:\n"+
                "Based on the card that's currently showing, the player has to guess whether the\n" +
                "face-down card is higher or lower than the face-up card. \n" +
                "After the guess, the dealer flips the hidden card, and  the  answer is revealed.\n"+
                "If the guess was correct, the player wins!\n" +
                "***********************************************************************************");

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



