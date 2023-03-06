package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    public boolean moveTo(Position targetPosition) {
        if (targetPosition.isSquareOccupiedBySameColor(targetPosition, this))
            return false;

        if (targetPosition.isHorizontalMove(targetPosition, this)) {
            int start = getPosition().getYOffset();
            int end = targetPosition.getYOffset();
            if (targetPosition.isInvalidMove(targetPosition, start, end, this))
                return false;
        }

        if (targetPosition.isVerticalMove(targetPosition, this)) {
            int start = getPosition().getXOffset();
            int end = targetPosition.getXOffset();
            if (targetPosition.isInvalidMove(targetPosition, start, end, this))
                return false;
        }

        if (targetPosition.isDiagonalMove(targetPosition, this)) {
            //diagonal logic here
            return false;
        }

        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }
}
