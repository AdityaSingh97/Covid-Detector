package com.example.mobass1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Size;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private Button hrt;
    private Button symp;
    private Button view;
    DatabaseHelper mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdata = new DatabaseHelper(this);
        //GlobalClass.get().setMdata(mdata);
        //mdata = GlobalClass.get().getmData();

        hrt = (Button) findViewById(R.id.heart);
        hrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, heartactivity.class);
                startActivity(intent);
            }
        });

        symp = (Button) findViewById(R.id.sympbut);
        symp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Symptoms.class);
                startActivity(i);
            }
        });

        view = (Button) findViewById(R.id.viewbut);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mdata.getData();
                if(res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while( res.moveToNext()){
                    buffer.append("Heartrate:" +res.getString(0) + "\n");
                    buffer.append("Respiratory:" +res.getString(1) + "\n");
                    buffer.append("Nausea:" +res.getString(2) + "\n");
                    buffer.append("Headache:" +res.getString(3) + "\n");
                    buffer.append("Diarrhea:" +res.getString(4) + "\n");
                    buffer.append("Sore Throat:" +res.getString(5) + "\n");
                    buffer.append("Fever:" +res.getString(6) + "\n");
                    buffer.append("Muscle Ache:" +res.getString(7) + "\n");
                    buffer.append("Loss of smell/taste:" +res.getString(8) + "\n");
                    buffer.append("Cough:" +res.getString(9) + "\n");
                    buffer.append("Shortness of breath:" +res.getString(10) + "\n");
                    buffer.append("Feeling tired:" +res.getString(11) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }







}