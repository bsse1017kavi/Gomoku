package gamePackage;

import java.util.ArrayList;

public class Node {
    Node parent;
    ArrayList<Node> children = new ArrayList<>();
    Board board;
    Move move, nextMove;
    Integer turn, value = null;
    Utils utils = new Utils();


    public Node(Board board, Move move, int turn, Node parent) {
        this.board = board;
        this.move = move;
        this.turn = turn;
        this.parent = parent;
    }

    public void populateChildren(int depth, int max){
        WinStates temp = utils.checkWin(board); // if someone has won
        if(depth > max || temp != WinStates.Continue) {
            value = evaluate(temp, depth); // evaluation function
            return; // return if the game has ended or if depth > max
        }

        boolean breakCondition = false;

        /*for(int i=0; i<board.dimension && !breakCondition; i++)
        {
            for(int j = 0; j<board.dimension && !breakCondition; j++)
            {
                shit();

                Move newMove = new Move(i, j);
                if(board.validate_input(newMove))
                {
                    board.makeMove(newMove, turn);
                    Node node = new Node(board, newMove, (turn+1)%2, this);
                    children.add(node);
                    node.populateChildren(depth+1, max);
                    updateValue(node);
                    board.undoMove(newMove);
                    breakCondition = evaluateToBreak();
                }

            }
        }*/

        int dim = GomokuLogic.dimension;
        int hdim = dim/2;

        // this loop checks boxes one by one from the center
        for(int k=1; k<hdim+1; k++) {
            for(int i=hdim-k; i<Math.min(hdim+k, dim) && !breakCondition; i++)
            {
                for(int j = hdim-k; j<Math.min(hdim+k, dim) && !breakCondition; j++)
                {
                    Move newMove = new Move(i, j); // just to pass params
                    if(board.validate_input(newMove))
                    {
                        board.makeMove(newMove, turn);
                        Node node = new Node(board, newMove, (turn+1)%2, this); //  create the new child
                        children.add(node);
                        node.populateChildren(depth+1, max); // gives us the branch value
                        updateValue(node);
                        board.undoMove(newMove);
                        breakCondition = evaluateToBreak(); // alpha beta pruning condition
                    }

                }
            }
        }

        /*Node n;
        if(turn == 0)
        {
            n = utils.getMinChildren(children);
        }
        else
        {
            n = utils.getMaxChildren(children);
        }
        nextMove = n.move;
        value = n.value;*/


    }

    private boolean evaluateToBreak()
    {
        if(parent == null || parent.value == null)
            return false;

        if(parent.turn == 0) {
            return parent.value <= value;
        }

        else {
            return parent.value >= value;
        }
    }

    private void updateValue(Node node) {
        boolean flag = false;

        if(value == null)
        {
            value = node.value;
            flag = true;
        }

        else if(turn == 0 && value > node.value) // min
        {
            // value = Math.min(value, node.value);
            flag = true;
        }
        else if(turn == 1 && value < node.value) // max
        {
            // value = Math.max(value, node.value);
            flag = true;
        }
        if(flag)
        {
            nextMove = node.move;
            value = node.value;
        }
    }

    int evaluate(WinStates states, int depth) {
        /// WinStates states = utils.checkWin(board);
        if(states == WinStates.AI_Win)
            return 1000 - 20*depth;

        else if(states == WinStates.Human_Win)
            return -1000 + 20*depth;
        else if(states == WinStates.Draw)
            return 0;
        /*else
            return utils.getDistFromCenter(move);*/
        else
            return -1;
    }

    Node getChildAfter(Move move) {
        for(Node child: children) {
            if(child.move.equals(move))
                return child;
        }
        return null;
    } // useless

}

