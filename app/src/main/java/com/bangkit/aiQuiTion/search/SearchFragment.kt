package com.bangkit.aiQuiTion.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.aiQuiTion.R
import com.bangkit.aiQuiTion.adapter.PredictionAdapter
import com.bangkit.aiQuiTion.databinding.FragmentSearchBinding
import com.bangkit.aiQuiTion.home.HomeActivity
import com.miguelcatalan.materialsearchview.MaterialSearchView

class SearchFragment : Fragment() {

    private var fragmentSearchBinding: FragmentSearchBinding? = null
    private val binding get() = fragmentSearchBinding!!
    private lateinit var searchView: MaterialSearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        val toolbar: Toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.title = ""

        searchView = (activity as HomeActivity).findViewById(R.id.search_view)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val appBar = activity?.findViewById<View>(R.id.appBarLayout)
        if (appBar != null) {
            appBar.visibility = View.VISIBLE
        }
        searchView.showSearch(true)
        searchView.setQuery("", false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[SearchViewModel::class.java]

        observeSearchQuery(viewModel)

        val adapter = PredictionAdapter()
        adapter.notifyDataSetChanged()

        with(binding) {
            rvList.setHasFixedSize(true)
            rvList.adapter = adapter
        }

        viewModel.getDataList().observe(this, {
            if (it != null) {
                adapter.setData(it)
                binding.titleLocation.visibility = View.VISIBLE
                binding.dividerBody2.visibility = View.VISIBLE
                binding.titleLocation.text = it[0].city
            }
            binding.progressBar.visibility = View.INVISIBLE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)
    }

    private fun observeSearchQuery(viewModel: SearchViewModel) {
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setDataList()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentSearchBinding = null
    }

}