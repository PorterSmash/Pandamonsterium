package Release1;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;

public class MonsterGUI extends Application {
	Scene titleScene, monsterScene,battleScene,pickPokemon;
	FlowPane switchMonPane;
	Stage stage;
	String test;
	Monster choose = null;
	Monster team1Chosen, team2Chosen = null;
	Rectangle healthBar1, healthBar2;
	Label HPLabel1, HPLabel2;
	Label nameLabel1, nameLabel2;
	Label levelLabel1, levelLabel2;
	Text display;
	ArrayList<Monster> player1Team = new ArrayList<Monster>();
	ArrayList<Monster> player2Team = new ArrayList<Monster>();
	Logic engine = new Logic();
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Pandemon(ster)ium");
		//the gridpane was functioning weird, so i did borderpane
		//i think that it is good for what we are doing
		BorderPane titleLayout = new BorderPane();
		//		titleLayout.setPadding(new Insets(10, 10, 10, 10));
		//		titleLayout.setHgap(10);
		//		titleLayout.setVgap(10);
		//Canvas canvas = new Canvas(600, 70);	


		// Title
		//		GraphicsContext gc = canvas.getGraphicsContext2D();
		//		gc.setFill( Color.RED );
		//		gc.setStroke( Color.BLACK );
		//		gc.setLineWidth(2);
		//		Font theFont = Font.font( "Verdana", FontWeight.BOLD, 48 );
		//		gc.setFont( theFont );
		//		gc.fillText( "PandeMonsterium", 60, 50 );
		//		gc.strokeText( "PandeMonsterium", 60, 50 );

		//instead of canvas, just do a text. less code?
		Image titleImage = new Image("titlePic.png");
		Text titleText = new Text("Pandemonsterium");
		titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		titleText.setFill(Color.RED);

		// Start button
		Button button = new Button("Start");
		//button.setLayoutX(500);
		//button.setLayoutY(500);
		button.setPrefWidth(100);
		button.setPrefHeight(50);
		button.setOnAction(e -> primaryStage.setScene(monsterScene));


		titleLayout.setTop(titleText);
		titleLayout.setBottom(button);

		ImageView titleImage1 = new ImageView(titleImage);
		titleLayout.setCenter(titleImage1);
		titleImage1.setFitHeight(300);
		titleImage1.setFitWidth(300);
		BorderPane.setMargin(button, new Insets(100, 50, 50, 350));
		BorderPane.setMargin(titleText, new Insets(10, 50, 50, 150));
		//titleLayout.add(titleText, 11, 0);
		//titleLayout.add(button, 1, 20);  
		//	titleLayout.add(canvas, 3, 2);

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

		Text whichTeam = new Text("Pick Team One Monsters");

