/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bird_shooting;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import treasurehunt.TreasureHunt;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Bird_scoreController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label killed;
    @FXML
    private Label used;
    @FXML
    private Label birdscore;
    @FXML
    private AnchorPane pane_birdscore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(Threads.ClientSendThread.ClntName);
        killed.setText(Integer.toString(bird_Controller.birds_killed));
        used.setText(Integer.toString(25-bird_Controller.bullet_remaining));
        birdscore.setText(Integer.toString(bird_Controller.score));
        // TODO
    }    

    @FXML
    private void seeresult(ActionEvent event) throws InterruptedException, IOException {
        Threads.ClientSendThread.toServer.println("E");
        Thread.sleep(100);
        Threads.ClientSendThread.toServer.println("K");
        Thread.sleep(100);
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane =FXMLLoader.load(getClass().getResource("/end/end_score.fxml"));
        pane_birdscore.getChildren().setAll(pane);
        
    }
    
}
