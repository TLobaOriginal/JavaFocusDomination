package engine;

public class Board {
    private Square[][] board;
    Player currentPlayer;
    Player player1, player2;

    public Board(Player player1, Player player2){
        board = new Square[BoardUtils.ROW_SIZE][BoardUtils.COL_SIZE];
        BoardUtils.initialiseBoard(board);
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
    }

    public void changePlayer(){
        if(currentPlayer.equals(player1))
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }

    /*public boolean isPlayer1Turn(){
        return currentPlayer.equals(player1);
    }*/

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Square[][] getBoard() {
        return board;
    }

    public void makeMove(int destinationX, int destinationY, int sourceX, int sourceY){
        Square sourceSquare = board[sourceY][sourceX];
        Square destinationSquare = board[destinationY][destinationX];

        destinationSquare.getStack().mergeStack(sourceSquare.getStack());
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

