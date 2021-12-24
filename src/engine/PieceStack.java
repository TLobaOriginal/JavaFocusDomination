package engine;
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

    public Stack<Piece> getStack() {
        return stack;
    }
}