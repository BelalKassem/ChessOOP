
package chess;

import java.util.List;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author schramm
 */
public class Assign4 extends Application implements ListChangeListener{
    
    VBox left, right;
    Button whiteTakenSquare[];
    Button blackTakenSquare[];
    
    @Override
    public void start(Stage primaryStage) {
        
        ChessBoard board = new ChessBoard();
        SquareEventHandler handler = new SquareEventHandler( board );
             
        boolean colour = false;
        Button buttons[][] = new Button[8][8];  
        GridPane centre = new GridPane();
        
        //for (int c=7; c>=0; c--) {
        //    for (int r=7; r>=0; r--) {
        for (int c=0; c<8; c++) {
            for (int r=0; r<8; r++) {
                Square square = board.getSquare( new Coordinate(c,r));
                Piece piece = square.getPiece();
                if (piece == null) {
                    buttons[c][r] = new Button( new Coordinate(c,r).name());
                } else {
                    Image icon;
                    icon = new Image("chess/images/"+piece.getImageName());
                    //buttons[c][r] = new Button( new Coordinate(c,r).toString(), icon);
                    buttons[c][r] = new Button( new Coordinate(c,r).name());
                    buttons[c][r].setGraphic( new ImageView( icon ));
                }
                if (colour) {
                    buttons[c][r].setStyle("-fx-background-color: grey");
                    colour = false;
                } else {
                    buttons[c][r].setStyle("-fx-background-color: white");
                    colour = true;
                }
                buttons[c][r].autosize();  // No effect
                buttons[c][r].setMinSize(90, 90);  //  IMPORTANT
                buttons[c][r].setOnAction( handler );
                /*
                buttons[c][r].setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Node node = (Node) event.getSource();
                Button button = (Button)node;
                String coordinate = button.getText();
                System.out.println("Index is " + coordinate + " coordinates ");
                Square square = board.getSquare( new Coordinate (coordinate) );
            }
        });
                */
                
                centre.add( buttons[c][r], c, 7-r); 
            }
            colour = colour? false:true;
        }      
        
        BorderPane root = new BorderPane();
        left = new VBox();
        right = new VBox();
        whiteTakenSquare = new Button[16];
        blackTakenSquare = new Button[16];
        
        // Example: https://www.programcreek.com/java-api-examples/index.php?class=javafx.scene.layout.BorderPane&method=setStyle
        
        for (int i=0; i<16; i++) {
            whiteTakenSquare[i] = new Button();
            whiteTakenSquare[i].setStyle("-fx-background-color: grey; -fx-border-color: white;");
            whiteTakenSquare[i].setMinSize(90, 90);  //  IMPORTANT
            left.getChildren().add( whiteTakenSquare[i] );
            blackTakenSquare[i] = new Button();
            blackTakenSquare[i].setStyle("-fx-background-color: grey; -fx-border-color: white;");
            blackTakenSquare[i].setMinSize(90, 90);  //  IMPORTANT
            right.getChildren().add( blackTakenSquare[i] );  
        }
        board.addTakenObserver(this);
        
        root.setCenter( centre );
        root.setLeft( left );
        root.setRight( right );
        
        Scene scene = new Scene(root, 900, 720);
        
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    @Override
    public void onChanged(Change c) {
        while (c.next()) {
            if (c.wasAdded()) {   // In our application, should be nothing else
             int index = c.getFrom();
             List<Piece> pieces = c.getAddedSubList();
             for (Piece p: pieces) {
                Image icon;
                icon = new Image("chess/images/"+p.getImageName());
                if (p.getColour() == ChessColour.BLACK) {
                   blackTakenSquare[index].setGraphic( new ImageView( icon ));
                } else {
                   whiteTakenSquare[index].setGraphic( new ImageView( icon ));
                }
              }
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        launch(args);
    }
    
}

class SquareEventHandler implements EventHandler<ActionEvent> {
    
    private ChessBoard board;
    private boolean firstClick;
    private Square firstSquare, secondSquare;
    private Button firstButton, secondButton;
    
    public SquareEventHandler(ChessBoard board)
    {
        this.board = board;
        firstClick = true;
        firstSquare = secondSquare = null;
    }
              
    @Override
    public void handle(ActionEvent event) {
        Node node = (Node) event.getSource();
        Button button = (Button)node;
        String coordinate = button.getText();
        System.out.println("Index is " + coordinate + " coordinates ");
        Square square = board.getSquare( new Coordinate (coordinate) );
        if (firstClick) {
            firstSquare = square; firstButton = button;
            firstClick = false;
        } else {
            secondSquare = square; secondButton = button;
            System.out.println("Moving from " + firstSquare + " to " + secondSquare);
            boolean success = board.move( firstSquare.getCoordinate(), secondSquare.getCoordinate());
            System.out.println("move is successful " + success);
            if (success) {
                secondButton.setGraphic( firstButton.getGraphic());
                firstButton.setGraphic( null );
                
            }
            firstClick = true;
        }
    }

}
