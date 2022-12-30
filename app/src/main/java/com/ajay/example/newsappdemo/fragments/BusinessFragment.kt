package com.ajay.example.newsappdemo.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajay.example.newsappdemo.WebActivity
import com.ajay.example.newsappdemo.adapter.BusinessAdapter
import com.ajay.example.newsappdemo.adapter.RecyclerItemClickListener
import com.ajay.example.newsappdemo.databinding.FragmentBusinessBinding
import com.ajay.example.newsappdemo.factory.BusinessViewModelFactory
import com.ajay.example.newsappdemo.repository.NewsRepository
import com.ajay.example.newsappdemo.retro.RetrofitService
import com.ajay.example.newsappdemo.viewmodels.BusinessViewModel

class BusinessFragment : Fragment(), RecyclerItemClickListener.OnRecyclerClickListner {


    lateinit var viewModel: BusinessViewModel

    private val adapter = BusinessAdapter()
    private lateinit var binding: FragmentBusinessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBusinessBinding.inflate(inflater, container, false)

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
            BusinessViewModelFactory(newsRepository)
        ).get(BusinessViewModel::class.java)

        viewModel.getBusinessNews()


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
            var intent=Intent(activity,WebActivity::class.java)
            intent.putExtra("url",article.url)
        intent.putExtra("title",article.title)
            startActivity(intent)
    }



}