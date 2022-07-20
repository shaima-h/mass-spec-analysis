package modules;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DatabaseEditorGUI extends JFrame {
  private JPanel contentPane;
  
  private File databaseFile;
  
  private JTextArea fileViewer;
  
  private JLabel databaseLabel;
  
  private JTextArea statusArea;
  
  private JTextField name1;
  
  private JTextField symbol1;
  
  private JTextField mono1;
  
  private JTextField avg1;
  
  private JTextField searchEdit;
  
  private JTextField name2;
  
  private JTextField symbol2;
  
  private JTextField mono2;
  
  private JTextField avg2;
  
  private JTextField searchDelete;
  
  private JTextField name3;
  
  private JTextField symbol3;
  
  private JTextField mono3;
  
  private JTextField avg3;
  
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
  
  public DatabaseEditorGUI() {
    setTitle("Database Editor");
    setDefaultCloseOperation(3);
    setBounds(150, 80, 920, 580);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.rowHeights = new int[] { 
        0, 0, 0, 0, 21, 21, 22, 50, 31, 22, 
        64, 31 };
    this.contentPane.setLayout(gbl_contentPane);
    JToolBar toolbar = new JToolBar();
//    toolbar.setBounds(5, 0, 200, 30);
    JButton matcherBtn = new JButton("Mod Matcher");
    matcherBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            MyGUI mainMatcher = new MyGUI();
            mainMatcher.setVisible(true);
            DatabaseEditorGUI.this.dispose();
          }
        });
    JButton dbEditBtn = new JButton("Database Editor");
    toolbar.add(matcherBtn);
    toolbar.add(dbEditBtn);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = 18;
    gbc.weightx = 0.1D;
    gbc.weighty = 0.1D;
    gbc.gridx = 0;
    gbc.gridy = 0;
    this.contentPane.add(toolbar, gbc);
    JScrollPane scrollPane = new JScrollPane();
//    scrollPane.setBounds(20, 70, 700, 170);
    GridBagConstraints gbc1 = new GridBagConstraints();
    gbc1.weightx = 0.1D;
    gbc1.weighty = 0.1D;
    gbc1.gridx = 0;
    gbc1.gridy = 2;
    gbc1.gridheight = 2;
    gbc1.gridwidth = 5;
    gbc1.fill = 1;
    this.contentPane.add(scrollPane, gbc1);
    this.fileViewer = new JTextArea(8, 55);
    scrollPane.setViewportView(this.fileViewer);
    this.fileViewer.setEditable(false);
    JButton importBtn = new JButton("Import database file");
//    importBtn.setBounds(15, 42, 170, 29);
    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.anchor = 17;
    gbc2.weightx = 0.1D;
    gbc2.weighty = 0.1D;
    gbc2.gridx = 0;
    gbc2.gridy = 1;
    gbc2.gridheight = 1;
    gbc2.gridwidth = 1;
    this.contentPane.add(importBtn, gbc2);
    importBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DatabaseEditorGUI.this.btnImportDBActionPerformed(e);
          }
        });
    this.databaseLabel = new JLabel("File: ");
//    this.databaseLabel.setBounds(190, 47, 170, 16);
    GridBagConstraints gbc3 = new GridBagConstraints();
    gbc3.anchor = 17;
    gbc3.weightx = 0.1D;
    gbc3.weighty = 0.1D;
    gbc3.gridx = 1;
    gbc3.gridy = 1;
    gbc3.gridheight = 1;
    gbc3.gridwidth = 1;
    this.contentPane.add(this.databaseLabel, gbc3);
    JLabel lblNewLabel = new JLabel("Add Mod to Database");
    lblNewLabel.setFont(new Font("Lucida Grande", 1, 13));
//    lblNewLabel.setBounds(15, 247, 147, 16);
    GridBagConstraints gbc4 = new GridBagConstraints();
    gbc4.anchor = 17;
    gbc4.weightx = 0.1D;
    gbc4.weighty = 0.1D;
    gbc4.gridx = 0;
    gbc4.gridy = 4;
    gbc4.gridheight = 1;
    gbc4.gridwidth = 1;
    this.contentPane.add(lblNewLabel, gbc4);
    JLabel lblNewLabel_1 = new JLabel("Name");
//    lblNewLabel_1.setBounds(124, 275, 41, 16);
    GridBagConstraints gbc5 = new GridBagConstraints();
    gbc5.anchor = 15;
    gbc5.weightx = 0.1D;
    gbc5.weighty = 0.1D;
    gbc5.gridx = 0;
    gbc5.gridy = 5;
    gbc5.gridheight = 1;
    gbc5.gridwidth = 1;
    this.contentPane.add(lblNewLabel_1, gbc5);
    this.name1 = new JTextField();
