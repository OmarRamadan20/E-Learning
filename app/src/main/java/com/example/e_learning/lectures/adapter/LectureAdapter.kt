package com.example.e_learning.lectures.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.lectures.LectureItem
import com.example.e_learning.databinding.ItemLectureBinding

class LectureAdapter(private val items: List<LectureItem?>?, private val onItemClick: ((LectureItem) -> Unit?)? =null) :
    RecyclerView.Adapter<LectureAdapter.LectureViewHolder>() {

    inner class LectureViewHolder(val binding: ItemLectureBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val binding = ItemLectureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LectureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val item = items?.get(position)
        val binding = holder.binding

        binding.textTitle.text = item?.title
        binding.textDesc.text = item?.description
        binding.textDuration.text = item?.duration.toString()
        binding.textPrice.text = "$" + item?.price.toString()

        // Show/hide best seller badge
//        binding.badgeBestSeller.visibility = if (item?.== true) View.VISIBLE else View.GONE

        // Load image
        Glide.with(binding.imageView.context)
            .load(item?.logo)
            .into(binding.imageView)

        binding.btnGoToCourse.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "Opening ${item?.title}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = items?.size?:0
}
