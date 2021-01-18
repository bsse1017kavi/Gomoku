package gamePackage;

import java.util.Scanner;

public class GomokuLogic
{
    public static int dimension = 15;

    public static int [][] board = new int[dimension][dimension];
    boolean isHuman = true;

    AI ai = new AI();

    public GomokuLogic()
    {
        for(int i=0;i<dimension;i++)
        {
            for(int j=0;j<dimension;j++)
            {
                //initialize all cells with -1, Human means 0 and Ai means 1
                board[i][j] = -1;
            }
        }

    }

    public void play()
    {
        WinStates result;

        int i = 0;

        while(true)
        {
            if(i==3) break;

            giveTurn();

            result = checkWin();

            if(result!=WinStates.Continue)
            {
                break;
            }

            i++;
        }


    }

    public void giveTurn()
    {
        if(isHuman)
        {
            while(true)
            {
                int x,y;
                Scanner scanner = new Scanner(System.in);

                x = scanner.nextInt();
                y = scanner.nextInt();

                //System.out.println(x + " " + y);

                //scanner.close();

                if(validate_input(x,y))
                {
                    board[x][y] = 0;
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
            ai.makeMove();
        }
    }

    public WinStates checkWin()
    {
        return WinStates.Continue;
    }

    public static boolean validate_input(int x, int y)
    {
        if(board[x][y] == -1)
        {
            return true;
        }

        else return false;
    }
}
