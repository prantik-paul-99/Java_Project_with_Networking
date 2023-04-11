
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
import treasurehunt.TreasureHunt;


public class SignUpFXMLController extends TreasureHunt implements Initializable {

 @FXML
  private AnchorPane rootpane_signup;
    @FXML
    private Button button1;
    @FXML
    private TextField signName;
    @FXML
    private PasswordField signPass;
    @FXML
    private Button button11;
    @FXML
    private ImageView exit;
    
    public static String sgnUpMsg=null;
    @FXML
    private Label status;
    
     
 

      @FXML
    private void signupdone(ActionEvent e) throws Exception{
        String signupInfo = "";
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        if(signName.getText().equals("")||signPass.getText().equals("")){
            status.setText("Please enter a vaild username and password.");
        }
        else{
           signupInfo = "S-"+signName.getText()+"-"+signPass.getText();
           Threads.ClientSendThread.toServer.println(signupInfo);
        }
        
        Thread.sleep(1000);
       
          System.out.println(sgnUpMsg);
          if (sgnUpMsg != null) {
              if (sgnUpMsg.equalsIgnoreCase("yes")) {
                  status.setText("Sign up successful. Please log in now.");
              } else if (sgnUpMsg.equalsIgnoreCase("no")) {
                  status.setText("Username already taken. Please try again.");
              }
              
              
          }
//          else{
//                 status.setText("Please, enter a vaild username and password ."); 
//              }
          //System.out.println("inside sign up controller");

          TreasureHunt.buttonsound(TreasureHunt.ismute);
//        AnchorPane pane2 =FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//        rootpane_signup.getChildren().setAll(pane2);
    }
      @FXML
    private void goback(ActionEvent e) throws Exception{
        TreasureHunt.buttonsound(TreasureHunt.ismute);
        AnchorPane pane1 =FXMLLoader.load(getClass().getResource("LogInFXML.fxml"));
       rootpane_signup.getChildren().setAll(pane1);
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