//    this.name1.setBounds(20, 294, 247, 26);
    GridBagConstraints gbc6 = new GridBagConstraints();
    gbc6.fill = 2;
    gbc6.anchor = 11;
    gbc6.weightx = 0.1D;
    gbc6.weighty = 0.1D;
    gbc6.gridx = 0;
    gbc6.gridy = 6;
    gbc6.gridheight = 1;
    gbc6.gridwidth = 1;
    this.contentPane.add(this.name1, gbc6);
    this.name1.setColumns(10);
    this.symbol1 = new JTextField();
//    this.symbol1.setBounds(297, 294, 103, 26);
    GridBagConstraints gbc7 = new GridBagConstraints();
    gbc7.fill = 2;
    gbc7.anchor = 11;
    gbc7.weightx = 0.1D;
    gbc7.weighty = 0.1D;
    gbc7.gridx = 1;
    gbc7.gridy = 6;
    gbc7.gridheight = 1;
    gbc7.gridwidth = 1;
    this.contentPane.add(this.symbol1, gbc7);
    this.symbol1.setColumns(10);
    JLabel lblNewLabel_2 = new JLabel("Symbol");
//    lblNewLabel_2.setBounds(327, 275, 51, 16);
    GridBagConstraints gbc8 = new GridBagConstraints();
    gbc8.anchor = 15;
    gbc8.weightx = 0.1D;
    gbc8.weighty = 0.1D;
    gbc8.gridx = 1;
    gbc8.gridy = 5;
    gbc8.gridheight = 1;
    gbc8.gridwidth = 1;
    this.contentPane.add(lblNewLabel_2, gbc8);
    this.mono1 = new JTextField();
//    this.mono1.setBounds(430, 294, 130, 26);
    GridBagConstraints gbc9 = new GridBagConstraints();
    gbc9.fill = 2;
    gbc9.anchor = 11;
    gbc9.weightx = 0.1D;
    gbc9.weighty = 0.1D;
    gbc9.gridx = 2;
    gbc9.gridy = 6;
    gbc9.gridheight = 1;
    gbc9.gridwidth = 1;
    this.contentPane.add(this.mono1, gbc9);
    this.mono1.setColumns(10);
    JLabel lblNewLabel_3 = new JLabel("Mono");
//    lblNewLabel_3.setBounds(478, 275, 41, 16);
    GridBagConstraints gbc10 = new GridBagConstraints();
    gbc10.anchor = 15;
    gbc10.weightx = 0.1D;
    gbc10.weighty = 0.1D;
    gbc10.gridx = 2;
    gbc10.gridy = 5;
    gbc10.gridheight = 1;
    gbc10.gridwidth = 1;
    this.contentPane.add(lblNewLabel_3, gbc10);
    this.avg1 = new JTextField();
    this.avg1.setColumns(10);
//    this.avg1.setBounds(590, 294, 130, 26);
    GridBagConstraints gbc11 = new GridBagConstraints();
    gbc11.fill = 2;
    gbc11.anchor = 11;
    gbc11.weightx = 0.1D;
    gbc11.weighty = 0.1D;
    gbc11.gridx = 3;
    gbc11.gridy = 6;
    gbc11.gridheight = 1;
    gbc11.gridwidth = 1;
    this.contentPane.add(this.avg1, gbc11);
    JLabel lblNewLabel_4 = new JLabel("Avg");
//    lblNewLabel_4.setBounds(644, 275, 30, 16);
    GridBagConstraints gbc12 = new GridBagConstraints();
    gbc12.anchor = 15;
    gbc12.weightx = 0.1D;
    gbc12.weighty = 0.1D;
    gbc12.gridx = 3;
    gbc12.gridy = 5;
    gbc12.gridheight = 1;
    gbc12.gridwidth = 1;
    this.contentPane.add(lblNewLabel_4, gbc12);
    JButton addBtn = new JButton("Add");
    addBtn.setFont(new Font("Lucida Grande", 1, 13));
//    addBtn.setBounds(805, 285, 67, 35);
    addBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DatabaseEditorGUI.this.addMod(e);
          }
        });
    GridBagConstraints gbc13 = new GridBagConstraints();
    gbc13.anchor = 11;
    gbc13.gridx = 4;
    gbc13.gridy = 6;
    gbc13.gridheight = 1;
    gbc13.gridwidth = 1;
    this.contentPane.add(addBtn, gbc13);
    JSeparator separator = new JSeparator();
