package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    protected boolean isMovementValid(Position targetPosition) {
        if(!getPosition().isTargetPositionHorizontal(targetPosition)&&
                !getPosition().isTargetPositionVertical(targetPosition)&&
                !getPosition().isTargetPositionDiagonal(targetPosition)){
            return false;
        }

        if (getChessboard().isSameColorAtTargetPosition(getColor(),targetPosition)) return false;
        if (isPathNotEmpty(targetPosition)) return false;

        return true;
    }

    private boolean isPathNotEmpty(Position targetPosition) {
        List<Position> positions = getPosition().getPositionsForMovementPath(targetPosition);

        if (getChessboard().checkMovementPathForOtherPieces(positions)) return true;
        return false;
    }
}
