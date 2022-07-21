package modules;

/**
 * Parameters set by user in GUI.
 * @author shaimahussaini
 */
public class ValuesFromGUI {
	
	private boolean posPolarity = false;		//positive polarity
	private double lowRange;					//low range
	private double highRange;					//high range
	private double searchTol;					//search tolerance
	private boolean ppmSearchType = false;		//search tolerance in ppm
	private double searchIntensThresh;			//intensity threshold
	private boolean searchRelIntens = false;	//intensity threshold in relative resolution
	private char massType;						//matching to mono or average mass
	private String filePath;					//file path to save excel file
	
	//SETTERS
	public void setPosPolarity(boolean value) {
		posPolarity = value;
	}
	
	public void setLowRange(double low) {
		lowRange = low;
	}
	
	public void setHighRange(double high) {
		highRange = high;
	}
	
	public void setSearchTol(double tol) {
		searchTol = tol;
	}
	
	public void setppmSearchType(boolean value) {
		ppmSearchType = value;
	}
	
	public void setSearchIntensThresh(double thresh) {
		searchIntensThresh = thresh;
	}
	
	public void setSearchRelIntens(boolean value) {
		searchRelIntens = value;
	}
	
	public void setMassType(char ch) {
		massType = ch;
	}
	
	public void setFilePath(String str) {
		filePath = str;
	}
	
	//GETTERS
	public boolean getPosPolarity() {
		return posPolarity;
	}
	
	public double getLowRange() {
		return lowRange;
	}
	
	public double getHighRange() {
		return highRange;
	}
	
	public double getSearchTol() {
		return searchTol;
	}
	
	public boolean getppmSearchType() {
		return ppmSearchType;
	}
	
	public double getSearchIntensThresh() {
		return searchIntensThresh;
	}
	
	public boolean getSearchRelIntens() {
		return searchRelIntens;
	}
	
	public char getMassType() {
		return massType;
	}
	
	public String getFilePath() {
		return filePath;
	}

}
