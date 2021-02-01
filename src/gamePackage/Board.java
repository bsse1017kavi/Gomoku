package gamePackage;

public class Board {
    int dimension;
    public int [][] board;

    public Board(int dimension) {
        this.dimension = dimension;
        board = new int[dimension][dimension];
    }

    public boolean makeMove(Move move, int placeHolder) {
        if(validate_input(move))
        {
            board[move.x][move.y] = placeHolder;
            return true;
        }
        else
            return false;

    }

    public void undoMove(Move move) {
        board[move.x][move.y] = -1;
    }

    public boolean validate_input(Move move)
    {

        return board[move.x][move.y] == -1;
    }



}
