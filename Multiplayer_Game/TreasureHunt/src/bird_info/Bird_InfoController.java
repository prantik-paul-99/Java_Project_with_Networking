/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bird_info;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import treasurehunt.TreasureHunt;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Bird_InfoController implements Initializable {

    @FXML
    private AnchorPane bird_info_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void proceed(ActionEvent event) throws IOException {
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/bird_shooting/bird_shooting.fxml"));
        bird_info_pane.getChildren().setAll(pane);
    }
    
}
