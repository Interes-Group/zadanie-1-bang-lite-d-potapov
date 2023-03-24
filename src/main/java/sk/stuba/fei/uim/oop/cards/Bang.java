package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;

import java.util.ArrayList;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";

    public Bang(){
        super(CARD_NAME);
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> players) {
        super.play(activePlayer, players);

        Player selectedPlayer = selectPlayer(activePlayer, players);
        BlueCard barrelCard = selectedPlayer.getBarrelInFront();
        Missed missedCard = selectedPlayer.getMissedCard();

        if (barrelCard == null || !barrelCard.effect(selectedPlayer, players)){
            if (missedCard == null) {
                System.out.println("Player " + activePlayer.getId() + " shoots player " + selectedPlayer.getId() + "!");
                selectedPlayer.loseLife();
            } else {
                missedCard.effect(selectedPlayer);
            }
        }

        activePlayer.discardCard(this);
    }
}
