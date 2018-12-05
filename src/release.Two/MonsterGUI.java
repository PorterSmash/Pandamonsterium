package release.Two;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

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
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
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
import javafx.util.Duration;


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
	private Scene titleScene, monsterScene, battleScene, pickMonster,itemShop;

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
	private AudioClip punchSound = new 
			AudioClip(new File("punch.wav").toURI().toString());

	/** holds the audio sound for healing*/
	private AudioClip healSound = new 
			AudioClip(new File("heal.wav").toURI().toString());

	/** holds the audio sound for the background music on non battle screens*/
	private static Media defaultBgm = new 
			Media(new File("defaultBGM.mp3").toURI().toString());

	/** holds the audio sound for the battle music*/
	private static Media normBattleBgm = new 
			Media(new File("normBattle.mp3").toURI().toString());

	/** Holds the players for the 2 music medias we have*/
	private static MediaPlayer defaultPlayer,normPlayer;

	/**Health bars displays. */
	private Rectangle healthBar1, healthBar2;

	/** Labels of each monsters health.*/
	private Label healthPointsLabel1, healthPointsLabel2;

	/** Labels of each monster's name.*/
	private Label nameLabel1, nameLabel2;

	/** Player one's team of monsters.*/
	private ArrayList<Monster> player1Team = 
			new ArrayList<Monster>();

	/** Player two's team of monsters.*/
	private ArrayList<Monster> player2Team = 
			new ArrayList<Monster>();

	/** An logic engine used for damage calculations.*/
	private Logic engine = new Logic();

	/**Checks if it the starting bgm*/
	private static boolean firstBgm = true;

	/** the four buttons the player can access.*/
	private Button attackButton, heavyButton, 
	healButton, switchMonButton;

	/** Buttons for Items in shop*/
	private Button healthBoostBut,DodgerBut,silkScarfBut,
	critChanceBut,godModeBut,iContinue;
	
	private boolean hbBought,dBought,ssBought,ccBought,gmBought = false;

	/** Image for player 1s sprite.*/
	private ImageView player1Sprite;

	/** Image for player 2s sprite.*/
	private ImageView player2Sprite;

	private ImageView player1Effect,player2Effect,player1Heal,player2Heal;

	/** Log to let the user know what just happened. */
	private TextArea battleLog;

	/** Announces what team. */
	private Text whichTeam,inventoryLog;

	/** Determines if it is a CPU game. */
	private boolean isCPUGame;

	private int aiMove;
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
		Button button = new Button("2-Player Game");
		button.setPrefWidth(150);
		button.setPrefHeight(50);
		button.setOnAction(e -> primaryStage.setScene(monsterScene));

		Button buttonCPU = new Button("Player Vs. CPU");
		buttonCPU.setPrefWidth(150);
		buttonCPU.setPrefHeight(50);
		isCPUGame = false;
		buttonCPU.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent arg0) {
						isCPUGame = true;
						attackButton.setDisable(false);
						heavyButton.setDisable(false);
						healButton.setDisable(false);
						switchMonButton.setDisable(false);
						primaryStage.setScene(monsterScene);	
					}
				});

		Button buttonLoad = new Button("Load Game");
		buttonLoad.setPrefWidth(150);
		buttonLoad.setPrefHeight(50);
		buttonLoad.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						loadGame();
						setUpHealthBars();
						primaryStage.setScene(battleScene);
						updateBattleScene();
						attackButton.setDisable(false);
						heavyButton.setDisable(false);
						healButton.setDisable(false);
						switchMonButton.setDisable(false); 
					}
				});

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
		startLayout.add(buttonLoad, 0, 4);
		GridPane.setHalignment(buttonLoad, HPos.CENTER);
		GridPane.setMargin(buttonLoad, new Insets(10, 10, 10, 10));
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
						if (!isCPUGame) {
							if (choices[0] == null || choices[1] == null
									|| choices[2] == null) {
							//	System.out.println(
								//		"Choose all monsters");
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
									switchMonButton.setDisable(false);
								}
							}		
						} else {
							for (int i = 0; i < 3; i++) {
								Monster monster = new Monster();
								monster.monsterFactory(choices[i]);
								player1Team.add(monster);
							}

							team1Chosen = player1Team.get(0);
							
							engine.generateEnemyTeam(
									player1Team.get(0).getLevel()
									+ player1Team.get(1).getLevel()
									+ player1Team.get(2).getLevel());
							player2Team = engine.getTeam2();
							team2Chosen = player2Team.get(0);
						
							setUpHealthBars();
						
							engine.setTeamsAndMons(player1Team, 
									player2Team, 0, 0);

							primaryStage.setScene(battleScene);
							updateBattleScene();
							backgroundMusic("normBattle");
							playerOnePicked = false;
							attackButton.setDisable(false);
							heavyButton.setDisable(false);
							healButton.setDisable(false);
							switchMonButton.setDisable(false);
							
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
		switchMonButton = new Button("Switch Monster");

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
		pickMonster = new Scene(switchMonPane);

		storedMoves = new Move[2];



		GridPane itemGrid = new GridPane();
		itemGrid.setPadding(new Insets(10, 10, 10, 10));
		itemGrid.setHgap(10);
		itemGrid.setVgap(10);
		inventoryLog = new Text("Coins: " + engine.getCoins() +
				", Inventory: " + engine.getItemList().toString());
		updateInventory();
		itemShop = new Scene(itemGrid,500,500);

		createButtons(primaryStage);
		itemGrid.add(healthBoostBut, 0, 0);
		itemGrid.add(DodgerBut, 0, 1);
		itemGrid.add(silkScarfBut, 0, 2);
		itemGrid.add(critChanceBut, 0, 3);
		itemGrid.add(godModeBut, 0, 4);
		itemGrid.add(inventoryLog, 0, 10);
		itemGrid.add(iContinue, 0, 8);


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

		switchMonButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				buttonMove(4);
				//switchMonsters();
				updateBattleScene();
				//primaryStage.setScene(itemShop);
			}
		});

		//images 
		Image placeHolder = new Image("transparent.png");


		battleScene = new Scene(battleLayout, 925, 625);
		setUpBattleLayout(battleLayout, healthBarBack1, healthBarBack2);

		player1Sprite = new ImageView(placeHolder);
		player2Sprite = new ImageView(placeHolder);

		player1Effect = new ImageView(placeHolder);
		player2Effect = new ImageView(placeHolder);

		player1Heal = new ImageView(placeHolder);
		player2Heal = new ImageView(placeHolder);

		player1Effect.setVisible(false);
		player2Effect.setVisible(false);
		player1Heal.setVisible(false);
		player2Heal.setVisible(false);

		battleLayout.add(player1Sprite, 0, 12);	
		battleLayout.add(player2Sprite, 2, 12);	

		battleLayout.add(player1Effect, 0, 12);	
		battleLayout.add(player2Effect, 2, 12);	

		battleLayout.add(player1Heal, 0, 12);	
		battleLayout.add(player2Heal, 2, 12);	

		mainStage = primaryStage;
		primaryStage.show();

	}
	
	/******************************************************************
	 * Switches Monster upon request if they are still in game
	 *****************************************************************/
	private void switchMonsters() {

		if(isCPUGame || engine.getTurn() == 0) {
			ArrayList<String> monChoices1 = new ArrayList<String>();
			if(player1Team.get(0).getHealthBattle()!=0) {
				monChoices1.add(player1Team.get(0).getMonsterName()+" 1");
			}
			if (player1Team.get(1).getHealthBattle()!=0) {
				monChoices1.add(player1Team.get(1).getMonsterName()+" 2");
			}
			if (player1Team.get(2).getHealthBattle()!=0) {
				monChoices1.add(player1Team.get(2).getMonsterName()+" 3");
			}

			ChoiceDialog<String> dialog1 = new ChoiceDialog<>
			(monChoices1.get(0), monChoices1);
			dialog1.setTitle("Switch Monster");
			dialog1.setHeaderText("Choose one of your monsters to switch!	");
			dialog1.setContentText("");

			// Traditional way to get the response value.
			Optional<String> result = dialog1.showAndWait();
			if (result.isPresent()){
				Monster placeHolder = new Monster();
				int index = 1;
				for (Monster mon : player1Team) {
					if (result.get().equals(mon.getMonsterName()+" "+index)) {
						placeHolder = mon;
						break;
					}
					index++;
				}
				team1Chosen = placeHolder;
				int switchedMonsterIndex = 1;
				for (int i = 0; i < player1Team.size(); i++) {
					if (player1Team.get(i) == team1Chosen) {
						switchedMonsterIndex = i;
					}
				}
				updateBattleScene();
				engine.setTeamsAndMons(player1Team,
						player2Team, switchedMonsterIndex, -1);
			}
		}
		else if(engine.getTurn()==1) {
			ArrayList<String> monChoices1 = new ArrayList<String>();
			if(player2Team.get(0).getHealthBattle()!=0) {
				monChoices1.add(player2Team.get(0).getMonsterName()+" 1");
			}
			if (player2Team.get(1).getHealthBattle()!=0) {
				monChoices1.add(player2Team.get(1).getMonsterName()+" 2");
			}
			if (player2Team.get(2).getHealthBattle()!=0) {
				monChoices1.add(player2Team.get(2).getMonsterName()+" 3");
			}

			ChoiceDialog<String> dialog1 = new ChoiceDialog<>(
					monChoices1.get(0), monChoices1);
			dialog1.setTitle("Switch Monster");
			dialog1.setHeaderText("Choose one of your monsters to switch!	");
			dialog1.setContentText("");

			// Traditional way to get the response value.
			Optional<String> result = dialog1.showAndWait();
			if (result.isPresent()){
				Monster placeHolder = new Monster();
				int index =1;
				for (Monster mon : player2Team) {
					if (result.get().equals(mon.getMonsterName()+" "+index)) {
						placeHolder = mon;
						break;
					}
					index++;
				}
				team2Chosen = placeHolder;
				int switchedMonsterIndex = 1;
				for (int i = 0; i < player2Team.size(); i++) {
					if (player2Team.get(i) == team2Chosen) {
						switchedMonsterIndex = i;
					}
				}
				updateBattleScene();
				engine.setTeamsAndMons(player1Team,
						player2Team,-1, switchedMonsterIndex);
			}
		}

	}
	
	/**
	 * Updates the shop buttons to be enabled ot disabled
	 */
	private void updateShopButtons() {
		if(engine.getCoins() < 100 || hbBought)
			healthBoostBut.setDisable(true);
		else
			healthBoostBut.setDisable(false);
		if(engine.getCoins() < 300 || dBought)
			DodgerBut.setDisable(true);
		else
			DodgerBut.setDisable(false);
		if(engine.getCoins() < 400 || ssBought)
			silkScarfBut.setDisable(true);
		else
			silkScarfBut.setDisable(false);
		if(engine.getCoins() < 500|| ccBought)
			critChanceBut.setDisable(true);
		else
			critChanceBut.setDisable(false);
		if(engine.getCoins() < 10000 || gmBought)
			godModeBut.setDisable(true);
		else
			godModeBut.setDisable(false);
	}
	/******************************************************************
	 * Creates button for the item shop
	 * @param mainStage holds the stage to create a new stage
	 *****************************************************************/
	public void createButtons(Stage mainStage) {
		updateInventory();
		healthBoostBut = new Button ("Health Boost - 100 Coins");
		healthBoostBut.setTooltip(new Tooltip("Adds a 30 HP Boost "
				+ "to your monsters"));
		healthBoostBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				engine.itemShop(100);
				updateInventory();
				hbBought = true;
				updateShopButtons();
				engine.saveGame();
			}
		});

		DodgerBut = new Button ("Enemy Dodge - 300 Coins");
		DodgerBut.setTooltip(new Tooltip("Enemies miss twice as often"));
		DodgerBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				engine.itemShop(300);
				updateInventory();
				dBought = true;
				updateShopButtons();
				engine.saveGame();
			}
		});

		silkScarfBut = new Button ("Silk Scarf - 400 Coins");
		silkScarfBut.setTooltip(new Tooltip("Monster can only be"
				+ " killed after it reaches 1 HP"));
		silkScarfBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				engine.itemShop(400);
				updateInventory();
				ssBought = true;
				updateShopButtons();
				engine.saveGame();
			}
		});

		critChanceBut = new Button("Critical Chance Drop - 500 Coins");
		critChanceBut.setTooltip(new Tooltip("Enemy Critical Chance"
				+ " drops to zero"));
		critChanceBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				engine.itemShop(500);
				updateInventory();
				ccBought = true;
				updateShopButtons();
				engine.saveGame();
			}
		});

		godModeBut = new Button("God Mode - 10000 Coins");
		godModeBut.setTooltip(new Tooltip("All enemy hit chances "
				+ "are zero"));
		godModeBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				engine.itemShop(10000);
				updateInventory();
				gmBought = true;
				updateShopButtons();
				engine.saveGame();
			}
		});	
		iContinue = new Button("Continue");
		iContinue.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				loadGame();
				setUpHealthBars();
				updateBattleScene();
				backgroundMusic("normBattle");
				attackButton.setDisable(false);
				heavyButton.setDisable(false);
				healButton.setDisable(false);
				switchMonButton.setDisable(false);
				mainStage.setScene(battleScene);
				updateBattleScene();
			}
		});
		updateShopButtons();
		updateInventory();

	}
	
	/******************************************************************
	 * Updates the inventory log
	 *****************************************************************/
	public void updateInventory() {
		inventoryLog.setText("Coins: " + engine.getCoins() + 
				", Inventory: " + engine.getItemList().toString());
	}
	
	/******************************************************************
	 * Checks if a monster fainted (0 health).
	 *****************************************************************/
	public boolean checkFainted() {
		boolean fainted = false;
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
			if(isCPUGame && onFieldMon == team2Chosen) {
				engine.addBattleText("20 coins have been "
						+ "added to your account\n");
				engine.setCoins(engine.getCoins() + 20000);
				updateInventory();
		//		System.out.println(engine.getCoins());
			}
			updateBattleScene();


			boolean hasMonstersLeft = false;
			attackButton.setDisable(true);
			heavyButton.setDisable(true);
			healButton.setDisable(true);
			switchMonButton.setDisable(true);

			for (Monster mon : teamList) {
				if (mon.getHealthBattle() > 0) {
					hasMonstersLeft = true;
					if(isCPUGame && onFieldMon == team2Chosen) {
						replaceFaintedMonster(teamList, mon);
						break;
					} else {
						Button but = new Button("Pick " 
								+ mon.getMonsterName());
						but.setOnAction(new 
								EventHandler<ActionEvent>() {
							public void handle(final ActionEvent arg0) {
								replaceFaintedMonster(teamList, mon);
							}
						});
						switchMonPane.getChildren().add(but);
					}
				}
			}
			if (!hasMonstersLeft) {
				gameOverAlert();
				fainted = true;
				return fainted;
			} else {
				if(!isCPUGame || onFieldMon == team1Chosen) {
					stage.setScene(pickMonster);
					stage.show();
				}
			}
		}
		return fainted;
	}

	/******************************************************************
	 * Resets the monsters and everything else for a new game.
	 *****************************************************************/
	private void resetEverything() {
		// TODO update text files for levels
		backgroundMusic("default");
		whichTeam.setText(
				"Pick Team One Monsters");
		// reset monsters
		player1Team = new ArrayList<Monster>();
		player2Team = new ArrayList<Monster>();
		isCPUGame = false;
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
		
		nameLabel1.setText("" + team1Chosen.getMonsterName()) ;

		healthBar2.setWidth((250 
				* (double) team2Chosen.getHealthBattle())
				/ (double) team2Chosen.getMaxHealthPoints());
		healthPointsLabel2.setText(team2Chosen.getHealthBattle() 
				+ "/" + team2Chosen.getMaxHealthPoints());
	
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
	 * Updates the image effects sprites on screen
	 * @param effect which effect needs to pop up
	 * @return the image of the effect
	 *****************************************************************/
	private Image updateImagesEffects(final String effect) {
		return new Image(effect);
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
		nameLabel1.setText("" + team1Chosen.getMonsterName()) ;
		

		healthPointsLabel2.setText(team2Chosen.getHealthBattle() 
				+ "/" + team2Chosen.getMaxHealthPoints());
		nameLabel2.setText("" + team2Chosen.getMonsterName() );
	
	}

	/******************************************************************
	 * Helper method creates labels and health bar rectangles.
	 *****************************************************************/
	private void instantiateHealthAndLabels() {
		healthBar1 = new Rectangle();
		healthPointsLabel1 = new Label();
		nameLabel1 = new Label();
	

		healthBar2 = new Rectangle();
		healthPointsLabel2 = new Label();
		nameLabel2 = new Label();
	
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
		
		battleLayout.add(healthPointsLabel1, 0, 2);

		battleLayout.add(nameLabel2, 2, 0);
		
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

		actionButtons.add(switchMonButton, 1, 1);
		GridPane.setHalignment(switchMonButton, HPos.CENTER);
		GridPane.setMargin(switchMonButton, new Insets(10, 10, 10, 10));
		switchMonButton.setPrefSize(100, 20);

		battleLayout.add(actionButtons, 2, 21);

		battleLayout.add(battleLog, 0, 21);

	}
	
	/******************************************************************
	 * changes the background music depending on scene
	 * @param scene
	 *****************************************************************/
	public static void backgroundMusic(String scene){

		if(scene.equals("default")) {
			defaultPlayer= new MediaPlayer(defaultBgm);
			if(!firstBgm) {
				normPlayer.stop();
			}
			defaultPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			defaultPlayer.play();
			firstBgm=false;
		}

		if(scene.equals("normBattle")) {
			normPlayer = new MediaPlayer(normBattleBgm);
			normPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			defaultPlayer.stop();
			//	bossPlayer.stop();

			normPlayer.play();
		}
	}
	/******************************************************************
	 * Updates the audio sounds and the images for the game effects
	 * @param move int of the move that is being performed
	 * @param player which player
	 *****************************************************************/
	private void soundImagesUpdate(final int move,final int player) {
		Image player1Icon = updateImagesEffects("transparent.png");;
		Image player2Icon= updateImagesEffects("transparent.png");;
		boolean heal = false;
		if(move==3) {
			healSound.setPriority(0);
			healSound.play();
			heal = true;

			if(player == 1) {
				player1Heal.setImage(updateImagesEffects("heal.png"));
				player1Icon = updateImagesEffects("heal.png");
			}else if(player ==2) {
				player2Heal.setImage(updateImagesEffects("heal.png"));
				player2Icon = updateImagesEffects("heal.png");
			}
			player1Heal.setVisible(true);
			player2Heal.setVisible(true);
		}else if(move==1 || move==2){

			punchSound.setPriority(0);
			punchSound.play();
			if(player == 1) {
				player2Effect.setImage(updateImagesEffects("attack.png"));
				player2Icon = updateImagesEffects("attack.png");
			}else if(player ==2) {
				player1Effect.setImage(updateImagesEffects("attack.png"));
				player1Icon = updateImagesEffects("attack.png");
			}
			player1Effect.setVisible(true);
			player2Effect.setVisible(true);

		}

		if(heal) {
			if(player==1) {
				Timeline Timeline1 = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(
								player1Heal.imageProperty(),player1Icon)),
						new KeyFrame(Duration.seconds(1), new KeyValue(
								player1Heal.imageProperty(),null))
						);
				Timeline1.play();
			}else if(player ==2) {
				Timeline Timeline2 = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(
								player2Heal.imageProperty(),player2Icon)),
						new KeyFrame(Duration.seconds(1), new KeyValue(
								player2Heal.imageProperty(),null))
						);
				Timeline2.play();
			}
		}else {
			if(player==2) {
				Timeline Timeline1 = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(
								player1Effect.imageProperty(),player1Icon)),
						new KeyFrame(Duration.seconds(1), new KeyValue(
								player1Effect.imageProperty(),null))
						);
				Timeline1.play();
			}

			if(player==1) {
				Timeline Timeline2 = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(
								player2Effect.imageProperty(),player2Icon)),
						new KeyFrame(Duration.seconds(1), new KeyValue(
								player2Effect.imageProperty(),null))
						);
				Timeline2.play();
			}
		}
	}


	/******************************************************************
	 * Executes the move that is pressed depending how many
	 * moves are stored. Uses the move class
	 * @param move indicates which move has been pressed
	 *****************************************************************/
	private void buttonMove(int move) {
		//checks if player one has gone. if they havent
		//it will store it into p1move.

		if (!isCPUGame) {
			if (storedMoves[0] == null) {
				p1Move = move;
				if (move == 1)  {
					storedMoves[0] = team1Chosen.getMove1();
				} else if (move == 2) {
					storedMoves[0] = team1Chosen.getMove2();
				} else if (move == 3) {
					storedMoves[0] = team1Chosen.getMove3();
				} else {
					storedMoves[0]= team1Chosen.getMove4();
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
					storedMoves[1]= team2Chosen.getMove4();
				}

		
				//checks which team has a faster monster
				//who will attack first
				if (team1Chosen.getSpeedBattle() 
						> team2Chosen.getSpeedBattle()) {
					if(p1Move !=4)
						engine.doMove(storedMoves[0], 1, p1Move);
					else 
						switchMonsters();

					soundImagesUpdate(p1Move,1);
					player1Team = engine.getTeam1();
					player2Team = engine.getTeam2();
					updateBattleScene();
					if (engine.getTurn() == 0) {
						engine.changeTurn();
					}
					boolean fainted =checkFainted(); // should check team 2
					if (team2Chosen.getHealthBattle() > 0&&!fainted) {
						if(move!=4)
							engine.doMove(storedMoves[1], 0, move);
						else
							switchMonsters();
						soundImagesUpdate(move,2);
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
					if(move!=4)
						engine.doMove(storedMoves[1], 0, move);
					else
						switchMonsters();
					soundImagesUpdate(move,2);
					player1Team = engine.getTeam1();
					player2Team = engine.getTeam2();
					updateBattleScene();
					if (engine.getTurn() == 1) {
						engine.changeTurn();
					}
					boolean fainted = checkFainted(); // should check team 1
					if (team1Chosen.getHealthBattle() > 0 !=fainted) {
						
						if(p1Move!=4)
							engine.doMove(storedMoves[0], 1, 
									p1Move);
						else
							switchMonsters();
						soundImagesUpdate(p1Move,1);
						player1Effect.setVisible(true);
						player2Effect.setVisible(true);
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
		} else { // This IS a CPU game, add P1's move, and randomly select P2's
		
			p1Move = move;
			if (move == 1)  {
				storedMoves[0] = team1Chosen.getMove1();
			} else if (move == 2) {
				storedMoves[0] = team1Chosen.getMove2();
			} else if (move == 3) {
				storedMoves[0] = team1Chosen.getMove3();
			} else {
				switchMonsters();
			}

			storedMoves[1] = monsterSmash(team2Chosen);
			
			//checks which team has a faster monster
			//who will attack first
			if (team1Chosen.getSpeedBattle() 
					> team2Chosen.getSpeedBattle()) {
		
				if(p1Move!=4)
					engine.doMove(storedMoves[0], 1, p1Move);
				soundImagesUpdate(p1Move,1);
				player1Team = engine.getTeam1();
				player2Team = engine.getTeam2();
				updateBattleScene();
				if (engine.getTurn() == 0) {
					engine.changeTurn();
				}
				boolean fainted = checkFainted(); // should check team 2
				if (team2Chosen.getHealthBattle() > 0 &&!fainted) {
					if(aiMove!=4)
						engine.doMove(storedMoves[1], 0, aiMove);
					soundImagesUpdate(aiMove,2);
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
				if(aiMove!=4)
					engine.doMove(storedMoves[1], 0, aiMove);
				soundImagesUpdate(aiMove,2);
				player1Team = engine.getTeam1();
				player2Team = engine.getTeam2();
				updateBattleScene();
				if (engine.getTurn() == 1) {
					engine.changeTurn();
				}
				boolean faint = checkFainted(); // should check team 1
				if (team1Chosen.getHealthBattle() > 0 && !faint) {
					if(p1Move!=4)
						engine.doMove(storedMoves[0], 1, 
								p1Move);
					soundImagesUpdate(p1Move,1);
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
	/******************************************************************
	 * Picks a monster move for the enemy AI
	 * @param enemyMon which monster is battling
	 * @return move that it will perform
	 *****************************************************************/
	private Move monsterSmash(Monster enemyMon) {
		Random rnd = new Random();
		int moveChoice = rnd.nextInt(100);

		aiMove=0;
		if (moveChoice < 39) {
			aiMove = 1;
			return enemyMon.getMove1();
		} else if (moveChoice < 79) {
			aiMove =2;
			return enemyMon.getMove2();
		} else {
			aiMove=3;
			return enemyMon.getMove3();
		}
	}
	/******************************************************************
	 * Replaces any fainted monsters
	 * @param teamList team of monsters
	 * @param mon monster that needs to be replaced
	 *****************************************************************/
	private void replaceFaintedMonster(ArrayList<Monster> teamList, Monster mon) {
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
		switchMonButton.setDisable(false);


		switchMonPane.getChildren().clear();
		stage.close();
	}
	
	/******************************************************************
	 * Creates game over alert or the continue to next round alert
	 * which will trigger a level up scene
	 *****************************************************************/
	private void gameOverAlert() {
		//game needs to end
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
		if(isCPUGame && playerOneWin) {
			engine.setCoins(engine.getCoins() + 100);
			updateInventory();


			ArrayList<String> monChoices = new ArrayList<String>();
			monChoices.add(player1Team.get(0).getMonsterName());
			if (!monChoices.get(0).equals(
					player1Team.get(1).getMonsterName())) {
				monChoices.add(player1Team.get(1).getMonsterName());
			}
			if (monChoices.size() == 2 && 
					!player1Team.get(2).getMonsterName().
					equals(monChoices.get(0)) && 
					!player1Team.get(2).getMonsterName().
					equals(monChoices.get(1))) {
				monChoices.add(player1Team.get(2).getMonsterName());
			}

			ChoiceDialog<String> dialog = new ChoiceDialog<>(
					monChoices.get(0), monChoices);
			dialog.setTitle("LEVEL UP!");
			dialog.setHeaderText("Choose one of your "
					+ "monster types to level up!	");
			dialog.setContentText("");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				Monster placeHolder = new Monster();
				for (Monster mon : player1Team) {
					if (result.get().equals(mon.getMonsterName())) {
						placeHolder = mon;
					}
				}
				placeHolder.attemptUpdateLevel();
			}
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		String saveFile = engine.toString() + ".txt";
		if(isCPUGame) {
		for(Monster mon : player1Team) {
			mon.resetStats(); //So no dead monsters are saved
		}
		
		engine.setTeams(player1Team, player2Team); 
		//update the teams for the engine
		engine.saveGame();
		}

		alert.setTitle("Someone has run out of Pokemon!");
		alert.setHeaderText("Player " + winner + " wins!");

		alert.setContentText("Player " + loser
				+ " has run out of Pokemon, so the match is over!\n"
				+ "(Game has been saved automatically in the file " 
				+ saveFile 
				+ ")");
		//return to main menu, or exit program
				
		ButtonType restart = new ButtonType("Restart");
		ButtonType cancel = new ButtonType("Cancel");
		ButtonType bContinue = new ButtonType("Continue");

		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(restart, cancel);

		if(playerOneWin&&isCPUGame) {
			alert.getButtonTypes().add(bContinue);
		}

		Optional<ButtonType> option = alert.showAndWait();
		attackButton.setDisable(true);
		heavyButton.setDisable(true);
		healButton.setDisable(true);
		switchMonButton.setDisable(true);
		
		if(playerOneWin&&isCPUGame) {
			if(option.get() == bContinue) {
				updateShopButtons();
				mainStage.setScene(itemShop);
			}
		}

	 if (option.get() == restart) {
			//okay button is pressed
		
			resetEverything();
			mainStage.setScene(titleScene);

		}
		else if (option.get() == cancel) {
			mainStage.close();
			stage.close();
		}
	}
	
	/******************************************************************
	 * Performs the loading for the game.
	 *****************************************************************/
	private void loadGame(){
		isCPUGame = true;
	
		TextInputDialog fileInput = new TextInputDialog(engine.toString());
		fileInput.setTitle("Load Game");
		fileInput.setHeaderText("Enter save file information");
		fileInput.setContentText(
				"Enter the name of the save file you wish to load from:");

		Optional<String> result = fileInput.showAndWait();
		
		result.ifPresent(fileName -> 
		engine.loadGame(fileName + ".txt"));
		
		player1Team = engine.getTeam1();
		engine.healTeam();
		engine.generateEnemyTeam(player1Team.get(0).getLevel() 
				+ player1Team.get(1).getLevel() 
				+ player1Team.get(2).getLevel());
		
		player2Team = engine.getTeam2();

		team1Chosen = player1Team.get(0);
		team2Chosen = player2Team.get(0);	
		engine.setTeamsAndMons(player1Team, 
				player2Team, 0, 0);
		updateInventory();
	}
}