//    separator.setBounds(163, 250, 737, 12);
    GridBagConstraints gbc14_1 = new GridBagConstraints();
    gbc14_1.anchor = 11;
    gbc14_1.weightx = 0.1D;
    gbc14_1.weighty = 0.1D;
    gbc14_1.gridx = 0;
    gbc14_1.gridy = 7;
    gbc14_1.gridheight = 1;
    gbc14_1.gridwidth = 6;
    gbc14_1.fill = 2;
    this.contentPane.add(separator, gbc14_1);
    JLabel lblEditCurrentMod = new JLabel("Edit Current Mod in Database");
    lblEditCurrentMod.setFont(new Font("Lucida Grande", 1, 13));
//    lblEditCurrentMod.setBounds(15, 332, 200, 16);
    GridBagConstraints gbc14 = new GridBagConstraints();
    gbc14.anchor = 17;
    gbc14.weightx = 0.1D;
    gbc14.weighty = 0.1D;
    gbc14.gridx = 0;
    gbc14.gridy = 7;
    gbc14.gridheight = 1;
    gbc14.gridwidth = 1;
    gbc14.insets = new Insets(0, 0, 0, 0);
    this.contentPane.add(lblEditCurrentMod, gbc14);
    JSeparator separator_1 = new JSeparator();
//    separator_1.setBounds(384, 335, 516, 12);
    GridBagConstraints gbc15_1 = new GridBagConstraints();
    gbc15_1.anchor = 11;
    gbc15_1.weightx = 0.1D;
    gbc15_1.weighty = 0.2D;
    gbc15_1.gridx = 0;
    gbc15_1.gridy = 10;
    gbc15_1.gridheight = 1;
    gbc15_1.gridwidth = 6;
    gbc15_1.fill = 2;
    gbc15_1.anchor = GridBagConstraints.CENTER;
//    gbc15_1.insets = new Insets(10, 0, 0, 10);
    this.contentPane.add(separator_1, gbc15_1);
    JLabel lblNewLabel_1_1 = new JLabel("Search Mod Name or Symbol");
//    lblNewLabel_1_1.setBounds(40, 360, 179, 16);
    GridBagConstraints gbc15 = new GridBagConstraints();
    gbc15.anchor = 15;
    gbc15.weightx = 0.1D;
    gbc15.weighty = 0.1D;
    gbc15.gridx = 0;
    gbc15.gridy = 8;
    gbc15.gridheight = 1;
    gbc15.gridwidth = 1;
    this.contentPane.add(lblNewLabel_1_1, gbc15);
    this.searchEdit = new JTextField();
    this.searchEdit.setColumns(10);
//    this.searchEdit.setBounds(20, 379, 216, 26);
    GridBagConstraints gbc16 = new GridBagConstraints();
    gbc16.fill = 2;
    gbc16.anchor = 11;
    gbc16.weightx = 0.1D;
    gbc16.weighty = 0.1D;
    gbc16.gridx = 0;
    gbc16.gridy = 9;
    gbc16.gridheight = 1;
    gbc16.gridwidth = 1;
    gbc16.insets = new Insets(0, 0, 0, 10);
    this.contentPane.add(this.searchEdit, gbc16);
    JSeparator separator_2 = new JSeparator();
    separator_2.setOrientation(1);
//    separator_2.setBounds(268, 344, 12, 79);
    GridBagConstraints gbc17_1 = new GridBagConstraints();
    gbc17_1.anchor = 13;
    gbc17_1.weightx = 0.1D;
    gbc17_1.weighty = 0.1D;
    gbc17_1.gridx = 0;
    gbc17_1.gridy = 8;
    gbc17_1.gridheight = 6;
    gbc17_1.gridwidth = 1;
    gbc17_1.fill = 3;
    this.contentPane.add(separator_2, gbc17_1);
    JLabel lblNewLabel_1_2 = new JLabel("Name");
//    lblNewLabel_1_2.setBounds(361, 359, 41, 16);
    GridBagConstraints gbc17 = new GridBagConstraints();
    gbc17.anchor = 15;
    gbc17.weightx = 0.1D;
    gbc17.weighty = 0.1D;
    gbc17.gridx = 1;
    gbc17.gridy = 8;
    gbc17.gridheight = 1;
    gbc17.gridwidth = 1;
    this.contentPane.add(lblNewLabel_1_2, gbc17);
    this.name2 = new JTextField();
    this.name2.setColumns(10);
//    this.name2.setBounds(279, 379, 207, 26);
    GridBagConstraints gbc18 = new GridBagConstraints();
    gbc18.fill = 2;
    gbc18.anchor = 11;
    gbc18.weightx = 0.1D;
    gbc18.weighty = 0.1D;
    gbc18.gridx = 1;
    gbc18.gridy = 9;
    gbc18.gridheight = 1;
    gbc18.gridwidth = 1;
    this.contentPane.add(this.name2, gbc18);
    this.symbol2 = new JTextField();
    this.symbol2.setColumns(10);
