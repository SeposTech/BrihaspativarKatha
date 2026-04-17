package com.spiritual.brihaspativarkatha.viewmodel

import androidx.lifecycle.ViewModel
import com.spiritual.brihaspativarkatha.data.analytics.model.AartiModel
import com.spiritual.brihaspativarkatha.data.analytics.repository.AartiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AartiViewModel : ViewModel() {

    private val repository = AartiRepository()

    private val _aartiList = MutableStateFlow<List<AartiModel>>(emptyList())
    val aartiList: StateFlow<List<AartiModel>> = _aartiList

    init {
        loadAarti()
    }

    private fun loadAarti() {
        _aartiList.value = repository.getAartiList()
    }
}