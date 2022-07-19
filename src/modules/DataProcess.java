package modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Processes raw experimental data and database files.
 * @author shaimahussaini
 */
public class DataProcess {
	    
		/**
		 * Reads experimental data and puts masses and intensities into 2D array.
		 * @param fileName Name of raw data file.
		 * @param searchIntensThresh Intensity threshold set by user.
		 * @param searchRelIntens True if relative resolution radio button is selected, false if counts radio button is selected.
		 * @return 2D array containing experimental masses and intensities.
		 */
	    public double[][] readExpMassAndIntens(String fileName, double searchIntensThresh, 
	    		boolean searchRelIntens) {
			
	    	//first, count number of lines of data in data file in order to make 2D array
	    	int numLines = 0;

	    	try {
				Scanner inFile = new Scanner(new File(fileName));
				String wholeLine;
				
				boolean data = false;
//				double intensity, relResolution;
//    	        String[] tokens;
    	        
		        while (inFile.hasNextLine()) {
	        		wholeLine = inFile.nextLine();

	        		if(wholeLine.substring(0, 3).equals("m/z")) {
	        			wholeLine = inFile.nextLine();
	        			wholeLine = inFile.nextLine();
	        			data = true;
	        		}
	        		
	        		if(data) {
//	        			tokens = wholeLine.split("\\s+");
//	        	        
//	        			intensity = Double.valueOf(tokens[2]);
//	        			relResolution = Double.valueOf(tokens[3]);
//	        				        			
//	        			if(!searchRelIntens) {
//	        	        	if(intensity >= searchIntensThresh) {
//	    	        			numLines++;
//	        	        	}
//	        	        }
//	        			else
//	        			{
//	        				if(relResolution >= searchIntensThresh) {
//	    	        			numLines++;
//	        				}
//	        			}
	        			
	        			numLines++;
	        		}
		        }
		        inFile.close();
		        
			} catch (FileNotFoundException e1) {
				System.out.println("Data .txt file not found.");
				e1.printStackTrace();
			}
	        
//	    	System.out.println("lines " + numLines);
	        
	        double[][] massIntensArray = new double[numLines][3];
	        
	        //now, add data to array
	        //columns of array: mass  intensity
	        String wholeLine;
	        
			try {
				Scanner inFile = new Scanner(new File(fileName));
    	        int index = 0;
    	        boolean data = false;
        		double mass;
        		double intensity;
        		double relRes;
    	        String[] tokens;

				while(inFile.hasNextLine()) {
	        		wholeLine = inFile.nextLine();

	        		if(wholeLine.substring(0, 3).equals("m/z")) {
	        			wholeLine = inFile.nextLine();
	        			wholeLine = inFile.nextLine();
	        			data = true;
	        		}
	        		
	        		if(data) {
	        	        tokens = wholeLine.split("\\s+");
	        	        
	        			mass = Double.valueOf(tokens[1]);
	        			intensity = Double.valueOf(tokens[2]);
	        			relRes = Double.valueOf(tokens[3]);
	        			
	        			//if threshold is by counts
//	        			if(!searchRelIntens) {
//	        	        	if(intensity >= searchIntensThresh) {
//	        	        		massIntensArray[index][0] = mass;
//	    	        			massIntensArray[index][1] = intensity;
//	    	        			index++;
//	        	        	}
//	        	        }
//	        			else //if threshold is by relative resolution
//	        			{
//	        				if(Double.valueOf(tokens[3]) >= searchIntensThresh) {
//	        					massIntensArray[index][0] = mass;
//	    	        			massIntensArray[index][1] = intensity;
//	    	        			index++;
//	        				}
//	        			}
	        			
	        			massIntensArray[index][0] = mass;
	        			massIntensArray[index][1] = intensity;
	        			massIntensArray[index][2] = relRes;
	        			index++;
	        		}
	        	}
		        inFile.close();
				
			} catch (FileNotFoundException e) {
				System.out.println("Data .txt file not found.");
				e.printStackTrace();
			}
        	
			return massIntensArray;
	    }
	    
