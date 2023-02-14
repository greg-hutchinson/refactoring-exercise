package ca.attractors.chess;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        return getPosition().isHorizontalOrVertical(targetPosition) && isColorValid(targetPosition) && isStraightPathFree(targetPosition);
    }
}
