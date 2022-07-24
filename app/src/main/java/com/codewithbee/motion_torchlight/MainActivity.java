package com.codewithbee.motion_torchlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    boolean  isSwitchedOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this,ShakeDetectionService.class);
        startService(i);

    }

    public void toggle(View v) throws CameraAccessException {
        Button button =(Button) v;
        if (button.getText().toString().equals("Switch On"))
        {
            button.setText("Switch Off");
            toggle("on");
        }
        else
        {
            button.setText("Switch On");
            toggle("off");
        }
    }
    private void toggle(String command) throws CameraAccessException {
       if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M)
       {
           CameraManager cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
           String cameraId = null;

           if(cameraManager != null)
           {
               cameraId = cameraManager.getCameraIdList()[0];

           }
           if (cameraManager!= null)
           {
               if (command.equals("on"))
               {
                   cameraManager.setTorchMode(cameraId,true);
                   isSwitchedOn =  true;
               }
               else
               {
                   cameraManager.setTorchMode(cameraId,false);
                   isSwitchedOn = false;

               }
           }
       }
    }
}