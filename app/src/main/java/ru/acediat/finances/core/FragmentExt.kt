package ru.acediat.finances.core

import androidx.fragment.app.Fragment
import ru.acediat.finances.App

val Fragment.applicationContext
    get() = (requireContext().applicationContext as App)