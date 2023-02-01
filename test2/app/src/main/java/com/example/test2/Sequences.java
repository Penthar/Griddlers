package com.example.test2;

import java.util.ArrayList;

public class Sequences {
    ArrayList<Integer> sequences;
    public Sequences(){
        this.sequences=new ArrayList<>();
    }
    public void addSequence(Integer sequenceLength){
        sequences.add(sequenceLength);
    }
    public void addEmptySequences(int number){
        for (int i = 0; i < number; i++) {
            sequences.add(0,0);
        }
    }
}
