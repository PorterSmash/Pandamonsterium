package release.One;


import java.io.File;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**********************************************************************
 * This class displays the monster battle to the user.
 * It uses javafx elements to display a launch screen, monster
 * choice menu, and a battle screen. The screen updates as the
 * logic class does the bulk of the calculations.
 * @author Michelle Vu, Justin Kaukonen, Alex Porter
 *
 *********************************************************************/
public class MonsterGUI extends Application {
	/** Declares the three needed scenes.*/
	private Scene titleScene, monsterScene, battleScene, pickPokemon;

	/** Pane used for switching monsters. */
	private FlowPane switchMonPane;

	/** Stage to be launched. */
	private Stage stage;

	/** Main battle scene. */
	private Stage mainStage;

	/** List of all finished moves. */
	private Move[] storedMoves;

	/** Placeholder for the monster from each chosen team. */
	private Monster team1Chosen, team2Chosen = null;

	/** holds player ones move. */
	private int p1Move;

	/** holds the audio sound for punches*/
	private AudioClip punchSound = new AudioClip(new File("punch.wav").toURI().toString());

	/** holds the audio sound for healing*/
	private AudioClip healSound = new AudioClip(new File("heal.wav").toURI().toString());


	private static Media defaultBgm = new Media(new File("defaultBGM.mp3").toURI().toString());

	private static Media normBattleBgm = new Media(new File("normBattle.mp3").toURI().toString());
		
	private static Media bossBattleBgm = new Media(new File("bossBattle.wav").toURI().toString());
	
	private static MediaPlayer defaultPlayer,normPlayer,bossPlayer;

	/**Health bars displays. */
	private Rectangle healthBar1, healthBar2;

	/** Labels of each monsters health.*/
	private Label healthPointsLabel1, healthPointsLabel2;

	/** Labels of each monster's name.*/
	private Label nameLabel1, nameLabel2;

	/** Label for the monsters level.*/
	private Label levelLabel1, levelLabel2;

	/** Player one's team of monsters.*/
	private ArrayList<Monster> player1Team = new ArrayList<Monster>();

	/** Player two's team of monsters.*/
	private ArrayList<Monster> player2Team = new ArrayList<Monster>();

	/** An logic engine used for damage calculations.*/
	private Logic engine = new Logic();

	/** the four buttons the player can access.*/
	private Button attackButton, heavyButton, healButton, otherButton;

	/** Image for player 1s sprite.*/
	private ImageView player1Sprite;

	/** Image for player 2s sprite.*/
	private ImageView player2Sprite;

	/** Log to let the user know what just happened. */
	private TextArea battleLog;

