package com.example.mobass1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.Arrays;

public class heartactivity extends AppCompatActivity {


    TextureView textureView;
    String cameraID;
    CameraDevice cameraDevice;
    CameraCaptureSession cameraCaptureSessions;
    CaptureRequest.Builder capBuild;
    Size Dim;
    int CRA;
    int LRA;
    int FRA;
    final int Camera_Permission = 1;
    HandlerThread bgThread;
    Handler BgHandle;
    String TAG = "HeartActivity";
    int hrtratebpm;
    long [] TimeArray;
    int Captures = 0;
    int Beats = 0;
    TextView findisp;
    Button back;
    DatabaseHelper mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartactivity);

        //mdata = GlobalClass.get().getmData();

        textureView =  findViewById(R.id.textureView2);
        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);
        TimeArray = new long [15];
        findisp = (TextView)findViewById(R.id.display);
        back = (Button) findViewById(R.id.backbut);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(heartactivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            Log.d(TAG, "onSurfaceTextureUpdated");
            Bitmap bmp = textureView.getBitmap();           //created a bitmap to process data
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] pixels = new int[height * width];         //getting pixels from bitmap

            bmp.getPixels(pixels, 0, width, width / 2, height / 2, width / 20, height / 20);
            int sum = 0;
            for (int i = 0; i < height * width; i++) {
                int red = (pixels[i] >> 16) & 0xFF;
                sum = sum + red;
            }
            if (Captures == 20) {                               //Here we build the rolling average
                CRA = sum;
            }
            else if (Captures > 20 && Captures < 49) {
                CRA = (CRA*(Captures-20) + sum)/(Captures-19);
            }
            else if (Captures >= 49) {
                CRA = (CRA*29 + sum)/30;
                if (LRA > CRA && LRA > FRA && Beats < 15) {
                    TimeArray[Beats] = System.currentTimeMillis();
                        Beats++;
                    if (Beats == 15) {
                        calcBPM();
                    }
                }
            }

            Captures++;
            FRA = LRA;
            LRA = CRA;
        }
    };
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            Log.e(TAG, "onOpened");
            cameraDevice = camera;
            createCameraPreview();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            cameraDevice.close();
        }

        @Override
        public void onError(CameraDevice camera, int error) {
            if (cameraDevice != null)
                cameraDevice.close();
            cameraDevice = null;
        }
    };

    // onResume
    protected void startBackgroundThread() {
        bgThread = new HandlerThread("Camera Background");
        bgThread.start();
        BgHandle = new Handler(bgThread.getLooper());

    }
    // onPause
    protected void stopBackgroundThread() {
        bgThread.quitSafely();
        try {
            bgThread.join();
            bgThread = null;
            BgHandle = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void calcBPM() {            //This function is a simple one to calculate Beats per minute
        int med;
        long [] timedist = new long [14];
        for (int i = 0; i < 14; i++) {
            timedist[i] = TimeArray[i+1] - TimeArray[i];
        }
        Arrays.sort(timedist);
        med = (int) timedist[timedist.length/2];
        hrtratebpm= 60000/med;
        findisp.setText("Heart rate is: "+hrtratebpm+"BPM");
        //AddData(hrtratebpm);

    }
    public void AddData(float item){            //This function adds the data to the database for heartbeat
        boolean insertData = mdata.addData1(item);

        if(insertData){
            Toast.makeText(heartactivity.this, "Inserted", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(heartactivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    protected void createCameraPreview() {      //This function sends the camera output to the textureview
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(Dim.getWidth(), Dim.getHeight());
            Surface surface = new Surface(texture);
            capBuild = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            capBuild.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    if (null == cameraDevice) {
                        return;
                    }
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(heartactivity.this, "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void openCamera() {         //creating a default function to open the rear camera
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        Log.e(TAG, "is camera open");
        try {
            cameraID = manager.getCameraIdList()[0];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraID);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            Dim = map.getOutputSizes(SurfaceTexture.class)[0];
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(heartactivity.this, new String[]{Manifest.permission.CAMERA}, Camera_Permission);
                return;
            }
            manager.openCamera(cameraID, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "openCamera X");
    }
    protected void updatePreview() {
        if (null == cameraDevice) {
            Log.e(TAG, "updatePreview error, return");
        }
        capBuild.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
        capBuild.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_TORCH);
        try {
            cameraCaptureSessions.setRepeatingRequest(capBuild.build(), null, BgHandle);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    private void closeCamera() {
        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
        }
    }

    @SuppressLint("MissingSuperCall")       //function to check if permission isn't granted
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Camera_Permission) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(heartactivity.this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        startBackgroundThread();
        if (textureView.isAvailable()) {
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }
    }
    @Override
    protected void onPause() {
        Log.e(TAG, "onPause");
        closeCamera();
        stopBackgroundThread();
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
