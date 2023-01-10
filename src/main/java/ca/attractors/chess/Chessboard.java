package ca.attractors.chess;

public class Chessboard {
    private ChessPiece[][] pieces = new ChessPiece[8][8];

    public Chessboard() {
    }

    public ChessPiece getPieceAt(Position position) {
        return pieces[position.getXOffset()] [position.getYOffset()];
    }

    void putPieceAt(ChessPiece chessPiece, Position position) {
        pieces[position.getXOffset()][position.getYOffset()] = chessPiece;
        if (chessPiece != null)
            chessPiece.pieceMoveTo(position);
    }

    // Code Smells
    void movePieceTo(ChessPiece chessPiece, Position position) {
        Position old = getPositionOf(chessPiece);
        ChessPiece piece = getPieceAt(position);
        if (piece != null) {
            piece.removed();
        }
        putPieceAt(chessPiece, position);
        putPieceAt(null, old);

    }

    public Position getPositionOf(ChessPiece piece) {
        for (Position position: Position.values())
            if (getPieceAt(position) == piece)
                return position;
        return null;
    }
}