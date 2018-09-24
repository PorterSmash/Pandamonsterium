package Release1;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MonsterGUI extends Application {
	Scene titleScene, monsterScene,battleScene,pickPokemon;
	GridPane grid;
	Stage stage;
	String test;
	Monster choose,opt1,opt2,opt3 = null;
	Rectangle healthBar1;
	Label HPLabel;
	Label nameLabel;
	Label levelLabel;
	Text display;
	ArrayList<Monster> player1Team = new ArrayList<Monster>();
	ArrayList<Monster> player2Team = new ArrayList<Monster>();

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

		monsterBox1.getItems().addAll("Charizard", "Staryu", "Nidoking", "Squirtle", "Jolteon", "Raichu");

		ChoiceBox<String> monsterBox2 = new ChoiceBox<>();
		monsterBox2.getItems().addAll("Charizard", "Staryu", "Nidoking", "Squirtle", "Jolteon", "Raichu");

		ChoiceBox<String> monsterBox3 = new ChoiceBox<>();
		monsterBox3.getItems().addAll("Charizard", "Staryu", "Nidoking", "Squirtle", "Jolteon", "Raichu");

		// Choose monster button (goes to next scene)
		Button chooseMonsterButton = new Button("Choose your Monster");

		// Chooses the monster, updates elements in the battle scene accordingly
		chooseMonsterButton.setOnAction(new EventHandler<ActionEvent>() {
			private boolean playerOnePicked;

			@Override
			public void handle(ActionEvent arg0) {
				String monster1 = monsterBox1.getValue();
				String monster2 = monsterBox2.getValue();
				String monster3 = monsterBox3.getValue();

				String[] choices = {monster1, monster2, monster3};

				if(choices[0] == null || choices[1] == null || choices[2] == null) {
					System.out.println("You haven't chosen all monsters yet.");
				}
				else {
					if(!(playerOnePicked)) {
						for(int i = 0; i < 3; i ++) {
							Monster monster = new Monster();
							monster.monsterFactory(choices[i]);
							player1Team.add(monster);
						}
						playerOnePicked = true;
					}
					else {
						for(int i = 0; i < 3; i ++) {
							Monster monster = new Monster();
							monster.monsterFactory(choices[i]);
							player2Team.add(monster);
						}
					}
				}

				Monster m1 = new Monster();
				Monster	m2 = new Monster();
				Monster m3 = new Monster();


				m1.monsterFactory(monster1);
				m2.monsterFactory(monster2);
				m3.monsterFactory(monster3);
				choose = m1;
				opt1 = m1;
				opt2 = m2;
				opt3 = m3;
				int number = (250*choose.healthBattle)/choose.getHealthBattle();
				healthBar1.setWidth(number);
				healthBar1.setHeight(5);
				//healthBar1 = new Rectangle((250*choose.healthBattle)/choose.maxHealthPoints, 5);
				healthBar1.setStroke(Color.BLACK);
				healthBar1.setFill(Color.GREEN);
				HPLabel.setText(choose.healthBattle + "/" + choose.maxHealthPoints);
				//HPLabel = new Label(choose.healthBattle + "/" + choose.maxHealthPoints);
				nameLabel.setText("" +choose.getMonsterName());
				//nameLabel = new Label("" +choose.getMonsterName());
				levelLabel.setText("Lvl. " + choose.getLevel());
				//levelLabel = new Label("Lv. 42");

				System.out.println(monster1 + monster2 + monster3);
				System.out.println(player1Team.size());
				if(player1Team.size() == player2Team.size() && player1Team.size() == 3) {
					//Logic engine = new Logic(player1Team, player2Team);
					//engine.startBattle();
					//the engine needs to have a copy of the teams so it can do all the damage calculation,
					//the GUI then receives the teams and displays the data from their health and info
					//then the GUI needs to pass the teams back, refer to setTeams() in logic class
				}
				primaryStage.setScene(battleScene);


				primaryStage.setScene(battleScene);
			}
		});
		// instantiating these here because, while the values can be updated later, they need to be objects to add them to the scene
		healthBar1 = new Rectangle();
		HPLabel = new Label();
		nameLabel = new Label();
		levelLabel = new Label();

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
		Button heavyButton = new Button("Heavy Attack");
		Button healButton = new Button("Heal");
		Button otherButton = new Button("Other");

		// Pokemon HP bar, make sure to make a border around it and add the monster and level name, as well as a text showing the total HP left
		Rectangle healthBarBack1 = new Rectangle(250, 5);
		healthBarBack1.setStroke(Color.BLACK);
		healthBarBack1.setFill(Color.RED);
		//in ratio to your actual health
		//healthBar1 = new Rectangle((250*choose.healthBattle)/choose.maxHealthPoints, 5);
		//simulates attacking not using attack yet though

		//displays comments
		display = new Text();
		display.setText("hello");

		//for the fainted new stage
		stage = new Stage();
		grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);
		pickPokemon = new Scene(grid);


		attackButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//just testing the decrease health
				if(choose.getMonsterName().equals(opt1.getMonsterName())){
					//choose.decreaseHealth(30);
					opt1.decreaseHealth(30);
					System.out.println(opt1.getHealthBattle() );
				}else if(choose.getMonsterName().equals(opt2.getMonsterName())) {
					//choose.decreaseHealth(30);
					opt2.decreaseHealth(30);
				}else {
					//	choose.decreaseHealth(30);
					opt3.decreaseHealth(30);
				}

				//updates
				healthBar1.setWidth((250*choose.healthBattle)/choose.maxHealthPoints);
				HPLabel.setText(choose.healthBattle + "/" + choose.maxHealthPoints);
				//checks if it fainted/ needs to switch pokemon
				checkFainted();
			}
		});


		// This is just a health bar ATM, haven't added the other things to this scene yet
		battleScene = new Scene(battleLayout, 800, 600);
		battleLayout.add(healthBarBack1, 0, 1);
		battleLayout.add(healthBar1, 0, 1);
		//add another health bar above the other sprite
		battleLayout.add(nameLabel, 0, 0);
		battleLayout.add(levelLabel, 1, 0);
		battleLayout.add(HPLabel, 0, 2);
		battleLayout.add(attackButton, 9, 1);
		//add the sprite images //Couldn't figure out how to do this - Alex
		battleLayout.add(heavyButton, 8, 1);
		battleLayout.add(healButton, 8, 2);
		battleLayout.add(otherButton, 9, 2);
		//add listeners to each of these buttons
		//use the an int to decide each move the player chose
		//performMove(int)

		battleLayout.add(display, 4, 47);



		primaryStage.show();

	}
	//checks if pokemon fainted
	public void checkFainted() {
		if(healthBar1.getWidth()<=0) {
			
			display.setText(choose.getMonsterName() + " Has Fainted");
			Button b1 = new Button();
			Button b2 = new Button();
			Button b3 = new Button();
			//only adds a button if the monster can battle
			if(!choose.getMonsterName().equals(opt1.getMonsterName()) && opt1.getHealthBattle() > 0) {
				b3.setText("Pick " + opt1.getMonsterName());

				grid.add(b3, 2, 2);
				System.out.println(opt1.getHealthBattle() );
			}
			if(!choose.getMonsterName().equals(opt2.getMonsterName()) && opt2.getHealthBattle() > 0) {
				b1.setText("Pick " + opt2.getMonsterName());
				grid.add(b1, 2, 2);
				//System.out.println(opt2.getHealthBattle() );
			}
			if(!choose.getMonsterName().equals(opt3.getMonsterName()) && opt3.getHealthBattle() > 0) {
				b2.setText("Pick " + opt3.getMonsterName());
				grid.add(b2, 4, 4);
				//System.out.println(opt3.getHealthBattle() );
			}
			if(opt1.getHealthBattle() <= 0 && opt2.getHealthBattle() <= 0&& opt3.getHealthBattle() <=0) {
				display.setText("you lose");
				grid.getChildren().clear();
				stage.close();
			}

			b3.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					choose = opt1;
					buttonFaint();
				}
			});
			b1.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					choose = opt2;
					buttonFaint();
				}
			});
			b2.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					choose = opt3;
					buttonFaint();
				}
			});
			stage.setScene(pickPokemon);
			//	stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();

		}
	}
	//updates the battle stage.
	public void buttonFaint() {

		healthBar1.setWidth((250*choose.healthBattle)/choose.maxHealthPoints);
		HPLabel.setText(choose.healthBattle + "/" + choose.maxHealthPoints);
		nameLabel.setText("" +choose.getMonsterName());
		display.setText("Monster playing is " + choose.getMonsterName());
		levelLabel.setText("Lvl. " + choose.getLevel());
		grid.getChildren().clear();
		stage.close();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void performMove(int moveChoice) {
		Monster onField = new Monster();
		Logic engine = new Logic(this.player1Team, this.player2Team);
		for(Monster mon : player1Team) {
			if(mon.getOnField()) {
				onField = mon;
			}
		}
		switch(moveChoice) {
		case 1:
			engine.calculateDamage(onField.getMove2());
			break;
		case 2:
			engine.calculateDamage(onField.getMove2());
			break;
		case 3:
			engine.calculateDamage(onField.getMove2());
			break;
		case 4:
			engine.calculateDamage(onField.getMove2());
			break;
		}
	}
}