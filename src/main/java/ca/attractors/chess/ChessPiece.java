package ca.attractors.chess;

public class ChessPiece {
    private Chessboard chessboard;
    private PieceColor color;

    public ChessPiece(Chessboard chessboard, PieceColor color) {
        this.chessboard = chessboard;
        this.color = color;
    }

    public static Rook newRookOnChessboard (Chessboard chessboard) {
        return new Rook(chessboard, PieceColor.White );
    }

    public static Bishop newBishopOnChessboard (Chessboard chessboard) {
        return new Bishop(chessboard, PieceColor.White );
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public Position getPosition() {
        return chessboard.getPositionOf(this);
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
