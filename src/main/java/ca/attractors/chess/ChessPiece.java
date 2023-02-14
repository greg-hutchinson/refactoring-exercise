package ca.attractors.chess;

public abstract class ChessPiece {
    private final Board board;
    private final PieceColor color;

    public ChessPiece(PieceColor color, Board board, Position position) {
        this.board = board;
        this.color = color;
        ChessPiece piece = board.getPieceAt(position);
        if (piece != null) {
            throw new IllegalStateException("Can not instantiate a piece at the same position as an existing piece at position: " + position);
        }
        board.putPieceAt(this, position);
    }

    protected abstract MoveDirection[] getValidMoveDirections();

    protected abstract MoveLimit getMoveLimit();

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

    public boolean moveTo(Position targetPosition) {
        Move move = new Move(this, targetPosition);
        if (!move.isValid()) {
            return false;
        }
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

}
