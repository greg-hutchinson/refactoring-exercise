package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    public boolean moveTo(Position targetPosition) {
        if (targetPosition.isSquareOccupiedBySameColor(targetPosition, this))
            return false;

        if (targetPosition.isHorizontalMove(targetPosition, this)) {
            if (targetPosition.isInvalidMove(targetPosition, this))
                return false;
        }

        if (targetPosition.isVerticalMove(targetPosition, this)) {
            if (targetPosition.isInvalidMove(targetPosition, this))
                return false;
        }

        if (targetPosition.isDiagonalMove(targetPosition, this)) {
            //diagonal logic here
            //if x offset is not equal to y offset? not a valid diagonal move


            return false;
        }

        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }
}
