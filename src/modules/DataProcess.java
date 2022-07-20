package modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class DataProcess {
  public double[][] readExpMassAndIntens(String fileName, double searchIntensThresh, boolean searchRelIntens) {
    int numLines = 0;
    try {
      Scanner inFile = new Scanner(new File(fileName));
      boolean data = false;
      while (inFile.hasNextLine()) {
        String wholeLine = inFile.nextLine();
        if (wholeLine.substring(0, 3).equals("m/z")) {
          wholeLine = inFile.nextLine();
          wholeLine = inFile.nextLine();
          data = true;
        } 
        if (data)
          numLines++; 
      } 
      inFile.close();
    } catch (FileNotFoundException e1) {
      System.out.println("Data .txt file not found.");
      e1.printStackTrace();
    } 
    double[][] massIntensArray = new double[numLines][3];
    try {
      Scanner inFile = new Scanner(new File(fileName));
      int index = 0;
      boolean data = false;
      while (inFile.hasNextLine()) {
        String wholeLine = inFile.nextLine();
        if (wholeLine.substring(0, 3).equals("m/z")) {
          wholeLine = inFile.nextLine();
          wholeLine = inFile.nextLine();
          data = true;
        } 
        if (data) {
          String[] tokens = wholeLine.split("\\s+");
          double mass = Double.valueOf(tokens[1]).doubleValue();
          double intensity = Double.valueOf(tokens[2]).doubleValue();
          double relRes = Double.valueOf(tokens[3]).doubleValue();
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
  
  public ArrayList<Database> readDatabase(String fileName, double lowRange, double highRange, boolean posPolarity, char massType) {
    DecimalFormat df = new DecimalFormat("#.#####");
    ArrayList<Database> dbList = new ArrayList<>();
    try {
      Scanner inFile = new Scanner(new File(fileName));
      while (inFile.hasNextLine()) {
        String wholeLine = inFile.nextLine();
        String[] tokens = wholeLine.split("\\t");
        if (wholeLine.charAt(0) != '=') {
          double modMono;
          double modAvg;
          if (posPolarity) {
            modMono = Double.valueOf(tokens[2]).doubleValue() + 18.0D + 1.007825D;
            modAvg = Double.valueOf(tokens[3]).doubleValue() + 18.0D + 1.00794D;
          } else {
            modMono = Double.valueOf(tokens[2]).doubleValue() + 18.0D - 1.007825D;
            modAvg = Double.valueOf(tokens[3]).doubleValue() + 18.0D - 1.00794D;
          } 
          if (massType == 'm') {
            if (modMono >= lowRange && modMono <= highRange) {
              String modName = tokens[0];
              String modSym = tokens[1];
              Database mod = new Database(modName, modSym, df.format(modMono), df.format(modAvg));
              dbList.add(mod);
            } 
            continue;
          } 
          if (modAvg >= lowRange && modAvg <= highRange) {
            String modName = tokens[0];
            String modSym = tokens[1];
            Database mod = new Database(modName, modSym, df.format(modMono), df.format(modAvg));
            dbList.add(mod);
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
  
  public static double calcAvP(double expIntens, ArrayList<MatchedMods> matchedMods) {
    double sumIntensities = 0.0D;
    double maxC = 0.0D, maxU = 0.0D, maxA = 0.0D, maxG = 0.0D;
    for (int i = 0; i < matchedMods.size(); i++) {
      if (((MatchedMods)matchedMods.get(i)).getSymbol().equalsIgnoreCase("C")) {
        maxC = ((MatchedMods)matchedMods.get(i)).getExpIntens();
        int count = i + 1;
        while (((MatchedMods)matchedMods.get(count)).getSymbol().equalsIgnoreCase("C")) {
          if (((MatchedMods)matchedMods.get(count)).getExpIntens() > maxC) {
            maxC = ((MatchedMods)matchedMods.get(count)).getExpIntens();
            continue;
          } 
          if (count < matchedMods.size())
            i = count; 
          break;
        } 
      } else if (((MatchedMods)matchedMods.get(i)).getSymbol().equalsIgnoreCase("U")) {
        maxU = ((MatchedMods)matchedMods.get(i)).getExpIntens();
        int count = i + 1;
        while (((MatchedMods)matchedMods.get(count)).getSymbol().equalsIgnoreCase("U")) {
          if (((MatchedMods)matchedMods.get(count)).getExpIntens() > maxU) {
            maxU = ((MatchedMods)matchedMods.get(count)).getExpIntens();
            continue;
          } 
          if (count < matchedMods.size())
            i = count; 
          break;
        } 
      } else if (((MatchedMods)matchedMods.get(i)).getSymbol().equalsIgnoreCase("A")) {
        maxA = ((MatchedMods)matchedMods.get(i)).getExpIntens();
        int count = i + 1;
        while (((MatchedMods)matchedMods.get(count)).getSymbol().equalsIgnoreCase("A")) {
          if (((MatchedMods)matchedMods.get(count)).getExpIntens() > maxA) {
            maxA = ((MatchedMods)matchedMods.get(count)).getExpIntens();
            continue;
          } 
          if (count < matchedMods.size())
            i = count; 
          break;
        } 
      } else if (((MatchedMods)matchedMods.get(i)).getSymbol().equalsIgnoreCase("G")) {
        maxG = ((MatchedMods)matchedMods.get(i)).getExpIntens();
        int count = i + 1;
        while (((MatchedMods)matchedMods.get(count)).getSymbol().equalsIgnoreCase("G")) {
          if (((MatchedMods)matchedMods.get(count)).getExpIntens() > maxG) {
            maxG = ((MatchedMods)matchedMods.get(count)).getExpIntens();
            continue;
          } 
          if (count < matchedMods.size())
            i = count; 
          break;
        } 
      } 
    } 
    sumIntensities = maxC + maxU + maxA + maxG;
    return expIntens / sumIntensities * 100.0D;
  }
}
