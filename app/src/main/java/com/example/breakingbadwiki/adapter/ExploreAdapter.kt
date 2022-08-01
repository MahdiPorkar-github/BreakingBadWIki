package com.example.breakingbadwiki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingbadwiki.data.ItemPost
import com.example.breakingbadwiki.databinding.ItemExploreBinding

class ExploreAdapter(private val data: ArrayList<ItemPost>) :
    RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {

    lateinit var binding: ItemExploreBinding

    inner class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(itemPost:ItemPost) {

            Glide.with(itemView.context).load(itemPost.imageUrl).into(binding.imgExploreMain)
            binding.txtExploreTitle.text = itemPost.txtTitle
            binding.txtExploreSubtitle.text = itemPost.txtSubtitle
            binding.txtExploreDetail.text = itemPost.txtDetail

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {

        binding = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploreViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}