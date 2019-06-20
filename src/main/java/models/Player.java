package models;

import javafx.scene.Node;
import controllers.GameController;
import models.cards.BonusCard;
import observers.GameViewObserver;
import observers.PlayerObservable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the player.
 * An object of this class is created for every player.
 * All statistics of a player, including it's position on the board, are stored here.
 * @author Stan, Joeri
 * @version 4 juni 2019
 */
public class Player implements PlayerObservable {

    // SystemVariables
    private List<GameViewObserver> observers = new ArrayList<>();

    // GameVariables
    public int rubies = 0;
    public int lira = 50;
    public int carUpgrades = 0;
    public List<BonusCard> playerBonusCards = new ArrayList<>();
    public String color = "red";

    public int spices = 5;
    public int fruits = 5;
    public int jewels = 5;
    public int fabrics = 5;


    public int maxJewels = 2;
    public int maxFruits = 2;
    public int maxSpices = 2;
    public int maxFabrics = 2;

    public int assistants = 4;
    public int maxAssistants = 4;

    public boolean greenTile = false;
    public boolean redTile = false;
    public boolean blueTile = false;
    public boolean yellowTile = false;

    public int positionOnBoard = 7;

    public boolean hasMoved = false;

    private int bonusMoves = 0;

    // LobbyVariables
    public String name;


    public Map<String, Object> getVariableMap(){
        Map<String, Object> playerData = new HashMap<>();

        playerData.put("rubies", rubies); playerData.put("lira", lira);
        playerData.put("carUpgrades", carUpgrades); playerData.put("playerBonusCards", playerBonusCards);
        playerData.put("color", color); playerData.put("spices", spices);
        playerData.put("fruits", fruits); playerData.put("jewels", jewels);
        playerData.put("fabrics", fabrics); playerData.put("maxJewels", maxJewels);
        playerData.put("maxFruits", maxFruits); playerData.put("maxSpices", maxSpices);
        playerData.put("maxFabrics", maxFabrics); playerData.put("assistants", assistants);
        playerData.put("maxAssistants", maxAssistants); playerData.put("greentile", greenTile);
        playerData.put("redtile", redTile); playerData.put("bluetile", blueTile);
        playerData.put("yellowTile", yellowTile); playerData.put("name", name);
        
        return playerData;
    }

    public void setData(Map playerData){

        this.rubies = (int) playerData.get("rubies"); this.lira = (int) playerData.get("lira");
        this.carUpgrades = (int) playerData.get("carUpgrades"); this.playerBonusCards = (List) playerData.get("playerBonusCards");
        this.color = (String) playerData.get("color"); this.spices = (int) playerData.get("spices");
        this.fruits = (int) playerData.get("fruits"); this.jewels = (int) playerData.get("jewels");
        this.fabrics = (int) playerData.get("fabrics"); this.maxJewels = (int) playerData.get("maxJewels");
        this.maxFruits = (int) playerData.get("maxFruits"); this.maxSpices = (int) playerData.get("maxSpices");
        this. maxFabrics = (int) playerData.get("maxFabrics"); this.assistants = (int) playerData.get("assistants");
        this.maxAssistants = (int) playerData.get("maxAssistants"); this.greenTile = (boolean) playerData.get("greentile");
        this.redTile = (boolean) playerData.get("redtile"); this.blueTile = (boolean) playerData.get("bluetile");
        this.yellowTile = (boolean) playerData.get("yellowTile"); this.name = (String) playerData.get("name");
    }

    // Constructor
    public Player(String name){
        this.name = name;
    }

    // Setters
    public void setMaxFabrics(){
        fabrics = maxFabrics;
        notifyAllObservers();
    }

    public void setMaxFruits(){
        fruits = maxFruits;
        notifyAllObservers();
    }

    public void setMaxSpices(){
        spices = maxSpices;
        notifyAllObservers();
    }

    public void setLira(int lira) {
        this.lira = lira;
        notifyAllObservers();
    }

    public void setRubies(int rubies) {
        this.rubies = rubies;
        notifyAllObservers();
    }

    public void setCarUpgrades(int carUpgrades) {
        this.carUpgrades = carUpgrades;
        notifyAllObservers();
    }

    public void setSpices(int spices) {
        this.spices = spices;
        notifyAllObservers();
    }

    public void setFruits(int fruits) {
        this.fruits = fruits;
        notifyAllObservers();
    }

    public void setJewels(int jewels) {
        this.jewels = jewels;
        notifyAllObservers();
    }

    public void setFabrics(int fabrics) {
        this.fabrics = fabrics;
        notifyAllObservers();
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

    public int getRubies() { return rubies;}

    public int getMaxJewels() {
        return maxJewels;
    }

    public int getMaxFruits() {
        return maxFruits;
    }

    public int getMaxSpices() {
        return maxSpices;
    }

    public int getMaxFabrics() {
        return maxFabrics;
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
