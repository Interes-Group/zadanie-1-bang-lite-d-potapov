package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private final Deck deck;
    public Game() {
        System.out.println(" ----- Welcome to Bang! -----");
        deck = new Deck();
        initPlayerList();
        start();
    }

    private void initPlayerList(){
        int playersCount = 0;
        while (playersCount < 2 || playersCount > 4) {
            playersCount = ZKlavesnice.readInt("Input number of players (2 - 4): ");
            if (playersCount < 2 || playersCount > 4) {
                System.out.println("Incorrect number of players! Try again.");
            }
        }

        this.players = new ArrayList<>();
        for (int i = 1; i <= playersCount; i++) {
            Player player = new Player(i, deck);
            players.add(player);
        }
    }

    private void start() {
        System.out.println(" ----- Game on! -----");
        Player activePlayer = players.get(0);

        while (getAlivePlayerCount() > 1){
            printPlayersStats(activePlayer);

            activePlayer.makeMove();
            activePlayer = getNextAlivePlayer(activePlayer);
        }
    }

    private Player getNextAlivePlayer(Player activePlayer){
        int nextPlayerIndex = players.indexOf(activePlayer);
        Player nextAlivePlayer;

        do {
            nextPlayerIndex++;
            if (nextPlayerIndex == players.size()) {
                nextPlayerIndex = 0;
            }
            nextAlivePlayer = players.get(nextPlayerIndex);
        } while (!nextAlivePlayer.isAlive());

        return nextAlivePlayer;
    }
    private int getAlivePlayerCount() {
        int count = 0;
        for (Player player : this.players) {
            if (player.isAlive()) {
                count++;
            }
        }
        return count;
    }
    private void printPlayersStats(Player activePlayer){
        System.out.println("Cards in front of each player:");

        for (Player player : players) {
            if (player == activePlayer) {
                System.out.print("You (" + player.getLives() + " lives):");
            } else {
                System.out.print(player.getId() + " player (" + player.getLives() + " lives):");
            }
            if (player.getFrontCards().size() == 0) {
                System.out.println(" -");
            } else {
                for (BlueCard card : player.getFrontCards()) {
                    System.out.print(" " + card.getName());
                }
                System.out.println("\n");
            }
        }
        System.out.println("\n");
    }

}
