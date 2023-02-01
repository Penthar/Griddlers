package com.example.test2;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Map {
    int[][] completeBoard;
    int rows;
    int colsumns;
    Sequences[] rowsSequences;
    Sequences[] columnsSequences;
    int longestSequenceInRow;
    int longestSequenceInCol;
    public Map(Context context, String FileName){
        readDataFromFile(context, FileName);
        calculateRowsSequences();
        calculateColsSequences();
        longestSequenceInRow = findLongestSequenceNumber(rowsSequences);
        longestSequenceInCol = findLongestSequenceNumber(columnsSequences);
        completeMissingSequences(rowsSequences, longestSequenceInRow);
        completeMissingSequences(columnsSequences, longestSequenceInCol);
    }
    private void readDataFromFile(Context context, String FileName){
        File file = new File(context.getExternalFilesDir(null), FileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            this.rows = fis.read();
            this.colsumns = fis.read();
            completeBoard = new int[rows][colsumns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colsumns; j++) {
                    completeBoard[i][j] = fis.read();
                    Log.d("readtry", Integer.toString(completeBoard[i][j]));
                }
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void calculateRowsSequences(){
        rowsSequences = new Sequences[rows];
        for (int i = 0; i < rows; i++) {
            rowsSequences[i] = new Sequences();
        }
        for (int i = 0; i < rows; i++) {
            int counter = 0;
            for (int j = 0; j < colsumns; j++) {
                if(completeBoard[i][j] == 1)
                    counter++;
                else {
                    if (counter > 0){
                    rowsSequences[i].addSequence(counter);
                    counter = 0;}}
            }
            if(counter>0)
                rowsSequences[i].addSequence(counter);
        }
    }
    private void calculateColsSequences(){
        columnsSequences = new Sequences[colsumns];
        for (int i = 0; i < colsumns; i++) {
            columnsSequences[i] = new Sequences();
        }
        for (int i = 0; i < colsumns; i++) {
            int counter = 0;
            for (int j = 0; j < rows; j++) {
                if(completeBoard[j][i] == 1)
                    counter++;
                else {
                    if (counter > 0) {
                        columnsSequences[i].addSequence(counter);
                        counter = 0;
                    }
                }
            }
            if(counter>0)
                columnsSequences[i].addSequence(counter);
        }
    }
    private int findLongestSequenceNumber(Sequences[] sequences){
        int counter = 0;
        for (int i = 0; i < sequences.length; i++) {
            if (sequences[i].sequences.size() > counter)
                counter = sequences[i].sequences.size();
        }
        Log.d("counter", Integer.toString(counter));
        return counter;
    }
    private void completeMissingSequences(Sequences[] sequences, int longestSize){
        Log.d("longest", Integer.toString(longestSize));
        for (int i = 0; i < sequences.length; i++) {
            Log.d("sizecheck1", Integer.toString(sequences[i].sequences.size()));
            if(longestSize > sequences[i].sequences.size())
            sequences[i].addEmptySequences(longestSize - sequences[i].sequences.size());
            Log.d("sizecheck2", Integer.toString(sequences[i].sequences.size()));
        }
    }
}
