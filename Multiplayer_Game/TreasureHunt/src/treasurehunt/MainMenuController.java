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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import treasurehunt.TreasureHunt;


public class MainMenuController extends TreasureHunt implements Initializable {

@FXML
  private AnchorPane rootpane_mainmenu;
    @FXML
    private Button options;
    @FXML
    private Button newgame;
    @FXML
    private Button credits;
    @FXML
    private Button rankings;
    @FXML
    private Button credits1;
    @FXML
    private ImageView exit;

      @FXML
    private void goback(ActionEvent e) throws Exception{
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        Threads.ClientSendThread.toServer.println("L-"+Threads.ClientSendThread.ClntName+"Z"+Threads.ClientSendThread.ClntPass+"-out");
        AnchorPane pane1 =FXMLLoader.load(getClass().getResource("LogInFXML.fxml"));
       rootpane_mainmenu.getChildren().setAll(pane1);
    }
    
          @FXML
    private void newgame(ActionEvent e) throws Exception{
        
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane1 =FXMLLoader.load(getClass().getResource("Players.fxml"));
        rootpane_mainmenu.getChildren().setAll(pane1);        
       
    }
    
        @FXML
    private void options(ActionEvent e) throws Exception{
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane2 =FXMLLoader.load(getClass().getResource("options.fxml"));
       rootpane_mainmenu.getChildren().setAll(pane2);
       
    }
    
            @FXML
    private void rankings(ActionEvent e) throws Exception{
        TreasureHunt.buttonsound(TreasureHunt.ismute);
           AnchorPane pane3 =FXMLLoader.load(getClass().getResource("rankings.fxml"));
       rootpane_mainmenu.getChildren().setAll(pane3);
       
    }
    
          @FXML
    private void gotocredits(ActionEvent e) throws Exception{
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        TreasureHunt.state=2;
        TreasureHunt.backgroundsound( TreasureHunt.state , TreasureHunt.ismute );
        AnchorPane pane4 =FXMLLoader.load(getClass().getResource("credits.fxml"));
       rootpane_mainmenu.getChildren().setAll(pane4);
    }
    
    @FXML
     public void exit(ActionEvent e)throws Exception {
         TreasureHunt.exitsound();
        System.exit(1);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
