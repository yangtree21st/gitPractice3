package io.zipcoder.casino.Models;

import java.util.Stack;
import static java.util.Collections.shuffle;

public class CardDeck{
    private Stack<Card> deckOfCard = new Stack<>();

    public CardDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank value : Card.Rank.values()) {
                deckOfCard.push(new Card(suit, value));
            }
        }
    }

    public Stack shuffleDeck(){
        shuffle(deckOfCard);
        return deckOfCard;
    }

    public Card peekAtTopCard(){
        return deckOfCard.peek() ;
    }

    public Card dealNextCard(){
        return deckOfCard.pop();
    }

    public Integer getCardDeckSize(){
        return deckOfCard.size();
    }

}