package main.api;

import main.game.Board;
import main.game.Cell;
import main.game.Move;
import main.game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        int row,col;
        Scanner scanner = new Scanner(System.in);
        while(!ruleEngine.getState(board).isOver())
        {

            Player human = new Player("X"),computer = new Player("O");
            System.out.println("Make your Move");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move( new Cell(row,col),human);
            gameEngine.move(board,oppMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver())
            {
                Move computerMove = aiEngine.suggestMove(computer,board);
                gameEngine.move(board,computerMove);
            }


        }
        System.out.println("Game Result"+ruleEngine.getState(board));
        System.out.println(board);


    }
}
