package com.bivizul.aglossaryforsportsbettors.ui.domanaka

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bivizul.aglossaryforsportsbettors.data.model.SetCapel
import com.bivizul.aglossaryforsportsbettors.data.repository.CapelRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class DomanakaViewModel @Inject constructor(
    private val setCapel: SetCapel,
    private val capelRepository: CapelRepository,
) : ViewModel() {

    val getCapel = capelRepository.capel

    init {
        viewModelScope.launch {
            capelRepository.getCapel(setCapel)
        }
    }

}

class DomanakaViewModelFactory @AssistedInject constructor(
    @Assisted("setCapel") private val setCapel: SetCapel,
    private val capelRepository: CapelRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DomanakaViewModel(setCapel, capelRepository) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("setCapel") setCapel: SetCapel): DomanakaViewModelFactory
    }

}