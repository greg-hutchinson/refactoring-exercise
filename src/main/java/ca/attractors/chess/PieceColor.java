package ca.attractors.chess;

public enum PieceColor {
    Black, White;

    PieceColor toggle() {
        if (this.equals(Black))
            return White;
        else
            return Black;
    }
}
