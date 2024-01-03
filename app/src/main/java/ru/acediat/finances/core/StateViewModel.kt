package ru.acediat.finances.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class StateViewModel<S: ViewState>: ViewModel() {

    protected abstract val _state: MutableStateFlow<S>

}