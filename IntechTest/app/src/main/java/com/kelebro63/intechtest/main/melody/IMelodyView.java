package com.kelebro63.intechtest.main.melody;

import com.kelebro63.intechtest.base.IPtrView;
import com.kelebro63.intechtest.models.Collection;

/**
 * reated by kelebro63 on 07.06.2016
 */
public interface IMelodyView extends IPtrView {
    void displayMelody(Collection melody);

    void showPlayButton();

    void showPauseButton();

    void setDurationPlayerProgress(int max);

    void setCurrentPlayerProgress(int progress);

    void setTime(int progress, int max);

}
