package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Missed;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Player {
    private final int id;
    private Deck deck;
    private int lives;
    private Random random;
    private ArrayList<Card> handCards;
    private ArrayList<BlueCard> frontCards;

    public Player(int id, Deck deck) {
        this.id = id;
        this.deck = deck;
        lives = 4;
        random = new Random();
        handCards = new ArrayList<>();
        frontCards = new ArrayList<>();

        for (int i = 0; i < lives; i++){
            this.addCardInHand();
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
    public ArrayList<Card> getHandCards(){
        return handCards;
    }
    public BlueCard getBarrelInFront(){
        for(BlueCard frontCard: frontCards) {
            if(frontCard instanceof Barrel){
                return frontCard;
            }
        }
        return null;
    }
    public Missed getMissedCard(){
        for(Card card: handCards) {
            if(card instanceof Missed){
                return ((Missed) card);
            }
        }
        return null;
    }
    public boolean isAlive(){
        return lives > 0;
    }
    public boolean isCardInFront(BlueCard insertCard){
        for(BlueCard frontCard: frontCards) {
            if (Objects.equals(frontCard, insertCard)){
                return true;
            }
        }
        return false;
    }
    public void addCardInFront(BlueCard card){
        frontCards.add(card);
    }
    public boolean addTwoCardsOrNone(){
        Card card1 = deck.drawCard();
        Card card2 = deck.drawCard();

        if (card1 != null && card2 != null){
            handCards.add(card1);
            handCards.add(card2);
            return true;
        }
        return false;
    }
    public void addCardInHand(){
        Card card = deck.drawCard();
        if (card != null){
            handCards.add(card);
        }
    }
    public void discardCard(Card card){
        deck.addToDiscardPile(card);

        if (!frontCards.remove(card)) {
            handCards.remove(card);
        }
    }
    public void addLife(){
        lives++;
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
        handCards.clear();
        frontCards.clear();
    }
    public void discardExtraCards(){
        int handCardsCount = handCards.size();
        while(handCardsCount > lives){
            handCardsCount = handCards.size();
            int discardCardIndex = random.nextInt(handCardsCount);
            Card discardCard = handCards.get(discardCardIndex);
            handCards.remove(discardCard);
            deck.addToDiscardPile(discardCard);
        }
    }
}
