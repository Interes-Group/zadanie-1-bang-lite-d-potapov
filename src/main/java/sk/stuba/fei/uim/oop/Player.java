package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.Missed;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final int id;
    private int lives;
    private ArrayList<Card> handCards;
    private ArrayList<BlueCard> frontCards;
    private Deck deck;

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
        playCards();
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
    public void addCardInFront(BlueCard card){
        frontCards.add(card);
    }
    public boolean isCardInFront(BlueCard insertCard){
        for(BlueCard frontCard: frontCards) {
            if (Objects.equals(frontCard, insertCard)){
                return true;
            }
        }
        return false;
    }
    public BlueCard getBarrelInFront(){
        for(BlueCard frontCard: frontCards) {
            if(frontCard instanceof Barrel){
                return frontCard;
            }
        }
        return null;
    }
    public Card getMissedCard(){
        for(Card card: handCards) {
            if(card instanceof Missed){
                return card;
            }
        }
        return null;
    }

    private void playCards(){
        int cardIndex;
        do {
            printFrontCards();
            printHandCards();
            cardIndex = ZKlavesnice.readInt("Input the index of the card you want to play (0 to skip): ");
            if (cardIndex < 0 || cardIndex > handCards.size()) {
                System.out.println("There is no such index.");
                continue;
            }
            handCards.get(cardIndex - 1).play(this);
        } while (cardIndex != 0);
    }

    private void printFrontCards(){
        System.out.print("Cards in front of you: ");
        for (Card card: frontCards) {
            System.out.print(card.getName() + "; ");
        }
        System.out.println("\n");
    }

    private void printHandCards(){
        System.out.print("Cards in your hand: ");
        for (int i = 1; i <= handCards.size(); i++) {
            System.out.print(i + ") " + handCards.get(i).getName() + "; ");
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
                System.out.println("There is no such index.");
                continue;
            }

            Card discardCard = handCards.get(cardIndex - 1);
            handCards.remove(discardCard);
            deck.addToDiscardPile(discardCard);
        }
    }
    public void loseLife(){
        lives--;
        if (!this.isAlive()){
            kill();
        }
    }
    private void kill(){
        System.out.println(" !!!! Player " + id + " died !!!!");
        deck.addToDiscardPile(handCards, frontCards);
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
    public ArrayList<Card> getHandCards(){
        return handCards;
    }
    public boolean isAlive(){
        return lives > 0;
    }

}
