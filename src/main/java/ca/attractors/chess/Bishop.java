package ca.attractors.chess;

public class Bishop extends GroundedChessPiece {


    public Bishop(PieceColor color, Board board, Position position) {
        super(color, board,position);
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        return false;
    }

}
