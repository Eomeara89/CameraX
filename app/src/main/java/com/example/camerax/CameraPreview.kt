package com.example.camerax

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import java.lang.reflect.Modifier

@Composable
fun CameraPreview(
    controller:LifecycleCameraController,
    modifier: androidx.compose.ui.Modifier
){
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(factory = {
        PreviewView(it).apply {
            this.controller = controller
            controller.bindToLifecycle(lifecycleOwner)
        }
    }
    ,
        modifier = modifier
    )

}