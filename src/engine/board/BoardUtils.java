package engine.board;

import engine.Colour;
import engine.pieces.Piece;
import engine.square.Square;
import engine.square.SquareType;

public class BoardUtils {
    public final static int BOARD_SIZE = 64;
    public final static int ROW_SIZE = 8;
    public final static int COL_SIZE = 8;

    public static void initialiseBoard(Square[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = new Square(SquareType.INVALID, i, j);
                /*Now we should do the if statements to check and see if the current square is VALID or INVALID*/
                if(!isInvalidCase(i, j))/*{
                    board[i][j].setSquareColour(Colour.NONE);
                }
                else*/{
                    board[i][j].setSquareType(SquareType.VALID);
                    /*if(isEmptyCase(i, j)){
                        board[i][j].setSquareColour(Colour.NONE);
                    }
                    else */if(isRedCase(i, j)){
                        //board[i][j].setSquareColour(Colour.RED);
                        board[i][j].getStack().push(new Piece(Colour.RED));
                    }
                    else if(isGreenCase(i , j)){
                        //board[i][j].setSquareColour(Colour.GREEN);
                        board[i][j].getStack().push(new Piece(Colour.GREEN));
                    }
                }
            }
        }
    }

    private static boolean isGreenCase(int i, int j) {
        return ((i == 1 || i == 3 || i == 5) && (j == 3 || j == 4)) || //Rows 2, 4, 6
                ((i == 2 || i == 4 || i == 6) && (j == 1 || j == 2 || j == 5 || j == 6)); //Rows 3, 5, 7
    }

    private static boolean isRedCase(int i, int j) {
        return ((i == 1 || i == 3 || i == 5) && (j == 1 || j == 2 || j == 5 || j == 6)) ||
                ((i == 2 || i == 4 || i == 6) && (j == 3 || j == 4));
    }

    private static boolean isEmptyCase(int i, int j) {
        return i == 0 || i == 7 || j == 0 || j == 7;
    }

    private static boolean isInvalidCase(int i, int j) {
        return (i == 0 && (j == 0 || j == 1 || j == 6 || j == 7)) ||
                ((i == 1) && (j == 0 || j == 7)) ||
                ((i == 6) && (j == 0 || j == 7)) ||
                ((i == 7) && (j == 0 || j == 1 || j == 6 || j == 7));
    }
}
