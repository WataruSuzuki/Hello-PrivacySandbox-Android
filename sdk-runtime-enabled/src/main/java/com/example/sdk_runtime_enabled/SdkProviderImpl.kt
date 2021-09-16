package com.example.sdk_runtime_enabled

import android.annotation.SuppressLint
import android.app.sdksandbox.SandboxedSdkContext
import android.app.sdksandbox.SandboxedSdkProvider
import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import java.util.concurrent.Executor

/*
 * This class works as an entry point for the sandbox to interact with the SDK.
 *
 * This class should be populated inside the AndroidManifest file.
 */
@SuppressLint("NewApi")
class SdkProviderImpl : SandboxedSdkProvider() {

    @SuppressLint("Override")
    override fun initSdk(
        sandboxedSdkContext: SandboxedSdkContext, params: Bundle,
        executor: Executor, initSdkCallback: InitSdkCallback
    ) {
        executor.execute { initSdkCallback.onInitSdkFinished(Bundle()) }
    }

    @SuppressLint("Override")
    override fun getView(windowContext: Context, bundle: Bundle): View {
        val webView = WebView(windowContext)
        webView.loadUrl("https://www.google.com/")
        return webView
    }

    @SuppressLint("Override")
    override fun onExtraDataReceived(bundle: Bundle) {}
}