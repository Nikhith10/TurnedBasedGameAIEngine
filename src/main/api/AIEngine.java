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
            Move suggestion;
            if(isStarting(board1,3))
            {
                suggestion = getBasicMove(board1,computer);

            }
            else
            {
                suggestion = getSmartMove(board1,computer);
            }
            if(suggestion!=null)
            {
                return suggestion;
            }
            throw new IllegalArgumentException();

        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    public boolean isStarting(TicTacToeBoard board,int threshold)
    {
        int count =0;
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)!=null)
                {
                    count++;
                }
            }
        }
        return count<threshold;

    }
    public Move getSmartMove(TicTacToeBoard board,Player player)
    {
        RuleEngine ruleEngine = new RuleEngine();
        //Attacking Moves
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move = new Move(new Cell(i,j),player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver())
                    {
                        return move;
                    }
                }
            }
        }
        //Defensive Moves
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move = new Move(new Cell(i,j),player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver())
                    {
                        return new Move(new Cell(i,j),player);
                    }
                }
            }
        }
        return null;
    }
    public Move getBasicMove(TicTacToeBoard board,Player player)
    {
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    return new Move(new Cell(i,j),player);
                }
            }
        }
        return null;
    }

}
