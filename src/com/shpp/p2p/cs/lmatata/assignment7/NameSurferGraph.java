package com.shpp.p2p.cs.lmatata.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private final ArrayList<NameSurferEntry> entryGraphData;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        entryGraphData = new ArrayList<>();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entryGraphData.clear();
        update();
    }

    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entryGraphData.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawGrid();
        drawGraph();
        drawGraphLegend();
    }

    /**
     *
     */
    private void drawGraph() {
        for (int i = 0; i < entryGraphData.size(); i++) {
            drawGraphItems(entryGraphData.get(i), i);
        }
    }

    /**
     *
     * @param entry
     * @param colorIndex
     */
    private void drawGraphItems(NameSurferEntry entry, int colorIndex) {
        drawGraphLines(entry, colorIndex);
        drawGraphLabels(entry, colorIndex);
    }

    private void drawGraphLegend() {
        for (int i = 0; i < entryGraphData.size(); i++)
            drawGraphLegendItem(entryGraphData.get(i), i);
    }
    private void drawGraphLegendItem(NameSurferEntry entry, int colorIndex) {
        GLabel legend = new GLabel(entry.getName());
        Color[] lineColor = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
        legend.setColor(lineColor[colorIndex]);
        legend.setFont(new Font("Arial", Font.BOLD, 16));
        legend.setLocation(getWidth() - LEGEND_WIDTH_SIZE + 20, 50 + 30 * colorIndex);
        add(legend);
    }

    /**
     *
     * @param entry
     * @param colorIndex
     */
    private void drawGraphLabels(NameSurferEntry entry, int colorIndex) {
        for (int i = 0; i < NDECADES; i++) {
            String name = "";
            int rank = entry.getRank(i);
            int x = i * ((getWidth() - LEGEND_WIDTH_SIZE) / NDECADES);
            int y;
            if (rank == 0) {
                y = getHeight() - GRAPH_MARGIN_SIZE;
                name = "*";
            } else {
                y = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * rank / MAX_RANK;
                name += rank;
            }
            GLabel nameLabel = new GLabel(name, x, y);
            Color[] lineColor = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
            nameLabel.setColor(lineColor[colorIndex % 4]);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            add(nameLabel);
        }
    }

    /**
     *
     * @param entry
     * @param colorIndex
     */
    private void drawGraphLines(NameSurferEntry entry, int colorIndex) {
        for (int i = 0; i < NDECADES - 1; i++) {
            int rank1 = entry.getRank(i);
            int rank2 = entry.getRank(i + 1);
            int x1 = i * ((getWidth() - LEGEND_WIDTH_SIZE) / NDECADES);
            int x2 = (i + 1) * ((getWidth() - LEGEND_WIDTH_SIZE) / NDECADES);
            int y1, y2;

            int r1 = ((rank1 - 1) >> 31);
            int r2 = ((rank2 - 1) >> 31);

            y1 = (GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * rank1 / MAX_RANK) * (r1 + 1) +
                   (getHeight() - GRAPH_MARGIN_SIZE) * Math.abs(r1);
            y2 = (GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * rank2 / MAX_RANK) * (r2 + 1) +
                   (getHeight() - GRAPH_MARGIN_SIZE) * Math.abs(r2);

            GLine line = new GLine(x1, y1, x2, y2);
            Color[] lineColor = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
            line.setColor(lineColor[colorIndex % 4]);
            add(line);
        }
    }

    /**
     *
     */
    private void drawGrid() {
        // Draw vertical lines
        for (int i = 0; i <= NDECADES; i++) {
            int yNorth = 0;
            int ySouth = getHeight();
            int x = i * ((getWidth() - LEGEND_WIDTH_SIZE) / NDECADES);
            GLine line = new GLine(x, yNorth, x, ySouth);
            add(line);
        }
        // draw horizontal lines
        int xWest = 0;
        int xEast =  NDECADES * ((getWidth() - LEGEND_WIDTH_SIZE) / NDECADES);
        //int yNorth = getHeight() - GRAPH_MARGIN_SIZE;
        int yNorth = getHeight() - GRAPH_MARGIN_SIZE;
        for (int i = 0; i < 11; i++)
        {
            int y = yNorth - i * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / 10  ;
            GLine lineNorth = new GLine(xWest, y, xEast, y);
            add(lineNorth);
        }

        GLine lineSouth = new GLine(xWest, GRAPH_MARGIN_SIZE, xEast, GRAPH_MARGIN_SIZE);
        add(lineSouth);
        // draw decade labels
        int decade = START_DECADE;
        for (int i = 0; i < NDECADES; i++, decade += 10) {
            String decadeValue = Integer.toString(decade);
            GLabel labelDecade = new GLabel(decadeValue);
            labelDecade.setFont(new Font("Arial", Font.BOLD, 16));
            int x = i * ((getWidth() - LEGEND_WIDTH_SIZE)/ NDECADES);
            labelDecade.setLocation(x, (getHeight() - labelDecade.getDescent()));
            add(labelDecade);
        }
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
