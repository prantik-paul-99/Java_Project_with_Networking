/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ClientSendThread implements Runnable{
    private Socket clientSocket;
    private OutputStream ClientOS;
    public static PrintWriter toServer;
    public String val = null;
    public static String ClntName,ClntPass,oppo_name;
    
    public ClientSendThread(Socket s) {
        this.clientSocket = s;
        try {
            this.ClientOS = this.clientSocket.getOutputStream();
        } catch (Exception e) {
        }

    }

    @Override
    public void run() {
        try {
            toServer = new PrintWriter(clientSocket.getOutputStream(),true);
            while(true)
            {
                if(val!=null)
            {
                toServer.println(val);
            }
            
            }
            
            
        } catch (IOException ex) {
            System.out.println("after send");
        }
    }
}
