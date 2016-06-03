package com.kelebro63.intechtest.models;

/**
 * Created by Bistrov Alexey on 02.06.2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("limitedVisibility")
    @Expose
    private Boolean limitedVisibility;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("isFullCatalogEnabled")
    @Expose
    private Boolean isFullCatalogEnabled;
    @SerializedName("topMelodiesCount")
    @Expose
    private Integer topMelodiesCount;
    @SerializedName("isBlockDisplayMode")
    @Expose
    private Boolean isBlockDisplayMode;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The limitedVisibility
     */
    public Boolean getLimitedVisibility() {
        return limitedVisibility;
    }

    /**
     *
     * @param limitedVisibility
     * The limitedVisibility
     */
    public void setLimitedVisibility(Boolean limitedVisibility) {
        this.limitedVisibility = limitedVisibility;
    }

    /**
     *
     * @return
     * The position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     *
     * @param position
     * The position
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     *
     * @return
     * The isFullCatalogEnabled
     */
    public Boolean getIsFullCatalogEnabled() {
        return isFullCatalogEnabled;
    }

    /**
     *
     * @param isFullCatalogEnabled
     * The isFullCatalogEnabled
     */
    public void setIsFullCatalogEnabled(Boolean isFullCatalogEnabled) {
        this.isFullCatalogEnabled = isFullCatalogEnabled;
    }

    /**
     *
     * @return
     * The topMelodiesCount
     */
    public Integer getTopMelodiesCount() {
        return topMelodiesCount;
    }

    /**
     *
     * @param topMelodiesCount
     * The topMelodiesCount
     */
    public void setTopMelodiesCount(Integer topMelodiesCount) {
        this.topMelodiesCount = topMelodiesCount;
    }

    /**
     *
     * @return
     * The isBlockDisplayMode
     */
    public Boolean getIsBlockDisplayMode() {
        return isBlockDisplayMode;
    }

    /**
     *
     * @param isBlockDisplayMode
     * The isBlockDisplayMode
     */
    public void setIsBlockDisplayMode(Boolean isBlockDisplayMode) {
        this.isBlockDisplayMode = isBlockDisplayMode;
    }

}

