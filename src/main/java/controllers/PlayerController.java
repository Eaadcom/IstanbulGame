package controllers;

import models.Player;
import views.GameView;

import javax.smartcardio.Card;
import java.util.Scanner;
import java.io.IOException;

public class PlayerController {

    GameView game = new GameView();

    Player player = new Player("name");
    Scanner scanner = new Scanner(System.in);

    ////

    public void pay(int amount, Player player){
        if (amount > player.lira){}else{
            player.lira -= amount;
            addRubysLiras("lira", amount);
        }}

    public void addRubysLiras(String g, int amount){
        switch(g) {
            case "ruby":
                player.rubies += amount;
                break;
            case "lira":
                player.lira += amount;
                break;
            default:
                // code block
        }}

    public void addGoods(String nameOfGoods){
        switch(nameOfGoods) {
            case "spice":
                player.spices = (player.carUpgrades);
                break;
            case "fruit":
                player.fruits = (player.carUpgrades);
                break;
            case "jewel":
                player.jewels = (player.carUpgrades);
                break;
            case "fabric":
                player.fabrics = (player.carUpgrades);
                break;
            default:
                // code block
        }}

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
     * @param pos
     */
    public void changePosition(int pos) throws IOException {
        if(player.position != pos && !player.hasMoved){
            game.askConfirmMovement();
            player.position = pos;
            player.hasMoved = true;
            System.out.println("Position updated to: " + player.position);
        }}
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
}
