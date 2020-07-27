package ca.attractors.chess;

public abstract class ChessPiece {
    private final PieceColor color;

    public ChessPiece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public boolean sameColorAs(ChessPiece otherPiece) {
        return this.color == otherPiece.color;
    }

    @Override
    public String toString() {
        return getName() + "{" + getColor() + "}";
    }

}
