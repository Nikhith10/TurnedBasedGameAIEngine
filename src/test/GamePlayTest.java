package test;

import main.api.AIEngine;
import main.api.GameEngine;
import main.api.RuleEngine;
import main.game.Board;
import main.game.Cell;
import main.game.Move;
import main.game.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class GamePlayTest {

    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;
    @Before
    public void setUp()
    {
         aiEngine = new AIEngine();
         ruleEngine = new RuleEngine();
         gameEngine = new GameEngine();

    }

    @Test
    public void CheckForRowWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0},{1,1},{1,2}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());

    }
    @Test
    public void CheckForColWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0},{0,1},{0,2}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void CheckForDiagWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0},{1,1},{2,2}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void CheckForRevDiagWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,2},{1,1},{2,0}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void CheckForComputerWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0},{1,1},{2,1}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());
    }
    private void playGame(Board board,int [][]moves)
    {

        int row,col;
        Scanner scanner = new Scanner(System.in);
        int next = 0;

        while(!ruleEngine.getState(board).isOver())
        {

            Player human = new Player("X"),computer = new Player("O");
            System.out.println("Make your Move");
            System.out.println(board);
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move oppMove = new Move( new Cell(row,col),human);
            gameEngine.move(board,human,oppMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver())
            {
                Move computerMove = aiEngine.suggestMove(computer,board);
                gameEngine.move(board,computer,computerMove);
            }


        }


    }
}
