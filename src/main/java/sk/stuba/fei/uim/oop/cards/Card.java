package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public abstract class Card {
    protected String name;
    protected ArrayList<Player> players;

    protected Card(String name, ArrayList<Player> players){
        this.name = name;
        this.players = players;
    }
    public void play(Player activePlayer){
    }

    protected boolean effect(Player player){
        return true;
    }

    public String getName() {
        return name;
    }
}
