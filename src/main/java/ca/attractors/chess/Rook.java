package ca.attractors.chess;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    /**
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {
        if (isValidMove(targetPosition)) {
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }
        return false;
    }

    private boolean isValidMove(Position targetPosition) {
        return getPosition().isHorizontalOrVertical(targetPosition) && isColorValid(targetPosition) && isStraightPathFree(targetPosition);
    }
}
