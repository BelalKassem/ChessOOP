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
public class Assign2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ChessBoard board = new ChessBoard();
        
        System.out.println("New Board: " + board.toFEN());
        
        // These two moves will now fail because we are adding in "legalMoves"
        //board.move ( new Coordinate(1,0), new Coordinate(3,0) );
        //board.move ( new Coordinate(6,3), new Coordinate(5,3) );
        // System.out.println(board.toString());
        // Replace with a few valid moves that are testable from the original setup
        
        boolean status;
        
        status = board.move ( new Coordinate("b2"), new Coordinate("b3")); // White Pawn - Move 1 forward
        if (!status) System.err.println("Illegal Move 1");
        status = board.move ( new Coordinate("g7"), new Coordinate("g6")); // Black Pawn =- Move 1 forward
        if (!status) System.err.println("Illegal Move 2");
        status = board.move ( new Coordinate("b1"), new Coordinate ("a3")); // White Knight - Hop over
        if (!status) System.err.println("Illegal Move 3");
        status = board.move ( new Coordinate("g8"), new Coordinate ("f6")); // Black Knight - Hop over        
        if (!status) System.err.println("Illegal Move 4");
        
        System.out.println("After 4 moves: " + board.toFEN());
      
        // Sample board = RN,,K,,R/,P,,,,,,/,,,,,,,,/,,,,,,,,/,Q,,,,,,/,,,,,,,,/pppppppp/rn,,k,,r/
        Coordinate coordinates[] = new Coordinate[18];
        Piece pieces[] = new Piece[18];
              
        coordinates[0] = new Coordinate("a1"); pieces[0] = new Rook(ChessColour.WHITE); // White
        coordinates[1] = new Coordinate("b1"); pieces[1] = new Knight(ChessColour.WHITE);
        coordinates[2] = new Coordinate("e1"); pieces[2] = new King(ChessColour.WHITE);
        coordinates[3] = new Coordinate("h1"); pieces[3] = new Rook(ChessColour.WHITE);
        coordinates[4] = new Coordinate("b2"); pieces[4] = new Pawn(ChessColour.WHITE);
        coordinates[5] = new Coordinate("b5"); pieces[5] = new Queen(ChessColour.WHITE);
        coordinates[6] = new Coordinate("a7"); pieces[6] = new Pawn(ChessColour.BLACK);
        coordinates[7] = new Coordinate("b7"); pieces[7] = new Pawn(ChessColour.BLACK);
        coordinates[8] = new Coordinate("c7"); pieces[8] = new Pawn(ChessColour.BLACK);
        coordinates[9] = new Coordinate("d7"); pieces[9] = new Pawn(ChessColour.BLACK);
        coordinates[10] = new Coordinate("e7"); pieces[10] = new Pawn(ChessColour.BLACK);
        coordinates[11] = new Coordinate("f7"); pieces[11] = new Pawn(ChessColour.BLACK);
        coordinates[12] = new Coordinate("g7"); pieces[12] = new Pawn(ChessColour.BLACK);
        coordinates[13] = new Coordinate("h7"); pieces[13] = new Pawn(ChessColour.BLACK);
        coordinates[14] = new Coordinate("a8"); pieces[14] = new Rook(ChessColour.BLACK);
        coordinates[15] = new Coordinate("b8"); pieces[15] = new Knight(ChessColour.BLACK);
        coordinates[16] = new Coordinate("e8"); pieces[16] = new King(ChessColour.BLACK);
        coordinates[17] = new Coordinate("h8"); pieces[17] = new Rook(ChessColour.BLACK); // Black
              
        board = new ChessBoard(coordinates, pieces);
        System.out.println("Test Board: " + board.toFEN());
        
        // TBD: Add more tests for KING, ROOK, BISHOP
    }
    
}
