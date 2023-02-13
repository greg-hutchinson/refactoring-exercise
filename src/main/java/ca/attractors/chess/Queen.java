package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    public boolean isMoveValid(Position targetPosition) {
        if (!isMoveValidForPiece(targetPosition) ||
                isPositionOccupiedWithSameColor(targetPosition)) {
            return false;
        }
        if (targetPosition.isHorizontalMove(getPosition())) {
            return isHorizontalMoveValid(targetPosition);
        }
        if (targetPosition.isVerticalMove(getPosition())) {
            return isVerticalMoveValid(targetPosition);
        }
        if (targetPosition.isDiagonalMove(getPosition())) {
            return isDiagonalMoveValid(targetPosition);
        }
        return true;
    }

    private boolean isMoveValidForPiece(Position targetPosition) {
        return targetPosition.isHorizontalMove(getPosition())
                || targetPosition.isVerticalMove(getPosition()) || (targetPosition.isDiagonalMove(getPosition()));
    }

    private boolean isDiagonalMoveValid(Position targetPosition) {
        int startY = getPosition().getYOffset();
        int endY = targetPosition.getYOffset();
        int startX = getPosition().getYOffset();
        int endX = targetPosition.getYOffset();
        int incrementX = getIncrement(startX, endX);
        int incrementY = getIncrement(startY, endY);
        for (int x = startY + incrementY, y = startY + incrementY; (x != endX) || (y != endY); x += incrementX, y += incrementY) {
            Position position = Position.getPositionFor(x, y);
            if (getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isVerticalMoveValid(Position targetPosition) {
        int start = getPosition().getYOffset();
        int end = targetPosition.getYOffset();
        int increment = getIncrement(start, end);
        for (int y = start + increment; y != end; y += increment) {
            Position position = Position.getPositionFor(targetPosition.getXOffset(), y);
            if (getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isHorizontalMoveValid(Position targetPosition) {
        int start = getPosition().getXOffset();
        int end = targetPosition.getXOffset();
        int increment = getIncrement(start, end);
        for (int x = start + increment; x != end; x += increment) {
            Position position = Position.getPositionFor(x, targetPosition.getYOffset());
            if (getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }
        return true;
    }

    private static int getIncrement(int start, int end) {
        if (start > end) {
            return -1;
        }
        return 1;
    }


}
