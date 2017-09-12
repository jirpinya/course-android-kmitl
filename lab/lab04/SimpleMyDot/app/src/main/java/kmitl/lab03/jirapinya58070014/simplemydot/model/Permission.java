package kmitl.lab03.jirapinya58070014.simplemydot.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import kmitl.lab03.jirapinya58070014.simplemydot.MainActivity;

/**
 * Created by student on 9/12/2017 AD.
 */

public class Permission {

    private final int WRITE_EXTERNAL_REQUEST_CODE = 2;

    private void askPermission(String permission, int requestCode, Context context){
        if(ContextCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(context, new  String[]{permission}, requestCode);
        }
        else{
            Toast.makeText(context, "Permission is Already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, Context context){
        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "WRITE_EXTERNAL Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
