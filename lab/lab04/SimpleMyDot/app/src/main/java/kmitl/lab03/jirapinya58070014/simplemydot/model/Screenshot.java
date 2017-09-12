package kmitl.lab03.jirapinya58070014.simplemydot.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kmitl.lab03.jirapinya58070014.simplemydot.BuildConfig;
import kmitl.lab03.jirapinya58070014.simplemydot.MainActivity;

public class Screenshot {

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
//        Uri uriImage = FileProvider.getUriForFile(context,
//                BuildConfig.APPLICATION_ID + ".provider",
//                imagePath);

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
