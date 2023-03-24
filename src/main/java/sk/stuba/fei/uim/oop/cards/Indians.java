package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> players) {
        super.play(activePlayer, players);
        for (Player player: players) {
            if (player != activePlayer){
                for (Card card: player.getHandCards()) {
                    if (card instanceof Bang){
                        System.out.println("Player " + player.getId() + " discards the Bang card!");
                        player.discardCard(card);
                        return;
                    }
                }
                System.out.println("Player " + player.getId() + " loses life!");
                player.loseLife();
            }
        }
        activePlayer.discardCard(this);
    }
}
