package com.ajay.example.newsappdemo

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import com.ajay.example.newsappdemo.databinding.ActivityMainBinding
import com.ajay.example.newsappdemo.repository.NewsRepository
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {


    lateinit var binding:ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initTabLayout()

        binding.apply {
            toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.business -> {
                        binding.viewPager.currentItem=0
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.entertainment -> {
                        binding.viewPager.currentItem=1
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.health -> {
                        binding.viewPager.currentItem=2
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.science -> {
                        binding.viewPager.currentItem=3
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.sports -> {
                        binding.viewPager.currentItem=4
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.technology -> {
                        binding.viewPager.currentItem=5
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                }
                true
            }
        }

    }

    fun initTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Business"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Entertainment"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Health"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Science"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Sports"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Technology"))
        binding.tabLayout.tabGravity=TabLayout.GRAVITY_CENTER
        binding.tabLayout.tabMode=TabLayout.MODE_SCROLLABLE

        val adapter=MyAdapter(this,supportFragmentManager,binding.tabLayout.tabCount)
        binding.viewPager.adapter=adapter

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        binding.tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}