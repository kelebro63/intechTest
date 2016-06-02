package com.kelebro63.intechtest.di.modules;

import android.support.v4.app.FragmentManager;

import com.kelebro63.intechtest.base.BaseActivity;
import com.trello.rxlifecycle.ActivityEvent;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Created by kelebro63 on 02.06.2016.
 */
@Module
public class ActivityModule {
    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides
    BaseActivity provideActivity() {
        return activity;
    }

    @Provides
    Observable.Transformer provideLifecycleTransformer() {
        return activity.bindUntilEvent(ActivityEvent.STOP);
    }
}
