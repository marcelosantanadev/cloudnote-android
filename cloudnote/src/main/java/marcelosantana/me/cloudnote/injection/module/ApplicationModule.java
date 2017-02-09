package marcelosantana.me.cloudnote.injection.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import marcelosantana.me.cloudnote.data.DataManager;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager(mApplication);
    }
}
