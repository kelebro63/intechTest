package com.kelebro63.intechtest.main.melodies_list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
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
import com.kelebro63.intechtest.widgets.DividerItemDecoration;

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

    private static final String KEY_LAYOUT_MANAGER_TYPE = "layoutManagerType";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT_PORTRAIT = 2;
    private static final int SPAN_COUNT_LANDSCAPE = 3;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private LayoutManagerType mCurrentLayoutManagerType;

    private MelodiesListAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.melodies_fragment;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.melodies);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        adapter = new MelodiesListAdapter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        createFragmentComponent().inject(this);
        presenter.setView(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER_TYPE);
            Parcelable savedRecyclerLayoutState =  savedInstanceState.getParcelable(KEY_LAYOUT_MANAGER);
            setRecyclerViewLayoutManager(mCurrentLayoutManagerType, savedRecyclerLayoutState);
        } else {
            setRecyclerViewLayoutManager(mCurrentLayoutManagerType, null);
        }

        melodiesList.setLayoutManager(layoutManager);
        melodiesList.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        melodiesPtrView.setOnRefreshListener(this);
        melodiesPtrView.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.red_bright));

        adapter.setOnItemClickListener(this);
        melodiesList.setAdapter(adapter);
        melodiesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!adapter.isLoading() &&  //adapter is not already loading more news
                        layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1 //reached the last item
                        ) {
                    adapter.displayLoadingFooter();//indicate loading more news by showing footer
                    melodiesList.smoothScrollToPosition(adapter.getItemCount() - 1);//smooth scroll to footer
                    presenter.loadMelodies();//load more melodies
                }
            }
        });
        if (adapter.isEmpty()) {
            presenter.loadMelodies();
        }
    }


    @Override
    public void addMelodiesToDisplay(List<Melody> melodies) {
        adapter.addItems(melodies);
    }

    @Override
    public void setMelodiesToDisplay(List<Melody> melodies) {
        adapter.setItems(melodies);
        if (melodies != null && melodies.size() > 0) {
            melodiesList.animate().alpha(1).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        }
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
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onRefresh() {
        if (!adapter.isLoading()) {
            presenter.updateMelodies(LIMIT_MELODIES, FIRST_PAGE);
        }
    }

    @Override
    public void onItemClicked(Melody item) {
         presenter.navigateToMelody(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list:
                if ( mCurrentLayoutManagerType == LayoutManagerType.GRID_LAYOUT_MANAGER) {
                    layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                } else {
                    layoutManager = new GridLayoutManager(getActivity(), 2);
                    mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                }
                melodiesList.setLayoutManager(layoutManager);
                melodiesList.setAdapter(adapter);
                return true;
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
    }

    @Override
    public void displayError(String error) {
    }

    @Override
    public void displayError(@StringRes int stringRes) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.melodies_list_menu, menu);
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType, Parcelable savedRecyclerLayoutState) {
        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT_PORTRAIT);
                } else {
                    layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT_LANDSCAPE);
                }

                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                layoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                layoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        melodiesList.setLayoutManager(layoutManager);
        if (savedRecyclerLayoutState != null) {
            melodiesList.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        try {
            savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER_TYPE, mCurrentLayoutManagerType);
            if (melodiesList != null)  savedInstanceState.putParcelable(KEY_LAYOUT_MANAGER, melodiesList.getLayoutManager().onSaveInstanceState());
            super.onSaveInstanceState(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
