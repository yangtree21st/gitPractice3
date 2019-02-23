package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingPlayer;

public class CrapsPlayer extends DicePlayer implements GamblingPlayer {

    public CrapsPlayer(Guest newGuest){
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
