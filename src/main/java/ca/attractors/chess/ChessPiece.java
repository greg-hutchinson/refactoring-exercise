package ca.attractors.chess;

public abstract  class ChessPiece {
    private Chessboard chessboard;
    private PieceColor color;

    public ChessPiece(Chessboard chessboard, PieceColor color) {
        this.chessboard = chessboard;
        this.color = color;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public Position getPosition() {
        return chessboard.getPositionOf(this);
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean moveTo(Position targetPosition) {
        Move move = new Move(this, targetPosition);
        if (!canMoveTo(move))
            return false;
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    public boolean canMoveTo(Position targetPosition) {
        return canMoveTo(new Move(this, targetPosition));
    }

    private boolean canMoveTo(Move move) {
        if (move.isOccupiedBySameColor())
            return false;
        return isValidMove(move);
    }

    protected abstract boolean isValidMove(Move move);

    public String getName() {
        return this.getClass().getSimpleName();
    }
    @Override
    public String toString() {
        return getName() + "{" + getColor() + " at: " + getPosition() +"}";
    }
}