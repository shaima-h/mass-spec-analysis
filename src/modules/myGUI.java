package modules;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JSeparator;

/**
 * GUI for matching mods.
 * @author shaimahussaini
 */
public class myGUI extends JFrame {
	
	private File txtFile, databaseFile;		//experimental data file, database file
	private String filePath;				//file path to save file
	
	private JPanel contentPane;				//content pane
	private JTextArea fileViewer;			//file viewer
	private JTextArea statusArea;			//status area
	
	private JLabel dataLabel;				//data file name
	private JLabel databaseLabel;			//database file name
	
	private JRadioButton posPolarRadioBtn;	//positive polarity
	private JRadioButton negPolarRadioBtn;	//negative polarity
	
	private JTextField lowRange;			//low range
	private JTextField highRange;			//high range
	
	private JTextField searchTol;			//search tolerance
	private JRadioButton ppmRadioBtn;		//ppm
	private JRadioButton mzRadioBtn;		//m/z
	
	private JTextField searchIntensThresh;	//intensity threshold
	private JRadioButton countsRadioBtn;	//counts
	private JRadioButton relResRadioBtn;	//relative resolution
	
	private JRadioButton monoRadioBtn;		//mono mass
	private JRadioButton avgRadioBtn;		//average mass
	
	private JTextField pathTextField;		//file path to save .xlsx file
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myGUI frame = new myGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public myGUI() {
		setTitle("Mod Matcher");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 80, 920, 580);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JToolBar toolbar = new JToolBar();
		toolbar.setBounds(5, 0, 200, 30);
		
		JButton matcherBtn = new JButton("Mod Matcher");
		
