package com.kelebro63.intechtest.di.components;

import com.kelebro63.intechtest.api.IIntechAPI;
import com.kelebro63.intechtest.di.modules.AppModule;
import com.kelebro63.intechtest.main.melodies_list.MelodyNormalViewHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kelebro63 on 02.06.20165.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    IIntechAPI api();

    void inject(MelodyNormalViewHolder melodyNormalViewHolder);
}
