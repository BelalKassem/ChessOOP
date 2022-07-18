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
public class King extends Piece {
    public King(ChessColour colour) {
        super(colour, ChessPieces.KING);
    }
    
    public boolean isLegalMove(ChessBoard board,  Coordinate src, Coordinate dest){
        if (!super.isLegalMove(board, src, dest)) return false;
        // Only 1 square move
        int deltaX = src.getColumn() - dest.getColumn();
        int deltaY = src.getRow() - dest.getRow();
        if ( Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1) return false;
            else return true;
    }
}
