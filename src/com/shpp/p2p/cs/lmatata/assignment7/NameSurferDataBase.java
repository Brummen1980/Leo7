package com.shpp.p2p.cs.lmatata.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

    /* Constructor: NameSurferDataBase(filename) */
    private final HashMap<String, NameSurferEntry> namesDataBase = new HashMap<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        try {
            BufferedReader bR = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bR.readLine()) != null) {
                NameSurferEntry entry = new NameSurferEntry(line);
                namesDataBase.put(entry.getName(), entry);
            }
            bR.close();
        } catch (IOException e) {
            System.out.println("Requested file does not exist");
        }
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        if (name.length() == 0) return null;
        String nameLowerCase = name.toLowerCase();
        char firstLatter = Character.toUpperCase(nameLowerCase.charAt(0));
        String otherLetters = nameLowerCase.substring(1);
        name = firstLatter + otherLetters;
        return namesDataBase.get(name);
    }
}

