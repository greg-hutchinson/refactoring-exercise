package ca.attractors.chess;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public enum MoveDirection {
    Invalid(0, 0),
    HorizontalWest(-1, 0),
    HorizontalEast(1, 0),
    VerticalNorth(0, 1),
    VerticalSouth(0, -1),
    DiagonalNorthWest(-1, 1),
    DiagonalNorthEast(1, 1),
    DiagonalSouthWest(-1, -1),
    DiagonalSouthEast(1, -1);

    public final static MoveDirection[] HORIZONTAL_ONLY = new MoveDirection[]{
            HorizontalWest,
            HorizontalEast
    };
    public final static MoveDirection[] VERTICAL_ONLY = new MoveDirection[]{
            VerticalNorth,
            VerticalSouth
    };
    public final static MoveDirection[] DIAGONAL_ONLY = new MoveDirection[]{
            DiagonalNorthWest,
            DiagonalNorthEast,
            DiagonalSouthWest,
            DiagonalSouthEast
    };

    private final int incrementX;
    private final int incrementY;

    MoveDirection(int incrementX, int incrementY) {
        this.incrementX = incrementX;
        this.incrementY = incrementY;
    }

    public int getIncrementX() {
        return incrementX;
    }

    public int getIncrementY() {
        return incrementY;
    }

    public static MoveDirection getMoveDirection(Position start, Position end) {
        int horizontalShift = start.getHorizontalShift(end);
        int verticalShift = start.getVerticalShift(end);
        int horizontalIncrement = getIncrement(horizontalShift);
        int verticalIncrement = getIncrement(verticalShift);
        Stream<MoveDirection> moveDirections = getMoveDirections(horizontalShift, verticalShift);

        return getMoveDirection(moveDirections, horizontalIncrement, verticalIncrement)
                .orElse(MoveDirection.Invalid);
    }

    private static Stream<MoveDirection> getMoveDirections(int horizontalShift, int verticalShift) {
        MoveDirection[] moveDirections = getMoveDirectionsArray(horizontalShift, verticalShift);
        return (moveDirections != null) ? Arrays.stream(moveDirections) : Stream.empty();
    }

    private static MoveDirection[] getMoveDirectionsArray(int horizontalShift, int verticalShift) {
        if (horizontalShift == 0)
            return VERTICAL_ONLY;
        if (verticalShift == 0)
            return HORIZONTAL_ONLY;
        if (Math.abs(horizontalShift) == Math.abs(verticalShift))
            return DIAGONAL_ONLY;
        return null;
    }

    private static Optional<MoveDirection> getMoveDirection(Stream<MoveDirection> moveDirections,
                                                            int horizontalIncrement,
                                                            int verticalIncrement) {
        return moveDirections
                .filter(direction -> direction.incrementX == horizontalIncrement
                        && direction.incrementY == verticalIncrement)
                .findFirst();
    }

    private static int getIncrement(int shift) {
        return (int) Math.signum(shift);
    }

}
