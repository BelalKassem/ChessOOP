/*
 ** Good reference on ENUMs: https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html

 */
package chess;

/**
 *
 * @author Cheryl
 */
public enum ChessPieces {
    PAWN ('P'),
    KNIGHT ('N'),
    BISHOP ('B'),
    ROOK ('R'),
    QUEEN ('Q'),
    KING ('K');
    
    private final char shortName;

    ChessPieces (char shortName) {
        this.shortName = shortName;
    }
    
    public char getShortName() {
        return this.shortName;
    }
}
