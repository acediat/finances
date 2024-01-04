package ru.acediat.finances.operations

import androidx.appcompat.widget.PopupMenu
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.acediat.finances.databinding.ItemOperationBinding
import ru.acediat.finances.databinding.ItemOperationDividerBinding
import ru.acediat.finances.entities.operations.Operation
import ru.acediat.finances.entities.operations.OperationsDivider
import ru.acediat.finances.entities.operations.OperationsListEntity

fun dividerDelegateAdapter() = adapterDelegateViewBinding<OperationsDivider, OperationsListEntity, ItemOperationDividerBinding>(
    { layoutInflater, parent ->  ItemOperationDividerBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        binding.dateText.text = item.date.toString()
    }
}

fun operationDelegateAdapter() = adapterDelegateViewBinding<Operation, OperationsListEntity, ItemOperationBinding>(
    { layoutInflater, parent ->  ItemOperationBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        val moreMenu = PopupMenu(context, binding.moreButton)
        moreMenu.menu.apply {
            add("lol")
            add("kek")
        }
        binding.moreButton.setOnClickListener {
            moreMenu.show()
        }
        binding.valueText.text = item.value.toString()
    }
}