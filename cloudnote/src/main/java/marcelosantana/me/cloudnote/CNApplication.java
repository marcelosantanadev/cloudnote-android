package marcelosantana.me.cloudnote;

import android.app.Application;
import android.content.Context;

import java.util.Locale;

import marcelosantana.me.cloudnote.injection.component.ApplicationComponent;
import marcelosantana.me.cloudnote.injection.component.DaggerApplicationComponent;
import marcelosantana.me.cloudnote.injection.module.ApplicationModule;


/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

public class CNApplication extends Application {
    private static Context mContext;
    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
        Locale.setDefault(new Locale("pt", "BR"));
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static CNApplication get(Context context) {
        return (CNApplication) context.getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        CNApplication.mContext = mContext;
    }

    public ApplicationComponent getAppComponent() {
        return mApplicationComponent;
    }

    public void setAppComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
