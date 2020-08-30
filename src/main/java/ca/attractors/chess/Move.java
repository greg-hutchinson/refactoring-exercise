package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Move {
    private ChessPiece piece;
    private Position source;
    private Position target;
    private Chessboard chessboard;

    public Move(ChessPiece piece, Position target) {
        this.piece = piece;
        this.source = piece.getPosition();
        this.target = target;
        this.chessboard = piece.getChessboard();
    }


    public boolean isLine() {
        return source.isLineTo(target);
    }
    public boolean isHorizontal() { return source.isHorizontalTo(target); }
    public boolean isVertical() {
        return source.isVerticalTo(target);
    }
    public boolean isDiagonal() {return source.isDiagonalTo(target); }

    public boolean isPathUnoccupied() {
        return !isPathBlocked();

    }
    public boolean isPathBlocked() {
        if (!isLine())
            throw new IllegalStateException("Can't invoke isBlocked if the move is not a line.");
        for (Position position :  source.getPathTo(target))
            if (chessboard.getPieceAt(position) != null)
                return true;
        return false;
    }

    public boolean isOccupiedBySameColor() {
        ChessPiece targetPiece = chessboard.getPieceAt(target);
        if (targetPiece == null)
            return false;
        return piece.getColor() == targetPiece.getColor();
    }
}
