package BackEndLogic;

import java.util.ArrayList;
import java.util.Stack;

public class History {
    private Stack<Board> records = new Stack<>();

//    public History(Board board){
//        addMove(board);
//    }


    public void addRecord(Board board){
        Piece[][] positions = new Piece[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if (board.positions[i][j] != null){
                    Piece piece = board.getPiece(i,j);
                    Spot spot = new Spot(piece.currentSpot.row, piece.currentSpot.col);
                    if(piece instanceof Pawn)
                        positions[i][j] = new Pawn(piece.getColor(), spot);
                    else if(piece instanceof Rook)
                        positions[i][j] = new Rook(piece.getColor(), spot);
                    else if(piece instanceof Bishop)
                        positions[i][j] = new Bishop(piece.getColor(), spot);
                    else if(piece instanceof King)
                        positions[i][j] = new King(piece.getColor(), spot);
                    else if(piece instanceof Queen)
                        positions[i][j] = new Queen(piece.getColor(), spot);
                    else if(piece instanceof Knight)
                        positions[i][j] = new Knight(piece.getColor(), spot);
                    positions[i][j].setBoard(board);
                }
            }
        }
        System.out.println("Added a Move");
        printBoard(new Board(positions));
        records.push(new Board(positions));
    }

    void printBoard(Board board){
        Piece positions[][] = board.getPositions();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(positions[i][j] != null)
                    System.out.print(1);
                else
                    System.out.print(0);
            }
            System.out.print('\n');
        }
    }

    public boolean isEmpty(){
        return records.isEmpty();
    }

    public void printEntireHistory(){
        System.out.println("Printing Entire History:");
        for(int i=0; i<records.size(); i++){
            System.out.println("Move=" + i);
            printBoard(records.get(i));
        }
    }

    public void removeLastRecord(){
        if(records.isEmpty())
            return;
        records.pop();
        System.out.println("Removed last Move, Size is now: " + records.size());
    }

    public Board getLastRecord(){
        if(isEmpty())
            return null;
        Board res = records.peek();
        printEntireHistory();
        return res;
    }

    void printSize(){
        System.out.println("Total Moves Made=" + records.size());
    }
}
