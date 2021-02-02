package gamePackage;

public class AI
{

    public static int hdim = 3;

    public Move makeMove(Board board)
    {
        /*if(hdim < 5 && t%2==1)
            hdim++;
        t++;*/

        Node root = new Node(board, null, 1, null); // it all starts here
        root.populateChildren(1, 5); // build tree
        board.makeMove(root.nextMove, 1); // the next move is automatically calculated and played here
        System.out.println(root.nextMove);
        System.out.println(root.value);
        System.out.println(root.getChildAfter(root.nextMove).value);
        return root.nextMove;
    }


}
