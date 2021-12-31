package engine.pieces;

import engine.Colour;

public class Piece {
    private Colour colour;

    public Piece(Colour colour){
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }
}
