package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;

public abstract class Player{


    public  Guest guest;

    public Player(Guest guest){
        this.guest = guest;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void addFunds(Double amount){
        guest.addFunds(amount);
    }
    public void removeFunds(Double amount){
        guest.removeFunds(amount);
    }
    public Double getAccountBalance(){
        return guest.getAccountBalance();
    }
}
