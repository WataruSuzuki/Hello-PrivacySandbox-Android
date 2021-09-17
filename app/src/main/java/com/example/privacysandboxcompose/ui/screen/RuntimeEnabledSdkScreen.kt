package com.example.privacysandboxcompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.privacysandboxcompose.domain.RuntimeEnabledSdkUseCase

@Composable
fun RuntimeEnabledSdkScreen(useCase: RuntimeEnabledSdkUseCase) {
    val composableScope = rememberCoroutineScope()
    var isSdkLoaded = false
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "RE SDK Screen",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Text(
            text = "RE SDK Screen",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Button(onClick = {
            isSdkLoaded = true
//            composableScope.launch {
//                isSdkLoaded = useCase.loadSdk()
//            }
        }) {
            Text("SDKをロードする")
        }
        Button(onClick = { /*TODO*/ }) {
            Text("SDKからWebViewを取得して表示する")
        }
    }
}