
public class Stats {
	
	public class IdentifyStats {
		private int ID;
		private String name; 
		private String gender; 
		private String type;
		private int level; 
	
		public IdentifyStats(int id, String name, String gender, String type, int level)
		{
			this.ID = id;
			this.name = name;
			this.gender = gender;
			this.type = type;
			this.level = level; 
		}
		
		// setters for Pokemon common attributes 
		public void setID(int  thisID) { this.ID = thisID; }
		public void setName(String thisName) { this.name = thisName; }
		public void setGender(String thisGender) { this.gender = thisGender; }
		public void setType(String thisType) { this.type = thisType; }
		public void setLevel(int thisLevel)	{ this.level = thisLevel; }	
		
		// getters for Pokemon common attributes 
		public int getID() { return ID; }
		public String getName() { return name; }
		public String getGender() { return gender; }
		public String getType() { return type; }
		public int getLevel() { return level; }
	
	}
	
	public class OffensiveStats {
		
		private int atk;
		private int spatk;
		private int spd;
		
		public OffensiveStats(int atk, int spatk, int spd)
		{
			this.atk = atk;
			this.spatk = spatk;
			this.spd = spd; 
		}
		
		// setters for OffeniveStats class
		public void setSpeed(int thisSPD) { this.spd = thisSPD; }
		public void setATK(int thisATK)	{ this.atk = thisATK; }
		public void setSPAtk(int thisSPAtk) { this.spatk = thisSPAtk; }
		
		// getters for OffensiveStats class
		public int getSpeed(){ return spd; }
		public int getATK()	{ return atk; }
		public int getSPATK() { return spatk; }
	}
	
	public class DefensiveStats {
		private int hp;
		private int def;
		private int spdef;
		
		public DefensiveStats(int hp, int def, int spdef)
		{
			this.hp = hp;
			this.def = def;
			this.spdef = spdef; 
		}
		
		// setters for DefensiveStats class
		public void setHP(int thisHP) {	this.hp = thisHP; }
		public void setDEF(int thisDEF) { this.def = thisDEF; }
		public void setSPDef(int thisSPDef) { this.spdef = thisSPDef; }
		
		// getters for DefensiveStats class
		public int getHP() { return hp; }
		public int getDEF() { return def; }
		public int getSPDEF() { return spdef; }
		
	}

}
