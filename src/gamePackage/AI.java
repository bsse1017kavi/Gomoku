package gamePackage;

public class AI
{
    Utils utils = new Utils();
    int depth = 1;
    static double shit = 0;

    Node root;

    public AI(Board board) {
        // root = new Node(board, null, 1, null);
    }

    public void makeMove(Board board, Move playerMove)
    {
        /*if(playerMove != null)
            root = root.getChildAfter(playerMove);*/


        Node root = new Node(board, null, 1, null);
        root.populateChildren(1, 6);
        shit = 0;
        board.makeMove(root.nextMove, 1);
        System.out.println(root.nextMove);
        System.out.println(root.value);

        //root = root.getChildAfter(root.nextMove);
    }


}
