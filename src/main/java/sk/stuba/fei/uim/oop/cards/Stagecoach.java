package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagecoach";

    public Stagecoach(ArrayList<Player> players) {
        super(CARD_NAME, players);
    }

}
