package com.kelebro63.intechtest.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kelebro63.intechtest.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dtx12 on 24.09.2015.
 */
public abstract class BaseToolbarActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_ab_back);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        enableHomeButtonIfNeeded();
    }

    private void enableHomeButtonIfNeeded() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().removeOnBackStackChangedListener(this);
    }

    protected abstract int getLayoutId();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }
}
