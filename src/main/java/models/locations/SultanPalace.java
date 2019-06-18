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
import java.util.List;

public class SultanPalace implements Location, SultanPalaceObservable {

    // Variables
    private static SultanPalace sultanPalace;

    private List<GameViewObserver> observers = new ArrayList<>();

    public SultanPalace(){

    }

    //blauw rood groen geel keuze blauw rood groen geel keuze

    //price
    private int jewel  = 1;
    private int fabric = 0;
    private int spice  = 0;
    private int fruit  = 0;
    private int choice = 0;
    private boolean soldOut = false;

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
