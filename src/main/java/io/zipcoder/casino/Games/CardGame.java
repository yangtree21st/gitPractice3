package io.zipcoder.casino.Games;

import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Players.Player;

public abstract class CardGame implements Game {

    private Player currentplayer;
    private CardDeck gameDeck= new CardDeck();


    public void shuffleDeck() {

    }

    public void collectCards() {

    }

    public Player getPlayer() {
        return null;
    }

    public void setPlayer(Player player) {

    }

    public CardDeck getDeck() {
        return gameDeck;
    }

    public abstract Card deal();

}
