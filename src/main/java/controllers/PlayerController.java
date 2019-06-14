package controllers;

import models.Player;
import views.GameView;
import views.PopUpView;

import java.util.Scanner;
import java.io.IOException;

public class PlayerController {

    // Variables
    private static PlayerController playerController;
    private Player player = new Player("name");
    Scanner scanner = new Scanner(System.in);
    private PopUpView pv = new PopUpView();

    ////
    public void AssistantHandler(String todo){

        if(todo == "Remove"){
            player.assistants -= 1;
        } else if(todo == "Add"){
            player.assistants += 1;
        } else if(todo == "Fountain"){
            player.assistants = player.maxAssistants;
        }
    }

    public boolean CargoCheckSpices(int toAdd){
        boolean CargoCheckSpices;
        MaxCargoUpdater();

        if(player.spices + toAdd < player.maxSpices || player.spices + toAdd == player.maxSpices){

            CargoCheckSpices = true;

        }else{
            CargoCheckSpices = false;
        }

        return CargoCheckSpices;
    }
    public boolean CargoCheckFruits(int toAdd){
        boolean CargoCheckFruits;
        MaxCargoUpdater();

        if(player.fruits + toAdd < player.maxFruits || player.fruits + toAdd == player.maxFruits){

            CargoCheckFruits = true;

        }else{
            CargoCheckFruits = false;
        }

        return CargoCheckFruits;
    }

    public boolean CargoCheckFabrics(int toAdd){
        boolean CargoCheckFabrics;
        MaxCargoUpdater();

        if(player.fabrics + toAdd < player.maxFabrics || player.fabrics + toAdd == player.maxFabrics){

            CargoCheckFabrics = true;

        }else{
            CargoCheckFabrics = false;
        }

        return CargoCheckFabrics;
    }

    public boolean CargoCheckJewels(int toAdd){
        boolean CargoCheckJewels;
        MaxCargoUpdater();

        if(player.jewels + toAdd < player.maxJewels || player.fabrics + toAdd == player.maxJewels){

            CargoCheckJewels = true;

        }else{
            CargoCheckJewels = false;
        }

        return CargoCheckJewels;
    }
    public void MaxCargoUpdater(){
        player.maxSpices = player.maxSpices + player.carUpgrades;
        player.maxFabrics = player.maxFabrics + player.carUpgrades;
        player.maxFruits = player.maxFruits + player.carUpgrades;
        player.maxJewels = player.maxJewels + player.carUpgrades;
    }
    public void pay(int amount, Player player){
        if (amount > player.lira){}else{
            player.lira -= amount;
            addRubysLiras("lira", amount);
        }}

    public void addRubysLiras(String g, int amount) {
        switch(g) {
            case "ruby":
                player.rubies += amount;
                if (player.rubies == 5) {
                    winCondition();
                }
                break;
            case "lira":
                player.lira += amount;
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
                player.spices = player.maxSpices;
                break;
            case "fruit":
                player.fruits = player.maxFruits;
                break;
            case "jewel":
                player.jewels = player.maxJewels;
                break;
            case "fabric":
                player.fabrics = player.maxFabrics;
                break;
            default:
                // code block
        }

    }

    public void addMosqueTile(String colorOfTile){
        switch(colorOfTile) {
            case "green":
                player.greenTile = true;
                break;
            case "blue":
                player.blueTile = true;
                break;
            case "red":
                player.redTile = true;
                break;
            case "yellow":
                player.yellowTile = true;
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
        player.name = name;
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
