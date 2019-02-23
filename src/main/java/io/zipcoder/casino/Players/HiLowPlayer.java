package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingPlayer;

public class HiLowPlayer extends CardPlayer implements GamblingPlayer {

    public HiLowPlayer(Guest newGuest){
        super(newGuest);
    }

    public Guest getGuest(){
        return super.getGuest();
    }

    public Boolean takePlayersMoney(Double amount){
        return false;
    }
    public Boolean givePlayerMoney(Double amount){
        return false;
    }
    public Boolean checkCurrentBalance(Double amount){
        return false;
    }
}
