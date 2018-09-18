package Release1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MonsterGUI extends Application implements EventHandler<ActionEvent>{
	Scene titleScene, monsterScene,battleScene;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Pandemon(ster)ium");

		GridPane titleLayout = new GridPane();
		Canvas canvas = new Canvas(600, 400);	

		// Title
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill( Color.RED );
		gc.setStroke( Color.BLACK );
		gc.setLineWidth(2);
		Font theFont = Font.font( "Verdana", FontWeight.BOLD, 48 );
		gc.setFont( theFont );
		gc.fillText( "PandeMonsterium", 60, 50 );
		gc.strokeText( "PandeMonsterium", 60, 50 );

		// Start button
		Button button = new Button("Start");
		button.setLayoutX(250);
		button.setLayoutY(300);
		button.setPrefWidth(100);
		button.setPrefHeight(50);
		button.setOnAction(e -> primaryStage.setScene(monsterScene));

		titleLayout.add(canvas, 0, 0);
		titleLayout.add(button, 0, 1);

		titleScene = new Scene(titleLayout, 800, 600);
		primaryStage.setScene(titleScene);

		// Monster Select Scene
		GridPane monsterLayout = new GridPane();
		FlowPane flowLayout = new FlowPane();
		// GridPane in a FlowPane, because Gridpane is better but idk how to change background color
		flowLayout.setStyle("-fx-background-color: DAE6F3;");
		flowLayout.getChildren().add(monsterLayout);

		// Choose monster choiceboxes
		ChoiceBox<String> monsterBox1 = new ChoiceBox<>();
		monsterBox1.setLayoutX(100);
		monsterBox1.setLayoutY(100);
		monsterBox1.getItems().addAll("Monster 1", "Monster 2", "Monster 3", "Monster 4", "Monster 5");

		ChoiceBox<String> monsterBox2 = new ChoiceBox<>();
		monsterBox2.getItems().addAll("Monster 1", "Monster 2", "Monster 3", "Monster 4", "Monster 5");

		ChoiceBox<String> monsterBox3 = new ChoiceBox<>();
		monsterBox3.getItems().addAll("Monster 1", "Monster 2", "Monster 3", "Monster 4", "Monster 5");

		// Choose monster button (goes to next scene)
		Button chooseMonsterButton = new Button("Choose your Monster");
		chooseMonsterButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String monster1 = monsterBox1.getValue();
				String monster2 = monsterBox2.getValue();
				String monster3 = monsterBox3.getValue();
				System.out.println(monster1 + monster2 + monster3);

				primaryStage.setScene(battleScene);
			}
		});

		monsterLayout.add(monsterBox1, 0, 0);
		monsterLayout.add(monsterBox2, 0, 1);
		monsterLayout.add(monsterBox3, 0, 2);
		monsterLayout.add(chooseMonsterButton, 0, 3);
		monsterScene = new Scene(flowLayout, 400, 600);

		// Battle scene
		// Might need to change to a different layout type because of the health bars, depending on how we do it
		GridPane battleLayout = new GridPane();
		battleLayout.setPadding(new Insets(10, 10, 10, 10));
		battleLayout.setHgap(10);
		battleLayout.setVgap(10);

		// Not sure what the javaFX imageIcon is, so commented out for now
		//ImageIcon monsterIcon1;
		//ImageIcon monsterIcon2;

		Button attackButton = new Button("Attack");
		Button blockButton = new Button("Block");
		Button itemButton = new Button("Item");
		Button otherButton = new Button("Other (???)");

		// Pokemon HP bar, make sure to make a border around it and add the monster and level name, as well as a text showing the total HP left
		Rectangle healthBarBack1 = new Rectangle(250, 5);
		healthBarBack1.setStroke(Color.BLACK);
		healthBarBack1.setFill(Color.RED);
		Rectangle healthBar1 = new Rectangle(240, 5);
		healthBar1.setStroke(Color.BLACK);
		healthBar1.setFill(Color.GREEN);
		Label HPLabel = new Label("120/120");
		Label nameLabel = new Label("Pikachu");
		Label levelLabel = new Label("Lv. 42");
		
		// This is just a health bar ATM, haven't added the other things to this scene yet
		battleScene = new Scene(battleLayout, 800, 600);
		battleLayout.add(healthBarBack1, 0, 1);
		battleLayout.add(healthBar1, 0, 1);
		battleLayout.add(nameLabel, 0, 0);
		battleLayout.add(levelLabel, 1, 0);
		battleLayout.add(HPLabel, 0, 2);

		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}