package ca.attractors.chess;

public class Bishop extends ChessPiece {


    public Bishop(PieceColor color, Board board, Position position) {
        super(color, board,position);
    }

    @Override
    protected MoveDirection[] getValidMoveDirections() {
        return MoveDirection.DIAGONAL_ONLY;
    }

    @Override
    protected MoveLimit getMoveLimit() {
        return MoveLimit.NoLimit;
    }


}
