package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

class RookPiece extends ChessPiece {

    protected RookPiece(PieceColor color, Position position, Chessboard chessboard) {
        super(color, position, chessboard);
    }

    /**
     * A rook moves any number of vacant squares horizontally or vertically.
     * Anywhere that is vacant or occupied by opposite side piece
     * @return
     */
    @Override
    public List<Position> getValidMovePositions() {
        List<Position> allValidPositions = new ArrayList<>();

        allValidPositions.addAll(peekUp());
        allValidPositions.addAll(peekDown());
        allValidPositions.addAll(peekLeft());
        allValidPositions.addAll(peekRight());

        return allValidPositions;
    }
}
