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

    // Verandert de username naar de string die hij doorkrijgt als argument + notified alle observers van de verandering
    public void setUsername(String usr){
        this.username = usr;
        notifyAllObservers();
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

    // Getter voor de username
    @Override
    public String getUsername() {
        return username;
    }

    // Singleton Pattern
    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }
}
