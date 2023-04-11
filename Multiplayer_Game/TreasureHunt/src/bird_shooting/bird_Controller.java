package bird_shooting;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class bird_Controller implements Initializable {

    //All FXML variables
    @FXML
    private AnchorPane root_pane;
    @FXML
    private ImageView cannon;
    @FXML
    private ImageView bird;
    @FXML
    private Label remaining;
    @FXML
    private Label killed;
    @FXML
    private ImageView background;

    //All boolean variables
    private boolean cannon_active = false;
    private boolean bullet_state = true;
    private boolean angle_state = false;
    private double angle = 0;
    public static  boolean game_started = false ;
    AnimationTimer run;
    // General variables
    double bullet_vx = 30, bullet_vy = 30;
    double bullet_angle[] = new double[200];
    static int bullet_count = -1;
    static int birds_killed = 0 ;
    static int bullet_remaining = 25 ;
    static int bullet_on_screen = 0 ;
    static int score = 0;

    //bullet arraylist
    ArrayList<ImageView> aliv_bullet = new ArrayList<>();

    //BulletBlast ArrayList
    ArrayList<ImageView> aliv_bullet_blast = new ArrayList<>();

    //All mediaplayer variables `
    MediaPlayer bomb_sound;
    //Boat
    ImageView boat = new ImageView("/images/boat.gif");

    //bird array
    public static ArrayList<ImageView> aliv_bird = new ArrayList<>();
    private int bird_count = 0;

    @FXML
    private void click_cannon(MouseEvent event) throws Exception {
        if(bird_count == 0){
        aliv_bird.get(bird_count).setFitHeight(130);
        aliv_bird.get(bird_count).setFitWidth(100);
        aliv_bird.get(bird_count).setLayoutY(60);
        aliv_bird.get(bird_count).setLayoutX(1350);
//        root_pane.getChildren().add(aliv_bird.get(bird_count));
        } 
        
        cannon_active = !cannon_active;

    }

    @FXML
    private void rotate_cannon(MouseEvent event) {

        try {
            
            if (cannon_active) {
                angle = angleOfSlope(event.getX(), event.getY(), cannon.getLayoutX(), cannon.getLayoutY());

                cannon.setRotate(Math.toDegrees(angle) + 90);
                cannon.setRotationAxis(Rotate.Z_AXIS);

            }
        } catch (Exception e) {

        }

    }

    private double angleOfSlope(double sceneX, double sceneY, double layoutX, double layoutY) {
        if (sceneX - layoutX >= 0) {
            return Math.atan((sceneY - layoutY) / (sceneX - layoutX));
        }
        return Math.PI + Math.atan((sceneY - layoutY) / (sceneX - layoutX));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          MediaPlayer shooting_bird_sound;
       Media sound = new Media("file:///C:/Users/Asus/Documents/NetBeansProjects/TreasureHunt/src/audios/background_sound.wav");

        shooting_bird_sound = new MediaPlayer(sound);

        shooting_bird_sound.setVolume(0.5);
        shooting_bird_sound.play();
        shooting_bird_sound.setAutoPlay(true);
        shooting_bird_sound.setOnEndOfMedia(new Runnable() {
            public void run() {
                shooting_bird_sound.seek(Duration.ZERO);
            }
        });
        aliv_bird.add(new ImageView("/images/9.gif"));
        aliv_bird.add(new ImageView("/images/10.gif"));
        aliv_bird.add(new ImageView("/images/12.gif"));
        aliv_bird.add(new ImageView("/images/13.gif"));
        aliv_bird.add(new ImageView("/images/9.gif"));
        aliv_bird.add(new ImageView("/images/10.gif"));
        aliv_bird.add(new ImageView("/images/12.gif"));
        aliv_bird.add(new ImageView("/images/13.gif"));
        aliv_bird.add(new ImageView("/images/9.gif"));    
        aliv_bird.add(new ImageView("/images/10.gif"));
        aliv_bird.add(new ImageView("/images/12.gif"));
        aliv_bird.add(new ImageView("/images/13.gif"));
        aliv_bird.add(new ImageView("/images/9.gif"));
        aliv_bird.add(new ImageView("/images/10.gif"));
        aliv_bird.add(new ImageView("/images/12.gif"));
        aliv_bird.add(new ImageView("/images/13.gif"));
        aliv_bird.add(new ImageView("/images/10.gif"));
        aliv_bird.add(new ImageView("/images/9.gif"));        
        aliv_bird.add(new ImageView("/images/12.gif"));
        aliv_bird.add(new ImageView("/images/13.gif"));
        System.out.println("after bird image loading ");

        boat.setLayoutY(290);
        boat.setFitHeight(150);
        boat.setFitWidth(200);
        boat.setLayoutX(-200);
        root_pane.getChildren().add(boat);
        run = new AnimationTimer() {
            long previoustime = 0;

            @Override
            public void handle(long now) {
                double timeDif = (now - previoustime) / 1_000_000_000.0;
                try {
                    
                    end_birdshooting();
                    bird_move(timeDif);
                    boat_move(timeDif);
                    
                    bullet_move(timeDif);

                    //System.out.println(bullet_count);
                    killed.setText(Integer.toString(birds_killed));
                    remaining.setText(Integer.toString(bullet_remaining));

                    
                    

                    previoustime = now;
                } catch (Exception e) {

                }
            }

        };
        run.start();
    }

    private void boat_move(double timedif) {

        if (boat.getLayoutX() <= -900) {
            boat.setLayoutX(1500);

        } else {
            boat.setLayoutX(boat.getLayoutX() - 150 * timedif);
        }
    }

    private void bullet_hits_bird() {

//        if (bullet_on_screen !=1) 
//        { // when one bullet is shot
//
//             for (int i = 0; i < bullet_count; i++) {
//
//                double bird_x = aliv_bird.get(bird_count).getLayoutX();
//                double bird_y = aliv_bird.get(bird_count).getLayoutY();
//                double bullet_x = aliv_bullet.get(i).getLayoutX();
//                double bullet_y = aliv_bullet.get(i).getLayoutY();
//                if ((bullet_x >= bird_x && bullet_x <= bird_x + 80) && (bullet_y >= bird_y && bullet_y <= bird_y + 80)) {
//
//                    if(root_pane.getChildren().remove(aliv_bird.get(bird_count))){
//                        birds_killed++;
//                    }
//                    
//                    
//                    if(root_pane.getChildren().remove(aliv_bullet.get(i))){
//                        bullet_on_screen -- ;
//                    }
//                    //aliv_bullet.remove(i);
//                    break;
//
//                }
//            }

//        } else {
            double bird_x = aliv_bird.get(bird_count).getLayoutX();
            double bird_y = aliv_bird.get(bird_count).getLayoutY();
            double bullet_x = aliv_bullet.get(bullet_count).getLayoutX();
            double bullet_y = aliv_bullet.get(bullet_count).getLayoutY();
            if ((bullet_x >= bird_x && bullet_x <= bird_x + 110) && (bullet_y >= bird_y && bullet_y <= bird_y + 110)) {
                
                ImageView bird_blast;
                bird_blast = new ImageView("/images/blast.gif");
                bird_blast.setFitHeight(80);
                bird_blast.setFitWidth(80);
                bird_blast.setLayoutX(bird_x);
                bird_blast.setLayoutY(bird_y);
                
                
                 Timeline blast_time = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            root_pane.getChildren().add(bird_blast);
                        }),
                        new KeyFrame(Duration.seconds(0.77), e -> {
                            root_pane.getChildren().remove(bird_blast);
                        })
                );
                blast_time.play();
        
                // bird screaming sound 
                Media sound = new Media("file:///C:/Users/Asus/Documents/NetBeansProjects/TreasureHunt/src/audios/bird.wav");

                MediaPlayer bird_sound = new MediaPlayer(sound);
                Timeline scream_sound_time = new Timeline(
                        new KeyFrame(Duration.ZERO, e -> {
                            bird_sound.setVolume(.6);
                            bird_sound.play();
                        }),
                        new KeyFrame(Duration.seconds(.6), e -> {
                            bird_sound.stop();
                        })
                );
                scream_sound_time.play();

               if(root_pane.getChildren().remove(aliv_bird.get(bird_count))){
                  birds_killed++;
                   }
                if(root_pane.getChildren().remove(aliv_bullet.get(bullet_count))){
                       bullet_on_screen -- ;
                    }

        }

    }
   

    private void bird_move(double timedif) {
            
           bullet_hits_bird();
         
            if (aliv_bird.get(bird_count).getLayoutX() <= -150) {
                root_pane.getChildren().remove(aliv_bird.get(bird_count));
                bird_count++;
                aliv_bird.get(bird_count).setFitHeight(130);
                aliv_bird.get(bird_count).setFitWidth(100);
                aliv_bird.get(bird_count).setLayoutY(50);
                 
                aliv_bird.get(bird_count).setLayoutX(1360);
                root_pane.getChildren().add(aliv_bird.get(bird_count));

            } else {
                aliv_bird.get(bird_count).setLayoutX(aliv_bird.get(bird_count).getLayoutX() - 430 * timedif);
            } 
    }

    private void bullet_move(double timedif) {
        try {

            for (int i = 0; i <= bullet_count; i++) {

                if (aliv_bullet.get(i).getLayoutY() >= 0) {
                    bullet_vx = 400 * Math.cos(bullet_angle[i]);
                    bullet_vy = 400 * Math.sin(bullet_angle[i]);
                    aliv_bullet.get(i).setLayoutX(aliv_bullet.get(i).getLayoutX() + bullet_vx * timedif);
                    aliv_bullet.get(i).setLayoutY(aliv_bullet.get(i).getLayoutY() + bullet_vy * timedif);

                } else {
                    if(root_pane.getChildren().remove(aliv_bullet.get(i))){
                        bullet_on_screen--;
                        System.out.println(bullet_on_screen);
                    }
                    
                    
                }
            }

        } catch (Exception e) {

        }
    }

    private void show_bullet() throws Exception {

        double C_C_X, C_C_Y, pivot;
        C_C_X = cannon.getLayoutX() + cannon.getFitWidth() / 2;
        C_C_Y = cannon.getLayoutY() + cannon.getFitHeight() / 2;
        pivot = cannon.getFitHeight() / 2;


        ImageView bullet;
        bullet = new ImageView("/images/bullet1.png");
        aliv_bullet.add(bullet);
        aliv_bullet.get(bullet_count).setFitHeight(35);
        aliv_bullet.get(bullet_count).setFitWidth(35);

        aliv_bullet.get(bullet_count).setLayoutX(C_C_X + pivot * Math.cos(bullet_angle[bullet_count]));
        aliv_bullet.get(bullet_count).setLayoutY(C_C_Y + pivot * Math.sin(bullet_angle[bullet_count]));
        aliv_bullet.get(bullet_count).setRotationAxis(Rotate.Z_AXIS);
        aliv_bullet.get(bullet_count).setRotate(Math.toDegrees(bullet_angle[bullet_count]) + 90);
        root_pane.getChildren().add(aliv_bullet.get(bullet_count));

    }

    void show_blast() throws InterruptedException {

        //BULLET BLAST SETUP
        ImageView bullet_blast;
        bullet_blast = new ImageView("/images/blast.gif");
        aliv_bullet_blast.add(bullet_blast);
        //bullet.setImage(new Image(getClass().getResourceAsStream("/images/blast.gif")));
        aliv_bullet_blast.get(bullet_count).setFitHeight(365);
        aliv_bullet_blast.get(bullet_count).setFitWidth(300);

        aliv_bullet_blast.get(bullet_count).setLayoutX(460);
        aliv_bullet_blast.get(bullet_count).setLayoutY(400);
        aliv_bullet_blast.get(bullet_count).setRotationAxis(Rotate.Z_AXIS);
        aliv_bullet_blast.get(bullet_count).setRotate(Math.toDegrees(bullet_angle[bullet_count]) + 90);

        //BulletBlast Sound
        Media sound = new Media("file:///C:/Users/ASUS/Documents/NetBeansProjects/TreasureHunt/src/audios/blast.wav");
        bomb_sound = new MediaPlayer(sound);
        Timeline blast_sound_time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    bomb_sound.setVolume(.6);
                    bomb_sound.play();
                }),
                new KeyFrame(Duration.seconds(1.7), e -> {
                    bomb_sound.stop();
                })
        );
        blast_sound_time.play();

        Timeline blast_time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    root_pane.getChildren().add(bullet_blast);
                }),
                new KeyFrame(Duration.seconds(0.97), e -> {
                    for (int i = 0; i <= bullet_count; i++) {
                        root_pane.getChildren().remove(aliv_bullet_blast.get(i));
                    }

                })
        );
        blast_time.play();

    }

    @FXML
    private void bullet_init(MouseEvent event) throws InterruptedException {
        
        System.out.println("inside bullet init");
         bullet_remaining-- ;
         bullet_on_screen++;
         if(birds_killed==-1){
             birds_killed++;
         }
         
        
        bullet_count++;
        bullet_angle[bullet_count] = angle;

        bullet_state = true;
        try {

            show_blast();
            angle_state = true;
            show_bullet();
            angle_state = false;

        } catch (Exception e) {

        }

    }
    
    void end_birdshooting() throws IOException{
        if(bird_count==20 || bullet_remaining<= 0){
            run.stop();
            score = birds_killed*10+(bullet_remaining)*5;
            if(!treasurehunt.TreasureHunt.stop)
            treasurehunt.TreasureHunt.my_score+=score;
            Threads.ClientSendThread.toServer.println("B/"+Integer.toString(score));
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/bird_shooting/bird_score.fxml"));
            root_pane.getChildren().setAll(pane);
            
            
        }
    }

}
