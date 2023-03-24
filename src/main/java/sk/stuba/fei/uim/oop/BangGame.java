package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;


public class BangGame {
    private ArrayList<Player> players;
    private Deck deck;
    public BangGame() {
        System.out.println(" ----- Welcome to Bang! -----");
        deck = new Deck();
        initPlayersList();
        start();
    }

    private void initPlayersList() {
        players = new ArrayList<>();

        int playersCount = 0;
        while (playersCount < 2 || playersCount > 4) {
            playersCount = ZKlavesnice.readInt("Input number of players (2 - 4): ");
            if (playersCount < 2 || playersCount > 4) {
                System.out.println("Incorrect number of players! Try again.");
            }
        }
        for (int i = 1; i <= playersCount; i++) {
            Player player = new Player(i, deck);
            players.add(player);
        }
    }

    private void start() {
        System.out.println("\n ----- Game on! -----");
        Player activePlayer = players.get(0);

        while (getAlivePlayerCount() > 1){
            printPlayersStats();

            makeMove(activePlayer);
            activePlayer = getNextAlivePlayer(activePlayer);
        }
        System.out.println(" ----- End of game. The winner is " + activePlayer.getId() + " player! ----- ");
    }
    private void makeMove(Player activePlayer){
        System.out.println("\n ---- " + activePlayer.getId() + "'s player move ---- ");

        useFrontCardsEffect(activePlayer);
        if (!activePlayer.isAlive()) {
            System.out.println(" --- You died! :( ---");
            return;
        }

        activePlayer.addCardInHand();
        activePlayer.addCardInHand();
        playCards(activePlayer);
        activePlayer.discardExtraCards();
    }
    private void useFrontCardsEffect(Player player){
        for (BlueCard frontCard : player.getFrontCards()) {
            frontCard.effect(player, players);
            if (!player.isAlive()) {
                return;
            }
        }
    }
    private void playCards(Player activePlayer){
        int cardIndex;
        do {
            ArrayList<BlueCard> frontCards = activePlayer.getFrontCards();
            ArrayList<Card> handCards = activePlayer.getHandCards();

            printFrontCards(frontCards);
            printHandCards(handCards);

            cardIndex = ZKlavesnice.readInt("Input the index of the card you want to play (0 to skip): ");
            if (cardIndex < 0 || cardIndex > handCards.size()) {
                System.out.println("There is no such index.");
                continue;
            }

            if (cardIndex != 0) {
                handCards.get(cardIndex - 1).play(activePlayer, players);
                if (getAlivePlayerCount() == 1) {
                    return;
                }
            }
        } while (cardIndex != 0);
    }

    private void printFrontCards(ArrayList<BlueCard> frontCards){
        System.out.println("Cards in front of you: ");
        if (frontCards.isEmpty()){
            System.out.println(" -");
        }
        else {
            for (BlueCard card: frontCards) {
                System.out.print(card.getName() + "; ");
            }
            System.out.println("\n");
        }
    }

    private void printHandCards(ArrayList<Card> handCards){
        System.out.println("Cards in your hand: ");
        for (int i = 0; i < handCards.size(); i++) {
            System.out.print((i + 1) + ") " + handCards.get(i).getName() + "; ");
        }
        System.out.println();
    }

    private Player getNextAlivePlayer(Player activePlayer){
        int nextPlayerIndex = players.indexOf(activePlayer);
        Player nextAlivePlayer;
        do {
            nextPlayerIndex++;
            nextPlayerIndex %= players.size();
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
        System.out.println("Cards in front of each player: ");
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
    }
}
