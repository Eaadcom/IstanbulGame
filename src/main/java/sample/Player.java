package sample;

import java.util.ArrayList;

public class Player {
    private static String username = "Edje";
    private static int gemstones = 2;
    private static String className = "Player";

    public static void writeToController() {
        ArrayList<String> variables = new ArrayList<>();
        variables.add(username);
        variables.add(Integer.toString(gemstones));
        variables.add(className);
        FirebaseController.fireBaseWriter(variables);
    }
}
