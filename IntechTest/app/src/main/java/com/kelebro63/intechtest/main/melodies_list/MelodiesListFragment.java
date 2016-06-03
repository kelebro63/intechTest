package com.kelebro63.intechtest.main.melodies_list;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseFragment;
import com.kelebro63.intechtest.base.OnItemClickListener;
import com.kelebro63.intechtest.main.MainNavigator;
import com.kelebro63.intechtest.models.Melody;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MelodiesListFragment extends BaseFragment implements IMelodiesView, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<Melody> {

    public static final int FIRST_PAGE = 0;
    public static final int LIMIT_MELODIES = 20;

    @Bind(R.id.melodiesPtrView)
    SwipeRefreshLayout melodiesPtrView;
    @Bind(R.id.melodiesList)
    RecyclerView melodiesList;
    @Inject
    MelodiesPresenter presenter;
    @Inject
    MainNavigator navigator;

    @Override
    protected int getLayoutId() {
        return R.layout.melodies_fragment;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.melodies);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        createFragmentComponent().inject(this);
        presenter.setView(this);
        melodiesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        melodiesPtrView.setOnRefreshListener(this);
        melodiesPtrView.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.red_bright));
    }

    @Override
    public void displayMelodies(List<Melody> melodies) {
        MelodiesListAdapter adapter = new MelodiesListAdapter();
        adapter.setItems(melodies);
        adapter.setOnItemClickListener(this);
        melodiesList.setAdapter(adapter);
    }



    @Override
    public void displaySimpleError(@StringRes int resId) {
        super.displayError(resId);
    }

    @Override
    public void setDisplayPermissionError(boolean enabled) {

    }

    @Override
    public void stopRefreshing() {
        melodiesPtrView.setRefreshing(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu., menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onRefresh() {
        presenter.updateMelodies(LIMIT_MELODIES, FIRST_PAGE);
    }

    @Override
    public void onItemClicked(Melody item) {
       // presenter.navigateToMelody(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.:
//                navigator.navigateTo..;
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadMelodies(LIMIT_MELODIES, FIRST_PAGE);
    }

    @Override
    public void displayError(String error) {
    }

    @Override
    public void displayError(@StringRes int stringRes) {
    }
}
