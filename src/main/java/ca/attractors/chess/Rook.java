package ca.attractors.chess;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (targetPosition.isDiagonalMove(targetPosition, this))
            return false;

        if (targetPosition.isSquareOccupiedBySameColor(targetPosition, this))
            return false;

        if (targetPosition.isHorizontalMove(targetPosition, this)) {
            if (targetPosition.isInvalidMove(targetPosition, this))
                return false;
        }

        if (targetPosition.isVerticalMove(targetPosition, this)) {
            if (targetPosition.isInvalidMove(targetPosition,this))
                return false;
        }
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

}
