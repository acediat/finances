package ru.acediat.finances.ui

import androidx.appcompat.widget.PopupMenu
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import ru.acediat.finances.databinding.ItemOperationBinding
import ru.acediat.finances.databinding.ItemOperationDividerBinding
import ru.acediat.finances.ui.entities.DisplayableEntity
import ru.acediat.finances.ui.entities.operations.Operation
import ru.acediat.finances.ui.entities.operations.OperationsDivider

fun dividerDelegateAdapter() = adapterDelegateViewBinding<OperationsDivider, DisplayableEntity, ItemOperationDividerBinding>(
    { layoutInflater, parent ->  ItemOperationDividerBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        binding.dateText.text = item.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }
}

fun operationDelegateAdapter() = adapterDelegateViewBinding<Operation, DisplayableEntity, ItemOperationBinding>(
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