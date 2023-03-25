package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;
import java.util.Random;

public class CatBalou extends Card {
    private static final String CARD_NAME = "Cat Balou";
    private final Random random;

    public CatBalou() {
        super(CARD_NAME);
        random = new Random();
    }

    @Override
    public void play(Player activePlayer, List<Player> players) {
        Player selectedPlayer = selectPlayer(activePlayer, players);

        List<Card> selectedPlayersHandCards = selectedPlayer.getHandCards();
        List<BlueCard> selectedPlayersFrontCards = selectedPlayer.getFrontCards();

        super.play(activePlayer, players);

        boolean isDiscardFrontCard = "1".equals(ZKlavesnice.readString("Enter \"1\" to discard a card in front of the player. " +
                "\nOtherwise, a card from the hand will be discarded. "));

        if (isDiscardFrontCard) {
            if (selectedPlayersFrontCards.isEmpty()) {
                System.out.println("Player " + selectedPlayer.getId() + " has no card's in front of him.");
                return;
            } else {
                int randomCardIndex = random.nextInt(selectedPlayersFrontCards.size());
                BlueCard randomCard = selectedPlayersFrontCards.get(randomCardIndex);
                selectedPlayer.discardCard(randomCard);
                System.out.println("Player " + activePlayer.getId() + " discards the " + randomCard.name + " card from player " + selectedPlayer.getId() + "!");
            }
        } else {
            if (selectedPlayersHandCards.isEmpty()) {
                System.out.println("Player " + selectedPlayer.getId() + " has no card's in his hands.");
                return;
            } else {
                int randomCardIndex = random.nextInt(selectedPlayersHandCards.size());
                Card randomCard = selectedPlayersHandCards.get(randomCardIndex);
                selectedPlayer.discardCard(randomCard);
                System.out.println("Player " + activePlayer.getId() + " discards the " + randomCard.name + " card from player " + selectedPlayer.getId() + "!");
            }
        }

        activePlayer.discardCard(this);
    }
}
