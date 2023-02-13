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
        if (!isTargetPositionValid(targetPosition)){
            return false;
        }
        //Next - Check to make sure that if the target square is occupied it is not the same color
        if (!isColorValid(targetPosition)){
            return false;
        }
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // the increment might be positive or negative.
        if (isPathEmpty(targetPosition)) {
            return false;
        }
        //If we get here - is is a valid move. Physically move the piece and answer true.
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isPathEmpty(Position targetPosition) {
        int start,end;

        if (targetPosition.y == getPosition().y) {
            start = getPosition().getXOffset();
            end = targetPosition.getXOffset();
        }
        else {
            start = getPosition().getYOffset();
            end = targetPosition.getYOffset();
        }

        int increment = 0;
        if (start > end)
            increment = -1;
        else
            increment = 1;

        List<Position> positions = new ArrayList<>();

        for (int v = start+increment; v != end; v = v + increment) {
            if (targetPosition.y == getPosition().y) {
                positions.add(Position.getPositionFor(v, targetPosition.getYOffset()));
            }
            else {
                positions.add(Position.getPositionFor(targetPosition.getXOffset(), v));
            }
        }

        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }

        return false;
    }

    private boolean isColorValid(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        return !(targetPiece != null
                && targetPiece.getColor() == getColor());
    }

    private boolean isTargetPositionValid(Position targetPosition) {
        return !(targetPosition.x != getPosition().x
                && targetPosition.y != getPosition().y);
    }
}
