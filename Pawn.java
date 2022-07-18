/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author Cheryl
 */
public class Pawn extends Piece {
    
    private boolean firstMove;
    
    public Pawn(ChessColour colour) {
        super(colour, ChessPieces.PAWN);
        this.firstMove = true;
    }
    public boolean isLegalMove(ChessBoard board,  Coordinate src, Coordinate dest){
        if (!super.isLegalMove(board, src, dest)) return false;  
                // Part 1: 1 square forward or 2 square forward, if it is unoccupied
                //         i.e. For Part 1: Ignore restriction of 2-square move to first-move
                // OR 1 diagonally if occupied.
                int deltaX = src.getColumn() - dest.getColumn();
                int deltaY = src.getRow() - dest.getRow();
                // Pawns can only move forward
                // Notice: colour has private access, even to subclass.
                if (this.getColour() == ChessColour.BLACK && deltaY <= 0) return false; 
                if (super.getColour() == ChessColour.WHITE && deltaY >= 0) return false;
                
                deltaX = Math.abs(deltaX);
                deltaY = Math.abs(deltaY);
                                
                // Advance
                // TBD: Improve code -- Conditions are readable & clear, but repeated code of setting firstMove
                if ( deltaX == 0 && deltaY == 1 & !board.getSquare(dest).isOccupied() )
                {   firstMove = false; return true; }
                if ( firstMove && deltaX == 0 && deltaY == 2 & !board.getSquare(dest).isOccupied() ) 
                {   firstMove = false; return true; }
                
                // Or, take a piece
                if ( deltaX == 1 && deltaY == 1 && board.getSquare(dest).isOccupied())
                {   firstMove = false; return true;   }
                
                return false;
                 
    }
    
    
}
