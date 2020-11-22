package BackEndLogic;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, Spot spot){
        this.isWhite = isWhite;
        this.spot = spot;
    }

    @Override
    public void makeMove(Board board) {
    }

    @Override
    public ArrayList<Spot> calculateAllPossibleMoves(Board board) {
        Piece[][] positions = board.positions;
        availableMoves.clear();
        int row = spot.row;
        int col = spot.col;
        if(isWhite) {
            if (row -1 >= 0 && !board.isOccupied(new Spot(row-1, col)))
                availableMoves.add(new Spot(row - 1, col));
            if (row - 1 >= 0 && col - 1 >= 0 && positions[row - 1][col - 1] != null)
                availableMoves.add(new Spot(row - 1, col - 1));
            if (row -1 >= 0 && col + 1 <=7 && positions[row-1][col+1] != null)
                availableMoves.add(new Spot(row - 1, col + 1));
        }
        return  availableMoves;
    }



}
