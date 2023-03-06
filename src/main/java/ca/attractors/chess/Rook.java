package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    /**
     *
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {

        if (!isValidMove(targetPosition)) {
            return false;
        }

        if (isPositionOccupiedBySameColour(targetPosition)) {
            return false;
        }
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // the increment might be positive or negative.
        if (targetPosition.x == getPosition().x) {
            int start = getPosition().getYOffset();
            int end = targetPosition.getYOffset();

            if (isHorizontalMovementObstructed(start, end, targetPosition)) {
                return false;
            }
        }
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        if (targetPosition.y == getPosition().y) {
            int start = getPosition().getXOffset();
            int end = targetPosition.getXOffset();

            if (isVerticalMovementObstructed(start, end, targetPosition)) {
                return false;
            }
        }
        //If we get here - is is a valid move. Physically move the piece and answer true.
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private List<Position> getVerticalPositionsInPath(int start, int end, int increment, Position targetPosition) {
        List<Position> positions = new ArrayList<>();

        for (int x = start+increment; x != end; x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }

        return positions;
    }

    private List<Position> getHorizontalPositionsInPath(int start, int end, int increment, Position targetPosition) {
        List<Position> positions = new ArrayList<>();

        for (int y = start+increment; y != end; y = y + increment) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }

        return positions;
    }

    private int getIncrement(int start, int end) {
        if (start > end) {
            return -1;
        }

        return 1;
    }

    private boolean isPositionOccupiedBySameColour(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);

        if (targetPiece == null) {
            return false;
        }

        return targetPiece.getColor() == getColor();
    }

    private boolean isValidMove(Position targetPosition) {
        return targetPosition.x == getPosition().x || targetPosition.y == getPosition().y;
    }

    private boolean isHorizontalMovementObstructed(int start, int end, Position targetPosition) {
        return getHorizontalPositionsInPath(start, end, getIncrement(start, end), targetPosition)
                .stream()
                .anyMatch(position -> getChessboard().getPieceAt(position) != null);
    }

    private boolean isVerticalMovementObstructed(int start, int end, Position targetPosition) {
        return getVerticalPositionsInPath(start, end, getIncrement(start, end), targetPosition)
                .stream()
                .anyMatch(position -> getChessboard().getPieceAt(position) != null);
    }
}
