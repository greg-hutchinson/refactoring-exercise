package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    protected Bishop(Chessboard chessboard, PieceColor colour) {
        super(chessboard, colour);
    }

    @Override
    protected boolean isValidMove(Position targetPosition) {
        return isDiagonalMove(targetPosition) && isPathClear(targetPosition);
    }

    private boolean isDiagonalMove(Position targetPosition) {
        return targetPosition.x - getPosition().x == targetPosition.y - getPosition().y;
    }

    private boolean isPathClear(Position targetPosition) {
        if (!isDiagonalMove(targetPosition)){
            return false;
        }
        return isPositionsClear(getDiagnoalPositions(targetPosition));
    }

    private List<Position> getDiagnoalPositions(Position targetPosition) {
        List<Position> positions = new ArrayList<>();
        int horizontalIncrement = getIncrement(getPosition().getXOffset(), targetPosition.getXOffset());
        int verticalIncrement = getIncrement(getPosition().getYOffset(), targetPosition.getYOffset());
        Position tempPosition = getPosition();
        while (!tempPosition.equals(targetPosition)) {
            tempPosition = Position.getPositionFor(tempPosition.getXOffset() + horizontalIncrement, tempPosition.getYOffset() + verticalIncrement);
            if (!tempPosition.equals(targetPosition))
                positions.add(tempPosition);
        }
        return positions;
    }

}
