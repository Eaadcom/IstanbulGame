package models;

import controllers.FirebaseController;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Player {
    private static String username = "Ed";
    private static int gemstones = 2;
    private static String className = "Player";

    public static void getVariables() {
        LinkedHashMap<String, String> variables = new LinkedHashMap<>();

        variables.put("className", className);
        variables.put("username", username);
        variables.put("gemstones", Integer.toString(gemstones));
        FirebaseController.firebaseWriter(variables);
    }

    public static void updateVariables(HashMap variables){

    }
}
