package mysnake;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import treasurehunt.TreasureHunt;


public class MySnakeController implements Initializable {

    @FXML
    public GridPane gameGrid;
    private String p1name = Threads.ClientSendThread.ClntName;
    TimerTask tasknew ;
    public static String p2name;
    Label lb = new Label("hi");
    @FXML
    private ImageView DiceImage;
    public static int opnt_val=0,opnt_pos=-1,wongame = 0;
    public static boolean turn_val_your = false ;
    public int val,alert_count =0;
    static int cur_pos=-1;
    public ImageView iv,iv1;
    @FXML
    private Label turn;
    @FXML
    private Button dice;
   
    Player p1,p2 ;

    @FXML
    private AnchorPane snake_pane;
    public MediaPlayer snakeladder_sound;
    
    
    @FXML
    public void ClickDice() throws InterruptedException, IOException
    {
        if (p1.getTurn()) {
            gameGrid.getChildren().remove(iv);
        }
        if (p2.getTurn()) {
            gameGrid.getChildren().remove(iv1);
        }
        final int MIN = 1;
        final int MAX = 6;
        Random rand = new Random();
        val = rand.nextInt((MAX - MIN) + 1) + MIN;

        
        System.out.println(val+" in controller");
        //Image img1=new Image("/Res/images/blankdice.png");
        Image img;
        
        if (val==1)
              {img=new Image("/Res/images/dice1.png");
            DiceImage.setImage(img);}
        else if (val == 2)
             {img=new Image("/Res/images/dice2.png");
             DiceImage.setImage(img);}
        else if (val == 3)
             {img=new Image("/Res/images/dice3.png");
             DiceImage.setImage(img);}
        else if (val == 4)
             {img=new Image("/Res/images/dice4.png");
             DiceImage.setImage(img);}
        else if (val == 5)
             {img=new Image("/Res/images/dice5.png");
             DiceImage.setImage(img);}
        else if (val == 6)
             {img=new Image("/Res/images/dice6.png");
             DiceImage.setImage(img);}
             
       
        if (p1.getTurn())
        {
            update_player(p1);
        }
           
        }
    
    
    public int index_to_pos(Player p)
    {
        int row = p.getPos_row();
        int col = p.getPos_col();
        int pos;
        if (row%2==0)
            pos = 10*(9-row)+(9-col)+1;
        else
            pos = 10*(9-row)+col+1;
        return pos;    
    }
    
    public void  pos_to_index(int pos_value,Player p)
    {
        int row = 9-(pos_value-1)/10;
        int col;
        if (row%2==0 && (pos_value/10)%2==0) col = 0;
        
        else if (row%2==0)
        {
            col = 9- (pos_value%10)+1;
        }
        else if (row%2==1 && (pos_value/10)%2==1) col = 9-(pos_value%10);
        else col = (pos_value%10)-1;
        p.setPos_col(col);
        p.setPos_row(row);
    }
    
    public int check_pos (int pos)
    {
        Board board = new Board();
        if (pos>100)
            pos= pos-val;
        else if (pos==100)
        {
            pos=pos;
        }
        else if (board.SNAKES.containsKey(pos))
            {pos= board.SNAKES.get(pos);
            }
        else if (board.LADDERS.containsKey(pos)) {
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setHeaderText("DO YOU WANT TO CLIMB THE LADDER?");
            al.setContentText("Currently at position " + pos + "\n GO to position " + board.LADDERS.get(pos) + "?");
            Optional<ButtonType> option = al.showAndWait();

            if (option.get() == ButtonType.OK) {
                if(board.LADDERS.get(pos).equals(56))
                {
//                    Path lp1 = new Path();
//                    
//                    lp1.getElements().add(new LineTo(363,310));
//                    PathTransition L1 = new PathTransition(Duration.seconds(2), lp1, iv);
//                    L1.play();
                }
                pos= board.LADDERS.get(pos);
            } else {
                pos= pos;
            }
        }
        //else pos+=val;
        Threads.ClientSendThread.toServer.println("V-"+Integer.toString(val)+"-"+Integer.toString(pos)); //SEND THE  valuev to client thread
        p1.setTurn(false);
        turn.setText("OPPONENT's");
        wongame=0;
        dice.setDisable(true);
        treasurehunt.TreasureHunt.my_score = cur_pos;
        return pos;
    }
    
