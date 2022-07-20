package modules;

public class Database {
  private String modName;
  
  private String modSym;
  
  private double modMono;
  
  private double modAvg;
  
  public Database(String modName, String modSym, double modMono, double modAvg) {
    this.modName = modName;
    this.modSym = modSym;
    this.modMono = modMono;
    this.modAvg = modAvg;
  }
  
  public Database(String modName, String modSym, String modMono, String modAvg) {
    this.modName = modName;
    this.modSym = modSym;
    this.modMono = Double.valueOf(modMono).doubleValue();
    this.modAvg = Double.valueOf(modAvg).doubleValue();
  }
  
  public void setModName(String modName) {
    this.modName = modName;
  }
  
  public void setModSym(String modSym) {
    this.modSym = modSym;
  }
  
  public void setModMono(double modMono) {
    this.modMono = modMono;
  }
  
  public void setModAvg(double modAvg) {
    this.modAvg = modAvg;
  }
  
  public String getModName() {
    return this.modName;
  }
  
  public String getModSym() {
    return this.modSym;
  }
  
  public double getModMono() {
    return this.modMono;
  }
  
  public double getModAvg() {
    return this.modAvg;
  }
  
  public boolean equals(Database d) {
    return getModName().equalsIgnoreCase(d.getModName());
  }
}
