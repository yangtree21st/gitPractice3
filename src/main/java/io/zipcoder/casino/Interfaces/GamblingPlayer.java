package io.zipcoder.casino.Interfaces;

public interface GamblingPlayer {

    Boolean takePlayersMoney(Double amount);

    Boolean givePlayerMoney(Double amount);

    Boolean checkCurrentBalance(Double amount);

}
