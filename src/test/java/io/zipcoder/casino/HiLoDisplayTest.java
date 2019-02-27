package io.zipcoder.casino;

import io.zipcoder.casino.Games.HiLo;
import io.zipcoder.casino.Models.GuestAccount;
//import org.testfx.framework.junit.ApplicationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HiLoDisplayTest {

    Display display;
    HiLoDisplay hiLoDisplay;
    GuestAccount guestAccount;
    Guest guest;
    HiLo hiLoGame;
    Stage stage;


    @Before
    public void setup(){
        display = new Display();
        hiLoDisplay = new HiLoDisplay();
        guestAccount = new GuestAccount("Marshilla",1,1000.0);
        guest = new Guest("Marshilla",guestAccount);
        hiLoGame = new HiLo(guest);
        hiLoDisplay.createHiLoContent(hiLoGame);

    }

    @Test
    public void constructorTest(){

    }

    @Test
    public void returnStringInstructionsTest(){
        //given
        String expected = "Hi-Lo, or High-Low, is a fairly simple card game. It uses a standard deck of 52 cards,\n" +
                "and it has players guess whether a certain card is higher or lower than one showing on the table.\n" +
                "There are variations for drinking and gambling, but it's a pretty standard and simple game.\n\n" +

                "Technically, the game can have any number of players, but at any given time,\n" +
                "there are only two players actually playing. The dealer, who is in control of the deck,\n" +
                "and the player, who is responsible for guessing the values of cards, are the only participants.\n\n" +

                "After shuffling and cutting the deck, the dealer places one card face-down in front of the player, " +
                "then another card face-up.The rest of the deck, they set aside while the player guesses the value.\n\n" +

                "After the cards are down, the player places his initial bet. The house matches that bet into the pot.\n"+
                "When the player guesses, he wins or loses the pot depending on the outcome of his guess.\n" +
                "After that round, the player can pass the bet to another player, or go double or nothing on the next bet\n";
        //when
        String actual = hiLoDisplay.returnInstructions();
        //then
        Assert.assertEquals(expected,actual);

    }
}
