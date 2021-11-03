
package jp.osaka.appppy.card;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 基本アクティビティ
 *
 * @author APPPPY
 *
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * @serial モバイル広告のアクション定義
     */
    static private final String ACTION_ADMOB = "jp.osaka.appppy.action.ADMOB";

    /**
     * @serial ブロードキャストレシーバ
     */
    private CustomBroadcastReceiver mReceiver = null;

    /**
     * @serial インテントフィルタ
     */
    private IntentFilter mIntentFilter = null;

    /**
     * @serial ハンドラ
     */
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /**
     * @serial タスク
     */
    private final Runnable mTask = new Runnable() {
        @Override
        public void run() {
            View AdMob = findViewById(R.id.AdMob);
            if (AdMob != null) {
                AdMob.setVisibility(View.INVISIBLE);
            }
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReceiver = new CustomBroadcastReceiver();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(ACTION_ADMOB);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
        View AdMob = findViewById(R.id.AdMob);
        if (AdMob != null) {
            AdMob.setVisibility(View.VISIBLE);
        }
        mHandler.postDelayed(mTask, 30000);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
        View AdMob = findViewById(R.id.AdMob);
        if (AdMob != null) {
            AdMob.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * カスタムブロードキャストレシーバ
     *
     * @note    コマンド例
     *          adb shell am broadcast -a jp.osaka.appppy.action.ADMOB --ez VISIBLE false
     */
    public class CustomBroadcastReceiver extends BroadcastReceiver
    {
        /**
         * {@inheritDoc}
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_ADMOB)) {
                View AdMob = findViewById(R.id.AdMob);
                if (AdMob != null) {
                    Bundle bundle = intent.getExtras();
                    if(bundle.getBoolean("VISIBLE")) {
                        AdMob.setVisibility(View.VISIBLE);
                    } else {
                        AdMob.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }
}
