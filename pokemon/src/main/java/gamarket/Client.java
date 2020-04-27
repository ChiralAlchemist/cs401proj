package gamarket;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.*;

public class Client extends Application {
    private Grid grid;
    private Player player;
    private GridPane gameGUI;
    private StackPane stackPane;
    private Stage window;
    private int width = 800;
    private int height = 800;
    private int menuOpen;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    /**
     * start begins the application and is the main controller of it
     */
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Pokemon: East Bay");
        window.setResizable(false);
        stackPane = new StackPane();
        StartMenuGUI startMenu = new StartMenuGUI();
        startMenu.display();
        stackPane.getChildren().addAll(gameInterface(startMenu.getNewUser(), startMenu.getUsername(), startMenu.getPassword()));
        Scene scene = new Scene(stackPane);
        menuOpen = 0;
        window.setScene(scene);

            ArrayList<String> input = new ArrayList<String>();
            scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {
                        public void handle(KeyEvent e) {
                            String code = e.getCode().toString();
                            if (!input.contains(code))
                                input.add(code);
                        }
                    });
            scene.setOnKeyReleased(
                    new EventHandler<KeyEvent>() {
                        public void handle(KeyEvent e) {
                            String code = e.getCode().toString();
                            input.remove(code);
                        }
                    });
            new AnimationTimer() {
                private long lastUpdate;

                @Override
                public void start() {
                    lastUpdate = System.nanoTime();
                    super.start();
                }

                public void handle(long currentNanoTime) {
                    long elapsedNanoSeconds = currentNanoTime - lastUpdate;
                    double elapsedSeconds = elapsedNanoSeconds / 1000000000.0;
                    if (elapsedSeconds >= .1) {
                        lastUpdate = currentNanoTime;
                        if (input.contains("W")) {
                            updateGUI("w");
                            encounterCheck();
                        } else if (input.contains("A")) {
                            updateGUI("a");
                            encounterCheck();
                        } else if (input.contains("S")) {
                            updateGUI("s");
                            encounterCheck();
                        } else if (input.contains("D")) {
                            updateGUI("d");
                            encounterCheck();
                        } else if (input.contains("E")) {
                            if(menuOpen == 0){
                                menu();
                                menuOpen++;
                            }
                        }
                    }
                }
            }.start();



        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                save();
                window.close();
                System.exit(0);
            }
        });

        window.show();
    }

    /**
     * gameInterface loads up the appropriate grid depending on whether the player is new or returning,
     * instantiates the player class and loads in their new or saved information, and creates the GUI.
     * @param newPlayer lets gameInterface know whether we have a new player
     * @param username is used to instantiate the player
     * @param password is usded to instantiate the player
     * @return returns the GUI
     */
    public GridPane gameInterface(boolean newPlayer, String username, String password){
        gameGUI = new GridPane();
        if(!newPlayer){
            player = new Player(false, username, password);
            grid = new Grid();
            grid.loadData(username, false);
        }else{
            player = new Player(true, username, password);
            grid = new Grid();
            grid.loadData("new",false);
        }

        gameGUI.setVgap(0.0);
        gameGUI.setHgap(0.0);

        TileGUI tile;
        for (int y = 0; y < grid.getYMax(); y++) {
            for (int x = 0; x < grid.getXMax(); x++) {
                tile = new TileGUI(grid.getTile(x,y));
                gameGUI.add(tile, x, y);
            }
        }
        updateGUI("a");
        updateGUI("d");


        return gameGUI;

    }

    /**
     * save saves the current players data and grid
     */
    public void save(){
        player.saveData();
        grid.save(player.getName(), false);
    }

    public void displayTeam(){
        //TO-DO
    }

    public void encouter(){
        System.out.println("Pokemon encountered!");
    }

    /**
     * encounterCheck checks the tile the player moved on.
     * If the tile is grass, checks the probablity of encountering a pokemon,
     * and calls the encounter method if a pokemon is encounterd.
     */
    private void encounterCheck(){
         Random random = new Random();
         int rand = random.nextInt(10);
         int rand2 = random.nextInt(10);

         Tile tile = grid.getTile(grid.getPlayerPosition()[0], grid.getPlayerPosition()[1]);

         if(tile.getType() == Tile.Type.GRASS){
              if(rand == rand2){
                 encouter();
             }
         }

    }

    /**
     * updatesGUI updates the player sprite location on the gui
     * @param direction lets the method know in which direection to move the sprite
     */
    private void updateGUI(String direction){
        int location = grid.getPlayerPosition()[0] + (grid.getPlayerPosition()[1] * grid.getYMax());
        TileGUI player = (TileGUI)this.gameGUI.getChildren().get(location);
        player.removePlayer();
        int x = grid.getPlayerPosition()[0];
        int y = grid.getPlayerPosition()[1];

        switch (direction){
            case "w":
                if(grid.canMove(x, y-1)) {
                    grid.updateGrid("w");
                    location -= grid.getXMax() ;
                }
                break;
            case "a":
                if(grid.canMove(x-1, y)) {
                    grid.updateGrid("a");
                    location--;
                }
                break;
            case "s":
                if(grid.canMove(x, y+1)) {
                    grid.updateGrid("s");
                    location += grid.getXMax();
                }
                break;
            case "d":
                if(grid.canMove(x+1, y)) {
                    grid.updateGrid("d");
                    location += 1;
                }
                break;
        }
        TileGUI playerNew = (TileGUI)this.gameGUI.getChildren().get(location);
        playerNew.renderPlayer();
    }

    private void menu(){
        StackPane sp = new StackPane();
        GridPane black = new GridPane();

        black.setStyle("-fx-background-color: black;" +
                "-fx-opacity: .3px");

        GridPane gameMenu = new GridPane();
        gameMenu.setAlignment(Pos.CENTER_LEFT);
        gameMenu.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 15px;" +
                "-fx-max-height: 800px;" +
                "-fx-max-width: 400px;" +
                "-fx-translate-x: 250px;" +
                "-fx-font-family: 'Courier New';" +
                "-fx-font-size: 50px;" +
                "-fx-vgap: 30px;");

        //gameMenu.setGridLinesVisible(true);  

        Button pokedex = new Button("Pokedex");
        pokedex.setOnAction(e -> {
            //TODO
        });
        pokedex.setStyle(buttonStyle());
        pokedex.setOnMouseEntered(event -> {
            pokedex.setStyle(hover());
        });
        pokedex.setOnMouseExited(event -> {
            pokedex.setStyle(buttonStyle());
        });

        Button pokemon = new Button("Pokemon");
        pokemon.setOnAction(e -> {
            //TODO
        });
        pokemon.setStyle(buttonStyle());
        pokemon.setOnMouseEntered(event -> {
            pokemon.setStyle(hover());
        });
        pokemon.setOnMouseExited(event -> {
            pokemon.setStyle(buttonStyle());
        });

        Button bag = new Button("Bag");
        bag.setOnAction(e -> {
            //TODO
        });
        bag.setStyle(buttonStyle());
        bag.setOnMouseEntered(event -> {
            bag.setStyle(hover());
        });
        bag.setOnMouseExited(event -> {
            bag.setStyle(buttonStyle());
        });

        String name = player.getName();
        Button playerInfo = new Button(name);
        playerInfo.setOnAction(e -> {
            //TODO
        });
        playerInfo.setStyle(buttonStyle());
        playerInfo.setOnMouseEntered(event -> {
            playerInfo.setStyle(hover());
        });
        playerInfo.setOnMouseExited(event -> {
            playerInfo.setStyle(buttonStyle());
        });

        Button saveGame = new Button("Save");
        saveGame.setOnAction(e -> {
            save();
            saveGame.setStyle(buttonStyle());
            //TODO
        });
        saveGame.setStyle(buttonStyle());
        saveGame.setOnMouseEntered(event -> {
            saveGame.setStyle(hover());
        });
        saveGame.setOnMouseExited(event -> {
           saveGame.setStyle(buttonStyle());
        });


        Button exit = new Button("Exit");
        exit.setOnAction(e -> {
            stackPane.getChildren().removeAll(black, gameMenu);
            exit.setStyle(buttonStyle());
        });
        exit.setStyle(buttonStyle());
        exit.setOnMouseEntered(event -> {
            exit.setStyle(hover());
        });
        exit.setOnMouseExited(event -> {
            exit.setStyle(buttonStyle());
        });

        pokedex.setPadding(new Insets(20,20,20,20));
        gameMenu.add(pokedex, 0,0);
        gameMenu.add(pokemon, 0,1);
        gameMenu.add(bag,0,2);
        gameMenu.add(playerInfo, 0,3);
        gameMenu.add(saveGame, 0,4);
        gameMenu.add(exit, 0,5);


        stackPane.getChildren().addAll(black, gameMenu);
        window.setScene(new Scene(stackPane));
    }

    private String buttonStyle(){
        String style = "-fx-background-color: white;" +
                "-fx-text-alignment: center;" +
                "-fx-max-width: 300px" +
                "-fx-translate-x: 20px;";

        return style;
    }
    private String hover(){
        String style = "-fx-background-color: white;" +
                "-fx-text-alignment: center;" +
                "-fx-font-weight: bold;" +
                "-fx-max-width: 300px" +
                "-fx-translate-x: 20px;";

        return style;
    }



}