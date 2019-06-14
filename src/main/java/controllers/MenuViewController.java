package controllers;

import models.MainMenu;
import views.MenuView;

import java.util.List;

public class MenuViewController {

    // Variabelen
    private MainMenu mainMenu;
    private FirebaseController firebaseController;
    private static MenuViewController menuViewController;

    // Constructor waarin het MainMenu object wordt opgehaald
    public MenuViewController(){
        mainMenu = new MainMenu();
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

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public int getPlayerTotal(){
        return mainMenu.getPlayerTotal();
    }

    public String getUserName(){
        return mainMenu.getUsername();
    }

    // Stuurt de username die hij doorkrijgt als argument naar de mainMenu model
    public void throwUsername(String username){
        mainMenu.setUsername(username);
    }

    // Pakt de aangemaakt lobbies uit de Firebase
    public List getLobbies(){
        firebaseController = FirebaseController.getInstance();
        return firebaseController.fillGameLobby();
    }

    // Singleton Pattern
    public static MenuViewController getInstance() {
        if (menuViewController == null) {
            menuViewController = new MenuViewController();
        }
        return menuViewController;
    }
}
