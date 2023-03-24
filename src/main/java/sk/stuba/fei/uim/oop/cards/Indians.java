package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians(ArrayList<Player> players) {
        super(CARD_NAME, players);
    }

}
