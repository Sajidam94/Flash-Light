package personal.sajid.flashlight;


import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Switch flashSwitch;
    private boolean isFlashAvailable;
    static Camera camera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashSwitch = findViewById(R.id.switch1);
        isFlashAvailable = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        Log.d("Rabby", "System has flash: " + Boolean.toString(isFlashAvailable));

        
       flashSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(isFlashAvailable){
                   if(isFlashAvailable){
                       try {
                           if(b){
                               camera = Camera.open();
                               Camera.Parameters parameters = camera.getParameters();
                               parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                               camera.setParameters(parameters);
                               camera.startPreview();
                           }
                           else {
                               camera.stopPreview();
                               camera.release();
                               camera = null;
                           }
                       }
                       catch (Exception e){
                           Log.d("Rabby","error: " + e.getMessage());
                       }

                   }
               }
           }
       });

    }
}
