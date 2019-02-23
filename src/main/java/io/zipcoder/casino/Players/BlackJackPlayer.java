package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingPlayer;
import io.zipcoder.casino.Models.Hand;

public class BlackJackPlayer extends CardPlayer implements GamblingPlayer {

    private Hand playerHand;
    public BlackJackPlayer(Guest newGuest){
        super(newGuest);
    }

    public Guest getGuest(){
        return super.getGuest();
    }


    public Double takePlayersMoney(Double amount){
        return null;
    }
    public Double givePlayerMoney(Double amount){
        return null;
    }
    public Double checkCurrentBalance(){
        return null;
    }

}
