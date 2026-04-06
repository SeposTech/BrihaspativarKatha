package com.spiritual.brihaspativarkatha.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.play.core.appupdate.AppUpdateManager
import com.spiritual.brihaspativarkatha.data.analytics.AdManager
import com.spiritual.brihaspativarkatha.screen.AartiScreen
import com.spiritual.brihaspativarkatha.screen.AboutUsScreen
import com.spiritual.brihaspativarkatha.screen.HomeScreen
import com.spiritual.brihaspativarkatha.screen.KathaMahattvaVidhiScreen
import com.spiritual.brihaspativarkatha.screen.KathaScreen
import com.spiritual.brihaspativarkatha.screen.KathaVidhiScreen
import com.spiritual.brihaspativarkatha.screen.SplashScreen
import com.spiritual.brihaspativarkatha.screen.brihaspatiAartiText
import com.spiritual.brihaspativarkatha.screen.jagdishAartiText
import com.spiritual.brihaspativarkatha.screen.lakshmiAartiText

@Composable
fun Navigation(appUpdateManager: AppUpdateManager) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val activity = context as Activity

    LaunchedEffect(Unit) {
        AdManager.init(activity)
        AdManager.loadAd()
    }

    NavHost(navController = navController, startDestination = "Splash") {
        composable(route = "Splash") {
            SplashScreen(navController = navController,appUpdateManager)
        }
        composable(route = "Home") {
            HomeScreen(onTopicClick = { topic ->
                when (topic) {
                    "🌼 बृहस्पतिवार व्रत का महत्व" -> navController.navigate("mahattva")
                    "🙏 व्रत विधि" -> navController.navigate("vidhi")
                    "📖 व्रत कथा" -> {
                        if (AdManager.isAdReady()) {
                            AdManager.showAd {
                                navController.navigate("katha")
                            }
                        } else {
                            navController.navigate("katha")
                        }
                    }
                    "🪔 बृहस्पति देव की आरती" -> navController.navigate("बृहस्पति देव की आरती")
                    "🌸 ओम जय जगदीश हरे आरती" -> navController.navigate("ओम जय जगदीश हरे आरती")
                    "💰 लक्ष्मी जी की आरती" -> navController.navigate("लक्ष्मी जी की आरती")
                }
            }, navController = navController)
        }
        composable(route = "mahattva") {
            KathaMahattvaVidhiScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable(route = "vidhi") {
            KathaVidhiScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable(route = "katha") {
            KathaScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable("बृहस्पति देव की आरती") {
            AartiScreen(
                title = "बृहस्पति देव की आरती",
                aartiText = brihaspatiAartiText,
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = "ओम जय जगदीश हरे आरती") {
            AartiScreen(
                title = "ओम जय जगदीश हरे आरती",
                aartiText = jagdishAartiText,
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = "लक्ष्मी जी की आरती") {
            AartiScreen(
                title = "लक्ष्मी जी की आरती",
                aartiText = lakshmiAartiText,
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = "About") {
            AboutUsScreen(onBack = {
                navController.popBackStack()
            })
        }
    }
}