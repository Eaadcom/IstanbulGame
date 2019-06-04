package models;

import observers.MainMenuObservable;
import observers.MenuViewObserver;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements MainMenuObservable {
    private List<MenuViewObserver> observers = new ArrayList<>();

    private String username;

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

    @Override
    public String getUsername() {
        return username;
    }

}
