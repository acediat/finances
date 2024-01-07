package ru.acediat.finances.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.acediat.finances.R
import ru.acediat.finances.databinding.ItemCashAccountBinding
import ru.acediat.finances.ui.entities.CashAccount

class CashAccountAdapter(
    private val selectedItemPosition: Int = 0,
    private val onAccountClick: (CashAccount, Int) -> Unit,
) : RecyclerView.Adapter<CashAccountAdapter.CashAccountViewHolder>() {

    private val items = mutableListOf<CashAccount>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<CashAccount>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CashAccountViewHolder(
        ItemCashAccountBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CashAccountViewHolder, position: Int) = holder.bind(items[position], position)

    inner class CashAccountViewHolder(
        private val binding: ItemCashAccountBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cashAccount: CashAccount, position: Int) = with(binding) {
            accountNameText.text = cashAccount.name
            accountSummaryText.text = String.format("%.2f", cashAccount.summary)
            accountLayout.setOnClickListener {
                accountLayout.background = AppCompatResources
                    .getDrawable(root.context, R.color.light_gray)
                onAccountClick(cashAccount, position)
            }
        }
    }
}