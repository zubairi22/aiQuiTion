package com.bangkit.aiQuiTion.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bangkit.aiQuiTion.R
import com.bangkit.aiQuiTion.dashboard.DashboardFragment
import com.bangkit.aiQuiTion.databinding.ActivityHomeBinding
import com.bangkit.aiQuiTion.search.SearchFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarLayout.visibility = View.INVISIBLE

        navigationChange(DashboardFragment())

        binding.bottomNavigationContainer.setNavigationChangeListener { _, position ->
            when (position) {
                0 -> navigationChange(DashboardFragment())
                1 -> navigationChange(SearchFragment())
            }
        }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

}