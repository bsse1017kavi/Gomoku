package gamePackage;

import java.util.ArrayList;

import static gamePackage.GomokuLogic.winCount;

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

//        for(int i=0; i<board.dimension && !breakCondition; i++)
//        {
//            for(int j = 0; j<board.dimension && !breakCondition; j++)
//            {
//
//                Move newMove = new Move(i, j);
//                if(board.validate_input(newMove))
//                {
//                    board.makeMove(newMove, turn);
//                    Node node = new Node(board, newMove, (turn+1)%2, this);
//                    children.add(node);
//                    node.populateChildren(depth+1, max);
//                    updateValue(node);
//                    board.undoMove(newMove);
//                    breakCondition = evaluateToBreak();
//                }
//
//            }
//        }

        int dim = GomokuLogic.dimension;
        // int hdim = dim/2;
        int hdim = AI.hdim;
        int hdim2 = dim/2;


        for(int k=1; k<hdim+1; k++) {
            for(int i=hdim2-k; i<Math.min(hdim2+k, dim) && !breakCondition; i++)
            {
                for(int j = hdim2-k; j<Math.min(hdim2+k, dim) && !breakCondition; j++)
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

        states = utils.checkCertainWin(board, turn);
        if(states == WinStates.AI_Win)
            return 700 - 20*depth;

        else if(states == WinStates.Human_Win)
            return -700 + 20*depth;

        else
        {
            int score = 0;
            int count = 0;
            int var = 2;
            for(int i=0; i<board.dimension; i++)
            {
                count = 0;
                for(int j =0; j<board.dimension-1; j++)
                {
                    if(board.board[i][j] == board.board[i][j+1]  && board.board[i][j]  != -1 && board.board[i][j]  == 0)
                        count++;

                    if(count>var)
                    {
                        score -= 200*count;
                    }

                    else
                        count = 0;
                }
            }

            score += count;

            for(int i=0; i<board.dimension; i++)
            {
                count = 0;
                for(int j =0; j<board.dimension-1; j++)
                {
                    if(board.board[j][i] == board.board[j+1][i] && board.board[j][i] != -1 && board.board[j][i] == 0)
                        count++;
                    if(count>var)
                    {
                        score -= 200*count;
                    }
                    else
                        count = 0;

                }
            }

            score += count;

            for(int i=0; i<board.dimension; i++)
            {
                for(int j=0; j<board.dimension; j++) {
                    if(i!=0 && j!=0)
                        break;
                    count = 0;
                    for(int k = 0; i+k+1<board.dimension && j+k+1<board.dimension; k++)
                    {
                        if(board.board[i+k][j+k]==board.board[i+k+1][j+k+1]  && board.board[i+k][j+k] != -1 && board.board[i+k][j+k] == 0)
                            count++;
                        if(count>var)
                        {
                            score -= 200*count;
                        }
                        else
                            count = 0;
                    }
                }
            }

            score += count;

            for(int i=board.dimension-1; i>-1; i=-1)
            {
                for(int j=board.dimension-1; j> -1; j--) {
                    // if(i!=dimension-1 && j!=dimension-1) break;

                    count = 0;
                    for(int k = 0; i-k-1>-1 && j+k+1<board.dimension; k++)
                    {
                        if(board.board[i-k][j+k]==board.board[i-k-1][j+k+1]  && board.board[i-k][j+k] != -1 && board.board[i-k][j+k] == 0)
                            count++;
                        if(count>var)
                        {
                            score -= 200*count;
                        }
                        else
                            count = 0;

                    }
                }
            }

            score += count;

            //System.out.println("Score: " + score);

            return score;

        }

            //return -1;
    }

    Node getChildAfter(Move move) {
        for(Node child: children) {
            if(child.move.equals(move))
                return child;
        }
        return null;
    } // useless

}

