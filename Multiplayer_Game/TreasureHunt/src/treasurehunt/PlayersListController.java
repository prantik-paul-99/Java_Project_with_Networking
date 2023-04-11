/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehunt;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PlayersListController implements Initializable {

    public static String[] names ;
    public static boolean game_started = false;
    
             
    @FXML
    private AnchorPane mode_pane =new AnchorPane();
    @FXML
    private ListView<String> player_list;
    public static boolean request_sent = false ;
    public static String oppo_name = null ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void showlist(ActionEvent event) throws InterruptedException {
        Threads.ClientSendThread.toServer.println("showlist");
        Thread.sleep(100);
        player_list.getItems().clear();
        for (int i = 1; i < names.length; i++) {
            if(!names[i].equals(Threads.ClientSendThread.ClntName))
            player_list.getItems().add(names[i]);
            //AnchorPane pane = FXMLLoader.load(getClass().getResource("/bird_shooting/bird_shooting.fxml"));
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/mysnake/Snake.fxml"));
//        //AnchorPane pane = FXMLLoader.load(getClass().getResource("/wordtris/wordtris.fxml"));
//
//        mode_pane.getChildren().setAll(pane);
        }
    }

    @FXML
    private void make_pair(MouseEvent event) throws IOException {
        String name = player_list.getSelectionModel().getSelectedItem();
        Alert sure = new Alert(Alert.AlertType.CONFIRMATION);
        sure.setTitle("Pairing");
        sure.setContentText("Are you sure you wanna play with "+name+"?");
        Optional<ButtonType> option = sure.showAndWait();
        
        if (option.get() == ButtonType.OK) {
            Threads.ClientSendThread.oppo_name = name;
                Threads.ClientSendThread.toServer.println("R-request-"+name);
           } else {
                System.out.println("Didn't pair");
            }
    }
    @FXML
    public void load_snake() throws IOException{
        if (game_started) {
            TreasureHunt.buttonsound(TreasureHunt.ismute);
            TreasureHunt.state=2;
            TreasureHunt.backgroundsound( TreasureHunt.state , TreasureHunt.ismute );
            
                AnchorPane pane;
            
                pane = FXMLLoader.load(getClass().getResource("/snake_info/Snake_Info.fxml"));
                 // pane = FXMLLoader.load(getClass().getResource("/bird_shooting/bird_shooting.fxml"));
                mode_pane.getChildren().setAll(pane);
             
        }

    }

}
