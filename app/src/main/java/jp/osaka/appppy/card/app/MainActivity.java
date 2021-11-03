
package jp.osaka.appppy.card.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import jp.osaka.appppy.card.BaseActivity;
import jp.osaka.appppy.card.BuildConfig;
import jp.osaka.appppy.card.R;
import jp.osaka.appppy.card.view.CardFragment;
import jp.osaka.appppy.card.view.CardFragment.OnCardFragmentInteractionListener;

/**
 * メインアクティビティ
 *
 * @author APPPPY
 */
public class MainActivity extends BaseActivity implements OnCardFragmentInteractionListener
{
    /* (non-Javadoc)
     * @see jp.osaka.appppy.card.BaseActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // テーマ設定
        setTheme(R.style.AppTheme_Home);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                //
            }
        });

        // 画面の生成
        if(BuildConfig.DEBUG) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_admob);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,
                            CardFragment.newInstance(),
                            CardFragment.class.getSimpleName())
                    .commit();
        }
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /* (non-Javadoc)
     * @see jp.osaka.appppy.card.CardFragment.OnCardFragmentInteractionListener#onCardFragmentInteraction(android.net.Uri)
     */
    @Override
    public void onCardFragmentInteraction(String name) {
        switch (name) {
            case "YELLOW": {
                Intent i = new Intent(getApplicationContext(), YellowCardActivity.class);
                startActivity(i);
                break;
            }
            case "RED": {
                Intent i = new Intent(getApplicationContext(), RedCardActivity.class);
                startActivity(i);
                break;
            }
            case "GREEN": {
                Intent i = new Intent(getApplicationContext(), GreenCardActivity.class);
                startActivity(i);
                break;
            }
            case "ZEBRA": {
                Intent i = new Intent(getApplicationContext(), ZebraCardActivity.class);
                startActivity(i);
                break;
            }
            case "ORANGE": {
                Intent i = new Intent(getApplicationContext(), OrangeCardActivity.class);
                startActivity(i);
                break;
            }
        }
    }
}
