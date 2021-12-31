package engine.square;
import engine.Colour;
import engine.pieces.PieceStack;

public class Square {
    private SquareType squareType;
    int row;
    int column;
    private PieceStack stack;
    //TODO Add a stack which will represent a stack of pieces

    public Square(SquareType squareType, int row, int column){
        this.squareType = squareType;
        this.row = row;
        this.column = column;
        stack = new PieceStack();
    }

    public void setSquareType(SquareType squareType) {
        this.squareType = squareType;
    }

    public Colour getSquareColour() {
        return stack.getTopColour();
    }

    public void setSquareColour(Colour colour){
        stack.setTopColour(colour);
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public PieceStack getStack() {
        return stack;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString(){
        if(this.squareType.isInvalid())
            return "-";
        return getSquareColour().toString();
    }
}