//    this.symbol2.setBounds(498, 378, 75, 26);
    GridBagConstraints gbc19 = new GridBagConstraints();
    gbc19.fill = 2;
    gbc19.anchor = 11;
    gbc19.weightx = 0.1D;
    gbc19.weighty = 0.1D;
    gbc19.gridx = 2;
    gbc19.gridy = 9;
    gbc19.gridheight = 1;
    gbc19.gridwidth = 1;
    this.contentPane.add(this.symbol2, gbc19);
    JLabel lblNewLabel_2_1 = new JLabel("Symbol");
//    lblNewLabel_2_1.setBounds(510, 359, 51, 16);
    GridBagConstraints gbc20 = new GridBagConstraints();
    gbc20.anchor = 15;
    gbc20.weightx = 0.1D;
    gbc20.weighty = 0.1D;
    gbc20.gridx = 2;
    gbc20.gridy = 8;
    gbc20.gridheight = 1;
    gbc20.gridwidth = 1;
    this.contentPane.add(lblNewLabel_2_1, gbc20);
    this.mono2 = new JTextField();
    this.mono2.setColumns(10);
//    this.mono2.setBounds(585, 379, 85, 26);
    GridBagConstraints gbc21 = new GridBagConstraints();
    gbc21.fill = 2;
    gbc21.anchor = 11;
    gbc21.weightx = 0.1D;
    gbc21.weighty = 0.1D;
    gbc21.gridx = 3;
    gbc21.gridy = 9;
    gbc21.gridheight = 1;
    gbc21.gridwidth = 1;
    this.contentPane.add(this.mono2, gbc21);
    JLabel lblNewLabel_3_1 = new JLabel("Mono");
//    lblNewLabel_3_1.setBounds(607, 359, 41, 16);
    GridBagConstraints gbc22 = new GridBagConstraints();
    gbc22.anchor = 15;
    gbc22.weightx = 0.1D;
    gbc22.weighty = 0.1D;
    gbc22.gridx = 3;
    gbc22.gridy = 8;
    gbc22.gridheight = 1;
    gbc22.gridwidth = 1;
    this.contentPane.add(lblNewLabel_3_1, gbc22);
    this.avg2 = new JTextField();
    this.avg2.setColumns(10);
//    this.avg2.setBounds(682, 379, 85, 26);
    GridBagConstraints gbc23 = new GridBagConstraints();
    gbc23.fill = 2;
    gbc23.anchor = 11;
    gbc23.weightx = 0.1D;
    gbc23.weighty = 0.1D;
    gbc23.gridx = 4;
    gbc23.gridy = 9;
    gbc23.gridheight = 1;
    gbc23.gridwidth = 1;
    this.contentPane.add(this.avg2, gbc23);
    JLabel lblNewLabel_4_1 = new JLabel("Avg");
//    lblNewLabel_4_1.setBounds(710, 359, 30, 16);
    GridBagConstraints gbc24 = new GridBagConstraints();
    gbc24.anchor = 15;
    gbc24.weightx = 0.1D;
    gbc24.weighty = 0.1D;
    gbc24.gridx = 4;
    gbc24.gridy = 8;
    gbc24.gridheight = 1;
    gbc24.gridwidth = 1;
    this.contentPane.add(lblNewLabel_4_1, gbc24);
    JButton makeChangesBtn = new JButton("Make Changes");
    makeChangesBtn.setFont(new Font("Lucida Grande", 1, 13));
//    makeChangesBtn.setBounds(780, 370, 121, 35);
    makeChangesBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DatabaseEditorGUI.this.makeChangesToMod(e);
          }
        });
    GridBagConstraints gbc25 = new GridBagConstraints();
    gbc25.anchor = 11;
    gbc25.weightx = 0.1D;
    gbc25.weighty = 0.1D;
    gbc25.gridx = 5;
    gbc25.gridy = 9;
    gbc25.gridheight = 1;
    gbc25.gridwidth = 1;
    this.contentPane.add(makeChangesBtn, gbc25);
    JLabel lblDeleteModFrom = new JLabel("Delete Mod From Database");
    lblDeleteModFrom.setFont(new Font("Lucida Grande", 1, 13));
//    lblDeleteModFrom.setBounds(15, 422, 200, 16);
    GridBagConstraints gbc26 = new GridBagConstraints();
    gbc26.anchor = 17;
    gbc26.weightx = 0.1D;
    gbc26.weighty = 0.1D;
    gbc26.gridx = 0;
    gbc26.gridy = 10;
    gbc26.gridheight = 1;
    gbc26.gridwidth = 1;
    gbc26.insets = new Insets(25, 0, 0, 0);
//    gbc26.anchor = GridBagConstraints.SOUTH;
    this.contentPane.add(lblDeleteModFrom, gbc26);
    JLabel lblNewLabel_1_1_1 = new JLabel("Search Mod Name or Symbol");
