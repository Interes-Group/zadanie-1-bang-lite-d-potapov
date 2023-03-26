package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Bang;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.Missed;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Player {
    private final int id;
    private final Deck deck;
    private final Random random;
    private final List<Card> handCards;
    private final List<BlueCard> frontCards;
    private int lives;

    public Player(int id, Deck deck) {
        this.id = id;
        this.deck = deck;
        random = new Random();
        handCards = new ArrayList<>();
        frontCards = new ArrayList<>();
        lives = 4;

        for (int i = 0; i < lives; i++) {
            this.addCardInHand();
        }
    }

    public int getId() {
        return id;
    }

    public int getLives() {
        return lives;
    }

    public void addLife() {
        lives++;
    }

    public List<BlueCard> getFrontCards() {
        return frontCards;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public BlueCard getBarrelInFront() {
        for (BlueCard frontCard : frontCards) {
            if (frontCard instanceof Barrel) {
                return frontCard;
            }
        }
        return null;
    }

    public Missed getMissedCard() {
        for (Card card : handCards) {
            if (card instanceof Missed) {
                return ((Missed) card);
            }
        }
        return null;
    }

    public Card getBangInCards() {
        for (Card card : handCards) {
            if (card instanceof Bang) {
                return card;
            }
        }
        return null;
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public boolean isCardInFront(BlueCard insertCard) {
        for (BlueCard frontCard : frontCards) {
            if (insertCard.getClass() == frontCard.getClass()) {
                return true;
            }
        }
        return false;
    }

    public void addCardInFront(BlueCard card) {
        frontCards.add(card);
    }

    public boolean addTwoCardsOrNone() {
        Card card1 = deck.drawCard();
        Card card2 = deck.drawCard();

        if (card1 != null && card2 != null) {
            handCards.add(card1);
            handCards.add(card2);
            return true;
        }
        return false;
    }

    public void addCardInHand() {
        Card card = deck.drawCard();
        if (card != null) {
            handCards.add(card);
        }
    }

    public void discardCard(Card card) {
        deck.addToDiscardPile(card);
        handCards.remove(card);
    }

    public void discardCard(BlueCard card) {
        deck.addToDiscardPile(card);
        if (!handCards.remove(card)) {
            frontCards.remove(card);
        }
    }

    public void loseLife() {
        lives--;
        if (!this.isAlive()) {
            kill();
        }
    }

    private void kill() {
        System.out.println("\u001B[31m !!!! Player " + id + " died !!!! \u001B[0m");
        deck.addToDiscardPile(handCards, frontCards);
    }

    public void discardExtraCards() {
        int handCardsCount = handCards.size();
        if (handCardsCount > lives) {
            System.out.println("Player " + id + " discards this extra cards:");
            while (handCardsCount > lives) {
                int discardCardIndex = random.nextInt(handCardsCount);
                Card discardCard = handCards.get(discardCardIndex);
                System.out.print(" " + discardCard.getName() + " ");
                handCards.remove(discardCard);
                deck.addToDiscardPile(discardCard);
                handCardsCount = handCards.size();
            }
            System.out.println();
        }
    }
}
