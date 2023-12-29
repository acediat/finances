package ru.acediat.finances.core.navigation

import androidx.viewbinding.ViewBinding
import ru.acediat.finances.core.BaseFragment

interface Navigator {

    fun <B: ViewBinding> navigate(fragment: BaseFragment<B>)

    fun back()
}