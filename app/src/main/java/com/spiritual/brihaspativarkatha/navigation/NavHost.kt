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
import com.spiritual.brihaspativarkatha.data.analytics.InAppReviewManager
import com.spiritual.brihaspativarkatha.screen.AartiDetailScreen
import com.spiritual.brihaspativarkatha.screen.AartiScreen
import com.spiritual.brihaspativarkatha.screen.AboutUsScreen
import com.spiritual.brihaspativarkatha.screen.DailyAartiScreen
import com.spiritual.brihaspativarkatha.screen.HomeScreen
import com.spiritual.brihaspativarkatha.screen.KathaMahattvaVidhiScreen
import com.spiritual.brihaspativarkatha.screen.KathaScreen
import com.spiritual.brihaspativarkatha.screen.KathaVidhiScreen
import com.spiritual.brihaspativarkatha.screen.PanchangScreen
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
            SplashScreen(navController = navController, appUpdateManager)
        }
        composable(route = "Home") {
            val reviewManager = remember {
                InAppReviewManager(context)
            }
            val showReview = navController
                .currentBackStackEntry
                ?.savedStateHandle
                ?.get<Boolean>("show_review") ?: false

            LaunchedEffect(showReview) {

                if (showReview) {

                    reviewManager.launchReviewFlow(activity)

                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.remove<Boolean>("show_review")
                }
            }

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

                    "🪔 बृहस्पति देव की आरती" ->
                        if (AdManager.isAdReady()) {
                            AdManager.showAd {
                                navController.navigate("बृहस्पति देव की आरती")
                            }
                        } else {
                            navController.navigate("बृहस्पति देव की आरती")
                        }


                    "🌸 ओम जय जगदीश हरे आरती" ->
                        if (AdManager.isAdReady()) {
                            AdManager.showAd {
                                navController.navigate("ओम जय जगदीश हरे आरती")
                            }
                        } else {
                            navController.navigate("ओम जय जगदीश हरे आरती")
                        }


                    "💰 लक्ष्मी जी की आरती" ->
                        if (AdManager.isAdReady()) {
                            AdManager.showAd {
                                navController.navigate("लक्ष्मी जी की आरती")
                            }
                        } else {
                            navController.navigate("लक्ष्मी जी की आरती")
                        }

                    "🛕 दैनिक पूजा / आरती" -> {
                        if (AdManager.isAdReady()) {
                            AdManager.showAd {
                                navController.navigate("दैनिक पूजा / आरती")
                            }
                        } else {
                            navController.navigate("दैनिक पूजा / आरती")
                        }

                    }

                    "🗓️ पंचांग" -> navController.navigate("Panchang")
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
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("show_review", true)

                navController.popBackStack()
            })
        }

        composable("बृहस्पति देव की आरती") {
            AartiScreen(
                title = "बृहस्पति देव की आरती",
                aartiText = brihaspatiAartiText,
                onBack = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("show_review", true)

                    navController.popBackStack()
                }
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

        composable(route = "Panchang") {
            PanchangScreen(onBackPress = {
                navController.popBackStack()
            })
        }

        composable(route = "दैनिक पूजा / आरती") {
            DailyAartiScreen(navController, onItemClick = {
                if (AdManager.isAdReady()) {
                    AdManager.showAd {
                        navController.navigate("detail/$it")
                    }
                } else {
                    navController.navigate("detail/$it")
                }
            }, onBack = {
                navController.popBackStack()
            })
        }

        composable("aartiDetail/{resId}") { backStackEntry ->
            val resId = backStackEntry.arguments?.getString("resId")?.toInt() ?: 0
            AartiDetailScreen(navController, resId, {
                navController.popBackStack()
            })
        }


    }
}