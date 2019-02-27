package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.Hand;

public class GoFishPlayer extends CardPlayer {

    private Hand playerHand;

    public GoFishPlayer(Guest newGuest) {
        super(newGuest);
        this.playerHand = new Hand();
    }

    public Guest getGuest() {
        return super.getGuest();
    }

    public Hand getPlayerHand() {
        return playerHand;
    }
}

