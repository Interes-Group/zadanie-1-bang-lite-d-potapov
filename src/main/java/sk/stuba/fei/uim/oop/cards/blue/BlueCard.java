package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.List;

public abstract class BlueCard extends Card {
    public BlueCard(String name) {
        super(name);
    }

    @Override
    public void play(Player activePlayer, List<Player> players) {
        if (activePlayer.isCardInFront(this)) {
            System.out.println("The player already has the " + name + " card in front of him.");
        } else {
            System.out.println("Card " + name + " is laid out in front of player " + activePlayer.getId() + ".");
            activePlayer.addCardInFront(this);
            activePlayer.getHandCards().remove(this);
        }
    }

    public abstract boolean effect(Player activePlayer, List<Player> players);
}
