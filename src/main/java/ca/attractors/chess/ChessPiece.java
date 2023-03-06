package ca.attractors.chess;

import java.util.List;

public abstract class ChessPiece {
    private final Board board;
    private final PieceColor color;

    public ChessPiece(PieceColor color, Board board, Position position) {
        this.board = board;
        this.color = color;
        board.putPieceAt(this, position);
    }

    public Board getChessboard() {
        return board;
    }

    public Position getPosition() {
        return getChessboard().getPositionOf(this);
    }

    public PieceColor getColor() {
        return color;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
    @Override
    public String toString() {
        return getName() + "{" + getColor() + " at: " + getPosition() +"}";
    }

    /**
     *
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {
        if (isValidMove(targetPosition)) {
            //If we get here - is is a valid move. Physically move the piece and answer true.
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }
        return false;
    }

    protected abstract boolean isTargetPositionCoordsValid(Position targetPosition);

    protected abstract boolean canBeImpeded();

    protected boolean isPathImpeded(Position targetPosition) {
        List<Position> positions = getPosition().getPositionsForPath(targetPosition);

        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }

        return false;
    }

    protected boolean isValidMove(Position targetPosition) {
        if (!isTargetPositionCoordsValid(targetPosition)) return false;

        if (isPositionOccupiedBySameColour(targetPosition)) return false;

        if (canBeImpeded() && isPathImpeded(targetPosition)) return false;

        return true;
    }

    protected boolean isPositionOccupiedBySameColour(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == getColor())
                return true;
        }
        return false;
    }
}
