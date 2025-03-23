package main.api;

import main.boards.TicTacToeBoard;
import main.game.*;
import main.game.Board;
import main.game.Move;
import main.game.Player;

public class GameEngine {

    public Board start(String type)
    {

        if(type.equals("TicTacToe"))
        {
            return new TicTacToeBoard();
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    public void move(Board board, Move move)
    {
        if(board instanceof TicTacToeBoard)
        {
            board.move(move);
        }
        else
        {
            throw new IllegalArgumentException();
        }


    }


}

