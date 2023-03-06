package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    public boolean moveTo(Position targetPosition){

        if (isMoveValid(targetPosition)) {
            //If we get here - this is a valid move. Physically move the piece and answer true.
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }
        return false;

    }

    public boolean isMoveValid(Position targetPosition){

        if (getChessboard().isOccupiedTargetPieceColorDifferent(targetPosition, this.getColor())) return false;



        return true;
    }


}
