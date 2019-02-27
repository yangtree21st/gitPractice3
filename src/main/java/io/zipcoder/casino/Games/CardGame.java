package io.zipcoder.casino.Games;

import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Models.Card;

public abstract class CardGame implements Game {

    public abstract Card deal();

}
