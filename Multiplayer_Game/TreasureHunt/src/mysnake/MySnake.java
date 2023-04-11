package mysnake;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import treasurehunt.*;


public class MySnake implements Initializable {

    @FXML
    public GridPane gameGrid;
    Label lb = new Label("hi");
    @FXML
    private ImageView DiceImage;
    public static int opnt_val=0,opnt_pos=-1;
    public static boolean turn_val_your = false ;
    public static String img;
    public int val;
    public ImageView iv,iv1;
    @FXML
    public Label turn;
    @FXML
    
   
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img1 = new Image("/Res/images/p1.jpg");
        Image img2 = new Image("/Res/images/p2.jpg");
        if (turn_val_your) {
            turn.setText("YOUR");
            Player p1 = new Player("p1", true, 9, 5, img1);
            iv = new ImageView(p1.getImg());
            iv.setFitHeight(50);
            iv.setFitWidth(50);
            gameGrid.add(iv, p1.getPos_col(), p1.getPos_row());
        }
        
//        else {
//            turn.setText("OPPONENT's");
//            Player p2 = new Player("p2", true, 9, 3, img2);
//            iv1 = new ImageView(p2.getImg());
//            iv1.setFitHeight(50);
//            iv1.setFitWidth(50);
//            gameGrid.add(iv1, p2.getPos_col(), p2.getPos_row());
//        }
    }
}
