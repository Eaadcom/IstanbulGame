package models;

import java.util.HashMap;
import java.util.Map;

public enum Difficulty {

    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    RANDOM("random");

    private String value;
    private static final Map<String, Difficulty> difficultyMap = new HashMap<>();

    Difficulty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    static {
        for (Difficulty difficulty : values()) {
            difficultyMap.put(difficulty.getValue(), difficulty);
        }
    }

    public static Difficulty fromString(String difficulty) {
        return difficultyMap.get(difficulty);
    }
}
