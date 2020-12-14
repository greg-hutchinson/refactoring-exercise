package ca.attractors.chess;

public class Knight extends ChessPiece {
    protected Knight(Chessboard chessboard, PieceColor colour) {
        super(chessboard, colour);
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        return !isTargetSameColour(targetPosition)
                && isLShapedMove(targetPosition);
    }

    private boolean isLShapedMove(Position targetPosition) {
        int verticalChange = Math.abs(targetPosition.getYOffset() - getPosition().getYOffset());
        int horizontalChange = Math.abs(targetPosition.getXOffset() - getPosition().getXOffset());
        return (verticalChange == 1 && horizontalChange == 2) ^ (verticalChange == 2 && horizontalChange == 1);
    }
}
