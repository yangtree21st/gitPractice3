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


    public Boolean takePlayersMoney(Double amount){
        return null;
    }
    public Boolean givePlayerMoney(Double amount){
        return null;
    }
    public Boolean checkCurrentBalance(Double amount){return null;
    }

}
