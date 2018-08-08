package com.omrital.reddit.screens.fullItem

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.omrital.reddit.R
import com.omrital.reddit.core.BaseFragment
import com.omrital.reddit.core.CustomViewModelFactory
import com.omrital.reddit.dagger.AppComponent
import kotlinx.android.synthetic.main.fragment_full_item.*
import javax.inject.Inject

class FullItemFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var viewModel: FullItemViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_full_item, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FullItemViewModel::class.java)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(dialog != null) {
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen)
        }
    }

    override fun onStart() {
        super.onStart()
        if(dialog != null) {
            dialog.window.setWindowAnimations(R.style.dialog_slide_animation)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        bindViewModel()
    }

    private fun setupViews() {
        setupToolbar()
        initWebView()
    }

    private fun initWebView() {
        webView.webViewClient = ProgressWebViewListener(progressBar)
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            activity!!.onBackPressed()
        }
        starButton.setOnClickListener {
            viewModel.onStarClick()
        }
    }

    private fun bindViewModel() {
        viewModel.title.observe(this, Observer {
            it.let {
                toolbar.title = it
            }
        })
        viewModel.starred.observe(this, Observer {
            if(it != null) {
                starButton.setImageResource(if(it) R.drawable.ic_heart else R.drawable.ic_heart_empty)
            }
        })
        viewModel.Url.observe(this, Observer {
            if(it != null) {
                webView.loadUrl(it)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onExit()
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}

class ProgressWebViewListener constructor(val progressBar: ProgressBar?) : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        progressBar?.visibility = View.GONE
    }
}