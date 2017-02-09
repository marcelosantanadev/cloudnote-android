package marcelosantana.me.cloudnote.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Marcelo Santana on 13/11/16.
 * Contact Email: contact@marcelosantana.me and marcelo.oreen@gmail.com
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerDataManager {
}
