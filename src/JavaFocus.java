import engine.board.Board;
import engine.Colour;
import engine.Player;
import engine.square.Square;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    private int sCol = 0, sRow = 0, dCol = 0, dRow = 0;
    private boolean reservePossible = false;

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

        Text p1Reinforcement = new Text(Integer.toString(board.getPlayer1().getNumReinforcementPieces()));
        p1Reinforcement.setFont(new Font(30));

        Text p2Reinforcement = new Text(Integer.toString(board.getPlayer2().getNumReinforcementPieces()));
        p2Reinforcement.setFont(new Font(30));


        Text p1NameText = new Text("Player 1 Name: ");
        p1NameText.setFont(new Font(30));
        p1NameText.setFill(Color.RED);
        Text p1TotalText = new Text("Total pieces: ");
        p1TotalText.setFont(new Font(30));
        Text p1CapturedText = new Text("Total captured: ");
        p1CapturedText.setFont(new Font(30));
        Text p1ColourText = new Text("Colour: ");
        p1ColourText.setFont(new Font(30));
        Text p1ReinforcementText = new Text("Reinforcements: ");
        p1ReinforcementText.setFont(new Font(30));

        Text p2NameText = new Text("Player 2 Name: ");
        p2NameText.setFont(new Font(30));
        p2NameText.setFill(Color.GREEN);
        Text p2TotalText = new Text("Total pieces: ");
        p2TotalText.setFont(new Font(30));
        Text p2CapturedText = new Text("Total captured: ");
        p2CapturedText.setFont(new Font(30));
        Text p2ColourText = new Text("Colour: ");
        p2ColourText.setFont(new Font(30));
        Text p2ReinforcementText = new Text("Reinforcements: ");
        p2ReinforcementText.setFont(new Font(30));

        VBox p1InfoBox = new VBox(p1Name, p1Total, p1Captured, p1Reinforcement, p1Colour);
        VBox p1InfoBoxText = new VBox(p1NameText, p1TotalText, p1CapturedText, p1ReinforcementText, p1ColourText);
        VBox p2InfoBox = new VBox(p2Name, p2Total, p2Captured, p2Reinforcement, p2Colour);
        VBox p2InfoBoxText = new VBox(p2NameText, p2TotalText, p2CapturedText, p2ReinforcementText, p2ColourText);

        Button playMove = new Button("Submit Move");
        playMove.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Button reserveMove = new Button("Place Reserve");
        reserveMove.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox p1Box = new HBox(p1InfoBoxText, p1InfoBox);
        HBox p2Box = new HBox(p2InfoBoxText, p2InfoBox);

        player1 = new Pane(p1Box);
        player1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        player2 = new Pane(p2Box);
        player2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        VBox gameInfoContainer = new VBox(player1, player2, playMove, reserveMove);
        AtomicReference<HBox> gameLayout = new AtomicReference<>(new HBox(parent.get(), gameInfoContainer));


        AtomicReference<StackPane> layout = new AtomicReference<>(new StackPane());
        layout.get().getChildren().add(gameLayout.get());

        AtomicReference<Scene> gamePlay = new AtomicReference<>(new Scene(layout.get(), 1100, 500));
        primaryStage.setScene(gamePlay.get());
        primaryStage.show();

        playMove.setOnMouseClicked(event -> {
            boolean hasWon = false;
            if(dRow >= 0 && dCol >= 0 && sRow >= 0 && sCol >= 0){
                int steps = board.getBoard()[sRow][sCol].getStack().numberOfPieces();
                if(distance(dCol, dRow, sCol, sRow) <= steps){
                    hasWon = board.makeMove(dCol, dRow, sCol, sRow);
                    reservePossible = false;
                }
                else
                    System.out.println("Error: Too many steps, submit a new move\n" +
                            "Distance: " + distance(dCol, dRow, sCol, sRow) + "\n" +
                            "Allowed steps: " + steps);
            }
            else if(((sRow >= 0 && sCol >= 0) && board.getCurrentPlayer().getNumReinforcementPieces() > 0)){
                hasWon = board.makeMove(sRow, sCol);
                reservePossible = false;
            }
            else    //TODO soon to be changed with a textfield that will communicate with the players. We mainly want a working solution
            {
                System.out.println("Invalid Move, submit a new move");
            }
            this.dCol = - 1;
            sCol = sRow = dRow = dCol;

            if(hasWon){
                Player winner = board.getOpponent();
                System.out.println(winner.getName() + " HAS WON THE GAME!");
                winningScene(primaryStage,winner);
                return;
            }

            //CHANGE COLOUR OF RESERVE BUTTON
            Player currentPlayer = board.getCurrentPlayer();
            if(currentPlayer.getPlayerColour().isGreen()){
                if(currentPlayer.getNumReinforcementPieces() > 0)
                    reserveMove.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                else
                    reserveMove.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            } else {
                if(currentPlayer.getPlayerColour().isRed())
                    if(currentPlayer.getNumReinforcementPieces() > 0)
                        reserveMove.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    else
                        reserveMove.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }

            if(currentPlayer.getPlayerColour().isGreen())
                playMove.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            else
                playMove.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            p1Total.setText(Integer.toString(board.getPlayer1().getNumPieces()));
            p1Captured.setText(Integer.toString(board.getPlayer1().getNumPiecesCaptured()));
            p1Reinforcement.setText(Integer.toString(board.getPlayer1().getNumReinforcementPieces()));
            p2Total.setText(Integer.toString(board.getPlayer2().getNumPieces()));
            p2Captured.setText(Integer.toString(board.getPlayer2().getNumPiecesCaptured()));
            p2Reinforcement.setText(Integer.toString(board.getPlayer2().getNumReinforcementPieces()));

            parent.set(createBoard(board));
            gameLayout.set(new HBox(parent.get(), gameInfoContainer));
            layout.set(new StackPane(gameLayout.get()));
            gamePlay.set(new Scene(layout.get(), 1100, 500));
            primaryStage.setScene(gamePlay.get());
            primaryStage.show();

            //board.prettyPrint();
        });

        reserveMove.setOnMouseClicked(event -> {
            reservePossible = !reservePossible; //Switch the mode

            Player currentPlayer = board.getCurrentPlayer();
            if(currentPlayer.getNumReinforcementPieces() > 0){
                if(reservePossible){
                    if(currentPlayer.getPlayerColour().isGreen())
                        reserveMove.setBorder(new Border(new BorderStroke(Color.LIGHTGREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    else
                        reserveMove.setBorder(new Border(new BorderStroke(Color.LIGHTPINK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                }
                else{
                    if(currentPlayer.getPlayerColour().isGreen())
                        reserveMove.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    else
                        reserveMove.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                }
            }
        });
    }

    private int distance(int destinationRow, int destinationCol, int sourceRow, int sourceCol){
        return Math.abs(destinationRow - sourceRow) + Math.abs(destinationCol - sourceCol);
    }

    private void winningScene(Stage primaryStage, Player winner){
        Text winnerText = new Text(winner.getName() + " has won!!!");
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setPrefSize(200, 50);
        playAgainButton.setFont(new Font(20));

        Button quit = new Button("Quit");
        quit.setPrefSize(200, 50);
        quit.setFont(new Font(20));

        winnerText.setFont(new Font(50));
        winnerText.textAlignmentProperty().setValue(TextAlignment.CENTER);
        if (winner.getPlayerColour().isGreen())
            winnerText.setFill(Color.GREEN);
        else
            winnerText.setFill(Color.RED);

        HBox line = new HBox(winnerText);
        line.alignmentProperty().setValue(Pos.CENTER);
        HBox playAgainLine = new HBox(playAgainButton);
        playAgainLine.alignmentProperty().setValue(Pos.CENTER);
        HBox quitLine = new HBox(quit);
        quitLine.alignmentProperty().setValue(Pos.CENTER);
        //line.alignmentProperty().setValue(Pos.CENTER);
        VBox column = new VBox(line, playAgainLine, quitLine);
        column.alignmentProperty().setValue(Pos.CENTER);

        StackPane pane = new StackPane(column);
        pane.setPrefSize(1100, 500);
        Scene winnerScene = new Scene(pane, 700, 500);
        primaryStage.setScene(winnerScene);
        primaryStage.show();

        playAgainButton.setOnMouseClicked(event -> {
            start(primaryStage);
        });

        quit.setOnMouseClicked(event -> {
            primaryStage.close();
        });
    }


    //TODO FIX GUI DISPLAY OF REINFORCEMENT

    public Parent createBoard(Board board){
        GridPane gameBoardGrid = new GridPane();
        gameBoardGrid.setPrefSize(755, 755);
        Square[][] gameBoard = board.getBoard();
        Square selected1 = null;
        Square selected2 = selected1;
        //Holds values of the selected pieces
        final Square[] finalSelect1 = {selected1};
        final Square[] finalSelect2 = {selected2};
        AtomicInteger sourceX = new AtomicInteger(-1);
        AtomicInteger sourceY = new AtomicInteger(-1);
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
                            if(reservePossible){
                                if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                    finalSelect1[0] = gameBoard[finalI][finalJ];
                                    if(board.getCurrentPlayer().getPlayerColour() == Colour.RED)
                                        highlightMove(tile, Color.LIGHTPINK); //Highlights tile
                                    else
                                        highlightMove(tile, Color.LIGHTGREEN);
                                    sourceY.set(finalI);
                                    sourceX.set(finalJ);
                                    this.sRow = sourceY.get();
                                    this.sCol = sourceX.get();
                                }
                                else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                    finalSelect1[0] = null;
                                    this.sCol = -1;
                                    this.sRow = -1;
                                    deselectMove(tile, Color.WHITE);
                                }
                            }else{
                                if (finalSelect2[0] == gameBoard[finalI][finalJ]){
                                    this.dCol = -1;
                                    this.dRow = -1;
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
                                    highlightMove(tile, Color.WHEAT);
                                    destinationY.set(finalI);
                                    destinationX.set(finalJ);
                                    this.dRow = destinationY.get();
                                    this.dCol = destinationX.get();
                                    //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                }
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
                                if(reservePossible){
                                    if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                        finalSelect1[0] = gameBoard[finalI][finalJ];
                                        if(board.getCurrentPlayer().getPlayerColour() == Colour.RED)
                                            highlightMove(tile, Color.LIGHTPINK); //Highlights tile
                                        else
                                            highlightMove(tile, Color.LIGHTGREEN);
                                        sourceY.set(finalI);
                                        sourceX.set(finalJ);
                                        this.sRow = sourceY.get();
                                        this.sCol = sourceX.get();
                                    }
                                    else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                        if(finalSelect1[0].getSquareColour().isGreen())
                                            deselectMove(tile, Color.GREEN);
                                        else
                                            deselectMove(tile, Color.RED);
                                        finalSelect1[0] = null;
                                        this.sCol = -1;
                                        this.sRow = -1;

                                    }
                                } else{
                                    if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                        finalSelect1[0] = gameBoard[finalI][finalJ];
                                        sourceY.set(finalI);
                                        sourceX.set(finalJ);
                                        this.sRow = sourceY.get();
                                        this.sCol = sourceX.get();
                                        highlightMove(tile, Color.YELLOW); //Highlights tile
                                    }
                                    else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                        finalSelect1[0] = null;
                                        this.sRow = -1;
                                        this.sCol = -1;
                                        deselectMove(tile, Color.GREEN);
                                    }
                                    else if (finalSelect2[0] == gameBoard[finalI][finalJ]){
                                        finalSelect2[0] = null;
                                        this.dCol = -1;
                                        this.dRow = -1;
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
                                        this.dRow = destinationY.get();
                                        this.dCol = destinationX.get();
                                        highlightMove(tile, Color.WHEAT);
                                        //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                    }
                                }
                            });
                            selected1 = finalSelect1[0];
                            selected2 = finalSelect2[0];
                        }
                        else{
                            tile.setOnMouseClicked(event ->{
                                if(reservePossible){
                                    if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                        finalSelect1[0] = gameBoard[finalI][finalJ];
                                        if(board.getCurrentPlayer().getPlayerColour() == Colour.RED)
                                            highlightMove(tile, Color.LIGHTPINK); //Highlights tile
                                        else
                                            highlightMove(tile, Color.LIGHTGREEN);
                                        sourceY.set(finalI);
                                        sourceX.set(finalJ);
                                        this.sRow = sourceY.get();
                                        this.sCol = sourceX.get();
                                    }
                                    else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                        if(finalSelect1[0].getSquareColour().isGreen())
                                            deselectMove(tile, Color.GREEN);
                                        else
                                            deselectMove(tile, Color.RED);
                                        finalSelect1[0] = null;
                                        this.sCol = -1;
                                        this.sRow = -1;
                                    }
                                }else{
                                    if (finalSelect2[0] == gameBoard[finalI][finalJ]){
                                        this.dCol = -1;
                                        this.dRow = -1;
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
                                        highlightMove(tile, Color.WHEAT);
                                        destinationY.set(finalI);
                                        destinationX.set(finalJ);
                                        this.dRow = destinationY.get();
                                        this.dCol = destinationX.get();
                                        //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                    }
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
                                if(reservePossible){
                                    if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                        finalSelect1[0] = gameBoard[finalI][finalJ];
                                        if(board.getCurrentPlayer().getPlayerColour() == Colour.RED)
                                            highlightMove(tile, Color.LIGHTPINK); //Highlights tile
                                        else
                                            highlightMove(tile, Color.LIGHTGREEN);
                                        sourceY.set(finalI);
                                        sourceX.set(finalJ);
                                        this.sRow = sourceY.get();
                                        this.sCol = sourceX.get();
                                    }
                                    else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                        if(finalSelect1[0].getSquareColour().isGreen())
                                            deselectMove(tile, Color.GREEN);
                                        else
                                            deselectMove(tile, Color.RED);
                                        finalSelect1[0] = null;
                                        this.sCol = -1;
                                        this.sRow = -1;
                                    }
                                }else{
                                    if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                        finalSelect1[0] = gameBoard[finalI][finalJ];
                                        highlightMove(tile, Color.YELLOW); //Highlights tile
                                        sourceY.set(finalI);
                                        sourceX.set(finalJ);
                                        this.sRow = sourceY.get();
                                        this.sCol = sourceX.get();
                                    }
                                    else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                        finalSelect1[0] = null;
                                        this.sCol = -1;
                                        this.sRow = -1;
                                        deselectMove(tile, Color.RED);
                                    }
                                    else if (finalSelect2[0] == gameBoard[finalI][finalJ]){
                                        this.dCol = -1;
                                        this.dRow = -1;
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
                                        highlightMove(tile, Color.WHEAT);
                                        destinationY.set(finalI);
                                        destinationX.set(finalJ);
                                        this.dRow = destinationY.get();
                                        this.dCol = destinationX.get();

                                        //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                    }
                                }
                            });
                            selected1 = finalSelect1[0];
                            selected2 = finalSelect2[0];
                        } else {
                            tile.setOnMouseClicked(event -> {
                                if(reservePossible){
                                    if(finalSelect1[0] == null){ //If nothing is selected then we make our first selection
                                        finalSelect1[0] = gameBoard[finalI][finalJ];
                                        if(board.getCurrentPlayer().getPlayerColour() == Colour.RED)
                                            highlightMove(tile, Color.LIGHTPINK); //Highlights tile
                                        else
                                            highlightMove(tile, Color.LIGHTGREEN);
                                        sourceY.set(finalI);
                                        sourceX.set(finalJ);
                                        this.sRow = sourceY.get();
                                        this.sCol = sourceX.get();
                                    }
                                    else if (finalSelect1[0] == gameBoard[finalI][finalJ]){//If we want to deselect
                                        if(finalSelect1[0].getSquareColour().isGreen())
                                            deselectMove(tile, Color.GREEN);
                                        else
                                            deselectMove(tile, Color.RED);
                                        finalSelect1[0] = null;
                                        this.sCol = -1;
                                        this.sRow = -1;
                                    }
                                }else{
                                    if (finalSelect2[0] == gameBoard[finalI][finalJ]) {
                                        this.dCol = -1;
                                        this.dRow = -1;
                                        finalSelect2[0] = null;
                                        if (colour.isRed())
                                            deselectMove(tile, Color.RED);
                                        else if (colour.isGreen())
                                            deselectMove(tile, Color.GREEN);
                                        else
                                            deselectMove(tile, Color.WHITE);
                                    } else if (finalSelect1[0] != null && finalSelect2[0] == null) {
                                        finalSelect2[0] = gameBoard[finalI][finalJ];
                                        highlightMove(tile, Color.WHEAT);
                                        destinationY.set(finalI);
                                        destinationX.set(finalJ);
                                        this.dRow = destinationY.get();
                                        this.dCol = destinationX.get();

                                        //board.makeMove(destinationX.get(), destinationY.get(), sourceX.get(), sourceY.get());
                                    }
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
    private static void highlightMove(Rectangle tile, Color color){
        tile.setFill(color);
    }
    private static void deselectMove(Rectangle tile, Color color){
        tile.setFill(color);
    }
}
