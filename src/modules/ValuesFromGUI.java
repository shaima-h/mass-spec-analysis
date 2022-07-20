package modules;

public class ValuesFromGUI {
  private boolean posPolarity = false;
  
  private double lowRange;
  
  private double highRange;
  
  private double searchTol;
  
  private boolean ppmSearchType = false;
  
  private double searchIntensThresh;
  
  private boolean searchRelIntens = false;
  
  private char massType;
  
  private String filePath;
  
  public void setPosPolarity(boolean value) {
    this.posPolarity = value;
  }
  
  public void setLowRange(double low) {
    this.lowRange = low;
  }
  
  public void setHighRange(double high) {
    this.highRange = high;
  }
  
  public void setSearchTol(double tol) {
    this.searchTol = tol;
  }
  
  public void setppmSearchType(boolean value) {
    this.ppmSearchType = value;
  }
  
  public void setSearchIntensThresh(double thresh) {
    this.searchIntensThresh = thresh;
  }
  
  public void setSearchRelIntens(boolean value) {
    this.searchRelIntens = value;
  }
  
  public void setMassType(char ch) {
    this.massType = ch;
  }
  
  public void setFilePath(String str) {
    this.filePath = str;
  }
  
  public boolean getPosPolarity() {
    return this.posPolarity;
  }
  
  public double getLowRange() {
    return this.lowRange;
  }
  
  public double getHighRange() {
    return this.highRange;
  }
  
  public double getSearchTol() {
    return this.searchTol;
  }
  
  public boolean getppmSearchType() {
    return this.ppmSearchType;
  }
  
  public double getSearchIntensThresh() {
    return this.searchIntensThresh;
  }
  
  public boolean getSearchRelIntens() {
    return this.searchRelIntens;
  }
  
  public char getMassType() {
    return this.massType;
  }
  
  public String getFilePath() {
    return this.filePath;
  }
}
