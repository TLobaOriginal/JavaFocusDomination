package engine;

public class BoardUtils {
    public final static int BOARD_SIZE = 64;
    public final static int ROW_SIZE = 8;
    public final static int COL_SIZE = 8;

    public static void initialiseBoard(Square[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = new Square(SquareType.INVALID);
                /*Now we should do the if statements to check and see if the current square is VALID or INVALID*/
                if(isInvalidCase(i, j)){
                    board[i][j].setSquareType(SquareType.INVALID);
                    board[i][j].setSquareColour(Colour.NONE);
                    // System.out.println("Invalid case -> i: " + i + " j: " + j);
                }
                else{
                    board[i][j].setSquareType(SquareType.VALID);
                    if(isEmptyCase(i, j)){
                        board[i][j].setSquareColour(Colour.NONE);
                        /* if(board[i][j].getSquareColour().isUnOccupied())
                            System.out.println("Empty case -> i: " + i + " j: " + j);*/
                    }
                    else if(isRedCase(i, j)){
                        board[i][j].setSquareColour(Colour.RED);
                        /* if(board[i][j].getSquareColour().isRed())
                            System.out.println("Red case -> i: " + i + " j: " + j);*/
                    }
                    else if(isGreenCase(i , j)){
                        board[i][j].setSquareColour(Colour.GREEN);
                        /* if(board[i][j].getSquareColour().isGreen())
                            System.out.println("Green case -> i: " + i + " j: " + j);*/
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
