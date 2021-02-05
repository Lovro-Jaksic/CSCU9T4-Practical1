// GUI and main program for the Training Record
package main.java.com.stir.cscu9t4practical1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField where = new JTextField(15); //text field for the swimming "where" variable
    private JTextField repetitions = new JTextField(4); //text field for the sprinting "repetitions" variable
    private JTextField recovery = new JTextField(4); //text field for the sprinting "recovery" variable
    private JTextField terrain = new JTextField(15); //text field for the cycling "terrain" variable
    private JTextField tempo = new JTextField(15); //text field for the cycling "tempo" variable

    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labwhere = new JLabel(" Where:"); //label for the swimming "where" variable
    private JLabel labrep = new JLabel(" Repetitions:"); // label for the sprinting "repetitions" variable
    private JLabel labrec = new JLabel(" Recovery:"); //label for the sprinting "recovery" variable
    private JLabel labterr = new JLabel(" Terrain:"); // label for the cycling "terrain" variable
    private JLabel labtemp = new JLabel(" Tempo:"); //label for the cycling "tempo" variable

    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find All"); //#1

    String typesOfSessions[] = {"Sprinting","Swimming","Cycling",}; //types of sessions for the drop down menu
    private JComboBox differentSessions = new JComboBox(typesOfSessions); //drop down menu for different training sessions

    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        differentSessions.addActionListener(this); //add the drop down menu
        add(differentSessions);
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(labwhere); //for the swimming "where" variable
        add(where);
        where.setEditable(false);
        add(labrep); //for the sprinting "repetitions" variable
        add(repetitions);
        repetitions.setEditable(false);
        add(labrec); //for the sprinting "recovery" variable
        add(recovery);
        recovery.setEditable(false);
        add(labterr); //for the cycling "terrain" variable
        add(terrain);
        terrain.setEditable(false);
        add(labtemp); //for the cycling "tempo" variable
        add(tempo);
        tempo.setEditable(false);

        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate); //#1
        findAllByDate.addActionListener(this); //#1
        add(outputArea);
        outputArea.setEditable(false);
        setSize(875, 450);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor


    //respond to the JComboBox chosen training session
    public void checkTrainingSession (ActionEvent event) {
        if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Sprinting") {
            repetitions.setEditable(true);
            recovery.setEditable(true);
        }
        else if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Swimming") {
            where.setEditable(true);
        }
        else if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Cycling") {
            terrain.setEditable(true);
            tempo.setEditable(true);
        }
    }


    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Sprinting") {
            repetitions.setEditable(true); //if sprinting selected then enable sprinting entries, but disable other entries
            recovery.setEditable(true);
            where.setEditable(false);
            terrain.setEditable(false);
            tempo.setEditable(false);
        }
        if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Swimming") {
            where.setEditable(true); //if swimming selected then enable swimming entries, but disable other entries
            repetitions.setEditable(false);
            recovery.setEditable(false);
            terrain.setEditable(false);
            tempo.setEditable(false);
        }
        if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Cycling") {
            terrain.setEditable(true); //if cycling selected then enable cycling entries, but disable other entries
            tempo.setEditable(true);
            repetitions.setEditable(false);
            recovery.setEditable(false);
            where.setEditable(false);
        }
        if (event.getSource() == addR) {
            differentSessions.getItemAt(differentSessions.getSelectedIndex()); //see what is selected from the drop down menu
            if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Sprinting") {
                message = addSprintingEntry("Sprinting");
            }
            else if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Swimming") {
                message = addSwimmingEntry("Swimming");
            }
            else if (differentSessions.getItemAt(differentSessions.getSelectedIndex()) == "Cycling") {
                message = addCyclingEntry("Cycling");
            }
            blankDisplay();
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
            blankDisplay();
        }
        if (event.getSource() == findAllByDate) { //#1
            message = lookupAllEntries(); //#3
            blankDisplay();
        }
        outputArea.setText(message);
//        blankDisplay();
    } // actionPerformed

//    public String addEntry(String what) {
//        String message = "Record added\n";
//        System.out.println("Adding "+what+" entry to the records");
//        String n = name.getText();
//        int m = Integer.parseInt(month.getText());
//        int d = Integer.parseInt(day.getText());
//        int y = Integer.parseInt(year.getText());
//        float km = java.lang.Float.parseFloat(dist.getText());
//        int h = Integer.parseInt(hours.getText());
//        int mm = Integer.parseInt(mins.getText());
//        int s = Integer.parseInt(secs.getText());
//        SprintEntry e = new SprintEntry(n, d, m, y, h, mm, s, km);
//        myAthletes.addEntry(e);
//        return message;
//    }

    public String addSprintingEntry(String what) { //addEntry if sprinting selected from drop down menu
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        int rep = Integer.parseInt(repetitions.getText()); //additional variable repetitions
        int rec = Integer.parseInt(recovery.getText()); //additional variable recovery
        SprintEntry sprint = new SprintEntry(n, d, m, y, h, mm, s, km, rep, rec);
        myAthletes.addEntry(sprint);
        return message;
    }

    public String addSwimmingEntry(String what) { //addEntry if swimming selected from drop down menu
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        String w = where.getText(); //additional variable where
        SwimEntry swim = new SwimEntry(n, d, m, y, h, mm, s, km, w);
        myAthletes.addEntry(swim);
        return message;
    }

    public String addCyclingEntry(String what) { //addEntry if cycling selected from drop down menu
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        String terr = terrain.getText();
        String temp = tempo.getText();
        CycleEntry cycle = new CycleEntry(n, d, m, y, h, mm, s, km, terr, temp);
        myAthletes.addEntry(cycle);
        return message;
    }

//    public String checkEntry () { //#4 attempt
//        String n = name.getText();
//        int m = Integer.parseInt(month.getText());
//        int d = Integer.parseInt(day.getText());
//        int y = Integer.parseInt(year.getText());
//        float km = java.lang.Float.parseFloat(dist.getText());
//        int h = Integer.parseInt(hours.getText());
//        int mm = Integer.parseInt(mins.getText());
//        int s = Integer.parseInt(secs.getText());
//        Entry e = new Entry(n, d, m, y, h, mm, s, km);
//        myAthletes.addEntry(e);
//        String message = "Error: one of the entries is wrong";
//        return message;
//    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public String lookupAllEntries() { //#3
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("Looking up record...");
        String message = myAthletes.lookupAllEntries(d, m, y);
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        where.setText("");
        repetitions.setText("");
        recovery.setText("");
        terrain.setText("");
        tempo.setText("");


    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

