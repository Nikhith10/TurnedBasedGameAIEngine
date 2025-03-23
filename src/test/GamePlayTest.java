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
    RuleEngine ruleEngine;
    @Before
    public void setUp()
    {
         ruleEngine = new RuleEngine();
         gameEngine = new GameEngine();

    }

    @Test
    public void CheckForRowWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0},{1,1},{1,2}};
        int [][] secondPlayerMoves = new int [][]{{0,0},{0,1},{0,2}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());

    }
    @Test
    public void CheckForColWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0},{1,0},{2,0}};
        int [][] secondPlayerMoves = new int [][]{{0,1},{0,2},{1,1}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void CheckForDiagWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0},{1,1},{2,2}};
        int [][] secondPlayerMoves = new int [][]{{0,1},{0,2},{1,0}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void CheckForRevDiagWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,2},{1,1},{2,0}};
        int [][] secondPlayerMoves = new int [][]{{0,0},{0,1},{1,0}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void CheckForComputerWin()
    {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0},{1,1},{2,0}};
        int [][] secondPlayerMoves = new int [][]{{0,0},{0,1},{0,2}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());
    }
    private void playGame(Board board,int [][] firstPlayerMoves, int[][] secondPlayerMoves)
    {

        int row,col;
        Scanner scanner = new Scanner(System.in);
        int next = 0;

        while(!ruleEngine.getState(board).isOver())
        {

            Player first = new Player("X"),second = new Player("O");
            System.out.println("Make your Move");
            System.out.println(board);
            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];

            Move firstPlayerMove = new Move( new Cell(row,col),first);
            gameEngine.move(board,firstPlayerMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver())
            {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondPlayerMove = new Move( new Cell(sRow,sCol),second);
                gameEngine.move(board,secondPlayerMove);
            }
            next++;


        }


    }
}
