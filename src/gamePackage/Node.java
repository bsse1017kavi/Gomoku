package gamePackage;

import java.util.ArrayList;

public class Node {
    ArrayList<Node> children = new ArrayList<>();
    Board board;
    Move move, nextMove;
    int turn, value;
    Utils utils = new Utils();


    public Node(Board board, Move move, int turn) {
        this.board = board;
        this.move = move;
        this.turn = turn;
    }

    public void populateChildren(int depth, int max){
        WinStates temp = utils.checkWin(board);
        if(depth > max || temp != WinStates.Continue) {
            value = evaluate(temp);
            return;
        }

        for(int i=0; i<board.dimension; i++)
        {
            for(int j = 0; j<board.dimension; j++)
            {
                Move newMove = new Move(i, j);
                if(board.validate_input(newMove))
                {
                    board.makeMove(newMove, turn);
                    Node node = new Node(board, newMove, (turn+1)%2);
                    children.add(node);
                    node.populateChildren(depth+1, max);
                    board.undoMove(newMove);
                }
            }
        }

        Node n;
        if(turn == 0)
        {
            n = utils.getMinChildren(children);
        }
        else
        {
            n = utils.getMaxChildren(children);
        }
        nextMove = n.move;
        value = n.value;


    }

    int evaluate(WinStates states) {
        /// WinStates states = utils.checkWin(board);
        if(states == WinStates.AI_Win)
            return 1000;

        else if(states == WinStates.Human_Win)
            return -1000;
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
