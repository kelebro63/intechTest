package com.kelebro63.intechtest.di.components;

import com.kelebro63.intechtest.di.modules.FragmentModule;
import com.kelebro63.intechtest.di.qualifiers.PerFragment;
import com.kelebro63.intechtest.main.melodies_list.MelodiesListFragment;

import dagger.Component;

/**
 * Created by kelebro63 on 02.06.2016
 */
@PerFragment
@Component(dependencies = {AppComponent.class}, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(MelodiesListFragment fragment);

}
