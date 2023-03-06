package ca.attractors.chess;

public class Rook extends GroundedChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    protected boolean isTargetPositionCoordsValid(Position targetPosition) {
        return getPosition().isHorizontalMove(targetPosition)
                || getPosition().isVerticalMove(targetPosition);
    }

}
