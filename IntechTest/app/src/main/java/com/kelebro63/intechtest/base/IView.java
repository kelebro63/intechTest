package com.kelebro63.intechtest.base;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by dtx12 on 23.09.2015.
 */
public interface IView {
    void setInProgress(boolean inProgress);

    void displayError(String error);

    void displaySimpleError(String error);

    void displayError(@StringRes int stringRes);

    String provideProgressTitle();

    Context getContext();
}
