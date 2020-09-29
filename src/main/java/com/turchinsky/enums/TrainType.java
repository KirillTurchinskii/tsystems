package com.turchinsky.enums;

public enum TrainType {
    REGULAR ("Regular"),
    SAPSAN ("Sapsan"),
    LASTOCHKA ("Lastochka");


    private String type;

    TrainType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
