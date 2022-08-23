package com.bivizul.aglossaryforsportsbettors.ui.glossary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bivizul.aglossaryforsportsbettors.data.model.Glossary
import com.bivizul.aglossaryforsportsbettors.data.model.GlossaryItem
import com.bivizul.aglossaryforsportsbettors.databinding.ItemGlossaryBinding

class GlossaryAdapter:RecyclerView.Adapter<GlossaryAdapter.GlossaryViewHolder>() {

    inner class GlossaryViewHolder(val binding: ItemGlossaryBinding) : RecyclerView.ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<GlossaryItem>(){
        override fun areItemsTheSame(oldItem: GlossaryItem, newItem: GlossaryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GlossaryItem, newItem: GlossaryItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlossaryViewHolder {
        val binding = ItemGlossaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GlossaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GlossaryViewHolder, position: Int) {
        val item = differ.currentList[position]
//        holder.itemView.apply {
//
//        }
        with(holder.binding){
            itemTitle.text = item.name
            itemSubtitle.text = item.description
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}