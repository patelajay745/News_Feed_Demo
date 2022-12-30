package com.ajay.example.newsappdemo.adapter

import android.content.Context
import android.graphics.Color
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ajay.example.newsappdemo.R
import com.ajay.example.newsappdemo.databinding.NewsItemBinding
import com.ajay.example.newsappdemo.models.Article
import com.ajay.example.newsappdemo.models.News
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class BusinessAdapter : RecyclerView.Adapter<MyNewsViewHolder>() {

    private val newsList = ArrayList<Article>()

    fun setList(news: News) {
        newsList.clear()

        newsList.addAll(news.articles)
        //newsList.addAll(news)
        Log.i("mydata", newsList.size.toString())


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyNewsViewHolder, position: Int) {
        holder.bind(newsList[position] )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun getArticles(position: Int): Article {
        return newsList[position]
    }


}

 class MyNewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: Article) {
        binding.title.text = news.title
        binding.author.text = news.author
        binding.description.text = news.description
        binding.publishDate.text = news.publishedAt
        binding.title.setBackgroundColor(Color.parseColor("#F4F4F4"))
        binding.title.setTextColor(Color.parseColor("#000000"))

        /*
        Picasso.get()
            .load(news.urlToImage)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.imageView)
        */

        Glide.with(binding.root)
            .load(news.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(binding.imageView);

    }

    fun timeAgo(publishedAt: String): String {

        val inputFormat: SimpleDateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") //2022-12-29T14:31:00Z
        val date: Date = inputFormat.parse(publishedAt)
        val niceDateStr: String = DateUtils.getRelativeTimeSpanString(
            date.getTime(),
            Calendar.getInstance().getTimeInMillis(),
            DateUtils.MINUTE_IN_MILLIS
        )
            .toString();

        return niceDateStr
    }


}
