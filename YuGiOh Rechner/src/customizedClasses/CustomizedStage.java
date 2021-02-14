package customizedClasses;

import java.awt.Toolkit;

import images.mainframe.MainframeImagesReferenceClass;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CustomizedStage extends Stage{

	private static Image frameImage = new Image(MainframeImagesReferenceClass.class.getResourceAsStream("465035229.jpg"));
	
	public CustomizedStage() {
		setSizeToCompleteScreen();
		super.setFullScreen(true);
		getIcons().add(getFrameImage());
		
	}
	
	public void setSize(double width, double height) {
		setHeight(height);
		setWidth(width);
	}
	
	public static Image getFrameImage() {
		return frameImage;
	}
	
	public void setSizeToCompleteScreen() {
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 
				Toolkit.getDefaultToolkit().getScreenSize().height - 40);
	}
}
