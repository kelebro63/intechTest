package com.kelebro63.intechtest.main.melodies_list;

import android.support.annotation.StringRes;

import com.kelebro63.intechtest.base.IPtrView;
import com.kelebro63.intechtest.models.Melody;

import java.util.List;

/**
 * Created by kelebro63 on 02.06.2016
 */
public interface IMelodiesView extends IPtrView {
    void displayMelodies(List<Melody> orders);

    void displaySimpleError(@StringRes int resId);

    void setDisplayPermissionError(boolean enabled);
}
