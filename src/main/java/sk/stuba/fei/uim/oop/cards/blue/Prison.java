package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public class Prison extends BlueCard {
    private static final String CARD_NAME = "Prison";
    private final Random random;

    public Prison() {
        super(CARD_NAME);
        random = new Random();
    }

    @Override
    public void play(Player activePlayer, List<Player> players) {
        Player selectedPlayer = selectPlayer(activePlayer, players);
        if (selectedPlayer.isCardInFront(this)) {
            System.out.println("The player already has the " + CARD_NAME + " card in front of him.");
        } else {
            System.out.println("Card " + CARD_NAME + " is laid out in front of player " + selectedPlayer.getId() + ".");
            selectedPlayer.addCardInFront(this);
            activePlayer.getHandCards().remove(this);
        }
    }

    @Override
    public boolean effect(Player player, List<Player> players) {
        boolean successEscape = (random.nextInt(4) == 0);
        player.discardCard(this);
        if (successEscape) {
            System.out.println("Player " + player.getId() + " escaped from the prison!");
            return false;
        }
        System.out.println("Player " + player.getId() + " fails to escape from prison. Player skips a turn!");
        return true;
    }
}
