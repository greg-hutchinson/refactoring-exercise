package ca.attractors.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ChessPiece {

    private PieceColor color;
    private Position position;

    private Chessboard chessboard;

    public ChessPiece(PieceColor color, Position position, Chessboard chessboard) {
        this.color = color;
        this.position = position;
        this.chessboard = chessboard;
    }

    protected Chessboard getChessboard() {
        return chessboard;
    }

    public Position getPosition() {
        return position;
    }

    public PieceColor getColor() {
        return color;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public abstract List<Position> getValidMovePositions();

    /**
     * This is a shortcut method of board#moveTo method
     * @param position
     */
    public final void moveTo(Position position) {
        chessboard.moveTo(this, position);
    }

    /**
     * This is the shared movePieceTo implementation
     * @param position
     * @return
     */
    public boolean movePieceTo(Position position) {
        boolean isValid = getValidMovePositions().contains(position);
        if (isValid) {
            this.position = position;
            return true;
        } else {
            return false;
        }
    }

    protected boolean isPositionVacant(Position position) {
        ChessPiece piece = chessboard.getPieceAtPosition(position);
        return piece == null;
    }

    protected boolean isPositionOccupiedByOpponent(Position position) {
        ChessPiece piece = chessboard.getPieceAtPosition(position);
        return piece != null || piece.getColor() == getColor().toggle();
    }

    protected List<Position> peekUp() {
        List<Position> validPositions = new ArrayList<>();

        Position upPathFinder = getPosition();
        while (!upPathFinder.atTopBorder()) {
            Position upPosition = upPathFinder.up();
            if (isPositionVacant(upPosition)) {
                validPositions.add(upPosition);
                upPathFinder = upPosition;
            } else {
                if (isPositionOccupiedByOpponent(upPosition))
                    validPositions.add(upPosition);
                break;
            }
        }

        return validPositions;
    }

    protected List<Position> peekUpLeft() {
        List<Position> validPositions = new ArrayList<>();

        Position upLeftPathFinder = getPosition();
        while (!upLeftPathFinder.atLeftBorder() && !upLeftPathFinder.atTopBorder()) {
            Position upLeftPosition = upLeftPathFinder.upLeft();
            if (isPositionVacant(upLeftPosition)) {
                validPositions.add(upLeftPosition);
                upLeftPathFinder = upLeftPosition;
            } else {
                if (isPositionOccupiedByOpponent(upLeftPosition))
                    validPositions.add(upLeftPosition);
                break;
            }
        }

        return validPositions;
    }

    protected List<Position> peekUpRight() {
        List<Position> validPositions = new ArrayList<>();

        Position upRightPathFinder = getPosition();
        while (!upRightPathFinder.atRightBorder() && !upRightPathFinder.atTopBorder()) {
            Position upRightPosition = upRightPathFinder.upRight();
            if (isPositionVacant(upRightPosition)) {
                validPositions.add(upRightPosition);
                upRightPathFinder = upRightPosition;
            } else {
                if (isPositionOccupiedByOpponent(upRightPosition))
                    validPositions.add(upRightPosition);
                break;
            }
        }

        return validPositions;
    }

    protected List<Position> peekDown() {
        List<Position> validPositions = new ArrayList<>();

        Position downPathFinder = getPosition();
        while (!downPathFinder.atBottomBorder()) {
            Position downPosition = downPathFinder.down();
            if (isPositionVacant(downPosition)) {
                validPositions.add(downPosition);
                downPathFinder = downPosition;
            } else {
                if (isPositionOccupiedByOpponent(downPosition))
                    validPositions.add(downPosition);
                break;
            }
        }

        return validPositions;
    }

    protected List<Position> peekDownLeft() {
        List<Position> validPositions = new ArrayList<>();

        Position downLeftPathFinder = getPosition();
        while (!downLeftPathFinder.atBottomBorder() && !downLeftPathFinder.atLeftBorder()) {
            Position downLeftPosition = downLeftPathFinder.downLeft();
            if (isPositionVacant(downLeftPosition)) {
                validPositions.add(downLeftPosition);
                downLeftPathFinder = downLeftPosition;
            } else {
                if (isPositionOccupiedByOpponent(downLeftPosition))
                    validPositions.add(downLeftPosition);
                break;
            }
        }

        return validPositions;
    }

    protected List<Position> peekDownRight() {
        List<Position> validPositions = new ArrayList<>();

        Position downRightPathFinder = getPosition();
        while (!downRightPathFinder.atRightBorder() && !downRightPathFinder.atBottomBorder()) {
            Position downRightPosition = downRightPathFinder.downRight();
            if (isPositionVacant(downRightPosition)) {
                validPositions.add(downRightPosition);
                downRightPathFinder = downRightPosition;
            } else {
                if (isPositionOccupiedByOpponent(downRightPosition))
                    validPositions.add(downRightPosition);
                break;
            }
        }

        return validPositions;
    }

    protected List<Position> peekLeft() {
        List<Position> validPositions = new ArrayList<>();

        Position leftPathFinder = getPosition();
        while (!leftPathFinder.atLeftBorder()) {
            Position leftPosition = leftPathFinder.left();
            if (isPositionVacant(leftPosition)) {
                validPositions.add(leftPosition);
                leftPathFinder = leftPosition;
            } else {
                if (isPositionOccupiedByOpponent(leftPosition))
                    validPositions.add(leftPosition);
                break;
            }
        }

        return validPositions;
    }

    protected List<Position> peekRight() {
        List<Position> validPositions = new ArrayList<>();

        Position rightPathFinder = getPosition();
        while (!rightPathFinder.atRightBorder()) {
            Position rightPosition = rightPathFinder.right();
            if (isPositionVacant(rightPosition)) {
                validPositions.add(rightPosition);
                rightPathFinder = rightPosition;
            } else {
                if (isPositionOccupiedByOpponent(rightPosition))
                    validPositions.add(rightPosition);
                break;
            }
        }

        return validPositions;
    }

    @Override
    public String toString() {
        return getName() + "{" + getColor() + " at: " + getPosition() +"}";
    }

    /**
     * Return an immutable copy
     * @return
     */
    public ImmutableChessPiece asImmutable() {
        return new ImmutableChessPiece(color, position);
    }

}

class ImmutableChessPiece extends ChessPiece {

    public ImmutableChessPiece(PieceColor color, Position position) {
        super(color, position, null);
    }

    @Override
    public boolean movePieceTo(Position position) {
        throw new UnsupportedOperationException("Invalid move");
    }

    @Override
    public List<Position> getValidMovePositions() {
        throw new IllegalStateException("Not applicable for an immutable piece");
    }
}