    public void update_player(Player p) throws InterruptedException, IOException
    {
        System.out.println("update");
        
        if(p.getName().equals("p1"))
        {
            System.out.println("player");
        cur_pos = index_to_pos(p);
        cur_pos += val;
        cur_pos = check_pos(cur_pos);
        if (cur_pos == 100) {
            mysnake.SnakeEndController.winner = Threads.ClientSendThread.ClntName;
            mysnake.SnakeEndController.loser = Threads.ClientSendThread.oppo_name;
            Thread.sleep(100);
            Threads.ClientSendThread.toServer.println("W");
            go_to_next_game("You");
        }
        pos_to_index(cur_pos,p);
        iv = new ImageView(p.getImg());
        iv.setFitHeight(50);
        iv.setFitWidth(70);
        gameGrid.add(iv, p.getPos_col(),p.getPos_row());
        }
        if (p.getName().equals("p2"))
        {
            turn.setText("YOUR");
            dice.setDisable(false);
            System.out.println("update p2");
            pos_to_index(opnt_pos, p);
            iv1 = new ImageView(p.getImg());
            iv1.setFitHeight(50);
            iv1.setFitWidth(70);
            gameGrid.add(iv1, p.getPos_col(),p.getPos_row());
        }
    }
    
    
    public void go_to_next_game(String p_name) throws IOException
    {
        
        if (alert_count < 1) {
            alert_count++;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("GameOver");
            alert.setHeaderText("Results:");
            if (wongame == 0) {
                alert.setContentText("You won the game");
            }
            if (wongame == 1) {
                alert.setContentText("Opponent won the game");
            }
            alert.showAndWait();
            tasknew.cancel();

            snakeladder_sound.stop();

            TreasureHunt.state = 1;
            TreasureHunt.backgroundsound(TreasureHunt.state, TreasureHunt.ismute);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("SnakeEnd.fxml"));
            snake_pane.getChildren().setAll(pane);
        }
    }
    
    
    
    public void update() throws InterruptedException, IOException
    {
        dice.setDisable(false);
        gameGrid.getChildren().remove(iv1);
        update_player(p2);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //gameGrid.setStyle(p1name);
        //gameGrid.setStyle("-fx-background-color: radial-gradient(radius 180%, burlywood, derive(burlywood, -30%), derive(burlywood, 30%)); -fx-text-fill: white;");
        
        Media sound = new Media("file:///C:/Users/Asus/Documents/NetBeansProjects/TreasureHunt/src/audios/snake_background.mp3");

        snakeladder_sound = new MediaPlayer(sound);

        snakeladder_sound.setVolume(0.2);
        snakeladder_sound.play();
        snakeladder_sound.setAutoPlay(true);
        snakeladder_sound.setOnEndOfMedia(new Runnable() {
            public void run() {
                snakeladder_sound.seek(Duration.ZERO);
            }
        });
        
        Image img1 = new Image("/Res/images/p1.png");
        Image img2 = new Image("/Res/images/p2.png");
        if (turn_val_your) {
            turn.setText("YOUR");
            p1 = new Player("p1", true, 9, -1, img1);
            p2 = new Player("p2", false, 9, -1, img2);
        }
        else
        {
            turn.setText("OPPONENT's");
            dice.setDisable(true);
            p1 = new Player("p1", true, 9, -1, img2);
            p2 = new Player("p2", false, 9, -1, img1);
        }
        
        tasknew = new TimerTask() {
            @Override
            public void run() {
                if ((opnt_pos != -1 && opnt_val != 0) || wongame == 1) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("in timtask");
                            if (opnt_pos != -1 && opnt_val != 0) {
                                dice.setDisable(false);
                                if(opnt_pos==100)
                {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(MySnakeController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    mysnake.SnakeEndController.winner = Threads.ClientSendThread.oppo_name;
                                    System.out.println(Threads.ClientSendThread.oppo_name);
                                    System.out.println(mysnake.SnakeEndController.winner);
                                    mysnake.SnakeEndController.loser = Threads.ClientSendThread.ClntName;
                                    mysnake.SnakeEndController.score = cur_pos;
                                } else
                                    mysnake.SnakeEndController.score = opnt_pos;
                                gameGrid.getChildren().remove(iv1);
                                try {
                                    try {
                                        update_player(p2);
                                    } catch (IOException ex) {
                                        Logger.getLogger(MySnakeController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } catch (InterruptedException ex) {
                                }
                                p1.setTurn(true);
                                opnt_pos = -1;
                                opnt_val = 0;
                            }

                            if (wongame == 1) {
                                try {
                                    go_to_next_game("Opponent");
                                } catch (IOException ex) {
                                }
                                wongame = 0;
                            }

                        }
                    });

                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(tasknew, 500, 500);
    }
    // TODO;

    @FXML
    private void setPink(ActionEvent event) {
        gameGrid.setStyle("-fx-background-color: #FF66CC");
    }

    @FXML
    private void setOrange(ActionEvent event) {
        gameGrid.setStyle("-fx-background-color: #FF9900");
    }

    @FXML
    private void setPaste(ActionEvent event) {
        gameGrid.setStyle("-fx-background-color: #47c6bf");
    }
}
    

