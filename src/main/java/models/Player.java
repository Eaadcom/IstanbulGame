package models;

import models.cards.BonusCard;
import observers.GameViewObserver;
import observers.PlayerObservable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the player.
 * An object of this class is created for every player.
 * All statistics of a player, including it's position on the board, are stored here.
 *
 * @author Stan, Joeri
 * @version 4 juni 2019
 */
public class Player implements PlayerObservable {

    // LobbyVariables
    public String name;
    private int playerID;
    // SystemVariables
    private List<GameViewObserver> observers = new ArrayList<>();

    // GameVariables
    public int rubies = 0;
    public int lira = 0;
    public int carUpgrades = 0;
    public List<BonusCard> playerBonusCards = new ArrayList<>();
    public String color = "red";

    public int spices = 2;
    public int fruits = 2;
    public int jewels = 2;
    public int fabrics = 2;


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
    public FamilyMember familyMember = new FamilyMember();

    public boolean hasMoved = false;

    private int bonusMoves = 0;


    public Map<String, Object> getVariableMap() {
        Map<String, Object> playerData = new HashMap<>();

        playerData.put("rubies", rubies);
        playerData.put("lira", lira);
        playerData.put("carUpgrades", carUpgrades);
        playerData.put("playerBonusCards", playerBonusCards);
        playerData.put("color", color);
        playerData.put("spices", spices);
        playerData.put("fruits", fruits);
        playerData.put("jewels", jewels);
        playerData.put("fabrics", fabrics);
        playerData.put("maxJewels", maxJewels);
        playerData.put("maxFruits", maxFruits);
        playerData.put("maxSpices", maxSpices);
        playerData.put("maxFabrics", maxFabrics);
        playerData.put("assistants", assistants);
        playerData.put("maxAssistants", maxAssistants);
        playerData.put("greentile", greenTile);
        playerData.put("redtile", redTile);
        playerData.put("bluetile", blueTile);
        playerData.put("yellowTile", yellowTile);
        playerData.put("name", name);
        playerData.put("playerID", playerID);

        return playerData;
    }

    public void setData(Map playerData) {

        this.rubies = Math.toIntExact((long) playerData.get("rubies"));
        this.lira = Math.toIntExact((long) playerData.get("lira"));
        this.carUpgrades = Math.toIntExact((long) playerData.get("carUpgrades"));
        this.playerBonusCards = (List) playerData.get("playerBonusCards");
        this.color = (String) playerData.get("color");
        this.spices = Math.toIntExact((long) playerData.get("spices"));
        this.fruits = Math.toIntExact((long) playerData.get("fruits"));
        this.jewels = Math.toIntExact((long) playerData.get("jewels"));
        this.fabrics = Math.toIntExact((long) playerData.get("fabrics"));
        this.maxJewels = Math.toIntExact((long) playerData.get("maxJewels"));
        this.maxFruits = Math.toIntExact((long) playerData.get("maxFruits"));
        this.maxSpices = Math.toIntExact((long) playerData.get("maxSpices"));
        this.maxFabrics = Math.toIntExact((long) playerData.get("maxFabrics"));
        this.assistants = Math.toIntExact((long) playerData.get("assistants"));
        this.maxAssistants = Math.toIntExact((long) playerData.get("maxAssistants"));
        this.greenTile = (boolean) playerData.get("greentile");
        this.redTile = (boolean) playerData.get("redtile");
        this.blueTile = (boolean) playerData.get("bluetile");
        this.yellowTile = (boolean) playerData.get("yellowTile");
        this.name = (String) playerData.get("name");
        this.playerID = Math.toIntExact((long) playerData.get("playerID"));
    }

    public Player() {}

    // Constructor
    public Player(String name, int playerID) {
        this.name = name;
        this.playerID = playerID;
    }

    // Setters
    public void setMaxFabrics() {
        fabrics = maxFabrics;
        notifyAllObservers();
    }

    public void setMaxFruits() {
        fruits = maxFruits;
        notifyAllObservers();
    }

    public void setMaxSpices() {
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

    public int getRubies() {
        return rubies;
    }

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

    public int getCarUpgrades() {
        return carUpgrades;
    }

    public int getPlayerID() {
        return playerID;
    }

    // Observer Pattern
    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (GameViewObserver gvo : observers) {
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
