package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";

    public Missed(ArrayList<Player> players) {
        super(CARD_NAME, players);
    }

    @Override
    public void play(Player activePlayer) {
        super.play(activePlayer);
        System.out.println("The " + CARD_NAME + " card cannot be played!");
    }

    public void use(Player player) {
        System.out.println("Player " + player.getId() + " used " + CARD_NAME + "!");
    }
}
