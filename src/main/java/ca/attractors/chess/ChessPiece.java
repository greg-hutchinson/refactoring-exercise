package ca.attractors.chess;

public abstract class ChessPiece {
    private final Board board;
    private final PieceColor color;

    public ChessPiece(PieceColor color, Board board, Position position) {
        this.board = board;
        this.color = color;
        board.putPieceAt(this, position); //VS: What about the case when we try to put a pieces at the position where another piece is?
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
