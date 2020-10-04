/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sleepshift;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.util.*;
import java.time.*;

/**
 *
 * @author user
 */
public class AstronautDailySchedule {

    private Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("EEE");
    private final Object weekdayData[][] = {
        {"6.00am", "Wake up / Bath"},
        {"6.40am", "Breakfast"},
        {"7.00am", "Audio conference with Earth"},
        {"7.30am", "Scientific research / Maintenance & Logistic tasks"},
        {"12.30pm", "Lunch"},
        {"1.00pm", "Maintenance & Logistic tasks / Scientific research"},
        {"5.00pm", "Exercise"},
        {"7.30pm", "Dinner"},
        {"8.00pm", "Free time"},
        {"9.30pm", "Lights off"}
    };
    private final Object fridayData[][] = {
        {"6.00am", "Wake up / Bath"},
        {"6.40am", "Breakfast"},
        {"7.00am", "Audio conference with Earth"},
        {"7.30am", "Scientific research / Maintenance & Logistic tasks"},
        {"12.30pm", "Lunch"},
        {"1.00pm", "Maintenance & Logistic tasks / Scientific research"},
        {"5.00pm", "Exercise"},
        {"7.00pm", "Closing conference"},
        {"7.30pm", "Dinner"},
        {"8.00pm", "Free time"},
        {"9.30pm", "Lights off"}
    };
    private final Object weekendData[][] = {
        {"6.00am", "Wake up / Bath"},
        {"6.40am", "Breakfast"},
        {"7.00am", "Free time"},
        {"12.30pm", "Lunch"},
        {"1.00pm", "Free time"},
        {"5.00pm", "Exercise"},
        {"7.30pm", "Dinner"},
        {"8.00pm", "Free time"},
        {"9.30pm", "Lights off"}
    };
    private final Object EVAData[][] = {
        {"6.00am", "Wake up / Bath"},
        {"6.40am", "Breakfast"},
        {"7.00am", "Audio conference with Earth"},
        {"7.30am", "Preparation for spacewalk"},
        {"7.50am", "Start spacewalk"},
        {"1.50pm", "Head back to ISS"},
        {"2.30pm", "Lunch"},
        {"3.00pm", "Nap"},
        {"4.00pm", "Exercise"},
        {"6.00pm", "Audio Conference with Earth"},
        {"7.30pm", "Dinner"},
        {"8.00pm", "Free time"},
        {"9.30pm", "Lights off"}
    };
    private String columnName[] = {"Time", df.format(d)};
    private boolean isEVAday = false;

    public AstronautDailySchedule() {
        TableModel fixedColumnModel = new AbstractTableModel() {
            String day = df.format(d);

            @Override
            public int getRowCount() {

                if (isEVAday) {
                    return EVAData.length;
                } else if (day.equalsIgnoreCase("mon") || day.equalsIgnoreCase("tue") || day.equalsIgnoreCase("wed") || day.equalsIgnoreCase("thu")) {
                    return weekdayData.length;
                } else if (day.equalsIgnoreCase("fri")) {
                    return fridayData.length;
                } else {
                    return weekendData.length;
                }
            }

            @Override
            public int getColumnCount() {
                return 1;
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (isEVAday) {
                    return EVAData[row][column];
                } else if (day.equalsIgnoreCase("mon") || day.equalsIgnoreCase("tue") || day.equalsIgnoreCase("wed") || day.equalsIgnoreCase("thu")) {
                    return weekdayData[row][column];
                } else if (day.equalsIgnoreCase("fri")) {
                    return fridayData[row][column];
                } else {
                    return weekendData[row][column];
                }
            }

            public String getColumnName(int column) {
                return columnName[column];
            }

        };
        TableModel mainModel = new AbstractTableModel() {
            String day = df.format(d);

            @Override
            public int getRowCount() {
                if (isEVAday) {
                    return EVAData.length;
                } else if (day.equalsIgnoreCase("mon") || day.equalsIgnoreCase("tue") || day.equalsIgnoreCase("wed") || day.equalsIgnoreCase("thu")) {
                    return weekdayData.length;
                } else if (day.equalsIgnoreCase("fri")) {
                    return fridayData.length;
                } else {
                    return weekendData.length;
                }
            }

            @Override
            public int getColumnCount() {
                return columnName.length - 1;
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (isEVAday) {
                    return EVAData[row][column + 1];
                } else if (day.equalsIgnoreCase("mon") || day.equalsIgnoreCase("tue") || day.equalsIgnoreCase("wed") || day.equalsIgnoreCase("thu")) {
                    return weekdayData[row][column + 1];
                } else if (day.equalsIgnoreCase("fri")) {
                    return fridayData[row][column + 1];
                } else {
                    return weekendData[row][column + 1];
                }
            }

            public String getColumnName(int column) {
                return columnName[column + 1];
            }
        };
        JTable fixedTable = new JTable(fixedColumnModel);
        fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        

        JTable mainTable = new JTable(mainModel);
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        

        ListSelectionModel model = fixedTable.getSelectionModel();
        mainTable.setSelectionModel(model);

        JScrollPane scrollPane = new JScrollPane(mainTable);
        Dimension fixedSize = fixedTable.getPreferredSize();
        JViewport viewport = new JViewport();
        viewport.setView(fixedTable);
        viewport.setPreferredSize(fixedSize);
        viewport.setMaximumSize(fixedSize);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedTable
                .getTableHeader());
        scrollPane.setRowHeaderView(viewport);

        JFrame frame = new JFrame("Daily TimeTable");

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

}
