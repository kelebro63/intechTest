package com.kelebro63.intechtest.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kelebro63.intechtest.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MainNavigator {
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
}
