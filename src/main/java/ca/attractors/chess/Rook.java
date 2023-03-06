package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        //if it is not the same x or y coordinate it is not a rooks valid move at all
        if (!isTargetPositionCoordsValid(targetPosition)) return false;

        //Next - Check to make sure that if the target square is occupied it is not the same color
        if (isOccupiedBySameColour(targetPosition)) return false;

        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        if (isPathImpeded(targetPosition)) return false;

        return true;
    }

    private boolean isPathImpeded(Position targetPosition) {
        List<Position> positions = getPositionsForPath(targetPosition);

        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }

        return false;
    }

    private List<Position> getPositionsForPath(Position targetPosition) {
        // Assume we are moving horizontally by default
        int start = getPosition().getYOffset();
        int end = targetPosition.getYOffset();

        // If Y position hasn't changed, then we are moving in the X direction
        if (getPosition().isHorizontalMove(targetPosition)) {
            start = getPosition().getXOffset();
            end = targetPosition.getXOffset();
        }

        int increment;
        if (start > end)
            increment = -1;
        else
            increment = 1;

        List<Position> positions = new ArrayList<>();
        for (int i = start +increment; i != end; i = i + increment) {
            if (getPosition().isHorizontalMove(targetPosition)) {
                positions.add(Position.getPositionFor(i, targetPosition.getYOffset()));
            } else {
                positions.add(Position.getPositionFor(targetPosition.getXOffset(), i));
            }
        }
        return positions;
    }

    private boolean isTargetPositionCoordsValid(Position targetPosition) {
        return getPosition().isHorizontalMove(targetPosition)
                || getPosition().isVerticalMove(targetPosition);
    }

    private boolean isOccupiedBySameColour(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == getColor())
                return true;
        }
        return false;
    }

}
