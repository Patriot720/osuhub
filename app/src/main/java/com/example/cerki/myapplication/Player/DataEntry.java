package com.example.cerki.myapplication.Player;


import java.util.Locale;

public class DataEntry {
    private int intVal;
    private double doubleVal;
    private final boolean isFloating;

    public boolean isFloating() {
        return isFloating;
    }

    public DataEntry(int intVal){
        this(String.valueOf(intVal));
    }
    public DataEntry(double doubleVal){
        this(String.valueOf(doubleVal));
    }
    public DataEntry(String value){
        Double tmp = Double.parseDouble(value.replaceAll("[^0-9.]",""));
        if(tmp % 1 == 0) {
            intVal = tmp.intValue();
            isFloating = false;
        } else {
            doubleVal = tmp;
            isFloating = true;
        }
    }

    public boolean isPositive(){
        if(isFloating) {
            if (doubleVal >= 0)
                return true;
            if(doubleVal < 0)
                return false;
        }
        else{
            if(intVal >= 0)
                return true;
            if(intVal < 0)
                return false;
        }
        return true;
    }
    public String getAbsString(){
        if(isPositive())
            return getAsString();
        return getAsString().substring(1);
    }
    public DataEntry add(DataEntry entry){
        if(isFloating) {
            double dval = doubleVal + entry.getDoubleVal();
            return new DataEntry(dval);
        }
        int ival = intVal + entry.getIntVal();
        return new DataEntry(ival);
    }
    public boolean equals(DataEntry val) {
        return val != null && getAsString().equals(val.getAsString());
    }
    public DataEntry subtract(DataEntry entry){
        if(entry == null)return new DataEntry(0);
        if(isFloating){
            double dval = doubleVal - entry.getDoubleVal();
            return new DataEntry(dval);
        }
        int ival = intVal - entry.getIntVal();
        return new DataEntry(ival);
    }
    public String getAsString() {
        if (isFloating)
            return String.format(Locale.getDefault(),"%.2f", doubleVal);
        return String.valueOf(intVal);
    }
    public double getDoubleVal(){
        return doubleVal;
    }
    public int getIntVal(){
        return intVal;
    }
}
