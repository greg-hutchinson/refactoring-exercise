package ca.attractors.chess;

public class BoardSettings {

    public static BoardSettings getDefaultSettings() {
        return new BoardSettings(PieceColor.White);
    }

    private PieceColor currentMoveColor;

    public BoardSettings(PieceColor currentMoveColor) {
        this.currentMoveColor = currentMoveColor;
    }

    public PieceColor getCurrentMoveColor() {
        return currentMoveColor;
    }
}
