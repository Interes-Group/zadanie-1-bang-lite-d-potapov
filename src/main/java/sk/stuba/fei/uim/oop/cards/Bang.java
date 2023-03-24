package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";
    private ArrayList<Player> players;

    public Bang(ArrayList<Player> players){
        super(CARD_NAME, players);
        this.players = players;
    }

    @Override
    public void play(Player activePlayer) {
        super.play(activePlayer);

        ArrayList<Integer> indexes = new ArrayList<>();
        System.out.print("Your enemies: ");
        for (Player player: players){
            if (player != activePlayer && player.isAlive()) {
                indexes.add(player.getId());
                System.out.println("Player " + player.getId() + "; ");
            }
        }
        System.out.println("\n");

        int index;
        boolean isIndexValid = false;
        do {
            index = ZKlavesnice.readInt("Input the index of the player you want to attack: ");
            if (indexes.contains(index)) {
                isIndexValid = true;
            } else {
                System.out.println("There is no such index");
            }
        } while (isIndexValid);

        effect(players.get(index));
    }

    @Override
    public boolean effect(Player player) {
        BlueCard barrelCard = player.getBarrelInFront();
        Card missedCard = player.getMissedCard();

        if (barrelCard != null){
            if(barrelCard.effect(player)){
                return false;
            }
        }

        if (missedCard != null){
            missedCard.effect(player);
            return false;
        }else {
            System.out.println("Player " + player + " is shot!");
            player.loseLife();
            return true;
        }

    }
}
