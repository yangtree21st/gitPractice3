package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingPlayer;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.Hand;

import java.util.ArrayList;

public class BlackJackPlayer extends CardPlayer implements GamblingPlayer {


    private ArrayList <Card> cardsHand = new ArrayList<Card>();

    private Hand playerhand = new Hand();

    private int result;


    public BlackJackPlayer(Guest newGuest){
        super(newGuest);
        this.result = 0;
    }

    public BlackJackPlayer() {
        this.result =0;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Guest getGuest(){
        return super.getGuest();
    }


    public Boolean takePlayersMoney(Double amount){
        Boolean result = false;
        if (this.getAccountBalance() - amount > 0.0001){
            return true;
        } else
            return result;

    }


    public Boolean givePlayerMoney(Double amount){

           this.addFunds(amount);
           return true;
    }

    public Boolean checkCurrentBalance(Double amount){
        this.checkCurrentBalance(amount);
        return true;
    }

    public Hand addCardToHand(Card cardToAdd){
        playerhand.addCard(cardToAdd);
        getHandTotal();
        return  playerhand;
    }


    public Hand getPlayerHand(){
       playerhand.getAllOfPlayerCards();
       return playerhand;
    }

    public Hand removeCardFromHand(Card cardToRemove){
        playerhand.removeCard(cardToRemove);
        getHandTotal();
        return playerhand;
}
    public Integer getHandTotal() {
        result = 0;
        int aceCounter = 0;
        for(Card card : playerhand.getAllOfPlayerCards()) {
            int score;
            String c = card.getValue().toString();
            if (c.equals("TEN") || c.equals("JACK") || c.equals("QUEEN") || c.equals("KING")) {
                c = "10";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            } else if (c.equals("ACE")) {
                if(result > 10) {
                    c = "1";
                    score = Integer.parseInt(c);
                    result += score;
                } else {
                    c = "11";
                    score = Integer.parseInt(c);
                    result += score;
                    aceCounter++;
                }
            }// need to make a choice for two int value which are 1 and 11;
            else if (c.equals("TWO")) {
                c = "2";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            } else if (c.equals("THREE")) {
                c = "3";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            } else if (c.equals("FOUR")) {
                c = "4";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            } else if (c.equals("FIVE")) {
                c = "5";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            } else if (c.equals("SIX")) {
                c = "6";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            } else if (c.equals("SEVEN")) {
                c = "7";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            } else if (c.equals("EIGHT")) {
                c = "8";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            } else if (c.equals("NINE")) {
                c = "9";
                score = Integer.parseInt(c);
                result += score;
//                return result;
            }

        }
        if(result > 21){
            for(int i = 0 ; i < aceCounter ; i++){
                result -= 10;
            }
        }
           return result;
        }

    }







