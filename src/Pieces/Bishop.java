package Pieces;

public class Bishop extends Piece {
    @Override
    public boolean makeMove(int[][] positions) {
        return false;
    }

    @Override
    public void calculateAvailableMoves() {
        availableMoves.add(new Spot(0,0));
    }
}
