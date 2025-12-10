package com.mobile;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

//import com.google.common.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Camera extends AppCompatActivity {

    private static final String[] REQUIRED_PERMISSIONS = {
            android.Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };

    private ExecutorService cameraExecutor;
    private androidx.camera.view.PreviewView viewFinder;
    private ImageView foto;
    private ImageCapture imageCapture;
    private CameraSelector cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Iniciar objetos
        cameraExecutor = Executors.newSingleThreadExecutor();
        viewFinder = findViewById(R.id.viewFinder);
        foto = findViewById(R.id.foto);

        // Request de permissão
        if (allPermissionsGranted()) {
//            startCamera();
        } else {
            requestPermissions();
        }

    }

//    // Configura a camera
//    private void startCamera() {
//        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
//
//        cameraProviderFuture.addListener(() -> {
//            try {
//                // Used to bind the lifecycle of cameras to the lifecycle owner
//                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
//
//                // Preview
//                Preview preview = new Preview.Builder().build();
//                preview.setSurfaceProvider(viewFinder.getSurfaceProvider());
//
//                // ImageCapture
//                imageCapture = new ImageCapture.Builder().build();
//
//                try {
//                    // Unbind use cases before rebinding
//                    cameraProvider.unbindAll();
//
//                    // Bind use cases to camera
//                    cameraProvider.bindToLifecycle(
//                            this,
//                            cameraSelector,
//                            preview,
//                            imageCapture
//                    );
//                } catch (Exception exc) {
//                    Log.e("Log", "Camera binding failed", exc);
//                }
//
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, ContextCompat.getMainExecutor(this));
//    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS);
    }

    private ActivityResultLauncher<String[]> activityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.RequestMultiplePermissions(),
        permissions -> {
            // Handle Permission granted/rejected
            boolean permissionGranted = true;
            for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
                if (Arrays.asList(REQUIRED_PERMISSIONS).contains(entry.getKey()) && !entry.getValue()) {
                    permissionGranted = false;
                    break;
                }
            }
            if (!permissionGranted) {
                Toast.makeText(getApplicationContext(),"Permissão NEGADA.",Toast.LENGTH_SHORT).show();
            } else {
//                startCamera();
                // ok acessos liberados ;
            }
    });

}