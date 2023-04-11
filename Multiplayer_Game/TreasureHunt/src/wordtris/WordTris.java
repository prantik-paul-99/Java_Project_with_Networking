
package wordtris;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class WordTris extends Application {
    
     
    
    @Override
    public void start(Stage stage) throws Exception {
       
         Parent root = FXMLLoader.load(getClass().getResource("wordtris.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
               
    }

    public static void main(String[] args) throws Exception {
        
        launch(args);
        
    }
    
}
