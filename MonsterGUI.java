package Release1;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MonsterGUI extends Application implements EventHandler<ActionEvent>{

	Scene monsterPick,battle;
	private boolean playerOnePicked = false;
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
		
		//Logic engine = new Logic();
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //some check to see if the person has chosen their monsters yet
            	//if they have, create a new engine object with their monsters in
            	String[] choices = returnChoice(choiceBox, choiceBox1, choiceBox2);
            	ArrayList<Monster> player1Team = new ArrayList<Monster>();
            	ArrayList<Monster> player2Team = new ArrayList<Monster>();
            	
            	for(int i = 0; i < choices.length; i ++) {
            		if(choices[i] == null) {
            			Alert alert = new Alert(AlertType.INFORMATION);
            			alert.setTitle("Choose 3 Monsters");
            			alert.setContentText("You need to select all three monsters");
            			alert.showAndWait().ifPresent(rs -> {
            			    if (rs == ButtonType.OK) {
            			        //System.out.println("Pressed OK.");
            			    }
            			});
            		}
            		else {
            			if(!(playerOnePicked)) {
            				playerOnePicked = true;
            				Alert alert = new Alert(AlertType.INFORMATION);
                			alert.setTitle("Player 2 Turn");
                			alert.setContentText("Player 1 has chosen their monsters, now it is player 2's turn to pick");
                			alert.showAndWait().ifPresent(rs -> {
                			    if (rs == ButtonType.OK) {
                			        //System.out.println("Pressed OK.");
                			    }
                			});
                			for(int j = 0; j < choices.length; j ++) {
                				Monster monster = new Monster();
                				monster.monsterFactory(choices[j], "Monsterinfo.txt");
                				player1Team.add(monster);
                			}
            			}
            			else {
                			for(int k = 0; k < choices.length; k ++) {
                				Monster monster = new Monster();
                				monster.monsterFactory(choices[k], "Monsterinfo.txt");
                				player2Team.add(monster);
            			}
                		System.out.println("Let the battle begin!");
                		Logic engine = new Logic(player1Team, player2Team);
                		engine.startBattle();
            		}
            	}
            }
            }});
			

	}
	private void getChoice(ChoiceBox<String> choiceBox,ChoiceBox<String> choiceBox1,ChoiceBox<String> choiceBox2){
		String monster1 = choiceBox.getValue();
		String monster2 = choiceBox1.getValue();
		String monster3 = choiceBox2.getValue();
		System.out.println(monster1 + monster2 + monster3);
		
	}
	
	private String[] returnChoice(ChoiceBox<String> choiceBox,ChoiceBox<String> choiceBox1,ChoiceBox<String> choiceBox2){
		String monster1 = choiceBox.getValue();
		String monster2 = choiceBox1.getValue();
		String monster3 = choiceBox2.getValue();
		
		String[] toReturn = {monster1, monster2, monster3};
		return toReturn;
	}

	@Override
	public void handle(ActionEvent arg0) {
		System.out.println("ButtonListener?");

	}
	public static void main(String[] args) 
	{
		System.out.println("This is a test");
		launch(args);
	}
}