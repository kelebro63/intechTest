package com.kelebro63.intechtest.main;

import android.os.Bundle;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseToolbarActivity;
import com.kelebro63.intechtest.base.PlaybackControlBaseActivity;
import com.kelebro63.intechtest.main.melodies_list.control.PlaybackControlsFragment;

import javax.inject.Inject;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MainActivity extends PlaybackControlBaseActivity {
    @Inject
    MainNavigator navigator;
    @Inject
    MainPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.container_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createActivityComponent().inject(this);
        presenter.setView(this);
        presenter.determineScreenToShow();
    }
}
