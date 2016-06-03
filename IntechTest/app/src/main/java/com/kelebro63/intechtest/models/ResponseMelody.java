package com.kelebro63.intechtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bistrov Alexey on 03.06.2016.
 */
public class ResponseMelody implements Serializable {
        @SerializedName("melodies")
        @Expose
        private List<Melody> melodies = new ArrayList<Melody>();

        /**
         *
         * @return
         * The melodies
         */
        public List<Melody> getMelodies() {
            return melodies;
        }

        /**
         *
         * @param melodies
         * The melodies
         */
        public void setMelodies(List<Melody> melodies) {
            this.melodies = melodies;
        }

        public static ArrayList<Melody> addDividers(List<Melody> melodies) {
                ArrayList<Melody> result = new ArrayList<>();
                for (int i = 0; i < melodies.size(); i++) {
                        result.add(melodies.get(i));
                        if (i != melodies.size() - 1) {
                                result.add(Melody.createDivider());
                        }
                }
                return result;
        }
}
