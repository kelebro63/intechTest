package com.kelebro63.intechtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kelebro63.intechtest.main.melodies_list.MelodyItemType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bistrov Alexey on 02.06.2016.
 */
public class Melody implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("artistId")
        @Expose
        private Integer artistId;
        @SerializedName("artist")
        @Expose
        private String artist;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("smsNumber")
        @Expose
        private String smsNumber;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("fPrice")
        @Expose
        private Double fPrice;
        @SerializedName("prolongationPrice")
        @Expose
        private String prolongationPrice;
        @SerializedName("fProlongationPrice")
        @Expose
        private Double fProlongationPrice;
        @SerializedName("purchasePeriod")
        @Expose
        private String purchasePeriod;
        @SerializedName("position")
        @Expose
        private Integer position;
        @SerializedName("picUrl")
        @Expose
        private String picUrl;
        @SerializedName("demoUrl")
        @Expose
        private String demoUrl;
        @SerializedName("tags")
        @Expose
        private List<Tag> tags = new ArrayList<Tag>();
        @SerializedName("active")
        @Expose
        private Boolean active;
        @SerializedName("relevance")
        @Expose
        private Integer relevance;
        @SerializedName("melodyId")
        @Expose
        private String melodyId;

        @Expose
        private MelodyItemType itemType = MelodyItemType.NORMAL;

        public static Melody createDivider() {
                Melody melody = new Melody();
                melody.setItemType(MelodyItemType.DIVIDER);
                return melody;
        }


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
         * The artistId
         */
        public Integer getArtistId() {
            return artistId;
        }

        /**
         *
         * @param artistId
         * The artistId
         */
        public void setArtistId(Integer artistId) {
            this.artistId = artistId;
        }

        /**
         *
         * @return
         * The artist
         */
        public String getArtist() {
            return artist;
        }

        /**
         *
         * @param artist
         * The artist
         */
        public void setArtist(String artist) {
            this.artist = artist;
        }

        /**
         *
         * @return
         * The code
         */
        public String getCode() {
            return code;
        }

        /**
         *
         * @param code
         * The code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         *
         * @return
         * The smsNumber
         */
        public String getSmsNumber() {
            return smsNumber;
        }

        /**
         *
         * @param smsNumber
         * The smsNumber
         */
        public void setSmsNumber(String smsNumber) {
            this.smsNumber = smsNumber;
        }

        /**
         *
         * @return
         * The price
         */
        public String getPrice() {
            return price;
        }

        /**
         *
         * @param price
         * The price
         */
        public void setPrice(String price) {
            this.price = price;
        }

        /**
         *
         * @return
         * The fPrice
         */
        public Double getFPrice() {
            return fPrice;
        }

        /**
         *
         * @param fPrice
         * The fPrice
         */
        public void setFPrice(Double fPrice) {
            this.fPrice = fPrice;
        }

        /**
         *
         * @return
         * The prolongationPrice
         */
        public String getProlongationPrice() {
            return prolongationPrice;
        }

        /**
         *
         * @param prolongationPrice
         * The prolongationPrice
         */
        public void setProlongationPrice(String prolongationPrice) {
            this.prolongationPrice = prolongationPrice;
        }

        /**
         *
         * @return
         * The fProlongationPrice
         */
        public Double getFProlongationPrice() {
            return fProlongationPrice;
        }

        /**
         *
         * @param fProlongationPrice
         * The fProlongationPrice
         */
        public void setFProlongationPrice(Double fProlongationPrice) {
            this.fProlongationPrice = fProlongationPrice;
        }

        /**
         *
         * @return
         * The purchasePeriod
         */
        public String getPurchasePeriod() {
            return purchasePeriod;
        }

        /**
         *
         * @param purchasePeriod
         * The purchasePeriod
         */
        public void setPurchasePeriod(String purchasePeriod) {
            this.purchasePeriod = purchasePeriod;
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
         * The picUrl
         */
        public String getPicUrl() {
            return picUrl;
        }

        /**
         *
         * @param picUrl
         * The picUrl
         */
        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        /**
         *
         * @return
         * The demoUrl
         */
        public String getDemoUrl() {
            return demoUrl;
        }

        /**
         *
         * @param demoUrl
         * The demoUrl
         */
        public void setDemoUrl(String demoUrl) {
            this.demoUrl = demoUrl;
        }

        /**
         *
         * @return
         * The tags
         */
        public List<Tag> getTags() {
            return tags;
        }

        /**
         *
         * @param tags
         * The tags
         */
        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }

        /**
         *
         * @return
         * The active
         */
        public Boolean getActive() {
            return active;
        }

        /**
         *
         * @param active
         * The active
         */
        public void setActive(Boolean active) {
            this.active = active;
        }

        /**
         *
         * @return
         * The relevance
         */
        public Integer getRelevance() {
            return relevance;
        }

        /**
         *
         * @param relevance
         * The relevance
         */
        public void setRelevance(Integer relevance) {
            this.relevance = relevance;
        }

        /**
         *
         * @return
         * The melodyId
         */
        public String getMelodyId() {
            return melodyId;
        }

        /**
         *
         * @param melodyId
         * The melodyId
         */
        public void setMelodyId(String melodyId) {
            this.melodyId = melodyId;
        }

        public MelodyItemType getItemType() {
                return itemType;
        }

        public void setItemType(MelodyItemType itemType) {
                this.itemType = itemType;
        }
}
