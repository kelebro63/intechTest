package com.kelebro63.intechtest.main.melody;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseFragment;
import com.kelebro63.intechtest.models.Melody;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Bistrov Alexey on 07.06.2016.
 */
public class MelodyFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IMelodyView, SeekBar.OnSeekBarChangeListener{

    @Inject
    protected MelodyPresenter presenter;

    @Bind(R.id.cover)
    ImageView cover;

    @Nullable
    @Bind(R.id.play)
    ImageButton btnPlayPause;

    @Nullable
    @Bind(R.id.stop)
    ImageButton btnStop;

    @Nullable
    @Bind(R.id.tv_progress)
    TextView tvProgress;

    @Nullable
    @Bind(R.id.tv_left_time)
    TextView tvLeftTime;


    @Nullable
    @Bind(R.id.seekBar)
    SeekBar seekBar;

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
        Log.d("debug","onViewCreated");
        createFragmentComponent().inject(this);
        presenter.setView(this);
        Picasso.with(getContext()).load(getCachedMelody().getPicUrl()).into(cover);
        seekBar.setOnSeekBarChangeListener(this);
        initViews();
    }

    private void initViews() {
        presenter.determinateShowButtons();
    }

    @Override
    protected String getTitle() {
        return getCachedMelody().getTitle();
    }

    protected Melody getCachedMelody() {
        return (Melody) getArguments().getSerializable("melody");
    }


    @Override
    public void displayMelody(Melody melody) {

    }

    @Override
    public void showPlayButton() {
        if (btnPlayPause != null) {
            btnPlayPause.setImageResource(R.drawable.ic_action_play);
        }

    }

    @Override
    public void showPauseButton() {
        if (btnPlayPause != null) {
            btnPlayPause.setImageResource(R.drawable.ic_action_pause);
        }
    }

    @Override
    public void setDurationPlayerProgress(int max) {
        seekBar.setMax(max);
    }

    @Override
    public void setCurrentPlayerProgress(int progress) {
        if (seekBar != null) {
            seekBar.setProgress(progress);
        }
    }

    @Override
    public void setTime(int progress, int max) {
        tvProgress.setText(DateUtils.formatElapsedTime(progress / 1000));
        tvLeftTime.setText(DateUtils.formatElapsedTime(max/1000));
    }

    @Override
    public void stopRefreshing() {

    }

    @Override
    public void onRefresh() {

    }

    @Nullable
    @OnClick(R.id.play)
    void play(){
        presenter.playPauseStream(getCachedMelody());
    }


    @Nullable
    @OnClick(R.id.stop)
    void stop() {
        presenter.stopStream();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d("debug","onDestroy");
        if (!getActivity().isChangingConfigurations()) {
            presenter.clearPlayer();
        }
        super.onDestroy();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser) {
            presenter.setProgressPlayer(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("debug","onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}
