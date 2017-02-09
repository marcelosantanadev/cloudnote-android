package marcelosantana.me.cloudnote.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class Util {
    private static Util mUtil;
    private Context mContext;
    private Activity mActivity;

    public Util(@Nullable Context context){
        this.mContext = context;
    }

    public Util(@Nullable Activity activity){
        this.mActivity = activity;
    }

    public Util(Activity mActivity, Context mContext) {
        this.mActivity = mActivity;
        this.mContext = mContext;
    }

    public static Util getInstance(){
        if(mUtil == null){
            mUtil = new Util(null);
        }
        return mUtil;
    }

    public static Util getInstance(Context context){
        if(mUtil == null || context == null){
            mUtil = new Util(context);
        }
        return mUtil;
    }



    public static Util getInstance(Activity activity){
        if(mUtil == null || activity == null){
            mUtil = new Util(activity);
        }
        return mUtil;
    }

    public static Util getInstance(Activity mActivity, Context mContext) {
        if(mUtil == null || mActivity == null){
            mUtil = new Util(mActivity, mContext);
        }
        return mUtil;
    }


    public boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void setTimeout(final Callback callback, final long time){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    callback.success();
                } catch (InterruptedException e) {
                    callback.error(e);
                }
            }
        }).start();
    }

    public void setInterval(final Callback callback, final long time){
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        callback.error(e);
                        break;
                    }
                } while (callback.success());
            }
        }).start();
    }



    public interface Callback {
        boolean success();
        void error(Exception e);
    }
}
