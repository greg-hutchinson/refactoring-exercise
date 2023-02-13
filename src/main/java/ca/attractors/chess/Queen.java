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
        return getPosition().isTargetPositionValid(targetPosition)
                && isColorValid(targetPosition)
                && isPathFree(targetPosition);
    }

    private boolean isDiagonal(Position targetPosition) {
        return targetPosition.x != getPosition().x
                && targetPosition.y != getPosition().y;
    }

    private boolean isPathFree(Position targetPosition) {
        if (isDiagonal(targetPosition)) {
            return isNonDiagonalPathFree(targetPosition);
        }
        return isStraightPathFree(targetPosition);
    }

    private boolean isNonDiagonalPathFree(Position targetPosition) {
        int start;
        int end;

        if (targetPosition.y == getPosition().y) {
            start = getPosition().getXOffset();
            end = targetPosition.getXOffset();
        }
        else if (targetPosition.x == getPosition().x) {
            start = getPosition().getYOffset();
            end = targetPosition.getYOffset();
        }
        else {
            return false;
        }

        int increment = -1;
        if (start <= end) {
            increment = 1;
        }

        Position position;
        for (int v = start+increment; v != end; v = v + increment) {
            if (targetPosition.y == getPosition().y) {
                position = Position.getPositionFor(v, targetPosition.getYOffset());
            }
            else {
                position = Position.getPositionFor(targetPosition.getXOffset(), v);
            }

            if (getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }

        return true;
    }

}
