package BackEndLogic;
import java.util.ArrayList;

public class Board {

    public Board(){
        // Do stuff
    }

    public Piece[][] positions = new Piece[8][8];
    ArrayList<Spot> emptySpots = new ArrayList<>();
    ArrayList<Spot> availableMoves = new ArrayList<>();

    MoveTransitionRecord moveTransitionRecord = new MoveTransitionRecord();
//    boardGUI UI;

    Piece toMove;
    boolean makingMove = false;
    int prevX, prevY;


    boolean checkGameState(Piece[][] positions){
        return false;
    }

    boolean updateGameState(Piece[][] positions) {
        return false;
    }

//    Board(boardGUI UI){
//        this.UI = UI;
//        UI.setInternalBoard(this);
//        start();
//    }

    void displayAvailableMoves(){
        availableMoves = moveTransitionRecord.getToMovePiece().calculateAllPossibleMoves(this);

        // Display Code...
    }

    boolean ifMoveIsValid(Spot inputSpot){
        return availableMoves.contains(inputSpot);
    }

    void makeMove(Piece inputPiece){
        if(isOccupied(inputPiece.getSpot()) && !moveTransitionRecord.checkIfSelected())
            return;

        if(moveTransitionRecord.checkIfSelected()){
            if(inputPiece.equals(moveTransitionRecord.getToMovePiece()))
                return;
            if(ifMoveIsValid(inputPiece.getSpot())){
                Spot thisSpot = inputPiece.getSpot();
                positions[thisSpot.row][thisSpot.col] = inputPiece;
                moveTransitionRecord.getToMovePiece().setSpot(inputPiece.getSpot());
                moveTransitionRecord.reset();
            }
        }
        else{
            moveTransitionRecord.setToMovePiece(inputPiece);
            displayAvailableMoves();
        }
    }

    boolean isOccupied(Spot inputSpot){
        int col = inputSpot.col;
        int row = inputSpot.row;
        Piece positionToCheck = positions[row][col];
        if(positionToCheck == null)
            return false;
        else
            return true;
    }

    boolean getOccupiedColor(Spot inputSpot){
        int col = inputSpot.col;
        int row = inputSpot.row;
        Piece toCheck = positions[row][col];
        return toCheck.isWhite();
    }


    void start() {
        positions[1][0] = new Pawn(true, new Spot(6,1));
    }

    public void getEmptySpots(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(this.positions[i][j]==null)
                    emptySpots.add(new Spot(i,j));
            }
        }
    }
    public void addPiece(Piece piece){
        positions[piece.spot.row][piece.spot.col] = piece;
    }
}