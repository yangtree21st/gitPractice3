package io.zipcoder.casino.Interfaces;

public interface GamblingPlayer {

    public Boolean takePlayersMoney(Double amount);
    public Boolean givePlayerMoney(Double amount);
    public Boolean checkCurrentBalance(Double amount);

}
