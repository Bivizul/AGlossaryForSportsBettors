package com.bivizul.aglossaryforsportsbettors.ui.glossary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bivizul.aglossaryforsportsbettors.R
import com.bivizul.aglossaryforsportsbettors.data.model.GlossaryItem
import com.bivizul.aglossaryforsportsbettors.databinding.ItemGlossaryBinding
import com.l4digital.fastscroll.FastScroller

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GlossaryItem>() {
    override fun areItemsTheSame(oldItem: GlossaryItem, newItem: GlossaryItem) =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: GlossaryItem, newItem: GlossaryItem) =
        oldItem == newItem
}

class GlossaryAdapter :
    ListAdapter<GlossaryItem, GlossaryAdapter.GlossaryViewHolder>(DIFF_CALLBACK),
    FastScroller.SectionIndexer {

    inner class GlossaryViewHolder(val binding: ItemGlossaryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlossaryViewHolder {
        val binding = ItemGlossaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GlossaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GlossaryViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            itemTitle.text = item.name
            itemSubtitle.text = item.description
            itemSubtitle.visibility = if (item.check) View.VISIBLE else View.GONE
            val arrow = if (item.check) R.drawable.arrow_drop_up else R.drawable.arrow_drop_down
            imageArrow.setImageResource(arrow)

            root.setOnClickListener {
                item.check = !item.check
                notifyItemChanged(position)
            }
        }
    }

    override fun getSectionText(position: Int): CharSequence {
        return getItem(position).name.first().toString()
    }
}