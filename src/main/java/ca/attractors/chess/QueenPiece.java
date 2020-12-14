package ca.attractors.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class QueenPiece extends ChessPiece {

    public QueenPiece(PieceColor color, Position position, Chessboard chessboard) {
        super(color, position, chessboard);
    }

    /**
     * The queen moves any number of vacant squares horizontally, vertically, or diagonally.
     * @return
     */
    @Override
    public List<Position> getValidMovePositions() {
        List<Position> allValidPositions = new ArrayList<>();

        allValidPositions.addAll(peekUp());
        allValidPositions.addAll(peekUpLeft());
        allValidPositions.addAll(peekUpRight());
        allValidPositions.addAll(peekDown());
        allValidPositions.addAll(peekDownLeft());
        allValidPositions.addAll(peekDownRight());
        allValidPositions.addAll(peekLeft());
        allValidPositions.addAll(peekRight());

        return allValidPositions;
    }
}
