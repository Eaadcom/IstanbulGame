package controllers;

import models.Player;
import views.PopUpView;

import java.util.Scanner;
import java.io.IOException;

/**
 * This class is the controller for the player.
 * It contains inmportant methods that update the player's statistics.
 * @author Edward Deen, Joeri van Duijkeren, Stan Hogenboom, Floris Dekker, Thomas van Velzen
 * @version 21-6-2019
 */
public class PlayerController {

    // Variables
    private static PlayerController playerController;
    private FirebaseController firebaseController = FirebaseController.getInstance();
    private models.Board board = models.Board.getInstance();

    private Player myPlayer;

    public Player createNewPlayer(String name, int myPlayerID) {
        Player player = new Player(name, myPlayerID);
        //.createNewPlayer(player);
        myPlayer = player;
        return player;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    Scanner scanner = new Scanner(System.in);
    private PopUpView pv = new PopUpView();


    public void AssistantHandler(String todo){

        if(todo == "Remove"){
            myPlayer.assistants -= 1;
        } else if(todo == "Add"){
            myPlayer.assistants += 1;
        } else if(todo == "Fountain"){
            myPlayer.assistants = myPlayer.maxAssistants;
        }
    }

    /**
     * Upgrades the maximum values of the cart so the player can have more goods.
     * @author Stan Hogenboom, Floris Dekker
     * @version 19-6-2019
     */
    public void CarUpgrader(){
            myPlayer.maxSpices++;
            myPlayer.maxFabrics++;
            myPlayer.maxFruits++;
            myPlayer.maxJewels++;
            if(myPlayer.carUpgrades == 3){
                addRubysLiras("ruby", 1);
            }
        }


    /**
     * Cargo checkers ensure that the amount that needs to be added does not exceed the max that  player can have.
     * @author Floris Dekker
     * @version 24-06-2019
     * @param toAdd
     * @return
     */

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

    /**
     * The MaxCargoUpdater updates the Max Cargo when once car has been upgraded
     * @author Floris Dekker
     * @version 24-06-2019
     */
    public void MaxCargoUpdater(){
        myPlayer.maxSpices = myPlayer.maxSpices + myPlayer.carUpgrades;
        myPlayer.maxFabrics = myPlayer.maxFabrics + myPlayer.carUpgrades;
        myPlayer.maxFruits = myPlayer.maxFruits + myPlayer.carUpgrades;
        myPlayer.maxJewels = myPlayer.maxJewels + myPlayer.carUpgrades;
    }

    /**
     * Adds either rubies or lira to the player.
     * Can also be used to remove rubies and lira by inserting a negative number.
     * @author Stan Hogenboom, Floris Dekker
     * @version 20-6-2019
     * @param g
     * @param amount
     */
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

    /**
     * this function is called when the warehouse tiles have been reached by a player.
     * @author Floris Dekker
     * @version 24-06-2019
     * @param nameOfGoods
     */

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

    public int PlayerChoosesCard(){

        int CardChoice = scanner.nextInt();

        return CardChoice;
    }

    public void setName(String name){
        myPlayer.name = name;
    }

    // Singleton Pattern
    public static PlayerController getInstance() {
        if (playerController == null) {
            playerController = new PlayerController();
        }
        return playerController;
    }

}
