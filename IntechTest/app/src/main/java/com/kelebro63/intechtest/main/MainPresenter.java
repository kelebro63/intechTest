package com.kelebro63.intechtest.main;

import com.kelebro63.intechtest.api.IIntechAPI;
import com.kelebro63.intechtest.base.BasePresenter;
import com.kelebro63.intechtest.base.IView;

import javax.inject.Inject;

import rx.Observable;

/*
* Created by kelebro63 on 02.06.2016
*/
public class MainPresenter extends BasePresenter<IView> {

    private final IIntechAPI api;
    private final MainNavigator navigator;

    @Inject
    public MainPresenter(Observable.Transformer transformer, IIntechAPI api, MainNavigator navigator) {
        super(transformer);
        this.api = api;
        this.navigator = navigator;
    }

    public void determineScreenToShow() {
        navigator.navigateToMelodiesList();
    }
}
