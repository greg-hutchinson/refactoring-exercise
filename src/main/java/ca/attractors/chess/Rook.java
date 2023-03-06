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

       if (isValidMove(targetPosition)) {
           getChessboard().movePieceTo(this, targetPosition);
           return true;
       }

       return false;
    }

    private boolean isValidMove(Position targetPostion) {

        if (!isHorizontalOrVerticalMove(targetPostion)) {
            return false;
        }

        if (isPositionOccupiedBySameColour(targetPostion)) {
            return false;
        }

       return isMovementPathClear(targetPostion);
    }

    private boolean isHorizontalOrVerticalMove(Position targetPosition) {
        return targetPosition.x == getPosition().x || targetPosition.y == getPosition().y;
    }

    private boolean isPositionOccupiedBySameColour(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);

        if (targetPiece == null) {
            return false;
        }

        return targetPiece.getColor() == getColor();
    }

    private boolean isMovementPathClear(Position targetPosition) {
        if (targetPosition.x == getPosition().x) {
            return !isHorizontalMovementObstructed(getPosition().getYOffset(), targetPosition.getYOffset(), targetPosition);
        }

        if (targetPosition.y == getPosition().y) {
            return !isVerticalMovementObstructed(getPosition().getXOffset(), targetPosition.getXOffset(), targetPosition);
        }

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
