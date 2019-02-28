package io.zipcoder.casino;

import io.zipcoder.casino.Games.BlackJack;
import io.zipcoder.casino.Games.Craps;
import io.zipcoder.casino.Games.GoFish;
import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Interfaces.Game;

import java.util.function.Function;
import java.util.function.Supplier;

public enum GameEnum {

    BLACKJACK(BlackJack::new),
    CRAPS(Craps::new),
    GOFISH(GoFish::new),
    HILO(HiLo::new);

    private final Function<Guest,Game> function;

    GameEnum(Function<Guest,Game> function){this.function = function;};

    public Game create(Guest guest) {
        return function.apply(guest);
    }

    public static GameEnum getValueOf(String userInput) {
        userInput = userInput.toUpperCase();
        try {
            return valueOf(userInput);
        } catch (IllegalArgumentException iae) {
            return valueOf(userInput.replaceAll(" ", "_"));
        }
    }
}