//    lblNewLabel_1_1_1.setBounds(40, 448, 179, 16);
    GridBagConstraints gbc27 = new GridBagConstraints();
    gbc27.anchor = 15;
    gbc27.weightx = 0.1D;
    gbc27.weighty = 0.1D;
    gbc27.gridx = 0;
    gbc27.gridy = 11;
    gbc27.gridheight = 1;
    gbc27.gridwidth = 1;
    this.contentPane.add(lblNewLabel_1_1_1, gbc27);
    this.searchDelete = new JTextField();
    this.searchDelete.setColumns(10);
//    this.searchDelete.setBounds(20, 467, 216, 26);
    GridBagConstraints gbc28 = new GridBagConstraints();
    gbc28.fill = 2;
    gbc28.anchor = 11;
    gbc28.weightx = 0.1D;
    gbc28.weighty = 0.1D;
    gbc28.gridx = 0;
    gbc28.gridy = 12;
    gbc28.gridheight = 1;
    gbc28.gridwidth = 1;
    gbc28.insets = new Insets(0, 0, 0, 10);
    this.contentPane.add(this.searchDelete, gbc28);
    this.name3 = new JTextField();
    this.name3.setBackground(Color.WHITE);
    this.name3.setEditable(false);
    this.name3.setColumns(10);
//    this.name3.setBounds(279, 466, 207, 26);
    GridBagConstraints gbc29 = new GridBagConstraints();
    gbc29.fill = 2;
    gbc29.anchor = 11;
    gbc29.weightx = 0.1D;
    gbc29.weighty = 0.1D;
    gbc29.gridx = 1;
    gbc29.gridy = 12;
    gbc29.gridheight = 1;
    gbc29.gridwidth = 1;
    this.contentPane.add(this.name3, gbc29);
    JLabel lblNewLabel_1_2_1 = new JLabel("Name");
//    lblNewLabel_1_2_1.setBounds(361, 446, 41, 16);
    GridBagConstraints gbc30 = new GridBagConstraints();
    gbc30.anchor = 15;
    gbc30.weightx = 0.1D;
    gbc30.weighty = 0.1D;
    gbc30.gridx = 1;
    gbc30.gridy = 11;
    gbc30.gridheight = 1;
    gbc30.gridwidth = 1;
    this.contentPane.add(lblNewLabel_1_2_1, gbc30);
    this.symbol3 = new JTextField();
    this.symbol3.setBackground(Color.WHITE);
    this.symbol3.setEditable(false);
    this.symbol3.setColumns(10);
//    this.symbol3.setBounds(498, 465, 75, 26);
    GridBagConstraints gbc31 = new GridBagConstraints();
    gbc31.fill = 2;
    gbc31.anchor = 11;
    gbc31.weightx = 0.1D;
    gbc31.weighty = 0.1D;
    gbc31.gridx = 2;
    gbc31.gridy = 12;
    gbc31.gridheight = 1;
    gbc31.gridwidth = 1;
    this.contentPane.add(this.symbol3, gbc31);
    JLabel lblNewLabel_2_1_1 = new JLabel("Symbol");
//    lblNewLabel_2_1_1.setBounds(510, 446, 51, 16);
    GridBagConstraints gbc32 = new GridBagConstraints();
    gbc32.anchor = 15;
    gbc32.weightx = 0.1D;
    gbc32.weighty = 0.1D;
    gbc32.gridx = 2;
    gbc32.gridy = 11;
    gbc32.gridheight = 1;
    gbc32.gridwidth = 1;
    this.contentPane.add(lblNewLabel_2_1_1, gbc32);
    this.mono3 = new JTextField();
    this.mono3.setEditable(false);
    this.mono3.setColumns(10);
//    this.mono3.setBounds(585, 466, 85, 26);
    GridBagConstraints gbc33 = new GridBagConstraints();
    gbc33.fill = 2;
    gbc33.anchor = 11;
    gbc33.weightx = 0.1D;
    gbc33.weighty = 0.1D;
    gbc33.gridx = 3;
    gbc33.gridy = 12;
    gbc33.gridheight = 1;
    gbc33.gridwidth = 1;
    this.contentPane.add(this.mono3, gbc33);
    JLabel lblNewLabel_3_1_1 = new JLabel("Mono");
//    lblNewLabel_3_1_1.setBounds(607, 446, 41, 16);
    GridBagConstraints gbc34 = new GridBagConstraints();
    gbc34.anchor = 15;
    gbc34.weightx = 0.1D;
    gbc34.weighty = 0.1D;
    gbc34.gridx = 3;
    gbc34.gridy = 11;
    gbc34.gridheight = 1;
    gbc34.gridwidth = 1;
    this.contentPane.add(lblNewLabel_3_1_1, gbc34);
    this.avg3 = new JTextField();
    this.avg3.setEditable(false);
    this.avg3.setColumns(10);
