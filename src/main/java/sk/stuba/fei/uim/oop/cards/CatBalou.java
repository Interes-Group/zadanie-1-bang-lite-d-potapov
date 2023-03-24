package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;
import java.util.ArrayList;

public class CatBalou extends Card {
    private static final String CARD_NAME = "Cat Balou";

    public CatBalou(ArrayList<Player> players) {
        super(CARD_NAME, players);
    }

}
