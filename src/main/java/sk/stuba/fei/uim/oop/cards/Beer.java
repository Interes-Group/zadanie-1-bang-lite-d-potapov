package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";

    public Beer() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, List<Player> players) {
        super.play(activePlayer, players);
        System.out.println("Player " + activePlayer.getId() + " adds a life!");
        activePlayer.addLife();
        activePlayer.discardCard(this);
    }
}