//    this.avg3.setBounds(682, 466, 85, 26);
    GridBagConstraints gbc35 = new GridBagConstraints();
    gbc35.fill = 2;
    gbc35.anchor = 11;
    gbc35.weightx = 0.1D;
    gbc35.weighty = 0.1D;
    gbc35.gridx = 4;
    gbc35.gridy = 12;
    gbc35.gridheight = 1;
    gbc35.gridwidth = 1;
    this.contentPane.add(this.avg3, gbc35);
    JLabel lblNewLabel_4_1_1 = new JLabel("Avg");
//    lblNewLabel_4_1_1.setBounds(710, 446, 30, 16);
    GridBagConstraints gbc36 = new GridBagConstraints();
    gbc36.anchor = 15;
    gbc36.weightx = 0.1D;
    gbc36.weighty = 0.1D;
    gbc36.gridx = 4;
    gbc36.gridy = 11;
    gbc36.gridheight = 1;
    gbc36.gridwidth = 1;
    this.contentPane.add(lblNewLabel_4_1_1, gbc36);
    JButton deleteBtn = new JButton("Delete");
    deleteBtn.setFont(new Font("Lucida Grande", 1, 13));
//    deleteBtn.setBounds(805, 455, 75, 35);
    deleteBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DatabaseEditorGUI.this.deleteMod(e);
          }
        });
    GridBagConstraints gbc37 = new GridBagConstraints();
    gbc37.anchor = 11;
    gbc37.weightx = 0.1D;
    gbc37.weighty = 0.1D;
    gbc37.gridx = 5;
    gbc37.gridy = 12;
    gbc37.gridheight = 1;
    gbc37.gridwidth = 1;
    this.contentPane.add(deleteBtn, gbc37);
    JSeparator separator_1_1_1 = new JSeparator();
//    separator_1_1_1.setBounds(5, 511, 895, 12);
    JButton goBtn1 = new JButton("Go");
    goBtn1.setBounds(233, 377, 20, 29);
    goBtn1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DatabaseEditorGUI.this.searchModForEdit(e);
          }
        });
    GridBagConstraints gbc41 = new GridBagConstraints();
    gbc41.weightx = 0.1D;
    gbc41.weighty = 0.1D;
    gbc41.gridx = 0;
    gbc41.gridy = 10;
    gbc41.gridheight = 1;
    gbc41.gridwidth = 1;
    gbc41.anchor = GridBagConstraints.NORTH;
    this.contentPane.add(goBtn1, gbc41);
    JButton goBtn2 = new JButton("Go");
//    goBtn2.setBounds(233, 465, 20, 29);
    goBtn2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DatabaseEditorGUI.this.searchModForDelete(e);
          }
        });
    GridBagConstraints gbc42 = new GridBagConstraints();
    gbc42.weightx = 0.1D;
    gbc42.weighty = 0.1D;
    gbc42.gridx = 0;
    gbc42.gridy = 13;
    gbc42.gridheight = 2;
    gbc42.gridwidth = 1;
    gbc16.insets = new Insets(0, 0, 0, 10);
    this.contentPane.add(goBtn2, gbc42);
    JLabel lblNewLabel_5 = new JLabel("Status");
    lblNewLabel_5.setFont(new Font("Lucida Grande", 1, 13));
//    lblNewLabel_5.setBounds(785, 47, 41, 16);
    GridBagConstraints gbc39 = new GridBagConstraints();
    gbc39.anchor = 11;
    gbc39.weightx = 0.1D;
    gbc39.weighty = 0.1D;
    gbc39.gridx = 5;
    gbc39.gridy = 1;
    gbc39.gridheight = 2;
    gbc39.gridwidth = 1;
    this.contentPane.add(lblNewLabel_5, gbc39);
    JScrollPane scrollPane_1 = new JScrollPane();
//    scrollPane_1.setBounds(725, 70, 170, 170);
    GridBagConstraints gbc38 = new GridBagConstraints();
    gbc38.weightx = 0.1D;
    gbc38.weighty = 0.1D;
    gbc38.gridx = 5;
    gbc38.gridy = 2;
    gbc38.gridheight = 2;
    gbc38.gridwidth = 1;
    gbc38.fill = 1;
    this.contentPane.add(scrollPane_1, gbc38);
    this.statusArea = new JTextArea();
    scrollPane_1.setViewportView(this.statusArea);
    JButton clearBtn = new JButton("Clear all fields");
