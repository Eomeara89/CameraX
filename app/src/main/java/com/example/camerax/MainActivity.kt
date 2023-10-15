@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.camerax

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.camerax.ui.theme.CameraXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasRequiredPermissions()){
            ActivityCompat.requestPermissions(
                    this, CameraX_Permissions, 69
                    )
        }
        setContent {
            CameraXTheme {
                val scaffoldState = rememberBottomSheetScaffoldState()
                val controller = remember {
                    LifecycleCameraController(applicationContext).apply {
                        setEnabledUseCases(
                            CameraController.IMAGE_CAPTURE
                                    or
                            CameraController.IMAGE_ANALYSIS

                        )
                    }

                }
                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetPeekHeight = 0.dp,
                        sheetContent ={

                        }
                    ) {padding ->
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                        ){
                            CameraPreview(controller = controller , modifier = Modifier
                                .fillMaxSize())

                        }
                        IconButton(onClick = {
                            controller.cameraSelector =
                                if(controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA){
                                    CameraSelector.DEFAULT_FRONT_CAMERA
                                }else{
                                    CameraSelector.DEFAULT_BACK_CAMERA
                                }
                        },
                            modifier = Modifier
                                .offset(16.dp, 16.dp)) {
                            Icon(
                                imageVector = Icons.Default.Cameraswitch ,
                                contentDescription ="Switch the camera" )

                        }

                    }

            }
        }
    }
    //A function to check all permissions are granted
    private fun hasRequiredPermissions(): Boolean{
        return CameraX_Permissions.all{
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
    companion object{
        private  val CameraX_Permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    }
}


