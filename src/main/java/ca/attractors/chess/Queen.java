package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    protected boolean isValidMove(Position targetPosition) {
        return isColorValid(targetPosition) && isPathFree(targetPosition);
    }

    private boolean isPathFree(Position targetPosition) {
        if (getPosition().isHorizontalOrVertical(targetPosition)) {
            return isStraightPathFree(targetPosition);
        } else if (getPosition().isDiagonal(targetPosition)) {
            return isDiagonalPathFree(targetPosition);
        }
        return false;
    }

    private boolean isDiagonalPathFree(Position targetPosition) {
        int distance = Math.abs(getPosition().y - targetPosition.y);

        int incrementX = -1;
        if (getPosition().getXOffset() <= targetPosition.getXOffset()) {
            incrementX = 1;
        }

        int incrementY = -1;
        if (getPosition().getYOffset() <= targetPosition.getYOffset()) {
            incrementY = 1;
        }

        Position position;
        for (int v = 1; v != distance + 1; v++) {
            position = Position.getPositionFor(v * incrementX, v * incrementY);
            if (getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }

        return true;
    }

}
