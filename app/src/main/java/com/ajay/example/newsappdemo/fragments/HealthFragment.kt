package com.ajay.example.newsappdemo.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajay.example.newsappdemo.WebActivity
import com.ajay.example.newsappdemo.adapter.HealthAdapter
import com.ajay.example.newsappdemo.adapter.RecyclerItemClickListener
import com.ajay.example.newsappdemo.databinding.FragmentHealthBinding
import com.ajay.example.newsappdemo.factory.BusinessViewModelFactory
import com.ajay.example.newsappdemo.factory.HealthViewModelFactory
import com.ajay.example.newsappdemo.repository.NewsRepository
import com.ajay.example.newsappdemo.retro.RetrofitService
import com.ajay.example.newsappdemo.viewmodels.HealthViewModel

class HealthFragment : Fragment() , RecyclerItemClickListener.OnRecyclerClickListner{

    private lateinit var binding: FragmentHealthBinding
    private val adapter = HealthAdapter()
    lateinit var viewModel: HealthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthBinding.inflate(inflater, container, false)

        val retrofitService = RetrofitService.getInstance()
        val newsRepository = NewsRepository(retrofitService)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                binding.root.context,
                binding.recyclerView,
                this
            )
        )

        viewModel = ViewModelProvider(
            this,
            HealthViewModelFactory(newsRepository)
        ).get(HealthViewModel::class.java)

        viewModel.getHealthNews()


        viewModel.newsList.observe(viewLifecycleOwner) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onItemClick(view: View, position: Int) {
        val article=adapter.getArticles(position)
        //Toast.makeText(activity,article.url,Toast.LENGTH_SHORT).show()
        var intent= Intent(activity, WebActivity::class.java)
        intent.putExtra("url",article.url)
        intent.putExtra("title",article.title)
        startActivity(intent)
    }


}