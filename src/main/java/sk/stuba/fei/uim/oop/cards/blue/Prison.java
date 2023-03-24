package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;
import java.util.Random;

public class Prison extends BlueCard {
    private static final String CARD_NAME = "Prison";
    private Random random;


    public Prison() {
        super(CARD_NAME);
        random = new Random();
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> players) {
        Player selectedPlayer = selectPlayer(activePlayer, players);
        super.play(selectedPlayer, players);
    }

    @Override
    public boolean effect(Player player, ArrayList<Player> players) {
        boolean successEscape = (random.nextInt(4) == 0);
        player.discardCard(this);
        if (successEscape) {
            System.out.println("Player " + player.getId() + " escaped from the prison!");
            return true;
        }
        System.out.println("Player " + player.getId() + " fails to escape from prison. Player skips a turn!");
        return false;
    }
}
