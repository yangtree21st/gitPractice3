package io.zipcoder.casino.Games;
import io.zipcoder.casino.Casino;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingGame;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Models.Hand;
import io.zipcoder.casino.Players.HiLowPlayer;
import io.zipcoder.casino.Players.Player;


import java.util.HashMap;

public class HiLo extends CardGame implements GamblingGame {

    private Card currentCard;
    private Card nextCard;
    private CardDeck cardDeck;
    private Guest guest;
    private HiLowPlayer hiloPlayer;
    private String playerChoice;
    private Double bet;
    private Double getAccountBalance;
    private boolean continueGame;
    private Double minimumBet;
    private Double winnings;


    public HiLo(){
        this.cardDeck = new CardDeck();
        cardDeck.shuffleDeck();


    }

    public HiLo(Guest guest){
        hiloPlayer = new HiLowPlayer(guest);
        this.cardDeck = new CardDeck();
        cardDeck.shuffleDeck();

    }
    public CardDeck getDeck() {
        return cardDeck;
    }

    public Card deal() {

        currentCard = cardDeck.dealNextCard();

        if (currentCard == null) {
            currentCard = cardDeck.dealNextCard();
            return currentCard;
        } else {
            return nextCard =cardDeck.dealNextCard();
        }
    }



    public void discard(){
        if(nextCard!=null){
        currentCard =nextCard;}


    }
//    public Integer getCardValue(Card card){
//        String[] cards = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN" , "KING"};
////      Integer value = currentCard.getValue().ordinal()+1;
//        HashMap<String,Integer> cardValue = new HashMap<>();
//        for (int i = 0; i <cards.length ; i++) {
//            cardValue.put(cards[i],i+1);
//
//        }
//
//        return cardValue.get(card.getValue().toString());
//    }


    public boolean isLess(Card currentCard, Card nextCard) {
        Integer currentCardValue = currentCard.getValue().ordinal()+1;
        Integer nextCardValue = nextCard.getValue().ordinal()+1;

        if (nextCardValue<currentCardValue){

        return true;
        }
        return false;
    }


    public boolean isMore(Card currentCard, Card nextCard) {

        return !isLess(currentCard,nextCard);
    }

    public void playFullGame() {
        this.continueGame = true;
        setUp();

        while (continueGame){
            //stuff
            quitGame();
        }
        Casino.console.println("You have played a full game of Hi-Lo!");
    }

    public void setUp() {

        Casino.console.println("WELCOME TO HI-LO GAME");

    }


    public Double checkPlayersBalance(Player currentPlayer) {
        getAccountBalance = currentPlayer.getAccountBalance();
        return getAccountBalance;
    }

    public boolean enoughMoneyForBet(Double bet, Player currentPlayer) {
        if ((checkPlayersBalance(currentPlayer) >= minimumBet) && (checkPlayersBalance(currentPlayer) >= bet)) {
            return true;
        }
        return false;
    }



    public void receiveBetFromPlayer(Double bet) {
        this.bet = Casino.console.getDoubleInput("Please enter your bet:");
            if(enoughMoneyForBet(bet,hiloPlayer)){
            guest.removeFunds(bet);
        }
            this.continueGame =false;
    }

    public void takeTurn() {

        this.playerChoice =Casino.console.getStringInput("Enter 'H' for Higher,'L' for Low");


    }
    public void updateDisplay() {
        deal();


    }


    public void winning() {
        if(playerChoice.equals("H")&&isMore(currentCard,nextCard)){
            Casino.console.println("You Win");
            giveWinningsToPlayer(winnings);
        }
        else if(playerChoice.equals("L")&&isLess(currentCard,nextCard)){
            Casino.console.println("You Win");
            giveWinningsToPlayer(winnings);

        }

    }

    public void losing() {
        if(playerChoice.equals("H")&&!isMore(currentCard,nextCard)){
            Casino.console.println("You Lose");
        }
        else if(playerChoice.equals("L")&&!isLess(currentCard,nextCard)){
            Casino.console.println("You Lose");

        }

    }

    public void giveWinningsToPlayer(Double winnings) {

        winnings = bet*1.25;


    }

    public void quitGame() {
        this.playerChoice =
                Casino.console.getStringInput("Do you wish to continue, Enter 'Y' for Yes, 'N' for No");

        if (playerChoice.equalsIgnoreCase("N")){
            this.continueGame = false;

        }if(playerChoice.equalsIgnoreCase("Y")){
            this.continueGame = true;
    }
    }




}
