package ca.attractors.chess;

public class ChessPiece {
    private final Chessboard chessboard;
    private final PieceColor color;

    public ChessPiece(PieceColor color, Chessboard chessboard, Position position) {
        this.chessboard = chessboard;
        this.color = color;
        chessboard.putPieceAt(this, position);
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public Position getPosition() {
        return getChessboard().getPositionOf(this);
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
