package modules;

public class MatchedMods {
  private String name;
  
  private String symbol;
  
  private double expMass;
  
  private double expIntens;
  
  private double dbMono;
  
  private double dbAvg;
  
  private String message;
  
  public MatchedMods(String name, String symbol, double expMass, double expIntens, double dbMono, double dbAvg, String message) {
    this.name = name;
    this.symbol = symbol;
    this.expMass = expMass;
    this.expIntens = expIntens;
    this.dbMono = dbMono;
    this.dbAvg = dbAvg;
    this.message = message;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getSymbol() {
    return this.symbol;
  }
  
  public double getExpMass() {
    return this.expMass;
  }
  
  public double getExpIntens() {
    return this.expIntens;
  }
  
  public double getDBMono() {
    return this.dbMono;
  }
  
  public double getDBAvg() {
    return this.dbAvg;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public String toString() {
    return "Name: " + this.name + ", Symbol: " + this.symbol + ", expMass: " + 
      this.expMass + ", expIntens: " + this.expIntens + ", dbMono: " + this.dbMono + 
      ", dbAvg: " + this.dbAvg;
  }
  
  public boolean equals(MatchedMods m) {
    return (getSymbol().equals(m.getSymbol()) && 
      getExpMass() == m.getExpMass() && 
      getExpIntens() == m.getExpIntens());
  }
}
