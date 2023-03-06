package ca.attractors.chess;

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
        if (isMovementValid(targetPosition)) {
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }

        return false;
    }

    protected abstract boolean isMovementValid(Position targetPosition);
}
