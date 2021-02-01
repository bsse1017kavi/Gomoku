package gamePackage;

import java.util.Scanner;

public class GomokuLogic
{
    public static int dimension = 10, winCount = 4;

    Board board = new Board(dimension);
    Utils utils = new Utils();


    boolean isHuman = false;

    AI ai = new AI();

    public GomokuLogic()
    {
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                //initialize all cells with -1, Human means 0 and Ai means 1
                board.board[i][j] = -1;
            }
        }

    }

    public void play()
    {
        WinStates result;

        while(true)
        {
            giveTurn();

            result = utils.checkWin(board);

            if(result!=WinStates.Continue)
            {
                if(result == WinStates.Draw)
                    System.out.println("Draw!");
                if(result == WinStates.AI_Win)
                    System.out.println("AI Wins");
                if(result == WinStates.Human_Win)
                    System.out.println("Human Wins");
                break;
            }

            isHuman = !isHuman;
        }


    }

    public void giveTurn()
    {
        Move humanMove = null;
        if(isHuman)
        {
            while(true)
            {
                int x,y;
                Scanner scanner = new Scanner(System.in);

                x = scanner.nextInt();
                y = scanner.nextInt();
                humanMove = new Move(x, y);

                //System.out.println(x + " " + y);

                //scanner.close();

                if(utils.validate_input(board, x,y))
                {
                    board.board[x][y] = 0;
                    break;
                }

                else
                {
                    System.out.println("Give input in a cell which is not occupied");
                }
            }
        }

        else
        {
            ai.makeMove(board);
        }
    }

/*
    public WinStates checkWin()
    {
        if(isStalemate())
            return WinStates.Draw;

        int hwin = getHorizontalWin();
        int dwin = getDiagWin();
        int vwin = getVerticalWin();

        if(hwin == 0 || dwin == 0 || vwin == 0)
            return WinStates.Human_Win;
        else if(hwin == 1 || dwin == 1 || vwin == 1)
            return WinStates.AI_Win;



        return WinStates.Continue;
    }

    private int getHorizontalWin() {
        for(int i=0; i<dimension; i++)
        {
            int count = 0;
            for(int j =0; j<dimension-1; j++)
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

    private int getVerticalWin() {
        for(int i=0; i<dimension; i++)
        {
            int count = 0;
            for(int j =0; j<dimension-1; j++)
            {
                if(board.board[j][i] == board.board[j+1][i] && board.board[j][i] != -1)
                    count++;
                else
                    count = 0;
                if(count == winCount)
                    return board.board[i][j];
            }
        }
        return -1;
    }

    private int getDiagWin() {
        for(int i=0; i<dimension; i++)
        {
            for(int j=0; j<dimension; j++) {
                if(i!=0 && j!=0)
                    break;
                int count = 0;
                for(int k = 0; i+k+1<dimension && j+k+1<dimension; k++)
                {
                    if(board.board[i+k][j+k]==board.board[i+k+1][j+k+1]  && board.board[i+k][j+k] != -1)
                        count++;
                    else
                        count = 0;
                    if(count == winCount)
                        return board.board[i+j][j];
                }
            }
        }

        for(int i=dimension-1; i>-1; i=-1)
        {
            for(int j=dimension-1; j> -1; j--) {
                // if(i!=dimension-1 && j!=dimension-1) break;

                int count = 0;
                for(int k = 0; i-k-1>-1 && j+k+1<dimension; k++)
                {
                    if(board.board[i-k][j+k]==board.board[i-k-1][j+k+1]  && board.board[i-k][j+k] != -1)
                        count++;
                    else
                        count = 0;
                    if(count == winCount)
                        return board.board[i+j][j];
                }
            }
        }
        return -1;
    }

    private boolean isStalemate() {
        for(int i=0; i<dimension; i++)
        {
            for(int j=0; j<dimension; j++)
            {
                if(board.board[i][j] == -1)
                {
                    return false;
                }
            }
        }
        return true;
    }
*/


}

