package models;

import views.GameView;
import controllers.GameController;

import java.io.IOException;

/**
 * This class is the player. An object of this class is created for every player. All statistics of a player, including it's position on the board, are stored here.
 * @author Stan
 * @version 2 juni 2019
 */
public class Player {

    private String name;

    private int rubies = 0;
    private int lira = 0;
    private int carUpgrades = 2;
    private String teamColor;

    private int spices = 0;
    private int fruits = 0;
    private int jewels = 0;
    private int fabrics = 0;

    private int assistants = 4;
    private int position = 7;

    private boolean greenTile = false;
    private boolean redTile = false;
    private boolean blueTile = false;
    private boolean yellowTile = false;

    private boolean hasMoved = false;

    ////
    GameView game = new GameView();

    public Player(String name){
        this.name = name;
    }

    ////

    public void pay(int amount, Player player){
        if (amount > this.lira){}else{
        lira -= amount;
        player.addRubysLiras("lira", amount);
    }}

    public void addRubysLiras(String g, int amount){
        switch(g) {
            case "ruby":
                rubies += amount;
                break;
            case "lira":
                lira += amount;
                break;
            default:
                // code block
    }}

    public void addGoods(String nameOfGoods){
        switch(nameOfGoods) {
            case "spice":
                spices = (carUpgrades);
                break;
            case "fruit":
                fruits = (carUpgrades);
                break;
            case "jewel":
                jewels = (carUpgrades);
                break;
            case "fabric":
                fabrics = (carUpgrades);
                break;
            default:
                // code block
    }}

    public void addMosqueTile(String colorOfTile){
        switch(colorOfTile) {
            case "green":
                greenTile = true;
                break;
            case "blue":
                blueTile = true;
                break;
            case "red":
                redTile = true;
                break;
            case "yellow":
                yellowTile = true;
                break;
            default:
                // code block
    }}

    /**
     * Deze functie wordt aangeroepen als een speler aan het begin van zijn beurt op een tegel klikt waar hij naartoe wilt lopen.
     * @Author: Stan Hogenboom
     * @Version: 3 juni 2019
     * @param pos
     */
    public void changePosition(int pos) throws IOException {
        if(position != pos && !hasMoved){
            game.askConfirmMovement();
            position = pos;
            hasMoved = true;
            System.out.println("Position updated to: " + position);
    }}


//    public void placeAssistant(Location l){
//        if(assistants > 0){
//            assistants =- 1;
//            l.placeAssistant(teamColor);
//
//    }}

    public void setName(String name){
        this.name = name;
    }

    //    public static void getVariables() {
//        LinkedHashMap<String, String> variables = new LinkedHashMap<>();
//
//        variables.put("className", className);
//        variables.put("username", username);
//        variables.put("gemstones", Integer.toString(gemstones));
//        FirebaseController.firebaseWriter(variables);
//    }
//
//    public static void updateVariables(HashMap variables){
//
//    }


}
