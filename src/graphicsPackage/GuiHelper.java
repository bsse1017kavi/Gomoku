package graphicsPackage;

import gamePackage.*;

import java.util.Scanner;

public class GuiHelper
{

    Board board = new Board(GomokuLogic.dimension);
    Utils utils = new Utils();
    AI ai = new AI();
    Move lastHumanMove = null;

    public GuiHelper() {
        for(int i=0;i<GomokuLogic.dimension;i++)
        {
            for(int j=0;j<GomokuLogic.dimension;j++)
            {
                //initialize all cells with -1, Human means 0 and Ai means 1
                board.board[i][j] = -1;
            }
        }
    }

    public void humanMove(Move move) {
        lastHumanMove = move;
        board.makeMove(move, 0);
    }

    public Move AIMove() {
        return  ai.makeMove(board);
    }

    public boolean continueGame()
    {
        WinStates result;
        result = utils.checkWin(board);

        if(result!=WinStates.Continue)
        {
            if(result == WinStates.Draw)
                System.out.println("Draw!");
            if(result == WinStates.AI_Win)
                System.out.println("AI Wins");
            if(result == WinStates.Human_Win)
                System.out.println("Human Wins");
            return false;
        }

        return true;



    }

}

