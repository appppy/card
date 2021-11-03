
package jp.osaka.appppy.card.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;

/**
 * An API 11+ implementation of {@link SystemUiHider}. Uses APIs available in
 * Honeycomb and later (specifically {@link View#setSystemUiVisibility(int)}) to
 * show and hide the system UI.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SystemUiHiderHoneycomb extends SystemUiHiderBase {

    /**
     * Constructor not intended to be called by clients. Use
     * {@link SystemUiHider#getInstance} to obtain an instance.
     */
    protected SystemUiHiderHoneycomb(Activity activity, View anchorView, int flags) {
        super(activity, anchorView, flags);
    }

    /** {@inheritDoc} */
    @Override
    public void setup() {
    }

    /** {@inheritDoc} */
    @Override
    public void hide() {
    }

    /** {@inheritDoc} */
    @Override
    public void show() {
    }

    /** {@inheritDoc} */
    @Override
    public boolean isVisible() {
        return true;
    }
}
