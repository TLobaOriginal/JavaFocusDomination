package engine;

public class Square {
    private SquareType squareType;
    //private Colour squareColour;
    PieceStack stack;
    //TODO Add a stack which will represent a stack of pieces

    public Square(SquareType squareType){
        this.squareType = squareType;
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

    @Override
    public String toString(){
        if(this.squareType.isInvalid())
            return "-";
        return getSquareColour().toString();
    }
}
