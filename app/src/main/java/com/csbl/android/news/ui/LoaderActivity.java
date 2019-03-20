package com.csbl.android.news.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.csbl.android.news.R;
import com.csbl.android.news.utils.Constants;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import androidx.appcompat.app.AppCompatActivity;

public class LoaderActivity extends AppCompatActivity implements RewardedVideoAdListener {


    private WebView mWebView;
    private String mUrl;
    private ProgressBar mProgressBar;

    private AdView mAdView;

    private InterstitialAd mInterstitialAd;

    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        mProgressBar = findViewById(R.id.progressbarID);
        mProgressBar.setMax(100);

        mWebView = findViewById(R.id.web_view_news);

        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient());

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.d("LOADER", "newProgress: "+newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == mProgressBar.getMax()){
                    mProgressBar.setVisibility(View.GONE);

                }
            }
        });

        if (savedInstanceState != null){
            mUrl = savedInstanceState.getString(Constants.TAG_URL);
        } else{
            if (getIntent().getExtras() != null) {
                mUrl = getIntent().getExtras().getString(Constants.TAG_URL, getString(R.string.url_prathom_alo));
            } else mUrl = getString(R.string.url_prathom_alo);
        }

        mWebView.loadUrl(mUrl);

        MobileAds.initialize(this,
                "ca-app-pub-1148034318341197~3492073598");

        mAdView = findViewById(R.id.banner_ads);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1148034318341197/9129868493");


        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

    }

    private void loadRewardedVideoAd() {

        mRewardedVideoAd.loadAd("ca-app-pub-1148034318341197/9293520027",
                new AdRequest.Builder().build());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(Constants.TAG_URL, mUrl);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();

            mRewardedVideoAd.show();

        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });
    }



    //videoAds

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}