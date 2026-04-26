package com.spiritual.brihaspativarkatha.ads

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.spiritual.brihaspativarkatha.BuildConfig

@Composable
fun BannerAdView(modifier: Modifier = Modifier) {

    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->

            val adView = AdView(context)
            Log.d("AD_CHECK", BuildConfig.ADMOB_BANNER_ID)
            adView.adUnitId = BuildConfig.ADMOB_BANNER_ID

            // ✅ Adaptive Banner Size
            val displayMetrics = context.resources.displayMetrics
            val adWidthPixels = displayMetrics.widthPixels
            val density = displayMetrics.density
            val adWidth = (adWidthPixels / density).toInt()

            val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                context,
                adWidth
            )

            adView.setAdSize(adSize)

            adView.loadAd(AdRequest.Builder().build())

            adView
        }
    )
}