package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Random;

public class CatBalou extends Card {
    private static final String CARD_NAME = "Cat Balou";
    private Random random;

    public CatBalou() {
        super(CARD_NAME);
        random = new Random();
    }

    @Override
    public void play(Player activePlayer, ArrayList<Player> players) {
        super.play(activePlayer, players);
        Player selectedPlayer = selectPlayer(activePlayer, players);
        ArrayList<Card> handCards = selectedPlayer.getHandCards();
        ArrayList<BlueCard> frontCards = selectedPlayer.getFrontCards();

        int discardPlace;
        do {
            discardPlace = ZKlavesnice.readInt("Do you want discard from his hand(1) or from the table(2)? ");
        } while (discardPlace != 1 && discardPlace != 2);

        Card randomCard;
        if (discardPlace == 1 && handCards.size() > 0){
            int randomCardIndex = random.nextInt(handCards.size());
            randomCard = handCards.get(randomCardIndex);
            selectedPlayer.discardCard(randomCard);
        } else if (discardPlace == 2 && frontCards.size() > 0){
            int randomCardIndex = random.nextInt(frontCards.size());
            randomCard = frontCards.get(randomCardIndex);
            selectedPlayer.discardCard(randomCard);
        } else {
            System.out.println("Player " + selectedPlayer.getId() + " has no card's there.");
            return;
        }
        System.out.println("Player " + activePlayer.getId() + " discards the " +
                 randomCard.name + " card from player " + selectedPlayer.getId() + "!");
        activePlayer.discardCard(this);
    }
}
