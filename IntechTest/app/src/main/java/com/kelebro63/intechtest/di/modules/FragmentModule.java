package com.kelebro63.intechtest.di.modules;

import android.support.v4.app.FragmentManager;

import com.kelebro63.intechtest.base.BaseActivity;
import com.kelebro63.intechtest.base.BaseFragment;
import com.trello.rxlifecycle.FragmentEvent;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Created by kelebro63 on 02.06.2016
 */
@Module
public class FragmentModule {
    private final BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    BaseActivity provideActivity() {
        return ((BaseActivity) fragment.getActivity());
    }

    @Provides
    FragmentManager provideFragmentManager(BaseActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Provides
    Observable.Transformer provideTransformer() {
        return fragment.bindUntilEvent(FragmentEvent.STOP);
    }

    @Provides
    BaseFragment provideFragment() {
        return fragment;
    }
}
