package ca.attractors.chess;

public class ChessPiece {
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
        int start;
        int end;

        if (targetPosition.y == getPosition().y) {
            start = getPosition().getXOffset();
            end = targetPosition.getXOffset();
        } else {
            start = getPosition().getYOffset();
            end = targetPosition.getYOffset();
        }

        int increment = -1;
        if (start <= end) {
            increment = 1;
        }

        Position position;
        for (int v = start + increment; v != end; v = v + increment) {
            if (targetPosition.y == getPosition().y) {
                position = Position.getPositionFor(v, targetPosition.getYOffset());
            } else {
                position = Position.getPositionFor(targetPosition.getXOffset(), v);
            }

            if (getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }

        return true;
    }
}
