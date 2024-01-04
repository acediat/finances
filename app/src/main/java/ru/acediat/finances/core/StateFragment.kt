package ru.acediat.finances.core

abstract class StateFragment<S: ViewState> : BaseFragment() {

    protected abstract fun onStateChanged(newState: S)
}