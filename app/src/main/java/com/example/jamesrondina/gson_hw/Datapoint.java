package com.example.jamesrondina.gson_hw;

/**
 * Created by jamesrondina on 8/10/16.
 */
public class Datapoint {
    private String field1;


    public String getHumidity(){return field1;}
    public void setHumidity(String humidity){this.field1 = humidity;}

    @Override
    public String toString() {
        return "Temperature (F): " + field1;
    }
}
