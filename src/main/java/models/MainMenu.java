package models;

import observers.MainMenuObservable;
import observers.MenuViewObserver;
import views.MenuView;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements MainMenuObservable {

    // Variabelen
    private static MainMenu mainMenu;
    private List<MenuViewObserver> observers = new ArrayList<>();
    private String username;
    private String difficulty;
    private int playerTotal;
    private String gameName;

    // Setters
    public void setUsername(String usr){
        this.username = usr;
    }

    public void setDifficulty(String diff){
        this.difficulty = diff;
    }

    public void setPlayerTotal(int pt){
        this.playerTotal = pt;
    }

    public void setGameName(String gmn){
        this.gameName = gmn;
    }

    // Getters
    @Override
    public String getUsername() {
        return username;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getGameName() {
        return gameName;
    }

    public int getPlayerTotal(){
        return playerTotal;
    }

    // Observer pattern
    @Override
    public void register(MenuViewObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (MenuViewObserver mvo : observers){
            mvo.update(this);
        }
    }

    // Singleton Pattern
    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }
}
