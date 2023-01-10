package ca.attractors.chess;

public class ChessPiece {
    private final Chessboard chessboard;
    private final PieceColor color;

    private Position position;

    public ChessPiece(PieceColor color, Chessboard chessboard, Position position) {
        this.chessboard = chessboard;
        this.color = color;
        chessboard.putPieceAt(this, position);
    }

    //This method is called everytime a piece has been moved somewhere on the chessboard
    // Code Smells
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

    public String getName() {
        return this.getClass().getSimpleName();
    }
    @Override
    public String toString() {
        return getName() + "{" + getColor() + " at: " + getPosition() +"}";
    }

}
