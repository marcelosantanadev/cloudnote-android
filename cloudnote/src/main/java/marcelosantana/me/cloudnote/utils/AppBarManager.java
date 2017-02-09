package marcelosantana.me.cloudnote.utils;

import android.support.design.widget.AppBarLayout;

/**
 * Created by Marcelo Santana on 13/01/2016.
 */
public abstract class AppBarManager implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE,
        DEFAULT
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else if ((-i) >= 177) {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        } else {
            if (mCurrentState != State.DEFAULT) {
                onStateChanged(appBarLayout, State.DEFAULT);
            }
            mCurrentState = State.DEFAULT;
        }

        /*onObservableStateOffset(i, mCurrentState);*/
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    /*public abstract void onObservableStateOffset(int i, State state);*/
}