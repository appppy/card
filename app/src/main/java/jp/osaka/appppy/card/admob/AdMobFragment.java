
package jp.osaka.appppy.card.admob;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * モバイル広告フラグメント
 *
 * @author APPPPY
 */
public abstract class AdMobFragment extends Fragment {

    /**
     * @serial デバッグ
     */
    private static final boolean DEBUG = false;

    /**
     * @serial タグ
     */
    private static final String TAG = "AdMobFragment";

    /**
     * @serial モバイル広告ビュー
     */
    private AdView mAdView = null;

    /**
     * @serial モバイル広告要求
     */
    private AdRequest mAdRequest = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        if (DEBUG) {
            Log.v(TAG, "onCreateView");
        }
        mAdView = new AdView(requireActivity());
        mAdView.setAdUnitId(getUnitId());
        mAdView.setAdSize(getAdSize());
        mAdRequest = new AdRequest.Builder().build();
        final AdListener listener = getAdListener();
        if (listener != null) {
            mAdView.setAdListener(listener);
        }
        return mAdView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdView.loadAd(mAdRequest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroyView() {
        if (DEBUG) {
            Log.v(TAG, "onDestroyView");
        }
        super.onDestroyView();
        mAdView.destroy();
    }

    /**
     * モバイル広告のサイズ取得
     *
     * @return モバイル広告のサイズ
     */
    protected abstract AdSize getAdSize();

    /**
     * モバイル広告のユニット識別子の取得
     *
     * @return モバイル広告のユニット識別子
     */
    protected abstract String getUnitId();

    /**
     * モバイル広告のリスナ取得
     *
     * @return モバイル広告のリスナ
     */
    protected AdListener getAdListener() {
        return null;
    }

}
