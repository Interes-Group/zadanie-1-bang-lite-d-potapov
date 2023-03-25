package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagecoach";

    public Stagecoach() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, List<Player> players) {
        super.play(activePlayer, players);
        if (activePlayer.addTwoCardsOrNone()) {
            System.out.println("Player " + activePlayer.getId() + " draws 2 cards!");
            activePlayer.discardCard(this);
        } else {
            System.out.println("The " + CARD_NAME + " card cannot be played! There are not enough cards in the deck.");
        }
    }
}
