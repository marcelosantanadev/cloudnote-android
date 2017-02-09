package marcelosantana.me.cloudnote.injection.module;

import dagger.Module;
import dagger.Provides;
import marcelosantana.me.cloudnote.data.remote.NetworkService;
import marcelosantana.me.cloudnote.injection.scope.PerDataManager;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

@Module
public class DataManagerModule {
    public DataManagerModule() {
    }

    @Provides
    @PerDataManager
    NetworkService provideNetworkService() {
        return NetworkService.Factory.create();
    }

    @Provides
    @PerDataManager
    Scheduler provideSubscribeScheduler() {
        return Schedulers.io();
    }

}
