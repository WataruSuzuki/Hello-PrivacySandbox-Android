package com.example.privacysandboxcompose.domain

import android.app.sdksandbox.SdkSandboxManager
import android.content.Context
import android.os.Bundle
import android.view.SurfaceControlViewHost.SurfacePackage
import android.view.SurfaceView
import androidx.annotation.RequiresApi
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RuntimeEnabledSdkUseCase(context: Context) {
    var isSdkLoaded = false
        private set

    /**
     * An instance of SdkSandboxManager which contains APIs to communicate with the sandbox.
     */
    val sdkSandboxManager: SdkSandboxManager = context.getSystemService(SdkSandboxManager::class.java)

    /**
     * The SurfaceView which will be used by the client app to show the SurfacePackage
     * going to be rendered by the sandbox.
     */
    private lateinit var clientSurfaceView: SurfaceView

    private var loaderContinuation: Continuation<Boolean>? = null
    suspend fun loadSdk() = suspendCoroutine<Boolean> { continuation ->
        loaderContinuation = continuation
        sdkSandboxManager.loadSdk(SDK_NAME, Bundle(), { it.run() }, remoteCallback)
    }

    private var requestSurfaceContinuation: Continuation<SurfacePackage?>? = null
    suspend fun requestSurfacePackage(displayId: Int, surfaceView: SurfaceView, bundle: Bundle) = suspendCoroutine<SurfacePackage?> {
        requestSurfaceContinuation = it
        clientSurfaceView = surfaceView
        sdkSandboxManager.requestSurfacePackage(SDK_NAME, displayId, surfaceView.width, surfaceView.height, bundle)
    }

    @RequiresApi(api = 33)
    private val remoteCallback = object : SdkSandboxManager.RemoteSdkCallback {
        override fun onLoadSdkSuccess(p0: Bundle) {
            resumeLoadSdk(true)
        }

        override fun onLoadSdkFailure(errorCode: Int, errorMessage: String) {
            println("error code: ${errorCode}, message: $errorMessage")
            resumeLoadSdk(false)
        }

        override fun onSurfacePackageReady(
            surfacePackage: SurfacePackage,
            surfacePackageId: Int,
            param: Bundle
        ) {
            requestSurfaceContinuation?.run {
                resume(surfacePackage)
                requestSurfaceContinuation = null
            }
        }

        override fun onSurfacePackageError(errorCode: Int, errorMessage: String) {
            println("error code: ${errorCode}, message: $errorMessage")
        }

        private fun resumeLoadSdk(result: Boolean) {
            isSdkLoaded = result
            loaderContinuation?.run {
                resume(result)
                loaderContinuation = null
            }
        }
    }

    companion object {
        /**
         * Name of the SDK to be loaded.
         */
        private const val SDK_NAME = "com.example.sdk_runtime_enabled"
    }
}