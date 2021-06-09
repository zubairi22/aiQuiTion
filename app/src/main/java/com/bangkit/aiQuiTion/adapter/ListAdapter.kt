package com.bangkit.aiQuiTion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.aiQuiTion.R
import com.bangkit.aiQuiTion.data.remote.model.Aqi
import com.bangkit.aiQuiTion.databinding.ItemCardListBinding


class ListAdapter :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val list = ArrayList<Aqi>()

    fun setData(data: ArrayList<Aqi>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemCardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Aqi) {
            binding.apply {
                day.text = "Rabu"
                when (data.aqiInfo?.category) {
                    "Good" -> {
                        binding.img.setImageResource(R.drawable.normal)
                    }
                    "Normal" -> {
                        binding.condition.text = "Normal"
                        binding.img.setImageResource(R.drawable.normal)
                    }
                    else -> {
                        binding.condition.text = "Berbahaya"
                        binding.img.setImageResource(R.drawable.bahaya)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view =
            ItemCardListBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}