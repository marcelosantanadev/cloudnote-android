package marcelosantana.me.cloudnote.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Marcelo Santana on 13/01/2016.
 */
public class ConfigurableRecyclerView extends RecyclerView {
    private boolean isScrollingActive;
    int scrollDist = 0;
    boolean isVisible = true;
    private int y;
    static final float MINIMUM = 25;

    public ConfigurableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ConfigurableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConfigurableRecyclerView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return isScrollingActive || super.onTouchEvent(e);
    }

    //Enables/Disables scrolling through touch interaction with the RecyclerView directly
    public void setScrollingActive(boolean isScrollingActive) {
        this.isScrollingActive = isScrollingActive;
    }


    //Responsible for starting a programmatic scroll
    public boolean dispatchHandlerScroll(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) e.getY();
                startNestedScroll(2);
                break;
            case MotionEvent.ACTION_MOVE:
                int dY = y - ((int)e.getY());
                dispatchNestedPreScroll(0, dY, null, null);
                dispatchNestedScroll(0, 0, 0, dY, null);
                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }
        return true;
    }

}
