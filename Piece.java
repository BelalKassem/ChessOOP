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
public class Piece {
    
    private ChessColour colour;
    private ChessPieces name;
    private char shortName;
    private String imageName;
    
           
   // public Piece() {}
    
    protected Piece(ChessColour colour, ChessPieces name)
    {
        this.colour = colour;
        this.name = name;
        this.shortName = name.getShortName();
        if (colour == ChessColour.BLACK) this.shortName = Character.toLowerCase(this.shortName);    
        
        imageName = "" + colour + "_" + name + ".png";
        imageName = imageName.toLowerCase();
        // System.out.println(imageName);
    }
    /*
    public Piece(char shortName)
    {
        this.shortName = shortName;  
        char temp = Character.toUpperCase(shortName);
        switch (temp) {            
            case 'P' : this.name = ChessPieces.PAWN; break; 
            case 'N' : this.name = ChessPieces.KNIGHT; break; 
            case 'B' : this.name = ChessPieces.BISHOP; break;
            case 'R' : this.name = ChessPieces.ROOK; break;
            case 'Q' : this.name = ChessPieces.QUEEN; break;
            case 'K' : this.name = ChessPieces.KING; break;
            default: throw new IllegalArgumentException ("shortname " + shortName + " is not valid");
        }
        this.colour = (Character.isLowerCase(shortName)) ? ChessColour.BLACK : ChessColour.WHITE;

    }
    */
    public ChessColour getColour() { return this.colour; }
    public ChessPieces getName() { return this.name; }
    public char getShortName() { return this.shortName; }
    public String getImageName() { return this.imageName; }
    
    public String toString() { return colour + " " + name; }
    
    public boolean isLegalMove(ChessBoard board,  Coordinate src, Coordinate dest){
   
        // First rule: Can only move to a dest if it is unoccupied or occupied by the other colour
             
        Piece movingPiece = board.getSquare(src).getPiece();
        // System.out.println("isLegalMove? " + movingPiece + " ");
        
        Piece takenPiece = board.getSquare(dest).getPiece();
        if ((takenPiece!=null) && (takenPiece.getColour().equals(this.colour))) return false;
        return true;     
       
        
    }
    
}
