package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

class BishopPiece extends ChessPiece {

    protected BishopPiece(PieceColor color, Position position, Chessboard chessboard) {
        super(color, position, chessboard);
    }

    /**
     * A bishop moves any number of vacant squares diagonally.
     * @return
     */
    @Override
    public List<Position> getValidMovePositions() {
        List<Position> allValidPositions = new ArrayList<>();

        allValidPositions.addAll(peekUpLeft());
        allValidPositions.addAll(peekUpRight());
        allValidPositions.addAll(peekDownLeft());
        allValidPositions.addAll(peekDownRight());

        return allValidPositions;
    }

}
