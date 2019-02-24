package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingPlayer;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.Hand;

import java.util.ArrayList;

public class BlackJackPlayer extends CardPlayer implements GamblingPlayer {


    private ArrayList <Card> cardsHand = new ArrayList<Card>();

    private Hand playerhand = new Hand(cardsHand);



    public BlackJackPlayer(Guest newGuest){
        super(newGuest);
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
        return  playerhand;
    }


    public Hand getPlayerHand(){
       playerhand.getPlayerHand();
       return playerhand;
    }

    public Hand removeCardFromHand(Card cardToRemove){
        playerhand.removeCard(cardToRemove);
        return playerhand;
}
    public Integer getHandTotal() {


        int result=0;

        for(Card card : cardsHand) {
            String c = card.getValue().toString();
            if (c.equals("TEN") || c.equals("JACK") || c.equals("QUEEN") || c.equals("KING")) {
                c = "10";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;
            } else if (c.equals("ACE")) {
                return null;
            }// need to make a choice for two int value which are 1 and 11;
            else if (c.equals("TWO")) {
                c = "2";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;
            } else if (c.equals("THREE")) {
                c = "3";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;
            } else if (c.equals("FOUR")) {
                c = "4";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;
            } else if (c.equals("FIVE")) {
                c = "5";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;
            } else if (c.equals("SIX")) {
                c = "6";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;
            } else if (c.equals("SEVEN")) {
                c = "7";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;

            } else if (c.equals("EIGHT")) {
                c = "8";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;
            } else if (c.equals("NINE")) {
                c = "9";
                int Score = Integer.parseInt(c);
                result += Score;
                return result;
            }

        }
           return result;
        }

    }







