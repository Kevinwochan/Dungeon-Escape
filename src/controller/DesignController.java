package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.text.html.ImageView;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import project.Core;
import project.dungeon.Dungeon;
import project.dungeon.actors.Actor;
import project.dungeon.actors.npcs.Coward;
import project.dungeon.actors.npcs.Hound;
import project.dungeon.actors.npcs.Hunter;
import project.dungeon.actors.npcs.Strategist;
import project.dungeon.actors.player.Player;
import project.dungeon.dungeonObjects.Boulder;
import project.dungeon.dungeonObjects.DungeonObject;
import project.dungeon.dungeonObjects.items.Bomb;
import project.dungeon.dungeonObjects.items.Key;
import project.dungeon.dungeonObjects.items.Treasure;
import project.dungeon.dungeonObjects.items.potions.HoverPotion;
import project.dungeon.dungeonObjects.items.potions.InvincibilityPotion;
import project.dungeon.dungeonObjects.items.weapons.Arrow;
import project.dungeon.dungeonObjects.items.weapons.Sword;
import project.dungeon.dungeonoperations.MapCoordinate;
import project.dungeon.puzzles.Puzzle;
import project.dungeon.tiles.Door;
import project.dungeon.tiles.Exit;
import project.dungeon.tiles.MazeTile;
import project.dungeon.tiles.Pit;
import project.dungeon.tiles.Switch;
import project.dungeon.tiles.Wall;

public class DesignController extends Controller {
	Image tileset;
	Core core;
	Dungeon dungeon;
	Puzzle puzzle;
	MazeTile[][] maze;
	ImageView image;
	Stage stage;
	boolean erase;
	String str;
	boolean key;
	MazeTile tempTile;
	Key tempKey;
	DungeonObject tempObj;
	boolean playerPlaced;
	
	
	@FXML
	private GridPane gridMap;
	
	public DesignController (Stage s, Core core) throws IOException {
		super(s);
		this.core = core;
		gridMap = new GridPane();
		stage = s;
		//maze = new MazeTile();
		image = null;
		dungeon = new Dungeon(new Puzzle(9));
		puzzle = dungeon.getPuzzle();
		maze = puzzle.getMap();
		erase = false;
		playerPlaced = true;
	}
	
	@FXML
	public void initialise(){
		refresh();
	}
	
	@FXML
	public void selWall() {
			image = new ImageView(new Image("assets/Wall.png"));
			try {
				tempTile = new Wall();
			} catch (IOException e) {

			}
			tempObj = null;
	}
	
	@FXML
	public void selTreasure() {
			image = new ImageView(new Image("assets/gold.png"));
			try {
				tempObj = new Treasure();
			} catch (IOException e) {
			}
			tempTile = null;
	}
	
	@FXML
	public void selClosed() {
		image = new ImageView(new Image("assets/closed-door.png"));
		if(key && tempKey != null) {
			try {
				tempTile = new Door(tempKey);
				key = false;
				tempKey = null;
			} catch (IOException e) {

			}
		}
		else tempTile = null;
		tempObj = null;
		
	}
	
	@FXML
	public void selExit() {
			image = new ImageView(new Image("assets/exit.png"));
			try {
				tempTile = new Exit();
			} catch (IOException e) {
			}
			tempObj = null;
	}
	
	@FXML
	public void selBoulder() {
			image = new ImageView(new Image("assets/boulder.png"));
			tempObj = new Boulder();
			tempTile = null;
	}
	
	@FXML
	public void selBomb() {
			image = new ImageView(new Image("assets/bomb_unlit.png"));
			try {
				tempObj = new Bomb();
			} catch (IOException e) {
			}
			tempTile = null;
	}
	
	@FXML
	public void selKey() {
			image = new ImageView(new Image("assets/key.png"));
			if(!key) {
				try {
					tempKey = new Key();
					//key = true;
				} catch (IOException e) {
				}
			}
			tempObj = null;
			tempTile = null;
	}
	
	@FXML
	public void selShaft() {
			image = new ImageView(new Image("assets/pit.png"));
			try {
				tempTile = new Pit();
			} catch (IOException e) {
			}
			tempObj = null;
	}
	
	@FXML
	public void selSwitch() {
			image = new ImageView(new Image("assets/switch.png"));
			try {
				tempTile = new Switch();
			} catch (IOException e) {
			}
			tempObj = null;
	}
	
	@FXML
	public void selArrow() {
			image = new ImageView(new Image("assets/arrow.png"));
			try {
				tempObj = new Arrow();
			} catch (IOException e) {
			}
			tempTile = null;
	}
	
	@FXML
	public void selSword() {
			image = new ImageView(new Image("assets/sword.png"));
			try {
				tempObj = new Sword();
			} catch (IOException e) {
			}
			tempTile = null;
	}
	
	@FXML
	public void selHuman() {
			image = new ImageView(new Image("assets/player.png"));
			if(!playerPlaced) {
				try {
					tempObj = new Player();
				} catch (IOException e) {
				}
			}
			
			tempTile = null;
	}
	
	@FXML
	public void selHound() {
			image = new ImageView(new Image("assets/hound.png"));
			tempObj = new Hound();
			tempTile = null;
	}
	
	@FXML
	public void selGnome() {
			image = new ImageView(new Image("assets/gnome.png"));
			tempObj = new Coward();
			tempTile = null;
	}
	
	@FXML
	public void selStrategist() {
			image = new ImageView(new Image("assets/strategist.png"));
			tempObj = new Strategist();
			tempTile = null;
	}
	
	@FXML
	public void selHunter() {
			image = new ImageView(new Image("assets/hunter.png"));
			tempObj = new Hunter();
			tempTile = null;
	}
	
