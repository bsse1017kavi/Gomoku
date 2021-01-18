package gamePackage;

public class AI
{
    public void makeMove()
    {
        for(int i=0;i<GomokuLogic.dimension;i++)
        {
            for(int j=0;j<GomokuLogic.dimension;j++)
            {
                if(GomokuLogic.validate_input(i,j))
                {
                    GomokuLogic.board[i][j] = 1;
                    return;
                }
            }
        }
    }
}
