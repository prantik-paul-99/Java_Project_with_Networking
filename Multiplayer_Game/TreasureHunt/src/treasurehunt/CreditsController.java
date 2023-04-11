
package treasurehunt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import treasurehunt.TreasureHunt;



public class CreditsController extends TreasureHunt implements Initializable {

@FXML
private AnchorPane rootpane_credits;
    @FXML
    private Button credits1;



public void goback(ActionEvent e) throws IOException
{
    TreasureHunt.buttonsound(TreasureHunt.ismute);
    
      AnchorPane pane1 =FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
      rootpane_credits.getChildren().setAll(pane1);
      TreasureHunt.state=1;
      TreasureHunt.backgroundsound( TreasureHunt.state , TreasureHunt.ismute );
}
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoback(ActionEvent event) {
    }
    
}
