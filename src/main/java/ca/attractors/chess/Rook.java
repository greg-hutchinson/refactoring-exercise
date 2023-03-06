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
        if (isMovementValid(targetPosition)) {
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }

        return false;
    }

    private boolean isMovementValid(Position targetPosition) {
        if(!Position.isTargetHorizontalToCurrentPosition(targetPosition, getPosition())&&
                !Position.isTargetVerticalToCurrentPosition(targetPosition, getPosition())){
            return false;
        }

        if (isSameColorAtTargetPosition(targetPosition)) return false;
        if (isPathNotEmpty(targetPosition)) return false;

        return true;
    }

    private boolean isSameColorAtTargetPosition(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == getColor())
                return true;
        }
        return false;
    }

    private boolean isPathNotEmpty(Position targetPosition) {
        Rook.movementPath mp = new movementPath();
        if (Position.isTargetVerticalToCurrentPosition(targetPosition, getPosition())) {
            mp.direction = Direction.VERTICAL;
            mp.startPositionOffset = getPosition().getXOffset();
            mp.targetPositionOffset = targetPosition.getXOffset();
            mp.movementPathOffset = targetPosition.getYOffset();
        }
        if (Position.isTargetHorizontalToCurrentPosition(targetPosition, getPosition())) {
            mp.direction = Direction.HORIZONTAL;
            mp.startPositionOffset = getPosition().getYOffset();
            mp.targetPositionOffset = targetPosition.getYOffset();
            mp.movementPathOffset = targetPosition.getXOffset();
        }
        if (mp.startPositionOffset > mp.targetPositionOffset)
            mp.increment = -1;
        else
            mp.increment = 1;

        List<Position> positions = new ArrayList<>();
        getPositionsInMovementPath(mp, positions);
        if (checkMovementPathForOtherPieces(positions)) return true;
        return false;
    }

    private static void getPositionsInMovementPath(Rook.movementPath mp, List<Position> positions) {
        for (int p = mp.startPositionOffset + mp.increment; p != mp.targetPositionOffset; p = p + mp.increment) {
            if(mp.direction==Direction.HORIZONTAL)
                positions.add(Position.getPositionFor(mp.movementPathOffset, p));
            else if(mp.direction==Direction.VERTICAL)
                positions.add(Position.getPositionFor(p, mp.movementPathOffset));
            else return;
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

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    class movementPath{
        Direction direction;
        int startPositionOffset;
        int targetPositionOffset;
        int movementPathOffset;
        int increment;
    }

}
