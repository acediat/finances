package ru.acediat.finances.operations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.acediat.finances.core.BaseFragment
import ru.acediat.finances.databinding.FragmentOperationsBinding

class OperationsFragment : BaseFragment<FragmentOperationsBinding>() {

    override fun getBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentOperationsBinding =
        FragmentOperationsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}