//    clearBtn.setBounds(783, 517, 117, 29);
    clearBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            DatabaseEditorGUI.this.clearFields(e);
          }
        });
    GridBagConstraints gbc40 = new GridBagConstraints();
    gbc40.anchor = 11;
    gbc40.weightx = 0.1D;
    gbc40.weighty = 0.1D;
    gbc40.gridx = 5;
    gbc40.gridy = 13;
    gbc40.gridheight = 1;
    gbc40.gridwidth = 1;
    this.contentPane.add(clearBtn, gbc40);
    JLabel lblNewLabel_6 = new JLabel("Make edits here:");
    lblNewLabel_6.setFont(new Font("Lucida Grande", 2, 13));
//    lblNewLabel_6.setBounds(279, 332, 111, 16);
    GridBagConstraints gbc43 = new GridBagConstraints();
    gbc43.weightx = 0.1D;
    gbc43.weighty = 0.1D;
    gbc43.gridx = 1;
    gbc43.gridy = 7;
    gbc43.gridheight = 1;
    gbc43.gridwidth = 1;
    gbc43.insets = new Insets(10, 0, 0, 0);
    this.contentPane.add(lblNewLabel_6, gbc43);
    JLabel lblNewLabel_6_1 = new JLabel("Confirm:");
    lblNewLabel_6_1.setFont(new Font("Lucida Grande", 2, 13));
