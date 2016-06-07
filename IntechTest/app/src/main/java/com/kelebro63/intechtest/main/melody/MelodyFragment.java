package com.kelebro63.intechtest.main.melody;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseFragment;
import com.kelebro63.intechtest.models.Melody;

/**
 * Created by Bistrov Alexey on 07.06.2016.
 */
public class MelodyFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IMelodyView{


    public static MelodyFragment newInstance(Melody melody) {
        MelodyFragment fragment = new MelodyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("melody", melody);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.melody_fragment;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public void displayOrder(Melody melody) {

    }

    @Override
    public void stopRefreshing() {

    }

    @Override
    public void onRefresh() {

    }
}
