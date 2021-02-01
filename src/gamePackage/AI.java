package gamePackage;

public class AI
{

    public Move makeMove(Board board)
    {

        Node root = new Node(board, null, 1, null); // it all starts here
        root.populateChildren(1, 5); // build tree
        board.makeMove(root.nextMove, 1); // the next move is automatically calculated and played here
        System.out.println(root.nextMove);
        System.out.println(root.value);
        System.out.println(root.getChildAfter(root.nextMove).value);
        return root.nextMove;
    }


}
