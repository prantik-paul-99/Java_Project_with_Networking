
package end;

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


public class End_scoreController implements Initializable {

    @FXML
    private Label myname;
    @FXML
    private Label opponame;
    @FXML
    private Label player_score;
    @FXML
    private Label oppo_score;
    @FXML
    private AnchorPane result_pane;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myname.setText(Threads.ClientSendThread.ClntName);
        opponame.setText(Threads.ClientSendThread.oppo_name);
        player_score.setText(Integer.toString(treasurehunt.TreasureHunt.my_score));
        oppo_score.setText(Integer.toString(Threads.ClientReceiveThread.opnt_score));
        
        // TODO
    }    

    @FXML
    private void collect(ActionEvent event) throws IOException {
        
        if (treasurehunt.TreasureHunt.my_score >= Threads.ClientReceiveThread.opnt_score)
        {
            TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/end/winner.fxml"));
        result_pane.getChildren().setAll(pane);
        }

        else if (treasurehunt.TreasureHunt.my_score < Threads.ClientReceiveThread.opnt_score)
        {
            TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/end/Loser.fxml"));
        result_pane.getChildren().setAll(pane);
        }
        else System.out.println("nothing happened");
    }
    
}
