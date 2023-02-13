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
        return isColorValid(targetPosition)
                && isPathFree(targetPosition);
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
        int startX = getPosition().getXOffset();
        int endX = targetPosition.getXOffset();
        int startY = getPosition().getYOffset();
        int endY = targetPosition.getYOffset();

        int distance = Math.abs(getPosition().y - targetPosition.y);

        int incrementX = -1;
        if (startX <= endX) {
            incrementX = 1;
        }

        int incrementY = -1;
        if (startY <= endY) {
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
