package com.bangkit.aiQuiTion.prediction

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bangkit.aiQuiTion.R
import com.bangkit.aiQuiTion.adapter.PredictionAdapter
import com.bangkit.aiQuiTion.databinding.ActivityPredictionBinding

class PredictionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPredictionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prediction)

        binding = ActivityPredictionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[PredictionViewModel::class.java]

        val adapter = PredictionAdapter()
        adapter.notifyDataSetChanged()
        viewModel.setDataList()

        with(binding) {
            rvList.setHasFixedSize(true)
            rvList.adapter = adapter
        }

        viewModel.getDataList().observe(this, {
            if (it != null) {
                adapter.setData(it)
                binding.titleLocation.text = it[0].city
            }
            binding.progressBar.visibility = View.INVISIBLE
        })
    }
}