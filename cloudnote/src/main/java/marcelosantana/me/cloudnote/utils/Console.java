package marcelosantana.me.cloudnote.utils;

import android.util.Log;

/**
 * Created by Marcelo Santana on 27/02/2016.
 */
public class Console {
    public static void log(Class c, String message) {
        Log.d("AMDEBUG (" + c.getName() + "):", message);
    }

    public static void log(String message) {
        Log.d("AMDEBUG: ", message);
    }

    public static void log(Class c, Exception exception) {
        log(c, exception.getMessage());
    }

    public static void log(Object c, Exception exception) {
        log(c.getClass(), exception.getMessage());
    }
}
