package models;

public class Game {
    private static Board board;
    private static Player[] players;
    private static int turn[][];

    public Game(int numberOfPlayers, String boardLayout){
        board = new Board(boardLayout);
        players = new Player[numberOfPlayers];
        turn = new int[1][1];
    }
}