		// Chooses the monster, updates elements in the battle scene accordingly
		chooseMonsterButton.setOnAction(new EventHandler<ActionEvent>() {
			private boolean playerOnePicked;
			private boolean playerTwoPicked;

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
						whichTeam.setText("Pick Team Two Monsters");

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
						playerTwoPicked = true;
					}
				}
				if(playerTwoPicked) {


					Monster m1 = new Monster();
					Monster	m2 = new Monster();
					Monster m3 = new Monster();


					m1.monsterFactory(monster1);
					m2.monsterFactory(monster2);
					m3.monsterFactory(monster3);
					team1Chosen = player1Team.get(0);
					team2Chosen = player2Team.get(0);
					choose = m1;

					int healthPercent1 = (250*team1Chosen.healthBattle)/team1Chosen.getHealthBattle();
					healthBar1.setWidth(healthPercent1);
					healthBar1.setHeight(5);
					healthBar1.setStroke(Color.BLACK);
					healthBar1.setFill(Color.GREEN);

					int healthPercent2 = (250*team2Chosen.healthBattle)/team2Chosen.getHealthBattle();
					healthBar2.setWidth(healthPercent2);
					healthBar2.setHeight(5);
					healthBar2.setStroke(Color.BLACK);
					healthBar2.setFill(Color.GREEN);

					HPLabel1.setText(team1Chosen.healthBattle + "/" + team1Chosen.maxHealthPoints);
					nameLabel1.setText("" + team1Chosen.getMonsterName());
					levelLabel1.setText("Lvl. " + team1Chosen.getLevel());

					HPLabel2.setText(team2Chosen.healthBattle + "/" + team2Chosen.maxHealthPoints);
					nameLabel2.setText("" + team2Chosen.getMonsterName());
					levelLabel2.setText("Lvl. " + team2Chosen.getLevel());

					engine.setTeamsAndMons(player1Team, player2Team, 0, 0); //sets the teams in the engine class to current teams
					engine.startBattle(); //sets monster 1 of both teams onField value to true

					primaryStage.setScene(battleScene);
				}
			}
		});
		// instantiating these here because, while the values can be updated later, they need to be objects to add them to the scene
		healthBar1 = new Rectangle();
		HPLabel1 = new Label();
		nameLabel1 = new Label();
		levelLabel1 = new Label();

		healthBar2 = new Rectangle();
		HPLabel2 = new Label();
		nameLabel2 = new Label();
		levelLabel2 = new Label();

		monsterLayout.add(monsterBox1, 0, 0);
		monsterLayout.add(monsterBox2, 0, 1);
		monsterLayout.add(monsterBox3, 0, 2);
		monsterLayout.add(chooseMonsterButton, 0, 3);
		monsterLayout.add(whichTeam,4,6);
		monsterScene = new Scene(flowLayout, 400, 600);

		// Battle scene
		// Might need to change to a different layout type because of the health bars, depending on how we do it
		GridPane battleLayout = new GridPane();
		battleLayout.setPadding(new Insets(10, 10, 10, 10));
		battleLayout.setHgap(10);
		battleLayout.setVgap(10);


		Button attackButton = new Button("Attack");
		Button heavyButton = new Button("Heavy Attack");
		Button healButton = new Button("Heal");
		Button otherButton = new Button("Other");

		// Pokemon HP bar, make sure to make a border around it and add the monster and level name, as well as a text showing the total HP left
		Rectangle healthBarBack1 = new Rectangle(250, 5);
		healthBarBack1.setStroke(Color.BLACK);
		healthBarBack1.setFill(Color.RED);

		Rectangle healthBarBack2 = new Rectangle(250, 5);
		healthBarBack2.setStroke(Color.BLACK);
		healthBarBack2.setFill(Color.RED);
		//in ratio to your actual health
		//healthBar1 = new Rectangle((250*choose.healthBattle)/choose.maxHealthPoints, 5);
		//simulates attacking not using attack yet though

		//displays comments
		display = new Text();
		display.setText("Welcome to the Pandemonsterium Arena");

		//for the fainted new stage
		stage = new Stage();
		switchMonPane = new FlowPane();
		switchMonPane.setPadding(new Insets(10, 10, 10, 10));
		switchMonPane.setHgap(10);
		switchMonPane.setVgap(10);
		pickPokemon = new Scene(switchMonPane);


		attackButton.setOnAction(new EventHandler<ActionEvent>() {
			//Logic engine = new Logic(player1Team, player2Team);
			@Override

			public void handle(ActionEvent arg0) {
				performMove(1);


				/*if (engine.playerTurn == 1) {
					team1Chosen.decreaseHealth(20);
				} else {
					team2Chosen.decreaseHealth(20);
				}*/
				//				performMove(1);
				//				player1Team = engine.getTeam1();
				//				player2Team = engine.getTeam2();

				//updates
				updateHpBars();
				//engine.changeTurn();
				//checks if it fainted/ needs to switch pokemon
				checkFainted();

			}
		});

		heavyButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				performMove(2);
				updateHpBars();
				checkFainted();
			}
		});

		healButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				performMove(3);
				updateHpBars();
				checkFainted();
			}
		});

		otherButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				performMove(4);
				updateHpBars();
				checkFainted();
			}
		});

		//images 
		//Image charizard = new Image("charizard.png");
		//Image nidoking = new Image("nidoking.png");
		//Image jolteon = new Image("jolteon.png");
		//Image raichu = new Image("raichu.png");
		//Image squirtle = new Image("squirtle.png");


		// This is just a health bar ATM, haven't added the other things to this scene yet
		battleScene = new Scene(battleLayout, 900, 600);


		battleLayout.add(healthBarBack1, 0, 1);
		battleLayout.add(healthBar1, 0, 1);

		battleLayout.add(healthBarBack2, 2, 1);
		battleLayout.add(healthBar2, 2, 1);
		//add another health bar above the other sprite
		battleLayout.add(nameLabel1, 0, 0);
		battleLayout.add(levelLabel1, 1, 0);
		battleLayout.add(HPLabel1, 0, 2);

		battleLayout.add(nameLabel2, 2, 0);
		battleLayout.add(levelLabel2, 3, 0);
		battleLayout.add(HPLabel2, 2, 2);
		//add the sprite images //Couldn't figure out how to do this - Alex
		battleLayout.add(attackButton, 9, 1);
		battleLayout.add(heavyButton, 8, 1);
		battleLayout.add(healButton, 8, 2);
		battleLayout.add(otherButton, 9, 2);
		//add listeners to each of these buttons
		//use the an int to decide each move the player chose
		//performMove(int)

		battleLayout.add(display, 0, 20);


		//ok so this shows the image but idk how to make it change based off the pokemon. 
		//bc the pokemon isnt chosen yet. maybe im being stupid bout it but LOL.
		//battleLayout.add(updateImages(player1Team, 0), 0, 12);
		//battleLayout.add(updateImages(player2Team, 0), 2, 12);

		//	battleLayout.add(new ImageView(charizard),0,12);	
		//battleLayout.add(new ImageView(nidoking),0,12);		
		//	battleLayout.add(new ImageView(jolteon),0,12);		
		//	battleLayout.add(new ImageView(raichu),0,12);
		//	battleLayout.add(new ImageView(squirtle),0,12);


		//battleLayout.add(new ImageView(charizard),2,12);

		//battleLayout.add(new ImageView(nidoking),2,12);
		//battleLayout.add(new ImageView(jolteon),2,12);
		//battleLayout.add(new ImageView(raichu),2,12);
		//	battleLayout.add(new ImageView(squirtle),2,12);


		primaryStage.show();

	}
	//checks if pokemon fainted
	public void checkFainted() {
		ArrayList<Monster> teamList;
		Monster onFieldMon;
		if (engine.getTurn() == 0) {
			teamList = player1Team;
			onFieldMon = team1Chosen;
		} else {
			teamList = player2Team;
			onFieldMon = team2Chosen;
		}
		if(onFieldMon.getHealthBattle() <= 0) {

			display.setText(onFieldMon.getMonsterName() + " Has Fainted");

			for (Monster mon : teamList) {
				if (mon.getHealthBattle() > 0) {
					Button but = new Button("Pick " + mon.getMonsterName());
					but.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent arg0) {
							Monster chosenMon = null;
							switch (engine.getTurn()) {
							case 0:
								team1Chosen = mon;
								chosenMon = team1Chosen;
								break;
							case 1:
								team2Chosen = mon;
								chosenMon = team2Chosen;
								break;
							}
							int switchedMonsterIndex = 1;
							for(int i = 0; i < teamList.size(); i++) {
								if(teamList.get(i) == chosenMon) {
									switchedMonsterIndex = i;
									//we may need to define a .equals() here, but it might still work
								}
							}
							updateHpBars();
							if(teamList == player1Team) {
							engine.setTeamsAndMons(player1Team, player2Team, switchedMonsterIndex, -1);
							}
							else {
								engine.setTeamsAndMons(player1Team, player2Team, -1, switchedMonsterIndex);
							}
							
							int xCoord = (teamList == player1Team) ? 0 : 2;
							
							//battleLayout.add(updateImages(teamList, switchedMonsterIndex), xCoord, 12 );
							
							
							//chooseMon.getImagePath set on screen
							//delete the other image path from being on screen?
							switchMonPane.getChildren().clear();
							stage.close();
						}
					});
					switchMonPane.getChildren().add(but);
				}

			}

			//engine.setTeamsAndMons(player1Team, player2Team);
			stage.setScene(pickPokemon);
			//	stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();


		}
	}

	public void updateHpBars() {
		healthBar1.setWidth((250*team1Chosen.healthBattle)/team1Chosen.maxHealthPoints);
		HPLabel1.setText(team1Chosen.healthBattle + "/" + team1Chosen.maxHealthPoints);
		levelLabel1.setText("Lvl. " + team1Chosen.getLevel());
		nameLabel1.setText("" + team1Chosen.getMonsterName());

		healthBar2.setWidth((250*team2Chosen.healthBattle)/team2Chosen.maxHealthPoints);
		HPLabel2.setText(team2Chosen.healthBattle + "/" + team2Chosen.maxHealthPoints);
		levelLabel2.setText("Lvl. " + team2Chosen.getLevel());
		nameLabel2.setText("" +team2Chosen.getMonsterName());

		System.out.println(team1Chosen.healthBattle);
		System.out.println(team1Chosen.getMonsterName());
	}


	public static void main(String[] args) {
		launch(args);
	}

	private void performMove(int moveChoice) {
		Monster onField = new Monster();
		engine.setTeams(this.player1Team, this.player2Team);

		ArrayList<Monster> team = new ArrayList<Monster>();
		if(engine.getTurn() == 0) {
			team = this.player1Team;
		}
		else {
			team = this.player2Team;
		}
		for(Monster mon : team) {
			if(mon.getOnField()) {
				onField = mon;
			}
		}
		System.out.println(onField.getMonsterName());
		System.out.println(onField.getMove1());
		switch(moveChoice) {
		case 1:
			engine.calculateDamage(onField.getMove1());
			if(team == player1Team)
				display.setText("Player 1 has attacked");
			else
				display.setText("Player 2 has attacked");
			break;
		case 2:
			engine.calculateDamage(onField.getMove2());
			if(team == player1Team)
				display.setText("Player 1 has attacked");
			else
				display.setText("Player 2 has attacked");
			break;
		case 3:
			engine.calculateDamage(onField.getMove3());
			if(team == player1Team)
				display.setText("Player 1 has healed");
			else
				display.setText("Player 2 has healed");
			break;
		case 4:
			engine.calculateDamage(onField.getMove4());
			if(team == player1Team)
				display.setText("Player 1 performed move");
			else
				display.setText("Player 2 performed move");
			break;
		}
		this.player1Team = engine.getTeam1();
		this.player2Team = engine.getTeam2();

	}
	private ImageView updateImages(ArrayList<Monster> playerTeam, int monsterIndex) {
		Image monsterSprite = new Image(playerTeam.get(monsterIndex).getMonsterImagePath());
		return new ImageView(monsterSprite);
	}
}