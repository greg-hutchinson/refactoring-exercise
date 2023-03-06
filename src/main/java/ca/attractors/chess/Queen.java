package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    public boolean moveTo(Position targetPosition) {
        if (isValidMove(targetPosition)) {
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }

        return false;
    }

    private boolean isValidMove(Position targetPosition) {
        if (!getPosition().isHorizontalMove(targetPosition) && !getPosition().isVerticalMove(targetPosition) && !getPosition().isVerticalMove(targetPosition)) {
            return false;
        }

        if (getChessboard().isPositionOccupiedBySameColour(targetPosition, this.getColor())) {
            return false;
        }

        return true;
    }

    private boolean isMovementPathClear(Position targetPosition) {
        if (getPosition().isVerticalMove(targetPosition)) {
            return true;
        }

        return true;
    }
}
