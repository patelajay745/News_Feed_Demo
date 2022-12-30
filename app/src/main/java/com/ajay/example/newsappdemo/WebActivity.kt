package com.ajay.example.newsappdemo

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ajay.example.newsappdemo.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = intent.extras!!.getString("title","")
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
       // actionbar.setDisplayHomeAsUpEnabled(true)

        val url:String= intent.extras!!.getString("url","")

        binding.webview.webViewClient = MyWebViewClient(binding.root.context)
        binding.webview.loadUrl(url)


    }

    class MyWebViewClient internal constructor(private val context: Context) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            Toast.makeText(context, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}