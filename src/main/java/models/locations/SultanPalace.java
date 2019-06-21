package models.locations;

import controllers.GameController;
import models.Player;
import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SultanPalaceObservable;
import views.GameView;
import views.tiles.sultansPalace.SultansPalaceView;
import views.tiles.sultansPalace.SultansPalaceView2;
import views.tiles.sultansPalace.SultansPalaceView3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SultanPalace implements Location, SultanPalaceObservable {

    // Variables
    private static SultanPalace sultanPalace;
    private List<GameViewObserver> observers = new ArrayList<>();

    public SultanPalace(){

    }

    //blauw rood groen geel keuze blauw rood groen geel keuze

    //price
    private int jewel  = 1;
    private int fabric = 1;
    private int spice  = 1;
    private int fruit  = 1;
    private int choice = 0;
    private boolean soldOut = false;

    // Firebase
    public Map<String, Object> getVariableMap(){
        Map<String, Object> Data = new HashMap<>();

        Data.put("jewel", jewel); Data.put("fabric", fabric);
        Data.put("spice", spice); Data.put("fruit", fruit);
        Data.put("choice", choice); Data.put("soldOut", soldOut);

        return Data;
    }

    public void setData(Map variables){
        this.jewel = Math.toIntExact((long) variables.get("jewel")); this.fabric = Math.toIntExact((long) variables.get("fabric"));
        this.spice = Math.toIntExact((long) variables.get("spice")); this.fruit = Math.toIntExact((long) variables.get("fruit"));
        this.choice = Math.toIntExact((long) variables.get("choice")); this.soldOut = (boolean) variables.get("soldOut");
    }

    /**
     *  updates the price of the Sultans palace like the original game.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     */
    public void increasePrice(){
        if(jewel==2 && fabric==2 && spice==2 && fruit==2 && choice==2){ //voeg niks meer toe
        }else if(jewel  >  fabric)  { fabric++;
        }else if(fabric >  spice)   { spice++;
        }else if(spice  >  fruit)   { fruit++;
        }else if(fruit  >  choice)  { choice++;
        }else if(choice == jewel)   { jewel++;
        }
        notifyAllObservers();
    }

    /**
     *  confirms the purchase of the Sultans Palace. It checks if the player has enough resources, if so it removes the price off the
     *  players good and updates the price.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     * @param player
     */
    public void confirmPurchase(Player player) throws IOException {
        if(player.getFabrics() >= this.fabric &&
           player.getFruits()  >= this.fruit  &&
           player.getJewels()  >= this.jewel  &&
           player.getSpices()  >= this.spice  &&
           canChoose(player)   && !soldOut    ){

            // Als de speler genoeg heeft dan:

            player.setFabrics(player.getFabrics() - this.fabric);
            System.out.println("fabrics: " + player.fabrics);

            player.setFruits (player.getFruits()  - this.fruit);
            System.out.println("fruits: " + player.fruits);

            player.setJewels (player.getJewels()  - this.jewel);
            System.out.println("jewels: " + player.jewels);

            player.setSpices (player.getSpices()  - this.spice);
            System.out.println("spice: " + player.spices);



            // Code voor keuzescherm
            if      (this.choice == 1){
                SultansPalaceView2.getInstance().chooseOne();
                increasePrice();

            }else if(this.choice == 2){
                SultansPalaceView2.getInstance().chooseOne();
                increasePrice();
            }else                     {
                //add ruby
                increasePrice();
            }

        }else{  System.out.println("bro je hebt niet genoeg");
                //hier moet wat code om een scherm aan te roepen dat je niet genoeg
                //goederen hebt om de aankoop te voltooien ofzo
        }
    }

    /**
     *  Handles the choice of the sultans palace.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     *  @param choice
     *  @param player
     */
    public void handleChoice(String choice, Player player) {
        if (choice == "Fabric (Red)") {

            if (player.getFabrics() >= 1) {
                //speler heeft genoeg
                player.setFabrics(player.getFabrics() - 1);
                System.out.println("fabrics: " + player.fabrics);
                increasePrice();
                //afsluiten
                //add ruby
            } else {System.out.println("bro je hebt niet genoeg");
            }

        } else if (choice == "Fruit (Yellow)") {
            if (player.getFruits() >= 1) {
                //speler heeft genoeg
                player.setFruits(player.getFruits() - 1);
                System.out.println("fruits: " + player.fruits);
                increasePrice();
                //afsluiten
                //add ruby
            } else {System.out.println("bro je hebt niet genoeg");
            }

        } else if (choice == "Spice (Green)") {
            if (player.getSpices() >= 1) {
                //speler heeft genoeg
                player.setSpices(player.getSpices() - 1);
                System.out.println("spice: " + player.spices);
                increasePrice();
                //afsluiten
                //add ruby
            } else {System.out.println("bro je hebt niet genoeg");
            }

        } else if (choice == "Jewel (Blue)") {
            if (player.getJewels() >= 1) {
                //speler heeft genoeg
                player.setJewels(player.getJewels() - 1);
                System.out.println("jewels: " + player.jewels);
                increasePrice();
                //afsluiten
                //add ruby
            } else {System.out.println("bro je hebt niet genoeg");
            }
        }
    }


    /**
     *  returns a true if the player is able to choose a good. If not, it returns a false.
     *  @version 20-6-2019
     *  @Author Thomas van Velzen
     * @param player
     */
    public boolean canChoose(Player player){
        if ((player.getFabrics() - this.fabric) +
            (player.getFruits()  - this.fruit)  +
            (player.getJewels()  - this.jewel)  +
            (player.getSpices()  - this.spice)  >= this.choice){
            return true;
        }else{
            return false;
        }
    }

    public int getJewelPrice(){
        return jewel;
    }

    public int getFabricPrice(){
        return fabric;
    }

    public int getSpicePrice(){
        return spice;
    }

    public int getFruitPrice(){
        return fruit;
    }

    public int getChoiceAmount(){
        return choice;
    }


    public boolean redAs = false;
    public boolean blueAs = false;
    public boolean greenAs = false;
    public boolean yellowAs = false;
    public boolean whiteAs = false;

    public boolean color(String color) {
        boolean myColor;
        if (color == "red") {
            myColor = redAs;
        } else if( color == "blue"){
            myColor = blueAs;
        } else if(color == "green" ){
            myColor = greenAs;
        } else if ( color == "yellow"){
            myColor = yellowAs;
        } else if (color == "white"){
            myColor = whiteAs;
        }
        else{
            myColor = false;
        }
        return myColor;
    }

    public void setColor(String color, boolean set){
        if (color == "red"){
            redAs = set;
        } else if(color == "blue"){
            blueAs = set;
        } else if (color == "green"){
            greenAs = set;
        } else if (color == "yellow"){
            yellowAs = set;
        } else if (color == "white"){
            whiteAs = set;
        }
    }


    // Observer Pattern
    @Override
    public void register(GameViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        if (!observers.contains(GameView.getInstance())){
            register(GameView.getInstance());
        }
        for (GameViewObserver gvo : observers){
            gvo.update(this);
        }
    }

    // Singleton Pattern
    public static SultanPalace getInstance() {
        if (sultanPalace == null) {
            sultanPalace = new SultanPalace();
        }
        return sultanPalace;
    }
}
