import engine.Board;
import engine.Colour;
import engine.Player;
import engine.Square;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.MouseEvent;


public class JavaFocus extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Focus Java Engine");

        /*Enter player name scene*/
        HBox p1Line = new HBox();
        HBox p2Line = new HBox();
        VBox table = new VBox();

        TextField player1Field = new TextField("Enter Player 1 name: ");
        //player1Field.setPrefColumnCount(100);
        player1Field.setPrefWidth(800);
        TextField player2Field = new TextField("Enter Player 2 name: ");
        //player2Field.setPrefColumnCount(100);
        player2Field.setPrefWidth(800);
        StackPane playerName = new StackPane();

        Player player1 = new Player("Player 1", 18, Colour.RED);
        Player player2 = new Player("Player 2", 18, Colour.GREEN);

        Button p1Button = new Button("Enter");
        Button p2Button = new Button("Enter");
        Button Submit = new Button("Submit");

        p1Line.getChildren().add(player1Field);
        p1Line.getChildren().add(p1Button);

        p2Line.getChildren().add(player2Field);
        p2Line.getChildren().add(p2Button);

        table.getChildren().add(p1Line);
        table.getChildren().add(p2Line);
        table.getChildren().add(Submit);

        playerName.getChildren().add(table);

        p1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!player1Field.getText().isEmpty())
                    player1.setName(player1Field.getText());
                else if(player1Field.getText().equals("Enter Player 1 name: ")){
                    player1Field.setText("Player 1");
                }
            }
        });

        p2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!player2Field.getText().isEmpty())
                    player2.setName(player2Field.getText());
                else if(player2Field.getText().equals("Enter Player 2 name: ")){
                    player2Field.setText("Player 2");
                }

            }
        });

        Submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gamePlaying(primaryStage);
            }
        });

        Scene NamingScene = new Scene(playerName, 1600, 1000);
        primaryStage.setScene(NamingScene);
        primaryStage.show();
    }

    private void gamePlaying(Stage primaryStage){
        Board board = new Board();
        Parent parent = createBoard(board);
        board.prettyPrint();

        StackPane layout = new StackPane();
        layout.getChildren().add(parent);

        Scene gamePlay = new Scene(layout, 1600, 1000);
        primaryStage.setScene(gamePlay);
        primaryStage.show();
    }

    public static Parent createBoard(Board board){
        GridPane gameBoardGrid = new GridPane();
        gameBoardGrid.setPrefSize(755, 755);
        Square[][] gameBoard = board.getBoard();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                Square square = gameBoard[i][j];
                if(square.getSquareType().isValid()){
                    Rectangle tile = new Rectangle(50, 50);

                    if(square.getSquareColour().isUnOccupied()){
                        tile.setFill(Color.WHITE);
                        tile.setStroke(Color.BLACK);

                        Text text = new Text();
                        text.setFont(Font.font(40));
                        gameBoardGrid.add(new StackPane(tile, text), j, i);
                        //GridPane.setRowIndex(tile, i);
                        //GridPane.setColumnIndex(tile, j);
                        //tile.setOnMouseClicked(event -> drawMove(text));

                        //TODO Deal with picking squares as a player when it is your turn.

                    }
                    else if(square.getSquareColour().isGreen()){
                        tile.setFill(Color.GREEN);
                        tile.setStroke(Color.BLACK);

                        Text text = new Text();
                        text.setFont(Font.font(40));
                        gameBoardGrid.add(new StackPane(tile, text), j, i);
                        //GridPane.setRowIndex(tile, i);
                        //GridPane.setColumnIndex(tile, j);
                        tile.setOnMouseClicked(event -> drawMove(text));

                        //TODO Deal with picking squares as a player when it is your turn.

                    }
                    else{
                        tile.setFill(Color.RED);
                        tile.setStroke(Color.BLACK);

                        Text text = new Text();
                        text.setFont(Font.font(40));
                        gameBoardGrid.add(new StackPane(tile, text), j, i);
                        //GridPane.setRowIndex(tile, i);
                        //GridPane.setColumnIndex(tile, j);
                        tile.setOnMouseClicked(event -> drawMove(text));
                        //TODO Deal with picking squares as a player when it is your turn.
                    }
                }
            }
        }
        return gameBoardGrid;
    }

    private static void drawMove(Text text) {
        text.setText("O");
        text.setFill(Color.BLACK);
    }
}
