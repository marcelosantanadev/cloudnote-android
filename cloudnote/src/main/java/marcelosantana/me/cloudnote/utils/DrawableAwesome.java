package marcelosantana.me.cloudnote.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

/**
 * Created by Marcelo Santana on 09/01/2016.
 */
public class DrawableAwesome extends Drawable {

    private static final float PADDING_RATIO = 0.88f;

    private final Context context;
    private int icon;
    private String iconString;
    private final Paint paint;
    private final int width;
    private final int height;
    private final float size;
    private final int color;
    private final boolean antiAliased;
    private final boolean fakeBold;
    private final float shadowRadius;
    private final float shadowDx;
    private final float shadowDy;
    private final int shadowColor;

    public DrawableAwesome(String icon, int sizeDpi, int color,
                           boolean antiAliased, boolean fakeBold, float shadowRadius,
                           float shadowDx, float shadowDy, int shadowColor, Context context) {
        super();
        this.context = context;
        this.iconString = icon;
        this.size = dpToPx(sizeDpi) * PADDING_RATIO;
        this.height = dpToPx(sizeDpi);
        this.width = dpToPx(sizeDpi);
        this.color = color;
        this.antiAliased = antiAliased;
        this.fakeBold = fakeBold;
        this.shadowRadius = shadowRadius;
        this.shadowDx = shadowDx;
        this.shadowDy = shadowDy;
        this.shadowColor = shadowColor;
        this.paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setColor(this.color);
        this.paint.setTextSize(this.size);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/icomoon.ttf");
        this.paint.setTypeface(font);
        this.paint.setAntiAlias(this.antiAliased);
        this.paint.setFakeBoldText(this.fakeBold);
        this.paint.setShadowLayer(this.shadowRadius, this.shadowDx, this.shadowDy, this.shadowColor);
    }

    public DrawableAwesome(int icon, int sizeDpi, int color,
                           boolean antiAliased, boolean fakeBold, float shadowRadius,
                           float shadowDx, float shadowDy, int shadowColor, Context context) {
        super();
        this.context = context;
        this.icon = icon;
        this.size = dpToPx(sizeDpi) * PADDING_RATIO;
        this.height = dpToPx(sizeDpi);
        this.width = dpToPx(sizeDpi);
        this.color = color;
        this.antiAliased = antiAliased;
        this.fakeBold = fakeBold;
        this.shadowRadius = shadowRadius;
        this.shadowDx = shadowDx;
        this.shadowDy = shadowDy;
        this.shadowColor = shadowColor;
        this.paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setColor(this.color);
        this.paint.setTextSize(this.size);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/icomoon.ttf");
        this.paint.setTypeface(font);
        this.paint.setAntiAlias(this.antiAliased);
        this.paint.setFakeBoldText(this.fakeBold);
        this.paint.setShadowLayer(this.shadowRadius, this.shadowDx, this.shadowDy, this.shadowColor);
    }

    @Override
    public int getIntrinsicHeight() {
        return height;
    }

    @Override
    public int getIntrinsicWidth() {
        return  width;
    }

    @Override
    public void draw(Canvas canvas) {
        float xDiff = (width/2.0f);

        String stringIcon = iconString != null ? iconString : this.context.getResources().getString(icon);
        canvas.drawText(stringIcon, xDiff, size, paint);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static class DrawableAwesomeBuilder {
        private Context context;
        private int icon;
        private String iconString;
        private int sizeDpi = 32;
        private int color;
        private boolean antiAliased = true;
        private boolean fakeBold = true;
        private float shadowRadius = 0;
        private float shadowDx = 0;
        private float shadowDy = 0;
        private int shadowColor = Color.WHITE;

        public DrawableAwesomeBuilder(Context context, int icon, int color) {
            this.context = context;
            this.icon = icon;
            this.color = color;
        }

        public DrawableAwesomeBuilder(Context context, String icon, int color) {
            this.context = context;
            this.iconString = icon;
            this.color = color;
        }

        public DrawableAwesomeBuilder(Context context, String icon, String color) {
            this.context = context;
            this.iconString = icon;
            this.color = Color.parseColor(color);
        }

        public DrawableAwesomeBuilder(Context context, int icon, String color) {
            this.context = context;
            this.icon = icon;
            this.color = Color.parseColor(color);
        }

        public DrawableAwesomeBuilder(Context context, int icon, int color, float size) {
            this.context = context;
            this.icon = icon;
            this.color = color;
            this.setSize((int) size);
        }

        public DrawableAwesomeBuilder(Context context, String icon, int color, int size) {
            this.context = context;
            this.iconString = icon;
            this.color = color;
            this.setSize(size);
        }


        public void setSize(int size) {
            this.sizeDpi = size;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public void setAntiAliased(boolean antiAliased) {
            this.antiAliased = antiAliased;
        }

        public void setFakeBold(boolean fakeBold) {
            this.fakeBold = fakeBold;
        }

        public void setShadow(float radius, float dx, float dy, int color) {
            this.shadowRadius = radius;
            this.shadowDx = dx;
            this.shadowDy = dy;
            this.shadowColor = color;
        }

        public DrawableAwesome build() {
            return iconString == null
                    ? new DrawableAwesome(icon, sizeDpi, color, antiAliased, fakeBold,
                    shadowRadius, shadowDx, shadowDy, shadowColor, context)
                    : new DrawableAwesome(iconString, sizeDpi, color, antiAliased, fakeBold,
                    shadowRadius, shadowDx, shadowDy, shadowColor, context);
        }
    }
}