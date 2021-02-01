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

        /*if(children.size() != 0)
        {
            for(Node n: children)
                n.populateChildren(depth + 1, max);
            return;
        }*/

        WinStates temp = utils.checkWin(board);
        if(depth > max || temp != WinStates.Continue) {
            value = evaluate(temp, depth);
            return;
        }

        /*WinStates temp = utils.checkWin(board);
        if(depth > max) {
            value = evaluate(temp);
            return;
        }*/

        boolean breakCondition = false;

        for(int i=0; i<board.dimension && !breakCondition; i++)
        {
            for(int j = 0; j<board.dimension && !breakCondition; j++)
            {

                shit();

                Move newMove = new Move(i, j);
                if(board.validate_input(newMove))
                {
                    // shit();

                    board.makeMove(newMove, turn);
                    Node node = new Node(board, newMove, (turn+1)%2, this);
                    children.add(node);

                        node.populateChildren(depth+1, max);
                        updateValue(node);

                    board.undoMove(newMove);
                    breakCondition = evaluateToBreak();
                }
            }
        }






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

        else if(turn == 0 && value >= node.value)
        {
            // value = Math.min(value, node.value);
            flag = true;
        }
        else if(turn == 1 && value <= node.value)
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

    private void shit() {
        AI.shit++;
        if(AI.shit % 100000 == 0)
            System.out.println(AI.shit / 1000000.0);
    }

    int evaluate(WinStates states, int depth) {
        /// WinStates states = utils.checkWin(board);
        if(states == WinStates.AI_Win)
            return 1000 - depth*30;

        else if(states == WinStates.Human_Win)
            return -1000 + depth * 30;
        else if(states == WinStates.Draw)
            return 0;
        else
            return -1;
    }

    Node getChildAfter(Move move) {
        for(Node child: children) {
            if(child.move.equals(move))
                return child;
        }
        return null;
    }

}

class Move {
    int x, y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Move move) {
        return this.x == move.x && this.y == move.y;
    }

    @Override
    public String toString() {
        return "Move{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
