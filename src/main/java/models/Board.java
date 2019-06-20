package models;

import controllers.GameController;
import models.cards.BonusCard;
import models.locations.*;
import observers.BoardObservable;
import observers.GameViewObserver;

import java.util.*;

public class Board implements BoardObservable {

    // SystemVariables
    private List<GameViewObserver> observers = new ArrayList<>();

    // GameVariables
    public List<Player> players = new LinkedList<Player>();
    public Map<String, Object> tiles = new HashMap<>();
    public Object[] cards;
    public Dice[] dice;
    public Governor governor = new Governor();
    public Smuggler smuggler = new Smuggler();

    public Board(){
        fillTileList();
    }

    // feeds variables back into this class
    public void setBoardData(Map variables){
        setPlayerData((Map) variables.get("Players"));
        setTileData((Map) variables.get("Tiles"));
        //this.cards = (Object[]) variables.get("cards");
        //this.dice = (Dice[]) variables.get("dice");
        //this.governor = (Governor) variables.get("governor");
        //this.smuggler = (Smuggler) variables.get("smuggler");
    }

    private void setPlayerData(Map<String, Object> playersList){
        int playerTotal = GameController.getInstance().getGame().getPlayerTotal(); //TODO niet goed volgens MVC

        players.get(0).setData((Map) playersList.get("Player1"));
        if (playersList.size() >= 2){
            players.get(1).setData((Map) playersList.get("Player2"));
        } if (playersList.size() >= 3){
            players.get(2).setData((Map) playersList.get("Player3"));
        } if (playersList.size() >= 4){
            players.get(3).setData((Map) playersList.get("Player4"));
        } if (playersList.size() == 5){
            players.get(4).setData((Map) playersList.get("Player5"));
        }
    }

    private void setTileData(Map tiles){
        BlackMarket.getInstance().setData((Map)tiles.get("BlackMarket")); Caravansary.getInstance().setData((Map)tiles.get("Caravansary"));
        FabricWarehouse.getInstance().setData((Map)tiles.get("FabricWarehouse")); Fountain.getInstance().setData((Map)tiles.get("Fountain"));

        FruitWarehouse.getInstance().setData((Map)tiles.get("FruitWarehouse")); GemstoneDealer.getInstance().setData((Map)tiles.get("GemstoneDealer"));
        GreatMarket.getInstance().setData((Map)tiles.get("GreatMarket")); GreatMosque.getInstance().setData((Map)tiles.get("GreatMosque"));

        PoliceStation.getInstance().setData((Map)tiles.get("PoliceStation")); PostOffice.getInstance().setData((Map)tiles.get("PostOffice"));
        SmallMarket.getInstance().setData((Map)tiles.get("SmallMarket")); SmallMosque.getInstance().setData((Map)tiles.get("SmallMosque"));

        SpiceWarehouse.getInstance().setData((Map)tiles.get("SpiceWarehouse")); SultanPalace.getInstance().setData((Map)tiles.get("SultanPalace"));
        TeaHouse.getInstance().setData((Map)tiles.get("TeaHouse")); Wainwright.getInstance().setData((Map)tiles.get("Wainwright"));
    }

    // returns hashmap of variables of this class
    public Map getVariables(){
        Map variables = new HashMap();
        variables.put("players", players);
        variables.put("tiles", tiles);
        variables.put("cards", cards);
        variables.put("dice", dice);
        variables.put("governor", governor);
        variables.put("smuggler", smuggler);
        return variables;
    }

    private void fillTileList(){
        tiles.put("BlackMarket", BlackMarket.getInstance()); tiles.put("Caravansary", Caravansary.getInstance());
        tiles.put("FabricWarehouse", FabricWarehouse.getInstance()); tiles.put("Fountain", Fountain.getInstance());

        tiles.put("FruitWarehouse", FruitWarehouse.getInstance()); tiles.put("GemstoneDealer", GemstoneDealer.getInstance());
        tiles.put("GreatMarket", GreatMarket.getInstance()); tiles.put("PoliceStation", PoliceStation.getInstance());

        tiles.put("PostOffice", PostOffice.getInstance()); tiles.put("SmallMarket", SmallMarket.getInstance());
        tiles.put("SmallMosque", SmallMosque.getInstance()); tiles.put("SpiceWarehouse", SpiceWarehouse.getInstance());

        tiles.put("SultanPalace", SultanPalace.getInstance()); tiles.put("TeaHouse", TeaHouse.getInstance());
        tiles.put("WainWright", Wainwright.getInstance()); tiles.put("GreatMosque", GreatMarket.getInstance());
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

    public void shufflePlayers() {
        Collections.shuffle(players);
    }

    // Setters
    public void addPlayer(Player player) {
        players.add(players.size(), player);
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    // Getters
    public Player getPlayer() {
        return players.get(0);
    }
    public Player getCurrentPlayerTurn() {
        return null;
    }
    public List<Player> getPlayers() {
        return players;
    }

    // singleton pattern
    private static Board board;
    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }
}
