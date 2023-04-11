
package treasurehunt;

import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import Threads.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TreasureHunt extends Application {

    //variables starts 
    Stage stage;
    public static String message;
    public static MediaPlayer buttonaudio;
    public static MediaPlayer backgroundaudio;
    public static MediaPlayer exitaudio;
    public static int state = 1;
    public static boolean ismute = false;
    public static int my_score;
    public static boolean stop = false;
    public static ArrayList<String> al_word= new ArrayList<>();

    // variables end
    public static void buttonsound(boolean var) {
        Media audio = new Media("file:///C:/Users/Asus/Documents/NetBeansProjects/TreasureHunt/src/audios/buttonclick.mp3");
        buttonaudio = new MediaPlayer(audio);
        buttonaudio.setAutoPlay(true);
        if (var == false) {
            buttonaudio.setVolume(0.5);
        } else {
            buttonaudio.setVolume(0);
        }

    }

    public static void backgroundsound(int var1, boolean var2) {
        if (var2 == false) {
            backgroundaudio.setVolume(0.2);
        } else {
            backgroundaudio.setVolume(0);
        }
        if (var1 == 1) {
            backgroundaudio.play();
        }
        if (var1 == 2) {
            backgroundaudio.pause();
        }
        if (var1 == 3) {
            backgroundaudio.stop();
        }

    }

    public static void exitsound() {
        Media audio = new Media("file:///C:/Users/Asus/Documents/NetBeansProjects/TreasureHunt/src/audios/buttonclick.mp3");
        exitaudio = new MediaPlayer(audio);
        exitaudio.setAutoPlay(true);
        exitaudio.setVolume(1);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Media audio = new Media("file:///C:/Users/Asus/Documents/NetBeansProjects/TreasureHunt/src/audios/background_sound.mp3");
        backgroundaudio = new MediaPlayer(audio);
        
        
        FileInputStream fstream = new FileInputStream("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\TreasureHunt\\src\\texts\\words.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        int count = 0;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            if (count == 50) {
                break;
            }

            al_word.add(strLine);
            count++;
        }
        fstream.close();

        Parent root = FXMLLoader.load(getClass().getResource("LogInFXML.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Treasure  Hunt");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        backgroundsound(state, ismute);

    }

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 7179);
        ClientSendThread clSnd = new ClientSendThread(clientSocket);
        Thread Snd = new Thread(clSnd); //the teread that sends to server
        Snd.start();
        
        
        ClientReceiveThread clRcv = new ClientReceiveThread(clientSocket);
        Thread Rcv = new Thread(clRcv); //the thread that receives from server
        Rcv.start();

        launch(args);

    }

}
