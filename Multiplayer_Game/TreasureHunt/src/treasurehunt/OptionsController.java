
package treasurehunt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import treasurehunt.TreasureHunt;


public class OptionsController implements Initializable {
   
    @FXML
   private AnchorPane  rootpane_options;
  
    @FXML
public void gotoback(ActionEvent e) throws IOException
{
    TreasureHunt.buttonsound(TreasureHunt.ismute);
    
    AnchorPane pane1 =FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
      rootpane_options.getChildren().setAll(pane1);
//      TreasureHunt.state=1;
//      TreasureHunt.backgroundsound(TreasureHunt.state);
}

@FXML
public void soundoff(ActionEvent e) throws IOException
{
    TreasureHunt.buttonsound(TreasureHunt.ismute);
    TreasureHunt.ismute=true;
    TreasureHunt.backgroundsound( TreasureHunt.state , TreasureHunt.ismute );
}

@FXML
public void soundon(ActionEvent e) throws IOException
{
    TreasureHunt.buttonsound(TreasureHunt.ismute);
    TreasureHunt.ismute=false;
    TreasureHunt.backgroundsound( TreasureHunt.state , TreasureHunt.ismute );
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
