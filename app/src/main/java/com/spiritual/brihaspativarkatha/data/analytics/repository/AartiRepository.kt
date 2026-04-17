package com.spiritual.brihaspativarkatha.data.analytics.repository

import com.spiritual.brihaspativarkatha.R
import com.spiritual.brihaspativarkatha.data.analytics.model.AartiModel

class AartiRepository {

    fun getAartiList(): List<AartiModel> {
        return listOf(

            AartiModel(
                "🕉️ सोमवार - शिव जी",
                resId = R.raw.shiv_aarti

            ),

            AartiModel(
                "🚩 मंगलवार - हनुमान जी",
                resId = R.raw.hanuman_chalisa

            ),

            AartiModel(
                "🐘 बुधवार - गणेश जी",
                resId = R.raw.ganesh_aarti
            ),

            AartiModel(
                "🌼 गुरुवार - बृहस्पति देव",
                resId = R.raw.vishnu_aarti

            ),

            AartiModel(
                "💰 शुक्रवार - लक्ष्मी जी",
                resId = R.raw.laxmi_aarti
            ),

            AartiModel(
                "🪔 शनिवार - शनि देव",
                resId = R.raw.shanni_dev
            ),

            AartiModel(
                "🌞 रविवार - सूर्य देव",
                resId = R.raw.surya_dev
            )
        )
    }
}