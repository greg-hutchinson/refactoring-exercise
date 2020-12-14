package ca.attractors.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class KingPiece extends ChessPiece {

    public KingPiece(PieceColor color, Position position, Chessboard chessboard) {
        super(color, position, chessboard);
    }

    /**
     * King piece validMove logic
     * Need to improve this method for King piece
     * as it needs to consider the cases of check & checkmate.
     * @return
     */
    @Override
    public List<Position> getValidMovePositions() {
        return Arrays.stream(Position.values()).filter(position -> {
            int xDistance = Math.abs(position.getXOffset() - getPosition().getXOffset());
            int yDistance = Math.abs(position.getYOffset() - getPosition().getYOffset());
            return position != getPosition()
                    && xDistance <= 1 && yDistance <= 1
                    && (isPositionVacant(position) || isPositionOccupiedByOpponent(position));
        }).collect(Collectors.toList());
    }
}
