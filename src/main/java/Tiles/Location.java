package Tiles;

import sample.FirebaseController;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Location {
    
    public int number;

    Location location = new Location();

    public void OnUse(){



        switch(number) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
        }
    }








    public static void writeToController() {
        ArrayList<String> variables = new ArrayList<>();
        FirebaseController.fireBaseWriter(variables);
    }
}