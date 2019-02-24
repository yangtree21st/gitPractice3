package io.zipcoder.casino.Players;

import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingPlayer;

public class HiLowPlayer extends CardPlayer implements GamblingPlayer {

    Double amount;


    public HiLowPlayer(Guest newGuest){
        super(newGuest);
    }

    public Guest getGuest(){
        return super.getGuest();
    }

    public Boolean takePlayersMoney(Double amount){
        super.getGuest().removeFunds(amount);
        return true;
    }
    public Boolean givePlayerMoney(Double amount){
        super.getGuest().addFunds(amount);
        return true;
    }
    public Boolean checkCurrentBalance(Double amount){
       if( getAccountBalance() >= amount){
           return true;
       }
        return false;
    }
}
