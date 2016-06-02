package com.kelebro63.intechtest.di.components;

import com.kelebro63.intechtest.di.modules.ActivityModule;
import com.kelebro63.intechtest.di.qualifiers.PerActivity;
import com.kelebro63.intechtest.main.MainActivity;

import dagger.Component;

/**
 * Created by kelebro63 on 02.06.2016
 */
@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
}
