
package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import treasurehunt.PlayersListController;

/**
 *
 * @author Prantik
 */
public class ClientReceiveThread implements Runnable {
    private Socket clientSocket;
    private InputStream ClientIS;
    public static BufferedReader fromServer ;
    public String msg = "null";
    public int value;
    public static int opnt_score = 0;
    //public static BufferedReader fromServer;
    
    public ClientReceiveThread(Socket s)
    {
        try {
            this.clientSocket = s;
            ClientIS = this.clientSocket.getInputStream();
            fromServer =new   BufferedReader(new InputStreamReader(this.ClientIS));

        } catch (IOException ex) {
            
        }
    }

    @Override
    public void run() {
        
        
        while (true) {
            try {
                msg = fromServer.readLine();
                
                if (msg != null)
                {
                    if(msg.charAt(0)=='S')
                    {
                        String[] signUpInfo = msg.split("-");
                         treasurehunt.SignUpFXMLController.sgnUpMsg = signUpInfo[1];
                         System.out.println(signUpInfo[1]);

                    }
                    else if(msg.charAt(0)=='L')
                    {
                        String[] loginInfo = msg.split("-");
                         treasurehunt.LoginController.Login_msg = loginInfo[1];
                         System.out.println(loginInfo[1]);
                    }
                    else if(msg.charAt(0)=='P'){
                       PlayersListController.names = msg.split("-");
                    }
                    else if (msg.charAt(0)=='R')
                    {
                        String[] request = msg.split("-");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                               
                                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                                System.out.println("alert box");
                                confirmation.setTitle("Request to play");
                                confirmation.setHeaderText("Request from "+request[2]);
                                Threads.ClientSendThread.oppo_name = request[2];
                                confirmation.setContentText("Do you want to play with " +request[2]+"?");
                                mysnake.MySnakeController.p2name=request[2];
                                Optional<ButtonType> option = confirmation.showAndWait();
                                System.out.println("waiting");
                               

                                if (option.get() == ButtonType.OK) {
                                    Threads.ClientSendThread.toServer.println("A-" + request[2] + "-ok");
                                    PlayersListController.game_started = true;

                                } else {
                                    Threads.ClientSendThread.toServer.println("A-" +request[2]+ "-no");
                                }
                            }
                        });
                        
                    }
                    
                    else if(msg.charAt(0)=='K')
                    {
                         Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                               
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("GameOver");
                                alert.setHeaderText("Results:");

                                alert.setContentText("Opponent Has Reached The Ending Point");

                                alert.showAndWait();
            
                            }
                        });
                        
                    }
                    else if (msg.equals("denied"))
                    {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("DENIED");
                                alert.setHeaderText("SORRY!");

                                alert.setContentText("Your request has been denied");

                                alert.showAndWait();
                                PlayersListController.game_started = false;
                        
                            }

                                });
                    }
                    
                    else if (msg.equals("accepted"))
                    {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                PlayersListController.game_started = true;
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("ACCEPTED");
                                alert.setHeaderText("Congrats!!");

                                alert.setContentText("Your request has been accepted");

                                alert.showAndWait();
                        
                            }

                                });
                    }

                    else if(msg.charAt(0)=='#'){
                        String[] snake = msg.split("-");
                          if(snake[1].equals("YourTurn")){
                              try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                            }
                               mysnake.MySnakeController.turn_val_your=true;
                            
                          }
                    }
                    
                    else if (msg.charAt(0)=='V')
                    {
                        String[] snake1 = msg.split("-");
                        System.out.println("after getting ");
                        System.out.println(snake1[1]+" "+ snake1[2]);
                        mysnake.MySnakeController.opnt_val = Integer.valueOf(snake1[1]);
                        mysnake.MySnakeController.opnt_pos = Integer.valueOf(snake1[2]);
                    }
                    
                    else if (msg.charAt(0)=='E')
                    {
                        String[] score = msg.split("-");
                        opnt_score = Integer.parseInt(score[1]);
                    }
                    
                    else if (msg.equals("W"))
                    {
                        mysnake.MySnakeController.wongame =1;
                    }
                    
                }
        } catch (IOException ex) {
                System.out.println("error");
                break;
                
            }
        }
    }
    
}
