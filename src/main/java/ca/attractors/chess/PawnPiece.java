package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

class PawnPiece extends ChessPiece {

    private boolean isEverMoved = false;

    public PawnPiece(PieceColor color, Position position, Chessboard chessboard) {
        super(color, position, chessboard);
    }

    @Override
    public void physicalMoveTo(Position position) {
        super.physicalMoveTo(position);
        isEverMoved = true;
    }

    @Override
    public List<Position> getValidMovePositions() {
        List<Position> allValidPositions = new ArrayList<>();

        allValidPositions.addAll(peekMovePositions());
        allValidPositions.addAll(peekCheckPositions());

        return allValidPositions;
    }

    private List<Position> peekCheckPositions() {
        Position currentPosition = getPosition();
        int step = getColor() == PieceColor.White ? 1 : -1;
        List<Position> validCheckPositions = new ArrayList<>();

        Position leftFrontPosition = getPositionOrDefaultNull(currentPosition.getXOffset() - 1, currentPosition.getYOffset() + step);
        Position rightFrontPosition = getPositionOrDefaultNull(currentPosition.getXOffset() + 1, currentPosition.getYOffset() + step);

        if (leftFrontPosition != null && isPositionOccupiedByOpponent(leftFrontPosition)) {
            validCheckPositions.add(leftFrontPosition);
        }
        if (rightFrontPosition != null && isPositionOccupiedByOpponent(rightFrontPosition)) {
            validCheckPositions.add(rightFrontPosition);
        }

        return validCheckPositions;
    }

    private List<Position> peekMovePositions() {
        Position currentPosition = getPosition();
        int step = getColor() == PieceColor.White ? 1 : -1;
        List<Position> validMovePositions = new ArrayList<>();

        Position oneStep = getPositionOrDefaultNull(currentPosition.getXOffset(), currentPosition.getYOffset() + step);
        Position twoSteps = getPositionOrDefaultNull(currentPosition.getXOffset(), currentPosition.getYOffset() + step + step);

        if (oneStep != null && isPositionVacant(oneStep)) {
            validMovePositions.add(oneStep);
            if (twoSteps != null && isPositionVacant(twoSteps))
                validMovePositions.add(twoSteps);
        }

        return validMovePositions;
    }

    private Position getPositionOrDefaultNull(int xOffset, int yOffset) {
        try {
            return Position.getPositionFor(xOffset, yOffset);
        } catch (Exception ex) {
            return null;
        }
    }
}
