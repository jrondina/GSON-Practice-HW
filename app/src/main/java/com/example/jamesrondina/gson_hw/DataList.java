package com.example.jamesrondina.gson_hw;

import java.util.ArrayList;

/**
 * Created by jamesrondina on 8/11/16.
 */
public class DataList {
  private Datapoint[] feeds;

    public DataList(Datapoint[] feeds) {
        this.feeds = feeds;
    }

    public Datapoint[] getFeeds() {
        return feeds;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
