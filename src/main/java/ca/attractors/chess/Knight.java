package ca.attractors.chess;

public class Knight extends ChessPiece {
    protected Knight(Chessboard chessboard, PieceColor colour) {
        super(chessboard, colour);
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        return true; //TODO
    }

}
