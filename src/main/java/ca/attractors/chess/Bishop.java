package ca.attractors.chess;

public class Bishop extends ChessPiece {

    protected Bishop(Chessboard chessboard, PieceColor colour) {
        super(chessboard, colour);
    }

    public boolean moveTo(Position targetPosition){
        if (!isValidMove(targetPosition)) {
            return false;
        }

        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isValidMove(Position targetPosition) {
        return !isTargetSameColour(targetPosition)
                && isDiagonalMove(targetPosition);//TODO
    }

    private boolean isDiagonalMove(Position targetPosition) {
        return targetPosition.x - getPosition().x == targetPosition.y - getPosition().y;
    }


}
