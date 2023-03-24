package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;

import java.util.Random;
import java.util.ArrayList;

public class Dynamite extends BlueCard {
    private static final String CARD_NAME = "Dynamite";
    Random random;


    public Dynamite(ArrayList<Player> players) {
        super(CARD_NAME, players);

        random = new Random();
    }
    @Override
    public void play(Player player) {
        super.play(player);
        lieInFrontOfPlayer(player);
    }
    @Override
    public boolean effect(Player player) {
        return random.nextInt(8) == 0;
    }
}
