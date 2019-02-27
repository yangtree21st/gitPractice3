package io.zipcoder.casino.Games;

import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.Player;

import java.util.Random;

public class GoFish extends CardGame implements Game {
    private Player player;
    private Player dealer;
    private Integer numberOfPairsPlayer;
    private Integer numberOfPairsDealer;
    private CardDeck goFishDeck;
    private Hand playerHand;
    private Hand dealerHand;
    private Card currentCard;
    private Card checkCard;


    /**
     * @param guest
     */
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

    /**
     * FOR TESTING PURPOSES ONLY
     *
     * @param testGuest test guest
     * @param testDeck  non random deck of cards
     */
    GoFish(Guest testGuest, CardDeck testDeck) {
        this.player = new Player(testGuest);
        this.dealer = new Player(null);
        this.numberOfPairsPlayer = 0;
        this.numberOfPairsDealer = 0;
        this.goFishDeck = new CardDeck();
        this.goFishDeck.shuffleDeck();
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.currentCard = new Card();
        this.checkCard = new Card();
        this.goFishDeck = new CardDeck();
    }

    public Player getPlayer(Guest guest) {
        return player;
    }

    public void setPlayer() {
        this.player = player;
    }

    public Player getOpponent() {
        return dealer;
    }

    public void setOpponent() {
        this.dealer = dealer;
    }


    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getOpponentHand() {
        return dealerHand;
    }


    @Override
    public CardDeck getDeck() {
        return this.goFishDeck;
    }

    public void playFullGame() {


        updateDisplay();

        playGoFish();

        setUp();

        containsPairs(playerHand);
        removePairs(playerHand);
        pairsCounter();
        containsPairs(dealerHand);
        removePairs(dealerHand);
        pairsCounter();


        do {
            displayCurrentScore();
            showPlayerHand(playerHand);
            takeTurn(playerHand, dealerHand);
            containsPairs(playerHand);
            removePairs(playerHand);
            pairsCounter();

            displayCurrentScore();
            showPlayerHand(playerHand);
            opponentTurn(dealerHand, playerHand);
            containsPairs(dealerHand);
            removePairs(dealerHand);
            pairsCounter();

        } while (!(playerHand.getAllOfPlayerCards().size() == 0 || dealerHand.getAllOfPlayerCards().size() == 0 || goFishDeck.getCardDeckSize() == 0));

        winning(numberOfPairsPlayer, numberOfPairsDealer);

        losing(numberOfPairsDealer, numberOfPairsPlayer);

        tie(numberOfPairsPlayer, numberOfPairsDealer);

        Casino.console.println("You have played a full game of Go Fish!");

        playGoFish();

    }

    public void updateDisplay() {

        Casino.console.println(":'######::::'#######:::::'########:'####::'######::'##::::'##:\n" +
                "'##... ##::'##.... ##:::: ##.....::. ##::'##... ##: ##:::: ##:\n" +
                " ##:::..::: ##:::: ##:::: ##:::::::: ##:: ##:::..:: ##:::: ##:\n" +
                " ##::'####: ##:::: ##:::: ######:::: ##::. ######:: #########:\n" +
                " ##::: ##:: ##:::: ##:::: ##...::::: ##:::..... ##: ##.... ##:\n" +
                " ##::: ##:: ##:::: ##:::: ##:::::::: ##::'##::: ##: ##:::: ##:\n" +
                ". ######:::. #######::::: ##:::::::'####:. ######:: ##:::: ##:\n" +
                ":......:::::.......::::::..::::::::....:::......:::..:::::..::\n");

    }

    public void playGoFish() {

        Casino.console.getStringInput("Would You Like To Play?");
    }


    public void setUp() {
        this.numberOfPairsDealer = 0;
        this.numberOfPairsPlayer = 0;

        shuffleDeck(goFishDeck);

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        playerHand.addCard(deal());
        dealerHand.addCard(deal());

        showPlayerHand(playerHand);

    }

    public Card deal() {

        return goFishDeck.dealNextCard();
    }

    public void shuffleDeck(CardDeck cardDeck) {

        cardDeck.shuffleDeck();
    }

    public void showPlayerHand(Hand hand) {

        Casino.console.println(hand.toString() + "\n");
        for (int i = 0; i < hand.getAllOfPlayerCards().size(); i++) {
            Casino.console.print("[%d]            ", i);
        }
    }

    public Integer containsPairs(Hand hand) {
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
        return counter;
    }

    private void removePairs(Hand hand) {


        hand.getAllOfPlayerCards().remove(currentCard);
        hand.getAllOfPlayerCards().remove(checkCard);

    }

    public void pairsCounter() {
        numberOfPairsPlayer += containsPairs(playerHand);

        numberOfPairsDealer += containsPairs(dealerHand);

    }

    public void takeTurn(Hand playerHand, Hand dealerHand) {

        Casino.console.println("Take Your Turn");

        boolean goFish = true;

        int input = Casino.console.getIntegerInput("\"Pick A Card To Fish For [Enter Index Number Below Card Choice]: \"");

        while (input < 0 || input >= playerHand.getAllOfPlayerCards().size()) {
            input = Casino.console.getIntegerInput("\"Pick A Card To Fish For [Enter Index Number Below Card Choice]: \"");
        }

        for (int dealerIndex = 0; dealerIndex < dealerHand.getAllOfPlayerCards().size(); dealerIndex++) {

            if (playerHand.getAllOfPlayerCards().get(input).getValue() == dealerHand.getAllOfPlayerCards().get(dealerIndex).getValue()) {
                dealerHand.removeCard(dealerHand.getAllOfPlayerCards().get(dealerIndex));
                playerHand.addCard(playerHand.getAllOfPlayerCards().get(input));

                goFish = false;
                break;
            }
        }
        if (goFish) {
            Casino.console.println("Go Fish!!!");
            playerHand.addCard(deal());
        }

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

                dealerHand.addCard(deal());
            }
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

    public void tie(Integer numberOfPairsDealer, Integer numberOfPairsPlayer) {

        if (numberOfPairsDealer == numberOfPairsPlayer) {

            Casino.console.println("\n" +
                    "                                                            \n" +
                    "  /###           /                ###      ###      ###     \n" +
                    " /  ############/ #                ###      ###      ###    \n" +
                    "/     #########  ###                ##       ##       ##    \n" +
                    "#     /  #        #                 ##       ##       ##    \n" +
                    " ##  /  ##                          ##       ##       ##    \n" +
                    "    /  ###      ###       /##       ##       ##       ##    \n" +
                    "   ##   ##       ###     / ###      ##       ##       ##    \n" +
                    "   ##   ##        ##    /   ###     ##       ##       ##    \n" +
                    "   ##   ##        ##   ##    ###    ##       ##       ##    \n" +
                    "   ##   ##        ##   ########     ### /    ### /    ### / \n" +
                    "    ##  ##        ##   #######       ##/      ##/      ##/  \n" +
                    "     ## #      /  ##   ##                                   \n" +
                    "      ###     /   ##   ####    /     #        #        #    \n" +
                    "       ######/    ### / ######/     ###      ###      ###   \n" +
                    "         ###       ##/   #####       #        #        #    \n" +
                    "                                                            \n" +
                    "                                                            \n" +
                    "                                                            \n" +
                    "                                                            \n");
        }
    }


}
/*
    public void quitGame() {

    }
    public void takeTurn() {

    }
    public void winning() {

    }

    public void losing() {

    }
}*/




