package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public class Dynamite extends BlueCard {
    private static final String CARD_NAME = "Dynamite";
    private final Random random;

    public Dynamite() {
        super(CARD_NAME);
        random = new Random();
    }

    @Override
    public boolean effect(Player activePlayer, List<Player> players) {
        boolean successExplode = (random.nextInt(8) == 0);

        if (successExplode) {
            System.out.println("Dynamite blew up player " + activePlayer.getId() + "!");
            activePlayer.discardCard(this);
            for (int i = 0; i < 3; i++) {
                activePlayer.loseLife();
                if (!activePlayer.isAlive()) {
                    return true;
                }
            }
        } else {
            Player previousPlayer = getPreviousPlayer(players.indexOf(activePlayer), players);
            System.out.println("Dynamite did not explode, goes to player " + previousPlayer.getId() + "!");
            previousPlayer.addCardInFront(this);
            activePlayer.getFrontCards().remove(this);
        }
        return false;
    }

    private Player getPreviousPlayer(int activePlayerIdx, List<Player> players) {
        int prevPlayerIdx = activePlayerIdx;
        do {
            prevPlayerIdx = (prevPlayerIdx - 1 < 0) ? players.size() - 1 : prevPlayerIdx - 1;
        } while (!players.get(prevPlayerIdx).isAlive());
        return players.get(prevPlayerIdx);
    }
}
