package graphicsPackage;

import gamePackage.AI;
import gamePackage.Board;
import gamePackage.GomokuLogic;
import gamePackage.Move;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainGUI extends Application
{
    int cellPixelSize = 50;
    int dimension = GomokuLogic.dimension;

    GridPane grid = new GridPane();
    Scene scene = new Scene(grid, cellPixelSize*dimension, cellPixelSize*dimension);
    GuiHelper helper = new GuiHelper(); // communicates i/o with logic classes


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            //grid.setGridLinesVisible(true);

            for(int i=0;i<dimension;i++)
            {
                for(int j=0;j<dimension;j++)
                {
                    Rectangle rectangle = new Rectangle(cellPixelSize,cellPixelSize);
                    rectangle.setFill(Color.WHITE);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStyle("-fx-border-color: black;");
                    grid.add(rectangle, i, j);
                }
            }

            grid.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    int x = (int)Math.floor((event.getSceneX()/cellPixelSize));
                    int y = (int)Math.floor((event.getSceneY()/cellPixelSize));
                    if(helper.utils.validate_input(helper.board,y,x))
                    {
                        handleUserInput(x, y);
                    }

                }
            });

            primaryStage.setScene(scene);
            primaryStage.show();


        }catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    void displayAIInput() {
        Move move = helper.AIMove(); // make ai move
        Circle circle = new Circle(cellPixelSize/2); // updates ui
        circle.setFill(Color.GREEN);
        grid.add(circle, move.y, move.x);
        helper.continueGame(); // if the game has ended
    }

    void handleUserInput(int x, int y) { // x y coordinate of user input

        Circle circle = new Circle(cellPixelSize/2);
        circle.setFill(Color.RED);
        grid.add(circle, x, y);
        Move move = new Move(y, x);
        helper.humanMove(move);
        System.out.println(move);

        //optimize(move);

        if(helper.continueGame()) // if the game has ended
            displayAIInput(); // this line needs to be executed concurrently
    }

    private void optimize(Move move) {
        if((move.x == 2 || move.y == 2 || move.x == 7 || move.y == 7 ) && AI.hdim <4)
        {
            AI.hdim = 4;
        }
        if((move.x == 1 || move.y == 1 || move.x == 8 || move.y == 8 ) && AI.hdim <5)
        {
            AI.hdim = 5;
        }
        if((move.x == 0 || move.y == 0 ) && AI.hdim <5)
        {
            AI.hdim = 5;
        }
        System.out.println("S: " + AI.hdim + ", " );
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}


