package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class BangGame {
    private ArrayList<Player> players;
    private Deck deck;
    public BangGame() {
        System.out.println(" ----- Welcome to Bang! -----");
        initPlayersList();
        deck = new Deck(players);
        start();
    }

    private void initPlayersList(){
        int playersCount = 0;
        while (playersCount < 2 || playersCount > 4) {
            playersCount = ZKlavesnice.readInt("Input number of players (2 - 4): ");
            if (playersCount < 2 || playersCount > 4) {
                System.out.println("Incorrect number of players! Try again.");
            }
        }

        players = new ArrayList<>();
        for (int i = 1; i <= playersCount; i++) {
            Player player = new Player(i, deck);
            players.add(player);
        }
    }

    private void start() {
        System.out.println(" ----- Game on! -----");
        Player activePlayer = players.get(0);

        while (getAlivePlayerCount() > 1){
            printPlayersStats();

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
    private void printPlayersStats(){

        System.out.println(" ---- Cards in front of each player: ---- ");

        for (Player player : players) {
            System.out.print("Player " + player.getId() + " (" + player.getLives() + " lives):");

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
