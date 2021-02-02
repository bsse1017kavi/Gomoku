package gamePackage;

import java.util.ArrayList;

import static gamePackage.GomokuLogic.dimension;
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

    public WinStates checkCertainWin(Board board, int turn)
    {

        int hwin = getHorizontalCount(board, turn);
        int dwin = getDiagCount(board, turn);
        int vwin = getVerticalCount(board, turn);

        if(!(hwin == 0 && dwin == 0 && vwin == 0) && turn == 0)
            return WinStates.Human_Win;
        else if(!(hwin == 0 && dwin == 0 && vwin == 0) && turn == 1)
            return WinStates.AI_Win;

        if(isStalemate(board))
            return WinStates.Draw;

        hwin = getHorizontalCount(board, (turn+1)%2);
        dwin = getDiagCount(board, (turn+1)%2);
        vwin = getVerticalCount(board, (turn+1)%2);
        int temp = 0;
        /*if(hwin == (turn+1)%2)
            temp++;
        if(dwin == (turn+1)%2)
            temp++;
        if(vwin == (turn+1)%2)
            temp++;*/
        temp += hwin + dwin + vwin;

        if(temp >= 2)
        {
            if(turn == 0)
                return WinStates.AI_Win;
            else
                return WinStates.Human_Win;
        }


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

    private int getHorizontalCount(Board board, int turn) {
        int i1=0, j1=0;
        for(int i=0; i<board.dimension; i++)
        {
            int count = 0;
            for(int j =0; j<board.dimension-1; j++)
            {
                if(board.board[i][j] == board.board[i][j+1]  && board.board[i][j]  == turn)
                {
                    if(count == 0)
                    {
                        i1= i;
                        j1= j;
                    }
                    count++;
                }
                else
                    count = 0;
                if(count == winCount-1)
                {
                    int openEnds = 0;
                    if(j1!= 0 && board.board[i1][j1-1] == -1)
                        openEnds++;
                    if((j < board.dimension - 2) && board.board[i][j+1+1] == -1)
                        openEnds++;
                    return openEnds;
                }
            }
        }
        return 0;
    }

    private int getVerticalCount(Board board, int turn) {
        int i1=0, j1=0;
        for(int i=0; i<board.dimension; i++)
        {
            int count = 0;
            for(int j =0; j<board.dimension-1; j++)
            {
                if(board.board[j][i] == board.board[j+1][i] && board.board[j][i] == turn)
                {
                    if(count == 0)
                    {
                        i1= i;
                        j1= j;
                    }
                    count++;
                }
                else
                    count = 0;
                if(count == winCount-1)
                {
                    int openEnds = 0;
                    if(board.board[j1-1][i1] == -1)
                        openEnds++;
                    if((j < board.dimension - 2) && board.board[j+1+1][i] == -1)
                        openEnds++;
                    return openEnds;
                }
            }
        }
        return 0;
    }

    private int getDiagCount(Board board, int turn) {
        int i1=0, j1=0;

        for(int i=0; i<board.dimension; i++)
        {
            for(int j=0; j<board.dimension; j++) {
                if(i!=0 && j!=0)
                    break;
                int count = 0;
                for(int k = 0; i+k+1<board.dimension && j+k+1<board.dimension; k++)
                {
                    if(board.board[i+k][j+k]==board.board[i+k+1][j+k+1]  && board.board[i+k][j+k] == turn)
                    {
                        if(count == 0)
                        {
                            i1= i+k-1;
                            j1= j+k-1;
                        }
                        count++;
                    }
                    else
                        count = 0;
                    if(count == winCount-1)
                    {
                        int openEnds = 0;
                        if(i1 != -1 && j1!= -1 && board.board[i1][j1] == -1)
                            openEnds++;
                        if((i+k+2<board.dimension && j+k+2<board.dimension-1) && board.board[i+k+2][j+k+2] == -1)
                            openEnds++;
                        return openEnds;
                    }
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
                    {
                        if(count == 0)
                        {
                            i1= i-k+1;
                            j1= j+k-1;
                        }
                        count++;
                    }
                    else
                        count = 0;
                    if(count == winCount-1)
                    {
                        int openEnds = 0;
                        if(i1 != -1 && j1!= -1 && i1<dimension && j1<dimension && board.board[i1][j1] == -1)
                            openEnds++;
                        if((i-k-1>-1 && j+k+2<board.dimension) && board.board[i-k-2][j+k+2] == -1)
                            openEnds++;
                        return openEnds;
                    }
                }
            }
        }
        return 0;
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

    public int getDistFromCenter(Move move) {
        double x2 = (4.5 - move.x) * (4.5 - move.x);
        double y2 = (4.5 - move.y) * (4.5 - move.y);
        int res = (int)(Math.sqrt(x2+y2)) * 8;
        return res;

    }

}
