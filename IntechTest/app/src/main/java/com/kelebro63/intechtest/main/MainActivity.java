package com.kelebro63.intechtest.main;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.PlaybackControlBaseActivity;
import com.kelebro63.intechtest.main.melodies_list.MelodiesListFragmentListener;

import javax.inject.Inject;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MainActivity extends PlaybackControlBaseActivity implements MelodiesListFragmentListener {
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

    @Override
    public void onMediaItemSelected(MediaBrowserCompat.MediaItem item) {
        if (item.isPlayable()) {
            final MediaControllerCompat mediaController = getSupportMediaController();
            if (mediaController != null) {
                mediaController.getTransportControls().playFromMediaId(item.getMediaId(), null);
            }
        } else {
           //error
        }
    }
}
