package com.kelebro63.intechtest.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kelebro63.intechtest.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity implements IView {

    private MaterialDialog progressDialog;

    @Override
    public void setInProgress(boolean inProgress) {

    }

    @Override
    public void displayError(String error) {

    }

    @Override
    public void displaySimpleError(String error) {

    }

    @Override
    public void displayError(@StringRes int stringRes) {

    }

    @Override
    public String provideProgressTitle() {
        return getString(R.string.dialogs_please_wait);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideSoftKeyboard();
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
