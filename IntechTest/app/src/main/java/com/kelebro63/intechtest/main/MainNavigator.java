package com.kelebro63.intechtest.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseActivity;
import com.kelebro63.intechtest.main.melodies_list.MelodiesListFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MainNavigator {

    private static final String TAG_LIST_MELODIES_FRAGMENT = "melodies_fragment";

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

    }
}
