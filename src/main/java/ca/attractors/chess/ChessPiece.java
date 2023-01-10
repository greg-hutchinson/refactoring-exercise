package ca.attractors.chess;

public class ChessPiece {
    private final Chessboard chessboard;
    private final PieceColor color;

    private Position position;

    public ChessPiece(PieceColor color, Chessboard chessboard, Position position) {
        this.chessboard = chessboard;
        this.color = color;
        chessboard.putPieceAt(this, position);
        //Note - we don't have to set the position here since when the chessboard moves the piece,
        //it always informs the piece that it has been moved. This is slick
    }

    //This method is called everytime a piece has been moved somewhere on the chessboard
    // Code Smells for some reason
    void pieceMoveTo(Position position) {
        this.position = position;
    }
    public static ChessPiece newRook(PieceColor color,Chessboard chessboard, Position position) {
        return new Rook(color,chessboard,position);
    }
    public Chessboard getChessboard() {
        return chessboard;
    }

    public Position getPosition() {
        return position;
    }

    public PieceColor getColor() {
        return color;
    }

    // Code smell - maybe - maybe not - for now - yes
    public void removed() {
        position = null;
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
    @Override
    public String toString() {
        return getName() + "{" + getColor() + " at: " + getPosition() +"}";
    }

}
