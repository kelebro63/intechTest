package com.kelebro63.intechtest.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseActivity;
import com.kelebro63.intechtest.main.melodies_list.MelodiesListFragment;
import com.kelebro63.intechtest.main.melody.MelodyFragment;
import com.kelebro63.intechtest.main.playback.PlaybackControlsFragment;
import com.kelebro63.intechtest.models.Melody;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MainNavigator {

    private static final String TAG_LIST_MELODIES_FRAGMENT = "melodies_fragment";
    private static final String TAG_PLAYBACK_FRAGMENT = "playback_fragment";

    private final FragmentManager fragmentManager;
    private final BaseActivity activity;

    @Inject
    public MainNavigator(FragmentManager fragmentManager, BaseActivity activity) {
        this.fragmentManager = fragmentManager;
        this.activity = activity;
    }

    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

    public void goBack() {
        fragmentManager.popBackStackImmediate();
    }

    public void navigateToMelodiesList() {
        MelodiesListFragment melodiesListFragment = (MelodiesListFragment) fragmentManager.findFragmentByTag(TAG_LIST_MELODIES_FRAGMENT);
        if (melodiesListFragment == null) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.container, new MelodiesListFragment(), TAG_LIST_MELODIES_FRAGMENT).commit();
        }
        if (melodiesListFragment == null) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction().replace(R.id.container, new MelodiesListFragment(), TAG_LIST_MELODIES_FRAGMENT).commit();
        }
    }

    public void navigateToPlayBackFragment(boolean show) {
        PlaybackControlsFragment controlsFragment = (PlaybackControlsFragment) fragmentManager.findFragmentById(R.id.fragment_playback_controls);
        if (show) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.animator.slide_in_from_bottom, R.animator.slide_out_to_bottom,
                            R.animator.slide_in_from_bottom, R.animator.slide_out_to_bottom)
                    .show(controlsFragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .hide(controlsFragment)
                    .commit();
        }
    }

    public void navigateToMelody(@NonNull Melody melody) {
        fragmentManager.beginTransaction().replace(R.id.container, MelodyFragment.newInstance(melody)).addToBackStack(null).commit();
    }
}
