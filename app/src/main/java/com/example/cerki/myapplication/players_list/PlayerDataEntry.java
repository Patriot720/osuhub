package com.example.cerki.myapplication.players_list;


public class PlayerDataEntry {
    private int intVal;
    private double doubleVal;
    private boolean isFloating;

    public boolean isFloating() {
        return isFloating;
    }

    public PlayerDataEntry(int intVal){
        this(String.valueOf(intVal));
    }
    public PlayerDataEntry(double doubleVal){
        this(String.valueOf(doubleVal));
    }
    public PlayerDataEntry(String value){
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
    public PlayerDataEntry add(PlayerDataEntry entry){
        if(isFloating) {
            double dval = doubleVal + entry.getDoubleVal();
            return new PlayerDataEntry(dval);
        }
        int ival = intVal + entry.getIntVal();
        return new PlayerDataEntry(ival);
    }
    public boolean equals(PlayerDataEntry val) {
        return val != null && getAsString().equals(val.getAsString());
    }
    public PlayerDataEntry subtract(PlayerDataEntry entry){
        if(entry == null)return new PlayerDataEntry(0);
        PlayerDataEntry test = entry;
        if(isFloating){
            double dval = doubleVal - entry.getDoubleVal();
            return new PlayerDataEntry(dval);
        }
        int ival = intVal - entry.getIntVal();
        return new PlayerDataEntry(ival);
    }
    public String getAsString() {
        if (isFloating)
            return String.valueOf(doubleVal);
        return String.valueOf(intVal);
    }
    public double getDoubleVal(){
        return doubleVal;
    }
    public int getIntVal(){
        return intVal;
    }
}
