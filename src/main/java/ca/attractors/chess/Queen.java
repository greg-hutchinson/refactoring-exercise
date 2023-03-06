package ca.attractors.chess;

public class Queen extends ChessPiece {

    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    protected boolean canBeImpeded() {
        return true;
    }

    @Override
    protected boolean isTargetPositionCoordsValid(Position targetPosition) {
        return getPosition().isHorizontalMove(targetPosition)
                || getPosition().isVerticalMove(targetPosition)
                || getPosition().isDiagonalMove(targetPosition);
    }

}
