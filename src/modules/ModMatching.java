package modules;

import java.util.ArrayList;

public class ModMatching {
  private ValuesFromGUI values = new ValuesFromGUI();
  
  public void setValuesFromGUI(ValuesFromGUI valueClass) {
    this.values = valueClass;
  }
  
  public double calcTolerance(double tolerance, double baseValue) {
    double actualTol;
    if (this.values.getppmSearchType()) {
      actualTol = baseValue * tolerance / Math.pow(10.0D, 6.0D);
    } else {
      actualTol = tolerance;
    } 
    return actualTol;
  }
  
  public ArrayList<MatchedMods> findMods(String fileName, String dBaseFile) {
    double[] eRange = { this.values.getLowRange(), this.values.getHighRange() };
    DataProcess readData = new DataProcess();
    ArrayList<Database> dbList = new ArrayList<>();
    dbList = readData.readDatabase(dBaseFile, eRange[0], eRange[1], this.values.getPosPolarity(), this.values.getMassType());
    double[][] expMassIntens = readData.readExpMassAndIntens(fileName, this.values.getSearchIntensThresh(), 
        this.values.getSearchRelIntens());
    double actualTol = calcTolerance(this.values.getSearchTol(), eRange[0]);
    ArrayList<MatchedMods> matchedMods = new ArrayList<>();
    double searchIntensThresh = this.values.getSearchIntensThresh();
    boolean searchRel = this.values.getSearchRelIntens();
    for (int dbCount = 0; dbCount < dbList.size(); dbCount++) {
      double theorMono;
      if (this.values.getMassType() == 'm') {
        theorMono = ((Database)dbList.get(dbCount)).getModMono();
      } else {
        theorMono = ((Database)dbList.get(dbCount)).getModAvg();
      } 
      for (int expCount = 0; expCount < expMassIntens.length; expCount++) {
        double expMass = expMassIntens[expCount][0];
        double expIntens = expMassIntens[expCount][1];
        double expRelRes = expMassIntens[expCount][2];
        if (expMass >= theorMono - actualTol && expMass <= theorMono + actualTol)
          if (((Database)dbList.get(dbCount)).getModSym().equalsIgnoreCase("c") || (
            (Database)dbList.get(dbCount)).getModSym().equalsIgnoreCase("u") || (
            (Database)dbList.get(dbCount)).getModSym().equalsIgnoreCase("a") || (
            (Database)dbList.get(dbCount)).getModSym().equalsIgnoreCase("g")) {
            if (!searchRel && expIntens > searchIntensThresh) {
              matchedMods.add(new MatchedMods(((Database)dbList.get(dbCount)).getModName(), ((Database)dbList.get(dbCount)).getModSym(), 
                    expMass, expIntens, ((Database)dbList.get(dbCount)).getModMono(), ((Database)dbList.get(dbCount)).getModAvg(), ""));
            } else if (searchRel && expRelRes > searchIntensThresh) {
              matchedMods.add(new MatchedMods(((Database)dbList.get(dbCount)).getModName(), ((Database)dbList.get(dbCount)).getModSym(), 
                    expMass, expIntens, ((Database)dbList.get(dbCount)).getModMono(), ((Database)dbList.get(dbCount)).getModAvg(), ""));
            } else {
              matchedMods.add(new MatchedMods(((Database)dbList.get(dbCount)).getModName(), ((Database)dbList.get(dbCount)).getModSym(), 
                    expMass, expIntens, ((Database)dbList.get(dbCount)).getModMono(), ((Database)dbList.get(dbCount)).getModAvg(), 
                    "*Intensity is below threshold"));
            } 
          } else if (!searchRel && expIntens > searchIntensThresh) {
            matchedMods.add(new MatchedMods(((Database)dbList.get(dbCount)).getModName(), ((Database)dbList.get(dbCount)).getModSym(), 
                  expMass, expIntens, ((Database)dbList.get(dbCount)).getModMono(), ((Database)dbList.get(dbCount)).getModAvg(), ""));
          } else if (searchRel && expRelRes > searchIntensThresh) {
            matchedMods.add(new MatchedMods(((Database)dbList.get(dbCount)).getModName(), ((Database)dbList.get(dbCount)).getModSym(), 
                  expMass, expIntens, ((Database)dbList.get(dbCount)).getModMono(), ((Database)dbList.get(dbCount)).getModAvg(), ""));
          }  
      } 
    } 
    for (int i = 0; i < matchedMods.size(); i++) {
      for (int j = matchedMods.size() - 1; j >= 0; j--) {
        if (i != j && ((MatchedMods)matchedMods.get(i)).equals(matchedMods.get(j)))
          matchedMods.remove(j); 
      } 
    } 
    return matchedMods;
  }
}
