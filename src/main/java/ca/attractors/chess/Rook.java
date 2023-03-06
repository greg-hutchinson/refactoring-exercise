package ca.attractors.chess;

public class Rook extends GroundedChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        //if it is not the same x or y coordinate it is not a rooks valid move at all
        if (!isTargetPositionCoordsValid(targetPosition)) return false;

        //Next - Check to make sure that if the target square is occupied it is not the same color
        if (isPositionOccupiedBySameColour(targetPosition)) return false;

        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        if (isPathImpeded(targetPosition)) return false;

        return true;
    }

    private boolean isTargetPositionCoordsValid(Position targetPosition) {
        return getPosition().isHorizontalMove(targetPosition)
                || getPosition().isVerticalMove(targetPosition);
    }

}
