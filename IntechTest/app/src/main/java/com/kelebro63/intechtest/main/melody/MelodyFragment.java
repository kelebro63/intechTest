package com.kelebro63.intechtest.main.melody;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseFragment;
import com.kelebro63.intechtest.models.Melody;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Bistrov Alexey on 07.06.2016.
 */
public class MelodyFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IMelodyView{

    @Inject
    protected MelodyPresenter presenter;

    @Nullable
    @Bind(R.id.play)
    Button btnPlay;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createFragmentComponent().inject(this);
        presenter.setView(this);
    }

    @Override
    protected String getTitle() {
        return null;
    }

    protected Melody getCachedMelody() {
        return (Melody) getArguments().getSerializable("melody");
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

    @Nullable
    @OnClick(R.id.play)
    void play() {
        presenter.playStream(getCachedMelody());
    }
}