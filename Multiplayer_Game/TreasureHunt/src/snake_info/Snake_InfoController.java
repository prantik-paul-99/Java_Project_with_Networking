
package snake_info;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import treasurehunt.TreasureHunt;


public class Snake_InfoController implements Initializable {

    @FXML
    private AnchorPane snake_info_pane;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void proceed(ActionEvent event) throws IOException {
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane =FXMLLoader.load(getClass().getResource("/mysnake/Snake.fxml"));
        snake_info_pane.getChildren().setAll(pane);
    }
    
}
