package controllers;

import models.Board;
import models.Player;
import views.PopUpView;

import java.io.Console;
import java.util.Scanner;
import java.io.IOException;

public class PlayerController {

    // Variables
    private static PlayerController playerController;
    private FirebaseController firebaseController = FirebaseController.getInstance();
    private models.Board board = models.Board.getInstance();

    private Player myPlayer;

    public Player createNewPlayer(String name) {
        Player player = new Player(name);
        firebaseController.createNewPlayer(player);
        myPlayer = player;
        return player;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    Scanner scanner = new Scanner(System.in);
    private PopUpView pv = new PopUpView();

    ////
    public void AssistantHandler(String todo){

        if(todo == "Remove"){
            myPlayer.assistants -= 1;
        } else if(todo == "Add"){
            myPlayer.assistants += 1;
        } else if(todo == "Fountain"){
            myPlayer.assistants = myPlayer.maxAssistants;
        }
    }

    public void CarUpgrader(){

            myPlayer.carUpgrades += 1;
            if(myPlayer.carUpgrades == 3){
                addRubysLiras("ruby", 1);
            }
        }


    public boolean CargoCheckSpices(int toAdd){
        boolean CargoCheckSpices;
        MaxCargoUpdater();

        if(myPlayer.spices + toAdd < myPlayer.maxSpices || myPlayer.spices + toAdd == myPlayer.maxSpices){

            CargoCheckSpices = true;

        }else{
            CargoCheckSpices = false;
        }

        return CargoCheckSpices;
    }
    public boolean CargoCheckFruits(int toAdd){
        boolean CargoCheckFruits;
        MaxCargoUpdater();

        if(myPlayer.fruits + toAdd < myPlayer.maxFruits || myPlayer.fruits + toAdd == myPlayer.maxFruits){

            CargoCheckFruits = true;

        }else{
            CargoCheckFruits = false;
        }

        return CargoCheckFruits;
    }

    public boolean CargoCheckFabrics(int toAdd){
        boolean CargoCheckFabrics;
        MaxCargoUpdater();

        if(myPlayer.fabrics + toAdd < myPlayer.maxFabrics || myPlayer.fabrics + toAdd == myPlayer.maxFabrics){

            CargoCheckFabrics = true;

        }else{
            CargoCheckFabrics = false;
        }

        return CargoCheckFabrics;
    }

    public boolean CargoCheckJewels(int toAdd){
        boolean CargoCheckJewels;
        MaxCargoUpdater();

        if(myPlayer.jewels + toAdd < myPlayer.maxJewels || myPlayer.fabrics + toAdd == myPlayer.maxJewels){

            CargoCheckJewels = true;

        }else{
            CargoCheckJewels = false;
        }

        return CargoCheckJewels;
    }
    public void MaxCargoUpdater(){
        myPlayer.maxSpices = myPlayer.maxSpices + myPlayer.carUpgrades;
        myPlayer.maxFabrics = myPlayer.maxFabrics + myPlayer.carUpgrades;
        myPlayer.maxFruits = myPlayer.maxFruits + myPlayer.carUpgrades;
        myPlayer.maxJewels = myPlayer.maxJewels + myPlayer.carUpgrades;
    }
    public void pay(int amount, Player player){
        if (amount > player.lira){}else{
            player.lira -= amount;
            addRubysLiras("lira", amount);
        }}

    public void addRubysLiras(String g, int amount) {
        switch(g) {
            case "ruby":
                myPlayer.rubies += amount;
                if (myPlayer.rubies == 5) {
                    winCondition();
                }
                break;
            case "lira":
                myPlayer.lira += amount;
                break;
            default:
                // code block
        }}

    /**
     * This function opens a pop-up when a player wins. This is the win screen if the currentplayer wins, or the lose screen if another players wins.
     * The requiredment for winning is obtaining 5 rubies.
     * @author Stan Hogenboom
     * @version June 11th, 2019
      */
    public void winCondition() {
        try {
            pv.winnerScreen();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loseCondition() {
        try {
            pv.loserScreen();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void MaxGoods(String nameOfGoods){
        switch(nameOfGoods) {
            case "spice":
                myPlayer.spices = myPlayer.maxSpices;
                System.out.println(myPlayer.maxSpices);
                System.out.println(myPlayer.spices);
                break;
            case "fruit":
                myPlayer.fruits = myPlayer.maxFruits;
                break;
            case "jewel":
                myPlayer.jewels = myPlayer.maxJewels;
                break;
            case "fabric":
                myPlayer.fabrics = myPlayer.maxFabrics;
                break;
            default:
                // code block
        }

    }

    /**
     *  Adds a mosque tile to the player. colorOfTile defines what mosque tile is added.
     *  @version 20-6-2019
     *  @param colorOfTile
     *  @Author Thomas van Velzen
     */
    public void addMosqueTile(String colorOfTile){
        switch(colorOfTile) {
            case "green":
                myPlayer.greenTile = true;
                break;
            case "blue":
                myPlayer.blueTile = true;
                break;
            case "red":
                myPlayer.redTile = true;
                break;
            case "yellow":
                myPlayer.yellowTile = true;
                break;
            default:
                // code block
        }}

    /**
     * Deze functie wordt aangeroepen als een speler aan het begin van zijn beurt op een tegel klikt waar hij naartoe wilt lopen.
     * @Author: Stan Hogenboom
     * @Version: 3 juni 2019
     * @param
     */

    public int PlayerChoosesCard(){

        int CardChoice = scanner.nextInt();

        return CardChoice;
    }

//    public void placeAssistant(Location l){
//        if(assistants > 0){
//            assistants =- 1;
//            l.placeAssistant(teamColor);
//
//    }}

    public void setName(String name){
        myPlayer.name = name;
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

    // Singleton Pattern
    public static PlayerController getInstance() {
        if (playerController == null) {
            playerController = new PlayerController();
        }
        return playerController;
    }

    /**
     * deze functie houdt bij welke bonuskaarten de speler tot zijn beschikking heeft
     * @AUTHOR Joeri van Duijkeren
     * @VERSION 8 june 2019
     */


}
