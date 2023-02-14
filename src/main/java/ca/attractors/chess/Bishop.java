package ca.attractors.chess;

public class Bishop extends ChessPiece {


    public Bishop(PieceColor color, Board board, Position position) {
        super(color, board,position);
    }

    @Override
    public MoveDirection[] getValidMoveDirections() {
        return MoveDirection.DIAGONAL_ONLY;
    }

    @Override
    public MoveLimit getMoveLimit() {
        return MoveLimit.NoLimit;
    }


}
