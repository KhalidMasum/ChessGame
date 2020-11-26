package BackEndLogic;

import java.util.ArrayList;

import static java.lang.Integer.max;

public class AI_Agent {
    Board board;

    public AI_Agent(Board currentGameBoard) {
        this.board = currentGameBoard;
    }
    public int minimax(Board board, int depth, boolean maxPlayer){ // first call to this method should be with !maxPlayer
        Spot spotPieceSource;
//        spotPieceDest is after availableMoves method is called
        Piece pieceSource;
        Piece pieceDest; // could be null
        int score = evaluate(board);
        if(depth==0)
            return score;
        // if someone wins
        if(score>10000 || score < -10000) // assuming winning score is greater than
            return score;

        // if someone hasn't won or max depth hasn't reached
        Piece[][] positions = board.positions;
        if(maxPlayer){
            int maxScore = -999999;
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    pieceSource = positions[i][j];
                    if(pieceSource!=null){
                        spotPieceSource = pieceSource.getSpot();
                        ArrayList<Spot> availableMoves = pieceSource.calculateAllPossibleMoves();
                        for(Spot spotPieceDest:availableMoves){
                            pieceDest = board.getPiece(spotPieceDest);
                            // make the move
                            // move pieceSource to spotPieceDest
                            movePieceToSpot(board, pieceSource, spotPieceDest);
                            // calculate score and take the maxScore
                            score = minimax(board, depth-1, !maxPlayer);
                            maxScore = max(maxScore, score);
                            // undo the move
                            // move pieceSource to spotPieceSource
                            movePieceToSpot(board, pieceSource, spotPieceSource);
                            // move pieceDest to spotPieceDest
                            movePieceToSpot(board, pieceDest, spotPieceDest);
                        }
                    }
                }
            }
            return maxScore;
        }
        else{
            int minScore = 999999;

            return minScore;
        }
    }
    public void movePieceToSpot(Board board, Piece piece, Spot spot){
        int row = spot.row;
        int col = spot.col;
        board.positions[row][col] = piece;
    }


    public int evaluate(Board board){ // this can evaluate any board not just current game board
        // need to calculate checkmate score
        // if white wins score should be greater than 10000 or must be changed in minimax method
        // if white loses score should be less than -10000 or must be changed in minimax method
        Piece[][] positions = board.positions;
        int score = 0;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(positions[i][j] != null)
                    score += positions[i][j].getValue();
        return score;
    }
}
