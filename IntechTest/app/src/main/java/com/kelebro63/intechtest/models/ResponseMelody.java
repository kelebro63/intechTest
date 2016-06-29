package com.kelebro63.intechtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bistrov Alexey on 29.06.2016.
 */
public class ResponseMelody {
    @SerializedName("collection")
    @Expose
    private List<Collection> collection = new ArrayList<Collection>();
    @SerializedName("next_href")
    @Expose
    private String nextHref;

    /**
     *
     * @return
     * The collection
     */
    public List<Collection> getCollection() {
        return collection;
    }

    /**
     *
     * @param collection
     * The collection
     */
    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    /**
     *
     * @return
     * The nextHref
     */
    public String getNextHref() {
        return nextHref;
    }

    /**
     *
     * @param nextHref
     * The next_href
     */
    public void setNextHref(String nextHref) {
        this.nextHref = nextHref;
    }

}
