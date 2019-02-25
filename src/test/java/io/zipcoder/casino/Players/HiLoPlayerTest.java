package io.zipcoder.casino.Players;

import io.zipcoder.casino.Guest;
import io.zipcoder.casino.Models.GuestAccount;
import io.zipcoder.casino.Players.HiLowPlayer;
import org.junit.Assert;
import org.junit.Test;

public class HiLoPlayerTest {

    @Test
    public void HiloPlayerTest(){
        //Given
        GuestAccount guestAccount=new GuestAccount("Bella", null,100.0);
        Guest expected = new Guest("Bella",guestAccount);
        HiLowPlayer hiloPlayer = new HiLowPlayer(expected);

        //When
        Guest actual = hiloPlayer.getGuest();

        //Then
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void takePlayersMoneyTest(){
        //Given
        GuestAccount guestAccount=new GuestAccount("Bella", null,100.0);
        Guest expected = new Guest("Bella",guestAccount);
        HiLowPlayer hiloPlayer = new HiLowPlayer(expected);



        //When




        //THen
    }

}
