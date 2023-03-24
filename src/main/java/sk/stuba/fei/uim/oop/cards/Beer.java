package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";

    public Beer(ArrayList<Player> players) {
        super(CARD_NAME, players);
    }
}
