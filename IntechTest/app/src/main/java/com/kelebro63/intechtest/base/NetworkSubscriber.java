package com.kelebro63.intechtest.base;

import android.support.annotation.Nullable;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.models.ErrorModel;
import com.kelebro63.intechtest.utils.RetrofitException;
import com.kelebro63.intechtest.utils.RetrofitUtils;

import rx.Subscriber;

/*
 * Created by kelebro63 on 02.06.2016
 */
public class NetworkSubscriber<T> extends Subscriber<T> {
    private static final String TAG = "NetworkSubscriber";
    protected final IView view;
    @Nullable
    protected final BasePresenter presenter;

    public NetworkSubscriber(IView view, @Nullable BasePresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        view.setInProgress(true);
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        view.setInProgress(false);

        ErrorModel errorModel = null;
        try {
            errorModel = RetrofitUtils.getError(throwable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (errorModel != null && errorModel.getMessage() != null && !errorModel.getMessage().isEmpty()) {
            view.displayError(errorModel.getMessage());
        } else {
            RetrofitException error = (RetrofitException) throwable;
            switch (error.getKind()) {

                case NETWORK:
                    view.displayError(R.string.api_error_network);
                    return;
                case HTTP:
                    view.displayError(R.string.api_error_server);
                    return;
            }
        }
    }


    @Override
    public void onNext(T t) {
        view.setInProgress(false);
    }
}
