package io.zipcoder.casino.Players;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Interfaces.GamblingPlayer;

public class CrapsPlayer extends DicePlayer implements GamblingPlayer {

    public CrapsPlayer(Guest newGuest){
        super(newGuest);
    }

    public Guest getGuest(){
        return super.getGuest();
    }


    public Boolean takePlayersMoney(Double amount){
        return null;
    }
    public Boolean givePlayerMoney(Double amount){
        return null;
    }
    public Boolean checkCurrentBalance(Double amount){
        return null;
    }
}
