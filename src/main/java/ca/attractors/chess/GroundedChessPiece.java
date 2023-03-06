package ca.attractors.chess;

import java.util.List;

public abstract class GroundedChessPiece extends ChessPiece {
    public GroundedChessPiece(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    protected boolean isPathImpeded(Position targetPosition) {
        List<Position> positions = getPositionsForPath(targetPosition);

        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }

        return false;
    }

    protected abstract List<Position> getPositionsForPath(Position targetPosition);
}
