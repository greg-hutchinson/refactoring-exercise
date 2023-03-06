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

        if (isMoveValid(targetPosition)) {
            //If we get here - is is a valid move. Physically move the piece and answer true.
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }
        return false;
    }

    private boolean isFileEmptyBetweenSourceAndTarget(Position targetPosition) {
        if (targetPosition.y == getPosition().y) {
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
                if (getChessboard().getPieceAt(position) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRankEmptyBetweenSourceAndTarget(Position targetPosition) {
        if (targetPosition.x == getPosition().x) {
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
                if (getChessboard().getPieceAt(position) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOccupiedTargetPieceColorDifferent(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == getColor())
                return true;
        }
        return false;
    }

    private boolean isMoveValid(Position targetPosition) {

        if(Position.isMoveDiagonal(targetPosition, getPosition())) return false;

        //Next - Check to make sure that if the target square is occupied it is not the same color
        if (isOccupiedTargetPieceColorDifferent(targetPosition)) return false;

        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // the increment might be positive or negative.
        if (isRankEmptyBetweenSourceAndTarget(targetPosition)) return false;

        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        if (isFileEmptyBetweenSourceAndTarget(targetPosition)) return false;

        return true;
    }

}
