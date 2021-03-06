package engine.board;

import engine.Colour;
import engine.Player;
import engine.pieces.Piece;
import engine.pieces.PieceStack;
import engine.square.Square;

public class Board {
    private Square[][] board;
    private Player currentPlayer, opponent;
    private Player player1, player2;

    public Board(Player player1, Player player2){
        board = new Square[BoardUtils.ROW_SIZE][BoardUtils.COL_SIZE];
        BoardUtils.initialiseBoard(board);
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        opponent = player2;
    }

    public void changePlayer(){
        if(currentPlayer.equals(player1)){
            currentPlayer = player2;
            opponent = player1;
        }
        else{
            currentPlayer = player1;
            opponent = player2;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponent() {
        return opponent;
    }

    public Square[][] getBoard() {
        return board;
    }

    public boolean makeMove(int destinationX, int destinationY, int sourceX, int sourceY){
        Square sourceSquare = board[sourceY][sourceX];
        Square destinationSquare = board[destinationY][destinationX];

        opponent.setNumPieces(opponent.getNumPieces() - destinationSquare.getStack().mergeStack(sourceSquare.getStack(), currentPlayer));
        boolean won = winCheck();
        if(won)
            System.out.println("Winner is found");
        else
            System.out.println("Winner not found");
        changePlayer();
        return won;
    }

    private boolean winCheck() {
        Colour opponentColour = opponent.getPlayerColour();
        if(opponent.getNumReinforcementPieces() > 0)
            return false;

        for(Square[] row: board){
            for(Square square: row){
                if(square.getSquareColour() == opponentColour){
                    return false;
                }
            }
        }
        System.out.println(currentPlayer.getName() + " HAS WON THE GAME!!!");
        return true;
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

    public boolean makeMove(int sRow, int sCol) {
        PieceStack newStack = new PieceStack();
        newStack.push(new Piece(currentPlayer.getPlayerColour()));
        opponent.setNumPieces(opponent.getNumPieces() - board[sRow][sCol].getStack().mergeStack(newStack, currentPlayer));
        currentPlayer.useReinforcement();
        boolean won = winCheck();
        changePlayer();
        return won;
    }
}

