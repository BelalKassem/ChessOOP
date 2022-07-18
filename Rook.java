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
public class Rook extends Piece {
    public Rook(ChessColour colour) {
        super(colour, ChessPieces.ROOK);
    }
    
    public boolean isLegalMove(ChessBoard board,  Coordinate src, Coordinate dest){
        if (!super.isLegalMove(board, src, dest)) return false;
        // TBD: Can also move along the X axis!!
        // Only along the COLUMN(Y) axis
        if (src.getColumn() != dest.getColumn() ) return false;
        // AND only if intermediate squares are unoccupied
        int x = src.getColumn();
        int srcY = src.getRow();
        int destY = src.getRow();
        if (srcY > destY) { // Moving from Black to White. Flip it for uniform iteration
           int temp = srcY;
            srcY = destY;
            destY = temp;   
        }
        srcY++; destY--;
        for (int i=srcY; i<=destY;i++) {
            if (board.getSquare(new Coordinate(x,i)).isOccupied()) return false;      
        }
        return true;
    }
}