	/** Announces what team. */
	private Text whichTeam;
	/******************************************************************
	 * Override method for javafx. Starts the game with the stage
	 * and runs the scenes.
	 * @param Stage the primary stage used for the game. 
	 *****************************************************************/
	@Override
	public void start(final Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Pandemon(ster)ium");
		GridPane startLayout = new GridPane();

		Image titleImage = new Image("titlePic.png");
		Text titleText = new Text("Pandemonsterium");
		titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		titleText.setFill(Color.RED);

		// Start button
		Button button = new Button("2-player game");
		button.setPrefWidth(100);
		button.setPrefHeight(50);
		button.setOnAction(e -> primaryStage.setScene(monsterScene));

		Button buttonCPU = new Button("Player Vs. CPU");
		buttonCPU.setPrefWidth(100);
		buttonCPU.setPrefHeight(50);
		buttonCPU.setOnAction(e -> primaryStage.setScene(monsterScene));

		ImageView titleImage1 = new ImageView(titleImage);
		titleImage1.setFitHeight(300);
		titleImage1.setFitWidth(300);
		
		//startLayout.setGridLinesVisible(true);

		VBox root = new VBox();
		root.getChildren().add(startLayout);
		root.setAlignment(Pos.CENTER);
		HBox root2 = new HBox();
		root2.getChildren().add(root);
		root2.setAlignment(Pos.CENTER);
		titleScene = new Scene(root2, 800, 650);
		startLayout.add(titleText, 0, 0);
		GridPane.setHalignment(titleText, HPos.CENTER);
		GridPane.setMargin(titleText, new Insets(10, 10, 10, 10));
		startLayout.add(titleImage1, 0, 1);
		GridPane.setHalignment(titleImage1, HPos.CENTER);
		GridPane.setMargin(titleImage1, new Insets(10, 10, 10, 10));
		startLayout.add(button, 0, 2);
		GridPane.setHalignment(button, HPos.CENTER);
		GridPane.setMargin(button, new Insets(10, 10, 10, 10));
		startLayout.add(buttonCPU, 0, 3);
		GridPane.setHalignment(buttonCPU, HPos.CENTER);
		GridPane.setMargin(buttonCPU, new Insets(10, 10, 10, 10));
		primaryStage.setScene(titleScene);

		backgroundMusic("default");

		// Monster Select Scene
		GridPane monsterLayout = new GridPane();

		// Choose monster choiceboxes
		ChoiceBox<String> monsterBox1 = new ChoiceBox<>();
		monsterBox1.setLayoutX(100);
		monsterBox1.setLayoutY(100);

		monsterBox1.getItems().addAll("Charizard", "Staryu", 
				"Nidoking", "Squirtle", "Jolteon", "Raichu");
		monsterBox1.getSelectionModel().selectFirst();

		ChoiceBox<String> monsterBox2 = new ChoiceBox<>();
		monsterBox2.getItems().addAll("Charizard", 
				"Staryu", "Nidoking", "Squirtle", 
				"Jolteon", "Raichu");
		monsterBox2.getSelectionModel().selectFirst();

		ChoiceBox<String> monsterBox3 = new ChoiceBox<>();
		monsterBox3.getItems().addAll("Charizard", 
				"Staryu", "Nidoking", "Squirtle", 
				"Jolteon", "Raichu");
		monsterBox3.getSelectionModel().selectFirst();

		// Choose monster button (goes to next scene)
		Button chooseMonsterButton = new Button("Choose your Monster");

		whichTeam = new Text("Pick Team One Monsters");

		// Chooses the monster, updates elements in the battle scene
		chooseMonsterButton.setOnAction(
				new EventHandler<ActionEvent>() {
					private boolean playerOnePicked;

					@Override
					public void handle(final ActionEvent arg0) {
						String monster1 = monsterBox1.getValue();
						String monster2 = monsterBox2.getValue();
						String monster3 = monsterBox3.getValue();

						String[] choices = {monster1, 
								monster2, monster3};

						if (choices[0] == null || choices[1] == null
								|| choices[2] == null) {
							System.out.println(
									"Choose all monsters");
						} else {
							if (!playerOnePicked) {
								whichTeam.setText(
										"Pick Team Two Monsters");
								for (int i = 0; i < 3; i++) {
									Monster monster = new Monster();
									monster.monsterFactory(choices[i]);
									player1Team.add(monster);
								}
								playerOnePicked = true;

							} else {
								for (int i = 0; i < 3; i++) {
									Monster monster = new Monster();
									monster.monsterFactory(choices[i]);
									player2Team.add(monster);
								}

								team1Chosen = player1Team.get(0);
								team2Chosen = player2Team.get(0);

								if (!attackButton.isDisabled()) {
									setUpHealthBars();
								}

								engine.setTeamsAndMons(player1Team, 
										player2Team, 0, 0);

								primaryStage.setScene(battleScene);
								updateBattleScene();
								backgroundMusic("normBattle");
								playerOnePicked = false;
								attackButton.setDisable(false);
								heavyButton.setDisable(false);
								healButton.setDisable(false);
								otherButton.setDisable(false);
							}
						}
					}
				});

		instantiateHealthAndLabels();

		monsterLayout.add(monsterBox1, 0, 0);
		GridPane.setHalignment(monsterBox1, HPos.CENTER);
		monsterLayout.add(monsterBox2, 0, 1);
		GridPane.setHalignment(monsterBox2, HPos.CENTER);
		monsterLayout.add(monsterBox3, 0, 2);
		GridPane.setHalignment(monsterBox3, HPos.CENTER);
		monsterLayout.add(chooseMonsterButton, 0, 3);
		GridPane.setHalignment(chooseMonsterButton, HPos.CENTER);
		monsterLayout.add(whichTeam, 0, 4);
		GridPane.setHalignment(whichTeam, HPos.CENTER);
		
		VBox root3 = new VBox();
		root3.getChildren().add(monsterLayout);
		root3.setAlignment(Pos.CENTER);
		HBox root4 = new HBox();
		root4.getChildren().add(root3);
		root4.setAlignment(Pos.CENTER);
		monsterScene = new Scene(root4, 250, 150);

		// Battle scene

		GridPane battleLayout = new GridPane();
		battleLayout.setPadding(new Insets(10, 10, 10, 10));
		battleLayout.setHgap(10);
		battleLayout.setVgap(10);


		attackButton = new Button("Attack");
		heavyButton = new Button("Heavy Attack");
		healButton = new Button("Heal");
		otherButton = new Button("Other");

		battleLog = new TextArea();

		// Pokemon HP bar
		Rectangle healthBarBack1 = new Rectangle(250, 5);
		healthBarBack1.setStroke(Color.BLACK);
		healthBarBack1.setFill(Color.RED);

		Rectangle healthBarBack2 = new Rectangle(250, 5);
		healthBarBack2.setStroke(Color.BLACK);
		healthBarBack2.setFill(Color.RED);


		//for the fainted new stage
		stage = new Stage();
		switchMonPane = new FlowPane();
		switchMonPane.setPadding(new Insets(10, 10, 10, 10));
		switchMonPane.setHgap(10);
		switchMonPane.setVgap(10);
		pickPokemon = new Scene(switchMonPane);

		storedMoves = new Move[2];

		attackButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent arg0) {
				buttonMove(1);
			}
		});

		heavyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent arg0) {
				buttonMove(2);
			}
		});

		healButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				buttonMove(3);
			}
		});

		otherButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				buttonMove(4);
			}
		});

		//images 
		Image placeHolder = new Image("titlePic.png");

		battleScene = new Scene(battleLayout, 770, 625);
		setUpBattleLayout(battleLayout, healthBarBack1, healthBarBack2);

		player1Sprite = new ImageView(placeHolder);
		player2Sprite = new ImageView(placeHolder);

		battleLayout.add(player1Sprite, 0, 12);	
		battleLayout.add(player2Sprite, 2, 12);		

		mainStage = primaryStage;
		primaryStage.show();

	}

	/******************************************************************
	 * Checks if a monster fainted (0 health).
	 *****************************************************************/
	public void checkFainted() {
		ArrayList<Monster> teamList;
		Monster onFieldMon;
		int teamNum = 0;
		if (engine.getTurn() == 0) {
			teamList = player1Team;
			teamNum = 1;
			onFieldMon = team1Chosen;
		} else {
			teamList = player2Team;
			onFieldMon = team2Chosen;
			teamNum = 2;
		}
		if (onFieldMon.getHealthBattle() <= 0) {
			engine.addBattleText(onFieldMon.getMonsterName() 
					+ " Has Fainted\n");
			updateBattleScene();


			boolean hasMonstersLeft = false;
			attackButton.setDisable(true);
			heavyButton.setDisable(true);
			healButton.setDisable(true);
			otherButton.setDisable(true);

			for (Monster mon : teamList) {
				if (mon.getHealthBattle() > 0) {
					hasMonstersLeft = true;
					Button but = new Button("Pick " 
							+ mon.getMonsterName());
					but.setOnAction(new 
							EventHandler<ActionEvent>() {
						public void handle(final ActionEvent arg0) {
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
							default:
								break;
							}
							if (chosenMon != null) {
								engine.addBattleText("Team " 
										+ (engine.getTurn() 
												+ 1) + " sent out " 
												+ chosenMon.getMonsterName() + "!\n");
							}
							int switchedMonsterIndex = 1;
							for (int i = 0; i < teamList.size(); i++) {
								if (teamList.get(i) == chosenMon) {
									switchedMonsterIndex = i;
								}
							}
							updateBattleScene();
							if (teamList == player1Team) {
								engine.setTeamsAndMons(player1Team,
										player2Team, switchedMonsterIndex, -1);

							} else {
								engine.setTeamsAndMons(player1Team,
										player2Team, -1, switchedMonsterIndex);
							}

							attackButton.setDisable(false);
							heavyButton.setDisable(false);
							healButton.setDisable(false);
							otherButton.setDisable(false);


							switchMonPane.getChildren().clear();
							stage.close();
						}
					});
					switchMonPane.getChildren().add(but);
				}

			}
			if (!hasMonstersLeft) {
				//game needs to end
				Alert alert = new Alert(AlertType.CONFIRMATION);

				int winner = 0;
				int loser = 0;
				boolean playerOneWin = false;

				for (Monster mon : player1Team) {
					if (mon.getHealthBattle() > 0) {
						playerOneWin = true;
					}
				}

				if (playerOneWin) { 
					winner = 1;
					loser = 2; 
				} else {
					winner = 2;
					loser = 1;
				}


				alert.setTitle("Someone has run out of Pokemon!");
				alert.setHeaderText("Player " + winner + " wins!");

				alert.setContentText("Player " + loser
						+ " has run out of Pokemon, so the match is over!");

				ButtonType restart = new ButtonType("Restart");
				ButtonType cancel = new ButtonType("Cancel");
				alert.getButtonTypes().clear();
				alert.getButtonTypes().addAll(restart, cancel);
				Optional<ButtonType> option = alert.showAndWait();



				//return to main menu, or exit program
				attackButton.setDisable(true);
				heavyButton.setDisable(true);
				healButton.setDisable(true);
				otherButton.setDisable(true);

				if (!option.isPresent()) { 
					// alert is exited, no button has been pressed.
					System.out.println("Quit");
					resetEverything();
					mainStage.setScene(titleScene);


				} else if (option.get() == restart) {
					//okay button is pressed
					System.out.println("OK");
					resetEverything();
					mainStage.setScene(titleScene);

				} else if (option.get() == cancel) {
					mainStage.close();
					stage.close();
				}
			} else {
				stage.setScene(pickPokemon);
				stage.show();
			}
		}
	}

	/******************************************************************
	 * Resets the monsters and everything else for a new game.
	 *****************************************************************/
	private void resetEverything() {
		// TODO update text files for levels
		whichTeam.setText(
				"Pick Team One Monsters");
		// reset monsters
		player1Team = new ArrayList<Monster>();
		player2Team = new ArrayList<Monster>();
		// reset engine
		engine = new Logic();
	}

	/******************************************************************
	 * Updates the HP bars for each monster.
	 *****************************************************************/
	public void updateBattleScene() {
		
		healthBar1.setWidth((250 
				* (double) team1Chosen.getHealthBattle())
				/ (double) team1Chosen.getMaxHealthPoints());
		healthPointsLabel1.setText(team1Chosen.getHealthBattle()
				+ "/" + team1Chosen.getMaxHealthPoints());
		levelLabel1.setText("Lvl. " + team1Chosen.getLevel());
		nameLabel1.setText("" + team1Chosen.getMonsterName());

		healthBar2.setWidth((250 
				* (double) team2Chosen.getHealthBattle())
				/ (double) team2Chosen.getMaxHealthPoints());
		healthPointsLabel2.setText(team2Chosen.getHealthBattle() 
				+ "/" + team2Chosen.getMaxHealthPoints());
		levelLabel2.setText("Lvl. " + team2Chosen.getLevel());
		nameLabel2.setText("" + team2Chosen.getMonsterName());

		player1Sprite.setImage(updateImages(team1Chosen));
		player2Sprite.setImage(updateImages(team2Chosen));

		battleLog.setText(engine.getBattleText());
	}

	/******************************************************************
	 * Launches the program.
	 * @param args String input, unused
	 *****************************************************************/
	public static void main(final String[] args) {
		launch(args);
	}

	/******************************************************************
	 * Updates the sprites on screen when one is replaced.
	 * @param monster Monster with image to replace current
	 * @return Image the image of the monster
	 *****************************************************************/
	private Image updateImages(final Monster monster) {
		return new Image(monster.getMonsterImagePath());
	}

	/******************************************************************
	 * Helper method creates health bars.
	 *****************************************************************/
	private void setUpHealthBars() {
		int healthPercent1 = (250 * team1Chosen.getHealthBattle())
				/ team1Chosen.getHealthBattle();
		healthBar1.setWidth(healthPercent1);
		healthBar1.setHeight(5);
		healthBar1.setStroke(Color.BLACK);
		healthBar1.setFill(Color.GREEN);

		int healthPercent2 = (250 * team2Chosen.getHealthBattle())
				/ team2Chosen.getHealthBattle();
		healthBar2.setWidth(healthPercent2);
		healthBar2.setHeight(5);
		healthBar2.setStroke(Color.BLACK);
		healthBar2.setFill(Color.GREEN);

		healthPointsLabel1.setText(team1Chosen.getHealthBattle() 
				+ "/" + team1Chosen.getMaxHealthPoints());
		nameLabel1.setText("" + team1Chosen.getMonsterName());
		levelLabel1.setText("Lvl. " + team1Chosen.getLevel());

		healthPointsLabel2.setText(team2Chosen.getHealthBattle() 
				+ "/" + team2Chosen.getMaxHealthPoints());
		nameLabel2.setText("" + team2Chosen.getMonsterName());
		levelLabel2.setText("Lvl. " + team2Chosen.getLevel());
	}

	/******************************************************************
	 * Helper method creates labels and health bar rectangles.
	 *****************************************************************/
	private void instantiateHealthAndLabels() {
		healthBar1 = new Rectangle();
		healthPointsLabel1 = new Label();
		nameLabel1 = new Label();
		levelLabel1 = new Label();

		healthBar2 = new Rectangle();
		healthPointsLabel2 = new Label();
		nameLabel2 = new Label();
		levelLabel2 = new Label();
	}

	/******************************************************************
	 * Sets up the battleLayout GridLayout.
	 * @param battleLayout Layout to set up
	 * @param healthBarBack1 Back of one health bar
	 * @param healthBarBack2 Back of second health bar
	 *****************************************************************/
	private void setUpBattleLayout(final GridPane battleLayout, 
			final Rectangle healthBarBack1, 
			final Rectangle healthBarBack2) {
		
		
		battleLayout.add(healthBarBack1, 0, 1);
		battleLayout.add(healthBar1, 0, 1);

		battleLayout.add(healthBarBack2, 2, 1);
		battleLayout.add(healthBar2, 2, 1);
		battleLayout.add(nameLabel1, 0, 0);
		battleLayout.add(levelLabel1, 1, 0);
		battleLayout.add(healthPointsLabel1, 0, 2);

		battleLayout.add(nameLabel2, 2, 0);
		battleLayout.add(levelLabel2, 3, 0);
		battleLayout.add(healthPointsLabel2, 2, 2);
		GridPane actionButtons = new GridPane();
		actionButtons.add(attackButton, 0, 0);
		GridPane.setHalignment(attackButton, HPos.CENTER);
		GridPane.setMargin(attackButton, new Insets(10, 10, 10, 10));
		attackButton.setPrefSize(100, 20);
		
		actionButtons.add(heavyButton, 1, 0);
		GridPane.setHalignment(heavyButton, HPos.CENTER);
		GridPane.setMargin(heavyButton, new Insets(10, 10, 10, 10));
		heavyButton.setPrefSize(100, 20);
		
		actionButtons.add(healButton, 0, 1);
		GridPane.setHalignment(healButton, HPos.CENTER);
		GridPane.setMargin(healButton, new Insets(10, 10, 10, 10));
		healButton.setPrefSize(100, 20);
		
		actionButtons.add(otherButton, 1, 1);
		GridPane.setHalignment(otherButton, HPos.CENTER);
		GridPane.setMargin(otherButton, new Insets(10, 10, 10, 10));
		otherButton.setPrefSize(100, 20);
		
		battleLayout.add(actionButtons, 2, 21);

		battleLayout.add(battleLog, 0, 21);

	}
	
	public static void backgroundMusic(String scene){
	
		if(scene.equals("default")) {
			defaultPlayer= new MediaPlayer(defaultBgm);
			//normPlayer.stop();
		     //bossPlayer.stop();
			defaultPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			defaultPlayer.play();
		}

		if(scene.equals("normBattle")) {
			normPlayer = new MediaPlayer(normBattleBgm);
			normPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			defaultPlayer.stop();
		//	bossPlayer.stop();
			
			normPlayer.play();
		}
		
		//		if(scene.equals("bossBattle")) {
		//			bossPlayer = new MediaPlayer(bossBattleBgm);
		//			normPlayer.stop();
		//			defaultPlayer.stop();
		//			bossPlayer.play();
		//		}
	}

	/******************************************************************
	 * Executes the move that is pressed depending how many
	 * moves are stored. Uses the move class
	 * @param move indicates which move has been pressed
	 *****************************************************************/
	private void buttonMove(final int move) {
		//checks if player one has gone. if they havent
		//it will store it into p1move.


		if (storedMoves[0] == null) {
			p1Move = move;
			if (move == 1)  {
				storedMoves[0] = team1Chosen.getMove1();
			} else if (move == 2) {
				storedMoves[0] = team1Chosen.getMove2();
			} else if (move == 3) {
				storedMoves[0] = team1Chosen.getMove3();
			} else {
				storedMoves[0] = team1Chosen.getMove4();
			}
		} else if (storedMoves[1] == null) {
			//if player one has a move already it will take 
			//move and give it to team 2
			if (move == 1)  {
				storedMoves[1] = team2Chosen.getMove1();
			}	else if (move == 2) {
				storedMoves[1] = team2Chosen.getMove2();
			}	else if (move == 3) {
				storedMoves[1] = team2Chosen.getMove3();
			}	else {
				storedMoves[1] = team2Chosen.getMove4();
			}

			//checks which team has a faster monster
			//who will attack first
			if (team1Chosen.getSpeedBattle() 
					> team2Chosen.getSpeedBattle()) {
				System.out.println("Player 1 attacked first. Speed: " 
						+ team1Chosen.getSpeedBattle() + " vs. " 
						+ team2Chosen.getSpeedBattle());

				engine.doMove(storedMoves[0], 1, p1Move);
				if(p1Move==3) {
					healSound.setPriority(0);
					healSound.play();
				}else {
					punchSound.setPriority(0);
					punchSound.play();
				}
				player1Team = engine.getTeam1();
				player2Team = engine.getTeam2();
				updateBattleScene();
				if (engine.getTurn() == 0) {
					engine.changeTurn();
				}
				checkFainted(); // should check team 2
				if (team2Chosen.getHealthBattle() > 0) {
					engine.doMove(storedMoves[1], 0, move);
					if(move==3) {
						healSound.setPriority(1);
						healSound.play();
					}else {
						punchSound.setPriority(1);
						punchSound.play();
					}
					player1Team = engine.getTeam1();
					player2Team = engine.getTeam2();
					updateBattleScene();

					engine.changeTurn();

					checkFainted(); // should check team 1 
				} else {
					engine.incTurnNum();
				}
				//clears stored moves for the next attack
				storedMoves[0] = null;
				storedMoves[1] = null;


			} else { // Team 2 is faster
				engine.doMove(storedMoves[1], 0, move);
				if(move==3) {
					healSound.setPriority(0);
					healSound.play();
				}else {
					punchSound.setPriority(0);
					punchSound.play();
				}
				player1Team = engine.getTeam1();
				player2Team = engine.getTeam2();
				updateBattleScene();
				if (engine.getTurn() == 1) {
					engine.changeTurn();
				}
				checkFainted(); // should check team 1
				if (team1Chosen.getHealthBattle() != 0) {
					engine.doMove(storedMoves[0], 1, 
							p1Move);
					if(p1Move==3) {
						healSound.setPriority(1);
						healSound.play();
					}else {
						punchSound.setPriority(1);
						punchSound.play();
					}
					player1Team = engine.getTeam1();
					player2Team = engine.getTeam2();
					updateBattleScene();
					engine.changeTurn();
					checkFainted();
				} else {
					engine.incTurnNum();
				}

				//clears stored moves for the next attack
				storedMoves[0] = null;
				storedMoves[1] = null;
			}
		}
	}
}