package kmitl.lab03.jirapinya58070014.simplemydot.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Screenshot {

    private File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");

    public static Bitmap takescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);


        return b;
    }

    public static Bitmap takescreenshotOfRootView(View view){
        return takescreenshot(view.getRootView());
    }

    public static Uri saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png"); //path to sd card
        FileOutputStream fos;
        Uri uriImage = Uri.fromFile(imagePath);
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
        return uriImage;
    }
}
