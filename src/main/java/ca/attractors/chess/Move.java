package ca.attractors.chess;

import java.util.Arrays;

public class Move {

    private final ChessPiece chessPiece;
    private final Position target;
    private final MoveDirection moveDirection;

    public Move(ChessPiece chessPiece, Position target) {
        this.chessPiece = chessPiece;
        this.target = target;
        this.moveDirection = MoveDirection.getMoveDirection(chessPiece.getPosition(), target);
    }

    public boolean isValid() {
        if (!isMoveValidForPiece()
                || isPositionOccupiedWithSameColor()
                || !isMoveInLimit()) {
            return false;
        }
        return isMoveBlocked();
    }

    private boolean isMoveValidForPiece(){
        MoveDirection[] moveDirections = chessPiece.getValidMoveDirections();
        return Arrays.stream(moveDirections)
                .anyMatch(moveDirection -> moveDirection == this.moveDirection);
    }

    protected boolean isPositionOccupiedWithSameColor() {
        ChessPiece targetPiece = chessPiece.getChessboard().getPieceAt(target);
        if (targetPiece == null) {
            return false;
        }
        return targetPiece.getColor() == chessPiece.getColor();
    }

    private boolean isMoveInLimit() {
        if ((chessPiece.getMoveLimit() == MoveLimit.NearestSquare)
                && (Math.abs(chessPiece.getPosition().getHorizontalShift(target)) <= 1)
                && Math.abs(chessPiece.getPosition().getVerticalShift(target)) <= 1) {
            return true;
        }
        return chessPiece.getMoveLimit() == MoveLimit.NoLimit;
    }

    private boolean isMoveBlocked() {
        int startY = chessPiece.getPosition().getYOffset();
        int endY = target.getYOffset();
        int startX = chessPiece.getPosition().getXOffset();
        int endX = target.getXOffset();
        int incrementX = moveDirection.getIncrementX();
        int incrementY = moveDirection.getIncrementY();
        for (int x = startX + incrementX, y = startY + incrementY; (x != endX) || (y != endY); x += incrementX, y += incrementY) {
            Position position = Position.getPositionFor(x, y);
            if (chessPiece.getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }
        return true;
    }

}
