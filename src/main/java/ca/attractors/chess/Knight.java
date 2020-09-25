package ca.attractors.chess;


public class Knight extends ChessPiece {

    protected Knight(Chessboard chessboard, PieceColor color) {
        super(chessboard, color);
    }

    @Override
    protected boolean isValidMove(Move move) {
        return move.isKnightsMove();
    }
}
