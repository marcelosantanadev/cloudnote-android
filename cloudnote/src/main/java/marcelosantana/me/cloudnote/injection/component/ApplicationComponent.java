package marcelosantana.me.cloudnote.injection.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import marcelosantana.me.cloudnote.data.DataManager;
import marcelosantana.me.cloudnote.injection.module.ApplicationModule;
import marcelosantana.me.cloudnote.view.activity.MainActivity;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    Application application();
    DataManager dataManager();

}
