
package jp.osaka.appppy.card.admob;

import com.google.android.gms.ads.AdSize;

/**
 * モバイル広告フラグメントの実装
 *
 * @author APPPPY
 */
public class AdMobFragmentImpl extends AdMobFragment {

    /**
     * {@inheritDoc}
     */
    @Override
    protected AdSize getAdSize() {
        return AdSize.BANNER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getUnitId() {
        return "IDを入力してください";
    }
}