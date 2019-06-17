package models.locations;

import models.Player;
import observers.GameViewObserver;
import observers.LocationViewObserver;
import observers.locations.SultanPalaceObservable;
import views.tiles.SultansPalaceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SultanPalace implements Location, SultanPalaceObservable {

    // Variables
    private static SultanPalace sultanPalace;
    //private SultansPalaceView sultansPalaceView = SultansPalaceView.getInstance();
    private List<LocationViewObserver> observers = new ArrayList<>();

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
    }

    public void confirmPurchase(Player player) throws IOException {
        if(player.getFabrics() >= this.fabric &&
           player.getFruits()  >= this.fruit  &&
           player.getJewels()  >= this.jewel  &&
           player.getSpices()  >= this.spice  &&
           canChoose(player)   && !soldOut    ){

            // Als de speler genoeg heeft dan:
            player.setFabrics(player.getFabrics() - this.fabric);
            player.setFruits (player.getFruits()  - this.fruit);
            player.setJewels (player.getJewels()  - this.jewel);
            player.setSpices (player.getSpices()  - this.spice);

            // Code voor keuzescherm
            if      (this.choice == 1){
                //sultansPalaceView.chooseOne();
            }else if(this.choice == 2){
                //sultansPalaceView.chooseTwo();
            }else                     {
                // niks uitvoeren
            }

        }else{  //hier moet wat code om een scherm aan te roepen dat je niet genoeg
                //goederen hebt om de aankoop te voltooien ofzo
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
    public void register(LocationViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (LocationViewObserver gvo : observers){
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
