package gamePackage;

public class AI
{
    Utils utils = new Utils();
    int depth = 1;

    public void makeMove(Board board)
    {

        Node root = new Node(board, null, 1);
        root.populateChildren(1, 5);
        board.makeMove(root.nextMove, 1);
        System.out.println(root.nextMove);
    }


}
