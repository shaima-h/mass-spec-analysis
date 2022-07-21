package modules;

/**
 * Represents a mod from the database.
 * @author shaimahussaini
 */
public class Database {
	
	private String modName;	//mod's name
	private String modSym;	//mod's symbol
	private double modMono;	//mod's mono mass
    private double modAvg;	//mod's average mass
    
    /**
     * Constructor
     * @param modName The mod's name.
     * @param modSym The symbol representing the mod.
     * @param modMono The mono mass of the mod.
     * @param modAvg The average mass of the mod.
     */
    public Database(String modName, String modSym, double modMono, double modAvg)
    {
		this.modName = modName;
		this.modSym = modSym;
		this.modMono = modMono;
		this.modAvg = modAvg;
    }
    
    /**
     * Constructor (takes in in all parameters as Strings).
     * @param modName The mod's name.
     * @param modSym The symbol representing the mod.
     * @param modMono The mono mass of the mod.
     * @param modAvg The average mass of the mod.
     */
    public Database(String modName, String modSym, String modMono, String modAvg)
    {
		this.modName = modName;
		this.modSym = modSym;
		this.modMono = Double.valueOf(modMono);
		this.modAvg = Double.valueOf(modAvg);
    }
    
    //SETTERS
    public void setModName( String modName) {
    	this.modName = modName;
    }
    
    public void setModSym( String modSym) {
    	this.modSym = modSym;
    }
    
    public void setModMono( double modMono) {
    	this.modMono = modMono;
    }
    
    public void setModAvg( double modAvg ) {
    	this.modAvg = modAvg;
    }
    
    
    //GETTERS
    public String getModName() {
    	return modName;
    }
    
    public String getModSym() {
    	return modSym;
    }
    
    public double getModMono() {
    	return modMono;
    }
    
    public double getModAvg() {
    	return modAvg;
    }
    
    public boolean equals(Database d) {
    	return this.getModName().equalsIgnoreCase(d.getModName());
    }

}
