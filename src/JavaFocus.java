import engine.Board;
import engine.Colour;
import engine.Player;
import engine.Square;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class JavaFocus extends Application {
   private Board board;
    //This will be used to see whether a move can be submitted
    private int sourceRow = 0, sourceCol = 0, destinationRow = 0, destinationCol = 0;

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
        player1Field.setPrefSize(400, 100);
        player1Field.setFont(new Font(30));

        TextField player2Field = new TextField("Enter Player 2 name: ");
        player2Field.setPrefSize(400, 100);
        player2Field.setFont(new Font(30));

        StackPane playerName = new StackPane();

        Player player1 = new Player("Player 1", 18, Colour.RED);
        Player player2 = new Player("Player 2", 18, Colour.GREEN);

        Button Submit = new Button("Submit");
        Submit.setPrefWidth(200);

        p1Line.getChildren().add(player1Field);
        p2Line.getChildren().add(player2Field);

        table.getChildren().add(p1Line);
        table.getChildren().add(p2Line);
        table.getChildren().add(Submit);

        p1Line.alignmentProperty().setValue(Pos.CENTER);
        p2Line.alignmentProperty().setValue(Pos.CENTER);
        table.alignmentProperty().setValue(Pos.CENTER);
        playerName.getChildren().add(table);

        Submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(player1Field.getText().equals("Enter Player 1 name: ")){
                    player1Field.setText("Player 1");
                }
                player1.setName(player1Field.getText());
                if(player2Field.getText().equals("Enter Player 2 name: ")){
                    player2Field.setText("Player 2");
                }
                player2.setName(player2Field.getText());
                gamePlaying(primaryStage);
            }
        });

        Scene NamingScene = new Scene(playerName, 800, 500);
        primaryStage.setScene(NamingScene);
        this.board = new Board(player1, player2);
        primaryStage.show();
    }

    //This is the game playing screen
    private void gamePlaying(Stage primaryStage){
        AtomicReference<Parent> parent = new AtomicReference<>(createBoard(board));
        board.prettyPrint();


        Pane player1, player2;
        Text p1Name, p2Name, p1Total, p2Total, p1Captured, p2Captured;

        p1Name = new Text(board.getPlayer1().getName());
        p1Name.setFont(new Font(30));

        p2Name = new Text(board.getPlayer2().getName());
        p2Name.setFont(new Font(30));

        p1Total = new Text(Integer.toString(board.getPlayer1().getNumPieces()));
        p1Total.setFont(new Font(30));

        Text p1Colour = new Text(board.getPlayer1().getPlayerColour().toString());
        p1Colour.setFont(new Font(30));

        Text p2Colour = new Text(board.getPlayer2().getPlayerColour().toString());
        p2Colour.setFont(new Font(30));

        p2Total = new Text(Integer.toString(board.getPlayer2().getNumPieces()));
        p2Total.setFont(new Font(30));

        p1Captured = new Text(Integer.toString(board.getPlayer1().getNumPiecesCaptured()));
        p1Captured.setFont(new Font(30));

        p2Captured = new Text(Integer.toString(board.getPlayer2().getNumPiecesCaptured()));
        p2Captured.setFont(new Font(30));

        Text p1NameText = new Text("Player 1 Name: ");
        p1NameText.setFont(new Font(30));
        Text p1TotalText = new Text("Total pieces: ");
        p1TotalText.setFont(new Font(30));
        Text p1CapturedText = new Text("Total captured: ");
        p1CapturedText.setFont(new Font(30));
        Text p1ColourText = new Text("Colour: ");
        p1ColourText.setFont(new Font(30));

        Text p2NameText = new Text("Player 2 Name: ");
        p2NameText.setFont(new Font(30));
        Text p2TotalText = new Text("Total pieces: ");
        p2TotalText.setFont(new Font(30));
        Text p2CapturedText = new Text("Total captured: ");
        p2CapturedText.setFont(new Font(30));
        Text p2ColourText = new Text("Colour: ");
        p2ColourText.setFont(new Font(30));

        VBox p1InfoBox = new VBox(p1Name, p1Total, p1Captured, p1Colour);
        VBox p1InfoBoxText = new VBox(p1NameText, p1TotalText, p1CapturedText, p1ColourText);
        VBox p2InfoBox = new VBox(p2Name, p2Total, p2Captured, p2Colour);
        VBox p2InfoBoxText = new VBox(p2NameText, p2TotalText, p2CapturedText, p2ColourText);

        HBox p1Box = new HBox(p1InfoBoxText, p1InfoBox);
        HBox p2Box = new HBox(p2InfoBoxText, p2InfoBox);

        player1 = new Pane(p1Box);
        player1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        player2 = new Pane(p2Box);
        player2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Button playMove = new Button("Submit Move");
        VBox gameInfoContainer = new VBox(player1, player2, playMove);
        AtomicReference<HBox> gameLayout = new AtomicReference<>(new HBox(parent.get(), gameInfoContainer));



        AtomicReference<StackPane> layout = new AtomicReference<>(new StackPane());
        layout.get().getChildren().add(gameLayout.get());

        AtomicReference<Scene> gamePlay = new AtomicReference<>(new Scene(layout.get(), 1100, 500));
        primaryStage.setScene(gamePlay.get());
        primaryStage.show();

        playMove.setOnMouseClicked(event -> {
            if(destinationCol >= 0 && destinationRow >= 0 && sourceCol >= 0 && sourceRow >= 0){
                int steps = board.getBoard()[destinationCol][destinationRow].getStack().numberOfPieces();
                if(distance(destinationRow, destinationCol, sourceRow, sourceCol) <= steps){
                    board.changePlayer();
                    board.makeMove(destinationRow, destinationCol, sourceRow, sourceCol);
                }
                else
                    System.out.println("Error: Too many steps, submit a new move");
            }
            else    //TODO soon to be changed with a textfield that will communicate with the players. We mainly want a working solution
            {
                System.out.println("Invalid Move, submit a new move");
            }
            this.destinationRow = - 1;
            sourceRow = sourceCol = destinationCol = destinationRow;

            parent.set(createBoard(board));
            gameLayout.set(new HBox(parent.get(), gameInfoContainer, playMove));
            layout.set(new StackPane(gameLayout.get()));
            gamePlay.set(new Scene(layout.get(), 1100, 500));
            primaryStage.setScene(gamePlay.get());
            primaryStage.show();

            //board.prettyPrint();
        });

    }

    private int distance(int destinationRow, int destinationCol, int sourceRow, int sourceCol){
        return Math.abs(destinationRow - sourceRow) + Math.abs(destinationCol - sourceCol);
    }


    public Parent createBoard(Board board){
        GridPane gameBoardGrid = new GridPane();
        gameBoardGrid.setPrefSize(755, 755);
        Square[][] gameBoard = board.getBoard();
        Square selected1 = null;
        Square selected2 = selected1;
        //Holds values of the selected pieces
        final Square[] finalSelect1 = {selected1};
        final Square[] finalSelect2 = {selected2};
        AtomicInteger sourceX = new AtomicInteger();
        AtomicInteger sourceY = new AtomicInteger();
        AtomicInteger destinationX = new AtomicInteger(-1);
        AtomicInteger destinationY = new AtomicInteger(-1);

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                /* We will loop through each place that is allocated */
                Square square = gameBoard[i][j];
                int finalI = i;
                int finalJ = j;

                Colour colour = gameBoard[finalI][finalJ].getSquareColour();
                if(square.getSquareType().isValid()){
                    //If there is a valid piece, we can create a 'tile' and then check the colour
                    Rectangle tile = new Rectangle(50, 50);
                    tile.setY(i);
                    tile.setX(j);

                    if(square.getSquareColour().isUnOccupied()){
                        //If unoccupied then we make the rectangle white to represent it is empty
                        tile.setFill(Color.WHITE);
                        tile.setStroke(Color.BLACK);

                        Text text = new Text();
                        text.setFont(Font.font(40));
                        gameBoardGrid.add(new StackPane(tile, text), j, i);

                        tile.setOnMouseClicked(event ->{
                            if (finalSelect2[0] == gameBoard[finalI][finalJ]){
                                this.destinationRow = -1;
                                this.destinationCol = -1;
                                finalSelect2[0] = null;
                                if (colour.isRed())
                                    deselectMove(tile, Color.RED);
                                else if(colour.isGreen())
                                    deselectMove(tile, Color.GREEN);
                                else
                                    deselectMove(tile, Color.WHITE);
                            }
                            else if(finalSelect1[0] != null && finalSelect2[0] == null){
                                finalSelect2[0] = gameBoard[finalI][finalJ];
                                highlightMove(tile);
                                destinationY.set(finalI);
                                destinationX.set(finalJ);
                                this.destinationRow = destinationY.get();
                                this.destinationCol = destinationX.get();
                                //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                            }
                        });
                        selected1 = finalSelect1[0];
                        selected2 = finalSelect2[0];
                    } else if(square.getSquareColour().isGreen()){
                        //If it is green then we make the tile green
                        tile.setFill(Color.GREEN);
                        tile.setStroke(Color.BLACK);
                        Text text = new Text();
                        text.setFont(Font.font(40));
                        drawMove(text, gameBoard, i, j);
                        gameBoardGrid.add(new StackPane(tile, text), j, i);
                        if(board.getCurrentPlayer().getPlayerColour().isGreen()){
                            tile.setOnMouseClicked(event ->{
                                if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                    finalSelect1[0] = gameBoard[finalI][finalJ];
                                    sourceY.set(finalI);
                                    sourceX.set(finalJ);
                                    this.sourceCol = sourceY.get();
                                    this.sourceRow = sourceX.get();
                                    highlightMove(tile); //Highlights tile
                                }
                                else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                    finalSelect1[0] = null;
                                    this.sourceCol = -1;
                                    this.sourceRow = -1;
                                    deselectMove(tile, Color.GREEN);
                                }
                                else if (finalSelect2[0] == gameBoard[finalI][finalJ]){
                                    finalSelect2[0] = null;
                                    this.destinationRow = -1;
                                    this.destinationCol = -1;
                                    if (colour.isRed())
                                        deselectMove(tile, Color.RED);
                                    else if(colour.isGreen())
                                        deselectMove(tile, Color.GREEN);
                                    else
                                        deselectMove(tile, Color.WHITE);
                                }
                                else if(finalSelect2[0] == null){
                                    finalSelect2[0] = gameBoard[finalI][finalJ];
                                    destinationY.set(finalI);
                                    destinationX.set(finalJ);
                                    this.destinationCol = destinationY.get();
                                    this.destinationRow = destinationX.get();
                                    highlightMove(tile);
                                    //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                }
                            });
                            selected1 = finalSelect1[0];
                            selected2 = finalSelect2[0];
                        }
                        else{
                            tile.setOnMouseClicked(event ->{
                                if (finalSelect2[0] == gameBoard[finalI][finalJ]){
                                    this.destinationRow = -1;
                                    this.destinationCol = -1;
                                    finalSelect2[0] = null;
                                    if (colour.isRed())
                                        deselectMove(tile, Color.RED);
                                    else if(colour.isGreen())
                                        deselectMove(tile, Color.GREEN);
                                    else
                                        deselectMove(tile, Color.WHITE);
                                }
                                else if(finalSelect1[0] != null && finalSelect2[0] == null){
                                    finalSelect2[0] = gameBoard[finalI][finalJ];
                                    highlightMove(tile);
                                    destinationY.set(finalI);
                                    destinationX.set(finalJ);
                                    this.destinationCol = destinationY.get();
                                    this.destinationRow = destinationX.get();
                                    //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                }
                            });
                        }
                    } else{
                        //If Red then we make the tile red
                        tile.setFill(Color.RED);
                        tile.setStroke(Color.BLACK);

                        Text text = new Text();
                        text.setFont(Font.font(40));
                        drawMove(text, gameBoard, i, j);
                        gameBoardGrid.add(new StackPane(tile, text), j, i);
                        if (board.getCurrentPlayer().getPlayerColour().isRed()) {
                            tile.setOnMouseClicked(event ->{
                                if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                    finalSelect1[0] = gameBoard[finalI][finalJ];
                                    highlightMove(tile); //Highlights tile
                                    sourceY.set(finalI);
                                    sourceX.set(finalJ);
                                    this.sourceCol = sourceY.get();
                                    this.sourceRow = sourceX.get();
                                }
                                else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                    finalSelect1[0] = null;
                                    this.sourceRow = -1;
                                    this.sourceCol = -1;
                                    deselectMove(tile, Color.RED);
                                }
                                else if (finalSelect2[0] == gameBoard[finalI][finalJ]){
                                    this.destinationRow = -1;
                                    this.destinationCol = -1;
                                    finalSelect2[0] = null;
                                    if (colour.isRed())
                                        deselectMove(tile, Color.RED);
                                    else if(colour.isGreen())
                                        deselectMove(tile, Color.GREEN);
                                    else
                                        deselectMove(tile, Color.WHITE);
                                }
                                else if(finalSelect2[0] == null){
                                    finalSelect2[0] = gameBoard[finalI][finalJ];
                                    highlightMove(tile);
                                    destinationY.set(finalI);
                                    destinationX.set(finalJ);
                                    this.destinationCol = destinationY.get();
                                    this.destinationRow = destinationX.get();

                                    //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                }
                            });
                            selected1 = finalSelect1[0];
                            selected2 = finalSelect2[0];
                        } else {
                            tile.setOnMouseClicked(event -> {
                                if (finalSelect2[0] == gameBoard[finalI][finalJ]) {
                                    this.destinationRow = -1;
                                    this.destinationCol = -1;
                                    finalSelect2[0] = null;
                                    if (colour.isRed())
                                        deselectMove(tile, Color.RED);
                                    else if (colour.isGreen())
                                        deselectMove(tile, Color.GREEN);
                                    else
                                        deselectMove(tile, Color.WHITE);
                                } else if (finalSelect1[0] != null && finalSelect2[0] == null) {
                                    finalSelect2[0] = gameBoard[finalI][finalJ];
                                    highlightMove(tile);
                                    destinationY.set(finalI);
                                    destinationX.set(finalJ);
                                    this.destinationCol = destinationY.get();
                                    this.destinationRow = destinationX.get();

                                    //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                }
                            });
                        }
                    }
                }
                finalSelect1[0] = selected1;
                finalSelect2[0] = selected2;
            }
        }
        return gameBoardGrid;
    }

    private static void drawMove(Text text, Square[][] board, int row, int col) {
        text.setText(Integer.toString(board[row][col].getStack().numberOfPieces()));
        text.setFill(Color.BLACK);
    }
    private static void highlightMove(Rectangle tile){
        tile.setFill(Color.YELLOW);
    }
    private static void deselectMove(Rectangle tile, Color color){
        tile.setFill(color);
    }
}
