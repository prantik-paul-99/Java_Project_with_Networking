package Res;

import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author Prantik
 */
public class ResourceLoader {
	static ResourceLoader resourceLoader = new ResourceLoader();
	
	
	public static Image getImage(String filename) {
		return new Image(resourceLoader.getClass().getClassLoader().getResource("Res/images/" + filename).toString());
	}
	
}
