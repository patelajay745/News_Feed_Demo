package com.ajay.example.newsappdemo

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ajay.example.newsappdemo.fragments.*

class MyAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return BusinessFragment()
            }
            1 -> {
                return EntertainmentFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return HealthFragment()
            }
            3 -> {
                // val movieFragment = MovieFragment()
                return ScienceFragment()
            }
            4 -> {
                // val movieFragment = MovieFragment()
                return SportsFragment()
            }
            5 -> {
                // val movieFragment = MovieFragment()
                return TechnologyFragment()
            }
            else-> return BusinessFragment()

        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}