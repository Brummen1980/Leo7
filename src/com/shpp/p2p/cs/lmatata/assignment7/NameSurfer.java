package com.shpp.p2p.cs.lmatata.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implement the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the database
     * and initializing the interactors at the top of the window.
     */
    public void init() {

        JLabel nameLabel = new JLabel("Name: ");
        add(nameLabel, NORTH);
        fieldName = new JTextField(TEXT_FIELD_FRAME_SIZE);
        add(fieldName, NORTH);
        fieldName.addActionListener(this);
        graphButton = new JButton("Graph");
        add(graphButton, NORTH);
        clearButton = new JButton("Clear");
        add(clearButton, NORTH);
        graph = new NameSurferGraph();
        add(graph);
        nameSurferDataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        addActionListeners();
    }

    private NameSurferGraph graph;
    private JTextField fieldName;
    private JButton clearButton;
    private JButton graphButton;
    private NameSurferDataBase nameSurferDataBase;



    /* Method: actionPerformed(e) */

    /**
     * This method is responsible for manipulating output and displaying information when buttons are pressed.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(fieldName) || e.getSource().equals(graphButton)) {
            NameSurferEntry surferName = nameSurferDataBase.findEntry(fieldName.getText());
            if (surferName != null) {
                graph.addEntry(surferName);
                graph.update();
            } else {
                System.out.println("Not valid entered value");
            }
        } else if (e.getSource().equals(clearButton)) {
            graph.clear();
        }
    }
}
