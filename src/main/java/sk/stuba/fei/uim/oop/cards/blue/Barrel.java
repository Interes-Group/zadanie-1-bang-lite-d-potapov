package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;
import java.util.Random;


public class Barrel extends BlueCard {
    private static final String CARD_NAME = "Barrel";
    private Random random;

    public Barrel() {
        super(CARD_NAME);
         random = new Random();
    }

    @Override
    public boolean effect(Player activePlayer, ArrayList<Player> players) {
        boolean successSave = (random.nextInt(4) == 0);
        if (successSave) {
            System.out.println("The barrel saved player" + activePlayer.getId() + "!");
            return true;
        }
        System.out.println("The barrel did not save player " + activePlayer.getId() + "! :(");
        return false;
    }
}
