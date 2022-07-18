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
public class Knight extends Piece {
    public Knight(ChessColour colour) {
        super(colour, ChessPieces.KNIGHT);
    }
    
    
     public boolean isLegalMove(ChessBoard board,  Coordinate src, Coordinate dest){
        if (!super.isLegalMove(board, src, dest)) return false;
        // L-shaped moves && Can hop over.
        int deltaX = src.getColumn() - dest.getColumn();
        int deltaY = src.getRow() - dest.getRow();
        if ( Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2) return true;
        else if ( Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1) return true;
        else return false;
    }
    
}
