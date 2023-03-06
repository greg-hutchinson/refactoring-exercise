package ca.attractors.chess;

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

    public boolean isOccupiedTargetPieceColorDifferent(Position targetPosition, PieceColor color) {
        ChessPiece targetPiece = this.getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == color)
                return true;
        }
        return false;
    }

}