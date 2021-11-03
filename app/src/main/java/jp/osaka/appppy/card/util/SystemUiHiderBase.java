
package jp.osaka.appppy.card.util;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

import androidx.annotation.RequiresApi;

/**
 * A base implementation of {@link SystemUiHider}. Uses APIs available in all
 * API levels to show and hide the status bar.
 */
public class SystemUiHiderBase extends SystemUiHider {
    /**
     * Whether or not the system UI is currently visible. This is a cached value
     * from calls to {@link #hide()} and {@link #show()}.
     */
    private boolean mVisible = true;

    /**
     * Constructor not intended to be called by clients. Use
     * {@link SystemUiHider#getInstance} to obtain an instance.
     */
    protected SystemUiHiderBase(Activity activity, View anchorView, int flags) {
        super(activity, anchorView, flags);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisible() {
        return mVisible;
    }

    /**
     * {@inheritDoc}
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void hide() {
        mActivity.getWindow().getInsetsController().hide(WindowInsets.Type.systemBars());
        mActivity.getWindow().getInsetsController().hide(WindowInsets.Type.navigationBars());
        mActivity.getWindow().getInsetsController().hide(WindowInsets.Type.statusBars());
        mOnVisibilityChangeListener.onVisibilityChange(false);
        mVisible = false;
    }

    /**
     * {@inheritDoc}
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void show() {
        mActivity.getWindow().getInsetsController().show(WindowInsets.Type.systemBars());
        mActivity.getWindow().getInsetsController().show(WindowInsets.Type.navigationBars());
        mActivity.getWindow().getInsetsController().show(WindowInsets.Type.statusBars());
        mOnVisibilityChangeListener.onVisibilityChange(true);
        mVisible = true;
    }
}
