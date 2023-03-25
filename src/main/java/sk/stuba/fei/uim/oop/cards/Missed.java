package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";

    public Missed() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, List<Player> players) {
        System.out.println("The " + CARD_NAME + " card cannot be played!");
    }

    public void effect(Player player) {
        System.out.println("Player " + player.getId() + " uses " + CARD_NAME + "! And saves his life.");
        player.discardCard(this);
    }
}
