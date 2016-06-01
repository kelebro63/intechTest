package com.kelebro63.intechtest.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kelebro63.intechtest.R;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by dtx12 on 22.09.2015.
 */
public abstract class BaseFragment extends RxFragment implements IView {
    private MaterialDialog progressDialog;

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (getView() instanceof ViewGroup) {
            ((ViewGroup) getView()).removeAllViews();
        }
    }

//    protected FragmentComponent createFragmentComponent() {
//        return DaggerFragmentComponent.builder().appComponent(App.getAppComponent(getActivity())).fragmentModule(new FragmentModule(this)).build();
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof BaseToolbarActivity) {
            getSupportActionBar().setTitle(getTitle());
            getSupportActionBar().setSubtitle(getSubtitle());
        }
    }

    @Override
    public void setInProgress(boolean inProgress) {
        if (progressDialog == null) {
            progressDialog = createProgressDialog();
        }
        if (inProgress) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        setInProgress(false);
    }

    private ActionBar getSupportActionBar() {
        return ((BaseActivity) getActivity()).getSupportActionBar();
    }

    protected abstract String getTitle();

    @Override
    public void displayError(String error) {
        ((BaseActivity) getActivity()).displayError(error);
    }

    @Override
    public void displaySimpleError(String error) {
        ((BaseActivity) getActivity()).displaySimpleError(error);
    }

    @Override
    public void displayError(@StringRes int stringRes) {
        ((BaseActivity) getActivity()).displayError(stringRes);
    }

//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        if (enter) {
//            return AnimationUtils.loadAnimation(getContext(), R.anim.abc_fade_in);
//        }
//        return AnimationUtils.loadAnimation(getContext(), R.anim.abc_fade_out);
//    }

    @Nullable
    protected String getSubtitle() {
        return null;
    }

    @Override
    public String provideProgressTitle() {
        return getString(R.string.dialogs_please_wait);
    }

    private MaterialDialog createProgressDialog() {
        progressDialog = new MaterialDialog.Builder(getActivity())
                .progress(true, 0)
                .title(provideProgressTitle())
                .cancelListener(((DialogInterface.OnCancelListener) getActivity())).build();
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
