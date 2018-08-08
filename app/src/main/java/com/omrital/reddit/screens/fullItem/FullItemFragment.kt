package com.omrital.reddit.screens.fullItem

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.omrital.reddit.R
import com.omrital.reddit.core.BaseFragment
import com.omrital.reddit.core.CustomViewModelFactory
import com.omrital.reddit.dagger.AppComponent
import kotlinx.android.synthetic.main.fragment_full_item.*
import javax.inject.Inject
import android.view.WindowManager

class FullItemFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var viewModel: FullItemViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_full_item, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FullItemViewModel::class.java)
        return view
    }

    override fun onStart() {
        super.onStart()
        if(dialog != null) {
            dialog.window.setWindowAnimations(R.style.dialog_slide_animation)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity, android.R.style.Theme_Holo_Light)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        return dialog
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
        progressBar.indeterminateDrawable.setColorFilter(resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
        webView.webViewClient = ProgressWebViewListener(progressBar)
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
    }

    private fun setupToolbar() {
        backButton.setOnClickListener {
            dismiss()
        }
        starButton.setOnClickListener {
            viewModel.onStarClick()
        }
    }

    private fun bindViewModel() {
        viewModel.title.observe(this, Observer {
            it.let {
                title.text = it
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

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}

class ProgressWebViewListener constructor(val progressBar: ProgressBar?) : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        progressBar?.visibility = View.GONE
    }
}