package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.GoFishPlayer;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.Player;

import java.util.Random;

public class GoFish extends CardGame implements Game {
    private Guest guest;
    private Player player;
    private Player dealer;
    private Integer numberOfPairsPlayer;
    private Integer numberOfPairsDealer;
    private CardDeck goFishDeck;
    private Hand playerHand;
    private Hand dealerHand;
    private Card currentCard;
    private Card checkCard;
    private Card pairCard1;
    private Card pairCard2;


    public GoFish(Guest guest) {

        this.player = new Player(guest);
        this.dealer = new Player(null);
        this.numberOfPairsPlayer = 0;
        this.numberOfPairsDealer = 0;
        this.goFishDeck = new CardDeck();
        this.goFishDeck.shuffleDeck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.currentCard = new Card();
        this.checkCard = new Card();
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOpponent() {
        return dealer;
    }

    public void playFullGame() {

        updateDisplay();

        Casino.console.getStringInput("Press [Enter] To Continue");

        setUp();

        while (!(playerHand.getAllOfPlayerCards().size() == 0 || dealerHand.getAllOfPlayerCards().size() == 0)) {

            containsPairs(playerHand);
            containsPairs(dealerHand);
            takeTurn(playerHand, dealerHand);
            opponentTurn(dealerHand, playerHand);
        }

        Casino.console.println("You have played a full game of Go Fish!");

    }

    public void updateDisplay() {

        Casino.console.println(":'######::::'#######:::::'########:'####::'######::'##::::'##:\n" +
                "'##... ##::'##.... ##:::: ##.....::. ##::'##... ##: ##:::: ##:\n" +
                " ##:::..::: ##:::: ##:::: ##:::::::: ##:: ##:::..:: ##:::: ##:\n" +
                " ##::'####: ##:::: ##:::: ######:::: ##::. ######:: #########:\n" +
                " ##::: ##:: ##:::: ##:::: ##...::::: ##:::..... ##: ##.... ##:\n" +
                " ##::: ##:: ##:::: ##:::: ##:::::::: ##::'##::: ##: ##:::: ##:\n" +
                ". ######:::. #######::::: ##:::::::'####:. ######:: ##:::: ##:\n" +
                ":......:::::.......::::::..::::::::....:::......:::..:::::..::");

    }

    public void setUp() {
        this.numberOfPairsDealer = 0;
        this.numberOfPairsPlayer = 0;

        this.goFishDeck.shuffleDeck();


        playerHand.addCard(goFishDeck.dealNextCard());
        dealerHand.addCard(goFishDeck.dealNextCard());

        playerHand.addCard(goFishDeck.dealNextCard());
        dealerHand.addCard(goFishDeck.dealNextCard());

        playerHand.addCard(goFishDeck.dealNextCard());
        dealerHand.addCard(goFishDeck.dealNextCard());

        playerHand.addCard(goFishDeck.dealNextCard());
        dealerHand.addCard(goFishDeck.dealNextCard());

        playerHand.addCard(goFishDeck.dealNextCard());
        dealerHand.addCard(goFishDeck.dealNextCard());

        playerHand.addCard(goFishDeck.dealNextCard());
        dealerHand.addCard(goFishDeck.dealNextCard());

        playerHand.addCard(goFishDeck.dealNextCard());
        dealerHand.addCard(goFishDeck.dealNextCard());



        playerHand.getAllOfPlayerCards();

        printOutCurrentHand();


    }

    public Card deal () {
        return super.getDeck().dealNextCard();
    }


    public Boolean containsPairs(Hand hand) {
        int counter = 0;

        for (int cardIndex = 0; cardIndex < hand.getAllOfPlayerCards().size() - 1; cardIndex++) {
            for (int checkIndex = cardIndex + 1; checkIndex < hand.getAllOfPlayerCards().size(); checkIndex++) {
                currentCard = hand.getAllOfPlayerCards().get(cardIndex);
                checkCard = hand.getAllOfPlayerCards().get(checkIndex);

                if (currentCard.getValue() == checkCard.getValue()) {
                    counter++;
                    removePairs(hand);
                }
            }
        }
        return (counter > 0);
    }


    private void removePairs(Hand hand) {


        hand.getAllOfPlayerCards().remove(currentCard);
        hand.getAllOfPlayerCards().remove(checkCard);

    }


    public void takeTurn(Hand playerHand, Hand dealerHand) {

        Casino.console.println("Take Your Turn");

        printOutCurrentHand();

        boolean goFish = true;

        int input = Casino.console.getIntegerInput("\"Pick A Card To Fish For [enter the index number listed above the card]: \"");
        for (int dealerIndex = 0; dealerIndex < dealerHand.getAllOfPlayerCards().size(); dealerIndex++) {
            if (playerHand.getAllOfPlayerCards().get(input).getValue() == dealerHand.getAllOfPlayerCards().get(dealerIndex).getValue()) {
                dealerHand.removeCard(dealerHand.getAllOfPlayerCards().get(dealerIndex));
                playerHand.removeCard(playerHand.getAllOfPlayerCards().get(input));
                numberOfPairsPlayer++;
                goFish = false;
                break;

            }
        }
         if (goFish){
            Casino.console.println("Go Fish!!!");
            playerHand.addCard(goFishDeck.dealNextCard());}

        displayCurrentScore();
    }


    public void opponentTurn(Hand dealerHand, Hand playerHand) {

        Casino.console.println("Now It Is Your Opponent's Turn");

        Random randomNumGen = new Random();


        Card fishCard = dealerHand.getAllOfPlayerCards().get(randomNumGen.nextInt(dealerHand.getAllOfPlayerCards().size()));

        Casino.console.println("Do You Have Any " + fishCard.getValue());
        Casino.console.getStringInput("Press [Enter] To Continue");
        boolean goFish = true;
        for (int cardIndex = 0; cardIndex < playerHand.getAllOfPlayerCards().size(); cardIndex++) {
            if (playerHand.getAllOfPlayerCards().get(cardIndex).getValue() == fishCard.getValue()) {
                playerHand.getAllOfPlayerCards().remove(cardIndex);
                dealerHand.removeCard(fishCard);
                numberOfPairsDealer++;
                goFish = false;
                break;

            }
        }
        if (goFish) {
            Casino.console.println("You Laugh And Tell Your Opponent To Go Fish!!!");
            if (goFishDeck.getCardDeckSize() > 0) {
                dealerHand.addCard(goFishDeck.dealNextCard());
            }
        }


        displayCurrentScore();
        }




    private void printOutCurrentHand() {
        Casino.console.println(playerHand.toString());
        for (int i = 0; i < playerHand.getAllOfPlayerCards().size(); i++) {
            Casino.console.print("[%d]            ", i);
        }
    }

    private void displayCurrentScore() {

        Casino.console.println("You Currently Have " + numberOfPairsPlayer + " matches.");
        Casino.console.println("Your Opponent Currently Has " + numberOfPairsDealer + " matches.");

    }

    public void winning(Integer numberOfPairsPlayer, Integer numberOfPairsDealer) {
        if (numberOfPairsPlayer > numberOfPairsDealer) {

            Casino.console.println("\n" +
                    "  ___    ___ ________  ___  ___          ___       __   ___  ________   ___  ___  ___       \n" +
                    " |\\  \\  /  /|\\   __  \\|\\  \\|\\  \\        |\\  \\     |\\  \\|\\  \\|\\   ___  \\|\\  \\|\\  \\|\\  \\      \n" +
                    " \\ \\  \\/  / | \\  \\|\\  \\ \\  \\\\\\  \\       \\ \\  \\    \\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \\  \\     \n" +
                    "  \\ \\    / / \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\  __\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \\  \\    \n" +
                    "   \\/  /  /   \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\|\\__\\_\\  \\ \\  \\ \\  \\\\ \\  \\ \\__\\ \\__\\ \\__\\   \n" +
                    " __/  / /      \\ \\_______\\ \\_______\\       \\ \\____________\\ \\__\\ \\__\\\\ \\__\\|__|\\|__|\\|__|   \n" +
                    "|\\___/ /        \\|_______|\\|_______|        \\|____________|\\|__|\\|__| \\|__|   ___  ___  ___ \n" +
                    "\\|___|/                                                                      |\\__\\|\\__\\|\\__\\\n" +
                    "                                                                             \\|__|\\|__|\\|__|\n" +
                    "                                                                                            \n");
        }

    }

    public void losing(Integer numberOfPairsDealer, Integer numberOfPairsPlayer) {

        if (numberOfPairsDealer > numberOfPairsPlayer) {

            Casino.console.println("\n" +
                    "                 _,.---._                                    _,.---._      ,-,--.     ,----.        \n" +
                    " ,--.-.  .-,--.,-.' , -  `.  .--.-. .-.-.          _.-.    ,-.' , -  `.  ,-.'-  _\\ ,-.--` , \\       \n" +
                    "/==/- / /=/_ //==/_,  ,  - \\/==/ -|/=/  |        .-,.'|   /==/_,  ,  - \\/==/_ ,_.'|==|-  _.-`       \n" +
                    "\\==\\, \\/=/. /|==|   .=.     |==| ,||=| -|       |==|, |  |==|   .=.     \\==\\  \\   |==|   `.-.       \n" +
                    " \\==\\  \\/ -/ |==|_ : ;=:  - |==|- | =/  |       |==|- |  |==|_ : ;=:  - |\\==\\ -\\ /==/_ ,    /       \n" +
                    "  |==|  ,_/  |==| , '='     |==|,  \\/ - |       |==|, |  |==| , '='     |_\\==\\ ,\\|==|    .-'        \n" +
                    "  \\==\\-, /    \\==\\ -    ,_ /|==|-   ,   /       |==|- `-._\\==\\ -    ,_ //==/\\/ _ |==|_  ,`-._       \n" +
                    "  /==/._/      '.='. -   .' /==/ , _  .'        /==/ - , ,/'.='. -   .' \\==\\ - , /==/ ,     /       \n" +
                    "  `--`-`         `--`--''   `--`..---'          `--`-----'   `--`--''    `--`---'`--`-----``        \n");
        }


    }

    public void quitGame() {

    }
    public void takeTurn() {

    }
    public void winning() {

    }

    public void losing() {

    }
}

        /*public Hand getPlayerHand (GoFishPlayer player){
            return null;
        }

        public Card askOtherPlayerforCard () {
            return null;
        }

        public void goFish () {

        }

        public GoFishPlayer breakTie () {
            return null;
        }

        public void incrementNumberOfPairs (GoFishPlayer playerToIncrement){

        }




        public static void main (String[]args){
            GuestAccount guestAccount = new GuestAccount("la", null, 0.0);
            Guest guest = new Guest("la", guestAccount);
            Casino console = new Casino();
            GoFish goFish = new GoFish(guest);
            goFish.playFullGame();


        }

}
/* for (int cardIndex = 0; cardIndex < playerHand.getAllOfPlayerCards().size(); cardIndex++) {

        if (playerHand.getAllOfPlayerCards().get(cardIndex) == null) {
        System.out.println("Annnnnnd That's Game!!"); */


