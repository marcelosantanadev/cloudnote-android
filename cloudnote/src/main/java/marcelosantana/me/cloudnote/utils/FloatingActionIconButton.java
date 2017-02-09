package marcelosantana.me.cloudnote.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import marcelosantana.me.cloudnote.R;


/**
 * Created by Marcelo Santana on 09/01/2016.
 */
public class FloatingActionIconButton extends FloatingActionButton {
    private boolean mIcon;
    private Integer mColor;
    private Integer mSize;
    private String mCodeIcon;
    private Context mContext;
    private Typeface mTypeface;
    private DrawableAwesome mDrawableAwesome;

    public FloatingActionIconButton(Context context) {
        super(context);
        this.mContext = context;
    }

    public FloatingActionIconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        readAttrs(context, attrs);
        init();
    }

    public FloatingActionIconButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        readAttrs(context, attrs);
        init();
    }



    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FloatingActionIconButton);

        mIcon = a.getBoolean(R.styleable.FloatingActionIconButton_icon_used, false);
        mColor = a.getInt(R.styleable.FloatingActionIconButton_icon_color, 0);
        mCodeIcon = a.getString(R.styleable.FloatingActionIconButton_icon_code);
        mSize = a.getInt(R.styleable.FloatingActionIconButton_icon_size,0);
        a.recycle();
    }

    private void init(){
        if(mIcon){

            this.mTypeface = (Typeface.createFromAsset(mContext.getAssets(), "fonts/icomoon.ttf"));

            if(mSize != null) {
                mDrawableAwesome = new DrawableAwesome
                        .DrawableAwesomeBuilder(mContext, mCodeIcon, mColor, mSize)
                        .build();
            }else{
                mDrawableAwesome = new DrawableAwesome
                        .DrawableAwesomeBuilder(mContext, mCodeIcon, mColor)
                        .build();
            }
            this.setImageDrawable(mDrawableAwesome);


        }
    }
}
