package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.cards.Bang;
import sk.stuba.fei.uim.oop.cards.Missed;
import sk.stuba.fei.uim.oop.cards.Beer;
import sk.stuba.fei.uim.oop.cards.CatBalou;
import sk.stuba.fei.uim.oop.cards.Stagecoach;
import sk.stuba.fei.uim.oop.cards.Indians;


import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    final private static int BARREL_COUNT = 2;
    final private static int DYNAMITE_COUNT = 1;
    final private static int PRISON_COUNT = 3;
    final private static int BANG_COUNT = 30;
    final private static int MISSED_COUNT = 15;
    final private static int BEER_COUNT = 8;
    final private static int CAT_BALOU_COUNT = 6;
    final private static int STAGECOACH_COUNT = 4;
    final private static int INDIANS_COUNT = 2;

    private final ArrayList<Card> cardsInDeck;
    private final ArrayList<Card> cardsInDiscardPile;

    public Deck(){
        cardsInDeck = new ArrayList<>();
        cardsInDiscardPile = new ArrayList<>();
        fillDeckByDefault();
    }

    private void fillDeckByDefault(){
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
    private void shuffleDiscardPileIntoDeck(){
        cardsInDeck.addAll(cardsInDiscardPile);
        cardsInDiscardPile.clear();
        Collections.shuffle(cardsInDeck);
    }

    public Card drawCard(){
        Card card = cardsInDeck.remove(cardsInDeck.size() - 1);
        if (cardsInDeck.size() == 0) {
            shuffleDiscardPileIntoDeck();
        }
        return card;
    }

    public void addToDiscardPile(Card card){
        cardsInDiscardPile.add(card);
    }

}
