package ru.acediat.finances.core

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.acediat.finances.core.navigation.Navigator

abstract class BaseFragment<B: ViewBinding>: Fragment() {

    protected var _binding: B? = null
    protected val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}