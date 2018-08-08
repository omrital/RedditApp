package com.omrital.reddit.network

import android.util.Log
import com.google.gson.Gson
import com.omrital.reddit.network.model.RedditRequestStructure
import com.omrital.reddit.network.model.RedditResponseStructure
import okhttp3.*
import org.jdeferred2.Promise
import org.jdeferred2.impl.DeferredObject
import java.io.IOException
import javax.inject.Inject

typealias ErrorMessage = String
typealias Progress = String

interface HttpClientType {
    fun doGetRequest(request: RedditRequestStructure): Promise<RedditResponseStructure, ErrorMessage, Progress>
}

class HttpClient @Inject constructor(val client: OkHttpClient): HttpClientType {

    private val errorResponseNull = "response is null"
    private val errorRequestFail = "request failed"
    private  val tag = javaClass.simpleName

    override fun doGetRequest(request: RedditRequestStructure): Promise<RedditResponseStructure, ErrorMessage, Progress> {

        val urlBuilder = HttpUrl.parse(request.URL)?.newBuilder()
        addRequestParams(urlBuilder, request)

        val url = urlBuilder?.build().toString()
        val getRequest = Request.Builder().url(url).build()

        return doRequest(getRequest)
    }

    private fun addRequestParams(urlBuilder: HttpUrl.Builder?, request: RedditRequestStructure) {
        if(request.params.isNotEmpty()) {
            for(key in request.params.keys) {
                val value = request.params[key]
                urlBuilder?.addQueryParameter(key, value)
            }
        }
    }

    private fun doRequest(request: Request): Promise<RedditResponseStructure, ErrorMessage, Progress> {
        val deferredObject = DeferredObject<RedditResponseStructure, ErrorMessage, Progress>()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                val error = "$errorRequestFail with exception " + e?.message
                deferredObject.reject(error)
                printError(call, error)
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response == null) {
                    deferredObject.reject(errorResponseNull)
                    printError(call, errorResponseNull)
                }
                if (response!!.isSuccessful) {
                    val gson = Gson()
                    val redditResponse = gson.fromJson(response.body()?.string(), RedditResponseStructure::class.java)
                    deferredObject.resolve(redditResponse)

                } else {
                    deferredObject.reject(errorRequestFail)
                    printError(call, errorRequestFail)
                }
            }
        })
        return deferredObject.promise()
    }

    private fun printError(call: Call?, errorMessage: String) {
        var requestName = "request name unknown"
        if(call != null) {
            requestName = call.request().url().url().path
        }
        Log.e(tag, "$requestName fail with error:  $errorMessage")
    }
}