
public class Pokemon {
	
	private Stats.IdentifyStats IS; 
	private Stats.OffensiveStats OS;
	private Stats.DefensiveStats DS;
	
	Pokemon(Stats.IdentifyStats IS, Stats.OffensiveStats OS, Stats.DefensiveStats DS) 
	{
		this.IS = IS;
		this.OS = OS;
		this.DS = DS; 
	}
	
	Pokemon()
	{
		IS = null;
		OS = null;
		DS = null; 
	}
	
	// A method to print out the Pokemon object. 
	public String toString()
	{
		String temp = "Pokemon ID: " + Integer.toString(IS.getID()) + " / " 
					   + "Name: " + IS.getName() + " / "
					   + "Gender: " + IS.getGender() + " / "
					   + "Type: " + IS.getType() + " / "
				       + "Level: " + Integer.toString(IS.getLevel()) + " / " 
				       + "Attack: " + Integer.toString(OS.getATK()) + " / "
				       + "Sp. Attack: " + Integer.toString(OS.getSPATK()) + " / "
				       + "Speed: " + Integer.toString(OS.getSpeed()) + " / "
				       + "Health: " + Integer.toString(DS.getHP()) + " / "
				       + "Defense: " + Integer.toString(DS.getDEF()) + " / "
					   + "Sp. Defense: " + Integer.toString(DS.getSPDEF()) + "\n";
		return temp; 
	}
	
	
} 
