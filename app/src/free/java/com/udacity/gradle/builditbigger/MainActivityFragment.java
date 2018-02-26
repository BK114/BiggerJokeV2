package com.udacity.gradle.builditbigger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    PublisherInterstitialAd mInterstitialAd = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        //set up spinner and set to GONE
        final ProgressBar spinner = root.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        //Set up interstitial ad request with interstitial ID from
        //https://developers.google.com/admob/android/test-ads
        mInterstitialAd = new PublisherInterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        //set a listenr for when ad is closed or does not load
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                //process the joke Request
                spinner.setVisibility(View.VISIBLE);
                getJokes();

                //get a new Ad
                requestNewAd();
            }

            @Override
            public void onAdFailedToLoad(int errorLoading) {
                super.onAdFailedToLoad(errorLoading);
                Log.i(LOG_TAG, "onAdFailedToLoad: ad Failed to load...");
                //get a new Ad
                requestNewAd();

            }

            @Override
            public void onAdLoaded() {
                Log.i(LOG_TAG, "onAdLoaded: New ad ready!");
                super.onAdLoaded();
                //make spinner visibilty GONE
                spinner.setVisibility(View.GONE);
            }
        });

        //request ad
        requestNewAd();
        //Create an ad request. Check logcat output for the hashed device ID to
        //get test ads on a physical device. e.g.
        //"Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        final AdView mAdView = root.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        final Button jokeButton = root.findViewById(R.id.btn_joke);
        jokeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    Log.i(LOG_TAG, "onClick: Ad is ready");
                    mInterstitialAd.show();
                } else {
                    Log.i(LOG_TAG, "onClick: Ad not ready");
                    getJokes();

                }
            }

        });
        spinner.setVisibility(View.GONE);


        return root;
    }

    public void getJokes() {
        //initiate the EndpointsAsyncTask here and get the Activity tied to fragment
        new EndpointsAsyncTask().execute(getActivity());
    }

    private void requestNewAd() {
        PublisherAdRequest request = new PublisherAdRequest.Builder()
                //I/Ads: Use AdRequest.Builder.addTestDevice("33BE2250B43518CCDA7DE426D04EE231")
                //to get test ads on this device."
                //found at https://developers.google.com/admob/android/test-ads
                .addTestDevice("33BE2250B43518CCDA7DE426D04EE231")
                .build();
        mInterstitialAd.loadAd(request);

    }
}
