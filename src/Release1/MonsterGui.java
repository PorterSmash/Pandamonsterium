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
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MonsterGui extends Application implements EventHandler<ActionEvent>{

	Scene monsterPick,battle;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle( "PandeMonsterium" );

		Group root = new Group();
		Scene theScene = new Scene( root );
		stage.setScene( theScene );

		Canvas canvas = new Canvas(600, 400);

		root.getChildren().add( canvas );

		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill( Color.RED );
		gc.setStroke( Color.BLACK );
		gc.setLineWidth(2);
		Font theFont = Font.font( "Verdana", FontWeight.BOLD, 48 );
		gc.setFont( theFont );
		gc.fillText( "PandeMonsterium", 60, 50 );
		gc.strokeText( "PandeMonsterium", 60, 50 );

		Button button = new Button("Start");
		button.setLayoutX(250);
		button.setLayoutY(300);
		button.setPrefWidth(100);
		button.setPrefHeight(50);
		root.getChildren().add(button);
		button.setOnAction(e -> stage.setScene(monsterPick));

		FlowPane layout2 = new FlowPane();
//		layout2.setPadding(new Insets(5, 0, 5, 0));
//		layout2.setVgap(4);
//		layout2.setHgap(4);
//		layout2.setPrefWrapLength(170); // preferred width allows for two columns
		layout2.setStyle("-fx-background-color: DAE6F3;");

		monsterPick = new Scene(layout2, 600, 400);
		Button button1 = new Button("Begin Battle");
		button1.setPrefWidth(100);
		button1.setPrefHeight(100);
		button1.setLayoutX(400);
		button1.setLayoutY(300);
		layout2.getChildren().add(button1);
		Button chooseMonster = new Button("Choose your Monster");
		
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.setLayoutX(100);
		choiceBox.setLayoutY(100);
		choiceBox.getItems().addAll("monster 1","monster 2","monster 3", "monster 4","monster 5");
		ChoiceBox<String> choiceBox1 = new ChoiceBox<>();
		choiceBox1.getItems().addAll("monster 1","monster 2","monster 3", "monster 4","monster 5");

		ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
		choiceBox2.getItems().addAll("monster 1","monster 2","monster 3", "monster 4","monster 5");
		
		layout2.getChildren().addAll(choiceBox, choiceBox1,choiceBox2,chooseMonster);
		chooseMonster.setOnAction(e -> getChoice(choiceBox,choiceBox1,choiceBox2));
		
		stage.show();

	}
	private void getChoice(ChoiceBox<String> choiceBox,ChoiceBox<String> choiceBox1,ChoiceBox<String> choiceBox2){
		String monster1 = choiceBox.getValue();
		String monster2 = choiceBox1.getValue();
		String monster3 = choiceBox2.getValue();
		System.out.println(monster1 + monster2 + monster3);
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) 
	{
		launch(args);
	}
}
