package marcelosantana.me.cloudnote.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;


import marcelosantana.me.cloudnote.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class UtilViews {

    private static UtilViews sUtilViews;

    private MaterialDialog mDialogProgress;


    public UtilViews() {
    }

    public static UtilViews getInstance() {
        if (sUtilViews == null) {
            sUtilViews = new UtilViews();
        }
        return sUtilViews;
    }



    public void goToActivity(final Activity activity, final Class<?> destination, final boolean isFinish) {
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(new Intent(activity, destination));
                activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                if(isFinish)
                activity.finish();
            }
        });
    }

    public void goToActivity(final Activity activity, final Class<?> destination, final Bundle bundle, final boolean isFinish) {
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(new Intent(activity, destination).putExtras(bundle));
                activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                if (isFinish)
                    activity.finish();
            }
        });
    }

    public void goToActivity(final Activity activity, final Class<?> destination, final boolean isFinish, final int enterAnim, final int exitAnim) {
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(new Intent(activity, destination));
                activity.overridePendingTransition(enterAnim, exitAnim);
                if (isFinish)
                    activity.finish();
            }
        });
    }

    public void requestFocus(Activity activity, View view) {
        if (view.requestFocus()) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }



    public void initCaligraphy(Context context) {
        if(context != null) {
            // Caligraphy
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                            .setDefaultFontPath(context.getString(R.string.font_sansation_regular))
                            .setFontAttrId(R.attr.fontPath)
                            .build()
            );
        }
    }

    public static ShapeDrawable drawCircle (Context context, int width, int height, int color) {

        //////Drawing oval & Circle programmatically /////////////

        ShapeDrawable oval = new ShapeDrawable (new OvalShape());
        oval.setIntrinsicHeight (height);
        oval.setIntrinsicWidth (width);
        oval.getPaint ().setColor (color);
        return oval;
    }

    public static Integer getPrimaryColor(Context context) {
        TypedValue typedValue = new TypedValue();

        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);

        return typedValue.data;
    }

    public static Integer getColor(Context context, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            // Noinspection deprecation
            return context.getResources().getColor(colorId);
        } else {
            return context.getColor(colorId);
        }
    }

    public static Boolean checkApiGreaterThan(Integer api) {
        return Build.VERSION.SDK_INT >= api;
    }


    public interface UtilViewsCustomLayout {
        void customize(MaterialDialog dialog);
    }

    public interface UtilViewsCallbackDialog {
        void onPositive(Integer id, MaterialDialog materialDialog, DialogAction dialogAction);
    }

    public interface UtilViewsCallbackDialogComplete {
        void onPositive(int id, MaterialDialog materialDialog, DialogAction dialogAction);
        void onNegative(int id, MaterialDialog materialDialog, DialogAction dialogAction);
    }


}
