package main.api;

import main.boards.TicTacToeBoard;
import main.game.Board;
import main.game.GameState;

public class RuleEngine {
    public GameState getState(Board board)
    {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            String firstCharacter = "-";
            boolean rowComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(i,0);
                rowComplete = firstCharacter!=null;
                if(firstCharacter!=null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(i, j))) {
                            rowComplete = false;
                            break;
                        }
                    }
                }
                if(rowComplete)
                {
                    break;
                }
            }
            if(rowComplete)
            {
                return new GameState(true,firstCharacter);
            }
            boolean colComplete= true;
            for (int i = 0; i < 3; i++) {

                firstCharacter = board1.getCell(0,i);
                colComplete = firstCharacter!=null;
                if(firstCharacter!=null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(j, i))) {
                            colComplete = false;
                            break;

                        }
                    }
                }
                if(colComplete)
                {
                    break;
                }
            }
            if(colComplete)
            {
                return new GameState(true,firstCharacter);
            }
            firstCharacter = board1.getCell(0,0);
            boolean diagComplete= firstCharacter!=null;
            for (int i = 0; i < 3; i++) {

                if (firstCharacter!=null && !firstCharacter.equals(board1.getCell(i,i))) {
                    diagComplete = false;
                    break;
                }

            }
            if(diagComplete)
            {
                return new GameState(true,firstCharacter);
            }
            firstCharacter = board1.getCell(0,2);
            boolean revDiagComplete= firstCharacter!=null;
            for (int i = 0; i < 3; i++) {

                if (firstCharacter!=null && !firstCharacter.equals(board1.getCell(i,2-i))) {
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
                    if(board1.getCell(i,j)!=null)
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
}
