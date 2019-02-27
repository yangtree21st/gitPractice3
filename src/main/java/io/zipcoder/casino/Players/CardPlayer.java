package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;

public abstract class CardPlayer extends Player {

    public CardPlayer(Guest newGuest) {
        super(newGuest);
    }

    public CardPlayer() {
    }

    public Guest getGuest() {
        return super.getGuest();
    }

    public void setGuest(Guest guest) {
        super.setGuest(guest);
    }
}
