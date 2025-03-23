package main.boards;

import main.game.Board;
import main.game.Cell;
import main.game.Move;

public class TicTacToeBoard implements Board {
    String[][] cells = new String[3][3];
    public String getSymbol(int row,int col)
    {
        return cells[row][col];
    }
    public void setCell(Cell cell, String symbol)
    {
        if(cells[cell.getRow()][cell.getCol()]==null)
        {
            cells[cell.getRow()][cell.getCol()] = symbol;
        }
        else
        {
            throw new IllegalArgumentException();
        }


    }
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                result.append(cells[i][j] == null ? "-" : cells[i][j]).append(" ");

            }
            result.append("\n");
        }
        return result.toString();
    }
    @Override
    public void move(Move move)
    {
        setCell(move.getCell(),move.getPlayer().symbol());

    }
    @Override
    public TicTacToeBoard copy()
    {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();

        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++)
            {
                ticTacToeBoard.cells[i][j] = cells[i][j];

            }
        }

        return ticTacToeBoard;
    }

}
