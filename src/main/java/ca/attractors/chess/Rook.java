package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    protected Rook(Chessboard chessboard, PieceColor color) {
        super(chessboard, color);
    }

    @Override
    protected boolean isValidMove(Move move) {
        if (!(move.isVertical() || move.isHorizontal()))
            return false;
        return move.isPathUnoccupied();
    }
}
