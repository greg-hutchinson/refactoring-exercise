package ca.attractors.chess;

public class Bishop extends ChessPiece {

    protected Bishop(Chessboard chessboard, PieceColor colour) {
        super(chessboard, colour);
    }

    public boolean moveTo(Position position){
        if (!isValidMove(position)) {
            return false;
        }

        getChessboard().movePieceTo(this, position);
        return true;
    }

    public boolean isValidMove(Position position) {
        return true;//TODO
    }
}
