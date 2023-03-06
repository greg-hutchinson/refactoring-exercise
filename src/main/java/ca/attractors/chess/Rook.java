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
        //if it is not the same x or y coordinate it is not a rooks valid move at all
        if (targetPosition.x != getPosition().x && targetPosition.y != getPosition().y) { //don't need to change
            return false;
        }
        //Next - Check to make sure that if the target square is occupied it is not the same color
        if (isSquareOccupiedBySameColor(targetPosition)) return false;

        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // the increment might be positive or negative.

        if (targetPosition.x == getPosition().x) {
            if (isInvalidHorizontalMove(targetPosition)) return false;
        }
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        if (targetPosition.y == getPosition().y) {
            if (isInvalidVerticalMove(targetPosition)) return false;
        }
        //If we get here - it is a valid move. Physically move the piece and answer true.
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isInvalidVerticalMove(Position targetPosition) {
        int start = getPosition().getXOffset();
        int end = targetPosition.getXOffset();
        int increment = 0;
        if (start > end)
            increment = -1;
        else
            increment = 1;
        List<Position> positions = new ArrayList<>();
        for (int x = start+increment; x != end; x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }
        for (Position position: positions) {
            if (isSuccessfulMove(position)) return true;
        }
        return false;
    }

    private boolean isInvalidHorizontalMove(Position targetPosition) {
        int start = getPosition().getYOffset();
        int end = targetPosition.getYOffset();
        int increment = 0;
        if (start > end)
            increment = -1;
        else
            increment = 1;
        List<Position> positions = new ArrayList<>();
        for (int y = start+increment; y != end; y = y + increment) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }
        for (Position position: positions) {
            if (isSuccessfulMove(position)) return true;
        }
        return false;
    }

    private boolean isSuccessfulMove(Position position) {
        if (getChessboard().getPieceAt(position) != null) {
            return true;
        }
        return false;
    }

    private boolean isSquareOccupiedBySameColor(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null)
            return targetPiece.getColor() == getColor();
        return false;
    }
    
}
