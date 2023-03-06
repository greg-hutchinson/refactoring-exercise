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

    private boolean isValidMove(Position targetPosition) {

        if (!getPosition().isHorizontalMove(targetPosition) && !getPosition().isVerticalMove(targetPosition)) {
            return false;
        }

        if (getChessboard().isPositionOccupiedBySameColour(targetPosition, this.getColor())) {
            return false;
        }

       return isMovementPathClear(targetPosition);
    }

    private boolean isMovementPathClear(Position targetPosition) {
        if (getPosition().isVerticalMove(targetPosition)) {
            return !getChessboard().isVerticalMovementObstructed(getPosition().getYOffset(), targetPosition.getYOffset(), targetPosition);
        }

        if (getPosition().isHorizontalMove(targetPosition)) {
            return !getChessboard().isHorizontalMovementObstructed(getPosition().getXOffset(), targetPosition.getXOffset(), targetPosition);
        }

        return true;
    }
}
