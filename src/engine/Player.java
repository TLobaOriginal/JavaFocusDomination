package engine;

import engine.pieces.Piece;

public class Player {
    private String name;
    private int numPieces;
    private int numPiecesCaptured;
    private int numReinforcementPieces;
    Colour playerColour;

    public Player(String name, int numPieces, Colour playerColour){
        this.name = name;
        this.numPieces = numPieces;
        numPiecesCaptured = 0;
        numReinforcementPieces = 0;
        this.playerColour = playerColour;
    }

    public int processPiece(Piece piece){
        int numCaptured = 0;
        if(piece.getColour() == playerColour)
            numReinforcementPieces++;
        else {
            numPiecesCaptured++;
            numCaptured++;
        }
        return numCaptured;
    }

    //Getter Methods
    public Colour getPlayerColour() {
        return playerColour;
    }

    public int getNumPieces() {
        return numPieces;
    }

    public String getName() {
        return name;
    }

    public int getNumPiecesCaptured() {
        return numPiecesCaptured;
    }

    public int getNumReinforcementPieces() {
        return numReinforcementPieces;
    }

    /*Setter Methods*/
    public void setName(String name) {
        this.name = name;
    }

    protected void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }
/*
    public void setNumPiecesCaptured(int numPiecesCaptured) {
        this.numPiecesCaptured = numPiecesCaptured;
    }

    public void setPlayerColour(Colour playerColour) {
        this.playerColour = playerColour;
    }*/
}
