import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.FileReader;
import java.util.Arrays;

public class Menu extends Application {
    public Position pos;
    public Dimensions dim;

    int board_size = 12;
    Node[][] gridPaneNodes = new Node[board_size][board_size];
    GridPane gp;
    public void maximumValue(Arrays array){

    }
    int maxX=pos.getX();
    int maxY=pos.getY();

    @Override
    public void start(Stage stage) throws Exception {
        gp = new GridPane();

//        initialize playfield
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {

                Rectangle tile = new Rectangle(64, 48);
                tile.setFill(Color.TRANSPARENT);
                tile.setStroke(Color.BLACK);
                gp.add(tile, i, j);
            }
        }
    }
}