package engine;

public class Player {
    private String name;
    private int numPieces;
    private int numPiecesCaptured;
    Colour playerColour;

    public Player(String name, int numPieces, Colour playerColour){
        this.name = name;
        this.numPieces = numPieces;
        numPiecesCaptured = 0;
        this.playerColour = playerColour;
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

    /*Setter Methods*/
    public void setName(String name) {
        this.name = name;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    public void setNumPiecesCaptured(int numPiecesCaptured) {
        this.numPiecesCaptured = numPiecesCaptured;
    }

    public void setPlayerColour(Colour playerColour) {
        this.playerColour = playerColour;
    }
}
