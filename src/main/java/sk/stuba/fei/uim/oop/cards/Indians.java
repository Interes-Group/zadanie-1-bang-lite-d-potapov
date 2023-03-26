package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, List<Player> players) {
        super.play(activePlayer, players);
        for (Player player : players) {
            if (player != activePlayer && player.isAlive()) {
                Card bang = player.getBangInCards();
                if (bang != null) {
                    System.out.println("Player " + player.getId() + " discards the Bang card!");
                    player.discardCard(bang);
                } else {
                    System.out.println("Player " + player.getId() + " loses life!");
                    player.loseLife();
                }
            }
        }
        activePlayer.discardCard(this);
    }
}
