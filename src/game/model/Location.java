package game.model;

import java.util.ArrayList;

public class Location {
    private ArrayList<Player> players;

    private boolean assistantWhite = false;
    private boolean assistantRed = false;
    private boolean assistantBlue = false;
    private boolean assistantGreen = false;
    private boolean assistantYellow = false;

    private boolean smuggler = false;
    private boolean governer = false;

    private int locationNumber;

    public void placeAssistant(String c){
        switch(c) {
            case "g":
                assistantGreen = true;
                break;
            case "b":
                assistantBlue = true;
                break;
            case "r":
                assistantRed = true;
                break;
            case "y":
                assistantYellow = true;
                break;
            case "w":
                assistantWhite = true;
                break;
            default:
                // code block
        }
    }


}
