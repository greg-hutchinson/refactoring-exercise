package ca.attractors.chess;

import java.util.List;

public class Bishop extends GroundedChessPiece {


    public Bishop(PieceColor color, Board board, Position position) {
        super(color, board,position);
    }

    @Override
    protected List<Position> getPositionsForPath(Position targetPosition) {
        return null;
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        return false;
    }

}
