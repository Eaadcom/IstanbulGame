package models;

import javafx.scene.Node;
import controllers.GameController;
import models.cards.BonusCard;
import observers.GameViewObserver;
import observers.PlayerObservable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the player. An object of this class is created for every player. All statistics of a player, including it's position on the board, are stored here.
 * @author Stan, Joeri
 * @version 4 juni 2019
 */
public class Player implements PlayerObservable {

    // Variables
    private List<GameViewObserver> observers = new ArrayList<>();
    public String name;

    public int rubies = 0;
    public int lira = 0;
    public int carUpgrades = 2;
    public String teamColor;
    public List<BonusCard> playerBonusCards = new ArrayList<>();

    public int spices = 0;
    public int fruits = 0;
    public int jewels = 0;
    public int fabrics = 0;

    public int maxJewels = 2;
    public int maxFruits = 2;
    public int maxSpices = 2;
    public int maxFabrics = 2;

    public int assistants = 4;
    public int maxAssistants = 4;
    public int position = 7;

    public boolean greenTile = false;
    public boolean redTile = false;
    public boolean blueTile = false;
    public boolean yellowTile = false;

    public boolean hasMoved = false;

    private int bonusMoves = 0;

    // Constructor
    public Player(String name){
        this.name = name;
    }

    // Setters
    public void setLira(int lira) {
        this.lira = lira;
    }

    public void setRubies(int rubies) {
        this.rubies = rubies;
    }

    public void setCarUpgrades(int carUpgrades) {
        this.carUpgrades = carUpgrades;
    }

    public void setSpices(int spices) {
        this.spices = spices;
    }

    public void setFruits(int fruits) {
        this.fruits = fruits;
    }

    public void setJewels(int jewels) {
        this.jewels = jewels;
    }

    public void setFabrics(int fabrics) {
        this.fabrics = fabrics;
    }

    public int getSpices() {
        return spices;
    }

    public int getFruits() {
        return fruits;
    }

    public int getJewels() {
        return jewels;
    }

    public int getFabrics() {
        return fabrics;
    }

    // Observer Pattern
    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (GameViewObserver gvo : observers){
            gvo.update(this);
        }
    }

    public void increaseLira(int amount) {
        lira += amount;
        notifyAllObservers();
    }

    public int getLira() {
        return lira;
    }

    public void addBonusCard(BonusCard bonusCard) {
        this.playerBonusCards.add(bonusCard);
    }

    public List<BonusCard> getBonusKaartenInBezit() {
        return playerBonusCards;
    }

    public String getName() {
        return name;
    }

    public int getTotaalAantalMoves() {
        return 0;
    }
}
