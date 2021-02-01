package gamePackage;

public class AI
{
    Utils utils = new Utils();
    int depth = 1;
    static double shit = 0;

    public Move makeMove(Board board)
    {

        Node root = new Node(board, null, 1, null);
        root.populateChildren(1, 6);
        board.makeMove(root.nextMove, 1);
        System.out.println(root.nextMove);
        System.out.println(root.value);
        System.out.println(root.getChildAfter(root.nextMove).value);
        return root.nextMove;
    }


}
