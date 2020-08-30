package ca.attractors.chess;

public class Pawn extends ChessPiece {
    protected Pawn(Chessboard chessboard, PieceColor white) {
        super(chessboard, PieceColor.White);
    }


    @Override
    protected boolean isValidMove(Move move) {

        return true;
    }
}
