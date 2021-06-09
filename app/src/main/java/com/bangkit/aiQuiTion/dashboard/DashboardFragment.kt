package com.bangkit.aiQuiTion.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.aiQuiTion.R
import com.bangkit.aiQuiTion.adapter.ListAdapter
import com.bangkit.aiQuiTion.databinding.FragmentDashboardBinding
import com.bangkit.aiQuiTion.prediction.PredictionActivity


class DashboardFragment : Fragment() {

    private var _fragmentDashboardBinding: FragmentDashboardBinding? = null
    private val binding get() = _fragmentDashboardBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showRecyclerList()
    }

    override fun onResume() {
        super.onResume()
        val appBar = activity?.findViewById<View>(R.id.appBarLayout)
        if (appBar != null) {
            appBar.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showRecyclerList() {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DashboardViewModel::class.java]

        val adapter = ListAdapter()
        adapter.notifyDataSetChanged()
        viewModel.setDataList()


        with(binding) {
            rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
            rvList.setHasFixedSize(true)
            rvList.adapter = adapter

            btPrediction.setOnClickListener {
                val intent = Intent(context, PredictionActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.getDataList().observe(this, {
            if (it != null) {
                adapter.setData(it)

                binding.titleLocation.text = it[0].city
                binding.titleDate.text = it[0].date.substring(0,17)
                binding.aqi.text = it[0].AQI.toString()

                binding.valueSO2.text = it[0].SO2.toString()
                binding.valuePM10.text = it[0].PM10.toString()
                binding.valueNO2.text = it[0].NO2.toString()
                binding.valueCO.text = it[0].CO.toString()
                binding.valueO3.text = it[0].O3.toString()

                when (it[0].AQI) {
                    in 1.0..50.0 -> {
                        binding.condition.text = "Sehat"
                        binding.img.setImageResource(R.drawable.sehat)
                        setVisibility(true)
                    }
                    in 51.0..100.0 -> {
                        binding.condition.text = "Normal"
                        binding.img.setImageResource(R.drawable.normal)
                        setVisibility(true)
                    }
                    in 101.0..199.0 -> {
                        binding.condition.text = "Tidak Sehat"
                        binding.img.setImageResource(R.drawable.bahaya)
                        setVisibility(true)
                    }
                    else -> {
                        binding.condition.text = "Berbahaya"
                        binding.img.setImageResource(R.drawable.sangat_berbahaya)
                        setVisibility(true)
                    }
                }
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun setVisibility(state: Boolean) {
        binding.main.visibility = if (state) View.VISIBLE else View.INVISIBLE

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentDashboardBinding = null
    }

}