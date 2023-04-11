
package wordtris;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;
import treasurehunt.TreasureHunt;




public class wordtrisController implements Initializable {

    @FXML
    private ImageView background;
    @FXML
    private Label tris;
    Label lb = new Label();
    @FXML
    private AnchorPane mainpane;
    
    int width=0,count=0,idx=0;
    String typed;
    boolean flag=false; 
    
    
    
     String name[] = {"shammya","shafayat","kamrul","mehedi","pranto","sabbir","fahim","anik","zahin","tanvir"};
    @FXML
    private Label correctwords;
    static int correctwords_count=0;
    @FXML
    private Label wrongwords;
    static int wrongwords_count=0;
    @FXML
    private Label score;
    static int score_count=0;
    @FXML
    private Label totalwordspassed;
    static int totalwordspassed_count=0;
    static MediaPlayer wordtris_sound; 
    @FXML
    private TextField typehere;
     
     
     
    
    
    public  void changebackground ()  
    {
         
        
        Image img = new Image ("/images/8.jpg");
        background.setImage(img);
        
        
        img = new Image ("/images/9.jpg");
        background.setImage(img);
        
        
        img = new Image ("/images/5.jpg");
        background.setImage(img);
        
        
        img = new Image ("/images/6.jpg");
        background.setImage(img);
        
        
        img = new Image ("/images/4.jpg");
        background.setImage(img);
        
        
        img = new Image ("/images/7.jpg");
        background.setImage(img);
           
    }
    
      
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        TreasureHunt.state=2;
        TreasureHunt.backgroundsound( TreasureHunt.state , TreasureHunt.ismute );
        
         Media sound = new Media("file:///C:/Users/Asus/Documents/NetBeansProjects/TreasureHunt/src/audios/My_demons.mp3");

        wordtris_sound = new MediaPlayer(sound);

        wordtris_sound.setVolume(0.5);
        wordtris_sound.play();
        wordtris_sound.setAutoPlay(true);
        wordtris_sound.setOnEndOfMedia(new Runnable() {
            public void run() {
                wordtris_sound.seek(Duration.ZERO);
            }
        });
       
        try {
            move(width+30).play();
            
            
            
            
            
//      try {
//            Thread r = new Timer(this);
//
//            System.out.println("ovi sdf"); 
//        } catch (Exception ex) {
//            System.out.println(ex.getCause());
//        }
//        
//    } 
        } catch (IOException ex) {
            Logger.getLogger(wordtrisController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      
     public Label createlabel(double x){
        Label lb = new Label();
         
        lb.setLayoutX(521);
        lb.setLayoutY(569-x);
        lb.setAlignment(Pos.CENTER);
        lb.setFont(new Font(25));
        lb.setText(treasurehunt.TreasureHunt.al_word.get(totalwordspassed_count));
       // lb.setTextAlignment(TextAlignment.LEFT);
        lb.setMinHeight(30);
        lb.setMinWidth(186);
        lb.setStyle(" -fx-border-color: black ;-fx-background-color: purple; -fx-text-fill: yellow;");
        
        //lb.setTranslateY();
         
        return lb;
     }
             
     public TranslateTransition move(double  x) throws IOException{
         
         if (count == 8) {
             TranslateTransition tt = new TranslateTransition();
             tt.setNode(null);
             tt.setCycleCount(0);
             if(!treasurehunt.TreasureHunt.stop)
             treasurehunt.TreasureHunt.my_score += score_count;
             Threads.ClientSendThread.toServer.println("T/"+Integer.toString(score_count));
             
             wordtris_sound.stop();
             TreasureHunt.state = 1;
             TreasureHunt.backgroundsound(TreasureHunt.state, TreasureHunt.ismute);
             AnchorPane pane = FXMLLoader.load(getClass().getResource("/wordtris/Score_wordtris.fxml"));
             mainpane.getChildren().setAll(pane);
             return tt;
         }
        
        
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(4));
        tris.setText(treasurehunt.TreasureHunt.al_word.get(totalwordspassed_count));
        tt.setNode(tris);
        tt.setByY(567-x);
        tt.setCycleCount(1);
        
        
        tt.setOnFinished(e ->{
         
            
           

            if(typehere.getText().equals("")){
                flag=false;
            }
           
            if(!flag){
              
              mainpane.getChildren().add(createlabel(width));  
              width=width+30;
              count++;
              wrongwords_count++;
              typehere.setText("");
              score_count-=5;
              wrongwords.setText(Integer.toString(wrongwords_count));
            }
        totalwordspassed_count++; 
         totalwordspassed.setText(Integer.toString(totalwordspassed_count));
         correctwords.setText(Integer.toString(correctwords_count));
        typehere.setText("");
        
        tris.setText(treasurehunt.TreasureHunt.al_word.get(totalwordspassed_count));
        tris.setTranslateY(14);
        
        score.setText(Integer.toString(score_count));
        
        
        System.out.println(count);
            
            try {
                move(width+30).play();
            } catch (IOException ex) {
                Logger.getLogger(wordtrisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        tt.setNode(null);
        
        });
        
        return tt;
     } 
     
     
     public void enterpressed()
   {
       System.out.println("inside enter pressed");
       if(typehere.getText().toString().equals(treasurehunt.TreasureHunt.al_word.get(totalwordspassed_count))){
           flag=true;
           correctwords_count++;
           
           score_count+=20;
       }
       else{
           flag=false;
           
       }
           
       
   }
   
    
}


//class Timer extends Thread{
//    public  wordtrisController  w;
//    int k=10;
//
//    public Timer(wordtrisController  ww) {
//        w=ww;
//        start();
//    }
//    
//    @Override
//    public void run() {
//        while(k>=0)
//        {
//            try {
//                Thread.sleep(1000);
//                w.changebackground();
//                System.out.println("Timer ");
//            } catch (InterruptedException ex) {
//                
//            } 
//            catch (Exception ex) {
//                
//            }
//            k--;
//        }
//   }
//    
//}
