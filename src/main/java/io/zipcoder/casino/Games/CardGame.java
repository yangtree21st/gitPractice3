package io.zipcoder.casino.Games;

import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;
import io.zipcoder.casino.Models.CardDeck;
import io.zipcoder.casino.Players.Player;

public abstract class CardGame implements Game {

    public abstract Card deal();

}
