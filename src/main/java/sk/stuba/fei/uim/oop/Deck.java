package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final static int BARREL_COUNT = 2;
    private final static int DYNAMITE_COUNT = 1;
    private final static int PRISON_COUNT = 3;
    private final static int BANG_COUNT = 30;
    private final static int MISSED_COUNT = 15;
    private final static int BEER_COUNT = 8;
    private final static int CAT_BALOU_COUNT = 6;
    private final static int STAGECOACH_COUNT = 4;
    private final static int INDIANS_COUNT = 2;

    private final List<Card> cardsInDeck;
    private final List<Card> cardsInDiscardPile;

    public Deck() {
        cardsInDeck = new ArrayList<>();
        cardsInDiscardPile = new ArrayList<>();

        for (int i = 0; i < BARREL_COUNT; i++)
            cardsInDeck.add(new Barrel());
        for (int i = 0; i < DYNAMITE_COUNT; i++)
            cardsInDeck.add(new Dynamite());
        for (int i = 0; i < PRISON_COUNT; i++)
            cardsInDeck.add(new Prison());
        for (int i = 0; i < BANG_COUNT; i++)
            cardsInDeck.add(new Bang());
        for (int i = 0; i < MISSED_COUNT; i++)
            cardsInDeck.add(new Missed());
        for (int i = 0; i < BEER_COUNT; i++)
            cardsInDeck.add(new Beer());
        for (int i = 0; i < CAT_BALOU_COUNT; i++)
            cardsInDeck.add(new CatBalou());
        for (int i = 0; i < STAGECOACH_COUNT; i++)
            cardsInDeck.add(new Stagecoach());
        for (int i = 0; i < INDIANS_COUNT; i++)
            cardsInDeck.add(new Indians());

        Collections.shuffle(cardsInDeck);
    }

    public void addToDiscardPile(Card card) {
        cardsInDiscardPile.add(card);
    }

    public void addToDiscardPile(List<Card> handCards, List<BlueCard> frontCards) {
        cardsInDiscardPile.addAll(handCards);
        cardsInDiscardPile.addAll(frontCards);
        handCards.clear();
        frontCards.clear();
    }

    public Card drawCard() {
        if (cardsInDeck.isEmpty() && reshuffleCards()) {
            return null;
        }
        return cardsInDeck.remove(cardsInDeck.size() - 1);
    }

    private boolean reshuffleCards() {
        cardsInDeck.addAll(cardsInDiscardPile);
        cardsInDiscardPile.clear();
        Collections.shuffle(cardsInDeck);
        return cardsInDeck.isEmpty();
    }
}
