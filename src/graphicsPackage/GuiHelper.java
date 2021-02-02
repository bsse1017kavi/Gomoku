package graphicsPackage;

import gamePackage.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class GuiHelper
{
    Board board = new Board(GomokuLogic.dimension);
    Utils utils = new Utils();
    AI ai = new AI();
    Move lastHumanMove = null;

    public GuiHelper() {
        for(int i=0;i<GomokuLogic.dimension;i++)
        {
            for(int j=0;j<GomokuLogic.dimension;j++)
            {
                //initialize all cells with -1, Human means 0 and Ai means 1
                board.board[i][j] = -1;
            }
        }
    }

    public void humanMove(Move move) {
        lastHumanMove = move;
        board.makeMove(move, 0);
    }

    public Move AIMove() {
        return  ai.makeMove(board);
    }

    public boolean continueGame()
    {
        WinStates result;
        result = utils.checkWin(board);

        if(result!=WinStates.Continue)
        {
            if(result == WinStates.Draw)
            {
                System.out.println("Draw!");

                Stage stage = new Stage();

                GridPane root = new GridPane();

                root.setAlignment(Pos.CENTER);

                Label text = new Label("Draw");
                text.setStyle("-fx-font-size: 20");
                root.addRow(0,text);

                Scene scene = new Scene(root,200, 100);
                stage.setScene(scene);
                stage.show();
            }

            if(result == WinStates.AI_Win)
            {
                System.out.println("You lost!");

                Stage stage = new Stage();

                GridPane root = new GridPane();

                root.setAlignment(Pos.CENTER);

                Label text = new Label("You lost!");
                text.setStyle("-fx-font-size: 20");
                root.addRow(0,text);

                Scene scene = new Scene(root,200, 100);
                stage.setScene(scene);
                stage.show();
            }

            if(result == WinStates.Human_Win)
            {
                System.out.println("You Won!");

                Stage stage = new Stage();

                GridPane root = new GridPane();

                root.setAlignment(Pos.CENTER);

                Label text = new Label("You Won!");
                text.setStyle("-fx-font-size: 20");
                root.addRow(0,text);

                Scene scene = new Scene(root,200, 100);
                stage.setScene(scene);
                stage.show();
            }
            return false;
        }

        return true;



    }

}

