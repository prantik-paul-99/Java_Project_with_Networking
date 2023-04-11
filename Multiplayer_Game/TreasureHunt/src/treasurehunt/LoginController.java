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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import static treasurehunt.SignUpFXMLController.sgnUpMsg;
import treasurehunt.TreasureHunt;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController extends TreasureHunt implements Initializable {


    @FXML
     AnchorPane rootpane_login;
    @FXML
    private Button LogIn;
    @FXML
    private Button SignUp;
    @FXML
    private ImageView exit;
    @FXML
    private Label warnings;
    @FXML
    private TextField user_name;
    @FXML
    private PasswordField pass_word;
    
    public static String Login_msg = null ;
    
    @FXML
    private void gotosignup(ActionEvent e) throws Exception{
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane =FXMLLoader.load(getClass().getResource("SignUpFXML.fxml"));
       rootpane_login.getChildren().setAll(pane);
    }
    
        @FXML
    private void logindone(ActionEvent e) throws Exception{
        
        String loginInfo = "";
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        if(user_name.getText().equals("")||pass_word.getText().equals("")){
         loginInfo  = "L-null" ; 
        }
        else{
           loginInfo = "L-"+user_name.getText()+"-"+pass_word.getText();
        }
        Threads.ClientSendThread.ClntName=user_name.getText();
        Threads.ClientSendThread.ClntPass = pass_word.getText();
        Threads.ClientSendThread.toServer.println(loginInfo);
        Thread.sleep(200);

          if (Login_msg != null) {
              if (Login_msg.equalsIgnoreCase("yes")) {
                  AnchorPane pane =FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                    rootpane_login.getChildren().setAll(pane);
                    
              }
              else if(Login_msg.equalsIgnoreCase("in")){
                warnings.setText("This account is already logged in .");  
              }
              else if((Login_msg.equalsIgnoreCase("no"))) {
                  warnings.setText("Login unsuccessful. Check your username and password.");
              }
              else if(Login_msg.equalsIgnoreCase("invalid")){
                  warnings.setText("Please, enter a vaild username and password .");
              }
          }
          
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/bird_info/Bird_Info.fxml"));
//        rootpane_login.getChildren().setAll(pane);
        
        
        
        
        
        
        
        
    }
    
    @FXML
    public void exit(ActionEvent e)throws Exception {
        System.exit(1);
    }

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