	@FXML
	public void selInvi() {
			image = new ImageView(new Image("assets/invincibility-potion.png"));
			try {
				tempObj = new InvincibilityPotion();
			} catch (IOException e) {
			}
			tempTile = null;
	}
	
	@FXML
	public void selFly() {
			image = new ImageView(new Image("assets/hover-potion.png"));
			try {
				tempObj = new HoverPotion();
			} catch (IOException e) {
			}
			tempTile = null;
	}
	
	@FXML
	public void selEraser() {
			erase = true;
			image = null;
	}
	
	@FXML
	public void placeObject(MouseEvent e) {
		//if no object was selected, then return null;
		//else if(image != null) {		
			//Node source = (Node) e.getSource() ;
			//int col = gridMap.get
			Node source = (Node) e.getTarget();
			if (source != gridMap) {
		        Node parent;
		        while ((parent = source.getParent()) != gridMap) {
		            source = parent;
		        }
		    }
			Integer colIndex = gridMap.getColumnIndex(source);
			if (colIndex == null) colIndex = (Integer) 0;
			Integer rowIndex = gridMap.getRowIndex(source);
			if (rowIndex == null) rowIndex = (Integer) 0;
				
//			if(erase) {
//				//gridMap.getChildren().remove(image);
//				ObservableList<Node> childrens = gridMap.getChildren();
//				for(Node node : childrens) {
//				    if(node instanceof ImageView && gridMap.getRowIndex(node) == rowIndex && gridMap.getColumnIndex(node) == colIndex) {
//				        ImageView imageView= (ImageView)node; // use what you want to remove
//				        gridMap.getChildren().remove(imageView);
//						erase = false;
//				        break;
//				    }
//				  } 
//				//gridMap.getChildren().remove((ImageView)
//			}
	
			if(tempTile != null) {
				puzzle.setTile(new MapCoordinate(colIndex.intValue(), rowIndex.intValue()), tempTile);
			}
			
			else if(tempObj != null) {
				if(tempObj instanceof Player) {
					dungeon.setPlayer((Player)tempObj, colIndex.intValue(), rowIndex.intValue());
					playerPlaced = true;
				}
				else if(tempObj instanceof Actor) {
					Actor a = (Actor)tempObj;
					DungeonObject temp = null;
					if (a.getName() == "Hound") {
						try {
							temp = new Hound(new MapCoordinate(colIndex.intValue(), rowIndex.intValue()));
						} catch (IOException e1) {
						}
					}
					else if (a.getName() == "Coward") {
						try {
							temp = new Coward(new MapCoordinate(colIndex.intValue(), rowIndex.intValue()));
						} catch (IOException e1) {
						}
					}
					else if (a.getName() == "Hunter") {
							temp = new Hunter(new MapCoordinate(colIndex.intValue(), rowIndex.intValue()));
					}
					else if (a.getName() == "strategist") {
						try {
							temp = new Strategist(new MapCoordinate(colIndex.intValue(), rowIndex.intValue()));
						} catch (IOException e1) {
						}
					}
				puzzle.placeObject(temp, new MapCoordinate(colIndex.intValue(), rowIndex.intValue()));
				dungeon.attach((Actor)temp);
				//puzzle.placeObject(tempObj, new MapCoordinate(colIndex.intValue(), rowIndex.intValue()));
				}
				else puzzle.addObject(tempObj, new MapCoordinate(colIndex.intValue(), rowIndex.intValue()));
			}
			
			else if (tempKey != null && !key) {
				puzzle.addObject(tempKey, new MapCoordinate(colIndex.intValue(), rowIndex.intValue()));
				key = true;
			}
			else {};
			
			tempTile = null;
			tempObj = null;
			
			refresh();
//			if (image != null) {
//				//image.
//				image.setFitWidth(40);
//				image.setFitHeight(40);
//				//image.fitWidthProperty().bind(stage.widthProperty()); 
//				image.setOnMouseClicked(MOUSE_CLICKED -> {
//				    gridMap.getChildren().remove(image);
//				});
//				//}
//				gridMap.add(image, colIndex.intValue(), rowIndex.intValue());
//				//create object
//				Object
//				image = null;
//				//e.consume();
			//}
	}
	
	@FXML
	public void finishButton(ActionEvent event) throws IOException {
		core.save(dungeon);
		Screen screen = new Screen(super.getS(), "", "view/Welcome.fxml");
		WelcomeController controller = new WelcomeController(super.getS());
		screen.start(controller);
	}
	
	public void refresh() {
		for(int i =0; i < maze.length; i++) {
			for(int j =0; j < maze[i].length; j++  ) {
				MapCoordinate co = new MapCoordinate(i, j);
		    	MazeTile tile = puzzle.getTile(co);
				if(tile.getSprite() != null) {
					System.out.println("Its a " + tile.getSprite() + " at " + i + j);
					Image img = new Image(tile.getSprite());
			    	ImageView imgView = new ImageView(img);
			    	imgView.setFitHeight(44);
			    	imgView.setFitWidth(45);
			    	gridMap.add(imgView, i,  j);
				}else if(tile.hasObjects()) {
					for (DungeonObject o: tile.getObjects() ) {
						if (o.getSprite() != null) {
							System.out.println("Object a " + tile.getTileName()  + " "+ o.getSprite() + " at " + i + j);
							Image img = new Image(o.getSprite());
					    	ImageView imgView = new ImageView(img);
					    	imgView.setFitHeight(44);
					    	imgView.setFitWidth(45);
					    	gridMap.add(imgView, i,  j);
						}
					}
				}
			}
		}
	}
}