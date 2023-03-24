package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.Card;
import java.util.ArrayList;

public abstract class BlueCard extends Card {
    public BlueCard(String name, ArrayList<Player> players) {
        super(name, players);
    }

    @Override
    public void play(Player activePlayer) {
        super.play(activePlayer);
    }


    public boolean effect(Player player) {
        return true;
    }

    protected void lieInFrontOfPlayer(Player player){
        if (!player.isCardInFront(this)) {
            player.addCardInFront(this);
            player.getHandCards().remove(this);
        } else {
            System.out.println("The player already have this card in front of him");
        }
    }
}
