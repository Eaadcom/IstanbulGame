package controllers;

import models.MainMenu;

public class MenuViewController {
    MainMenu mainMenu = new MainMenu();

    public void throwUsername(String username){
        mainMenu.setUsername(username);
    }
}
