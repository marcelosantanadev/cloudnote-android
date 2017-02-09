package marcelosantana.me.cloudnote.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import marcelosantana.me.cloudnote.R;


/**
 * Created by Marcelo Santana on 09/01/2016.
 */
public class TextIconView extends TextView {

    private boolean icone;
    private Context mContext;

    public TextIconView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TextIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        readAttrs(context, attrs);
        init();
    }

    public TextIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        readAttrs(context, attrs);
        init();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextIconView);
        icone = a.getBoolean(R.styleable.TextIconView_icone, false);
        a.recycle();
    }

    private void init(){
        if(icone){
            setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/icomoon.ttf"));
        }
    }
}