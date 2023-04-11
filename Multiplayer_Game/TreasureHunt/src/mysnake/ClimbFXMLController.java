/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysnake;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ClimbFXMLController implements Initializable {

    @FXML
    private Button yesbtn;
    @FXML
    private Button nobtn;
    static boolean dec;
    
    public void yesbtn (ActionEvent e) throws IOException
{
    dec = true;
}
    public void nobtn (ActionEvent e) throws IOException
{
    dec = false;
}
    public static boolean getDecision()
    {
        if (dec==false)
            System.out.println("hello");
        if (dec==true)
        {
            System.out.println("bollo");
        }
        return dec;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
