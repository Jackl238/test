package gui.mainframe;

import java.awt.Toolkit;

import customizedClasses.CustomizedStage;
import gui.calculatorFrame.CalculatorFrame;
import gui.deckframe.DeckFrame;
import gui.optionsFrame.OptionsFrame;
import gui.playerframe.PlayerFrame;
import gui.statisicsFrame.StatisticsFrame;
import images.mainframe.MainframeImagesReferenceClass;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame {

	public static CalculatorFrame currentCalculatorFrame;
	
	public static CustomizedStage primaryStage;
	
	private Image backgroundImage = new Image(MainframeImagesReferenceClass.class.getResourceAsStream("307514.jpg"));

	StackPane backgroundPane = new StackPane();

	BorderPane borderPane = new BorderPane(); 
	VBox buttonBox = new VBox();

	Button startGameButton = new Button("Spiel starten");
	Button editPlayerButton = new Button("Spieler");
	Button statisticsButton = new Button("Statistiken");
	Button optionsButton = new Button("Eintellungen");
	Button ruleBookButton = new Button("Regelbuch");
	Button deckButton = new Button("Deckverwaltung");
	Button exiteButton = new Button("Beenden");

	public MainFrame() {

	}

	private void setButtonActions() {
		startGameButton.setOnAction(e -> {
			if (currentCalculatorFrame == null) {
				currentCalculatorFrame = new CalculatorFrame();
			}
			currentCalculatorFrame.show();
			currentCalculatorFrame.showPlayerChooser();
			primaryStage.close();
		});
		exiteButton.setOnAction(e -> {
			System.exit(0);
		});
		editPlayerButton.setOnAction(e -> {
			PlayerFrame playerFrame = new PlayerFrame();
			playerFrame.show();
			primaryStage.close();
		});
		optionsButton.setOnAction(e -> {
			OptionsFrame optionsFrame = new OptionsFrame();
			optionsFrame.show();
		});
		statisticsButton.setOnAction(e ->{
			StatisticsFrame statisticsFrame = new StatisticsFrame();
			statisticsFrame.show();
		});
		deckButton.setOnAction(e -> {
			DeckFrame deckFrame = new DeckFrame();
			deckFrame.show();
		});
		ruleBookButton.setOnAction(e -> {
			
		});
	}

	private void setButtonLayout() {
		int buttonWidth = 250;
		int buttonHeigt = 70;

		startGameButton.setStyle("-fx-font: 24 arial;");
		editPlayerButton.setStyle("-fx-font: 24 arial;");
		statisticsButton.setStyle("-fx-font: 24 arial;");
		ruleBookButton.setStyle("-fx-font: 24 arial;");
		exiteButton.setStyle("-fx-font: 24 arial;");
		optionsButton.setStyle("-fx-font: 24 arial;");
		deckButton.setStyle("-fx-font: 24 arial;");

		startGameButton.setMinSize(buttonWidth, buttonHeigt);
		startGameButton.setMaxSize(buttonWidth, buttonHeigt);
		editPlayerButton.setMinSize(buttonWidth, buttonHeigt);
		editPlayerButton.setMaxSize(buttonWidth, buttonHeigt);
		statisticsButton.setMinSize(buttonWidth, buttonHeigt);
		statisticsButton.setMaxSize(buttonHeigt, buttonHeigt);
		ruleBookButton.setMinSize(buttonWidth, buttonHeigt);
		ruleBookButton.setMaxSize(buttonWidth, buttonHeigt);
		exiteButton.setMinSize(buttonWidth, buttonHeigt);
		exiteButton.setMaxSize(buttonWidth, buttonHeigt);
		optionsButton.setMinSize(buttonWidth, buttonHeigt);
		optionsButton.setMaxSize(buttonWidth, buttonHeigt);
		deckButton.setMinSize(buttonWidth, buttonHeigt);
		deckButton.setMaxSize(buttonWidth, buttonHeigt);

		ruleBookButton.setDisable(true);
		optionsButton.setDisable(true);
		
	}

	public void start(Stage primaryStage) {
		MainFrame.primaryStage = new CustomizedStage();
		
		setButtonLayout();
 
		setButtonActions();

		buttonBox.setPadding(new Insets(200, 0, 0, 200));

		buttonBox.getChildren().addAll(startGameButton, editPlayerButton,
				deckButton, statisticsButton, 
				optionsButton, ruleBookButton, exiteButton);

		borderPane.setLeft(buttonBox);
		backgroundPane.getChildren().add(borderPane);

		backgroundPane.setBackground(new Background(
				new BackgroundImage(backgroundImage,  BackgroundRepeat.NO_REPEAT,  
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Toolkit.getDefaultToolkit() 
								.getScreenSize().width, Toolkit.getDefaultToolkit() 
								.getScreenSize().height, false, false, false, false))));

		MainFrame.primaryStage.setScene(new Scene(backgroundPane));
		MainFrame.primaryStage.setTitle("YuGiOh Rechner | Hauptmenü");
//		MainFrame.primaryStage.setFullScreen(true);
		MainFrame.primaryStage.setSizeToCompleteScreen();
		MainFrame.primaryStage.show();
		
	}
	
	public void show() {
		primaryStage.show();
	}
	
}
