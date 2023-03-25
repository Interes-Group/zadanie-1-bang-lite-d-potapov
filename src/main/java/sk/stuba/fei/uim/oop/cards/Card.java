package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {
    protected String name;

    protected Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void play(Player player, List<Player> players) {
        System.out.println(name + "!");
    }

    protected Player selectPlayer(Player activePlayer, List<Player> players) {
        List<Integer> indexes = new ArrayList<>();
        System.out.println("Your enemies: ");

        for (Player player : players) {
            if (player != activePlayer && player.isAlive()) {
                indexes.add(player.getId());
                System.out.print(" Player " + player.getId() + ";");
            }
        }
        System.out.println();

        int index;
        do {
            index = ZKlavesnice.readInt("Enter the id of the player you want to select: ");
            if (!indexes.contains(index)) {
                System.out.println("There is no such index!");
            }
        } while (!indexes.contains(index));

        return players.get(index - 1);
    }
}
