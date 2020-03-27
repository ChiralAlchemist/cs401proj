import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PokemonCollection {
		
	/** The current number of POKEMONs in the array */
	private int numPOKEs;
		
	/** The array to contain the POKEMONs */
	private Pokemon[] pokeArray;
		
	/** The name of the data file that contains poke data */
	private String sourceName;
		
	/** PokemonCollection Constructor **/
	public PokemonCollection() {
		numPOKEs = 0;
		pokeArray = new Pokemon[7];				// 7 is just an arbitary number 
	}
		
	// A method to add a Pokemon to the array. 
	public void addPokemon(Pokemon myPoke) 
	{
		
		try {	// running time error prevention.   
				// double the size of the array if the current array is full. 
				if(numPOKEs == pokeArray.length-1)
				{
					pokeArray = Arrays.copyOf(pokeArray, pokeArray.length * 2);
				}
				
				pokeArray[numPOKEs++] = myPoke;	
				
			}catch(NumberFormatException e)
			{
				System.out.println("Error has occurred from addOrModifyPokemon() function ...");
			}
	}
		
	// A method to load in data from a given file.
	public void loadData(String filename) 
	{
		// create a buffered reader to read in text.
	    BufferedReader br;
		      
	    try {
	    	// to read in data from the file.
		        br = new BufferedReader(new FileReader(filename));
		        sourceName = filename;
		        String line = br.readLine();
			         
		       /** read line by line with each pokemon: id, name, gender, type, level, 
		        	HP, Attack, Defense, Sp. Atk, Sp. Def, Speed separated by commas.
		       **/
		        while(line != null && !line.isEmpty())
		        {
		        	String[] pokeData = line.split(",");
	               
		        	// this will read in Identification Stats: ID, name, gender, type
		        	Stats.IdentifyStats myIS = new Stats().new IdentifyStats(Integer.parseInt(pokeData[0]),
		        															  pokeData[1],
		        															  pokeData[2],
		        															  pokeData[3],
		        															  Integer.parseInt(pokeData[4]));
		        	
		        	// this will read in Offensive Stats: attack, sp. attack, speed
		        	Stats.OffensiveStats myOS = new Stats().new OffensiveStats(Integer.parseInt(pokeData[5]),
	                														   Integer.parseInt(pokeData[6]),
	                														   Integer.parseInt(pokeData[7]));
	               
	                // this will read in Defensive Stats: health, defense, sp. defense
		        	Stats.DefensiveStats myDS = new Stats().new DefensiveStats(Integer.parseInt(pokeData[8]),
	                														   Integer.parseInt(pokeData[9]),
	                														   Integer.parseInt(pokeData[10]));
		        	
	                // create the Pokemon object with all the stats we just read in: Identification, Offensive, Defensive stats. 
	                Pokemon myPoke = new Pokemon(myIS, myOS, myDS);
					
	                // add the pokemon object to the pokeArray. 
		        	addPokemon(myPoke);  	         
	                
	                line = br.readLine();
		        }
		        // close the buffered reader.
		        br.close();
		        }catch(IOException e) {
			           e.printStackTrace();
			    }
			}
	
	// A method to return the total # of Pokemons
	public int getNumPokes()
	{
		return numPOKEs; 
	}
	
	// A method to print out the whole Pokemon objects of the PokemonCollection class. 
	void printCollection()
	{
		for(int i = 0; i < numPOKEs; i++)
		{
			System.out.println(pokeArray[i].toString());
		}
	}
	
	/** testing function uncomment to test  
	public static void main(String[] args)
	{
		PokemonCollection pc = new PokemonCollection(); 
		pc.loadData("C:\\Users\\etern\\eclipse-workspace\\Pokemon\\pokedata.txt");
		pc.printCollection();
	}
	**/
} 