		JButton dbEditBtn = new JButton("Database Editor");
		dbEditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseEditorGUI dbEditor = new DatabaseEditorGUI();
				dbEditor.setVisible(true);
				dispose();
			}
		});
		
		toolbar.add(matcherBtn);
		toolbar.add(dbEditBtn);
		
		contentPane.add(toolbar);
		
		JButton btnImportTXT = new JButton("Import data file");
		btnImportTXT.setBounds(5, 60, 169, 29);
		btnImportTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImportTXTActionPerformed(e);
			}
		});
		
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File viewer");
		lblNewLabel.setBounds(500, 30, 75, 16);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblNewLabel);
		contentPane.add(btnImportTXT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(180, 50, 720, 155);
		contentPane.add(scrollPane);
		
		fileViewer = new JTextArea();
		scrollPane.setViewportView(fileViewer);
		fileViewer.setEditable(false);
		
		posPolarRadioBtn = new JRadioButton("Pos");
		posPolarRadioBtn.setBounds(70, 214, 54, 23);
		contentPane.add(posPolarRadioBtn);
		
		JLabel lblNewLabel_2 = new JLabel("Polarity:");
		lblNewLabel_2.setBounds(16, 218, 51, 16);
		contentPane.add(lblNewLabel_2);
		
		negPolarRadioBtn = new JRadioButton("Neg");
		negPolarRadioBtn.setBounds(117, 214, 57, 23);
		contentPane.add(negPolarRadioBtn);
		
		ButtonGroup polGroup = new ButtonGroup();
		polGroup.add(posPolarRadioBtn);
		polGroup.add(negPolarRadioBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Status");
		lblNewLabel_1.setBounds(717, 220, 41, 16);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Processing Parameters");
		lblNewLabel_3.setBounds(5, 245, 154, 16);
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(575, 240, 325, 300);
		contentPane.add(scrollPane_1);
		
		statusArea = new JTextArea();
		scrollPane_1.setViewportView(statusArea);
		statusArea.setEditable(false);
		
		lowRange = new JTextField();
		lowRange.setBounds(91, 274, 41, 26);
		contentPane.add(lowRange);
		lowRange.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("to");
		lblNewLabel_4.setBounds(131, 278, 18, 16);
		contentPane.add(lblNewLabel_4);
		
		highRange = new JTextField();
		highRange.setColumns(10);
		highRange.setBounds(144, 273, 41, 26);
		contentPane.add(highRange);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(163, 247, 503, 9);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_7 = new JLabel("Data");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_7.setBounds(5, 194, 41, 16);
		contentPane.add(lblNewLabel_7);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(39, 197, 145, 12);
		contentPane.add(separator_2);
		
		JLabel lblNewLabel_8 = new JLabel("Search & Match Parameters");
		lblNewLabel_8.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_8.setBounds(5, 312, 195, 16);
		contentPane.add(lblNewLabel_8);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(192, 314, 474, 9);
		contentPane.add(separator);
		
		JLabel lblNewLabel_9 = new JLabel("Search tolerance:");
		lblNewLabel_9.setBounds(15, 347, 114, 16);
		contentPane.add(lblNewLabel_9);
		
		searchTol = new JTextField();
		searchTol.setColumns(10);
		searchTol.setBounds(126, 342, 41, 26);
		contentPane.add(searchTol);
		
		ppmRadioBtn = new JRadioButton("ppm");
		ppmRadioBtn.setBounds(165, 342, 60, 23);
		contentPane.add(ppmRadioBtn);
		
		mzRadioBtn = new JRadioButton("m/z");
		mzRadioBtn.setBounds(223, 342, 60, 23);
		contentPane.add(mzRadioBtn);
		
		ButtonGroup searchTolGroup = new ButtonGroup();
		searchTolGroup.add(ppmRadioBtn);
		searchTolGroup.add(mzRadioBtn);
		
		JLabel lblNewLabel_10 = new JLabel("Search intensity threshold:");
		lblNewLabel_10.setBounds(15, 375, 174, 16);
		contentPane.add(lblNewLabel_10);
		
		searchIntensThresh = new JTextField();
		searchIntensThresh.setColumns(10);
		searchIntensThresh.setBounds(189, 370, 41, 26);
		contentPane.add(searchIntensThresh);
		
		countsRadioBtn = new JRadioButton("counts");
		countsRadioBtn.setBounds(226, 371, 75, 23);
		contentPane.add(countsRadioBtn);
		
		relResRadioBtn = new JRadioButton("relative resolution");
		relResRadioBtn.setBounds(296, 371, 152, 23);
		contentPane.add(relResRadioBtn);
		
		ButtonGroup searchIntensGroup = new ButtonGroup();
		searchIntensGroup.add(countsRadioBtn);
		searchIntensGroup.add(relResRadioBtn);
		
		JLabel lblNewLabel_11 = new JLabel("Compare to mass:");
		lblNewLabel_11.setBounds(15, 407, 117, 16);
		contentPane.add(lblNewLabel_11);
		
		monoRadioBtn = new JRadioButton("mono");
		monoRadioBtn.setBounds(130, 403, 68, 23);
		contentPane.add(monoRadioBtn);
		
		avgRadioBtn = new JRadioButton("average");
		avgRadioBtn.setBounds(195, 403, 80, 23);
		contentPane.add(avgRadioBtn);
		
		ButtonGroup predMassGroup = new ButtonGroup();
		predMassGroup.add(monoRadioBtn);
		predMassGroup.add(avgRadioBtn);
		
		dataLabel = new JLabel();
		dataLabel.setBounds(15, 90, 152, 16);
		contentPane.add(dataLabel);
		
		JButton btnImportDatabaseFile = new JButton("Import database file");
		btnImportDatabaseFile.setBounds(5, 130, 169, 29);
		contentPane.add(btnImportDatabaseFile);
		btnImportDatabaseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImportDBActionPerformed(e);
			}
		});
		
		databaseLabel = new JLabel();
		databaseLabel.setBounds(15, 160, 152, 16);
		contentPane.add(databaseLabel);
		
		JButton runBtn = new JButton("Run");
		runBtn.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		runBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runActionPerformed(e);
			}
		});
		runBtn.setBounds(420, 490, 117, 29);
		contentPane.add(runBtn);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(0, 438, 576, 9);
		contentPane.add(separator_1_1);
		
		JButton btnNewButton = new JButton("Choose location to save Excel file");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseLocationActionPerformed(e);
			}
		});
		btnNewButton.setBounds(5, 459, 242, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_6 = new JLabel("M/z Range:");
		lblNewLabel_6.setBounds(15, 279, 75, 16);
		contentPane.add(lblNewLabel_6);
		
		pathTextField = new JTextField();
		pathTextField.setBounds(12, 490, 348, 26);
		contentPane.add(pathTextField);
		pathTextField.setColumns(10);
		

	}
	
	/**
	 * Prints to status text area.
	 * @param str String to be printed.
	 */
	public void printStatus(String str) {
		statusArea.append("\n" + str);
	}
	
	/**
	 * Imports experimental data file.
	 * @param e
	 */
	public void btnImportTXTActionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Raw data files (*.txt)", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int option = fileChooser.showOpenDialog(contentPane);
        if(option == JFileChooser.APPROVE_OPTION) {
    		txtFile = fileChooser.getSelectedFile();
        	try {
        		printStatus("File selected: " + txtFile.getName());
        		fileViewer.read( new FileReader( txtFile.getAbsolutePath() ), null );
        		dataLabel.setText(txtFile.getName());
        	}
        	catch (IOException exc) {
        		printStatus("Problem accessing file "+ txtFile.getAbsolutePath() + exc);
        	}
           
        }else {
        	printStatus("Open command canceled.");
        }
        
	}
	
	/**
	 * Imports database file.
	 * @param e
	 */
	public void btnImportDBActionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Database file t.TAB", "tab"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int option = fileChooser.showOpenDialog(contentPane);
        if(option == JFileChooser.APPROVE_OPTION) {
    		databaseFile = fileChooser.getSelectedFile();
        	try {
        		printStatus("File selected: " + databaseFile.getName());
        		fileViewer.read( new FileReader( databaseFile.getAbsolutePath() ), null );
        		databaseLabel.setText(databaseFile.getName());
        	}
        	catch (IOException exc) {
        		printStatus("Problem accessing file "+ databaseFile.getAbsolutePath() + exc);
        	}
           
        }else {
        	printStatus("Open command canceled.");
        }
                               
	}
	
	/**
	 * Selects a folder to save excel file to.
	 * @param e
	 */
	public void chooseLocationActionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		int option = fileChooser.showOpenDialog(contentPane);
        if(option == JFileChooser.APPROVE_OPTION) {
        	File file = fileChooser.getSelectedFile();
        	filePath = file.getAbsolutePath();
        	
        	//checking operating system in order to save file
			if(System.getProperty("os.name").substring(0,3).equalsIgnoreCase("win")) //if Windows
			{
				filePath += "\\MatchedMods.xlsx";
			} else { //if Linux or Mac
				filePath += "/MatchedMods.xlsx";
			}
			
        	pathTextField.setText(filePath);
        }
	}
	
	/**
	 * Runs program.
	 * @param e
	 */
	public void runActionPerformed(ActionEvent e) {
		ValuesFromGUI values = new ValuesFromGUI();
		
		//sends parameters to ValuesFromGUI object
		
		if(posPolarRadioBtn.isSelected()) {
			values.setPosPolarity(true);
		}
				
		values.setLowRange(Double.valueOf(lowRange.getText()));
		values.setHighRange(Double.valueOf(highRange.getText()));
		
		values.setSearchTol(Double.valueOf(searchTol.getText()));
		
		if(ppmRadioBtn.isSelected()) {
	    	values.setppmSearchType(true);
	    }
		
		values.setSearchIntensThresh(Double.valueOf(searchIntensThresh.getText()));

		if(relResRadioBtn.isSelected()) {
			values.setSearchRelIntens(true);
		}
		
		if(monoRadioBtn.isSelected()) {
			values.setMassType('m');
		}
		else
		{
			values.setMassType('a');
		}
		
		values.setFilePath(pathTextField.getText());
		
		ModMatching modMatching = new ModMatching();
    	modMatching.setValuesFromGUI(values);
		
		//prints set parameters to status area
    	double actualTol = 0;;
		printStatus("");
		printStatus("Parameters set:");
		if(values.getPosPolarity()) {
			printStatus("- Positive polarity");
		}
		else {
			printStatus("- Negative polarity");
		}
		
		printStatus("- Range: " + values.getLowRange() + " to " + values.getHighRange());
    	
    	if(values.getppmSearchType()) {
    		actualTol = modMatching.calcTolerance(values.getSearchTol(), values.getLowRange());
        	printStatus("- Search tolerance: " + values.getSearchTol() + " ppm = " 
        					+ actualTol + " m/z");
    	}
    	else {
    		printStatus("- Search tolerance: " + values.getSearchTol() + " m/z");
    	}  	
    	
    	if(values.getSearchRelIntens()) {
        	printStatus("- Intensity threshold: " + values.getSearchIntensThresh() + " relative resolution");
    	}
    	else {
    		printStatus("- Intensity threshold: " + values.getSearchIntensThresh() + " counts");
    	}
    	
    	if(values.getMassType() == 'm') {
    		printStatus("- Compare to mono mass");
    	}
    	else {
    		printStatus("- Compare to average mass");
    	}
    	
//    	System.out.println("C: " + (305.08506 + 18 - 1.007825 - actualTol) + " to " + (305.08506 + 18 - 1.007825 + actualTol));
//    	System.out.println("U: " + (306.06978 + 18 - 1.007825 - actualTol) + " to " + (306.06978 + 18 - 1.007825 + actualTol));
//    	System.out.println("A: " + (329.09914 + 18 - 1.007825 - actualTol) + " to " + (329.09914 + 18 - 1.007825 + actualTol));
//    	System.out.println("G: " + (345.09854 + 18 - 1.007825 - actualTol) + " to " + (345.09854 + 18 - 1.007825 + actualTol));
    	
    	//matches mods
    	ArrayList<MatchedMods> findModsArray = modMatching.findMods(txtFile.getAbsolutePath(), databaseFile.getAbsolutePath());
		
    	//creates excel file
		WriteToExcel writeExcel = new WriteToExcel(findModsArray, values.getFilePath());
		writeExcel.createXLSXFile();
		printStatus("Excel file created.");
		
	}
}


