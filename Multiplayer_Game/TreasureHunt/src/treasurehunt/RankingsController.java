/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehunt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import treasurehunt.TreasureHunt;

public class RankingsController implements Initializable {

    @FXML
    private AnchorPane rootpane_rankings;
    @FXML
    private Button goback;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goback(ActionEvent event) throws Exception {
       TreasureHunt.buttonsound(TreasureHunt.ismute);
    
       AnchorPane pane1 =FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
      rootpane_rankings.getChildren().setAll(pane1);
      TreasureHunt.state=1;
      TreasureHunt.backgroundsound( TreasureHunt.state , TreasureHunt.ismute );
    }
    
}
