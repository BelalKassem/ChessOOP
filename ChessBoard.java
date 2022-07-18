/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 *
 * @author Cheryl
 */
public class ChessBoard {
    
    private Square board[][];
    private ChessColour activeColour;
    private int fullMove;
    private ArrayList<Piece> whiteTakenPieces, blackTakenPieces;
    private ObservableList<Piece> observableWhiteList, observableBlackList;
        
    public ChessBoard() {
        board = new Square[8][8];
        for (int c=0; c<8; c++)
            for (int r=0; r<8; r++)
                board[c][r] = new Square( new Coordinate(c,r));
        reset();
        activeColour = ChessColour.WHITE;
        fullMove = 1;
        
        whiteTakenPieces = new ArrayList<Piece>();
        blackTakenPieces = new ArrayList<Piece>();
        observableWhiteList = FXCollections.observableList(whiteTakenPieces);
        observableBlackList = FXCollections.observableList(blackTakenPieces);
        
    }
    
      /*
     *
    */
    public ChessBoard( Coordinate positions[], Piece pieces[])throws IllegalArgumentException
    {   
        if (positions.length != pieces.length) 
            throw new IllegalArgumentException ("The list of positions must correspond to the list of pieces");
        
        board = new Square[8][8];
        for (int r=0; r<8; r++)
            for (int c=0; c<8; c++)
                board[r][c] = new Square( new Coordinate(r,c));
        for (int i=0; i<positions.length;i++) {
            board[positions[i].getColumnNumber()][positions[i].getRowNumber()].addPiece(pieces[i]);
        }
        activeColour = ChessColour.WHITE;
        fullMove = 1;
    }
    private void reset()
    {
        // White rows
        board[0][0].addPiece (new Rook(ChessColour.WHITE));
        board[7][0].addPiece (new Rook(ChessColour.WHITE));  
        board[1][0].addPiece (new Knight(ChessColour.WHITE));
        board[6][0].addPiece (new Knight(ChessColour.WHITE));   
        board[2][0].addPiece (new Bishop(ChessColour.WHITE));
        board[5][0].addPiece (new Bishop(ChessColour.WHITE));
        board[3][0].addPiece (new Queen(ChessColour.WHITE));
        board[4][0].addPiece (new King(ChessColour.WHITE));    
        
        for (int c=0; c<8; c++)
            board[c][1].addPiece( new Pawn(ChessColour.WHITE));
       
        // Black rows
        board[0][7].addPiece (new Rook(ChessColour.BLACK));
        board[7][7].addPiece (new Rook(ChessColour.BLACK));  
        board[1][7].addPiece (new Knight(ChessColour.BLACK));
        board[6][7].addPiece (new Knight(ChessColour.BLACK));   
        board[2][7].addPiece (new Bishop(ChessColour.BLACK));
        board[5][7].addPiece (new Bishop(ChessColour.BLACK));
        board[3][7].addPiece (new Queen(ChessColour.BLACK));
        board[4][7].addPiece (new King(ChessColour.BLACK)); 
        
        for (int c=0; c<8; c++)
            board[c][6].addPiece( new Pawn(ChessColour.BLACK));
        
        // Middle rows : Make sure they are UNOCCUPIED by deleting
        Piece p;  
        for (int c=0; c<8; c++)
            for (int r=2; r<6; r++)
                p = board[c][r].deletePiece();
    
        
    }
    // Assign 1 : getSquare() is private
    // Assign 2 : getSquare() needs to be protected.
    protected Square getSquare (Coordinate c)
    {
        return board[c.getColumnNumber()][c.getRowNumber()];
    }
    public boolean move(Coordinate src, Coordinate dest) {
     
        Square srcSquare = this.getSquare(src);
        if (!srcSquare.isOccupied()) return false;
        
        Piece p = srcSquare.getPiece();
        if (!p.getColour().equals(activeColour)) return false;
        
        if (p.isLegalMove(this, src, dest))
        {
           Square destSquare = this.getSquare(dest);
           Piece takenPiece = destSquare.addPiece(p);
           if (takenPiece!= null) {
               if (takenPiece.getColour() == ChessColour.BLACK) 
                   observableBlackList.add(takenPiece);
               else 
                   observableWhiteList.add(takenPiece);
           }
           srcSquare.deletePiece();
           activeColour = (activeColour == ChessColour.BLACK) ? ChessColour.WHITE : ChessColour.BLACK;
           if (activeColour == ChessColour.WHITE) fullMove++;
            // fullMove is incremented only *AFTER* BLACK has moved.
           return true;    
        }
        else return false;
    }

    public String toString() {
        
       String s = "Board\n";
       for (int r=7; r>=0; r--)
       {
           for (int c=0; c<8; c++)
           {
               Piece p = board[c][r].getPiece();
               s += (p == null) ? " " : p.getShortName();
               s += "_";
           }
           s += "\n";
       }
       return s;
    }
    
       public String toFEN() {
        
       String s = "";
       for (int r=7; r>=0; r--)
       {
           for (int c=0; c<8; c++)
           {
               Piece p = board[c][r].getPiece();
               s += (p == null) ? " " : p.getShortName();
           }
           s += "/";
       }
       s += " " + ((activeColour == ChessColour.WHITE) ? "w" : "b");
       s += " " + fullMove;
       s += "\n";
       
       return s;
    }
    /*
       public String toTakenString() {
           Iterator<Piece> iterator = takenPieces.iterator();
           String black = "";
           String white = "";
           while (iterator.hasNext()) {
               Piece piece = (Piece) iterator.next();
               if (piece.getColour() == ChessColour.WHITE)
                   white += piece.getShortName();
               else
                   black += piece.getShortName();
           }
           return white + "," + black;
       }  
*/
        public String toTakenString() {
           String black = "";
           String white = "";
           for (Piece piece : whiteTakenPieces) {
               white += piece.getShortName();
           }
           for (Piece piece : blackTakenPieces) {
               black += piece.getShortName();
           }
           return white + "," + black;
       }      
       
       public void addTakenObserver(ListChangeListener listener) {
         observableWhiteList.addListener(listener);
         observableBlackList.addListener(listener);
       }
    
}

   