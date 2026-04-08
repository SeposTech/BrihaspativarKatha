package com.spiritual.brihaspativarkatha.data.analytics

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.spiritual.brihaspativarkatha.BuildConfig

@SuppressLint("StaticFieldLeak")
object AdManager {

    private var interstitialAd: InterstitialAd? = null
    private var activity: Activity? = null

    fun init(activity: Activity) {
        this.activity = activity
    }

    fun loadAd() {
        val adRequest = AdRequest.Builder().build()

        activity?.let {
            InterstitialAd.load(
                it,
                BuildConfig.ADMOB_INTERSTITIAL_ID,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(ad: InterstitialAd) {
                        Log.d("AD_TEST", "Ad Loaded Successfully")
                        interstitialAd = ad
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        Log.e("AD_TEST", "Ad Failed: ${error.message}")
                        interstitialAd = null
                    }
                }
            )
        }
    }

    fun showAd(onAdClosed: () -> Unit) {
        if (interstitialAd != null && activity != null) {

            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    loadAd()
                    onAdClosed()
                }
            }

            interstitialAd?.show(activity!!)
        } else {
            Log.e("AD_TEST", "Ad NULL ❌")
            onAdClosed()
        }
    }

    fun isAdReady(): Boolean {
        return interstitialAd != null
    }
}