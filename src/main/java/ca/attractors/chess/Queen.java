package ca.attractors.chess;

import java.util.Arrays;
import java.util.stream.Stream;

public class Queen extends ChessPiece {

    private static final MoveDirection[] MOVE_DIRECTIONS = Stream.concat(
                    Stream.concat(
                            Arrays.stream(MoveDirection.HORIZONTAL_ONLY),
                            Arrays.stream(MoveDirection.VERTICAL_ONLY)),
                    Arrays.stream(MoveDirection.DIAGONAL_ONLY))
            .toArray(MoveDirection[]::new);

    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    protected MoveDirection[] getValidMoveDirections() {
        return MOVE_DIRECTIONS;
    }

    @Override
    protected MoveLimit getMoveLimit() {
        return MoveLimit.NoLimit;
    }

}
