package marcelosantana.me.cloudnote.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;

import marcelosantana.me.cloudnote.R;


/**
 * Created by Marcelo Santana on 17/01/2016.
 */
public class ImageIconView extends ImageView {
    private boolean mIcon;
    private Integer mColor;
    private Integer mSize;
    private String mCodeIcon;
    private Context mContext;
    private Typeface mTypeface;
    private DrawableAwesome mDrawableAwesome;


    public ImageIconView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ImageIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        readAttrs(context, attrs);
        init();
    }

    public ImageIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        readAttrs(context, attrs);
        init();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageIconView);

        mIcon = a.getBoolean(R.styleable.ImageIconView_iv_icon_used, false);
        mColor = a.getInt(R.styleable.ImageIconView_iv_icon_color, 0);
        mCodeIcon = a.getString(R.styleable.ImageIconView_iv_icon_code);
        a.recycle();
    }

    private void init(){
        if(mIcon){

            this.mTypeface = (Typeface.createFromAsset(mContext.getAssets(), "fonts/icomoon.ttf"));


                mDrawableAwesome = new DrawableAwesome
                        .DrawableAwesomeBuilder(mContext, mCodeIcon, mColor)
                        .build();

            this.setImageDrawable(mDrawableAwesome);


        }
    }
}
