package ca.attractors.chess;

public class Queen extends GroundedChessPiece {

    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        if (!isTargetPositionCoordsValid(targetPosition)) return false;

        if (isPositionOccupiedBySameColour(targetPosition)) return false;

        if (isPathImpeded(targetPosition)) return false;

        return true;
    }

    private boolean isTargetPositionCoordsValid(Position targetPosition) {
        return getPosition().isHorizontalMove(targetPosition)
                || getPosition().isVerticalMove(targetPosition)
                || getPosition().isDiagonalMove(targetPosition);
    }

}
