package gamePackage;

import java.util.ArrayList;

import static gamePackage.GomokuLogic.winCount;

public class Utils {
    public boolean validate_input(Board board, int x, int y)
    {
        if(board.board[x][y] == -1)
        {
            return true;
        }

        else return false;
    }

    public WinStates checkWin(Board board)
    {

        int hwin = getHorizontalWin(board);
        int dwin = getDiagWin(board);
        int vwin = getVerticalWin(board);

        if(hwin == 0 || dwin == 0 || vwin == 0)
            return WinStates.Human_Win;
        else if(hwin == 1 || dwin == 1 || vwin == 1)
            return WinStates.AI_Win;

        if(isStalemate(board))
            return WinStates.Draw;

        return WinStates.Continue;
    }

    private int getHorizontalWin(Board board) {
        for(int i=0; i<board.dimension; i++)
        {
            int count = 0;
            for(int j =0; j<board.dimension-1; j++)
            {
                if(board.board[i][j] == board.board[i][j+1]  && board.board[i][j]  != -1)
                    count++;
                else
                    count = 0;
                if(count == winCount)
                    return board.board[i][j];
            }
        }
        return -1;
    }

    private int getVerticalWin(Board board) {
        for(int i=0; i<board.dimension; i++)
        {
            int count = 0;
            for(int j =0; j<board.dimension-1; j++)
            {
                if(board.board[j][i] == board.board[j+1][i] && board.board[j][i] != -1)
                    count++;
                else
                    count = 0;
                if(count == winCount)
                    return board.board[j][i];
            }
        }
        return -1;
    }

    private int getDiagWin(Board board) {
        for(int i=0; i<board.dimension; i++)
        {
            for(int j=0; j<board.dimension; j++) {
                if(i!=0 && j!=0)
                    break;
                int count = 0;
                for(int k = 0; i+k+1<board.dimension && j+k+1<board.dimension; k++)
                {
                    if(board.board[i+k][j+k]==board.board[i+k+1][j+k+1]  && board.board[i+k][j+k] != -1)
                        count++;
                    else
                        count = 0;
                    if(count == winCount)
                        return board.board[i+k][j+k];
                }
            }
        }

        for(int i=board.dimension-1; i>-1; i=-1)
        {
            for(int j=board.dimension-1; j> -1; j--) {
                // if(i!=dimension-1 && j!=dimension-1) break;

                int count = 0;
                for(int k = 0; i-k-1>-1 && j+k+1<board.dimension; k++)
                {
                    if(board.board[i-k][j+k]==board.board[i-k-1][j+k+1]  && board.board[i-k][j+k] != -1)
                        count++;
                    else
                        count = 0;
                    if(count == winCount)
                        return board.board[i-k][j+k];
                }
            }
        }
        return -1;
    }

    private boolean isStalemate(Board board) {
        for(int i=0; i<board.dimension; i++)
        {
            for(int j=0; j<board.dimension; j++)
            {
                if(board.board[i][j] == -1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Node getMaxChildren(ArrayList<Node> children)
    {
        int val = -999999;
        Node maxChild = null;

        for(Node n: children)
        {
            if(val < n.value)
            {
                val = n.value;
                maxChild = n;
            }
        }

        return maxChild;
    }

    public Node getMinChildren(ArrayList<Node> children)
    {
        int val = 999999;
        Node minChild = null;

        for(Node n: children)
        {
            if(val > n.value)
            {
                val = n.value;
                minChild = n;
            }
        }

        return minChild;
    }

}
