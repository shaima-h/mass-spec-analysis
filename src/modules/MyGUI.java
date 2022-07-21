package modules;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * GUI for matching mods.
 * @author shaimahussaini
 */
public class MyGUI extends JFrame {
	
	private File txtFile, databaseFile;		//experimental data file, database file
	private String filePath;				//file path to save file
	
	private JPanel contentPane;				//content pane
	private JTextArea fileViewer;			//file viewer
	private JTextArea statusArea;			//status area
	
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
					MyGUI frame = new MyGUI();
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
	  public MyGUI() {
	    setTitle("Mod Matcher");
	    setDefaultCloseOperation(3);
	    setBounds(150, 80, 920, 580);
	    
	    this.contentPane = new JPanel();
	    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(this.contentPane);
	    
	    GridBagLayout gbl_contentPane = new GridBagLayout();
	    gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 50 };
	    this.contentPane.setLayout(gbl_contentPane);
	    
	    JToolBar toolbar = new JToolBar();
	    
	    JButton matcherBtn = new JButton("Mod Matcher");
	    JButton dbEditBtn = new JButton("Database Editor");
	    dbEditBtn.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            DatabaseEditorGUI dbEditor = new DatabaseEditorGUI();
	            dbEditor.setVisible(true);
	            MyGUI.this.dispose();
	          }
	        });
	    
	    toolbar.add(matcherBtn);
	    toolbar.add(dbEditBtn);
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.anchor = 18;
	    gbc.weightx = 0.1D;
	    gbc.weighty = 0.1D;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    this.contentPane.add(toolbar, gbc);
	    
	    JButton btnImportTXT = new JButton("Import data file");
	    btnImportTXT.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            MyGUI.this.btnImportTXTActionPerformed(e);
	          }
	        });
	    GridBagConstraints gbc1 = new GridBagConstraints();
	    gbc1.weightx = 0.1D;
	    gbc1.weighty = 0.1D;
	    gbc1.gridx = 0;
	    gbc1.gridy = 1;
	    this.contentPane.add(btnImportTXT, gbc1);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    GridBagConstraints gbc3 = new GridBagConstraints();
	    gbc3.weightx = 0.1D;
	    gbc3.weighty = 0.1D;
	    gbc3.gridx = 1;
	    gbc3.gridy = 0;
	    gbc3.gridheight = 3;
	    gbc3.gridwidth = 6;
	    gbc3.fill = 1;
	    this.contentPane.add(scrollPane, gbc3);
	    
	    this.fileViewer = new JTextArea();
	    scrollPane.setViewportView(this.fileViewer);
	    this.fileViewer.setEditable(false);
	    
	    this.posPolarRadioBtn = new JRadioButton("pos");
	//    this.posPolarRadioBtn.setBounds(70, 214, 54, 23);
	    GridBagConstraints gbc4 = new GridBagConstraints();
	    gbc4.weightx = 0.1D;
	    gbc4.weighty = 0.1D;
	    gbc4.gridx = 1;
	    gbc4.gridy = 4;
	    gbc4.gridheight = 1;
	    gbc4.gridwidth = 1;
	    gbc4.fill = 3;
	    this.contentPane.add(this.posPolarRadioBtn, gbc4);
	    
	    JLabel lblNewLabel_2 = new JLabel("Polarity:  ");
	//    lblNewLabel_2.setBounds(16, 218, 51, 16);
	    GridBagConstraints gbc5 = new GridBagConstraints();
	    gbc5.anchor = 13;
	    gbc5.weightx = 0.5D;
	    gbc5.weighty = 0.1D;
	    gbc5.gridx = 0;
	    gbc5.gridy = 4;
	    gbc5.gridheight = 1;
	    gbc5.gridwidth = 1;
	    gbc5.fill = 3;
	    this.contentPane.add(lblNewLabel_2, gbc5);
	    
	    this.negPolarRadioBtn = new JRadioButton("neg");
	//    this.negPolarRadioBtn.setBounds(117, 214, 57, 23);
	    GridBagConstraints gbc6 = new GridBagConstraints();
	    gbc6.weightx = 0.1D;
	    gbc6.weighty = 0.1D;
	    gbc6.gridx = 2;
	    gbc6.gridy = 4;
	    gbc6.gridheight = 1;
	    gbc6.gridwidth = 1;
	    gbc6.fill = 1;
	    this.contentPane.add(this.negPolarRadioBtn, gbc6);
	    
	    ButtonGroup polGroup = new ButtonGroup();
	    polGroup.add(this.posPolarRadioBtn);
	    polGroup.add(this.negPolarRadioBtn);
	    
	    JLabel lblNewLabel_1 = new JLabel("Status");
	//    lblNewLabel_1.setBounds(717, 220, 41, 16);
	    lblNewLabel_1.setFont(new Font("Lucida Grande", 1, 13));
	    GridBagConstraints gbc7 = new GridBagConstraints();
	    gbc7.weightx = 0.1D;
	    gbc7.weighty = 0.1D;
	    gbc7.gridx = 4;
	    gbc7.gridy = 3;
	    gbc7.gridheight = 1;
	    gbc7.gridwidth = 3;
	    gbc7.fill = 3;
	    this.contentPane.add(lblNewLabel_1, gbc7);
	    
	    JSeparator separator_2 = new JSeparator();
	    GridBagConstraints gbc8_1 = new GridBagConstraints();
	    gbc8_1.anchor = 11;
	    gbc8_1.weightx = 0.5D;
	    gbc8_1.weighty = 0.1D;
	    gbc8_1.gridx = 0;
	    gbc8_1.gridy = 5;
	    gbc8_1.gridheight = 1;
	    gbc8_1.gridwidth = 6;
	    gbc8_1.fill = 2;
	    this.contentPane.add(separator_2, gbc8_1);
	    
	    JSeparator separatorr_2 = new JSeparator();
	    GridBagConstraints gbc8_2 = new GridBagConstraints();
	    gbc8_2.anchor = 15;
	    gbc8_2.weightx = 0.5D;
	    gbc8_2.weighty = 0.1D;
	    gbc8_2.gridx = 0;
	    gbc8_2.gridy = 5;
	    gbc8_2.gridheight = 1;
	    gbc8_2.gridwidth = 6;
	    gbc8_2.fill = 2;
	    this.contentPane.add(separatorr_2, gbc8_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("Processing Parameters");
	//    lblNewLabel_3.setBounds(5, 245, 154, 16);
	    lblNewLabel_3.setFont(new Font("Lucida Grande", 1, 13));
	    GridBagConstraints gbc8 = new GridBagConstraints();
	    gbc8.weightx = 0.5D;
	    gbc8.weighty = 0.1D;
	    gbc8.gridx = 0;
	    gbc8.gridy = 5;
	    gbc8.gridheight = 1;
	    gbc8.gridwidth = 1;
	    gbc8.fill = 1;
	    this.contentPane.add(lblNewLabel_3, gbc8);
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    this.statusArea = new JTextArea();
	    scrollPane_1.setViewportView(this.statusArea);
	    this.statusArea.setEditable(false);
	    GridBagConstraints gbc9 = new GridBagConstraints();
	    gbc9.weightx = 5.0D;
	    gbc9.weighty = 0.1D;
	    gbc9.gridx = 6;
	    gbc9.gridy = 4;
	    gbc9.gridheight = 9;
	    gbc9.gridwidth = 1;
	    gbc9.fill = 1;
	    this.contentPane.add(scrollPane_1, gbc9);
	    
	    this.lowRange = new JTextField();
	    this.lowRange.setColumns(5);
	    GridBagConstraints gbc10 = new GridBagConstraints();
	    gbc10.fill = 2;
	    gbc10.weightx = 0.1D;
	    gbc10.weighty = 0.1D;
	    gbc10.gridx = 1;
	    gbc10.gridy = 6;
	    gbc10.gridheight = 1;
	    gbc10.gridwidth = 1;
	    this.contentPane.add(this.lowRange, gbc10);
	    
	    JLabel lblNewLabel_4 = new JLabel("to");
	//    lblNewLabel_4.setBounds(131, 278, 18, 16);
	    GridBagConstraints gbc11 = new GridBagConstraints();
	    gbc11.weightx = 0.1D;
	    gbc11.weighty = 0.1D;
	    gbc11.gridx = 2;
	    gbc11.gridy = 6;
	    gbc11.gridheight = 1;
	    gbc11.gridwidth = 1;
	    this.contentPane.add(lblNewLabel_4, gbc11);
	    
	    this.highRange = new JTextField();
	    this.highRange.setColumns(5);
	//    this.highRange.setBounds(144, 273, 41, 26);
	    GridBagConstraints gbc12 = new GridBagConstraints();
	    gbc12.fill = 2;
	    gbc12.weightx = 0.1D;
	    gbc12.weighty = 0.1D;
	    gbc12.gridx = 3;
	    gbc12.gridy = 6;
	    gbc12.gridheight = 1;
	    gbc12.gridwidth = 1;
	    this.contentPane.add(this.highRange, gbc12);
	    
	    JSeparator separator_1 = new JSeparator();
	    GridBagConstraints gbc13_1 = new GridBagConstraints();
	    gbc13_1.anchor = 11;
	    gbc13_1.weightx = 0.5D;
	    gbc13_1.weighty = 0.1D;
	    gbc13_1.gridx = 0;
	    gbc13_1.gridy = 3;
	    gbc13_1.gridheight = 1;
	    gbc13_1.gridwidth = 7;
	    gbc13_1.fill = 2;
	    this.contentPane.add(separator_1, gbc13_1);
	    
	    JSeparator separatorr_1 = new JSeparator();
	    GridBagConstraints gbc13_2 = new GridBagConstraints();
	    gbc13_2.anchor = 15;
	    gbc13_2.weightx = 0.5D;
	    gbc13_2.weighty = 0.1D;
	    gbc13_2.gridx = 0;
	    gbc13_2.gridy = 3;
	    gbc13_2.gridheight = 1;
	    gbc13_2.gridwidth = 7;
	    gbc13_2.fill = 2;
	    this.contentPane.add(separatorr_1, gbc13_2);
	    
	    JLabel lblNewLabel_7 = new JLabel("Data");
	    lblNewLabel_7.setFont(new Font("Lucida Grande", 1, 13));
	//    lblNewLabel_7.setBounds(5, 194, 41, 16);
	    GridBagConstraints gbc13 = new GridBagConstraints();
	    gbc13.anchor = 17;
	    gbc13.weightx = 0.5D;
	    gbc13.weighty = 0.1D;
	    gbc13.gridx = 0;
	    gbc13.gridy = 3;
	    gbc13.gridheight = 1;
	    gbc13.gridwidth = 1;
	    gbc13.fill = 3;
	    this.contentPane.add(lblNewLabel_7, gbc13);
	    
	    JLabel lblNewLabel_8 = new JLabel("Search & Match Parameters");
	    lblNewLabel_8.setFont(new Font("Lucida Grande", 1, 13));
	//    lblNewLabel_8.setBounds(5, 312, 195, 16);
	    GridBagConstraints gbc14 = new GridBagConstraints();
	    gbc14.anchor = 17;
	    gbc14.weightx = 0.5D;
	    gbc14.weighty = 0.1D;
	    gbc14.gridx = 0;
	    gbc14.gridy = 7;
	    gbc14.gridheight = 1;
	    gbc14.gridwidth = 1;
	    gbc14.fill = 3;
	    this.contentPane.add(lblNewLabel_8, gbc14);
	    
	    JSeparator separator = new JSeparator();
	//    separator.setBounds(192, 314, 474, 9);
	    GridBagConstraints gbc14_1 = new GridBagConstraints();
	    gbc14_1.anchor = 15;
	    gbc14_1.weightx = 0.5D;
	    gbc14_1.weighty = 0.1D;
	    gbc14_1.gridx = 0;
	    gbc14_1.gridy = 7;
	    gbc14_1.gridheight = 1;
	    gbc14_1.gridwidth = 6;
	    gbc14_1.fill = 2;
	    this.contentPane.add(separator, gbc14_1);
	    
	    JSeparator separatorr = new JSeparator();
	//    separator.setBounds(192, 314, 474, 9);
	    GridBagConstraints gbc14_2 = new GridBagConstraints();
	    gbc14_2.anchor = 11;
	    gbc14_2.weightx = 0.5D;
	    gbc14_2.weighty = 0.1D;
	    gbc14_2.gridx = 0;
	    gbc14_2.gridy = 7;
	    gbc14_2.gridheight = 1;
	    gbc14_2.gridwidth = 6;
	    gbc14_2.fill = 2;
	    this.contentPane.add(separatorr, gbc14_2);
	    
	    JLabel lblNewLabel_9 = new JLabel("Search tolerance:  ");
	//    lblNewLabel_9.setBounds(15, 347, 114, 16);
	    GridBagConstraints gbc15 = new GridBagConstraints();
	    gbc15.anchor = 13;
	    gbc15.weightx = 0.5D;
	    gbc15.weighty = 0.1D;
	    gbc15.gridx = 0;
	    gbc15.gridy = 8;
	    gbc15.gridheight = 1;
	    gbc15.gridwidth = 1;
	    gbc15.fill = 3;
	    this.contentPane.add(lblNewLabel_9, gbc15);
	    
	    this.searchTol = new JTextField();
	    this.searchTol.setColumns(5);
	//    this.searchTol.setBounds(126, 342, 41, 26);
	    GridBagConstraints gbc16 = new GridBagConstraints();
	    gbc16.fill = 2;
	    gbc16.weightx = 0.1D;
	    gbc16.weighty = 0.1D;
	    gbc16.gridx = 1;
	    gbc16.gridy = 8;
	    gbc16.gridheight = 1;
	    gbc16.gridwidth = 1;
	    this.contentPane.add(this.searchTol, gbc16);
	    
	    this.ppmRadioBtn = new JRadioButton("ppm");
	//    this.ppmRadioBtn.setBounds(165, 342, 60, 23);
	    GridBagConstraints gbc17 = new GridBagConstraints();
	    gbc17.weightx = 0.1D;
	    gbc17.weighty = 0.1D;
	    gbc17.gridx = 2;
	    gbc17.gridy = 8;
	    gbc17.gridheight = 1;
	    gbc17.gridwidth = 1;
	    gbc17.fill = 1;
	    this.contentPane.add(this.ppmRadioBtn, gbc17);
	    
	    this.mzRadioBtn = new JRadioButton("m/z");
	//    this.mzRadioBtn.setBounds(223, 342, 60, 23);
	    GridBagConstraints gbc18 = new GridBagConstraints();
	    gbc18.weightx = 0.1D;
	    gbc18.weighty = 0.1D;
	    gbc18.gridx = 3;
	    gbc18.gridy = 8;
	    gbc18.gridheight = 1;
	    gbc18.gridwidth = 1;
	    gbc18.fill = 1;
	    this.contentPane.add(this.mzRadioBtn, gbc18);
	    
	    ButtonGroup searchTolGroup = new ButtonGroup();
	    searchTolGroup.add(this.ppmRadioBtn);
	    searchTolGroup.add(this.mzRadioBtn);
	    
	    JLabel lblNewLabel_10 = new JLabel("Search intensity threshold:  ");
	//    lblNewLabel_10.setBounds(15, 375, 174, 16);
	    GridBagConstraints gbc19 = new GridBagConstraints();
	    gbc19.anchor = 13;
	    gbc19.weightx = 0.5D;
	    gbc19.weighty = 0.1D;
	    gbc19.gridx = 0;
	    gbc19.gridy = 9;
	    gbc19.gridheight = 1;
	    gbc19.gridwidth = 1;
	    gbc19.fill = 3;
	    this.contentPane.add(lblNewLabel_10, gbc19);
	    
	    this.searchIntensThresh = new JTextField();
	    this.searchIntensThresh.setColumns(5);
	//    this.searchIntensThresh.setBounds(189, 370, 41, 26);
	    GridBagConstraints gbc20 = new GridBagConstraints();
	    gbc20.fill = 2;
	    gbc20.weightx = 0.1D;
	    gbc20.weighty = 0.1D;
	    gbc20.gridx = 1;
	    gbc20.gridy = 9;
	    gbc20.gridheight = 1;
	    gbc20.gridwidth = 1;
	    this.contentPane.add(this.searchIntensThresh, gbc20);
	    
	    this.countsRadioBtn = new JRadioButton("counts");
	//    this.countsRadioBtn.setBounds(226, 371, 75, 23);
	    GridBagConstraints gbc21 = new GridBagConstraints();
	    gbc21.weightx = 0.1D;
	    gbc21.weighty = 0.1D;
	    gbc21.gridx = 2;
	    gbc21.gridy = 9;
	    gbc21.gridheight = 1;
	    gbc21.gridwidth = 1;
	    gbc21.fill = 1;
	    this.contentPane.add(this.countsRadioBtn, gbc21);
	    
	    this.relResRadioBtn = new JRadioButton("rel. res.");
	//    this.relResRadioBtn.setBounds(296, 371, 152, 23);
	    GridBagConstraints gbc22 = new GridBagConstraints();
	    gbc22.weightx = 0.1D;
	    gbc22.weighty = 0.1D;
	    gbc22.gridx = 3;
	    gbc22.gridy = 9;
	    gbc22.gridheight = 1;
	    gbc22.gridwidth = 1;
	    gbc22.fill = 1;
	    this.contentPane.add(this.relResRadioBtn, gbc22);
	    
	    ButtonGroup searchIntensGroup = new ButtonGroup();
	    searchIntensGroup.add(this.countsRadioBtn);
	    searchIntensGroup.add(this.relResRadioBtn);
	    JLabel lblNewLabel_11 = new JLabel("Compare to mass:  ");
	//    lblNewLabel_11.setBounds(15, 407, 117, 16);
	    GridBagConstraints gbc23 = new GridBagConstraints();
	    gbc23.anchor = 13;
	    gbc23.weightx = 0.5D;
	    gbc23.weighty = 0.1D;
	    gbc23.gridx = 0;
	    gbc23.gridy = 10;
	    gbc23.gridheight = 1;
	    gbc23.gridwidth = 1;
	    gbc23.fill = 3;
	    this.contentPane.add(lblNewLabel_11, gbc23);
	    
	    this.monoRadioBtn = new JRadioButton("mono  ");
	//    this.monoRadioBtn.setBounds(130, 403, 68, 23);
	    GridBagConstraints gbc24 = new GridBagConstraints();
	    gbc24.weightx = 0.1D;
	    gbc24.weighty = 0.1D;
	    gbc24.gridx = 1;
	    gbc24.gridy = 10;
	    gbc24.gridheight = 1;
	    gbc24.gridwidth = 1;
	    gbc24.fill = 3;
	    this.contentPane.add(this.monoRadioBtn, gbc24);
	    
	    this.avgRadioBtn = new JRadioButton("average");
	//    this.avgRadioBtn.setBounds(195, 403, 80, 23);
	    GridBagConstraints gbc25 = new GridBagConstraints();
	    gbc25.weightx = 0.1D;
	    gbc25.weighty = 0.1D;
	    gbc25.gridx = 2;
	    gbc25.gridy = 10;
	    gbc25.gridheight = 1;
	    gbc25.gridwidth = 1;
	    gbc25.fill = 1;
	    this.contentPane.add(this.avgRadioBtn, gbc25);
	    
	    ButtonGroup predMassGroup = new ButtonGroup();
	    predMassGroup.add(this.monoRadioBtn);
	    predMassGroup.add(this.avgRadioBtn);
	    JButton btnImportDatabaseFile = new JButton("Import database file");
	    btnImportDatabaseFile.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            MyGUI.this.btnImportDBActionPerformed(e);
	          }
	        });
	    GridBagConstraints gbc27 = new GridBagConstraints();
	    gbc27.weightx = 0.1D;
	    gbc27.weighty = 0.1D;
	    gbc27.gridx = 0;
	    gbc27.gridy = 2;
	    gbc27.gridheight = 1;
	    gbc27.gridwidth = 1;
	    this.contentPane.add(btnImportDatabaseFile, gbc27);
	    
	    JButton runBtn = new JButton("Run");
	    runBtn.setFont(new Font("Lucida Grande", 1, 13));
	    runBtn.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            MyGUI.this.runActionPerformed(e);
	          }
	        });
	//    runBtn.setBounds(420, 490, 117, 29);
	    GridBagConstraints gbc29 = new GridBagConstraints();
	    gbc29.weightx = 0.1D;
	    gbc29.weighty = 0.1D;
	    gbc29.gridx = 3;
	    gbc29.gridy = 11;
	    gbc29.gridheight = 2;
	    gbc29.gridwidth = 1;
	    gbc29.fill = 2;
	    this.contentPane.add(runBtn, gbc29);
	    
	    JSeparator separator_1_1 = new JSeparator();
	//    separator_1_1.setBounds(0, 438, 576, 9);
	    JButton btnNewButton = new JButton("Choose location to save Excel file");
	    btnNewButton.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	            MyGUI.this.chooseLocationActionPerformed(e);
	          }
	        });
	//    btnNewButton.setBounds(5, 459, 242, 29);
	    GridBagConstraints gbc30 = new GridBagConstraints();
	    gbc30.anchor = 15;
	    gbc30.weightx = 0.1D;
	    gbc30.weighty = 0.1D;
	    gbc30.gridx = 0;
	    gbc30.gridy = 11;
	    gbc30.gridheight = 1;
	    gbc30.gridwidth = 2;
	    gbc30.fill = 2;
	    this.contentPane.add(btnNewButton, gbc30);
	    
	    JLabel lblNewLabel_6 = new JLabel("M/z Range:  ");
	//    lblNewLabel_6.setBounds(15, 279, 75, 16);
	    GridBagConstraints gbc31 = new GridBagConstraints();
	    gbc31.anchor = 13;
	    gbc31.weightx = 0.5D;
	    gbc31.weighty = 0.1D;
	    gbc31.gridx = 0;
	    gbc31.gridy = 6;
	    gbc31.gridheight = 1;
	    gbc31.gridwidth = 1;
	    gbc31.fill = 3;
	    this.contentPane.add(lblNewLabel_6, gbc31);
	    
	    this.pathTextField = new JTextField();
	//    this.pathTextField.setBounds(12, 490, 348, 26);
	    this.pathTextField.setColumns(10);
	    GridBagConstraints gbc32 = new GridBagConstraints();
	    gbc32.anchor = 11;
	    gbc32.weightx = 0.1D;
	    gbc32.weighty = 0.1D;
	    gbc32.gridx = 0;
	    gbc32.gridy = 12;
	    gbc32.gridheight = 1;
	    gbc32.gridwidth = 2;
	    gbc32.fill = 2;
	    this.contentPane.add(this.pathTextField, gbc32);
	  }
	  
		/**
		 * Prints to status text area.
		 * @param str String to be printed.
		 */
	  public void printStatus(String str) {
	    this.statusArea.append("\n" + str);
	  }
	  
		/**
		 * Imports experimental data file.
		 * @param e
		 */
	  public void btnImportTXTActionPerformed(ActionEvent e) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileFilter(new FileNameExtensionFilter("Raw data files (*.txt)", new String[] { "txt" }));
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    
	    int option = fileChooser.showOpenDialog(this.contentPane);
	    if (option == 0) {
	      this.txtFile = fileChooser.getSelectedFile();
	      try {
	        printStatus("Data file selected: " + this.txtFile.getName());
	        this.fileViewer.read(new FileReader(this.txtFile.getAbsolutePath()), (Object)null);
	      } catch (IOException exc) {
	        printStatus("Problem accessing file " + this.txtFile.getAbsolutePath() + exc);
	      } 
	    } else {
	      printStatus("Open command canceled.");
	    } 
	  }
	  
		/**
		 * Imports database file.
		 * @param e
		 */  
	  public void btnImportDBActionPerformed(ActionEvent e) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileFilter(new FileNameExtensionFilter("Database file t.TAB", new String[] { "tab" }));
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    
	    int option = fileChooser.showOpenDialog(this.contentPane);
	    if (option == 0) {
	      this.databaseFile = fileChooser.getSelectedFile();
	      try {
	        printStatus("Database file selected: " + this.databaseFile.getName());
	        this.fileViewer.read(new FileReader(this.databaseFile.getAbsolutePath()), (Object)null);
	      } catch (IOException exc) {
	        printStatus("Problem accessing file " + this.databaseFile.getAbsolutePath() + exc);
	      } 
	    } else {
	      printStatus("Open command canceled.");
	    } 
	  }
	  
		/**
		 * Selects a folder to save excel file to.
		 * @param e
		 */
	  public void chooseLocationActionPerformed(ActionEvent e) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(1);
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    
	    int option = fileChooser.showOpenDialog(this.contentPane);
	    if (option == 0) {
	      File file = fileChooser.getSelectedFile();
	      this.filePath = file.getAbsolutePath();
	      
      	//checking operating system in order to save file
	      if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("win")) { //if Windows
	        this.filePath = String.valueOf(this.filePath) + "\\MatchedMods.xlsx";
	      } else { //if Linux or Mac
	        this.filePath = String.valueOf(this.filePath) + "/MatchedMods.xlsx";
	      } 
	      this.pathTextField.setText(this.filePath);
	    } 
	  }
	  
		/**
		 * Runs program.
		 * @param e
		 */
	  public void runActionPerformed(ActionEvent e) {
	    ValuesFromGUI values = new ValuesFromGUI();
	    
		//sends parameters to ValuesFromGUI object
	    
	    if (this.posPolarRadioBtn.isSelected())
	      values.setPosPolarity(true); 
	    
	    values.setLowRange(Double.valueOf(this.lowRange.getText()).doubleValue());
	    values.setHighRange(Double.valueOf(this.highRange.getText()).doubleValue());
	    values.setSearchTol(Double.valueOf(this.searchTol.getText()).doubleValue());
	    
	    if (this.ppmRadioBtn.isSelected())
	      values.setppmSearchType(true); 
	    values.setSearchIntensThresh(Double.valueOf(this.searchIntensThresh.getText()).doubleValue());
	    
	    if (this.relResRadioBtn.isSelected())
	      values.setSearchRelIntens(true); 
	    
	    if (this.monoRadioBtn.isSelected()) {
	      values.setMassType('m');
	    } else {
	      values.setMassType('a');
	    } 
	    
	    values.setFilePath(this.pathTextField.getText());
	    
	    ModMatching modMatching = new ModMatching();
	    modMatching.setValuesFromGUI(values);
	    
		//prints set parameters to status area
	    double actualTol = 0.0D;
	    printStatus("");
	    printStatus("Parameters set:");
	    if (values.getPosPolarity()) {
	      printStatus("- Positive polarity");
	    } else {
	      printStatus("- Negative polarity");
	    } 
	    printStatus("- Range: " + values.getLowRange() + " to " + values.getHighRange());
	    if (values.getppmSearchType()) {
	      actualTol = modMatching.calcTolerance(values.getSearchTol(), values.getLowRange());
	      printStatus("- Search tolerance: " + values.getSearchTol() + " ppm = " + 
	          actualTol + " m/z");
	    } else {
	      printStatus("- Search tolerance: " + values.getSearchTol() + " m/z");
	    } 
	    if (values.getSearchRelIntens()) {
	      printStatus("- Intensity threshold: " + values.getSearchIntensThresh() + " relative resolution");
	    } else {
	      printStatus("- Intensity threshold: " + values.getSearchIntensThresh() + " counts");
	    } 
	    if (values.getMassType() == 'm') {
	      printStatus("- Compare to mono mass");
	    } else {
	      printStatus("- Compare to average mass");
	    } 
	    
    	//matches mods
	    ArrayList<MatchedMods> findModsArray = modMatching.findMods(this.txtFile.getAbsolutePath(), this.databaseFile.getAbsolutePath());
	    
    	//creates excel file
	    WriteToExcel writeExcel = new WriteToExcel(findModsArray, values.getFilePath());
	    writeExcel.createXLSXFile();
	    printStatus("Excel file created.");
	  }
}
