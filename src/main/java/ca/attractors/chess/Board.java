package ca.attractors.chess;

import java.util.List;

public class Board {
    private ChessPiece[][] pieces = new ChessPiece[8][8];

    public Board() {
    }

    public ChessPiece getPieceAt(Position position) {
        return pieces[position.getXOffset()] [position.getYOffset()];
    }

    void putPieceAt(ChessPiece chessPiece, Position position) {
        pieces[position.getXOffset()][position.getYOffset()] = chessPiece;
    }

    public boolean isSameColorAtTargetPosition(PieceColor pieceColor, Position targetPosition) {
        ChessPiece targetPiece = getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == pieceColor)
                return true;
        }
        return false;
    }

    public boolean checkMovementPathForOtherPieces(List<Position> positions) {
        for (Position position: positions) {
            if (isPositionFilled(position)) return true;
        }
        return false;
    }

    public boolean isPositionFilled(Position position) {
        if (getPieceAt(position) != null) {
            return true;
        }
        return false;
    }

    // Code Smells
    void movePieceTo(ChessPiece chessPiece, Position position) {
        putPieceAt(null, chessPiece.getPosition());
        putPieceAt(chessPiece, position);
    }

    public Position getPositionOf(ChessPiece piece) {
        for (Position position: Position.values())
            if (getPieceAt(position) == piece)
                return position;
        return null;
    }
}
