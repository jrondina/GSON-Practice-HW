package com.example.jamesrondina.gson_hw;

import java.util.ArrayList;

/**
 * Created by jamesrondina on 8/11/16.
 */
public class DataList {

    private ArrayList<Datapoint> datapoints;

    public void setItems(ArrayList<Datapoint> datapoints){this.datapoints = datapoints;}

    public ArrayList<Datapoint> getDatapoints(){return datapoints;}

    @Override
    public String toString() {
        return datapoints.size()+" item(s) in the search result";
    }
}
