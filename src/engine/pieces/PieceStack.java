package engine.pieces;
import engine.Colour;
import engine.Player;

import java.util.LinkedList;

public class PieceStack {
    /*We can implement the Piece stack as a LinkedList*/
    //private Stack<Piece> stack;
    LinkedList<Piece> stack; //TODO turn to private when needed
    private Colour topColour;

    public PieceStack(){
        stack = new LinkedList<>();
        topColour = Colour.NONE;
    }

    public Colour getTopColour() {
        return topColour;
    }

    public void setTopColour(Colour topColour) {
        this.topColour = topColour;
    }


    public void push(Piece piece){
        stack.addFirst(piece);
    }

    public Piece pop(){
        return stack.removeLast();
    }

    public int numberOfPieces(){
        return stack.size();
    }

    public boolean isEmpty(){
        topColour = Colour.NONE;
        return stack.isEmpty();
    }

    //public int size(){return stack.size();}

    //TODO fix bug of moving to empty square with stack that contains opponents piece
    //TODO sometimes the wrong top colour is initialised whenever we move a stack to a square that has an existing stack
    public int mergeStack(PieceStack incomingStack, Player currentPlayer){
        int numCaptured = 0;
        while(!incomingStack.isEmpty()) {
            stack.addFirst(incomingStack.pop());
            if (stack.size() > 5) {
                numCaptured += currentPlayer.processPiece(stack.removeLast());
            }
        }
        topColour = stack.getFirst().getColour();
        return numCaptured;
    }

}
