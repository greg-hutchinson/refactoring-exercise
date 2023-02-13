package ca.attractors.chess;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    public boolean isMoveValid(Position targetPosition) {
        if (!isMoveValidForPiece(targetPosition)
                || isPositionOccupiedWithSameColor(targetPosition)) {
            return false;
        }
        // VS: It is a vertical move!!!
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // the increment might be positive or negative.
        if (targetPosition.isVerticalMove(getPosition())) {
            return isVerticalMoveValid(targetPosition);
        }
        if (targetPosition.isHorizontalMove(getPosition())) {
            return isHorizontalMoveValid(targetPosition);
        }
        return true;
    }

    private boolean isMoveValidForPiece(Position targetPosition) {
        return targetPosition.isVerticalMove(getPosition()) || targetPosition.isHorizontalMove(getPosition());
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
