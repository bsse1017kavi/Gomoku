package gamePackage;

public class AI
{
    Utils utils = new Utils();
    int depth = 1;
    static double shit = 0;

    public void makeMove(Board board)
    {

        Node root = new Node(board, null, 1);
        root.populateChildren(1, 10);
        board.makeMove(root.nextMove, 1);
        System.out.println(root.nextMove);
    }


}
