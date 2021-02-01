package graphicsPackage;

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
    GuiHelper helper = new GuiHelper();


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
        Move move = helper.AIMove();
        Circle circle = new Circle(cellPixelSize/2);
        circle.setFill(Color.GREEN);
        grid.add(circle, move.y, move.x);
        helper.continueGame();
    }

    void handleUserInput(int x, int y) {

        Circle circle = new Circle(cellPixelSize/2);
        circle.setFill(Color.RED);
        grid.add(circle, x, y);
        Move move = new Move(y, x);
        helper.humanMove(move);
        System.out.println(move);
        if(helper.continueGame())
            displayAIInput();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
