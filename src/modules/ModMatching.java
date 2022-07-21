package modules;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Matches experimental data to database within a tolerance.
 * @author shaimahussaini
 */
public class ModMatching {
	
	/**
	 * Class containing the matching parameters set by the user.
	 */
    private ValuesFromGUI values = new ValuesFromGUI();
    
    /**
     * Sets values of matching parameters set by the user.
     * @param valueClass
     */
	public void setValuesFromGUI(ValuesFromGUI valueClass) {
		values = valueClass;
	}
	
	/**
	 * Calculates the actual tolerance to be used to match the mods.
	 * @param tolerance The tolerance set by the user.
	 * @param baseValue The low range set by the user.
	 * @return The actual tolerance to be used to match the mods.
	 */
	public double calcTolerance(double tolerance, double baseValue)
	{
        double actualTol;
        
        if (values.getppmSearchType()) { //ppm radio button selected for search tolerance
            actualTol = (baseValue * tolerance) / Math.pow(10, 6); //converting ppm to m/z
        }
        else { //m/z radio button selected
            actualTol = tolerance; 
        }
	    return actualTol;
	}
	
	/**
	 * Matches experimental data to database within a tolerance.
	 * @param fileName Name of raw data file.
	 * @param dBaseFile Name of database file.
	 * @return 2D array with matched mods and database values.
	 */
	public ArrayList<MatchedMods> findMods(String fileName, String dBaseFile)
	{
//		System.out.println("");    
//      System.out.println("Called findMods");
                        
        double[] eRange = {values.getLowRange(), values.getHighRange()}; //array to store range set by user
        
        DataProcess readData = new DataProcess();
        ArrayList<Database> dbList = new ArrayList<Database>(); //list to store data from database file
        dbList = readData.readDatabase(dBaseFile, eRange[0], eRange[1], values.getPosPolarity(), values.getMassType());
        
        double[][] expMassIntens; //2D array to store mass and intensity values from raw data file
        expMassIntens = readData.readExpMassAndIntens(fileName, values.getSearchIntensThresh(), 
        		values.getSearchRelIntens());
        
        //experimental mass, experimental intensity, theoretical mono mass from database, theoretical average mass from database
        double expMass, expIntens, expRelRes, theorMono, theorAvg;

//        System.out.println("Database: " + dBaseFile);
        
        double actualTol = calcTolerance(values.getSearchTol(), eRange[0]);
//        System.out.println("Tolerance: " + actualTol + " m/z");
        
        ArrayList<MatchedMods> matchedMods = new ArrayList<MatchedMods>(); //stores matched mods
        double searchIntensThresh = values.getSearchIntensThresh();
        boolean searchRel = values.getSearchRelIntens();
        
       
        //traverses through database list
		for(int dbCount = 0; dbCount < dbList.size(); dbCount++) {
			
			if(values.getMassType() == 'm') {
				theorMono = dbList.get(dbCount).getModMono();
			}
			else {
				theorMono = dbList.get(dbCount).getModAvg();
			}
							
			//traverses through array of experimental data
			for(int expCount = 0; expCount < expMassIntens.length; expCount++) {
				expMass = expMassIntens[expCount][0];
				expIntens = expMassIntens[expCount][1];
				expRelRes = expMassIntens[expCount][2];
				
				//if experimental mass matches database mass within tolerance, add to matchedMods array
				if(expMass >= (theorMono - actualTol) && expMass <= (theorMono + actualTol)) {
//						System.out.println(expMass + " matched mod " + dbList.get(dbCount).getModName()
//								+ ", mod mass: " + theorMono);
					
					if(dbList.get(dbCount).getModSym().equalsIgnoreCase("c") ||
							dbList.get(dbCount).getModSym().equalsIgnoreCase("u") ||
							dbList.get(dbCount).getModSym().equalsIgnoreCase("a") ||
							dbList.get(dbCount).getModSym().equalsIgnoreCase("g")) {
						if(!searchRel && expIntens > searchIntensThresh) {
							matchedMods.add(new MatchedMods(dbList.get(dbCount).getModName(), dbList.get(dbCount).getModSym(), 
									expMass, expIntens, dbList.get(dbCount).getModMono(), dbList.get(dbCount).getModAvg(), ""));
						}
						else if(searchRel && expRelRes > searchIntensThresh) {
						matchedMods.add(new MatchedMods(dbList.get(dbCount).getModName(), dbList.get(dbCount).getModSym(), 
								expMass, expIntens, dbList.get(dbCount).getModMono(), dbList.get(dbCount).getModAvg(), ""));
						}
						else {
							matchedMods.add(new MatchedMods(dbList.get(dbCount).getModName(), dbList.get(dbCount).getModSym(), 
									expMass, expIntens, dbList.get(dbCount).getModMono(), dbList.get(dbCount).getModAvg(), 
									"*Intensity is below threshold"));
						}
					}
					else {
						if(!searchRel && expIntens > searchIntensThresh) {
							matchedMods.add(new MatchedMods(dbList.get(dbCount).getModName(), dbList.get(dbCount).getModSym(), 
									expMass, expIntens, dbList.get(dbCount).getModMono(), dbList.get(dbCount).getModAvg(), ""));
						}
						else if(searchRel && expRelRes > searchIntensThresh) {
							matchedMods.add(new MatchedMods(dbList.get(dbCount).getModName(), dbList.get(dbCount).getModSym(), 
									expMass, expIntens, dbList.get(dbCount).getModMono(), dbList.get(dbCount).getModAvg(), ""));
						}
					}
				}
			}
		}
				
		//gets rid of duplicates (due to duplicates in the database)
		for(int i = 0; i < matchedMods.size(); i++) {
			for(int j = matchedMods.size()-1; j >= 0; j--) {
				if(i != j && matchedMods.get(i).equals(matchedMods.get(j))) {
					matchedMods.remove(j);
				}
			}
		}
				
        return matchedMods;
	}
}

