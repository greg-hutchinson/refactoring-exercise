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
        if (isTargetPositionInvalid(targetPosition)
            || isColorInvalid(targetPosition)
            || isPathBlocked(targetPosition)){
            return false;
        }
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isPathBlocked(Position targetPosition) {
        int start;
        int end;

        if (targetPosition.y == getPosition().y) {
            start = getPosition().getXOffset();
            end = targetPosition.getXOffset();
        }
        else {
            start = getPosition().getYOffset();
            end = targetPosition.getYOffset();
        }

        int increment = 0;
        if (start > end) {
            increment = -1;
        }
        else {
            increment = 1;
        }

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

    private boolean isColorInvalid(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        return targetPiece != null
                && targetPiece.getColor() == getColor();
    }

    private boolean isTargetPositionInvalid(Position targetPosition) {
        return targetPosition.x != getPosition().x
                && targetPosition.y != getPosition().y;
    }
}
