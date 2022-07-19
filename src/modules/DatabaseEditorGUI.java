package modules;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JLabel;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;

public class DatabaseEditorGUI extends JFrame {

	private JPanel contentPane;			//content pane
	
	private File databaseFile;			//database file
	private JTextArea fileViewer;		//shows file
	private JLabel databaseLabel;		//database label
	private JTextArea statusArea;		//prints status

	private JTextField name1;			//add name
	private JTextField symbol1;			//add symbol
	private JTextField mono1;			//add mono mass
	private JTextField avg1;			//add avg mass
	
	private JTextField searchEdit;		//search for editing current mod
	private JTextField name2;			//edit name
	private JTextField symbol2;			//edit symbol
	private JTextField mono2;			//edit mono mass
	private JTextField avg2;			//edit avg mass
	
	private JTextField searchDelete;	//search for deleting mod
	private JTextField name3;			//name
	private JTextField symbol3;			//symbol
	private JTextField mono3;			//mono mass
	private JTextField avg3;			//avg mass

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseEditorGUI frame = new DatabaseEditorGUI();
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
	public DatabaseEditorGUI() {
		setTitle("Database Editor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 80, 920, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JToolBar toolbar = new JToolBar();
		toolbar.setBounds(5, 0, 200, 30);
		
		JButton matcherBtn = new JButton("Mod Matcher");
		matcherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myGUI mainMatcher = new myGUI();
				mainMatcher.setVisible(true);
				dispose();
			}
		});
		
		JButton dbEditBtn = new JButton("Database Editor");
		
		toolbar.add(matcherBtn);
		toolbar.add(dbEditBtn);
		
		contentPane.add(toolbar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 700, 170);
		contentPane.add(scrollPane);
		
		fileViewer = new JTextArea(8, 55);
		scrollPane.setViewportView(fileViewer);
		fileViewer.setEditable(false);
		
		JButton importBtn = new JButton("Import database file");
		importBtn.setBounds(15, 42, 170, 29);
		contentPane.add(importBtn);
		importBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImportDBActionPerformed(e);
			}
		});
		
		databaseLabel = new JLabel("File: ");
		databaseLabel.setBounds(190, 47, 170, 16);
		contentPane.add(databaseLabel);
		
		JLabel lblNewLabel = new JLabel("Add Mod to Database");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(15, 247, 147, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(124, 275, 41, 16);
		contentPane.add(lblNewLabel_1);
		
		name1 = new JTextField();
		name1.setBounds(20, 294, 247, 26);
		contentPane.add(name1);
		name1.setColumns(10);
		
		symbol1 = new JTextField();
		symbol1.setBounds(297, 294, 103, 26);
		contentPane.add(symbol1);
		symbol1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Symbol");
		lblNewLabel_2.setBounds(327, 275, 51, 16);
		contentPane.add(lblNewLabel_2);
		
		mono1 = new JTextField();
		mono1.setBounds(430, 294, 130, 26);
		contentPane.add(mono1);
		mono1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mono");
		lblNewLabel_3.setBounds(478, 275, 41, 16);
		contentPane.add(lblNewLabel_3);
		
		avg1 = new JTextField();
		avg1.setColumns(10);
		avg1.setBounds(590, 294, 130, 26);
		contentPane.add(avg1);
		
		JLabel lblNewLabel_4 = new JLabel("Avg");
		lblNewLabel_4.setBounds(644, 275, 30, 16);
		contentPane.add(lblNewLabel_4);
		
		JButton addBtn = new JButton("Add");
		addBtn.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		addBtn.setBounds(805, 285, 67, 35);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMod(e);
			}
		});
		contentPane.add(addBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(163, 250, 737, 12);
		contentPane.add(separator);
		
		JLabel lblEditCurrentMod = new JLabel("Edit Current Mod in Database");
		lblEditCurrentMod.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblEditCurrentMod.setBounds(15, 332, 200, 16);
		contentPane.add(lblEditCurrentMod);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(384, 335, 516, 12);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Search Mod Name or Symbol");
		lblNewLabel_1_1.setBounds(40, 360, 179, 16);
		contentPane.add(lblNewLabel_1_1);
		
		searchEdit = new JTextField();
		searchEdit.setColumns(10);
		searchEdit.setBounds(20, 379, 216, 26);
		contentPane.add(searchEdit);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(268, 344, 12, 79);
		contentPane.add(separator_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setBounds(361, 359, 41, 16);
		contentPane.add(lblNewLabel_1_2);
		
		name2 = new JTextField();
		name2.setColumns(10);
		name2.setBounds(279, 379, 207, 26);
		contentPane.add(name2);
		
		symbol2 = new JTextField();
		symbol2.setColumns(10);
		symbol2.setBounds(498, 378, 75, 26);
		contentPane.add(symbol2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Symbol");
		lblNewLabel_2_1.setBounds(510, 359, 51, 16);
		contentPane.add(lblNewLabel_2_1);
		
		mono2 = new JTextField();
		mono2.setColumns(10);
		mono2.setBounds(585, 379, 85, 26);
		contentPane.add(mono2);
		
		JLabel lblNewLabel_3_1 = new JLabel("Mono");
		lblNewLabel_3_1.setBounds(607, 359, 41, 16);
		contentPane.add(lblNewLabel_3_1);
		
		avg2 = new JTextField();
		avg2.setColumns(10);
		avg2.setBounds(682, 379, 85, 26);
		contentPane.add(avg2);
		
		JLabel lblNewLabel_4_1 = new JLabel("Avg");
		lblNewLabel_4_1.setBounds(710, 359, 30, 16);
		contentPane.add(lblNewLabel_4_1);
		
		JButton makeChangesBtn = new JButton("Make Changes");
		makeChangesBtn.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		makeChangesBtn.setBounds(780, 370, 121, 35);
		makeChangesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeChangesToMod(e);
			}
		});
		contentPane.add(makeChangesBtn);
		
		JLabel lblDeleteModFrom = new JLabel("Delete Mod From Database");
		lblDeleteModFrom.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDeleteModFrom.setBounds(15, 422, 200, 16);
		contentPane.add(lblDeleteModFrom);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Search Mod Name or Symbol");
		lblNewLabel_1_1_1.setBounds(40, 448, 179, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		searchDelete = new JTextField();
		searchDelete.setColumns(10);
		searchDelete.setBounds(20, 467, 216, 26);
		contentPane.add(searchDelete);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setBounds(268, 433, 12, 79);
		contentPane.add(separator_2_1);
		
		name3 = new JTextField();
		name3.setBackground(Color.WHITE);
		name3.setEditable(false);
		name3.setColumns(10);
		name3.setBounds(279, 466, 207, 26);
		contentPane.add(name3);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Name");
		lblNewLabel_1_2_1.setBounds(361, 446, 41, 16);
		contentPane.add(lblNewLabel_1_2_1);
		
		symbol3 = new JTextField();
		symbol3.setBackground(Color.WHITE);
		symbol3.setEditable(false);
		symbol3.setColumns(10);
		symbol3.setBounds(498, 465, 75, 26);
		contentPane.add(symbol3);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Symbol");
		lblNewLabel_2_1_1.setBounds(510, 446, 51, 16);
		contentPane.add(lblNewLabel_2_1_1);
		
		mono3 = new JTextField();
		mono3.setEditable(false);
		mono3.setColumns(10);
		mono3.setBounds(585, 466, 85, 26);
		contentPane.add(mono3);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Mono");
		lblNewLabel_3_1_1.setBounds(607, 446, 41, 16);
		contentPane.add(lblNewLabel_3_1_1);
		
		avg3 = new JTextField();
		avg3.setEditable(false);
		avg3.setColumns(10);
		avg3.setBounds(682, 466, 85, 26);
		contentPane.add(avg3);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Avg");
		lblNewLabel_4_1_1.setBounds(710, 446, 30, 16);
		contentPane.add(lblNewLabel_4_1_1);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		deleteBtn.setBounds(805, 455, 75, 35);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMod(e);
			}
		});
		contentPane.add(deleteBtn);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(5, 511, 895, 12);
		contentPane.add(separator_1_1_1);
		
		JButton goBtn1 = new JButton("Go");
		goBtn1.setBounds(233, 377, 41, 29);
		goBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchModForEdit(e);
			}
		});
		contentPane.add(goBtn1);
		
		JButton goBtn2 = new JButton("Go");
		goBtn2.setBounds(233, 465, 41, 29);
		goBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchModForDelete(e);
			}
		});
		contentPane.add(goBtn2);
		
		JLabel lblNewLabel_5 = new JLabel("Status");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_5.setBounds(785, 47, 41, 16);
		contentPane.add(lblNewLabel_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(725, 70, 170, 170);
		contentPane.add(scrollPane_1);
		
		statusArea = new JTextArea();
		scrollPane_1.setViewportView(statusArea);
		
		JButton clearBtn = new JButton("Clear all fields");
		clearBtn.setBounds(783, 517, 117, 29);
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields(e);
			}
		});
		contentPane.add(clearBtn);
		
		JLabel lblNewLabel_6 = new JLabel("Make edits here:");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNewLabel_6.setBounds(279, 332, 111, 16);
		contentPane.add(lblNewLabel_6);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(216, 335, 58, 12);
		contentPane.add(separator_3);
		
		JLabel lblNewLabel_6_1 = new JLabel("Confirm:");
		lblNewLabel_6_1.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblNewLabel_6_1.setBounds(279, 422, 67, 16);
		contentPane.add(lblNewLabel_6_1);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setBounds(200, 425, 67, 12);
		contentPane.add(separator_3_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(340, 425, 560, 12);
		contentPane.add(separator_1_1);
		
	}
	
	/**
	 * Prints status to status text area.
	 * @param str String to be printed.
	 */
	public void printStatus(String str) {
		statusArea.append("\n" + str);
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
        		fileViewer.read( new FileReader( databaseFile.getAbsolutePath() ), null );
        		databaseLabel.setText("File: " + databaseFile.getName());
        		printStatus("File selected: " + databaseFile.getName());
        	}
        	catch (IOException exc) {
        		exc.printStackTrace();
        	}
           
        }
                               
	}
	
	/**
	 * Adds mod to database file.
	 * @param e
	 */
	public void addMod(ActionEvent e) {
		String name = name1.getText();
		String symbol = symbol1.getText();
		String mono = mono1.getText();
		String avg = avg1.getText();
		
		try {
			
			if(name != null) {
				FileWriter writer = new FileWriter(databaseFile.getAbsolutePath(), true);
				
				writer.write(System.getProperty("line.separator") + name + "\t" + symbol + "\t" + mono + "\t" + avg);
							
				fileViewer.read( new FileReader( databaseFile.getAbsolutePath() ), null );
				
				printStatus("Added " + name1.getText());
				
				writer.close();
			}
			else {
				printStatus("Please enter mod name");
			}
			
			
			
		} catch (IOException e1) {
			printStatus("Could not add mod");
			e1.printStackTrace();
		}
	}
	
	/**
	 * Searches mod for editing mod.
	 * @param e
	 */
	public void searchModForEdit(ActionEvent e) {
		
		String nameOrSym = searchEdit.getText();
		
		String wholeLine; 
        String[] tokens;
        boolean found = false;

		try {
			Scanner inFile = new Scanner(new File(databaseFile.getAbsolutePath()));
			while(inFile.hasNextLine()) {
        		wholeLine = inFile.nextLine();
        		tokens = wholeLine.split("\\t");
        		
        		if(wholeLine.length() > 0)
        		{
        			if(wholeLine.charAt(0) != '=') {
            			if(tokens[0].equalsIgnoreCase(nameOrSym) || tokens[1].equalsIgnoreCase(nameOrSym)) {
            				name2.setText(tokens[0]);
            				symbol2.setText(tokens[1]);
            				mono2.setText(tokens[2]);
            				avg2.setText(tokens[3]);
            				found = true;
            			}
            		}
        		}
			}
			
			if(found == false) {
				printStatus(nameOrSym + " NOT FOUND");
				name2.setText("MOD NOT FOUND");
				symbol2.setText(null);
				mono2.setText(null);
				avg2.setText(null);
			}
		}
		catch (FileNotFoundException exc) {
			exc.printStackTrace();
		}
	}
	
	/**
	 * Makes changes to mod in database.
	 * @param e
	 */
	public void makeChangesToMod(ActionEvent e) {
		
		String nameOrSym = searchEdit.getText();
		
		String newName, newSym, newMono, newAvg;
		
		newName = name2.getText();
		newSym = symbol2.getText();
		newMono = mono2.getText();
		newAvg = avg2.getText();
		
		String wholeLine; 
        String[] tokens;
        File tempFile = new File("myTempFile.txt");

		try {
			FileWriter writer = new FileWriter(tempFile);
			Scanner inFile = new Scanner(new File(databaseFile.getAbsolutePath()));
			
			while(inFile.hasNextLine()) {
        		wholeLine = inFile.nextLine();
        		tokens = wholeLine.split("\\t");
        		
        		if(wholeLine.length() > 0) {
        			if(wholeLine.charAt(0) == '=') {
                		writer.write(wholeLine + System.getProperty("line.separator"));
            		}
            		else if(tokens[0].equalsIgnoreCase(nameOrSym) || tokens[1].equalsIgnoreCase(nameOrSym)){
            			writer.write(newName + "\t" + newSym + "\t" + newMono + "\t" + newAvg + System.getProperty("line.separator"));
            		}
            		else {
                		writer.write(wholeLine + System.getProperty("line.separator"));
            		}
        		}
			}
			writer.close(); 
			inFile.close();
						
		    //Delete the original file
			if (!databaseFile.delete()) {
//		        System.out.println("Could not delete file");
				printStatus("Could not rewrite file");
		        return;
		      }

		    //Rename the new file to the filename the original file had.
		    if (!tempFile.renameTo(databaseFile))
		    {
//		    	System.out.println("Could not rename file");
				printStatus("Could not rewrite file");
		    }
		    
			fileViewer.read( new FileReader( databaseFile.getAbsolutePath() ), null );
			
			printStatus("Changed " + nameOrSym);

		}
		catch (FileNotFoundException exc) {
			exc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
	
	/**
	 * Search mod for delete function.
	 * @param e
	 */
	public void searchModForDelete(ActionEvent e) {
		
		String nameOrSym = searchDelete.getText();
		
		String wholeLine; 
        String[] tokens;
        boolean found = false;

		try {
			Scanner inFile = new Scanner(new File(databaseFile.getAbsolutePath()));
			while(inFile.hasNextLine()) {
        		wholeLine = inFile.nextLine();
        		tokens = wholeLine.split("\\t");
        		
        		if(wholeLine.length() > 0)
        		{
	        		if(wholeLine.charAt(0) != '=') {
	        			if(tokens[0].equalsIgnoreCase(nameOrSym) || tokens[1].equalsIgnoreCase(nameOrSym)) {
	        				name3.setText(tokens[0]);
	        				symbol3.setText(tokens[1]);
	        				mono3.setText(tokens[2]);
	        				avg3.setText(tokens[3]);
	        				found = true;
	        			}
	        		}
        		}
			}
			
			if(found == false) {
				printStatus(nameOrSym + " MOD NOT FOUND");
				name3.setText("MOD NOT FOUND");
				symbol3.setText(null);
				mono3.setText(null);
				avg3.setText(null);
			}
		}
		catch (FileNotFoundException exc) {
			exc.printStackTrace();
		}
	}
	
	/**
	 * Deletes mod from database.
	 * @param e
	 */
	public void deleteMod(ActionEvent e) {
		
		String nameOrSym = searchDelete.getText();
		
		String wholeLine; 
        String[] tokens;
        File tempFile = new File("myTempFile.txt");

		try {
			FileWriter writer = new FileWriter(tempFile);
			Scanner inFile = new Scanner(new File(databaseFile.getAbsolutePath()));
			
			while(inFile.hasNextLine()) {
        		wholeLine = inFile.nextLine();
        		tokens = wholeLine.split("\\t");
        		
        		if(wholeLine.length() > 0)
        		{
	        		if(wholeLine.charAt(0) == '=') {
	            		writer.write(wholeLine + System.getProperty("line.separator"));
	        		}
	        		else if(tokens[0].equalsIgnoreCase(nameOrSym) || tokens[1].equalsIgnoreCase(nameOrSym)){
	        			continue;
	        		}
	        		else {
	            		writer.write(wholeLine + System.getProperty("line.separator"));
	        		}
        		}
			}
			writer.close(); 
			inFile.close();
						
		    //Delete the original file
			if (!databaseFile.delete()) {
		        printStatus("Could not delete mod");
		        return;
		      }

		    //Rename the new file to the filename the original file had.
		    if (!tempFile.renameTo(databaseFile))
		    {
		    	printStatus("Could not delete mod");
		    	return;
		    }
		    
			fileViewer.read( new FileReader( databaseFile.getAbsolutePath() ), null );
			
			printStatus("Deleted " + nameOrSym);

		}
		catch (FileNotFoundException exc) {
			exc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
        
	}
	
	/**
	 * Clears all text fields.
	 * @param e
	 */
	public void clearFields(ActionEvent e) {
		name1.setText(null);
		symbol1.setText(null);
		mono1.setText(null);
		avg1.setText(null);
		searchEdit.setText(null);
		name2.setText(null);
		symbol2.setText(null);
		mono2.setText(null);
		avg2.setText(null);
		searchDelete.setText(null);
		name3.setText(null);
		symbol3.setText(null);
		mono3.setText(null);
		avg3.setText(null);
	}
}
