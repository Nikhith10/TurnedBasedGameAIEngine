package main.api;

import main.boards.TicTacToeBoard;
import main.game.Board;
import main.game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameState getState(Board board)
    {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";
            BiFunction<Integer,Integer, String> getNextRow = (i, j) -> board1.getSymbol(i,j);
            BiFunction<Integer,Integer, String> getNextCol = (i, j) -> board1.getSymbol(j,i);
            GameState firstCharacter1 = isVictory(getNextRow);
            if(firstCharacter1!=null)
            {
                return firstCharacter1;
            }
            GameState firstCharacter2 = isVictory(getNextCol);

            if(firstCharacter2!=null)
            {
                return firstCharacter2;
            }

            firstCharacter = board1.getSymbol(0,0);
            boolean diagComplete= firstCharacter!=null;
            for (int i = 0; i < 3; i++) {

                if (firstCharacter!=null && !firstCharacter.equals(board1.getSymbol(i,i))) {
                    diagComplete = false;
                    break;
                }

            }
            if(diagComplete)
            {
                return new GameState(true,firstCharacter);
            }
            firstCharacter = board1.getSymbol(0,2);
            boolean revDiagComplete= firstCharacter!=null;
            for (int i = 0; i < 3; i++) {

                if (firstCharacter!=null && !firstCharacter.equals(board1.getSymbol(i,2-i))) {
                    revDiagComplete = false;
                    break;
                }

            }
            if(revDiagComplete)
            {
                return new GameState(true,firstCharacter);
            }
            int countOfFilledCells=0;
            for(int i=0;i<3;i++)
            {
                for (int j=0;j<3;j++)
                {
                    if(board1.getSymbol(i,j)!=null)
                    {
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells ==9)
            {
                return new GameState(true,"-");
            }
            else
            {
                return new GameState(false,"-");
            }

        }
        else
        {
            return new GameState(false,"-");
        }

    }

    private GameState isVictory(BiFunction<Integer, Integer, String> next) {
        for (int i = 0; i < 3; i++) {
            boolean possibleStreak = true;
                for (int j = 0; j < 3; j++) {
                    if (next.apply(i,j)==null || !next.apply(i,0).equals(next.apply(i,j))) {
                        possibleStreak = false;
                        break;
                    }
                }
            if(possibleStreak)
            {
                return new GameState(true,next.apply(i,0));
            }
        }
        return null;
    }

}
