package engine;
import java.util.ArrayList;
import java.util.Stack;
public class PieceStack {

    private Stack<Piece> stack;
    private Colour topColour;

    PieceStack(){
        stack = new Stack<>();
        topColour = Colour.NONE;
    }

    public Colour getTopColour() {
        return topColour;
    }

    public void setTopColour(Colour topColour) {
        this.topColour = topColour;
    }

    /*public Stack<Piece> getStack() {
        return stack;
    }*/

    public void push(Piece piece){stack.push(piece);}

    public Piece pop(){return stack.pop();}

    public int numberOfPieces(){
        return stack.size();
    }

    public boolean isEmpty(){
        topColour = Colour.NONE;
        return stack.isEmpty();
    }

    public void prettyPrint(){
        while(!stack.isEmpty()){
            System.out.println(stack.pop().getColour().toString() + " - ");
        }
    }

    //TODO fix bug of moving to empty square with stack that contains opponents piece
    //TODO sometimes the wrong top colour is initialised whenever we move a stack to a square that has an existing stack
    public void mergeStack(PieceStack incomingStack){
        /*if(stack.isEmpty())
        {
            topColour = incomingStack.getTopColour();
            while(!incomingStack.isEmpty()){
                stack.push(incomingStack.pop());
            }
            incomingStack.reverseStack();
        }
        else{
            reverseStack();
            while(!incomingStack.isEmpty())
                stack.push(incomingStack.pop());
            topColour = stack.peek().getColour();
        }*/
        ArrayList<Piece> incomingStackPieces = new ArrayList<>();
        while(!incomingStack.isEmpty())
            incomingStackPieces.add(incomingStack.pop());
        for(int i = incomingStackPieces.size() - 1; i >= 0; i--)
            stack.push(incomingStackPieces.remove(i));
        topColour = stack.peek().getColour();
    }

}