	    /**
	     * Reads database file and puts data into an ArrayList of Database objects.
	     * @param fileName Name of database file.
	     * @param lowRange Low range set by user.
	     * @param highRange High range set by user.
	     * @param posPolarity True if polarity is positive, false if negative.
	     * @return An ArrayList of Database objects.
	     */
	    public ArrayList<Database> readDatabase(String fileName, double lowRange, 
	    		double highRange, boolean posPolarity, char massType) {
	    	
	    	String modName, modSym; //mod name, mod symbol
	        double modMono, modAvg; //mod mono mass, mod avg mass
	        DecimalFormat df = new DecimalFormat("#.#####");

	        String wholeLine; 
	        String[] tokens;
	        ArrayList<Database> dbList = new ArrayList<Database>();
	        
	        try {
				Scanner inFile = new Scanner(new File(fileName));
				while(inFile.hasNextLine()) {
	        		wholeLine = inFile.nextLine();
        			tokens = wholeLine.split("\\t");

	        		if(wholeLine.charAt(0) != '=') { //skip header lines in database
	        			
	        			//18 represents some atoms that are on each nucleotide as a part of the digestion process (OH group)
	        			if(posPolarity) { //if positive polarity
	        				modMono = Double.valueOf(tokens[2]) + 18 + 1.007825; 
	        				modAvg = Double.valueOf(tokens[3]) + 18 + 1.00794;
	        				//1.007825 and the 1.00794 are the mono and avg (respectively) values for Hydrogen
	        			}
	        			else { //if negative polarity
	        				modMono = Double.valueOf(tokens[2]) + 18 - 1.007825; 
	        				modAvg = Double.valueOf(tokens[3]) + 18 - 1.00794;
	        				//-1.007825 and the -1.00794 are the mono and avg (respectively) values for Hydrogen
	        			}
	        				    
	        			if(massType == 'm') //if comparing to mono masses
	        			{
	        				if((modMono >= lowRange) && (modMono <= highRange)) { //if masses within range
		        				modName = tokens[0];
				    	    	modSym = tokens[1];
				    	    	Database mod = new Database(modName, modSym, df.format(modMono), df.format(modAvg));
				    	    	dbList.add(mod);
		        			}
	        			}
	        			else { //if comparing to avg masses
	        				if((modAvg >= lowRange) && (modAvg <= highRange)) { //if masses within range
		        				modName = tokens[0];
				    	    	modSym = tokens[1];
				    	    	Database mod = new Database(modName, modSym, df.format(modMono), df.format(modAvg));
				    	    	dbList.add(mod);
		        			}
	        			}
	        		}	    	    	
	    		}
	    	    inFile.close();
	    	    
			} catch (FileNotFoundException e) {
				System.out.println("Database file not found.");
				e.printStackTrace();
			}
	        
	        return dbList;
	        
	    }
	    
	    /**
	     * Calculates Abundance vs Proxy (AvP) value.
	     * @param expIntens Experimental intensity of analyte.
	     * @param matchedMods ArrayList of matched mods.
	     * @return AvP Value.
	     */
	    public static double calcAvP(double expIntens, ArrayList<MatchedMods> matchedMods) {
	    	
	    	double sumIntensities = 0;
	    	double maxC = 0, maxU = 0, maxA = 0, maxG = 0;
	    	
	    	//getting max intensities of 4 canonical bases
	    	for(int i = 0; i < matchedMods.size(); i++) {
	    		if(matchedMods.get(i).getSymbol().equalsIgnoreCase("C")) {
					maxC = matchedMods.get(i).getExpIntens();
					int count = i + 1;
					while(matchedMods.get(count).getSymbol().equalsIgnoreCase("C")) {
						if(matchedMods.get(count).getExpIntens() > maxC) {
							maxC = matchedMods.get(count).getExpIntens();
						}
						else {
							if(count < matchedMods.size()) {
		    					i = count;
	    					}
							break;
						}
					}
	    		}
	    		else if(matchedMods.get(i).getSymbol().equalsIgnoreCase("U")) {
	    			maxU = matchedMods.get(i).getExpIntens();
					int count = i + 1;
					while(matchedMods.get(count).getSymbol().equalsIgnoreCase("U")) {
						if(matchedMods.get(count).getExpIntens() > maxU) {
							maxU = matchedMods.get(count).getExpIntens();
						}
						else {
							if(count < matchedMods.size()) {
		    					i = count;
	    					}
							break;
						}
					}
	    		}
	    		else if(matchedMods.get(i).getSymbol().equalsIgnoreCase("A")) {
	    			maxA = matchedMods.get(i).getExpIntens();
					int count = i + 1;
					while(matchedMods.get(count).getSymbol().equalsIgnoreCase("A")) {
						if(matchedMods.get(count).getExpIntens() > maxA) {
							maxA = matchedMods.get(count).getExpIntens();
						}
						else {
							if(count < matchedMods.size()) {
		    					i = count;
	    					}
							break;
						}
					}
	    		}
	    		else if(matchedMods.get(i).getSymbol().equalsIgnoreCase("G")) {
	    			maxG = matchedMods.get(i).getExpIntens();
					int count = i + 1;
					while(matchedMods.get(count).getSymbol().equalsIgnoreCase("G")) {
						if(matchedMods.get(count).getExpIntens() > maxG) {
							maxG = matchedMods.get(count).getExpIntens();
						}
						else {
							if(count < matchedMods.size()) {
		    					i = count;
	    					}
							break;
						}
					}
	    		}
	    	}
	    	
	    	//sum of max intensities
	    	sumIntensities = maxC + maxU + maxA + maxG;
	    	
	    	//calculation of AvP
	    	return (expIntens / sumIntensities) * 100;
	    }
	    
	    
	    

}
