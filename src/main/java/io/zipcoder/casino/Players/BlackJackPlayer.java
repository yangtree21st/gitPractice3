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



    }



