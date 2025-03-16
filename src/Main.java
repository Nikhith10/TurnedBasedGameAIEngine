import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

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
            gameEngine.move(board,human,oppMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver())
            {
                Move computerMove = aiEngine.suggestMove(computer,board);
                gameEngine.move(board,computer,computerMove);
            }


        }
        System.out.println("Game Result"+ruleEngine.getState(board));
        System.out.println(board);


    }
}
