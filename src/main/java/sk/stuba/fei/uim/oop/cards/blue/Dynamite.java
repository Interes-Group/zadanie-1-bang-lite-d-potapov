package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.Player;

import java.util.Random;
import java.util.ArrayList;

public class Dynamite extends BlueCard {
    private static final String CARD_NAME = "Dynamite";
    private Random random;


    public Dynamite() {
        super(CARD_NAME);
        random = new Random();
    }

    @Override
    public boolean effect(Player activePlayer, ArrayList<Player> players) {
        boolean successExplode = (random.nextInt(8) == 0);
        if (successExplode) {
            System.out.println("Dynamite blew up player " + activePlayer.getId() + "!");
            for (int i = 0; i < 3; i++){
                activePlayer.loseLife();
            }
            activePlayer.discardCard(this);
            return true;
        }

        Player previousPlayer = getPreviousPlayer(players.indexOf(activePlayer), players);
        System.out.println("Dynamite did not explode, goes to player " + previousPlayer.getId() + "!");
        previousPlayer.addCardInFront(this);
        return false;
    }

    private Player getPreviousPlayer(int activePlayerIdx, ArrayList<Player> players){
        int prevPlayerIdx = activePlayerIdx;
        do {
            prevPlayerIdx = (prevPlayerIdx - 1 < 0) ? players.size() - 1 : prevPlayerIdx - 1;
        } while (!players.get(prevPlayerIdx).isAlive());
        return players.get(prevPlayerIdx);
    }
}
