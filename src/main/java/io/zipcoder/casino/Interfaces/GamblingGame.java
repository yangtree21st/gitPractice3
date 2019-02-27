package io.zipcoder.casino.Interfaces;


import io.zipcoder.casino.Players.Player;

public interface GamblingGame extends Game {

    void receiveBetFromPlayer(Double bet);

    void giveWinningsToPlayer(Double winnings);

    Double checkPlayersBalance(Player currentPlayer);

    boolean enoughMoneyForBet(Double bet, Player currentPlayer);

}