//    lblNewLabel_6_1.setBounds(279, 422, 67, 16);
    GridBagConstraints gbc44 = new GridBagConstraints();
    gbc44.weightx = 0.1D;
    gbc44.weighty = 0.1D;
    gbc44.gridx = 1;
    gbc44.gridy = 10;
    gbc44.gridheight = 1;
    gbc44.gridwidth = 1;
    gbc44.insets = new Insets(25, 0, 0, 0);
    this.contentPane.add(lblNewLabel_6_1, gbc44);
  }
  
  public void printStatus(String str) {
    this.statusArea.append("\n" + str);
  }
  
  public void btnImportDBActionPerformed(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("Database file t.TAB", new String[] { "tab" }));
    fileChooser.setAcceptAllFileFilterUsed(false);
    int option = fileChooser.showOpenDialog(this.contentPane);
    if (option == 0) {
      this.databaseFile = fileChooser.getSelectedFile();
      try {
        this.fileViewer.read(new FileReader(this.databaseFile.getAbsolutePath()), (Object)null);
        this.databaseLabel.setText("File: " + this.databaseFile.getName());
        printStatus("File selected: " + this.databaseFile.getName());
      } catch (IOException exc) {
        exc.printStackTrace();
      } 
    } 
  }
  
  public void addMod(ActionEvent e) {
    String name = this.name1.getText();
    String symbol = this.symbol1.getText();
    String mono = this.mono1.getText();
    String avg = this.avg1.getText();
    try {
      if (name != null) {
        FileWriter writer = new FileWriter(this.databaseFile.getAbsolutePath(), true);
        writer.write(String.valueOf(System.getProperty("line.separator")) + name + "\t" + symbol + "\t" + mono + "\t" + avg);
        this.fileViewer.read(new FileReader(this.databaseFile.getAbsolutePath()), (Object)null);
        printStatus("Added " + this.name1.getText());
        writer.close();
      } else {
        printStatus("Please enter mod name");
      } 
    } catch (IOException e1) {
      printStatus("Could not add mod");
      e1.printStackTrace();
    } 
  }
  
  public void searchModForEdit(ActionEvent e) {
    String nameOrSym = this.searchEdit.getText();
    boolean found = false;
    try {
      Scanner inFile = new Scanner(new File(this.databaseFile.getAbsolutePath()));
      while (inFile.hasNextLine()) {
        String wholeLine = inFile.nextLine();
        String[] tokens = wholeLine.split("\\t");
        if (wholeLine.length() > 0)
          if (wholeLine.charAt(0) != '=' && (
            tokens[0].equalsIgnoreCase(nameOrSym) || tokens[1].equalsIgnoreCase(nameOrSym))) {
            this.name2.setText(tokens[0]);
            this.symbol2.setText(tokens[1]);
            this.mono2.setText(tokens[2]);
            this.avg2.setText(tokens[3]);
            found = true;
          }  
      } 
      if (!found) {
        printStatus(String.valueOf(nameOrSym) + " NOT FOUND");
        this.name2.setText("MOD NOT FOUND");
        this.symbol2.setText((String)null);
        this.mono2.setText((String)null);
        this.avg2.setText((String)null);
      } 
    } catch (FileNotFoundException exc) {
      exc.printStackTrace();
    } 
  }
  
  public void makeChangesToMod(ActionEvent e) {
    String nameOrSym = this.searchEdit.getText();
    String newName = this.name2.getText();
    String newSym = this.symbol2.getText();
    String newMono = this.mono2.getText();
    String newAvg = this.avg2.getText();
    File tempFile = new File("myTempFile.txt");
    try {
      FileWriter writer = new FileWriter(tempFile);
      Scanner inFile = new Scanner(new File(this.databaseFile.getAbsolutePath()));
      while (inFile.hasNextLine()) {
        String wholeLine = inFile.nextLine();
        String[] tokens = wholeLine.split("\\t");
        if (wholeLine.length() > 0) {
          if (wholeLine.charAt(0) == '=') {
            writer.write(String.valueOf(wholeLine) + System.getProperty("line.separator"));
            continue;
          } 
          if (tokens[0].equalsIgnoreCase(nameOrSym) || tokens[1].equalsIgnoreCase(nameOrSym)) {
            writer.write(String.valueOf(newName) + "\t" + newSym + "\t" + newMono + "\t" + newAvg + System.getProperty("line.separator"));
            continue;
          } 
          writer.write(String.valueOf(wholeLine) + System.getProperty("line.separator"));
        } 
      } 
      writer.close();
      inFile.close();
      if (!this.databaseFile.delete()) {
        printStatus("Could not rewrite file");
        return;
      } 
      if (!tempFile.renameTo(this.databaseFile))
        printStatus("Could not rewrite file"); 
      this.fileViewer.read(new FileReader(this.databaseFile.getAbsolutePath()), (Object)null);
      printStatus("Changed " + nameOrSym);
    } catch (FileNotFoundException exc) {
      exc.printStackTrace();
    } catch (IOException exc) {
      exc.printStackTrace();
    } 
  }
  
  public void searchModForDelete(ActionEvent e) {
    String nameOrSym = this.searchDelete.getText();
    boolean found = false;
    try {
      Scanner inFile = new Scanner(new File(this.databaseFile.getAbsolutePath()));
      while (inFile.hasNextLine()) {
        String wholeLine = inFile.nextLine();
        String[] tokens = wholeLine.split("\\t");
        if (wholeLine.length() > 0)
          if (wholeLine.charAt(0) != '=' && (
            tokens[0].equalsIgnoreCase(nameOrSym) || tokens[1].equalsIgnoreCase(nameOrSym))) {
            this.name3.setText(tokens[0]);
            this.symbol3.setText(tokens[1]);
            this.mono3.setText(tokens[2]);
            this.avg3.setText(tokens[3]);
            found = true;
          }  
      } 
      if (!found) {
        printStatus(String.valueOf(nameOrSym) + " MOD NOT FOUND");
        this.name3.setText("MOD NOT FOUND");
        this.symbol3.setText((String)null);
        this.mono3.setText((String)null);
        this.avg3.setText((String)null);
      } 
    } catch (FileNotFoundException exc) {
      exc.printStackTrace();
    } 
  }
  
  public void deleteMod(ActionEvent e) {
    String nameOrSym = this.searchDelete.getText();
    File tempFile = new File("myTempFile.txt");
    try {
      FileWriter writer = new FileWriter(tempFile);
      Scanner inFile = new Scanner(new File(this.databaseFile.getAbsolutePath()));
      while (inFile.hasNextLine()) {
        String wholeLine = inFile.nextLine();
        String[] tokens = wholeLine.split("\\t");
        if (wholeLine.length() > 0) {
          if (wholeLine.charAt(0) == '=') {
            writer.write(String.valueOf(wholeLine) + System.getProperty("line.separator"));
            continue;
          } 
          if (tokens[0].equalsIgnoreCase(nameOrSym) || tokens[1].equalsIgnoreCase(nameOrSym))
            continue; 
          writer.write(String.valueOf(wholeLine) + System.getProperty("line.separator"));
        } 
      } 
      writer.close();
      inFile.close();
      if (!this.databaseFile.delete()) {
        printStatus("Could not delete mod");
        return;
      } 
      if (!tempFile.renameTo(this.databaseFile)) {
        printStatus("Could not delete mod");
        return;
      } 
      this.fileViewer.read(new FileReader(this.databaseFile.getAbsolutePath()), (Object)null);
      printStatus("Deleted " + nameOrSym);
    } catch (FileNotFoundException exc) {
      exc.printStackTrace();
    } catch (IOException exc) {
      exc.printStackTrace();
    } 
  }
  
  public void clearFields(ActionEvent e) {
    this.name1.setText((String)null);
    this.symbol1.setText((String)null);
    this.mono1.setText((String)null);
    this.avg1.setText((String)null);
    this.searchEdit.setText((String)null);
    this.name2.setText((String)null);
    this.symbol2.setText((String)null);
    this.mono2.setText((String)null);
    this.avg2.setText((String)null);
    this.searchDelete.setText((String)null);
    this.name3.setText((String)null);
    this.symbol3.setText((String)null);
    this.mono3.setText((String)null);
    this.avg3.setText((String)null);
  }
}
