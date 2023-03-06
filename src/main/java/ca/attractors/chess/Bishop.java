package ca.attractors.chess;

import java.util.List;

public class Bishop extends ChessPiece {


    public Bishop(PieceColor color, Board board, Position position) {
        super(color, board,position);
    }

    @Override
    protected boolean isMovementValid(Position targetPosition) {
        if(!getPosition().isTargetPositionDiagonal(targetPosition)){
            return false;
        }

        if (getChessboard().isSameColorAtTargetPosition(getColor(),targetPosition)) return false;
        if (isPathNotEmpty(targetPosition)) return false;

        return true;
    }

    private boolean isPathNotEmpty(Position targetPosition) {
        List<Position> positions = getPosition().getPositionsForDiagonalMovementPath(targetPosition);

        if (getChessboard().checkMovementPathForOtherPieces(positions)) return true;
        return false;
    }
}
