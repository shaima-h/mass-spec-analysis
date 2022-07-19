package modules;

/**
 * Represents mods that were matched. Used to create rows in Excel file.
 * @author shaimahussaini
 */
public class MatchedMods {
		
	private String name;		//mod's name
	private String symbol;		//mod's symbol
	private double expMass;		//experimental mass
	private double expIntens;	//experimental intensity
	private double dbMono;		//mod's mono mass
	private double dbAvg;		//mod's average mass
	private String message;
	
	/**
	 * Comstructor
	 * @param name Mod's name.
	 * @param symbol Mod's symbol.
	 * @param expMass Experimental mass.
	 * @param expIntens Experimental intensity.
	 * @param dbMono Mod's mono mass.
	 * @param dbAvg Mod's average mass.
	 */
	public MatchedMods(String name, String symbol, double expMass, 
			double expIntens, double dbMono, double dbAvg, String message) {
		
		this.name = name;
		this.symbol = symbol;
		this.expMass = expMass;
		this.expIntens = expIntens;
		this.dbMono = dbMono;
		this.dbAvg = dbAvg;
		this.message = message;
	}
	
	//GETTERS
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public double getExpMass() {
		return expMass;
	}
	
	public double getExpIntens() {
		return expIntens;
	}
	
	public double getDBMono() {
		return dbMono;
	}
	
	public double getDBAvg() {
		return dbAvg;
	}
	
	public String getMessage() {
		return message;
	}
	
	/**
	 * Returns String representation of a matched mod.
	 * @return String representation of a matched mod.
	 */
	public String toString() {
		return "Name: " + name + ", Symbol: " + symbol + ", expMass: "
				+ expMass + ", expIntens: " + expIntens + ", dbMono: " + dbMono
				+ ", dbAvg: " + dbAvg;
	}
	
	public boolean equals(MatchedMods m) {
		return this.getSymbol().equals(m.getSymbol()) && 
				this.getExpMass() == m.getExpMass() &&
				this.getExpIntens() == m.getExpIntens();
	}

}
