package gui.calculatorFrame.center;


import org.controlsfx.dialog.ExceptionDialog;

import customizedClasses.CustomizedStage;
import gui.calculatorFrame.CalculatorFrame;
import images.others.OtherImagesReferenceClass;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;

public class CubeView extends CustomizedStage {

	StackPane backgroundPane = new StackPane();
	private Image backgroundImage = new Image(OtherImagesReferenceClass.class.getResourceAsStream("Würfel.jpg"));
	
	Label label = new Label("");
	VBox vBox = new VBox();

	Task<Void> task;
	
	CalculatorFrame calculatorFrame;
	
	
	public CubeView(CalculatorFrame calculatorFrame) {
		this.calculatorFrame = calculatorFrame;
		setFullScreen(false);
		setSize(350, 300);
		setTitle("Würfel");
		setResizable(false);
		
		label.setFont(Font.font("Arial", FontWeight.BOLD, 85));
		label.setTextFill(Paint.valueOf("#f0f0f0"));
		
		backgroundPane.setBackground(new Background(
				new BackgroundImage(backgroundImage,  BackgroundRepeat.NO_REPEAT,  
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(getWidth(), getHeight(), false, false, false, false))));

		
		vBox.setPadding(new Insets(5, 0, 0, 245));
		vBox.getChildren().add(label);
		backgroundPane.getChildren().add(vBox);
		setScene(new Scene(backgroundPane));
		setAlwaysOnTop(true);
		initModality(Modality.APPLICATION_MODAL);
	}
	
	public void throwCube() {
		calculatorFrame.setFullScreen(false);
		createNewTask();
		label.textProperty().bind(Bindings.concat(task.messageProperty()));
        Thread thread = new Thread(task);
        thread.start();
	}
	
	 private void createNewTask() {

         task = new Task<Void>() {
              @Override
              protected Void call() throws Exception {
            	  for (int i = 0; i < 50; i++) {
          			int value = (int) (Math.random() * 6) + 1;
          			System.out.println("Würfelwert: " + value);
          			updateMessage("" + value);
          			try {
          				Thread.sleep(10 * i);
          			} catch (InterruptedException e) {
          				ExceptionDialog exceptionDialog = new ExceptionDialog(e);
          				exceptionDialog.show();
          			}
          		}
                   return null;
              }
         };
     }
	
}
