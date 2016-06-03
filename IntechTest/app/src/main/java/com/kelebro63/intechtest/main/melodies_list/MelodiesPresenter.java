package com.kelebro63.intechtest.main.melodies_list;

import com.kelebro63.intechtest.api.IIntechAPI;
import com.kelebro63.intechtest.base.BasePresenter;
import com.kelebro63.intechtest.main.MainNavigator;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MelodiesPresenter extends BasePresenter<IMelodiesView> {

    private final IIntechAPI api;
    private final MainNavigator navigator;

    @Inject
    public MelodiesPresenter(Observable.Transformer transformer, IIntechAPI api, MainNavigator navigator) {
        super(transformer);
        this.api = api;
        this.navigator = navigator;
    }
}
