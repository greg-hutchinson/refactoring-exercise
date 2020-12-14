package ca.attractors.chess;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class KnightPiece extends ChessPiece {

    public KnightPiece(PieceColor color, Position position, Chessboard chessboard) {
        super(color, position, chessboard);
    }

    @Override
    public List<Position> getValidMovePositions() {
        return Arrays.stream(Position.values()).filter(position -> {
            int xDistance = Math.abs(position.getXOffset() - getPosition().getXOffset());
            int yDistance = Math.abs(position.getYOffset() - getPosition().getYOffset());
            return ((xDistance == 1 && yDistance == 2) || (xDistance == 2 && yDistance == 1))
                    && (isPositionVacant(position) || isPositionOccupiedByOpponent(position));
        }).collect(Collectors.toList());
    }
}
