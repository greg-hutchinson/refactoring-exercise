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
        return getName() + "{" + getColor() + " at: " + getPosition() + "}";
    }

    public boolean isColorValid(Position targetPosition) {
        ChessPiece targetPiece = this.board.getPieceAt(targetPosition);
        return !(targetPiece != null && targetPiece.getColor() == getColor());
    }

    public boolean isStraightPathFree(Position targetPosition) {

        int start = getStart(targetPosition);
        int end = getEnd(targetPosition);
        int increment = -1;
        if (start <= end) {
            increment = 1;
        }

        return !isPathBlockedTo(targetPosition, start, end, increment);
    }

    private int getStart(Position targetPosition) {
        if (targetPosition.y == getPosition().y)
            return getPosition().getXOffset();
        return getPosition().getYOffset();
    }
    private int getEnd(Position targetPosition) {
        if (targetPosition.y == getPosition().y)
            return targetPosition.getXOffset();
        return targetPosition.getYOffset();
    }

    private boolean isPathBlockedTo(Position targetPosition, int start, int end, int increment) {
        Position position;
        for (int v = start + increment; v != end; v = v + increment) {
            if (targetPosition.y == getPosition().y) {
                position = Position.getPositionFor(v, targetPosition.getYOffset());
            } else {
                position = Position.getPositionFor(targetPosition.getXOffset(), v);
            }

            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {
        if (isValidMove(targetPosition)) {
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }
        return false;
    }

    protected abstract boolean isValidMove(Position targetPosition);
}
