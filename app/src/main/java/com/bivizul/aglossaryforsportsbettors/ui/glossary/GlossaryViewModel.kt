package com.bivizul.aglossaryforsportsbettors.ui.glossary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bivizul.aglossaryforsportsbettors.data.repository.GlossaryRepository
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class GlossaryViewModel @Inject constructor(
    private val glossaryRepository: GlossaryRepository,
) : ViewModel() {

    val glossary = glossaryRepository.glossary

    init {
        viewModelScope.launch {
            glossaryRepository.getGlossary()
        }
    }

}

class GlossaryViewModelFactory @AssistedInject constructor(
    private val glossaryRepository: GlossaryRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GlossaryViewModel(glossaryRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(): GlossaryViewModelFactory
    }

}