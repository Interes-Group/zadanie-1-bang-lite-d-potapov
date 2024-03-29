package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;


public class BangGame {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final Deck deck;
    private List<Player> players;

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
            playersCount = ZKlavesnice.readInt("Enter number of players (2 - 4): ");
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
        System.out.println(" --------- Game on! ---------");
        Player activePlayer = players.get(0);

        while (getAlivePlayerCount() > 1) {
            printPlayersStats();
            boolean skipMove = useFrontCardsEffect(activePlayer);
            if (!skipMove) {
                makeMove(activePlayer);
            }
            activePlayer = getNextAlivePlayer(activePlayer);
        }

        System.out.println("\n ----- End of game. The winner is player " + activePlayer.getId() + "! ----- ");
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

    private void printPlayersStats() {
        System.out.println(ANSI_YELLOW);
        System.out.println("Cards in front of each player: ");
        for (Player player : players) {
            System.out.print(" Player " + player.getId() + " (" + player.getLives() + " lives):");
            if (player.getFrontCards().isEmpty()) {
                System.out.println(" -");
            } else {
                for (BlueCard card : player.getFrontCards()) {
                    System.out.print(" " + card.getName() + " ");
                }
                System.out.println();
            }
        }
        System.out.print(ANSI_RESET);
    }

    private boolean useFrontCardsEffect(Player player) {
        System.out.println("\n - checking front cards' effects - ");
        List<BlueCard> frontCards = player.getFrontCards();
        BlueCard dynamite = null;
        BlueCard prison = null;
        for (BlueCard blueCard : frontCards) {
            if (blueCard instanceof Dynamite) {
                dynamite = blueCard;
            } else if (blueCard instanceof Prison) {
                prison = blueCard;
            }
        }

        boolean isDynamiteKillPlayer = (dynamite != null && dynamite.effect(player, players));
        boolean isPlayerInPrison = false;
        if (!isDynamiteKillPlayer) {
            isPlayerInPrison = (prison != null && prison.effect(player, players));
        }

        return isDynamiteKillPlayer || isPlayerInPrison;
    }

    private void makeMove(Player activePlayer) {
        System.out.println("\n" + ANSI_BLUE + " ----- " + activePlayer.getId() + "'s player move ----- " + ANSI_RESET);

        activePlayer.addCardInHand();
        activePlayer.addCardInHand();

        playCards(activePlayer);

        if (getAlivePlayerCount() > 1) {
            activePlayer.discardExtraCards();
            System.out.println("\n ---------------------------");
        }
    }

    private void playCards(Player activePlayer) {
        int inputedCardIndex;
        do {
            printPlayerCards(activePlayer);

            List<Card> handCards = activePlayer.getHandCards();
            inputedCardIndex = ZKlavesnice.readInt("Enter the index of the card you want to play (0 to skip): ");
            if (inputedCardIndex > 0 && inputedCardIndex <= handCards.size()) {
                handCards.get(inputedCardIndex - 1).play(activePlayer, players);
                ZKlavesnice.readString(ANSI_GREEN + "Press Enter to continue..." + ANSI_RESET);
            } else if (inputedCardIndex != 0) {
                System.out.println("There is no such index.");
            }

        } while (inputedCardIndex != 0 && getAlivePlayerCount() > 1 && !activePlayer.getHandCards().isEmpty());
    }

    private void printPlayerCards(Player player) {
        System.out.print(ANSI_BLUE);
        printFrontCards(player.getFrontCards());
        printHandCards(player.getHandCards());
        System.out.print(ANSI_RESET);
    }

    private void printFrontCards(List<BlueCard> frontCards) {
        System.out.println("Cards in front of you: ");
        if (frontCards.isEmpty()) {
            System.out.println(" no cards");
        } else {
            for (BlueCard card : frontCards) {
                System.out.print(" " + card.getName() + " ");
            }
            System.out.println();
        }
    }

    private void printHandCards(List<Card> handCards) {
        System.out.println("Cards in your hand: ");
        if (handCards.isEmpty()) {
            System.out.println(" no cards");
        } else {
            for (int i = 0; i < handCards.size(); i++) {
                System.out.print(" " + (i + 1) + ") " + handCards.get(i).getName() + " ");
            }
            System.out.println();
        }
    }

    private Player getNextAlivePlayer(Player activePlayer) {
        int nextPlayerIndex = players.indexOf(activePlayer);
        Player nextAlivePlayer;
        do {
            nextPlayerIndex++;
            nextPlayerIndex %= players.size();
            nextAlivePlayer = players.get(nextPlayerIndex);
        } while (!nextAlivePlayer.isAlive());

        return nextAlivePlayer;
    }
}
