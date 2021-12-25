package engine;

import javafx.application.Application;
import javafx.stage.Stage;

public class Board {
    private Square[][] board;
    Player currentPlayer;
    Player player1, player2;

    public Board(){
        board = new Square[BoardUtils.ROW_SIZE][BoardUtils.COL_SIZE];
        BoardUtils.initialiseBoard(board);
    }

    public Square[][] getBoard() {
        return board;
    }

    public void prettyPrint(){
        for (Square[] squares : board) {
            for (int j = 0; j < squares.length; j++) {
                if (squares[j].getSquareType().isInvalid())
                    System.out.print(squares[j].toString() + " ");
                else {
                    if (squares[j].getSquareColour().isUnOccupied())
                        System.out.print(Colour.NONE + " ");
                    else if (squares[j].getSquareColour().isGreen())
                        System.out.print(Colour.GREEN + " ");
                    else
                        System.out.print(Colour.RED + " ");
                }
            }
            System.out.println("");
        }
    }
}

