package controllers;

import models.MainMenu;
import views.MenuView;

public class MenuViewController {

    // Variabelen
    private MainMenu mainMenu;
    private static MenuViewController menuViewController;

    // Constructor
    public MenuViewController(){
        mainMenu = MainMenu.getInstance();
        mainMenu.register(MenuView.getInstance());
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
