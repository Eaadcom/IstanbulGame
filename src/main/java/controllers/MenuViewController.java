package controllers;

import models.MainMenu;
import views.MenuView;

public class MenuViewController {

    // Variabelen
    private MainMenu mainMenu;
    private static MenuViewController menuViewController;

    // Constructor waarin het MainMenu object wordt opgehaald
    public MenuViewController(){
        mainMenu = MainMenu.getInstance();
        mainMenu.register(MenuView.getInstance());
    }

    // write data to models
    public void throwGameData(String gmn, String diff, int totP){
        mainMenu.setPlayerTotal(totP);
        mainMenu.setDifficulty(diff);
        mainMenu.setGameName(gmn);
    }

    // Get data from models
    public String getGameDifficulty(){
        return mainMenu.getDifficulty();
    }

    public String getGameName(){
        return mainMenu.getGameName();
    }

    public int getPlayerTotal(){
        return mainMenu.getPlayerTotal();
    }

    // Stuurt de username die hij doorkrijgt als argument naar de mainMenu model
    public void throwUsername(String username){
        mainMenu.setUsername(username);
    }

    // Singleton Pattern
    public static MenuViewController getInstance() {
        if (menuViewController == null) {
            menuViewController = new MenuViewController();
        }
        return menuViewController;
    }
}
