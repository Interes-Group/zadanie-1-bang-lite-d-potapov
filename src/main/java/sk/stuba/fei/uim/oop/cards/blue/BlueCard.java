package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import java.util.ArrayList;

public abstract class BlueCard extends Card {
    public BlueCard(String name) {
        super(name);
    }

    @Override
    public void play(Player player, ArrayList<Player> players) {
        super.play(player, players);
        if (!player.isCardInFront(this)) {
            System.out.println("Card " + name + " is laid out in front of player " + player.getId() + ".");
            player.addCardInFront(this);
            player.getHandCards().remove(this);
        } else {
            System.out.println("The player already has the " + name + " card in front of him.");
        }
    }


    public boolean effect(Player player, ArrayList<Player> players) {
        return true;
    }
}
