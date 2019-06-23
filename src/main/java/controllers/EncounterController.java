package controllers;

public class    EncounterController {

    // Variables
    private static EncounterController encounterController;

    // Singleton Pattern
    public static EncounterController getInstance() {
        if (encounterController == null) {
            encounterController = new EncounterController();
        }
        return encounterController;
    }
}
