package marcelosantana.me.cloudnote.injection.component;

import dagger.Component;
import marcelosantana.me.cloudnote.data.DataManager;
import marcelosantana.me.cloudnote.injection.module.DataManagerModule;
import marcelosantana.me.cloudnote.injection.scope.PerDataManager;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = DataManagerModule.class)
public interface DataManagerComponent {

    void inject(DataManager dataManager);
}
