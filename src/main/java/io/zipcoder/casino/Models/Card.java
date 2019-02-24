package io.zipcoder.casino.Models;

public class Card {




    public enum Suit {HEARTS,SPADES,DIAMONDS,CLUBS};
    public enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN , KING};

    protected Suit cardSuit;
    protected Rank value;



    public Card() {

    }

    public Card(Suit cardSuit, Rank value) {
        this.cardSuit = cardSuit;
        this.value = value;
    }


    public Rank getValue() {

        return value;
    }


    public Suit getCardSuit() {

        return cardSuit;
    }

    public Suit setCardSuit(Suit cardSuit) {
        this.cardSuit = cardSuit;
        return cardSuit;
    }


    public Rank setValue(Rank value) {
        this.value = value;
        return value;
    }



    public String toStringCard(){
        String cardS= cardSuit.toString( );
        String cardV= value.toString();
        return String.format("\n %10s \n %10s",cardS,cardV);

    }




}
