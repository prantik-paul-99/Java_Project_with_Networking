/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtris;

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
public class Score_wordtrisController implements Initializable {

    @FXML
    private Label playername;
    @FXML
    private Label passed;
    @FXML
    private Label correct;
    @FXML
    private Label wrong;
    @FXML
    private Label score;
    @FXML
    private AnchorPane pane_scoreword;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        passed.setText(Integer.toString(wordtris.wordtrisController.totalwordspassed_count));
        correct.setText(Integer.toString(wordtris.wordtrisController.correctwords_count));
        playername.setText(Threads.ClientSendThread.ClntName);
        score.setText(Integer.toString(wordtris.wordtrisController.score_count));
        wrong.setText(Integer.toString(wordtris.wordtrisController.wrongwords_count));
        // TODO
    }    

    @FXML
    private void explore(ActionEvent event) throws IOException {
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        TreasureHunt.state = 2;
        TreasureHunt.backgroundsound(TreasureHunt.state, TreasureHunt.ismute);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/bird_info/Bird_Info.fxml"));
        pane_scoreword.getChildren().setAll(pane);
    }

}
