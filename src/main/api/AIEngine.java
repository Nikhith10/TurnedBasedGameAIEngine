package main.api;

import main.boards.TicTacToeBoard;
import main.game.Board;
import main.game.Cell;
import main.game.Move;
import main.game.Player;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {
        if(board instanceof TicTacToeBoard)
        {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            for(int i=0;i<3;i++)
            {
                for (int j=0;j<3;j++)
                {
                    if(board1.getCell(i,j)==null)
                    {
                        return new Move(new Cell(i,j),computer);
                    }
                }
            }
            throw new IllegalArgumentException();

        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

}
