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
        //If we get here - is is a valid move. Physically move the piece and answer true.
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isValidMove(Position targetPosition) {
        return !(isDiagonalMove(targetPosition)
                || isTargetSameColour(targetPosition)
                || isHorizontalPathNotClear(targetPosition)
                || isVerticalPathNotClear(targetPosition));
    }

    private boolean isTargetSameColour(Position targetPosition) {
        //Next - Check to make sure that if the target square is occupied it is not the same color
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == getColor())
                return true;
        }
        return false;
    }


    private boolean isHorizontalPathNotClear(Position targetPosition) {
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        if (targetPosition.y != getPosition().y)
            return false;
        return isPositionsClear(getVerticalPositions(targetPosition));
    }

    private boolean isVerticalPathNotClear(Position targetPosition) {
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // the increment might be positive or negative.
        if (targetPosition.x != getPosition().x)
            return false;
        return isPositionsClear(getHorizontalPositions(targetPosition));
    }

    private boolean isPositionsClear(List<Position> positions) {
        for (Position position : positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }
        return false;
    }

    private List<Position> getVerticalPositions(Position targetPosition) {
        int start = getPosition().getXOffset();
        int end = targetPosition.getXOffset();
        int increment = getIncrement(start, end);
        List<Position> positions = new ArrayList<>();
        for (int x = start+increment; x != end; x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }
        return positions;
    }

    private List<Position> getHorizontalPositions(Position targetPosition) {
        int start = getPosition().getYOffset();
        int end = targetPosition.getYOffset();
        int increment = getIncrement(start, end);

        List<Position> positions = new ArrayList<>();
        for (int y = start +increment; y != end; y = y + increment)
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        return positions;
    }

    private int getIncrement(int start, int end)
    {
        if (start > end)
            return -1;
        return 1;
    }

    private boolean isDiagonalMove(Position targetPosition) {
        //if it is not the same x or y coordinate it is not a rooks valid move at all
        if (targetPosition.x != getPosition().x && targetPosition.y != getPosition().y) {
            return true;
        }
        return false;
    }


}
