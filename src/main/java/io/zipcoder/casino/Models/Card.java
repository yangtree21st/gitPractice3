package io.zipcoder.casino.Models;

public class Card {




    public enum Suit {HEARTS,SPADES,DIAMONDS,CLUBS}
    public enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN , KING}

    protected Suit cardSuit;
    protected Rank value;



    public Card() {}

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

    public void setCardSuit(Suit cardSuit) {
        this.cardSuit = cardSuit;
    }


    public void setValue(Rank value) {
        this.value = value;
    }

    
    /**
     * This method creates a string representation of a card that looks like this:
     * --------------
     * |A           |
     * |♥           |
     * |            |
     * |     A♥     |
     * |            |
     * |           ♥|
     * |           A|
     * --------------
     * @return the string representation of a card
     */
    public String toString() {
        return boundaryLineOfCard() +
                firstRankLineOfCard() +
                firstSuitLineOfCard() +
                emptyLineOfCard() +
                middleLineOfCard() +
                emptyLineOfCard() +
                secondSuitLineOfCard() +
                secondRankLineOfCard() +
                boundaryLineOfCard();
    }

    private String boundaryLineOfCard() {
        return "--------------\n";
    }

    private String firstRankLineOfCard() {
        if (this.value == Rank.TEN){
            return "|10          |\n";
        } else {
            return String.format("|%s           |\n", this.getRankAsString());
        }
    }

    private String secondRankLineOfCard() {
        if (this.value == Rank.TEN){
            return "|          10|\n";
        } else {
            return String.format("|           %s|\n", this.getRankAsString());
        }
    }

    private String firstSuitLineOfCard() {
        return String.format("|%c           |\n", this.getSuitAsChar());
    }

    private String secondSuitLineOfCard() {
        return String.format("|           %c|\n", this.getSuitAsChar());
    }

    private String emptyLineOfCard() {
        return "|            |\n";
    }

    private String middleLineOfCard() {
        if (this.value == Rank.TEN){
            return String.format("|    10%c     |\n", this.getSuitAsChar());
        } else {
            return String.format("|     %s%c     |\n", this.getRankAsString(), this.getSuitAsChar());
        }
    }





    private String getRankAsString() {
        switch (this.value) {
            case ACE:
                return "A";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            default:
                return "";
        }
    }

    private char getSuitAsChar() {
        switch (this.cardSuit) {
            case SPADES:
                return '\u2660';
            case HEARTS:
                return '\u2665';
            case CLUBS:
                return '\u2663';
            case DIAMONDS:
                return '\u2666';
            default:
                return ' ';
        }
    }

    public String toStringCard(){
        String cardS= cardSuit.toString();
        String cardV= value.toString();
        return String.format("\n %10s \n %10s",cardS,cardV);

    }

    public static void main(String[] args) {
        Card testCard = new Card(Suit.SPADES, Rank.THREE);
        Card testCard2 = new Card(Suit.HEARTS, Rank.ACE);
        Card testCard3 = new Card(Suit.DIAMONDS, Rank.TEN);
        System.out.println(testCard);
        System.out.println(testCard2);
        System.out.println(testCard3);
    }


}
