package ca.attractors.chess;

public class Bishop extends ChessPiece {


    public Bishop(PieceColor color, Board board, Position position) {
        super(color, board,position);
    }

    @Override
    protected boolean canBeImpeded() {
        return true;
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        return false;
    }

    @Override
    protected boolean isTargetPositionCoordsValid(Position targetPosition) {
        return false;
    }

}
