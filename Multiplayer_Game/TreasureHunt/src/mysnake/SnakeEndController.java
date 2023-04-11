/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysnake;

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
public class SnakeEndController implements Initializable {

    @FXML
    private Label win_name;
    @FXML
    private Label los_name;
    @FXML
    private Label opnt_score;
    @FXML
    private AnchorPane pane_snake_end;
    
    public static int score = 0;
    public static String winner, loser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        opnt_score.setText(Integer.toString(score));
        win_name.setText(winner);
        los_name.setText(loser);
        // TODO
    } 

    @FXML
    private void next_game(ActionEvent event) throws IOException {
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/wordtris_info/Wordtris_Info.fxml"));
        pane_snake_end.getChildren().setAll(pane);
    }

}
