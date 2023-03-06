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
        if(!getPosition().isTargetPositionHorizontal(targetPosition)&&
                !getPosition().isTargetPositionVertical(targetPosition)){
            return false;
        }

        if (getChessboard().isSameColorAtTargetPosition(getColor(),targetPosition)) return false;
        if (isPathNotEmpty(targetPosition)) return false;

        return true;
    }

    private boolean isPathNotEmpty(Position targetPosition) {
        List<Position> positions = new ArrayList<>();
        if (getPosition().isTargetPositionVertical(targetPosition)) {
            positions = getPosition().getPositionsForVerticalMovementPath(targetPosition);
        }
        if (getPosition().isTargetPositionHorizontal(targetPosition)) {
            positions = getPosition().getPositionsForHorizontalMovementPath(targetPosition);
        }

        if (checkMovementPathForOtherPieces(positions)) return true;
        return false;
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
}
