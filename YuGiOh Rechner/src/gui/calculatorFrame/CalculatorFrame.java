package gui.calculatorFrame;

import java.awt.Toolkit;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.tools.Borders;

import customizedClasses.CustomizedStage;
import gui.calculatorFrame.center.CalculatorCenterBorderPane;
import gui.calculatorFrame.center.CoinView;
import gui.calculatorFrame.center.CubeView;
import images.calculatorframe.CalculatorframeImagesReferenceClass;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CalculatorFrame extends CustomizedStage{
	public int coins = 0;
	public int cubes = 0;
	
	public static ChoosePlayerFrame choosePlayerFrame;

	StackPane backgroundPane = new StackPane(); 
	BorderPane borderPane = new BorderPane();
	private Image backgroundImage = new Image(CalculatorframeImagesReferenceClass.class.getResourceAsStream("59990.jpg"));

	Label header = new Label("Duellrechner");

	PlayerBox boxPlayer1 = new PlayerBox();
	PlayerBox boxPlayer2 = new PlayerBox();

	Button buttonCube = new Button("Würfel", new Glyph("FontAwesome", FontAwesome.Glyph.VIACOIN));
	Button buttonCoin = new Button("Münze", new Glyph("FontAwesome", FontAwesome.Glyph.VIACOIN));
	Button buttonQuickInfo = new Button("Schnellwissen");

	static CalculatorCenterBorderPane centerPane = new CalculatorCenterBorderPane();

	public CalculatorFrame() {
		header.setFont(Font.font("Arial", FontWeight.BOLD, 45));
		Node wrappedHeader = Borders.wrap(header).lineBorder().color(Color.GRAY.darker()).buildAll();

		boxPlayer1.setPadding(new Insets(50, 0, 0, 25));
		boxPlayer2.setPadding(new Insets(50, 25, 0, 0));

		buttonCube.setPadding(new Insets(10, 50, 10, 50));
		buttonCoin.setPadding(new Insets(10, 50, 10, 50));
		buttonQuickInfo.setPadding(new Insets(10, 50, 10, 50));

		buttonCube.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		buttonCoin.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		buttonQuickInfo.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		buttonQuickInfo.setOnAction(e -> {
			System.exit(8);
		});
		buttonCoin.setOnAction(e -> {
			coins++;
			CoinView coinView = new CoinView();
			coinView.show();
		});
		buttonCube.setOnAction(e -> {
			cubes++;
			CubeView cubeView = new CubeView(this);
			cubeView.show();
			cubeView.throwCube();
		});

		
		ButtonBar buttonBar = new ButtonBar();
		buttonBar.setPadding(new Insets(0, 365, 50, 0));
		buttonBar.getButtons().addAll(buttonCube, buttonCoin, buttonQuickInfo);

		borderPane.setTop(wrappedHeader);
		borderPane.setLeft(boxPlayer1);
		borderPane.setRight(boxPlayer2);
		borderPane.setBottom(buttonBar);
		borderPane.setCenter(centerPane);

		backgroundPane.setBackground(new Background(
				new BackgroundImage(backgroundImage,  BackgroundRepeat.NO_REPEAT,  
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Toolkit.getDefaultToolkit() 
								.getScreenSize().width, Toolkit.getDefaultToolkit() 
								.getScreenSize().height, false, false, false, false))));

		backgroundPane.getChildren().add(borderPane);
		setScene(new Scene(backgroundPane));
		setTitle("YuGiOh Rechner");
		setFullScreen(false);
		setSizeToCompleteScreen();
		
		buttonCoin.setDisable(true);
		choosePlayerFrame = new ChoosePlayerFrame(this);
		
		setOnHidden(e ->{
			if (CalculatorCenterBorderPane.timer != null) {				
				CalculatorCenterBorderPane.timer.cancel();
			}
		});
		
		
	}
	public void showPlayerChooser() {
		choosePlayerFrame.showAndWait();
		choosePlayerFrame.getSelectedPlayers();
		boxPlayer1.setPlayer(choosePlayerFrame.getSelectedPlayers()[0]);
		boxPlayer2.setPlayer(choosePlayerFrame.getSelectedPlayers()[1]);
//		setFullScreen(true);
	}


	public void newGame() {
		resetGame();
		showPlayerChooser();
	}
	public void resetGame() {
		boxPlayer1.setPlayer(null);
		boxPlayer2.setPlayer(null);
		coins = 0;
		cubes = 0;
		if (CalculatorCenterBorderPane.timer != null) {			
			CalculatorCenterBorderPane.timer.reset();
		}
		centerPane.reset();
	}

}
