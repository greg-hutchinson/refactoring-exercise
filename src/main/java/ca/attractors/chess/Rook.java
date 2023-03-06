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
        if (isMovementInvalid(targetPosition)) return false;

        //Next - Check to make sure that if the target square is occupied it is not the same color
        if (isSameColorAtTargetPosition(targetPosition)) return false;

        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // the increment might be positive or negative.
        if (isHorizontalPathNotEmpty(targetPosition)) return false;
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        if (isVerticalPathNotEmpty(targetPosition)) return false;
        //If we get here - this is a valid move. Physically move the piece and answer true.
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isMovementInvalid(Position targetPosition) {
        if (targetPosition.x != getPosition().x && targetPosition.y != getPosition().y) {
            return true;
        }
        return false;
    }

    private boolean isSameColorAtTargetPosition(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == getColor())
                return true;
        }
        return false;
    }

    private boolean isHorizontalPathNotEmpty(Position targetPosition) {
        if (targetPosition.x == getPosition().x) {
            Rook.movementPath mp = new movementPath();
            mp.direction = "Horizontal";
            mp.startPositionOffset = getPosition().getYOffset();
            mp.targetPositionOffset = targetPosition.getYOffset();
            mp.movementPathOffset = targetPosition.getXOffset();

            if (mp.startPositionOffset > mp.targetPositionOffset)
                mp.increment = -1;
            else
                mp.increment = 1;

            if (isPathNotEmpty(mp)) return true;
        }
        return false;
    }

    private boolean isVerticalPathNotEmpty(Position targetPosition) {
        if (targetPosition.y == getPosition().y) {
            Rook.movementPath mp = new movementPath();
            mp.direction = "Vertical";
            mp.startPositionOffset = getPosition().getXOffset();
            mp.targetPositionOffset = targetPosition.getXOffset();
            mp.movementPathOffset = targetPosition.getYOffset();

            if (mp.startPositionOffset > mp.targetPositionOffset)
                mp.increment = -1;
            else
                mp.increment = 1;

            if (isPathNotEmpty(mp)) return true;
        }
        return false;
    }

    private boolean isPathNotEmpty(Rook.movementPath mp) {

        List<Position> positions = new ArrayList<>();
        getPositionsInMovementPath(mp, positions);
        if (checkMovementPathForOtherPieces(positions)) return true;
        return false;
    }

    private static void getPositionsInHorizontalMovementPath(Rook.movementPath mp, List<Position> positions) {
        for (int p = mp.startPositionOffset + mp.increment; p != mp.targetPositionOffset; p = p + mp.increment) {
            positions.add(Position.getPositionFor(mp.movementPathOffset, p));
        }
    }
    private static void getPositionsInVerticalMovementPath(Rook.movementPath mp, List<Position> positions) {
        for (int p = mp.startPositionOffset + mp.increment; p != mp.targetPositionOffset; p = p + mp.increment) {
            positions.add(Position.getPositionFor(p, mp.movementPathOffset));
        }
    }

    private static void getPositionsInMovementPath(Rook.movementPath mp, List<Position> positions) {
        for (int p = mp.startPositionOffset + mp.increment; p != mp.targetPositionOffset; p = p + mp.increment) {
            if(mp.direction=="Horizontal")
                positions.add(Position.getPositionFor(mp.movementPathOffset, p));
            if(mp.direction=="Vertical")
                positions.add(Position.getPositionFor(p, mp.movementPathOffset));
        }
    }

    private boolean checkMovementPathForOtherPieces(List<Position> positions) {
        for (Position position: positions) {
            if (isPositionFilled(position)) return true;
        }
        return false;
    }

    private boolean isPositionFilled(Position position) {
        if (getChessboard().getPieceAt(position) != null) {
            return true;
        }
        return false;
    }

    class movementPath{
        String direction;
        int startPositionOffset;
        int targetPositionOffset;
        int movementPathOffset;
        int increment;
    }

}
