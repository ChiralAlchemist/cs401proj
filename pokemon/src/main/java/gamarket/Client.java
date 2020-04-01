package gamarket;

import java.util.*;

public class Client{
    private int encnounterChance = 200;
    private double encounterTable[] = {10, 8.5,6.75,3.33,1.25};
    private String startInput;
    private String interFaceInput;
    StartMenuGUI startMenuGUI;
    private Grid grid;
    private Tile tile;
    private Player player;

    static Client client = new Client ();

    private Client(){
        //constructor
        startMenu();
       /* while(startMenu.window.isShowing()) {
            System.out.println("!");
            if (startMenu.getNewUser()) {
                player = new Player(true, startMenu.getUsername(), startMenu.getPassword());
                System.out.println("new user created!");
                startMenu.window.close();
            } else if (startMenu.verified == true) {
                player = new Player(false, startMenu.getUsername(), startMenu.getPassword());
                System.out.println("user data retrieved");
                startMenu.window.close();
            }
        }
        */

    }

    public static Client getInstance(){
        return client;
    }

    public void startMenu(){
        startMenuGUI = new StartMenuGUI();
    }


    public void gameInterface(){

    }

    public void save(){
    //implemented in player class?
    }

    public void quitSave(){

    }

    public void displayTeam(){

    }

    public void encounterCheck(String move){
        Random random = new Random();
        int rand = random.nextInt(5);
        double encounterType = encounterTable[rand];
        int encounterFormula = (int) Math.floor(encnounterChance/encounterType);
        rand = random.nextInt(encnounterChance);
        int position[];

        switch(move){
            case "W":
                grid.updateGrid("W");
                position = grid.getPlayerPosition();
                tile = grid.getTile(position[0], position[1]);
                if(tile.getType() == Tile.Type.GRASS){
                    if(rand == encounterFormula){
                        encouter();
                    }
                }
                break;
            case "A":
                grid.updateGrid("A");
                position = grid.getPlayerPosition();
                tile = grid.getTile(position[0], position[1]);
                if(tile.getType() == Tile.Type.GRASS){
                    if(rand == encounterFormula){
                        encouter();
                    }
                }
                break;
            case "S":
                grid.updateGrid("S");
                position = grid.getPlayerPosition();
                tile = grid.getTile(position[0], position[1]);
                if(tile.getType() == Tile.Type.GRASS){
                    if(rand == encounterFormula){
                        encouter();
                    }
                }
                break;
            case "D":
                grid.updateGrid("D");
                position = grid.getPlayerPosition();
                tile = grid.getTile(position[0], position[1]);
                if(tile.getType() == Tile.Type.GRASS){
                    if(rand == encounterFormula){
                        encouter();
                    }
                }
                break;
        }
    }

    public void encouter(){

    }


}