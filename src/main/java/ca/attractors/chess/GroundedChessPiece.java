package ca.attractors.chess;

import java.util.List;

public abstract class GroundedChessPiece extends ChessPiece {
    public GroundedChessPiece(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    protected boolean isPathImpeded(Position targetPosition) {
        List<Position> positions = getPosition().getPositionsForPath(targetPosition);

        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        if (!isTargetPositionCoordsValid(targetPosition)) return false;

        if (isPositionOccupiedBySameColour(targetPosition)) return false;

        if (isPathImpeded(targetPosition)) return false;

        return true;
    }

    protected abstract boolean isTargetPositionCoordsValid(Position targetPosition);
}
