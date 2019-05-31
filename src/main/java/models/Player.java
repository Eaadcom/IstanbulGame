package models;

public class Player {

    private String name;

    private int rubies = 0;
    private int lira = 0;
    private int carUpgrades = 0;
    private String teamColor;

    private int spices = 0;
    private int fruits = 0;
    private int jewels = 0;
    private int fabrics = 0;

    private int assistants = 4;
    private int position;

    private boolean greenTile = false;
    private boolean redTile = false;
    private boolean blueTile = false;
    private boolean yellowTile = false;

    ////

    public void Player(String name){
        this.name = name;
    }

    ////

    public void pay(int amount, Player player){
        if (amount > this.lira){}else{
        lira -= amount;
        player.addRubysLiras("lira", amount);
    }}

    public void addRubysLiras(String g, int amount){
        switch(g) {
            case "ruby":
                rubies += amount;
                break;
            case "lira":
                lira += amount;
                break;
            default:
                // code block
    }}

    public void addGoods(String nameOfGoods, int goodsAmount){
        switch(nameOfGoods) {
            case "spice":
                spices += goodsAmount;
                break;
            case "fruit":
                fruits += goodsAmount;
                break;
            case "jewel":
                jewels += goodsAmount;
                break;
            case "fabric":
                fabrics += goodsAmount;
                break;
            default:
                // code block
    }}

    public void addMosqueTile(String colorOfTile){
        switch(colorOfTile) {
            case "green":
                greenTile = true;
                break;
            case "blue":
                blueTile = true;
                break;
            case "red":
                redTile = true;
                break;
            case "yellow":
                yellowTile = true;
                break;
            default:
                // code block
    }}

    public void changePosition(int pos){
        if(position != pos){
            position = pos;
    }}

//    public void placeAssistant(Location l){
//        if(assistants > 0){
//            assistants =- 1;
//            l.placeAssistant(teamColor);
//
//    }}

    public void setName(String name){
        this.name = name;
    }

    //    public static void getVariables() {
//        LinkedHashMap<String, String> variables = new LinkedHashMap<>();
//
//        variables.put("className", className);
//        variables.put("username", username);
//        variables.put("gemstones", Integer.toString(gemstones));
//        FirebaseController.firebaseWriter(variables);
//    }
//
//    public static void updateVariables(HashMap variables){
//
//    }


}
