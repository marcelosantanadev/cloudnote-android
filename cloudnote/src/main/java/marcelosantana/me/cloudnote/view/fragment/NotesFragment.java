package marcelosantana.me.cloudnote.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import marcelosantana.me.cloudnote.CNApplication;
import marcelosantana.me.cloudnote.R;
import marcelosantana.me.cloudnote.data.DataManager;
import marcelosantana.me.cloudnote.domain.Note;
import marcelosantana.me.cloudnote.utils.ConnectionDetector;
import marcelosantana.me.cloudnote.view.adapter.NoteAdapter;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

public class NotesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recycler_stories)
    RecyclerView mListNotes;

    @Bind(R.id.layout_offline)
    LinearLayout mOfflineContainer;

    @Bind(R.id.progress_indicator)
    ProgressBar mProgressBar;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private DataManager mDataManager;
    private NoteAdapter mNoteAdapter;
    private CompositeSubscription mSubscriptions;
    private List<Note> mNotes = new ArrayList<>();

    public NotesFragment() {
    }

    public static NotesFragment newInstance() {
        NotesFragment notesFragment = new NotesFragment();
        Bundle args = new Bundle();
        return notesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriptions = new CompositeSubscription();
        mNotes = new ArrayList<>();
        mDataManager = CNApplication.get(getActivity()).getAppComponent().dataManager();
        mNoteAdapter = new NoteAdapter(getActivity(), false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.note_fragment, container, false);
        ButterKnife.bind(this, fragmentView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        setupToolbar();
        setupRecyclerView();
        loadNotesIfNetworkConnected();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    public void onRefresh() {
        mSubscriptions.unsubscribe();
        if (mNoteAdapter != null) mNoteAdapter.setNotes(new ArrayList<Note>());
        getNoteStories();
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        mListNotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListNotes.setHasFixedSize(true);
        mNoteAdapter.setNotes(mNotes);
        mListNotes.setAdapter(mNoteAdapter);
    }

    private void loadNotesIfNetworkConnected() {
        if (new ConnectionDetector(getActivity()).isConnectingToInternet()) {
            showHideOfflineLayout(false);
            getNoteStories();
        } else {
            showHideOfflineLayout(true);
        }
    }

    private void getNoteStories() {
        mSubscriptions.add((Subscription) mDataManager.getNoteList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribe(new Subscriber<List<Note>>() {
                    @Override
                    public void onCompleted() {
                        if (!mNotes.isEmpty())
                            mNoteAdapter.setNotes(mNotes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingViews();

                    }

                    @Override
                    public void onNext(List<Note> notes) {
                        mNotes = notes;
                    }
                }));
    }

    private void hideLoadingViews() {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void showHideOfflineLayout(boolean isOffline) {
        mOfflineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        mListNotes.setVisibility(isOffline ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }

}
