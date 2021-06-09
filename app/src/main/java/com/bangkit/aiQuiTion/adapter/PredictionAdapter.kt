package com.bangkit.aiQuiTion.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.aiQuiTion.R
import com.bangkit.aiQuiTion.data.remote.model.Aqi
import com.bangkit.aiQuiTion.databinding.ItemPredictionBinding

class PredictionAdapter :
    RecyclerView.Adapter<PredictionAdapter.ListViewHolder>() {

    private val list = ArrayList<Aqi>()

    fun setData(data: ArrayList<Aqi>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemPredictionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Aqi) {
            binding.apply {
                date.text = "8 Juni"
                day.text = "Rabu" + " ,"
                condition.text = data.aqiInfo?.category
                if (data.aqiInfo?.category == "Good") {
                    binding.img.setImageResource(R.drawable.normal)
                } else if (data.aqiInfo?.category == "Bad") {
                    binding.condition.text = "Berbahaya"
                    binding.img.setImageResource(R.drawable.bahaya)
                } else {
                    binding.condition.text = "Normal"
                    binding.img.setImageResource(R.drawable.normal)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view =
            ItemPredictionBinding.inflate(
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