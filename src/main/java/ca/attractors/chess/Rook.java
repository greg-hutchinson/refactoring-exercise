package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    protected Rook(Chessboard chessboard, PieceColor color) {
        super(chessboard, color);
    }

    /**
     *
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {
        if (!isValidMove(targetPosition)) return false;

        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    protected boolean isValidMove(Position targetPosition) {
        return isLinearMove(targetPosition)
                && !isTargetSameColour(targetPosition)
                && isPathClear(targetPosition);
    }

    private boolean isPathClear(Position targetPosition) {
        if (isVerticalMove(targetPosition))
            return isPositionsClear(getVerticalPositions(targetPosition));
        return isPositionsClear(getHorizontalPositions(targetPosition));
    }

    private List<Position> getHorizontalPositions(Position targetPosition) {
        int start = getPosition().getXOffset();
        int end = targetPosition.getXOffset();
        int increment = getIncrement(start, end);
        List<Position> positions = new ArrayList<>();
        for (int x = start+increment; x != end; x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }
        return positions;
    }

    private List<Position> getVerticalPositions(Position targetPosition) {
        int start = getPosition().getYOffset();
        int end = targetPosition.getYOffset();
        int increment = getIncrement(start, end);

        List<Position> positions = new ArrayList<>();
        for (int y = start +increment; y != end; y = y + increment)
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        return positions;
    }

    private boolean isLinearMove(Position targetPosition) {
        return isHorizontalMove(targetPosition) || isVerticalMove(targetPosition);
    }

    private boolean isHorizontalMove(Position targetPosition) {
        return targetPosition.x != getPosition().x;
    }

    private boolean isVerticalMove(Position targetPosition) {
        return targetPosition.y != getPosition().y;
    }
}
