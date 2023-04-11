/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package end;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoserController implements Initializable {

    @FXML
    private AnchorPane rootpane_loser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Threads.ClientSendThread.toServer.println("X");
        AnchorPane pane =FXMLLoader.load(getClass().getResource("/treasurehunt/MainMenu.fxml"));
                    rootpane_loser.getChildren().setAll(pane);
    }
    
}
