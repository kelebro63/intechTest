package com.kelebro63.intechtest.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kelebro63.intechtest.App;
import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.di.components.ActivityComponent;
import com.kelebro63.intechtest.di.components.DaggerActivityComponent;
import com.kelebro63.intechtest.di.modules.ActivityModule;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by kelebro63 on 02.06.2016
 */

public class BaseActivity extends RxAppCompatActivity implements IView {

    private MaterialDialog progressDialog;

    @Override
    public void setInProgress(boolean inProgress) {

    }

    protected ActivityComponent createActivityComponent() {
        return DaggerActivityComponent.builder().appComponent(App.getAppComponent(this)).activityModule(new ActivityModule(this)).build();
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
