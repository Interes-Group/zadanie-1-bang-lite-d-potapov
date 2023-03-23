package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Player {
    private final int id;
    private int lives;
    private final ArrayList<Card> handCards;
    private final ArrayList<BlueCard> frontCards;
    private final Deck deck;

    public Player(int id, Deck deck) {
        this.id = id;
        this.deck = deck;
        lives = 4;
        handCards = new ArrayList<>();
        frontCards = new ArrayList<>();
    }

    public void makeMove(){
        System.out.println("\n ---- " + this.id + " player move ---- ");

        playFrontCards();
        if (!this.isAlive()) {
            System.out.println(" --- You died! :( ---");
            return;
        }

        takeTwoCards();
        playCardsInHand();
        discardingCards();
    }
    private void playFrontCards(){
        for (BlueCard frontCard : frontCards) {
            frontCard.effect(this);
            if (!this.isAlive()) {
                return;
            }
        }
    }
    private void takeTwoCards(){
        handCards.add(deck.drawCard());
        handCards.add(deck.drawCard());
    }

    private void playCardsInHand(){
        int cardIndex;
        do {
            printHandCards();
            cardIndex = ZKlavesnice.readInt("Input the index of the card you want to play (0 to skip): ");
            if (cardIndex < 0 || cardIndex > handCards.size()) {
                System.out.println("Index \"" + cardIndex + "\" doesn't exist");
                continue;
            }
            handCards.get(cardIndex - 1).effect(this);
        } while (cardIndex != 0);
    }

    private void printHandCards(){
        System.out.print("Cards in your hand: ");
        for (int i = 1; i <= handCards.size(); i++) {
            System.out.print(i + ") " + handCards.get(i) + " ");
        }
        System.out.println("\n");
    }

    private void discardingCards(){
        int handCardsCount = handCards.size();
        while(handCardsCount > lives){
            int cardIndex;
            System.out.println("You have " + handCardsCount + " cards. " +
                    "You need to discard " + (lives - handCardsCount) + " cards.");

            printHandCards();
            cardIndex = ZKlavesnice.readInt("Input the index of the card you want to discard: ");
            if (cardIndex < 1 || cardIndex > handCards.size()) {
                System.out.println("Index \"" + cardIndex + "\" doesn't exist");
                continue;
            }

            Card discardCard = handCards.get(cardIndex - 1);
            handCards.remove(discardCard);
            deck.addToDiscardPile(discardCard);
        }
    }

    public int getId(){
        return id;
    }
    public int getLives(){
        return lives;
    }
    public ArrayList<BlueCard> getFrontCards(){
        return frontCards;
    }
    public boolean isAlive(){
        return lives > 0;
    }

}
