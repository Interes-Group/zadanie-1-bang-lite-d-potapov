package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;
import java.util.Random;


public class Barrel extends BlueCard {
    private static final String CARD_NAME = "Barrel";
    Random random;

    public Barrel(ArrayList<Player> players) {
        super(CARD_NAME, players);
        this.players = players;
         random = new Random();
    }

    @Override
    public void play(Player player) {
        super.play(player);
        lieInFrontOfPlayer(player);
    }

    @Override
    public boolean effect(Player player) {
        return random.nextInt(4) == 0;
    }
}
