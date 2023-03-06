package ca.attractors.chess;

import java.util.ArrayList;
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

    public boolean isPositionOccupiedBySameColour(Position targetPosition, PieceColor color) {
        ChessPiece targetPiece = this.getPieceAt(targetPosition);

        if (targetPiece == null) {
            return false;
        }

        return targetPiece.getColor() == color;
    }

    public boolean isVerticalMovementObstructed(int start, int end, Position targetPosition) {
        return getHorizontalPositionsInPath(start, end, getIncrement(start, end), targetPosition)
                .stream()
                .anyMatch(position -> this.getPieceAt(position) != null);
    }

    public boolean isHorizontalMovementObstructed(int start, int end, Position targetPosition) {
        return getVerticalPositionsInPath(start, end, getIncrement(start, end), targetPosition)
                .stream()
                .anyMatch(position -> this.getPieceAt(position) != null);
    }

    private List<Position> getHorizontalPositionsInPath(int start, int end, int increment, Position targetPosition) {
        List<Position> positions = new ArrayList<>();

        for (int y = start+increment; y != end; y = y + increment) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }

        return positions;
    }

    private List<Position> getVerticalPositionsInPath(int start, int end, int increment, Position targetPosition) {
        List<Position> positions = new ArrayList<>();

        for (int x = start+increment; x != end; x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }

        return positions;
    }

    private int getIncrement(int start, int end) {
        if (start > end) {
            return -1;
        }

        return 1;
    }
}
