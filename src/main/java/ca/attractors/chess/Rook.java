package ca.attractors.chess;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    /**
     *
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {
        if (isDiagonalMove(targetPosition))
            return false;

        if (isSquareOccupiedBySameColor(targetPosition))
            return false;

        if (targetPosition.x == getPosition().x) {
            PiecePath horizontalPath = new PiecePath(getPosition().getYOffset(), targetPosition.getYOffset(), "HORIZONTAL");
            if (isInvalidMove(targetPosition, horizontalPath))
                return false;
        }

        if (targetPosition.y == getPosition().y) {
            PiecePath verticalPath = new PiecePath(getPosition().getXOffset(), targetPosition.getXOffset(), "VERTICAL");
            if (isInvalidMove(targetPosition, verticalPath))
                return false;
        }
        //If we get here - it is a valid move. Physically move the piece and answer true.
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isDiagonalMove(Position targetPosition) {
        return targetPosition.x != getPosition().x && targetPosition.y != getPosition().y;
    }

    private boolean isSquareOccupiedBySameColor(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null)
            return targetPiece.getColor() == getColor();
        return false;
    }

    private boolean isInvalidMove(Position targetPosition, PiecePath path) {
        int increment = targetPosition.getIncrement(path.getStart(), path.getEnd());
        List<Position> positions = new ArrayList<>();
        if(Objects.equals(path.getDirection(), "HORIZONTAL"))
            positions = getHorizontalPositions(targetPosition, path, increment);
        else if(Objects.equals(path.getDirection(), "VERTICAL"))
            positions = getVerticalPositions(targetPosition, path, increment);
        for (Position position: positions) {
            if (isSuccessfulMove(position)) return true;
        }
        return false;
    }

    private static List<Position> getVerticalPositions(Position targetPosition, PiecePath path, int increment) {
        List<Position> positions = new ArrayList<>();
        for (int x = path.getStart() + increment; x != path.getEnd(); x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }
        return positions;
    }

    private static List<Position> getHorizontalPositions(Position targetPosition, PiecePath path, int increment) {
        List<Position> positions = new ArrayList<>();
        for (int y = path.getStart() + increment; y != path.getEnd(); y = y + increment) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }
        return positions;
    }

    private boolean isSuccessfulMove(Position position) {
        return getChessboard().getPieceAt(position) != null;
    }

    static class PiecePath {
        private final int start;
        private final int end;
        private final String direction;

        PiecePath(int start, int end, String direction) {
            this.start = start;
            this.end = end;
            this.direction = direction;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public String getDirection() {
            return this.direction;
        }
    }
}
