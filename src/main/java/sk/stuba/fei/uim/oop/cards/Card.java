package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

public abstract class Card {
    protected String name;

    public Card(String name){
        this.name = name;
    }
    public void effect(Player player){

    }

    public String getName() {
        return name;
    }
}
