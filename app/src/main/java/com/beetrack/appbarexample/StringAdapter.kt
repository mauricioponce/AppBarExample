package com.beetrack.appbarexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class ItemDiffCallback: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem
}

data class Item(val text: String)

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textView: TextView

    init {
        // Define click listener for the ViewHolder's View.
        textView = itemView.findViewById(R.id.textView)
    }
}

class StringAdapter: ListAdapter<Item, ItemViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)
        return ItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.textView.text = item.text
    }

}