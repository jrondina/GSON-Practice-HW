package com.example.jamesrondina.gson_hw;

/**
 * Created by jamesrondina on 8/10/16.
 */
public class Datapoint {
    private String mDateTime;
    private String mHumidity;

    public String getmDateTime(){return mDateTime;}

    public String getHumidity(){return mHumidity;}

    public void setmDateTime(String dateTime){this.mDateTime = dateTime;}

    public void setHumidity(String humidity){this.mHumidity = humidity;}

    @Override
    public String toString() {
        return mDateTime;
    }
}
