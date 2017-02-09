package marcelosantana.me.cloudnote.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import marcelosantana.me.cloudnote.CNApplication;
import marcelosantana.me.cloudnote.data.remote.NetworkService;
import marcelosantana.me.cloudnote.domain.Note;
import marcelosantana.me.cloudnote.injection.component.DaggerDataManagerComponent;
import marcelosantana.me.cloudnote.injection.module.DataManagerModule;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

public class DataManager {

    @Inject protected NetworkService mNetworkService;
    @Inject protected Scheduler mSubscribeScheduler;


    public DataManager(Context context) {
        injectDependencies(context);
    }

    public DataManager(NetworkService networkService, Scheduler subscribeScheduler) {
        mNetworkService = networkService;
        mSubscribeScheduler = subscribeScheduler;
    }

    protected void injectDependencies(Context context) {
        DaggerDataManagerComponent.builder()
                .applicationComponent(CNApplication.get(context).getAppComponent())
                .dataManagerModule(new DataManagerModule())
                .build()
                .inject(this);
    }

    public Scheduler getScheduler() {
        return mSubscribeScheduler;
    }

    public Observable<List<Note>> getNoteList() {
        return mNetworkService.getNotes()
                .concatMap(new Func1<List<Note>, Observable<? extends List<Note>>>() {
                    @Override
                    public Observable<? extends List<Note>> call(List<Note> notes) {
                        return (Observable<? extends List<Note>>) notes;
                    }
                });
    }

}
