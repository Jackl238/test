package gui.optionsFrame;

import java.util.Map;

import org.controlsfx.control.PropertySheet;
import org.controlsfx.glyphfont.Glyph;

import customizedClasses.CustomizedStage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class OptionsFrame extends CustomizedStage {
 
	/*
	 * Geplante Einstellmöglihkeiten:
	 * 
	 */
	
	PropertySheet propertySheet = new PropertySheet();
	BorderPane pane = new BorderPane();
	VBox vBox = new VBox();
	Button saveButton = new Button("Speichern", new Glyph("FontAwesome", "SAVE"));
	
	public OptionsFrame() {
		for (String key : OptionItems.getMap().keySet()) propertySheet.getItems().add(new OptionItems(key));
		
		vBox.getChildren().addAll(saveButton);
		
		saveButton.setOnAction(e -> {
			Map<String, Object> customDataMap = OptionItems.getMap();
			OptionsData optionsData = new OptionsData();
			optionsData.setShortnamePreffered((boolean) customDataMap.get(OptionItems.SHORTNAME_PREFFERED));
			optionsData.setAutomaticTimerStart((boolean) customDataMap.get(OptionItems.AUTOMATIC_TIMER_START));
			optionsData.setFullscreen((boolean) customDataMap.get(OptionItems.FULLSCREN));
			optionsData.setCubeThrowCount((int) customDataMap.get(OptionItems.CUBE_LOOP_COUNT));
			optionsData.setCoinThrowCount((int) customDataMap.get(OptionItems.COIN_LOOP_COUNT));
			optionsData.setWaitingMultiplikator((int) customDataMap.get(OptionItems.WAITING_TIME_MULTIPLYER));
			optionsData.setLastMatchesParam((int) customDataMap.get(OptionItems.LAST_MATCHES_COUNT));
			optionsData.saveOptions();
			saveButton.setDisable(true);
		});
		
		pane.setCenter(propertySheet);
		pane.setBottom(vBox);
		
		setScene(new Scene(pane));
	}
	
}
