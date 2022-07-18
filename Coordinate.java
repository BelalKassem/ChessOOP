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
public class Coordinate {
    private int row;   // 0 is the bottom horizontal row
    private int column;   // 0 is the left vertical column
    
    public Coordinate(int column, int row) throws IndexOutOfBoundsException
    {
        if ( (column<0)|| (column>7)) throw new IndexOutOfBoundsException("column must be between 0 and 7,inclusive");
        if ( (row<0)|| (row>7)) throw new IndexOutOfBoundsException("row must be between 0 and 7,inclusive");
        this.row = row;
        this.column = column;    
    }
    
    public Coordinate(char column, char row) throws IndexOutOfBoundsException
    {
        if ( (column<'a')|| (column>'h')) throw new IndexOutOfBoundsException("column must be between a and h,inclusive");
        if ( (row<'1')|| (row>'8')) throw new IndexOutOfBoundsException("row must be between 1 and 8,inclusive");
        this.column = column - 'a';
        this.row = row - '1';    
    }   
    
    public Coordinate(String coordinate) throws IndexOutOfBoundsException
    {
        if (coordinate.length() != 2) throw new IllegalArgumentException ("Coordinate is a 2-character string");
        char column = coordinate.charAt(0);
        char row = coordinate.charAt(1);
        // this(x,y) except it must be the first statement!
        if ( (column<'a')|| (column>'h')) throw new IndexOutOfBoundsException("x must be between a and h, inclusive");
        if ( (row<'1')|| (row>'8')) throw new IndexOutOfBoundsException("y must be between 1 and 8, inclusive");
        this.column = column - 'a';
        this.row = row - '1'; 
    }   
           
    public int getColumnNumber() { return this.column; }
    public int getRowNumber() { return this.row; }
    
    public char getColumn() { return (char)(column + 'a'); }
    public char getRow() { return (char)(row+'0'+1); }
    
    @Override
    public String toString() { return "("+getColumnNumber()+","+getRowNumber()+")"; }
    public String name() { return "" + getColumn()+ getRow() ; }

}
